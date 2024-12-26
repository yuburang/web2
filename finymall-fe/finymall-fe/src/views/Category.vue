<template>
  <div>
    <div>
      <!-- 搜索栏 -->
      <van-search v-model="value" placeholder="请输入搜索关键词" />
      <!-- 选择栏 -->
      <van-tabs>
            <van-tab title="推荐"></van-tab>
            <van-tab title="党史"></van-tab>
            <van-tab title="竖"></van-tab>
            <van-tab title="炫"></van-tab>
            <van-tab title="窗"></van-tab>
        </van-tabs>
      <!-- 左侧根分类 -->
      <van-sidebar v-model="activeKey" style="float: left">
        <van-sidebar-item
          v-for="item in rootList"
          :key="item.id"
          :title="item.name"
          @click="selectRootCategory(item.id)"
        />
      </van-sidebar>
      <!-- 右侧根分类 -->
      <van-grid class="grid1" :column-num="3" :border="false">
        <van-grid-item v-for="item in currentSubList" :key="item.id">
          <router-link :to="{ name: 'CategoryList', query: { id: item.id } }">
            <div class="images">
              <img :src="item.img" class="imgs" />
            </div>
            <div>{{ item.name }}</div>
          </router-link>
        </van-grid-item>
      </van-grid>
    </div>
    <nav-bar></nav-bar>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
export default {
  name: "Category",
  components: {
    NavBar,
  },
  data() {
    return {
      rootList: [],
      allSubList: [],
      currentSubList: [],
      activeKey: 0,
      value: ''
    };
  },
  created() {
    this.getAllCategory();
  },
  methods: {
    getAllCategory() {
      this.$http.get("category/all").then((res) => {
        this.rootList = res.data.roots;
        this.allSubList = res.data.subs;
        this.selectRootCategory(this.rootList[0].id);
      });
    },
    selectRootCategory(rootId) {
      this.currentSubList = this.allSubList.filter(
        (item) => item.parentId === rootId
      );
    },
  },
};
</script>

<style>
.images {
  height: 60px;
}
.images img {
  max-width: 60px;
  max-height: 50px;
}
a {
  text-decoration: none;
  color: black;
}
.grid1 {
  padding-top: 13px;
  margin: 5px 5px;
  height: 140px;
}
</style>