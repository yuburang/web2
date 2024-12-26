<template>
  <div>
    <!-- 商品Spu轮播图 -->
    <van-swipe :autoplay="3000">
      <van-swipe-item
        v-for="(image, index) in goodsinfo.spuImageItems"
        :key="index"
      >
        <img :src="image.img" />
      </van-swipe-item>
    </van-swipe>
    <!-- 商品Spu信息 -->
    <div class="goodsInfo adapt">
      <div class="goodsInfo-left">
        <div class="title">
          {{ goodsinfo.title }}
        </div>
        <div class="tags-out">
          <van-tag
            v-for="(item2, index) in goodsinfo.tags"
            :key="index"
            type="primary"
            class="tags"
            >{{ item2 }}</van-tag
          >
        </div>
        <p class="subtitle">{{ goodsinfo.description }}</p>
        <p class="price">来源：{{ goodsinfo.price }}</p>
      </div>
    </div>
    <!-- 底部操作按钮 -->
    <!-- <van-goods-action>
      <van-goods-action-icon icon="chat-o" text="客服" color="#ee0a24" />
      <van-goods-action-icon
        :badge="getCount"
        icon="cart-o"
        text="购物车"
        to="/shopcart"
      />
      <van-goods-action-button
        class="cartbutton"
        type="warning"
        text="加入购物车"
        @click="
          ($refs.overlayRef.popupShow = true),
            ($refs.overlayRef.buyMode = false)
        "
      />
      <van-goods-action-button
        class="cartbutton"
        type="danger"
        text="立即购买"
        @click="
          ($refs.overlayRef.popupShow = true), ($refs.overlayRef.buyMode = true)
        "
      />
    </van-goods-action> -->
    <goods-popup :goodsinfo="goodsinfo" ref="overlayRef"></goods-popup>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import goodsPopup from "@/components/Popup.vue";
export default {
  name: "Goods",
  props: ["id"], //其他页面跳转传入的参数
  components: {
    goodsPopup,
  },
  data() {
    return {
      goodsinfo: {}, //商品Spu信息
    };
  },
  created() {
    this.getSpuDetail();
  },
  methods: {
    getSpuDetail() {
      //   console.log(this.$route.param.id);
      this.$http.get(`spu/detail/${this.id}`).then((res) => {
        this.goodsinfo = res.data;
        console.log(res.data);
        // 处理标签字段，按$分割
        if (this.goodsinfo.tags && this.goodsinfo.tags != "") {
          this.goodsinfo.tags = this.goodsinfo.tags.split("$");
        }
      });
    },
  },
  computed: {
    ...mapGetters("shopcart", ["getCount"]),
  },
};
</script>

<style scoped>
.goodsInfo {
  margin: 10px 0;
}
.goodsInfo p {
  padding-left: 20px;
  text-align: left;
}
.goodsInfo-left {
  float: left;
  width: 83%;
  padding-bottom: 50px;
}
.cartbutton {
  margin-top: 10px;
}
.subtitle {
  color: rgb(95, 126, 151);
  padding-top: 10px;
  font-size: 13px;
}
.tags-out {
  text-align: left;
  margin-left: 15px;
}
.tags {
  margin-left: 4px;
}
.title {
  text-align: left;
  margin-left: 19px;
}
.price {
  color: rbg(62, 62, 131);
  font-size: 16px;
  padding-top: 10px;
}
</style>