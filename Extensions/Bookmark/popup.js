'use strict';

let changeColor = document.getElementById('changeColor');
let c;
chrome.storage.sync.get('color', function(data) {
  changeColor.style.backgroundColor = data.color;
  c=data.color;
  changeColor.setAttribute('value', data.color);

  
});

changeColor.onclick=function(element){
    console.log("Here in");
    
    let color = changeColor.style.backgroundColor;
    document.getElementById('demo').textContent=color;
    chrome.tabs.executeScript(
      
        {code: 'document.body.style.backgroundColor ="'+color+'";'});
    
}
//Auth 210632742040-4ieebsq5gg5jhkvksg4fm64mdvppcu3m.apps.googleusercontent.com

//Api AIzaSyBXA_BPzVswCY-VKwAWtjw5mx6tPHY1ubM


// window.onload = function() {
//     var a =0;
//     function myFunction()
//     {
//         a= a +1;
//         document.getElementById('demo').textContent=a;
//         console.log("here");
//     }
//     document.getElementById('thing').onclick = myFunction;
// }
// window.addEventListener('load',function load(event){
//     changeColor.onclick=function(){
//         console.log("here inside");
//     }
// });

// changeColor.addEventListener('click',function(){
//     console.log("here inside");
// });
// changeColor.onclick = function(element) {
//     let color = element.target.value;
//     console.log(color);
//       chrome.tabs.executeScript(
//           tabs[0].id,
//           {code: 'document.body.style.backgroundColor = "' + color + '";'});
//   };

