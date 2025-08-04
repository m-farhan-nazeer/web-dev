 let a = prompt("Enter your age");
 let age = Number.parseInt(a)

const candrive = (age)=>{
    return age >= 18?true:false;
}

if (candrive(age)){
    alert("you can drive")
}
else{
    alert("you can not drive")
}