<template>
  <div>
   {{ip}}
   {{x}}
   <h1>Your IP is {{ ip }}</h1>
        <input type="text" v-model="input.firstname" placeholder="First Name" />
        <input type="text" v-model="input.lastname" placeholder="Last Name" />
        <button v-on:click="sendData()">Send</button>
        <br />
        <br />
        <textarea>{{ response }}</textarea>
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: "HelloWorld",
  data() {
    return {
      ip:"",
      x:5,
      input:{
        firstname:"",
        lastname:""
      },
      response:""
    }
  },
  beforeCreate:function(){console.log("x is b"+this.x)},
  created:function(){console.log("x is "+this.x)},
  mounted(){

   // This is using Axios library
     /*axios({
      method:"GET",
      "url":"https://httpbin.org/ip"
      }).then(result=>{
        this.ip=result.data.origin;
        console.log("inside");
        console.log(result);
      },error=>{
           Console.error(error);
      });
      */
    
    // This is using vue-resource
    this.$http.get("https://httpbin.org/ip").then(
      result=>{
        this.ip=result.body.origin;
      },
      error=>{
        console.error(error);
    });
  },
  methods:{
    sendData(){
    //This is using axios
    
    /*axios({method:"POST","url":"https://httpbin.org/post","data":this.input,"headers":{"content-type":"application/json"}}).
    then(result=>{this.response=result.data},
    error=>{
      console.error(error);
    });*/
    
    //This is using Vue-resource
    this.$http.post("https://httpbin.org/post",this.input,{headers:{"content-type":"application/json"}}).
    then(result=>{this.response=result.data;},
    error=>{
      console.error(error);
    })
    }
  }
};
</script>

<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
