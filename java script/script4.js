//promises

// let p1 = new Promise((resolve,reject)=>{
//     console.log("promise is pending")
//     setTimeout(() =>{
//         console.log("promise fullfilled")
//         reject(new Error("err"))
//     },4000)

// })
// // let p2 = new Promise((resolve,reject)=>{
// //     console.log("promise is pending")
// //     setTimeout(() =>{
// //         console.log("rejected")
// //         reject(new Error("i am an error"))
// //     },5000)

// //})

// p1.then((value)=>{
//     console.log("val"+value)
// },(error)=>{
//     console.log(error)
// })

// p2.catch((error)=>{
//     console.log("some error has occured")

// })


//------------Nested promises

let promise1 = new Promise((resolve,reject)=>{
    setTimeout(()=>{
        console.log("promise1")
        resolve(true)
    },2000)
})

promise1.then((value)=>{
    let promise2 = new Promise((resolve,reject)=>{
        setTimeout(() => {
            console.log("promise 2")
        },2000)
    })
}).then((value)=>{
    console.log('finished')
})