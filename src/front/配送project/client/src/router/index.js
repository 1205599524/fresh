import Vue from 'vue'
import Router from 'vue-router'
import recode from '@/components/recode'
import all from '../components/all'
import change from '../components/change'
import derict from '../components/derict'
import match from '../components/match'
import fenlei from '../components/fenlei'
import allcount from '../components/allcount'
import cpfl from '../components/cpfl'
import dpfl from '../components/dpfl'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'recode',
      redirect:'/recode'
    },
    {path:'/recode',name:'recode',component:recode,children:[{path:'/recode/match',component:match}]},
    // {path:'/recode/match',name:'match',component:match},
    {path:'/all',name:'all',component:all},
    {path:'/change',name:'change',component:change},
    {path:'/derict',name:'derict',component:derict},
    {path:'/fenlei',name:'fenlei',component:fenlei},
    {path:'/allcount',name:'fenlei',component:allcount},
    {path:'/dpfl',name:'fenlei',component:dpfl},
    {path:'/cpfl',name:'fenlei',component:cpfl},

  ],
  linkActiveClass:'mui-active'
})
