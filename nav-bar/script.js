const toggle = document.getElementById("togglebtn");
const btn = document.querySelector(".navi");
toggle.addEventListener("click",()=>{
    btn.classList.toggle("active");
})