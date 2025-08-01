let a = 'farhan';
let b = 6;
console.log(a+b)
console.log(typeof(a))
let c={
    name:"farhan",
    age:23
}
console.log(c);
c['name']='ali';
console.log(c.name)
c={};
console.log(c)
let d = 6;
let f = '6';
console.log(d==f);
console.log(d===f)
// let h = prompt("enter your age");

//switch statement 

let fruit = 'banana';
switch(fruit){
    case "banana":
        console.log("banasa are very expensive")
        break
    case "apple":
        console.log("An apple a day feels better ")
        break

    default:
        console.log("we are out of that fruit")


}
let age = 10;
console.log("you can ",age<18?"not drive":"drive")

//loops

for (let i=0;i<5;i++){
    console.log(i)
}
let obj ={
    ajeem:54,
    ali:38,
    hd:33
}
for(let a in obj){
    console.log("marks of "+a +"is : "+obj[a])
}

//while loops

n = 5;
i=10;
// while(i<=n){
//     console.log(i);
//     i++;
// }

//do while
do{
    console.log(i);
    i++;
}while(i<=n);


//template strings
let boy1 = 'ali';
let boy2 = 'Ahmad';
let sentence = `${boy1} is the friend of ${boy2}`
console.log(sentence)






