<template>
 <div class="login">
   <div class="content">
     <div class="box1">
       <img src="@/assets/images/login/login-image-01.png">
     </div>
     <div class="box2">
       <a-tabs default-active-key="1" @change="tabChange">
         <a-tab-pane key="1" title="密码登录">
           <div class="login-by-password">
             <ul>
               <li>
                 <a-input-number placeholder="手机号码" hide-button allow-clear v-model="form.phone">
                   <template #prefix>
                     <icon-phone />
                   </template>
                 </a-input-number>
               </li>
               <li>
                 <a-input-password placeholder="密码" v-model="form.password">
                   <template #prefix>
                     <icon-lock />
                   </template>
                 </a-input-password>
               </li>
               <li>
                 <a>忘记密码?</a>
               </li>
               <li>
                 <button class="login-btn" @click="loginEvent">登录</button>
               </li>
               <li>
                 <a class="to-register" @click="router.push({name:'Register'})" >立即注册</a>
               </li>
             </ul>
           </div>
         </a-tab-pane>
         <a-tab-pane key="2" title="邮箱登录">
           <div class="login-by-email">
             <ul>
               <li>
                 <a-input placeholder="邮箱" allow-clear v-model="form.email">
                   <template #prefix>
                     <icon-email />
                   </template>
                 </a-input>
               </li>
               <li class="code-row">
                 <a-input-number placeholder="验证码" hide-button v-model="form.authCode">
                   <template #prefix>
                     <icon-code-square />
                   </template>
                 </a-input-number>
                 <a-button class="get-email-code-btn" @click="getEmailCode">{{getEmailCodeWaitTime===0?"获取验证码":getEmailCodeWaitTime}}</a-button>
               </li>
               <li style="margin-top: 36px;">
                 <button class="login-btn" :disabled="EmailLoginWait" @click="loginEvent">登录</button>
               </li>
               <li>
                 <a class="to-register" @click="router.push({name:'Register'})">立即注册</a>
               </li>
             </ul>
           </div>
         </a-tab-pane>
       </a-tabs>
     </div>
   </div>
 </div>
</template>

<script setup>
import {ref,reactive,getCurrentInstance} from "vue";
import {useRoute, useRouter} from "vue-router";
import {getEmailCodeApi, loginByEmailApi, loginByPasswordApi} from "@/http/user";
import http from "@/http/request/request";
import {useToast} from "primevue/usetoast";
const toast = useToast()

const {proxy} = getCurrentInstance()

//页面路由
const route = useRoute()
const router = useRouter()

//true为手机密码登录
//false为邮箱验证码登录
let loginType = ref(true)

let getEmailCodeWaitTime=ref(0);  //获取验证码等待时间
let EmailLoginWait = ref(false)  //邮箱登录等待

let form  = reactive({
  phone:null,
  password:"",
  email:"",
  authCode:null
})

const tabChange = ()=>{
  loginType.value=!loginType.value
}

//邮箱验证格式
let emailVerify = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/

//获取邮箱验证码
const getEmailCode = ()=>{
  if(!emailVerify.test(form.email)){  //邮箱格式验证
    toast.add({severity: 'warn', summary: '邮箱格式错误！', life: 1500})
    return
  }
  if(getEmailCodeWaitTime.value>0){   //提示勿重复发送
    toast.add({severity: 'warn', summary: '发送给' + form.email + "!", detail: '请勿重复发送！', life: 3000})
    return;
  }
  getEmailCodeWaitTime.value=60
  const timeLoop =setInterval(function (){      //倒计时
    if(getEmailCodeWaitTime.value<=0){
      clearInterval(timeLoop)
      return
    }
    getEmailCodeWaitTime.value-=1;
  },1000)

  getEmailCodeApi({email:form.email,type:"login"}).then(res=>{
    if(res.data.code===200) {
      toast.add({severity: 'success', summary: '发送给' + form.email + "!", detail: '发送成功！', life: 3000})
    }else{
      toast.add({ severity: 'error', summary: '发送给'+form.email+"!", detail: '发送失败！', life: 3000 })
    }
  })
  toast.add({severity: 'success', summary: '正在发送，请稍等！', detail: '发送中！', life: 2000})
}

//手机密码登录
function loginByPassword(){
  let reg = /^(13\d|14[579]|15[012356789]|166|17[0135678]|18\d|19[89])\d{8}$/
  if(!reg.test(form.phone)){  //手机号格式验证
    return new Promise((resolve, reject)=>{
      reject({
        "code": 105,
        "msg": "手机号格式错误！"
      })
    })
  }
  reg = /^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{6,16}$/
  if(!reg.test(form.password)){
    return new Promise((resolve, reject)=>{
      reject({
        "code": 106,
        "msg": "密码格式错误！"
      })
    })
  }
  EmailLoginWait.value = true
  try {
    return loginByPasswordApi({
      "arg1": form.phone,
      "arg2": form.password
    })
  }catch (e) {
    EmailLoginWait.value = false
    return new Promise((resolve, reject)=>{
      reject({
        code: "460",
        msg: e.msg
      })
    })
  }
}

//邮箱登录
function loginByEmail(){
  if(!emailVerify.test(form.email)){  //邮箱格式验证
    return new Promise((resolve, reject)=>{
      reject({
        "code": 103,
        "msg": "邮箱格式错误！"
      })
    })
  }
  if(!/^\d{4}$/.test(form.authCode)){
    return new Promise((resolve, reject)=>{
      reject({
        "code": 104,
        "msg": "验证码格式错误！"
      })
    })
  }
  EmailLoginWait.value = true
  try {
    return loginByEmailApi({
      "arg1": form.email,
      "arg2": form.authCode
    })
  }catch (e) {
    EmailLoginWait.value = false
    return new Promise((resolve, reject)=>{
      reject({
        code: "460",
        msg: e.msg
      })
    })
  }
}

//登录
const loginEvent = ()=>{
  const req = loginType.value?loginByPassword:loginByEmail;
  req().then((res)=>{
    if(res.data.code===200){
      sessionStorage.setItem('Authorization',res.headers['authorization'])
      localStorage.removeItem("userMes")
      toast.add({severity: 'success', summary: res.data.data , life: 1500})
      router.push({name:"Home"})
    }else {
      toast.add({severity: 'error', summary: res.data.msg , life: 1500})
    }
  },(err)=>{
    toast.add({severity: 'error', summary: err.msg , life: 1500})
  })
}


</script>

<style scoped lang="less">

.login{
  display: flex;
  height: 100vh;
  justify-content: center;
  align-items: center;
  .content{
    display:  flex;
    width: 1000px;
    height: 500px;
    box-shadow: 5px 5px 5px #f7f9fa;
    background-color: #fff;
    .box1{
      >img{
        height: 500px;
      }
    }
    .box2{
      width: 320px;
      padding-top: 50px;
      margin: 0 auto;
      ul{
        text-align: center;
        >li{
          width: 320px;
          min-height: 20px;
          margin-top: 20px;
          >.arco-input-wrapper{   //修改arco的input样式
            width: 100%;
            height: 45px;
            //color: #FFF;
            border: 1px solid #e3e8f0;
            border-radius: 25px;
            background-color: rgba(0,0,0,0);
            padding: 0 20px;
            .arco-input-prefix{
              >svg{
                color: rgb(185, 190, 209);
                font-size: 20px;
              }
            }
          }
          a{
            float: right;
            color: rgba(158,161,177,1);
            margin-right: 20px;
            &:hover{
              color: rgba(0,118,254,1);
              text-decoration: underline rgba(0,118,254,1);
            }
          }
          .to-register{
            color: rgba(0,118,254,1);
          }
          .login-btn{
            width: 100%;
            height: 50px;
            cursor: pointer;
            line-height: 50px;
            color: #FFFFFF;
            font-size: 20px;
            letter-spacing: 15px;
            border: none;
            border-radius: 25px;
            background-color: rgb(0, 118, 254);
            &:disabled{
              background-color: #84add8;
            }
          }
        }
        .code-row{
          display: flex;
          .get-email-code-btn{
            height: 45px;
            border-radius: 25px;
            padding: 0 24px;
            margin-left: 15px;
          }
        }
      }
      :deep(.arco-tabs-nav-horizontal){
        .arco-tabs-nav-tab-list{
          width: 320px;
        }
        .arco-tabs-tab{   //使居中
          width: 160px;
          text-align: center;
          margin: 0;
          &:hover .arco-tabs-tab-title::before{   //消除hover黑影
            background-color: rgba(0,0,0,0);
          }
          .arco-tabs-tab-title{
            font-size: 20px;
            width: 160px;
          }
        }
        .arco-tabs-nav-ink{
          width: 160px!important;
        }
      }

    }
  }
}

</style>