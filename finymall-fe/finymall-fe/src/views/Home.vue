<template>
  <div class="home">
    
    <div style="padding-bottom: 50px">
      <!-- 因为页面刷新，params参数就会消失，用query可以解决这个问题。
      跳转的页面用this.$route.query接受传递过来的参数 -->
      <!-- <router-link :to="{ name: 'Themes', query: { name: 't-1' } }">
        <div class="theme">
          <img class="theme-img" :src="topimg" />
        </div>
      </router-link> -->
      <!-- 搜索栏 -->
      <van-search v-model="value" placeholder="请输入搜索关键词"  />
      <!-- 导航栏 -->

        <van-tabs>
            <van-tab title="推荐"></van-tab>
            <van-tab title="要闻"><list-2 ref="list2"/></van-tab>
            <van-tab title="思想"><list-1 ref="list1"/></van-tab>
            <van-tab title="浙江"><list-2 ref="list2"/></van-tab>
        </van-tabs>
      <!-- 轮播图 -->

      <van-swipe :autoplay="3000">
        <van-swipe-item v-for="(image, index) in imglist" :key="index">
          <router-link :to="{ name: 'Goods', params: { id: image.id } }">
            <img :src="image.img" />
          </router-link>
        </van-swipe-item>
      </van-swipe>
      <!-- 宫格菜单 -->
      <van-grid class="grid" :column-num="3" :border="false">
        <van-grid-item
          v-for="item in categorylist"
          :key="item.id"
          class="iconfont"
          :text="item.name"
          :class="item.img"
          style="font-size: 30px"
          :to="{ name: 'CategoryList', query: { id: item.id, isRoot: true } }"
        >
        </van-grid-item>
      </van-grid>
      <!-- 商品列表 -->
      <goods-list ref="goodslist" />
    </div>
    <nav-bar></nav-bar>
  </div>
</template>

<script>
// @ is an alias to /src
import navBar from "@/components/NavBar.vue";
import GoodsList from "./GoodsList.vue";
import List1 from "./List1.vue";
import List2 from "./List2.vue";
export default {
  name: "Home",
  components: {
    navBar,
    GoodsList,
    List1,
    List2
  },
  data() {
    return {
      toptitle: "",
      topname: "",
      topimg: "",
      imglist: [],
      categorylist: [],
      active: 2,
      value: ''
    };
  },
  created() {
    this.getImgInfo();
    this.getGridMenu();
  },
  methods: {
    getImgInfo() {
      this.$http.get("/theme/detail/t-1").then((result) => {
        this.topname = result.data.name;
        this.toptitle = result.data.title;
        this.topimg = result.data.entranceImg;
      });
      this.$http.get("poster/name/p-1").then((res) => {
        this.imglist = res.data.items;
      });
    },
    getGridMenu() {
      this.$http.get("category/grid").then((res) => {
        this.categorylist = res.data;
      });
    },
  },
};
</script>
<style>
.home {
  background: rgb(229, 223, 211);
}
.theme {
  height: 180px;
}
.theme-img {
  max-height: 100%;
  width: 100%;
}
.van-swipe-item {
  height: 300px;
}
.van-swipe-item img {
  width: 100%;
  height: 100%;
}
.grid {
  background: white;
  border-radius: 10px;
  border: 2px solid rgb(60, 41, 46);
  padding-top: 13px;
  margin: 5px 5px;
  height: 140px;
}
.iconfont {
  color: rgb(111, 139, 145);
  width: 2em;
  height: 0em;
}
</style>
