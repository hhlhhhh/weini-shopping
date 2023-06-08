<template>
  <div class="register">
    <div class="content">
      <div class="title">
        <h1>注册</h1>
      </div>
      <div class="form">
        <ul>
          <li>
            <a-input placeholder="昵称" allow-clear v-model="form.nickname">
              <template #prefix>
                <icon-user />
              </template>
            </a-input>
          </li>
          <li>
            <a-input-number placeholder="手机号码" hide-button allow-clear v-model="form.phone">
              <template #prefix>
                <icon-phone />
              </template>
            </a-input-number>
          </li>
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
          <li>
            <a class="to-login"  @click="router.push({name:'Login'})">立即登录</a>
          </li>
          <li>
            <a-button class="register-btn" :disabled="EmailLoginWait" @click="registerEvent">注册</a-button>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup>

import {useRouter} from "vue-router";
import {getEmailCodeApi, registerApi} from "@/http/user";
import {reactive, ref} from "vue";
import {useToast} from "primevue/usetoast";

const router = useRouter()

const toast = useToast()

const form = reactive({
  phone:null,
  nickname:"",
  email:"",
  authCode:null
})

let getEmailCodeWaitTime=ref(0);  //获取验证码等待时间
let EmailLoginWait = ref(false)  //邮箱登录等待

let emailVerify = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/

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

  getEmailCodeApi({email:form.email,type:"register"}).then(res=>{
    if(res.data.code===200) {
      toast.add({severity: 'success', summary: '发送给' + form.email + "!", detail: '发送成功！', life: 3000})
    }else{
      toast.add({ severity: 'error', summary: '发送给'+form.email+"!", detail: '发送失败！', life: 3000 })
    }
  })
  toast.add({severity: 'success', summary: '正在发送，请稍等！', detail: '发送中！', life: 2000})
}

const registerEvent = ()=>{
  if(!form.nickname||form.nickname.length<5||form.nickname.length>20){
    toast.add({ severity: 'error', summary: "昵称应为5到20位的字符", life: 1500 })
    return
  }
  if(!emailVerify.test(form.email)){  //邮箱格式验证
    toast.add({severity: 'warn', summary: '邮箱格式错误！', life: 1500})
    return
  }
  let reg = /^(13\d|14[579]|15[012356789]|166|17[0135678]|18\d|19[89])\d{8}$/
  if(!reg.test(form.phone)){  //手机号格式验证
    toast.add({severity:"error",summary:"手机号格式错误!",life:1500})
    return;
  }
  if(!form.authCode||(form.authCode+"").length!==4){
    toast.add({severity:"error",summary:"请正确输入验证码!",life:1500})
    return;
  }

  registerApi({...form,code:form.authCode}).then(({data})=>{
    if(data.code===200){
      toast.add({severity:"success",summary:"注册成功!",life:1500})
    }
  })
}

</script>

<style scoped lang="less">
.register{
  display: flex;
  height: 100vh;
  justify-content: center;
  align-items: center;
  .content{
    display: inline-block;
    width: 500px;
    height: 560px;
    box-shadow: 5px 5px 5px #f7f9fa;
    background-color: #fff;
    .title{
      margin: 30px 0 35px;
      >h1{
        text-align: center;
        color: #333333;
        letter-spacing: 15px;
        font-size: 26px;
      }
    }
    .form{
      width: 320px;
      margin: 0 auto;
      >ul{
        text-align: center;
        >li{
          width: 320px;
          min-height: 20px;
          margin-top: 24px;
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
          .to-login{
            color: rgba(0,118,254,1);
          }
          .register-btn{
            width: 100%;
            height: 50px;
            line-height: 50px;
            color: #FFFFFF;
            font-size: 20px;
            letter-spacing: 15px;
            border: none;
            border-radius: 25px;
            background-color: rgb(0, 118, 254);

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
    }
  }
}
</style>