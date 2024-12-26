<template>
  <div>
    <div>
      <form action="/">
        <van-search
          v-model="value"
          show-action
          placeholder="请输入搜索关键词"
          @search="onSearch"
          @cancel="onCancel"
        />
      </form>
    </div>
        <van-tabs>
            <van-tab title="听同期声"></van-tab>
            <van-tab title="听原著"><list-2 ref="list2"/></van-tab>
            <van-tab title="强国之声"><list-1 ref="list1"/></van-tab>
            <van-tab title="听广播"><list-1 ref="list1"/></van-tab>
        </van-tabs>
        <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
            <van-list
              v-model="loading"
              :finished="finished"
              finished-text="没有更多了"
              @load="onLoad"
            >
            <van-cell v-for="item in list" :key="item" :title="item" />
            </van-list>
  </van-pull-refresh>
    <nav-bar></nav-bar>
  </div>
</template>

<script>
import { ref } from 'vue';
import { Toast } from 'vant';
import NavBar from "@/components/NavBar.vue";
import List1 from "./List1.vue";
import List2 from "./List2.vue";
export default {
  name: "My",
  components: {
    NavBar,
    List1,
    List2,
  },
  data() {    
    return {
      value: '',
      list: [],
      loading: false,
      finished: false,
      refreshing: false
    };
  },
  created() {
    
    
  },
  methods: {
    
    onSearch(val) {
      Toast(val);
    },
    onCancel() {
      Toast('取消');
    },
    onLoad() {
      setTimeout(() => {
        if (this.refreshing) {
          this.list = [];
          this.refreshing = false;
        }

        for (let i = 0; i < 10; i++) {
          this.list.push("新闻");
        }
        this.loading = false;

        if (this.list.length >= 40) {
          this.finished = true;
        }
      }, 1000);
    },
    onRefresh() {
      // 清空列表数据
      this.finished = false;

      // 重新加载数据
      // 将 loading 设置为 true，表示处于加载状态
      this.loading = true;
      this.onLoad();
    }
  }
};
</script>

<style>
</style>