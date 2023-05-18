<template>
  <div class="register">
    <div class="content">
      <div class="title">
        <h1>注册</h1>
      </div>
      <div class="form">
        <ul>
          <li>
            <a-input placeholder="昵称" allow-clear>
              <template #prefix>
                <icon-user />
              </template>
            </a-input>
          </li>
          <li>
            <a-input-number placeholder="手机号码" hide-button allow-clear>
              <template #prefix>
                <icon-phone />
              </template>
            </a-input-number>
          </li>
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
          <li>
            <a class="to-login">立即登录</a>
          </li>
          <li>
            <a-button class="register-btn">注册</a-button>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup>

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