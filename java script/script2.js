element = document.getElementById("home");
element.style.color = "red";

Array.from(document.getElementsByTagName("li")).forEach((element )=> {
    element.style.background = "purple";
    
})

const x = (event) => {
      alert("you clicked button1");
    };

    const y = (event) => {
      alert("you clicked button2");
    };

    const z = (event) => {
      alert("you clicked button3");
    };

    let btn1 = document.getElementsByClassName("btn1")[0];
    let btn2 = document.getElementsByClassName("btn2")[0];
    let btn3 = document.getElementsByClassName("btn3")[0];

    if (btn1) btn1.addEventListener("click", x);
    if (btn2) btn2.addEventListener("click", y);
    if (btn3) btn3.addEventListener("click", z);

    document.getElementById("google").addEventListener("click",function(){
        window.location=("https://google.com")
    })

    setInterval(async function() {
        document.querySelector('#bulb').classList.toggle("bulb")
        
    },300)

