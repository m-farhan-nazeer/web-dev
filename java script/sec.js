let arr = [3,2,4,55,33,55,222]
//sorting alphabatically

arr.sort()
console.log(arr)
let compare = (a , b)=>{
    return a-b
}
let arr1 = [3,2,4,55,33,55,222];
arr1.sort(compare)
console.log(arr1);
// for each loops

arr1.forEach((element)=> {
    console.log(element*element)
})

let name = 'farhan';
let a = Array.from(name);
console.log(a);
for (let i of a){
    console.log(i)
}
