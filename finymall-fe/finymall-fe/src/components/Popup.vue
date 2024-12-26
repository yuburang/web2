<template>
  <!-- Sku详情弹出页面 -->
  <div>
    <van-popup v-model="popupShow" position="bottom" style="height: 60%">
      <div class="wrapper">
        <!-- 上方区域 -->
        <div class="adapt wrapper-content">
          <!-- 图片 -->
          <div class="warpper-img">
            <img :src="currItem.img" class="warpper-img-src" />
          </div>
          <!-- 标题 价格 组合 库存 -->
          <div class="detailInfo">
            <span>{{ currItem.title }}</span>
            <span>{{ currItem.price }}</span>
            <div class="detail_inv">
              <span v-if="currItem.stock">库存:{{ currItem.stock }}</span>
            </div>
          </div>
        </div>
        <!-- 分割线 -->
        <div class="divider"></div>

        <!-- 商品组合 -->
        <div class="choice">
          <div>组合详情:</div>
          <!-- 循环父组件传递过来的商品信息里的商品 -->
          <div
            v-for="(item, index) in currItem.skus"
            :key="index"
            @click="selectSepcs(item)"
          >
            <!-- 通过id来判断是否选中 -->
            <div
              :class="['specsItem', item.id == currSelectId ? 'selected' : '']"
            >
              <span>{{ arr2SepcsList(item) }}</span>
              <span>￥{{ item.price }}</span>
            </div>
          </div>
        </div>
        <!-- 分割线 -->
        <div class="divider"></div>
        <!-- 购买数量 -->
        <div class="adapt">
          <div class="buycount-left">购买数量</div>
          <van-stepper v-model="buyAmount" class="buycount-right" />
        </div>
      </div>
      <!-- 底部按钮 有可能是加入购物车 也有可能是确定 -->
      <div class="buybottom">
        <van-button class="buybottom-button" @click="addShopping">
          {{ buyMode ? "确认" : "加入购物车" }}
        </van-button>
      </div>
    </van-popup>
  </div>
</template>

<script>
export default {
  props: ["goodsinfo"],
  data() {
    return {
      popupShow: false, //是否显示Sku信息遮罩层
      buyMode: false, //控制popup层底部文字是加入购物车/确认
      buyAmount: 1, //购物数量
      currSelectId: 0, //当前选择的Sku,id
      currItem: {}, //另存父组件传递过来的值，因为会发生变化
      skuId: -1, //当前选择的id
    };
  },
  created() {
    // 保存父组件传递过来的值，(子组件中不要去操作父组件传过来的值)
    if (this.goodsinfo) {
      this.currItem = this.goodsinfo;
    }
  },
  methods: {
    // 将传进来的数组转化为中间带","号的商品规格数组，便于渲染
    arr2SepcsList(arr) {
      // this.skuId = arr.id;
      let list = arr.specs.map((item) => item.value);
      return list.join();
    },
    // 选择一条商品规格
    selectSepcs(item) {
      this.skuId = item.id;
      this.currSelectId = item.id;
      this.currItem.title = item.title;
      this.currItem.price = item.price;
      this.currItem.stock = item.stock;
    },
    // 加入购物车or确认
    addShopping() {
      // 先判断是否选中
      if (this.skuId === -1) {
        this.$toast.fail("请选择组合");
      } else {
        // 通过buyMode来判断他是否加入购物车还是直接购买
        if (!this.buyMode) {
          // 加入购物车
          this.$store.commit("shopcart/addCar", {
            id: this.goodsinfo.id,
            selected: true,
            buyAmount: this.buyAmount,
            skuId: this.skuId,
          });
        }
        this.buyAmount = 1;
        this.popupShow = false;
        this.skuId = -1;
        this.currItem = this.deepCopy(this.goodsinfo);
      }
      // this.popupShow = false;
      // this.currSelectId = 0;
    },
    // 深拷贝
    deepCopy(data) {
      return JSON.parse(JSON.stringify(data));
    },
  },
  watch: {
    goodsinfo(newval) {
      this.currItem = this.deepCopy(this.goodsinfo);
    },
  },
};
</script>

<style scoped>
.wrapper {
  margin: 15px 15px;
}
.detailInfo {
  text-align: left;
  display: flex;
  flex-direction: column;
  padding-left: 20px;
}
.detailInfo span:first-child {
  padding-top: 0;
}
.detailInfo span {
  padding: 3px 0;
}
.detailInfo .detail_inv {
  text-align: right;
}
.detailInfo .detail_inv span:last-child {
  color: #b2b2b2;
  font-size: 14px;
}
.adapt {
  zoom: 1;
  overflow: hidden;
}
.choice {
  text-align: left;
  margin: 10px 0px;
}
.divider {
  margin-top: 10px;
  height: 1px;
  width: 100%;
  background-color: #e9e9e9;
}
.warpper-img {
  float: left;
  width: 80px;
  height: 80px;
  background: black;
}
.warpper-img-src {
  max-height: 100%;
  max-width: 100%;
}
.buycount-left {
  float: left;
  margin: 10px 10px 10px 0px;
}
.buycount-right {
  float: right;
  margin: 10px 0px 10px 10px;
}
.specsItem {
  padding: 6px 10px;
  margin: 10px 0px;
  font-size: 15px;
  background: #e9e9e9;
  border-radius: 6px;
  display: flex;
  border: 1px solid #e9e9e9;
  justify-content: space-between;
}
.buybottom {
  position: fixed;
  width: 100%;
  bottom: 0;
}
.buybottom-button {
  background-color: rgb(63, 41, 48);
  color: #fff;
  width: 100%;
  font-size: 18px;
}
.selected {
  border: 1.5px solid red;
  background: #d8d8d8;
}
</style>