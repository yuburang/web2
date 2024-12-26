import axios from 'axios'
import { Toast } from 'vant'
// import { config } from 'vue/types/umd'
export default {
  install: function (vue) {
    let service = axios.create({
      baseURL: 'http://localhost:8080/'
    })
    var auth = vue.prototype.$auth
    // 请求拦截器，为所有请求添加'Authorization'头
    service.interceptors.request.use(config => {
      config.headers.Authorization = auth.getAuthorization()
      return config
    })
    // 后端返回数据，全局拦截处理，针对错误状态、错误码进行统一提示
    service.interceptors.response.use(response => {
      // 首先检查HTTP响应状态，如果非200，说明请求异常
      if (response.status !== 200) {
        Toast.fail({
          duration: 0,
          overlay: true,
          closeOnClickOverlay: true,
          closeOnClick: true,
          message: "请求异常",
        })
        return Promise.reject(res)
      }
      // 从后端响应数据中取出真正的数据部分
      const res = response.data
      // 如果后端返回的对象中，code非0，说明有异常
      if (res.code !== 0) {
        console.log(res)
        Toast.fail({
          duration: 0,
          overlay: true,
          closeOnClickOverlay: true,
          closeOnClick: true,
          message: res.msg,
        })
        return Promise.reject(res)
      }
      // 如果code正常，则放行
      return res
    })
    vue.prototype.$http = service
  }
}
