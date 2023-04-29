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
                        src="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/3ee5f13fb09879ecb5185e440cef6eb9.png~tplv-uwbnlip3yd-webp.webp"
                    />
                  </a-avatar>
                </div>
                <div class="self-nickname">
                  Hi! <span>wn24096032</span>
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
                 <li class="tool-item">
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
import {reactive} from "vue";
import Footer from "@/components/Footer.vue";

const images = [
  'https://gw.alicdn.com/imgextra/i4/O1CN01a71ilU1T2RCTvUXDY_!!6000000002324-0-tps-846-472.jpg',
  'https://img.alicdn.com/imgextra/i2/O1CN01XZC8Dd1IUs179sweg_!!6000000000897-2-tps-846-472.png',
  'https://img.alicdn.com/imgextra/i3/O1CN01Sf6dER1zbJ3uVQ0lE_!!6000000006732-0-tps-846-472.jpg',
  'https://img.alicdn.com/imgextra/i1/O1CN01YGUXOM1k5VKBPo5J3_!!6000000004632-2-tps-846-472.png'
]
const goods = reactive([
  {
    'id':1,
    'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
    'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
    'price':24.9
  },
  {
    'id':1,
    'image-url':'https://img.pddpic.com/gaudit-image/2022-10-17/a8729b76f47207e36910e14519c5b71d.jpeg',
    'desc':"美洲野牛新款ins韩版短款拉链多卡位钱包可爱少女心小众设计卡包",
    'price':19.6
  },
  {
    'id':1,
    'image-url':'https://img.pddpic.com/gaudit-image/2022-10-17/b3d0a6e1947b2c245e9b6156c974eb21.jpeg',
    'desc':"GOGO TALES戈戈舞布里克熊镜面水光玻璃唇女保湿滋润唇釉平价口红",
    'price':16.4
  },
  {
    'id':1,
    'image-url':'https://t00img-c.yangkeduo.com/goods/images/2021-02-27/0c08e0b97016781cd83276b8e6e08b3f.jpeg',
    'desc':"GOGO TALES戈戈舞浮雕蝴蝶结唇泥哑光唇釉不易掉色口红学生党素颜",
    'price':21.5
  }
])

</script>

<style scoped lang="less">
.home-content{
  width: 100%;
  //overflow-x: hidden;
  //overflow-y: scroll;
  background: linear-gradient(200deg, #f3e7e9, #325a96);
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
        cursor: pointer;
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
    margin: 20px 10px 20px 0px;
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