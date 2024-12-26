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
    <nav-bar></nav-bar>
  </div>
</template>

<script>
import { Toast } from 'vant';
import NavBar from "@/components/NavBar.vue";
export default {
  name: "GreatPower",
  components: {
    NavBar,
  },
  data() {    
    return {
      value: ''
    };
  },
  methods: {
    onSearch(val) {
      Toast(val);
    },
    onCancel() {
      Toast('取消');
    }
  }
};
</script>

<style>
</style>