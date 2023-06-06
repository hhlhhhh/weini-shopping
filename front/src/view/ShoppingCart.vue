<template>
  <div class="shopping-cart-content">
    <a-layout>
      <a-layout-header style="height: 80px">
        <div class="top-bar">
          <div>
            <a-image
                width="70"
                src="src/assets/images/logo.png"
                :preview="false"
            />
          </div>
          <div>
            <a-input-search :style="{width:'250px',height:'36px',borderRadius:'8px'}" placeholder="请输入宝贝信息"/>
          </div>
        </div>
      </a-layout-header>
      <a-layout-content>
        <div class="shopping-cart">
          <div class="header">
            <div class="left-header">
              <div >
                购物车（全部
                <span>{{shoppingCartMes.count}}</span>
                ）
              </div>
            </div>
            <div class="right-header">
              <div class="total-money">
                已选商品
                <span class="money">0.00</span>
              </div>
              <div class="settleAccounts-btn">
                <a-button type="primary" shape="round">结算</a-button>
              </div>
            </div>
          </div>
          <a-divider type="dotted" :margin="12"></a-divider>
          <div class="body">
            <div class="table-title">
              <div class="check-all">
                <input id="check-all-goods1" type="checkbox">
                <label for="check-all-goods1">全选</label>
              </div>
              <div>
                商品信息
              </div>
              <div>
                单价
              </div>
              <div>
                数量
              </div>
              <div>
                金额
              </div>
              <div>
                操作
              </div>
            </div>
            <div class="shopping-cart-item" v-for="cartItem in commoditiesTmp" :key="cartItem.id">
              <template v-if="cartItem">
                <div class="item-header">
                  <div class="item-checkbox">
                    <input type="checkbox">
                  </div>
                  <div class="item-shop">
                    店铺：
                    <span>
                  {{cartItem['shop_mes']['name']}}
                </span>
                  </div>
                </div>
                <div class="item-box" v-for="item in cartItem['commodity']">
                  <ul>
                    <li>
                      <input type="checkbox">
                    </li>
                    <li>
                      <img v-if="item['media-type']==='image'" class="item-image" :src="item['image-url']">
                      <video v-else class="item-image" :src="item['image-url']" muted autoplay/>
                    </li>
                    <li>
                      {{item.name}}
                    </li>
                    <li>
                      <ul class="goods-item-category">
                        <li v-for="(typeItem) in cartItem['type']" :key="typeItem.id">
                          <span>{{typeItem['typeName']}}</span>：
                          <span>{{typeItem['typeChoiceName']}}</span>
                        </li>
                      </ul>
                    </li>
                    <li>
                      <span class="seckill-price">￥{{item['price']}}</span>
                    </li>
                    <li>
                      <a-input-number :model-value="testNumberInput" :style="{width:'80px'}" :min="0" :max="100" mode="button" size="mini" class="item-count" />
                    </li>
                    <li>
                      <span>￥</span>
                      <span>{{item['price']*cartItem['count']}}</span>
                    </li>
                    <li>
                      <a>移入收藏夹</a>
                      <a>删除</a>
                    </li>
                  </ul>
                </div>
              </template>
            </div>
          </div>
          <div class="tail">
            <ul>
              <li>
                <div class="check-all">
                  <input id="check-all-goods2" type="checkbox">
                  <label for="check-all-goods2">全选</label>
                </div>
              </li>
              <li>
                <a class="del-goods">删除</a>
                <a class="move-collect">移入收藏夹</a>
              </li>
              <li>
                已选商品
                <span class="item-count">2</span>
                件
              </li>
              <li>
                <span>合计：</span>
                <span class="item-total">
                  ￥
                  <span class="item-total-money">374.30</span>
                </span>
              </li>
              <li>
                <div class="item-settle">
                  <a-button type="primary" shape="round">结算</a-button>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </a-layout-content>
      <a-layout-footer>
        <Footer></Footer>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script setup>
import Footer from "@/components/Footer.vue";
import {ref, getCurrentInstance, onMounted, reactive, onBeforeMount, toRefs, triggerRef, toRef, watch} from 'vue'
import {getCommodityTypeChoiceApi, getShoppingCartList} from "@/http/shoppingCart";
import {getShopMes} from "@/http/shop";
import {dealMediaUrl} from "@/utils";

const instance = getCurrentInstance();

let testNumberInput = ref(0)
let userMes = ref({})

const treeData = ref([
  {
    title: '',
    key: '0',
    children: [

      ]
  }
])

const shoppingCartMes = ref({
  commodities:[{"shop_mes":{},"commodity":{}}]
})

const commoditiesTmp = ref([])

//获取购物车数据
const getAllShoppingCartItem = ()=>{
   getShoppingCartList({current: 1 ,size: 100}).then(({data})=>{
    if(data.code === 200){
      shoppingCartMes.value = data.data
      shoppingCartMes.value.commodities.forEach(async (e)=>{
        await getShopMes({id:e['commodity']['shop_id']}).then(({data})=>{
          e['shop_mes'] = data.data
        })
        getCommodityTypeChoiceApi({"id":e['id']}).then(({data})=>{
          let arr = []
          data.data.forEach((e,index)=>{
            if(index<3){
              arr.push({
                "id":e.id,
                "typeName":e.type.name,
                "typeChoiceName":e.typeChoice.name
              })
            }
          })
          e['type']=arr
        })
        dealMediaUrl(e['commodity'])

        let i = 0
        for (; i < commoditiesTmp.value.length; i++) {   //合并同类店铺
          if(commoditiesTmp.value[i]['commodity']['0']['shop_id']===e['commodity']['shop_id']){
            commoditiesTmp.value[i]['commodity'].push(e['commodity'])
            break
          }
        }
        if(i===commoditiesTmp.value.length) {
          commoditiesTmp.value.push({
            ...e,
            commodity: [e.commodity]
          })
        }
      })
    }
  })

  treeData.value.children = commoditiesTmp;

  console.log(treeData)
}

watch(()=>treeData,(newValue,oldValue)=>{
  console.log(newValue.value)
  console.log(66)
},{deep:true,immediate:true})

onMounted( ()=>{
  //获取用户信息
  let userMesStr = localStorage.getItem("userMes")
  userMes.value = JSON.parse(userMesStr)

  getAllShoppingCartItem()

})


</script>

<style scoped lang="less">
.shopping-cart-content{
  width: 100%;
  overflow-x: hidden;
  :deep(.arco-tree-node-switcher){
    display: none;
  }
  :deep(.arco-checkbox-icon){
    width: 18px;
    height: 18px;
  }
  .top-bar{
    position: fixed;
    display: flex;
    height: 80px;
    width: 100%;
    min-width: 1200px;
    align-items: center;
    justify-content: space-around;
    padding: 0 150px;
    background-color: rgb(179, 184, 205);
    z-index: 99;
  }

  .shopping-cart{
    width: 1000px;
    background-color: rgb(250, 246, 246);
    border-radius: 26px;
    padding-bottom: 10px;
    //overflow-x: hidden;
    margin: 20px auto;
    .arco-divider-horizontal{
      border-color: gray;
    }
    .header{
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 60px;
      padding: 0 18px;
      .left-header{
        //height: 45px;
        font-size: 18px;
      }
      .right-header{
        display: flex;
        align-items: center;
        font: 14px tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
        .total-money{
          margin-right: 18px;
          .money{
            color: red;
            font-size: 24px;
            margin-left: 8px;
          }
        }
        .arco-btn{
          height: 40px;
          font: 18px tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
          letter-spacing: 3px;
          border-radius: 15px;
          padding: 0 15px;
          background-color: #ff915e;
        }
      }
    }
    .body{
      padding: 0 24px;
      margin: 18px 0 40px;
      .table-title{
        display: flex;
        align-items: center;
        justify-content: flex-start;
        color: #333333;
        width: 1000px;
        padding-right: 20px;
        .check-all{
          display: table;
          border-collapse: collapse;
          height: 21px;
          line-height: 21px;
          vertical-align: center;
          input[type="checkbox"]{
            width: 18px;
            height: 18px;
            display: table-cell;
            vertical-align: middle;
            cursor: pointer;
            margin-right: 5px;
          }
          label{
            display: inline;
            cursor: pointer;
          }
        }
        >div{
          flex: 1;
        }
        >div:nth-child(1){
          flex: 1;
        }
        >div:nth-child(2){
          flex: 3.5;
        }
        >div:nth-child(3){
          flex: 1.5;
        }
      }
      .shopping-cart-item{
        display: table;
        width: 100%;
        border-collapse: collapse;
        vertical-align: middle;
        font-family: tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
        margin-top: 25px;
        .item-header{
          margin-left: 8px;
          .item-checkbox{
            display: table-cell;
            input[type="checkbox"]{
              width: 18px;
              height: 18px;
              vertical-align: middle;
            }
          }
          .item-shop{
            display: table-cell;
            padding-left: 10px;
            span{
              cursor: pointer;
              &:hover{
                border-bottom: 1.5px solid rgb(255, 145, 94);
                padding-bottom: 1px;
              }
            }
          }
        }
        .item-box {
          height: 140px;
          background-color: #eeeae8;
          margin-top: 15px;
          border-radius: 12px;
          border: 1px solid #ff915e;
          > ul {
            display: flex;
            padding: 25px 20px;

            > li:first-child {
              margin-right: 10px;

              input[type="checkbox"] {
                width: 18px;
                height: 18px;
              }
            }

            > li:nth-child(2) {
              margin-right: 10px;

              video,img {
                width: 80px;
                border-radius: 4px;
              }
            }

            > li:nth-child(3) {
              width: 200px;
              min-width: 200px;
              font-size: 12px;
              color: #3c3c3c;
              font-family: tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
              margin-right: 50px;
            }

            > li:nth-child(4) {
              width: 150px;
              min-width: 150px;
              color: #9c9c9c;
              font-size: 12px;
              font-family: tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
              margin-right: 15px;
            }
            >li:nth-child(5){
              margin-right: 60px;
              .original-price{
                text-decoration: line-through;
                color: #9c9c9c;
                font-size: 14px;
              }
              .seckill-price{
                font-size: 15px;
                font-family: Verdana,Tahoma,arial,serif;
                //font-weight: bold;
                color: #000;
              }
              span{
                display: block;
              }
            }
            >li:nth-child(6){
              margin-right: 20px;
            }
            >li:nth-child(7){
              margin-right: 25px;
              span{
                font-size: 15px;
                color: #FF5000;
              }
            }
            >li:nth-child(7){
              margin-right: 25px;
            }
            >li:nth-child(8){
              a{
                display: block;
                color: #333;
                &:nth-child(2){
                  margin-top: 5px
                }
                &:hover{
                  color: #FF5000;
                  text-decoration: #FF5000 underline;
                }
              }
            }
          }
        }
      }
    }
    .tail{
      position: static;
      top:0;
      bottom: 0;
      height: 75px;
      border-top: 1px dotted #3c3c3c;
      padding: 0 20px;
      ul{
        display: flex;
        height: 100%;
        align-items: center;
        &,a{
          font-family: arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
          font-size: 14px;
          color: #3c3c3c;
        }
        li:first-child{
          margin-right:50px;
          .check-all{
            display: table;
            border-collapse: collapse;
            height: 21px;
            line-height: 21px;
            vertical-align: center;
            input[type="checkbox"]{
              width: 18px;
              height: 18px;
              display: table-cell;
              vertical-align: middle;
              cursor: pointer;
              margin-right: 5px;
            }
            label{
              display: inline;
              cursor: pointer;
            }
          }
        }
        li:nth-child(2){
          margin-right: 360px;
          a{
            &:first-child{
              margin-right:20px;
            }
            &:hover{
              color: #FF5000;
              text-decoration: #FF5000 underline;
            }
          }
        }
        li:nth-child(3){
          margin-right: 50px;
          span{
            color: #FF5000;
            font-size: 20px;
            font-weight: bold;
          }
        }
        li:nth-child(4){
          margin-right: 30px;
          .item-total{
            color: #FF5000;
            font-weight: bold;
            .item-total-money{
              font-size: 24px;
            }
          }
        }
        li:nth-child(5){
          .arco-btn{
            height: 40px;
            font: 18px tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
            letter-spacing: 3px;
            border-radius: 15px;
            padding: 0 15px;
            background-color: rgb(255, 80, 0);
          }
        }
      }
    }
  }
}

</style>