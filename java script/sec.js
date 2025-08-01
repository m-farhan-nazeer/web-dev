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


//map function
let arr2 = [3,2,4,55,33,55,222];
let b = arr2.map((value,index,array)=>{
    return value+index
})
console.log(b)

//filter function
let arr3 = [3,2,4,55,33,55,222];
let c = arr3.filter((a)=>{
    return a <5;
})
console.log(c)

//reduce
let arr4 = [3,2,4,55,33,55,222];

const reduce_fun = (h1,h2)=>{
    return h1 + h2
}
let f = arr4.reduce(reduce_fun);
console.log(f)
