import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import ToDo from '@/components/Todo'
import Form from '@/components/Form'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },{
      path:'/todo',
      name:'ToDo',
      component:ToDo
    },{
      path:'/form',
      name:'Form',
      component:Form
    }
  ]
})
