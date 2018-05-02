

var collection = db.collection('details');

exports.getAllUsers=function(req,res){
	//res.send("from model");
	collection.find({}).toArray((err,allusers)=>{

             if(err)
             	 throw  err;

             	//res.setHeader('Content-Type', 'application/json');
            
                res.json(allusers);	

          });  
	
};


exports.getUserByID=function(req,res){

      var userid=req.params.id;

      console.log("userid"+userid);
      db.collection("details").findOne({id:userid}, (err , user) => {
		if(err) throw err;
		console.log("Record Read successfully");
		console.log(user);
		res.send(user);
	});
};


exports.saveUser=function(req,res){
	var name=req.body.name;
	var password=req.body.password;
	var userid=req.body.id;
	var email=req.body.email;
	var gender=req.body.gender;
	var skills={};
	skills=req.body.skills;
	console.log(skills);
	console.log(name+"     "+password);
    var userdata={"name":name,"password":password,"id":userid,"email":email,"gender":gender,"skills":req.body.skills};
   // console.log(db);
      
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
};


exports.deleteUser=function(req,res){

 var query = { id: req.params.id };
	
	
	db.collection("details").deleteMany(query , (err,collection) => {
		if(err) throw err;
    });
      
    console.log("delete id:"+req.params.id);
 
};

exports.updateUser=function(req,res){
	//Query parameter is used to search the collection.
	var name=req.body.name;
	var password=req.body.password;
	var userid=req.body.id;
	var email=req.body.email;
	var gender=req.body.gender;
	console.log( req.params.id);
	var query = { "id" : req.params.id };
	console.log( req.params.id);
	//And When the query matches the data in the DB , "data" parameter is used to update the value.
	var data = { "name" : "vivek updated","password":"pwd updated"};
	//Accessing the collection using nodejs
	db.collection("details").updateOne({ "id" : req.params.id } ,{$set:{"name":name,"password":password,"id":userid,"email":email,"gender":gender}}, (err , collection) => {
		if(err) throw err;
		console.log("Record updated successfully");
		
	});
<<<<<<< HEAD
};



exports.simpleMethod=function(req,res){
	//Call HTTP Method for REST Api
	//OR
	// Interaction with database
}
=======
};
>>>>>>> a45cf734d8238947a4a51546577655dccc505a28
