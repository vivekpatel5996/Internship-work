<template>
  <div>
      {{name}}
      {{f}}
      {{m}}
    <ul v-for="s in siblings">
        <li>{{s}}</li>
    </ul>
    <ul v-for="o in objects">
        <li>{{o.name}} {{o.age}}</li>
    </ul>
    <ul>
        <li> Todo A </li> 
        <li> Todo B </li> 
        <li> Todo C </li> 
    </ul> 
    <p> From attribute</p>
    <button @click="changename()">change name</button>
   
  </div>
</template>

<script type = "text/javascript" >
export default {
 name:"Todo",
  data(){
      return {
          name:"Vivek",
          siblings:['Trupti','Arti','Vivek','Ruchi'],
          objects:[{name:"Vivek",age:21},{name:"Ruchi",age:19}]
      }
  },
  methods:{
      changename(){
          this.name="Vivek Changed"
      }
  },
  props:['f','m']
};
</script>
<style>

</style>
