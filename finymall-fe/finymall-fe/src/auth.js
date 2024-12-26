// auth将token储存在localStorage,用户登录后携带token才能使用购物车等功能
var auth = {
    // 获取localStorage里的请求头信息
    getAuthorization() {
        return localStorage.getItem('Authorization')
    },
    // 往localStorage里存请求头信息
    setAuthorization(Authorization) {
        localStorage.setItem('Authorization', 'Bearer' + Authorization)
    }
}

export default {
    // 将auth挂载到原型，每个Vue实例中都可以使用
    install: function (vue) {
        vue.prototype.$auth = auth
    }
}