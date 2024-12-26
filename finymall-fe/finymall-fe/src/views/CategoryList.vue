<template>
  <div>
    <waterfall :col="2" :data="flowGoodsList" :loadDistance="10">
      <template>
        <div
          class="goodslist"
          v-for="(item, index) in flowGoodsList"
          :key="index"
        >
          <router-link :to="{ name: 'Goods', params: { id: item.id } }">
            <img :src="item.img" />
            <p class="goodslist-title">{{ item.title }}</p>
            <div class="goodslist-tags-out">
              <van-tag
                v-for="(item2, index) in item.tags"
                type="primary"
                :key="index"
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
  </div>
</template>

<script>
export default {
  name: "CategoryList",
  data() {
    return {
      flowGoodsList: [], //商品列表，瀑布流数据
      params: {}, //请求携带的参数
    };
  },
  created() {
    //通过判断路由中是否携带isRoot来判断不同的分类商品页面，从而去请求不同的商品数据
    if (this.$route.query.isRoot) {
      this.params = { isRoot: true };
    }
    this.getCategory();
  },
  methods: {
    //请求分类数据
    getCategory() {
      this.$http
        .get(`spu/category/${this.$route.query.id}`, {
          params: this.params,
        })
        .then((res) => {
          this.flowGoodsList = res.data.list;
          this.flowGoodsList.forEach((item) => {
            if (item.tags) {
              item.tags = item.tags.split("$");
            }
          });
        });
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
  background: rbg(201, 174, 115);
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
</style>