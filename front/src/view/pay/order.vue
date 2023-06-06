<template>
  <PayHeader/>
  <div class="order">
    <div class="content">
      <div class="confirm-address">
        <header>确认收货地址</header>
        <div class="address-list">
          <a-radio-group direction="vertical" v-model="orderMes.address_id">
            <ul>
              <li v-for="address in addressData">
                <a-radio :value="address.id">{{address.province}}  {{address.city}}  {{ address.town }} &nbsp;&nbsp;&nbsp; {{address.detail}} &nbsp;&nbsp;&nbsp; （{{address.nickname}} 收）&nbsp;&nbsp;&nbsp; {{address.phone}}</a-radio>
              </li>
            </ul>
          </a-radio-group>
        </div>
      </div>
      <div class="confirm-message">
        <header>确认订单信息</header>
        <div class="order-message">
          <ul class="title">
            <li>店铺商品</li>
            <li>商品属性</li>
            <li>单价</li>
            <li>数量</li>
            <li>总计</li>
          </ul>
          <ul class="content">
            <li class="item-good">
              <div class="item-header">
                店铺: <a>老六印刷厂</a>
              </div>
              <div class="item-content">
                <img src="@/assets/images/logo.png">
                <div class="item-name">
                  <p>
                    A4纸张打印A4纸张打印 A4纸张打印A4纸张打印 A4纸张打印A4纸张打印 A4纸张打印A4纸张打印
                  </p>
                </div>
              </div>
            </li>
            <li class="item-category">
              <ul>
                <li>颜色分类：<i>白色</i></li>
              </ul>
            </li>
            <li>
              15.00
            </li>
            <li>
              1
            </li>
            <li>
              5.80
            </li>
          </ul>
          <ul class="content">
            <li class="item-good">
              <div class="item-header">
                店铺: <a>老六印刷厂</a>
              </div>
              <div class="item-content">
                <img src="@/assets/images/logo.png">
                <div class="item-name">
                  <p>
                    A4纸张打印A4纸张打印 A4纸张打印A4纸张打印 A4纸张打印A4纸张打印 A4纸张打印A4纸张打印
                  </p>
                </div>
              </div>
            </li>
            <li class="item-category">
              <ul>
                <li>颜色分类：<i>白色</i></li>
              </ul>
            </li>
            <li>
              15.00
            </li>
            <li>
              1
            </li>
            <li>
              5.80
            </li>
          </ul>
        </div>
      </div>
      <div class="order-operate">
        <a-button>提交订单</a-button>
      </div>
    </div>
    <Footer/>
  </div>
</template>

<script setup>

import Footer from "@/components/Footer.vue";
import PayHeader from "@/view/pay/components/PayHeader.vue";
import {getAddressListApi} from "@/http/address";
import {onMounted, reactive} from "vue";


const addressData = reactive([])

const orderMes = reactive({
  address_id:"",
  commodityList:[]
})

const getAddressList = ()=>{
  getAddressListApi().then(({data})=>{
    if(data.code === 200){
      addressData.length=0
      data.data.forEach(e=>{
        addressData.push(e)
      })
    }
  })
}

onMounted(()=>{
  getAddressList()
  let mesStr = localStorage.getItem("order-commodityList")
  if(mesStr){
    orderMes.commodityList = JSON.parse(mesStr) //获取订单的商品信息
  }
})

</script>

<style scoped lang="less">
.order{
  :deep(.arco-radio-label){
    color:var(--color-text-3)
  }
  >.content{
    width: 1200px;
    margin: 0 auto;
    .confirm-address{
      padding: 20px;
      >header{
        font-weight: bold;
        font-size: 15px;
      }
      .address-list{
        padding: 10px 0 0 10px;
        ul>li{
          position: relative;
          display: inline-flex;
          height: 32px;
          width: 1000px;
          min-width: 1000px;
          line-height: 32px;
          >a{
            position: absolute;
            color: #c75521;
            right: 0;
            top: 0;
          }
        }
      }
    }
    .confirm-message{
      padding: 20px;
      >header{
        font-weight: bold;
        font-size: 15px;
      }
      .order-message{
        >.title{
          display: flex;
          padding: 10px;
          >li{
            flex: 1;
          }
          >li:first-child{
            flex: 2;
          }
          >li:nth-child(2){
            flex: 1.5;
          }
        }
        >.content{
          display: flex;
          padding: 10px;
          >li{
            flex: 1;
          }
          >.item-good{
            flex: 2;
            .item-header{
              padding-left: 10px;
              a{
                color: #3c3c3c;
                font-family: tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
                margin-left: 10px;
                &:hover{
                  text-decoration: underline;
                }
              }
            }
            >.item-content{
              display: flex;
              >img{
                width: 60px;
              }
              >.item-name{
                width: 250px;
                color: #3c3c3c;
                font-family: tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
                >p{
                  overflow: hidden;
                  text-overflow: ellipsis;
                  display: -webkit-box;
                  -webkit-line-clamp: 3;
                  -webkit-box-orient: vertical;
                  cursor: pointer;
                }
              }
            }
          }
          >.item-category{
            flex: 1.5;
          }
        }
      }
    }
    .order-operate{
      display: inline-block;
      width: 1000px;
      >button{
        width: 100px;
        float: right;
      }
    }
  }
}
</style>