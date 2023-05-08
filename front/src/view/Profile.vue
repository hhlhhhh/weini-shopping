<template>
  <div class="profile">
    <a-layout>
      <a-layout-header>
        <div class="header">
          <div class="header-logo">
            <img src="@/assets/images/logo.png">
          </div>
        </div>
      </a-layout-header>
        <a-layout class="content">
            <a-layout-sider class="sider">
              <ul class="function">
                <li><router-link @click="clickedKey=0" :class="{'clicked':clickedKey===0}" to="./detail">我的信息</router-link></li>
                <li><router-link @click="clickedKey=1" :class="{'clicked':clickedKey===1}" to="./shopping-cart">购物车</router-link></li>
                <li><router-link @click="clickedKey=2" :class="{'clicked':clickedKey===2}" to="./buy">我的购买</router-link></li>
                <li><router-link @click="clickedKey=3" :class="{'clicked':clickedKey===3}" to="./collect">我的收藏</router-link></li>
                <li><router-link @click="clickedKey=4" :class="{'clicked':clickedKey===4}" to="./footprint">我的足迹</router-link></li>
                <li><router-link @click="clickedKey=5" :class="{'clicked':clickedKey===5}" to="./address">我的地址</router-link></li>
              </ul>
            </a-layout-sider>
            <a-layout-content>
              <router-view></router-view>
            </a-layout-content>
        </a-layout>
        <a-layout-footer>
          <Footer></Footer>
        </a-layout-footer>
    </a-layout>
  </div>
</template>

<script setup>
import Footer from "@/components/Footer.vue";
import {ref} from "vue";
//https://www.mxnzp.com/api/address/list?app_id=udjeaop9hnigut2h&app_secret=NWk0b2ZkQkdWczJydFA0MTlJcnJ3dz09
const clickedKey = ref(0)   //控制sider高亮


</script>

<style scoped lang="less">
.profile{
  display: inline-block;
  width: 100%;
  overflow-x: hidden;
  .arco-layout-header{
    background-color: rgb(181, 186, 206);
  }
  .header{
    display: block;
    width:1200px;
    height: 70px;
    color: #333333;
    margin: 0 auto 0;
    .header-logo{
      width: 200px;
      text-align: center;
      img{
        width: 70px;
      }
    }
  }
  .content{
    width: 1200px;
    color: #000;
    margin: 20px auto 0;
    .sider{
      background-color: rgba(0,0,0,0);
      font-size: 16px;
      font-family: tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
      .function{
        display: flex;
        flex-wrap: wrap;
        >li{
          width: 100%;
          min-width: 100%;
          text-align: center;
          margin-top: 15px;
          a{
            color: #333333;
          }
          .clicked{
            color: #FF5000;
          }
        }
      }
    }
    .arco-layout-sider-light{
      box-shadow: none;
    }
  }
}
</style>