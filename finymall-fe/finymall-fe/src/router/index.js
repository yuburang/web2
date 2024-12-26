import Vue from 'vue'
import VueRouter from 'vue-router'
import GreatPower from '../views/GreatPower.vue'
import Home from '../views/Home.vue'
import Category from '../views/Category.vue'
import Shopcart from '../views/Shopcart.vue'
import My from '../views/My.vue'
import GoodsList from '../views/GoodsList.vue'
import Goods from '../views/Goods.vue'
import Themes from '../views/Themes.vue'
import CategoryList from '../views/CategoryList.vue'
import Login from '../views/Login.vue'
import { Toast } from 'vant'
Vue.use(VueRouter)

const routes = [
  {
    path: '/greatpower',
    name: 'greatpower',
    meta: {
      title: '强国通'
    },
    component: GreatPower
  },{
    // path: '/',
    // name: 'home',
    // meta: {
    //   title: '学习'
    // },
    // component: Home
    path: '/',
    meta: {
      title: '登录'
    },
    component: Login
  }, {
    path: '/home',
    component: Home,
    name: 'home',
    meta: {
      title: '学习'
    }
  }, {
    path: '/category',
    component: Category,
    name: 'category',
    meta: {
      title: '百灵'
    }
  }, {
    path: '/shopcart',
    component: Shopcart,
    name: 'shopcart',
    meta: {
      title: '电视台'
    }
  }, {
    path: '/my',
    component: My,
    name: 'user',
    meta: {
      title: '电台'
    }
  }, {
    path: '/goods',
    component: GoodsList,
    name: 'GoodsList',
    meta: {
      title: "商品列表"
    },
    props: true
  },
  {
    path: '/goodsInfo/:id',
    component: Goods,
    name: 'Goods',
    meta: {
      title: "新闻详情",
    },
    props: true
  },
  {
    path: '/home/themes',
    component: Themes,
    name: 'Themes',
    meta: {
      title: '专题详情'
    },
    props: true
  },
  {
    path: '/category/categorylist',
    component: CategoryList,
    name: 'CategoryList',
    meta: {
      title: '新闻分类'
    },
    props: true,
  }, {
    path: '/user/login',
    meta: {
      title: '登录'
    },
    component: Login
  }
]

const router = new VueRouter({
  routes
})

// 添加导航守卫
router.beforeEach((to, from, next) => {
  // 需要token才能访问的页面
  let needTokenPage = ['buy', 'shopcart', 'order', 'my', 'adress']
  if (!needTokenPage.includes(to.name)) return next();

  const token = localStorage.getItem('Authorization')
  if (!token) {
    Toast.fail('请先登录')
    return next('/user/login')
  }
  next()
})

export default router
