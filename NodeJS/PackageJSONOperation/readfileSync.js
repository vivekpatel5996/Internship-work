var http=require('http');
var fs = require('fs');




var data=fs.readFileSync('package.json','utf8');
console.log(data);

var replacedData=data.replace(/(\^|~)/g,"");

var returned=fs.writeFileSync('package.json',replacedData);
console.log(returned);

  