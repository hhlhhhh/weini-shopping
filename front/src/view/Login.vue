<template>
 <div class="login">
   <div class="content">
     <div class="box1">
       <img src="@/assets/images/login/login-image-01.png">
     </div>
     <div class="box2">
       <a-tabs default-active-key="2">
         <a-tab-pane key="1" title="密码登录">
           <div class="login-by-password">
             <ul>
               <li>
                 <a-input-number placeholder="手机号码" hide-button allow-clear>
                   <template #prefix>
                     <icon-phone />
                   </template>
                 </a-input-number>
               </li>
               <li>
                 <a-input-password placeholder="密码">
                   <template #prefix>
                     <icon-lock />
                   </template>
                 </a-input-password>
               </li>
               <li>
                 <a>忘记密码?</a>
               </li>
               <li>
                 <button class="login-btn">登录</button>
               </li>
               <li>
                 <a class="to-register">立即注册</a>
               </li>
             </ul>
           </div>
         </a-tab-pane>
         <a-tab-pane key="2" title="邮箱登录">
           <div class="login-by-email">
             <ul>
               <li>
                 <a-input placeholder="邮箱" allow-clear>
                   <template #prefix>
                     <icon-email />
                   </template>
                 </a-input>
               </li>
               <li class="code-row">
                 <a-input-number placeholder="验证码" hide-button>
                   <template #prefix>
                     <icon-code-square />
                   </template>
                 </a-input-number>
                 <a-button class="get-email-code-btn">获取验证码</a-button>
               </li>
               <li style="margin-top: 36px;">
                 <button class="login-btn">登录</button>
               </li>
               <li>
                 <a class="to-register">立即注册</a>
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
import {useRoute} from "vue-router";
import {getEmailCodeApi} from "@/http/user";
import {useToast} from "primevue/usetoast";
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