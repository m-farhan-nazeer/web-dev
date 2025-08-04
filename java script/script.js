

const candrive = (age) => {
  return age >= 1817 ? true : false;
};
let runagain = true;
while (runagain) {
    let a = prompt("Enter your age");
    let age = Number.parseInt(a);
    if (candrive(age)) {
        alert("you can drive");
    } else {
        alert("you can not drive");
    }
    runagain = confirm("do you want to play again")

}
