var express=require("express");
var app=express();
const MongoClient = require('mongodb').MongoClient;
const assert = require('assert');

var bodyParser=require("body-parser"); 



// Connection URL
const url = 'mongodb://localhost:27017';
 
// Database Name
const dbName = 'demodb';

// Use connect method to connect to the server
MongoClient.connect(url, function(err, client) {
  assert.equal(null, err);
  console.log("Connected successfully to server");
  
   db = client.db(dbName);
  
   collection = db.collection('details');
  //  insertDocuments(db, function() {
  //   client.close();
  // });
  //client.close();
});


const insertDocuments = function(db, callback) {
  // Get the documents collection
  const collection = db.collection('documents');
  
 var data = { name : "rishabhio" , age : "25" , mobile : "1234567890" };

  collection.insertOne(data,function(err,data){

       if(err)
       	throw err;

       console.log("inserted");

  });
}

app.use('/public', express.static(__dirname + '/public'));
app.use( bodyParser.json() );

app.use(bodyParser.urlencoded({ // to support URL-encoded bodies
	extended: true
}));

app.get('/',function(req,res){


   return res.redirect("/public/login.html");
	
});

app.post("/users",function(req,res){
	var name=req.body.name;
	var password=req.body.password;
	var userid=req.body.userid;
	console.log(name+"     "+password);
    var userdata={"name":name,"password":password,"id":userid};
    console.log(db);
      
      collection.insertOne(userdata,function(err,userdata){

       if(err){
        var jsondata={"status":"problem"};
        res.send(jsondata);   	
       	//throw err;
       }
      
       console.log("inserted");
       
      });

   res.set({
		      'Access-Control-Allow-Origin' : '*'
         });
    
   	console.log("record inserted successfully");
   	var jsondata={"status":"Ok"};

   	res.send(jsondata);
   });	



app.get("/users",function(req,res){


    collection.find({}).toArray((err,collection)=>{

             if(err)
             	 throw  err;
             res.set({
		           'Access-Control-Allow-Origin' : '*'
         	});
             res.send(JSON.stringify(collection));	

          });  

});


app.delete("/users/:id",function(req,res){

   var query = { id: req.params.id };
	
	//Accessing the collection
	db.collection("details").deleteMany(query , (err , collection) => {
		if(err) throw err;
		// console.log(collection.result.n + " Record(s) deleted successfully");
		// console.log(collection);
		
	});
    console.log("delete id:"+req.params.id);
   
});


app.listen(3000, function () {

  console.log('Server is listening at 3000')
});
		

