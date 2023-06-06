<template>
  <div class="home-content">
    <a-layout>
      <a-layout-header style="padding-bottom: 70px">
        <Header/>
      </a-layout-header>
      <a-layout-content>
       <a-row>
         <a-col :span="3"></a-col>
         <a-col :span="5">
           <div class="classify">
             <h2>分类</h2>
            <ul>
              <li>
                <icon-desktop/>
                <a>女装</a>
                <span>/</span>
                <a>男鞋</a>
                <span>/</span>
                <a>奢品</a>
              </li>
              <li>
                <icon-desktop/>
                <a>女装</a>
                <span>/</span>
                <a>男鞋</a>
                <span>/</span>
                <a>奢品</a>
              </li>
              <li>
                <icon-desktop/>
                <a>女装</a>
                <span>/</span>
                <a>男鞋</a>
                <span>/</span>
                <a>奢品</a>
              </li>
              <li>
                <icon-desktop/>
                <a>女装</a>
                <span>/</span>
                <a>男鞋</a>
                <span>/</span>
                <a>奢品</a>
              </li>
              <li>
                <icon-desktop/>
                <a>女装</a>
                <span>/</span>
                <a>男鞋</a>
                <span>/</span>
                <a>奢品</a>
              </li>
              <li>
                <icon-desktop/>
                <a>女装</a>
                <span>/</span>
                <a>男鞋</a>
                <span>/</span>
                <a>奢品</a>
              </li>
              <li>
                <icon-desktop/>
                <a>女装</a>
                <span>/</span>
                <a>男鞋</a>
                <span>/</span>
                <a>奢品</a>
              </li>
              <li>
                <icon-desktop/>
                <a>女装</a>
                <span>/</span>
                <a>男鞋</a>
                <span>/</span>
                <a>奢品</a>
              </li>

            </ul>
           </div>
         </a-col>
         <a-col :span="8" style="display: flex;flex-direction: column">
           <div class="marquee-text">
              <marquee>今天是周天，本应该是放假的</marquee>
           </div>
           <div class="slideshow">
             <a-carousel
                 :style="{
                  height: '340px',
                  maxWidth: '500px',
                  borderRadius: '12px',
                  overflow: 'hidden'
                }"
                 :auto-play="true"
                 indicator-type="line"
                 show-arrow="hover">
               <a-carousel-item v-for="image in images">
                 <img
                     :src="image"
                     :style="{
                      width: '100%',
                      height: '100%',
                      borderRadius: '12px'
                }"/>
               </a-carousel-item>
             </a-carousel>
           </div>
         </a-col>
         <a-col :span="6">
           <div class="self-message">
              <div class="mes">
                <div>
                  <a-avatar :size="65">
                    <img
                        alt="avatar"
                        :src="userMes.avatarUrl"
                    />
                  </a-avatar>
                </div>
                <div class="self-nickname">
                  Hi! <span @click="router.push({name:'Profile'})">{{userMes.nickname}}</span>
                </div>
              </div>
             <div class="shopping">
               <ul>
                 <li class="shopping-item">
                   <span>1</span>
                   <span>购物车</span>
                 </li>
                 <li class="shopping-item">
                   <span>2</span>
                   <span>待收货</span>
                 </li>
                 <li class="shopping-item">
                   <span>0</span>
                   <span>待发货</span>
                 </li>
                 <li class="shopping-item">
                   <span>4</span>
                   <span>待付款</span>
                 </li>
                 <li class="shopping-item">
                   <span>3</span>
                   <span>待评价</span>
                 </li>
               </ul>
             </div>
             <div class="tool">
               <ul>
                 <li class="tool-item">
                   <span>
                     <icon-user />
                   </span>
                   <span>宝贝收藏</span>
                 </li>
                 <li class="tool-item">
                   <span>
                     <icon-user />
                   </span>
                   <span>我的消息</span>
                 </li>
                 <li class="tool-item" @click="router.push({name:'Profile'})">
                   <span>
                     <icon-user />
                   </span>
                   <span>个人信息</span>
                 </li>
                 <li class="tool-item">
                   <span>
                     <icon-user />
                   </span>
                   <span>我的足迹</span>
                 </li>
               </ul>
             </div>
           </div>
         </a-col>
         <a-col :span="2"></a-col>
       </a-row>
        <a-row>
          <a-col :span="3"></a-col>
          <a-col :span="19">
            <h1 class="personality-recommendation">
              <span>猜你喜欢</span>
              <img src="src/assets/images/personality-recommendation.png">
            </h1>
            <div class="good-item">
              <GoodItem v-for="item in goods" :goodItem="item" key="item.id"></GoodItem>
            </div>
          </a-col>
          <a-col :span="2"></a-col>
        </a-row>
      </a-layout-content>
      <a-layout-footer>
        <Footer></Footer>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script setup>
import Header from "@/components/Header.vue";
import { IconDesktop } from '@arco-design/web-vue/es/icon';
import {IconUser} from "@arco-design/web-vue/es/icon";
import GoodItem from "@/components/GoodItem.vue";
import {reactive, ref} from "vue";
import Footer from "@/components/Footer.vue";
import {getCommoditiesByPage} from '@/http/commodity'
import {getUserMesApi} from "@/http/user";
import {useRouter} from "vue-router";

const router = useRouter()

const images = [
  'https://gw.alicdn.com/imgextra/i4/O1CN01a71ilU1T2RCTvUXDY_!!6000000002324-0-tps-846-472.jpg',
  'https://img.alicdn.com/imgextra/i2/O1CN01XZC8Dd1IUs179sweg_!!6000000000897-2-tps-846-472.png',
  'https://img.alicdn.com/imgextra/i3/O1CN01Sf6dER1zbJ3uVQ0lE_!!6000000006732-0-tps-846-472.jpg',
  'https://img.alicdn.com/imgextra/i1/O1CN01YGUXOM1k5VKBPo5J3_!!6000000004632-2-tps-846-472.png'
]
const goods = reactive([])

const pageObject = reactive({
  "current": 1,
  "size": 20
})

const userMes = ref({})


//获取推荐列表
const getRecommendListByPage = (page)=>{
  getCommoditiesByPage(page).then(res=>{
    if(res.data.code===200){
      goods.push(...res.data.data.records)
    }
  })
}

//获取用户信息
const getUserMes=()=>{
  let str = localStorage.getItem("userMes")
  if(str){                                  //如果localStorage没有再去服务端获取
    userMes.value=JSON.parse(str)
  }else{
    getUserMesApi().then(({data})=>{
      if(data.code===200){
        userMes.value=data.data
        localStorage.setItem("userMes",JSON.stringify(data.data))
      }
    })
  }
  userMes.value.avatarUrl= process.env.BASE_MEDIA + userMes.value.avatar
}

//初始化函数
function init(){
  getRecommendListByPage(pageObject);
  getUserMes()
}

init()
</script>

<style scoped lang="less">
.home-content{
  width: 100%;
  overflow-x: hidden;
  //overflow-y: scroll;
  //background: linear-gradient(200deg, #f3e7e9, #325a96);
  .classify{
    width: 100%;
    max-width: 300px;
    height: 360px;
    background-color: #f7f9fa;
    padding: 20px 0 0 20px;
    border-radius: 8px;
    h2{
      font-weight: 400;
      font-size: 18px;
      margin-bottom: 15px;
    }
    ul{
      li{
        line-height: 20px;
        height: 20px;
        margin-bottom: 18px;
        color: #666;
        span{
          padding: 0 8px
        }
        a{
          color: #666;
        }
      }
      li:hover{
        color: red;
        a{
          color: red;
        }
      }
    }
  }
  .marquee-text marquee{
    height: 20px;
    color: #333;
    margin-left: 20px;
  }
  .slideshow{
    display: inline-block;
    width: 95%;
    margin-left: 5%;
  }
  .self-message{
    display: inline-block;
    width: 90%;
    height: 340px;
    margin: 20px 0 0 20px;
    padding: 35px 20px 10px;
    border-radius: 8px;
    background-color: #fff;
    .mes{
      height:120px;
      text-align: center;
      .self-nickname{
        font-size: 18px;
        margin-top: 30px;
        span{
          cursor: pointer;
        }
      }
      .arco-avatar-circle{
        //cursor: pointer;
      }
    }
    .shopping ul{
      display: flex;
      justify-content: space-between;
      margin-top: 25px;
      .shopping-item{
        display: inline-flex;
        height: 36px;
        cursor: pointer;
        justify-content: space-between;
        text-align: center;
        flex-direction: column;
        span:first-child{
          color: red;
        }
        span:last-child{
          color: #666666;
        }
      }
    }
    .tool ul{
      display: flex;
      justify-content: space-around;
      margin-top: 25px;
      .tool-item{
        display: inline-flex;
        height: 50px;
        color: #413d3d;
        cursor: pointer;
        justify-content:center;
        text-align: center;
        flex-direction: column;
        .arco-icon{
          font-size: 22px;
        }
        span{
          font-size: 14px;
          margin-top: 5px;
        }
      }
      .tool-item:hover{
        color: red;
      }
    }
  }
  .personality-recommendation{
    display: flex;
    align-items: end;
    height: 24px;
    line-height: 24px;
    margin: 50px 0 25px 8px;
    span{
      display: inline-block;
      letter-spacing: 2px;
      height: 24px;
      font-size: 24px;
      font-weight: 400;
      color: #111;
    }
    img{
      width: 65px;
      margin-left: 6px
    }
  }
  .good-item{
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    margin: 20px 10px 20px 0;
  }
}
.arco-layout-content{
  margin-top: 25px;
}
.arco-icon-desktop{
  font-size: 16px;
  margin-right: 12px;
}
</style>