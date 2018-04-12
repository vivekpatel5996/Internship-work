    var url = require('url');  
var bodyParser=require("body-parser"); 
var controller=require('../controllers/home');

module.exports=function(app){
   
    app.use(bodyParser.json());

    app.use(bodyParser.urlencoded({ 
	    extended: true
    }));
   
    app.get("/users",(req,res)=>{
    	
    	controller.getusers(req,res);
    });

   app.get("/users/:id",(req,res)=>{
    	
    	controller.getuserbyid	(req,res);
    });


    app.post("/users",function(req,res){
        controller.saveuser(req,res);
	});	


	app.delete("/users/:id",function(req,res){
        controller.deleteuser(req,res);
    });

    app.put("/users/:id",function(req,res){
    	controller.updateuser(req,res);
    })

};