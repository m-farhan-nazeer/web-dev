const menu = document.querySelector('#mobile-menu');
const menuList = document.querySelector('.navbar__menu');

menu.addEventListener('click',function(){
    menu.classList.toggle('is-active')
    menuList.classList.toggle('active')
})