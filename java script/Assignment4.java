// File: client/Student.java
package client;

import java.io.Serializable;

public class Student implements Serializable {
    private String rollNo;
    private String name;
    private String degree;
    private String semester;

    public Student(String rollNo, String name, String degree, String semester) {
        this.rollNo = rollNo;
        this.name = name;
        this.degree = degree;
        this.semester = semester;
    }

    public String getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getDegree() { return degree; }
    public String getSemester() { return semester; }
}

// File: client/ClientMain.java
package client;

public class ClientMain {
    public static void main(String[] args) {
        new GUI();
    }
}

// File: client/GUI.java
package client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class GUI extends JFrame {
    private JTextField rollField, nameField, degreeField, semesterField;
    private DefaultTableModel tableModel;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public GUI() {
        try {
            socket = new Socket("localhost", 12345);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Cannot connect to server");
            System.exit(1);
        }

        setTitle("Student Management System");
        setSize(600, 500);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Roll No:"));
        rollField = new JTextField();
        inputPanel.add(rollField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Degree:"));
        degreeField = new JTextField();
        inputPanel.add(degreeField);

        inputPanel.add(new JLabel("Semester:"));
        semesterField = new JTextField();
        inputPanel.add(semesterField);

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton viewBtn = new JButton("View All");

        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(viewBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.CENTER);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Roll No", "Name", "Degree", "Semester"});
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.SOUTH);

        addBtn.addActionListener(e -> sendCommand("ADD"));
        updateBtn.addActionListener(e -> sendCommand("UPDATE"));
        deleteBtn.addActionListener(e -> sendCommand("DELETE"));
        viewBtn.addActionListener(e -> sendCommand("VIEW"));

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void sendCommand(String command) {
        try {
            String roll = rollField.getText().trim();
            String name = nameField.getText().trim();
            String degree = degreeField.getText().trim();
            String semester = semesterField.getText().trim();

            if ((command.equals("ADD") || command.equals("UPDATE")) &&
                (roll.isEmpty() || name.isEmpty() || degree.isEmpty() || semester.isEmpty())) {
                JOptionPane.showMessageDialog(this, "All fields must be filled");
                return;
            }

            if (!roll.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Roll No must be numeric");
                return;
            }

            out.writeUTF(command);
            out.flush();

            if (!command.equals("VIEW")) {
                out.writeObject(new Student(roll, name, degree, semester));
                out.flush();
            }

            Object response = in.readObject();
            if (response instanceof String) {
                JOptionPane.showMessageDialog(this, response.toString());
            } else if (response instanceof Vector) {
                tableModel.setRowCount(0);
                for (Object row : (Vector<?>) response) {
                    tableModel.addRow((Vector<?>) row);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}

// File: server/ServerMain.java
package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Vector;

public class ServerMain {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(12345)) {
            Socket client = server.accept();
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            DatabaseHandler db = new DatabaseHandler();

            while (true) {
                String command = in.readUTF();
                if (command.equals("VIEW")) {
                    out.writeObject(db.viewStudents());
                } else {
                    Student s = (Student) in.readObject();
                    String result;
                    switch (command) {
                        case "ADD": result = db.addStudent(s); break;
                        case "UPDATE": result = db.updateStudent(s); break;
                        case "DELETE": result = db.deleteStudent(s.getRollNo()); break;
                        default: result = "Unknown command";
                    }
                    out.writeObject(result);
                }
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// File: server/DatabaseHandler.java
package server;

import client.Student;

import java.sql.*;
import java.util.Vector;

public class DatabaseHandler {
    private Connection conn;

    public DatabaseHandler() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:students.db");
        String sql = "CREATE TABLE IF NOT EXISTS Students (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "roll_no TEXT NOT NULL UNIQUE, " +
                     "name TEXT NOT NULL, " +
                     "degree TEXT, " +
                     "semester TEXT);";
        conn.createStatement().execute(sql);
    }

    public String addStudent(Student s) {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Students (roll_no, name, degree, semester) VALUES (?, ?, ?, ?);")) {
            ps.setString(1, s.getRollNo());
            ps.setString(2, s.getName());
            ps.setString(3, s.getDegree());
            ps.setString(4, s.getSemester());
            ps.executeUpdate();
            return "Student added successfully";
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String updateStudent(Student s) {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE Students SET name=?, degree=?, semester=? WHERE roll_no=?;")) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getDegree());
            ps.setString(3, s.getSemester());
            ps.setString(4, s.getRollNo());
            int rows = ps.executeUpdate();
            return rows > 0 ? "Student updated successfully" : "Student not found";
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String deleteStudent(String rollNo) {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Students WHERE roll_no = ?;")) {
            ps.setString(1, rollNo);
            int rows = ps.executeUpdate();
            return rows > 0 ? "Student deleted successfully" : "Student not found";
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }

    public Vector<Vector<String>> viewStudents() {
        Vector<Vector<String>> data = new Vector<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT roll_no, name, degree, semester FROM Students")) {
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("roll_no"));
                row.add(rs.getString("name"));
                row.add(rs.getString("degree"));
                row.add(rs.getString("semester"));
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}