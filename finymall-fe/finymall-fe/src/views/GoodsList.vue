<template>
  <div ref="goodslist">
    <waterfall :col="2" :data="spuList" :loadDistance="10" @loadmore="loadmore">
      <template>
        <div class="goodslist" v-for="(item, index) in spuList" :key="index">
          <router-link :to="{ name: 'Goods', params: { id: item.id } }">
            <img :src="item.img" />
            <p class="goodslist-title">{{ item.title }}</p>
            <div class="goodslist-tags-out">
              <van-tag
                v-for="(item2, index) in item.tags"
                :key="index"
                type="primary"
                class="goodslist-tags"
                >{{ item2 }}</van-tag
              >
            </div>
            <p class="goodslist-price">{{ item.price }}</p>
            <p class="goodslist-subtitle">{{ item.description }}</p>
          </router-link>
        </div>
      </template>
    </waterfall>
    <div class="tips">{{ tips }}</div>
  </div>
</template>

<script>
export default {
  name: "GoodsList",
  data() {
    return {
      spuList: [], //商品列表
      pageSize: 5, //分页请求数
      pageStart: 0, //本次分页请求起始计数值
      stillLoading: true,
    };
  },
  methods: {
    getSpuByPage() {
      this.$http
        .get("/spu/page", {
          params: { count: this.pageSize, start: this.pageStart },
        })
        .then((res) => {
          //得到当前分页结果的列表数据
          let currentPageResult = res.data.list;
          currentPageResult.forEach((item) => {
            if (item.tags && item.tags != "") {
              item.tags = item.tags.split("$");
            }
          });
          //当前分页结果添加到整个商品列表
          this.spuList.push(...currentPageResult);
          console.log(this.spuList);
          //根据分页结果数量，判断是否到底了
          if (currentPageResult.length < this.pageSize) {
            this.stillLoading = false;
          }
        });
    },
    loadmore(index) {
      if (!this.stillLoading) {
        return;
      }
      // 发起新的一次分页请求，要先计算好起始值
      this.pageStart += this.pageSize;
      this.getSpuByPage();
    },
  },
  computed: {
    tips() {
      return this.stillLoading ? "拼命加载中..." : "没有更多了";
    },
  },
};
</script>

<style scoped>
@media screen and (max-width: 800px) {
  .goodslist {
    background: white;
    text-align: left;
    float: left;
    width: 170px;
    margin: 8px 8px;
    padding-bottom: 10px;
    box-shadow: 0 0 4px rgb(64, 44, 47);
    border-radius: 8px 8px 8px 8px;
  }
  .goodslist img {
    border-radius: 8px 8px 0 0;
    width: 100%;
    max-width: 100%;
    max-height: 100%;
  }
}
@media screen and (min-width: 820px) {
  .goodslist {
    background: white;
    text-align: center;
    float: left;
    width: 170px;
    margin: 8px 8px;
    padding-bottom: 10px;
    box-shadow: 0 0 4px rbg(64, 44, 47);
    border-radius: 8px 8px 8px 8px;
  }
  .goodslist img {
    border-radius: 8px 8px 0 0;
    width: 100%;
    max-width: 100%;
    max-height: 100%;
  }
}
.goodslist-subtitle {
  word-wrap: break-word;
  padding: 5px 10px 0 11px;
  color: rbg(95, 126, 151);
  font-size: 12px;
}
.goodslist-background {
  height: 500px;
  background-color: rbg(169, 221, 187);
}
.goodslist-title {
  font-size: 15px;
  color: black;
  padding: 2px 10px 0 11px;
}
.goodslist-tags {
  background: rgb(201, 174, 115);
  font-size: 5px;
  padding-top: 2px;
  margin-top: 2px;
  margin-left: 4px;
  color: white;
}
.goodslist-tags-out {
  margin-left: 6px;
}
.goodslist-price {
  padding-top: 5px;
  color: rbg(62, 62, 131);
  padding-left: 10px;
}
.tips {
  color: burlywood;
}
</style>