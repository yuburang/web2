<template>
  <div>
    <div class="theme">
      <!-- 图片 -->
      <img :src="img" class="themeimg" />
    </div>
    <div class="activity">
      <p class="distance">
        <!-- 描述 -->
        {{ describe }}
      </p>
    </div>
    <div>
      <!-- 瀑布流，展示商品列表 -->
      <waterfall
        v-if="flowGoodsList"
        :col="2"
        :data="flowGoodsList"
        :width="itemWidth"
        :gutterWidth="gutterWidth"
        :loadDistance="10"
      >
        <template>
          <!-- 循环出来每个项 -->
          <div
            class="goodslist"
            v-for="(item, index) in flowGoodsList"
            :key="index"
          >
            <!-- 点击可以跳转到对应的商品详情页面 -->
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
    </div>
  </div>
</template>

<script>
export default {
  name: "Themes",
  data() {
    return {
      flowGoodsList: [], //当前专题对应的商品列表
      describe: "", //专题描述
      img: "", //专题图片
    };
  },
  created() {
    // this.$route.query来接收路由里的query
    if (this.$route.query.name) {
      this.getThemeDetail();
    }
  },
  methods: {
    getThemeDetail() {
      this.$http.get(`/theme/detail/${this.$route.query.name}`).then((res) => {
        this.img = res.data.entranceImg;
        this.describe = res.data.description;
        this.flowGoodsList = res.data.spuList;
        // 循环数组以$进行分割
        this.flowGoodsList.forEach((item) => {
          if (item.tags) {
            item.tags = item.tags.split("$");
          }
        });
      });
    },
  },
  computed: {
    itemWidth() {
      return 340 * 0.5 * (document.documentElement.clientWidth / 375);
    },
    gutterWidth() {
      return 36 * 0.5 * (document.documentElement.clientWidth / 375);
    },
  },
};
</script>

<style scoped>
.theme {
  height: 180px;
}
.themeimg {
  max-height: 100%;
  width: 100%;
}
.activity {
  text-align: left;
  padding: 10px 0 20px 10px;
  font-size: 17px;
}
.distance {
  padding: 10px 0 0 0;
}
.goodslist {
  background: white;
  text-align: left;
  float: left;
  width: 100%;
  margin: 8px 8px;
  padding-bottom: 10px;
  box-shadow: 0 0 6px rgb(173, 185, 196);
  border-radius: 8px 8px 8px 8px;
}
.goodslist img {
  border-radius: 8px 8px 0 0;
  width: 100%;
}
.goodslist-subtitle {
  word-wrap: break-word;
  padding: 5px 10px 0 11px;
  color: rgb(95, 126, 151);
  font-size: 12px;
}
.goodslist-title {
  font-size: 15px;
  color: black;
  padding: 5px 10px 0 11px;
}
.goodslist-tags {
  background: rgb(111, 212, 187);
  font-size: 5px;
  margin-left: 4px;
  color: rgb(219, 255, 230);
}
.goodslist-tags-out {
  margin-left: 6px;
}
.goodslist-price {
  padding-top: 5px;
  color: rgb(62, 62, 131);
  padding-left: 10px;
}
</style>