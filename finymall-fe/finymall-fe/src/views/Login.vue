<template>
  <div class="bg">
    <div class="wholebox">
      <div class="sign"><h1>Login</h1></div>
      <form action="" class="inputbox">
        <input v-model="loginForm.username" placeholder="用户名" type="text" />
        <br />
        <input
          v-model="loginForm.password"
          placeholder="密码"
          type="password"
        />
        <br />
        <button class="btn" @click="loginClick" type="button">登录</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    loginClick() {
      // 简单判断loginForm里的用户名和密码是否为空
      if (this.loginForm.username === "" || this.loginForm.password === "") {
        this.$toast("请输入用户名和密码");
      } else {
        this.$http.post("/login", this.loginForm).then((res) => {
          // 存token:调用auth.js中的setAuthorization方法
          this.$auth.setAuthorization(res.data.token);
          this.$router.push("/home");
        });
      }
    },
  },
};
</script>

<style scoped>
.bg {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  font-family: "Open Sans", sans-serif;
  background: url("../assets/bg4.jpeg");
  background-size: cover;
}
h1 {
  color: white;
  letter-spacing: 3px;
  text-align: center;
  font-size: 2em;
}
input {
  width: 220px;
  height: 20px;
  margin-bottom: 20px;
  padding: 10px 10px 10px 18px;
  font-size: 13px;
  color: white;
  border: none;
  border-radius: 8px;
  background: linear-gradient(
    to right,
    rgb(85, 110, 66, 0.9),
    rgb(216, 207, 198, 0.9)
  );
}
.btn {
  height: 40px;
  width: 250px;
  margin-bottom: 250px;
  background-color: rgb(187, 179, 188, 0.9);
  border: none;
  color: white;
  font-size: 15px;
  border-radius: 8px;
}
.sign {
  font-size: 20px;
  margin-bottom: 20px;
}
.wholebox {
  margin-top: 200px;
}
::-webkit-input-placeholder {
  /* WebKit browsers */
  color: rgb(9, 17, 55);
}
::-moz-placeholder {
  /* Mozilla Firefox 19++ */
  color: rgb(9, 17, 55);
}
:-ms-input-placeholder {
  /* Internet Explorer */
  color: rgb(9, 17, 55);
}
</style>