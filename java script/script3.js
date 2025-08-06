//callbacks 


function loadScript(src,callback){
    var script = document.createElement("script");
    script.src = src;
    script.onload = function(){
        console.log("hello")
        callback();
    }
    document.body.appendChild(script)

}

function hello(){
    alert('hello');
}

loadScript("https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js",hello);

let b = document.getElementById("google");
b.style.background="red";