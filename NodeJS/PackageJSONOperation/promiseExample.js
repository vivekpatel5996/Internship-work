var http=require('http');
var fs = require('fs');
var  {exec}=require('child_process');

   /*fs.readFile('package.json','utf8',(err,data)=>{
         
        if (err) throw err;
       
        console.log(data);
        var replacedData=data.replace(/(\^|~)/g,"");
        console.log(replacedData);

        fs.writeFile('package.json',replacedData,(err)=>{
       	if(err) throw  err;
       	console.log("saved");
          
        exec('npm install',function(){
        	console.log("executed");
        });
  
       });	         
       
    });*/
function forread()
{

  return new Promise(function(resolve,reject){


    fs.readFile('package.json','utf8',
       (err,data)=>{
        if(err)
          reject(err);

        resolve(data);

       }

      );

  })

}


forread().then(function(data){
  console.log(data);
})