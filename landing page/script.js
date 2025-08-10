const toggle = document.getElementById("toggle");
const menu = document.querySelector(".links")

toggle.addEventListener("click",()=>{
    menu.classList.toggle("active");
});