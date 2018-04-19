import { mongo } from "mongoose";

var mongoose=require("mongoose");

module.exports=mongoose.model('User',{
    username: String,
    password: String,
    email: String,
    gender: String,
    address: String
});