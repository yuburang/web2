function setItem(name, item) {
    localStorage.setItem(name, JSON.stringify(item))
}
function getItem(name) {
    return JSON.parse(localStorage.getItem(name) || '[]')
}
function removeItem(name) {
    return localStorage.removeItem(name)
}
const state = {
    // 请求所有保存在本地的商品信息
    car: getItem('car')
}
const getters = {
    // 获取商品数量
    getGoodsCount(state) {
        var goods = {}
        state.car.forEach((item) => {
            goods[item.skuId] = item.buyAmount
        })
        return goods
    },
    // 获取商品有没有选中的状态值
    getGoodsSelected(state) {
        var goods = {}
        state.car.forEach((item) => {
            goods[item.skuId] = item.selected
        })
        return goods
    },
    // 获取商品个数 在商品详情页面的购物车上表明购物车内的商品个数
    getCount(state) {
        // 为0的时候就不显示上面的红点
        return state.car.length || ''
    },
    // 获取选中的商品的简述
    getSelectedCount(state) {
        var count = 0
        state.car.forEach((item) => {
            if (item.selected) {
                count += 1
            }
        })
        return count
    },
    //获取选中的商品
    getSelectedGoods(state) {
        var goods = {}
        state.car.forEach((item) => {
            if (item.selected) {
                goods[item.skuId] = item
            }
        })
        return goods
    }
}
function sortByKey(array, key) {
    return array.sort(function (a, b) {
        var x = a[key]
        var y = b[key]
        return x < y ? -1 : x > y ? 1 : 0
    })
}
const actions = {}
const mutations = {
    // 添加商品到购物车
    addCar(state, goodsInfo) {
        var flag = false
        state.car.some((item) => {
            // 判断商品的skuId是否已存在与购物车中，是添加购物车还是增加数量
            if (item.skuId === goodsInfo.skuId) {
                item.buyAmount += parseInt(goodsInfo.buyAmount)
                flag = true
                return true
            }
        })
        if (!flag) {
            state.car.push(goodsInfo)
            sortByKey(state.car, 'id')
        }
        setItem('car', state.car)
    },
    updateGoodsInfo(state, goodsInfo) {
        state.car.some((item) => {
            if (item.skuId === goodsInfo.id) {
                item.buyAmount = parseInt(goodsInfo.buyAmount)
                return true
            }
        })
        setItem('car', state.car)
    },
    // 改变商品的选择状态
    updateGoodsSelected(state, info) {
        state.car.some((item) => {
            if (item.skuId === info.id) {
                item.selected = info.selected
            }
        })
        setItem('car', state.car)
    },
    // 删除产品
    removeCar(state, id) {
        state.car.some((item, i) => {
            if (item.skuId === id) {
                state.car.splice(i, 1)
                return true
            }
        })
        setItem('car', state.car)
    }
}

export default {
    // namespaced:true 的方式使其成为带命名空间的模块，保证在变量名一样的时候，添加一个父类名拼接
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}