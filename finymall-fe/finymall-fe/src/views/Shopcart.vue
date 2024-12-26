<template>
  <div style="height: 100%">
    <div class="adapt">
      <form action="/">
        <van-search
          v-model="value"
          show-action
          placeholder="请输入搜索关键词"
          
        />
      </form>
      <van-tabs>
            <van-tab title="第一频道"></van-tab>
            <van-tab title="联播频道"><list-2 ref="list2"/></van-tab>
            <van-tab title="看电视"><list-1 ref="list1"/></van-tab>
            <van-tab title="看理论"><list-2 ref="list2"/></van-tab>
            <van-tab title="看党史"><list-1 ref="list1"/></van-tab>
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
      <!-- <div class="wapper">
        <div v-for="(item, index) in skuList" :key="index" class="cart adapt">
          
          <van-swipe-cell class="swipeCell">
            <div class="leftCont">
              <input
                type="checkbox"
                v-model="getGoodsSelected[item.id]"
                class="checkbox"
                @change="selectedChange(item.id, getGoodsSelected[item.id])"
              />
              <van-card
                class="cartinfo"
                :desc="item.description"
                :title="item.title"
                :thumb="item.img"
              >
                <template #tags>
                  <van-tag
                    class="cartinfo-tag"
                    plain
                    v-for="(item2, index2) in item.specs"
                    :key="index2"
                    >{{ item2.value }}</van-tag
                  >
                </template>
                <template #bottom>
                  <div class="cart-price">￥{{ item.price }}</div>
                  <van-stepper
                    v-model="getGoodsCount[item.id]"
                    @change="countChanged(item.id, getGoodsCount[item.id])"
                    class="cart-stepper"
                  />
                </template>
              </van-card>
            </div>
           
            <template #right>
              <van-button
                @click="removeCarItem(item.id)"
                square
                text="删除"
                type="danger"
                class="delete-button"
              />
            </template>
          </van-swipe-cell>
        </div>
      </div> -->
      <!-- 购物车底部操作栏 -->
      <!-- <div class="account">
        <van-button class="pay">结算</van-button>
        <div>
          <div class="cart-choice">
            已选:<span class="highlight">{{ getSelectedCount }}</span
            >件
          </div>
          <div class="cart-sum">
            合计:<span class="highlight">￥{{ getSelectedAmount }}</span>
          </div>
        </div>
      </div> -->
    </div>
    <nav-bar></nav-bar>
  </div>
</template>

<script>
import List1 from "./List1.vue";
import List2 from "./List2.vue";
import NavBar from "@/components/NavBar.vue";
import { mapGetters, mapState } from "vuex";
export default {
  name: "Shopcart",
  components: {
    NavBar,
    List1,
    List2
  },
  data() {
    return {
      // 购物车商品数据
      goodsList: [],
      // 购物车sku数据
      skuList: [],
      value: '',
      list: [],
      loading: false,
      finished: false,
      refreshing: false,
    };
  },
  created() {
    // 请求sku数据
    this.getSkuDetail();
  },
  computed: {
    // mapState是state的语法糖 扩展运算符将Vuex state里面的car放入到computed中
    // shopcart是命名空间 需要在vuex中
    ...mapState("shopcart", ["car"]),
    // mapGetters是getters的语法糖 扩展运算符将shopcart.js里getters的方法都放入computed中
    // 等于调用this.$store.getter来获取最终需要的getter
    ...mapGetters("shopcart", [
      "getGoodsCount",
      "getGoodsSelected",
      "getSelectedCount",
      "getSelectedGoods",
    ]),
    // 计算选中状态下合计的金额
    getSelectedAmount() {
      // 通过vuex中的getSelectedGoods获取当前选中的商品（对象)
      var goods = this.getSelectedGoods;
      console.log(goods);
      var amount = 0;
      this.skuList.forEach((item) => {
        if (goods[item.id]) {
          amount += item.price * goods[item.id].buyAmount;
        }
      });
      return amount.toFixed(2);
    },
  },
  methods: {
    // 商品数量的改变调用vuex中的updateGoodsInfo方法
    countChanged(id, count) {
      // 这里的id表示的是商品详情的id及skuId
      this.$store.commit("shopcart/updateGoodsInfo", {
        id: id,
        buyAmount: count,
      });
    },
    // 删除购物车里的某一项商品
    removeCarItem(id) {
      this.$store.commit("shopcart/removeCar", id);
      // 删除完重新请求数据
      this.getSkuDetail();
    },
    // 请求sku的详情信息
    getSkuDetail() {
      let list = this.car.map((item) => item.skuId);
      let params = {
        ids: list.join(),
      };
      this.$http
        .get(`/spu/sku`, {
          params: params,
        })
        .then((res) => {
          this.skuList = res.data;
        });
    },
    // 改变商品选择状态调用vuex中的updateGoodsSelected方法
    selectedChange(id, val) {
      this.$store.commit("shopcart/updateGoodsSelected", {
        id: id,
        selected: val,
      });
    },
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
        //---------------------------
        

        //------------
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
  },
};
</script>

<style scoped>
.adapt {
  overflow: hidden;
  zoom: 1;
}
.wapper {
  overflow: auto;
  height: calc(100vh - 50px - 50px - 46px);
}
.checkbox {
  width: 10px;
  float: left;
  height: 120px;
  margin: 0 10px;
}
.account {
  position: fixed;
  bottom: 50px;
  height: 50px;
  width: 100%;
  background: rgb(240, 237, 237);
}
.cart-choice {
  float: left;
  line-height: 50px;
  padding-left: 20px;
}
.cart-sum {
  float: right;
  line-height: 50px;
  padding-right: 10px;
}
.pay {
  float: right;
  height: 40px;
  width: 100px;
  color: white;
  background: rgb(105, 134, 140);
  margin-top: 5px;
  margin-right: 5px;
  border-radius: 8px;
  border: none;
}
.delete-button {
  height: 100%;
}
.leftCont {
  border-bottom: 1px solid #eee;
  display: flex;
  height: 100%;
}
.highlight {
  color: rgb(207, 175, 116);
  padding-right: 5px;
}
.cart {
  text-align: left;
  width: 100%;
}
.cartinfo {
  flex: 1;
  float: left;
  font-size: 13px;
  margin: 0;
  background-color: #fff;
}
.cart-price {
  float: left;
  font-size: 18px;
  line-height: 30px;
}
.cart-stepper {
  float: right;
}
.cartinfo-tag {
  margin: 10px 0;
  margin-right: 5px;
}
</style>