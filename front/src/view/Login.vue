<template>
 <div class="content">
  <div class="content-box">
    <div :class="box1Class">
      <ul class="form">
        <li>
          <h1 class="title">登录</h1>
        </li>
        <li v-if="loginType">
          <div>
            <span class="label">手机号</span>
            <input v-model="form.phone" type="text" placeholder="请输入手机号">
          </div>
        </li>
        <li v-if="loginType">
          <span class="label">密码</span>
          <input v-model="form.password" type="password" placeholder="请输入密码">
        </li>
        <li v-if="!loginType">
          <div>
            <span class="label">邮箱</span>
            <input v-model="form.email" type="text" placeholder="请输入邮箱">
          </div>
        </li>
        <li v-if="!loginType">
          <span class="label">验证码</span>
          <input type="text" v-model="form.authCode" style="width: 150px" placeholder="请输入验证码">
          <a-button style="width: 50px;height: 34px;background-color: rgb(160, 170, 197 )" @click="getEmailCode">{{getEmailCodeWaitTime===0?"获取":getEmailCodeWaitTime}}</a-button>
        </li>
        <li>
          <Button label="登录" size="small" style="height: 40px"/>
        </li>
      </ul>
    </div>
    <div :class="box2Class">
      <ul class="form">
        <li>
          <h1 class="title">注册</h1>
        </li>
        <li>
          <div>
            <span class="label">手机号</span>
            <input type="text" placeholder="请输入手机号">
          </div>
        </li>
        <li>
          <div>
            <span class="label">邮箱</span>
            <input type="text" placeholder="邮箱">
          </div>
        </li>
        <li>
          <div>
            <span class="label">验证码</span>
            <input type="text" placeholder="验证码">
          </div>
        </li>
        <li>
          <span class="label">密码</span>
          <input type="password" placeholder="请输入密码">
        </li>
        <li>
          <Button  label="登录" size="small" style="height: 40px"/>
        </li>
      </ul>
    </div>
    <div :class="card1Class" ref="card1">
      <div >
        <ul>
          <li>
            <h2>欢迎来到<span style="color: rgb(70, 104, 158)">唯你购物</span></h2>
          </li>
          <li>
            <a-avatar :size="120" shape="square" image-url="http://localhost:8090/image/logo.png">Arco</a-avatar>
          </li>
          <li>
            <div style="color:rgb(74,76,79);margin-bottom: 5px">已有账号？</div>
            <div>
              <a-button type="outline" shape="round" size="small" style="color: rgb(74, 108, 160)" @click="gotoRegister">去登录</a-button>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <div :class="card2Class" ref="card2">
      <ul>
        <li>
          <h2>欢迎来到<span style="color: rgb(70, 104, 158)">唯你购物</span></h2>
        </li>
        <li>
          <a-avatar :size="90" shape="square" image-url="http://localhost:8090/image/logo.png"></a-avatar>
        </li>
        <li>
          <div style="color:rgb(74,76,79);margin-bottom: 5px">没有账号？</div>
          <div>
            <a-button type="outline" shape="round" size="small" style="color: rgb(74, 108, 160)" @click="gotoRegister">去注册</a-button>
          </div>
        </li>
        <li>
          <div style="color:rgb(74,76,79);margin-bottom: 5px">忘记密码？使用邮箱登录</div>
          <div>
            <a-button type="outline" shape="round" size="small" style="color: rgb(74, 108, 160)" @click="()=>{loginType=!loginType}">
              {{loginType? "邮箱登录":"密码登录" }}</a-button>
          </div>
        </li>
      </ul>
    </div>
  </div>
 </div>
</template>

<script setup>
import {ref,reactive,getCurrentInstance} from "vue";
import {useRoute} from "vue-router";
import {getEmailCodeApi} from "../http/user";
import {useToast} from "primevue/usetoast";
import Toast from "primevue/toast";
const toast = useToast()

const {proxy} = getCurrentInstance()

//页面路由
const route = useRoute()

//true为手机密码登录
//false为邮箱验证码登录
let loginType = ref(true)
let getEmailCodeWaitTime=ref(0);

let form  = reactive({
  phone:"",
  password:"",
  email:"",
  authCode:""
})

let emailVerify = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/

let box1Class = reactive(["box-1"])
let box2Class = reactive(["box-2"])
let card1Class = reactive(["card-1"])
let card2Class = reactive(["card-2"])

//init
const init = ()=>{
  const query = route.query
  if(query['login-flag']==="register"){
    card2Class.push("card-init-hidden","card-hidden")
    box1Class.push("card-init-hidden","box-hidden")
  }else{
    card1Class.push("card-init-hidden","card-hidden")
    box2Class.push("card-init-hidden","box-hidden")
  }
}

//切换到注册页面
const gotoRegister = ()=>{
  if(card1Class.length>=2&&card1Class.indexOf("card-hidden")!==-1){
    card2Class.length=0
    card1Class.length=0
    card2Class.push("card-2","card-hidden")
    card1Class.push("card-1","card-display")
    box1Class.length=0
    box2Class.length=0
    box1Class.push("box-1","card-hidden")
    box2Class.push("box-2","box-right-display")
  }else{
    card2Class.length=0
    card1Class.length=0
    card2Class.push("card-2","card-display")
    card1Class.push("card-1","card-hidden")
    box1Class.length=0
    box2Class.length=0
    box1Class.push("box-1","box-left-display")
    box2Class.push("box-2","card-hidden")
  }
}

const getEmailCode = ()=>{
  if(!emailVerify.test(form.email)){
    return
  }
  if(getEmailCodeWaitTime.value>0){
    toast.add({severity: 'warn', summary: '发送给' + form.email + "!", detail: '请勿重复发送！', life: 3000})
    return;
  }
  getEmailCodeWaitTime.value=60
  const timeLoop =setInterval(function (){
    if(getEmailCodeWaitTime.value<=0){
      clearInterval(timeLoop)
      return
    }
    getEmailCodeWaitTime.value-=1;
  },1000)

  getEmailCodeApi({email:form.email}).then(res=>{
    if(res.data.code===200) {
      toast.add({severity: 'success', summary: '发送给' + form.email + "!", detail: '发送成功！', life: 3000})
    }else{
      toast.add({ severity: 'error', summary: '发送给'+form.email+"!", detail: '发送失败！', life: 3000 })
    }
  })
}

init()
</script>

<style scoped lang="less">

input{
  width: 200px;
  height: 35px;
  outline: none;
  padding-left: 8px;
  border: 2px solid rgb(65, 101, 156);
  border-radius: 5px;
}

.content {
  width: 100vw;
  height: 100vh;
  background: linear-gradient(200deg, #f3e7e9, #325a96);

  .content-box {
    display: inline-block;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translateX(-50%) translateY(-50%);
    width: 720px;
    height: 280px;
    border-radius: 12px;
    background-color: #fff;

    .box-1, .box-2 {
      display: inline-block;
      overflow: hidden;
      width: 300px;
      background-color: rgba(255, 255, 255, .35);
      backdrop-filter: blur(13px);
      border-radius: 20px;
      border: 3px solid rgba(255, 255, 255, .45);
      transition: all .4s;

      .form {
        display: flex;
        flex-direction: column;
        text-align: center;

        .title {
          margin: 40px 0 10px 0;
        }

        li {
          margin: 15px 0;

          .label {
            display: inline-block;
            width: 60px;
            color: rgb(53, 92, 151);
          }
        }
      }
    }

    .box-1 {
      position: absolute;
      top: 50%;
      transform: translateY(-50%);
      left: 8%;
      height: 400px;
    }

    .box-2 {
      position: absolute;
      top: 50%;
      transform: translateY(-50%);
      right: 8%;
      height: 500px;
    }

    .card-1{
      display: block;
      position: absolute;
      left: 8%;
      width: 300px;
      height: 280px;
      text-align: center;
      color: rgba(0, 0, 0, .65);
      background-color: rgb(175, 176, 204);
      z-index: 99;
      ul {
        display: flex;
        flex-direction: column;

        li {
          margin: 12px 0;
        }
      }
    }

    .card-2 {
      display: block;
      position: absolute;
      right: 8%;
      width: 300px;
      height: 280px;
      text-align: center;
      color: rgba(0, 0, 0, .65);
      background-color: rgb(175, 176, 204);
      z-index: 99;
      ul {
        display: flex;
        flex-direction: column;

        li {
          margin: 8px 0;
        }
      }
    }

    .card-init-hidden{
      display: none;
    }
  }
}

  .box-left-display{
    animation-name: box-slider-left;
    animation-duration: .3s;
    animation-timing-function: linear;
    animation-fill-mode:forwards
  }

.box-right-display{
  animation-name: box-slider-right;
  animation-duration: .3s;
  animation-timing-function: linear;
  animation-fill-mode:forwards
}

  @keyframes box-slider-right {
    0% {
      right: 40%;
      opacity: 0;
    }
    75% {
      right: 15%;
      opacity: 0.5
    }
    100% {
      right: 8%;
      opacity: 1;
      z-index: 99;
    }
  }


  @keyframes box-slider-left {
    0% {
      left: 40%;
      opacity: 0;
    }
    75% {
      left: 15%;
      opacity: 0.5
    }
    100% {
      left: 8%;
      opacity: 1;
      z-index: 99;
    }
  }

  .card-hidden{
    animation-name: card-hidden;
    animation-duration: .3s;
    animation-timing-function: linear;
    animation-fill-mode:forwards
  }

  .card-display{
    display: block;
    animation-name: card-display;
    animation-duration: .3s;
    animation-timing-function: linear;
    animation-fill-mode:forwards
  }
@keyframes card-hidden {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
    z-index: 0;
  }
}
@keyframes card-display {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
</style>