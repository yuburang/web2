import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Vant from 'vant';
import 'vant/lib/index.css';
import axios from './axios.js'
import './assets/font_2943783_tefxe4eipwq/iconfont.css'
import './assets/font_3238290_55qg7a7836q/iconfont.css'
import waterfall from 'vue-waterfall2'
import auth from './auth.js'
import { Search } from 'vant';
Vue.use(auth);//需要放在axios前面
Vue.use(waterfall);
Vue.use(axios);
Vue.use(Vant);
Vue.use(Search);
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
