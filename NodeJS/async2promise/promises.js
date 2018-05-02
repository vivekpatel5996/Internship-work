/*function  waterfall()
{
	return new Promise(function(resolve,reject){
		resolve(1);
    });	
}


waterfall().then(function(data){
   var add=5;
   var result=Number(data)+add;
   console.log('%d + %d = %d',data, add, result);
   return result;
}).then(function(result){
    var add=10;
	var finalresult=Number(result)+add;
	 console.log('%d + %d = %d',result, add, finalresult);
    return finalresult;
}).then(function(finalresult){
	console.log("Finished");
});
*/

function seriescall(){

return new Promise(function(resolve,reject){
		resolve();
    });	
    
}

var message="";

seriescall().then(function(){
	message+="First ";
}).then(function(){
	message+="Second ";
}).then(function(){
    message+="Third ";
}).then(function(){
    console.log("Finale message: "+message);
});



