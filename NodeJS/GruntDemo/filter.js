var express = require('express')
var cron = require('node-cron');
var app = express()

// var myLogger = function (req, res, next) {
//   console.log('LOGGED')
//   //console.log( 
  	
  	
//   next();
// }


 
cron.schedule('1 * * * * *', function(){
  console.log('running a task every minute');
});

app.get('/user', function (req, res) {
  res.send(req.query.p);
})
// app.use(myLogger);	
app.listen(3000);
