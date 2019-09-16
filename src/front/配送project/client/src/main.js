// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Mint from 'mint-ui';
// import './assets/mui/css/mui.min.css'
// import  './assets/mui/fonts/mui.ttf'
// import  './assets/mui/fonts/mui-icons-extra.ttf'
// import './assets/mui/js/mui.min.js'
// import './assets/mui/css/icons-extra.css'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Vant from 'vant';
import 'vant/lib/index.css';
import axios from 'axios'
import store from './store/store.js'
import { Message,Loading } from 'element-ui';

let loading;
function startloading(){
    loading=Loading.service({
        lock:true,
        text:'拼命加载中',
        background:'rgba(0.0.0.0.7)'
    });
}
function endloading(){
    loading.close();
}
axios.interceptors.request.use(config=>{
    startloading();
   
    
    return config;
},error=>{
    return Promise.reject(error)
});
axios.interceptors.response.use(response=>{
    endloading();
    return response;
},error=>{
    endloading();
    // Message.error(error.response.data);
    return Promise.reject(error);
})
Vue.prototype.$axios=axios;

Vue.use(Vant);
Vue.use(Mint);
Vue.use(ElementUI);

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})
