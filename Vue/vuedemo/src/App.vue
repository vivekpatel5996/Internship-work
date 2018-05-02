<template>
  <div id="app">
    <img src="./assets/logo.png">
   
    <!-- <router-view/> -->
    <todo v-bind:f="f" v-bind:m="m"></todo>
  </div>
</template>
<script>
import Todo from './components/Todo'
import Form from './components/Form'
export default {
  name: 'App',
  data: function() {
    return {
    f:"J.L.Patel",
    m:"Daxa Patel"
    }
  },
  components:{Todo}
};
</script>
<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
