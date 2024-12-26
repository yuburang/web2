<template>
  <div>

     <van-swipe :autoplay="3000">
        <van-swipe-item v-for="(image, index) in imglist" :key="index">
          <router-link :to="{ name: 'Goods', params: { id: image.id } }">
            <img :src="image.img" />
          </router-link>
        </van-swipe-item>
      </van-swipe>

      <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
    <van-list
      v-model="loading"
      :finished="finished"
      finished-text="没有更多了"
      @load="onLoad"
     
      >
      <!--循环列表数据-->
    <van-cell v-for="item in list" 
    :key="item"
     :title="item">
     
    <template #title>
        <span class="title">
            标题
        </span>
    </template>
    <template #label>
        <span>发布人</span>&nbsp;&nbsp;
        <span>数字</span>&nbsp;&nbsp;
        <span>时间</span>
    </template>
    <template #right-icon>
        <van-image width="100" height="100" src="https://boot-img.xuexi.cn/lego/image/1564_first-slider/ae65712d54a54d949331e9e874cf938e.jpg">
        </van-image>
    </template>
  </van-cell>
  
    </van-list>
      </van-pull-refresh>
    </div>
</template>

<script>
export default {
name:"List2",
 data() {
    return {
      //列表
      loading:false,
      finished:false,
      list:[],
      refreshing:false
    };
  },
  methods: {
    //被@load调用的方法
    onLoad(){//若加载条到了底部
    setTimeout(() => {
      if(this.refreshing){
        this.list = [];
        this.refreshing = false;
      }
      for(let i = 0;i<10;i++){
        this.list.push(this.list.length + 1);
      }
     this.loading = false;

     if(this.list.length>=40){
       this.finished = true;
     }
    },1000);
    },
    onRefresh(){
      //清空列表数据
      this.finished = false;
      //重新加载数据
      //将loading设置为true，表示处于加载状态
      this.loading = ture;
      this.onLoad();
    },
    }
}
</script>

<style>

</style>