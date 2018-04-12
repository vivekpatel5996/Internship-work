var express=require("express");
var app=express();

app.use("/",(req,res,next)=>{

res.setHeader('Access-Control-Allow-Origin', '*');
   res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
   res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');
   res.setHeader('Access-Control-Allow-Credentials', false);
   next();
});

var winston = require('winston');
 const logger = new (winston.Logger)({
  transports: [
    // colorize the output to the console
    new (winston.transports.Console)({
          colorize: true,
          
     }),
    new (winston.transports.File)({
      filename: 'results.log',
      colorize:true,
    })
  ]
});

logger.level = 'debug';
logger.info('Hello world');
logger.debug('Debugging info');
 
   
const assert = require('assert');

const MongoClient = require('mongodb').MongoClient;

// Connection URL
const url = 'mongodb://localhost:27017';
 
// Database Name
const dbName = 'demodb';


MongoClient.connect(url, function(err, client) {
  assert.equal(null, err);
  console.log("Connected successfully to server");
   global.db = client.db(dbName);

   app.listen(3000, function (req,res) {
	  require('./router')(app);	
      console.log('Server is listening at 3000');
   });
  
});



