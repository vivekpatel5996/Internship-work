
var model=require('../model/crudops');


exports.getusers=function(req,res){
    model.getAllUsers(req,res);
};

exports.getuserbyid=function(req,res){
    model.getUserByID(req,res);
};


exports.saveuser=function(req,res){
    model.saveUser(req,res);
};


exports.deleteuser=function(req,res){
   model.deleteUser(req,res);
};

exports.updateuser=function(req,res){
   model.updateUser(req,res);
<<<<<<< HEAD
};
=======
};
>>>>>>> a45cf734d8238947a4a51546577655dccc505a28
