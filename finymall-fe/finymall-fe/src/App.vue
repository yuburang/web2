<template>
  <div id="app">
    <!-- 顶部导航栏 -->
    <van-nav-bar :fixed="true" :title="$route.meta.title">
      <template #left>
        <van-icon
          name="arrow-left"
          size="20"
          color="#000"
          @click="goBack()"
          v-show="showBack"
        ></van-icon>
      </template>
    </van-nav-bar>
    <!-- 页面主体，通过前端路由切换 -->
    <router-view style="padding: 46px 0 0 0" />
  </div>
</template>
<script>
export default {
  data() {
    return {
      showBack: false,
      noBackRoutes: ["/home", "/my", "/category", "/shopcart"],
    };
  },
  created() {
    this.showBack = !this.noBackRoutes.includes(this.$route.path);
  },
  watch: {
    "$route.path"(newval) {
      this.showBack = !this.noBackRoutes.includes(newval);
    },
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>
<style>
* {
  margin: 0;
  padding: 0;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
