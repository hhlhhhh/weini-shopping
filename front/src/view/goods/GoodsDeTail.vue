<template>
  <div class="goods-detail">
    <div class="header">
      <div class="header-logo">
        <img src="@/assets/images/logo.png">
      </div>
    </div>
    <div class="content">
      <div class="top">
        <div class="left">
          <div class="slideshow">
            <a-carousel
                :style="{
                  width: '400px',
                  height: '400px',
                }"
                auto-play
                indicator-type="dot"
                show-arrow="hover"
            >
              <a-carousel-item v-for="media in goodItem['mediaUrlList']">
                <div style="width: 100%;height:100%">
                  <img
                      v-if="imageReg.test(media.url)"
                      :src="media['url']"
                      :style="{
                      width:'100%',
                      height: '100%'
                  }"
                  >
                  <video
                      v-else
                      :src="media['url']"
                      :style="{
                      width:'100%',
                      height: '100%'
                  }"
                      controls
                      muted
                      autoplay
                  />
                </div>
              </a-carousel-item>
            </a-carousel>
          </div>
        </div>
        <div class="right">
          <div class="item-name">
            {{goodItem.name}}
          </div>
          <div class="item-detail">
            <ul>
              <li class="price">
                <span>价格</span>
                <span>
                  <p>￥</p>
                  <i>{{goodItem.price/100}}</i>
                </span>
              </li>
              <li class="address">
                <span>配送</span>
                <span>成都信息工程大学</span>
              </li>
              <li>
                <span>数量</span>
                <a-input-number mode="button" default-value="0" v-model="goodMes.count"></a-input-number>
              </li>
              <li>
                <ul class="type-option">
                  <li v-for="(type,index) in commodityTypeOption" :key="type.id">
                    <span style="color: #333333;display: inline-block;width:38px">{{type.name}}</span>
                    <span class="option">
                      <a-radio-group type="button" v-for="option in type['typeChoiceList']" :key="option.id" v-model="choice[index]">
                        <a-grid :cols="1" :colGap="5" :rowGap="10">
                          <a-grid-item>
                            <a-radio :value="option['id']">{{option['name']}}</a-radio>
                          </a-grid-item>
                        </a-grid>
                      </a-radio-group>
                    </span>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
          <div class="operate">
            <a-button>立即购买</a-button>
            <a-button @click="addToShoppingCart">加入购物车</a-button>
          </div>
        </div>
      </div>
      <div class="bottom">
        <div class="left">
          <div class="shop">
            <ul>
              <li class="shop-image">
                <img :src="shopMes.baseMes.image">
              </li>
              <li class="shop-name">
                {{shopMes.baseMes.name}}
              </li>
              <li class="introduce">
                <p>{{shopMes.baseMes.introduce}}</p>
              </li>
              <li class="sell-list">
                <h1>售卖榜首</h1>
                <ul>
                  <li v-for="topItem in shopMes.selledTop" :key="topItem.id" @click="gotoDetail(topItem)">
                    <a>
                      <div class="item-content">
                        <div class="item-image">
                          <img v-if="imageReg.test(topItem['image-url'])" :src="topItem['image-url']">
                          <video v-else :src="topItem['image-url']" muted autoplay/>
                        </div>
                        <div class="item-desc">
                          <div>
                            Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女
                          </div>
                          <div class="item-price">
                            <em>¥</em>
                            24.9
                          </div>
                        </div>
                      </div>
                    </a>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </div>
        <div class="right">
          <a-tabs>
            <a-tab-pane key="1">
              <template #title>
                 描述
              </template>
              <div class="describe">
                <span style="display: inline-block; height: 30px;">详细描述</span>
                <p>
                  {{goodItem.introduce}}
                </p>
              </div>
            </a-tab-pane>
            <a-tab-pane key="2">
              <template #title>
                <icon-clock-circle/> 评论
              </template>
              <div class="comment-input">
                  <a-textarea placeholder="请友善发表评论" allow-clear max-length="200" :auto-size="{minRows: 4,maxRows: 5}"/>
                  <a-button style="color: #FF5000;background-color: #FFE4D0">发送</a-button>
              </div>
              <a-divider :margin="12" style="border-color: #FFE4D0"></a-divider>
              <div class="comments">
                <a-comment
                    author="Socrates"
                    avatar="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/3ee5f13fb09879ecb5185e440cef6eb9.png~tplv-uwbnlip3yd-webp.webp"
                    content="Comment body content."
                    datetime="1 hour"
                >
                  <template #actions>
                    <span class="action"> <IconMessage /> Reply </span>
                  </template>
                  <a-comment
                      author="Balzac"
                      avatar="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/9eeb1800d9b78349b24682c3518ac4a3.png~tplv-uwbnlip3yd-webp.webp"
                      content="Comment body content."
                      datetime="1 hour"
                  >
                    <template #actions>
                      <span class="action"> <IconMessage /> Reply </span>
                    </template>
                  </a-comment>
                  <a-comment
                      author="Austen"
                      avatar="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/8361eeb82904210b4f55fab888fe8416.png~tplv-uwbnlip3yd-webp.webp"
                      content="Reply content"
                      datetime="1 hour"
                  >
                    <template #actions>
                      <span class="action"> <IconMessage /> Reply </span>
                    </template>
                  </a-comment>
                  <a-comment
                      author="Plato"
                      avatar="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/3ee5f13fb09879ecb5185e440cef6eb9.png~tplv-uwbnlip3yd-webp.webp"
                      content="Reply content"
                      datetime="1 hour"
                  >
                    <template #actions>
                      <span class="action"> <IconMessage /> Reply </span>
                    </template>
                  </a-comment>
                </a-comment>
              </div>
            </a-tab-pane>
          </a-tabs>
        </div>
      </div>
    </div>
    <div class="footer">
      <Footer/>
    </div>
  </div>
</template>

<script setup>
import Footer from "@/components/Footer.vue";
import {onMounted, reactive, ref,watch} from "vue";
import {useRoute} from 'vue-router'
import {getSelledTop, getShopMes} from "@/http/shop";
import {dealMediaUrl} from "@/utils";
import {useRouter} from "vue-router";
import {addToShoppingCartApi} from "@/http/shoppingCart";
import {useToast} from "primevue/usetoast";
import {getCommodityTypeOptionApi} from "@/http/commodity";

const toast = useToast()

const route = useRoute()
const router = useRouter()

//图片正则表达式
const imageReg = /\.(jpg|jpeg|png|bmp|gif)$/

let shopMes=reactive({
  baseMes:{},
  selledTop:[]
})

let goodItem = ref({})
let goodMes = reactive({
  "address_id": "",
  "count": 0,
  "typeOption":[]
})

const commodityTypeOption = ref([])
const choice = reactive([])


const getCommodityTypeOption = ()=>{
  getCommodityTypeOptionApi({id:goodItem.value.id}).then(({data})=>{
    if(data.code===200){
      commodityTypeOption.value = data.data
    }
  })
}

const addToShoppingCart = ()=>{
  for (let i = 0; i < commodityTypeOption.value.length; i++) {
    if(!choice[i]){
      toast.add({ severity: 'error', summary: "错误", detail: '请正确选择配置参数！', life: 1500 })
      return
    }
  }
  if(goodMes.count===0){
    toast.add({ severity: 'error', summary: "错误", detail: `商品数量不能为${goodMes.count}！`, life: 1500 })
    return
  }
  const types = []
  commodityTypeOption.value.forEach(e=>{
    types.push(e.id)
  })
  addToShoppingCartApi([{commodity_id:goodItem.value.id,count:goodMes.count,types,choices:choice}]).then(({data})=>{
    if(data.code===200){
      toast.add({ severity: 'success', summary: "操作成功！", detail: "添加商品成功！", life: 1500 })
      router.push({name:"ShoppingCart"})
    }
  })
}

//点击售卖榜单的每个单子
const gotoDetail = (item)=>{
  router.push({
    name: "GoodDetail",
    query:{
      goodItem:JSON.stringify(item)
    }
  })
}

onMounted(()=>{
  goodItem.value = JSON.parse(route.query.goodItem)
  getShopMes({"id":goodItem.value['shop_id']}).then(({data})=>{
    shopMes.baseMes=data.data
    shopMes.baseMes.image=process.env.BASE_MEDIA+shopMes.baseMes.image
  })
  getSelledTop({id:goodItem.value['shop_id'],n:3}).then(({data})=>{
    shopMes.selledTop=data.data
    shopMes.selledTop.forEach(e=>{
      dealMediaUrl(e)
    })
  })
  getCommodityTypeOption()
})

//跳转到同一个 页面页面不刷新问题
watch(()=>route.query,(to,from)=>{
    if (to.name === from.name) {
      location.reload()
    }
})


</script>

<style scoped lang="less">
  .goods-detail{
    .header{
      display: block;
      height: 70px;
      color: #333333;
      background-color: rgb(181, 186, 206);
      margin: 0 auto;
      .header-logo{
        width: 200px;
        text-align: center;
        img{
          width: 70px;
        }
      }
    }
    .content{
      width:1200px;
      margin: 20px auto 0;
      .top{
        display: flex;
        .left{
          margin-right: 15px;
          .slideshow{
            background-color: rgb(202, 216, 213);
            :deep(.arco-carousel-slide)>div{
              //position: relative;
              img{
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%,-50%);
              }
            }
            :deep(.arco-carousel-indicator-wrapper-bottom){
              background: none;
            }
          }
        }
        .right{
          width: 600px;
          .item-name{
            font-family: tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
            font-weight: bold;
            font-size: 16px;
            margin-bottom: 15px;
          }
          .item-detail ul{
            background-color: rgb(255, 249, 245);
            >li{
              display: flex;
              padding: 10px;
              >span:first-child{
                color: #666666;
                font-family: tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
                margin-right: 40px;
              }
              .arco-input-number{
                width: 120px;
              }
            }
            .price{
              height: 60px;
              line-height: 40px;
              background-color: #FFF2E8;
              p{
                color: #FF5000;
                font-size: 18px;
                display: inline-block;
              }
              i{
                color: #FF5000;
                font-size: 22px;
                font-family: tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
                font-weight: bold;
              }
            }
            .address{
              color: #3c3c3c;
              font-family: tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
            }
            .type-option{
              display: block;
              >li{
                font-size: 14px;
                color: #3c3c3c;
                padding-left: 0;
                .option{
                  :deep(.arco-radio-button){
                    display: inline-block;
                    background-color: rgba(242, 243, 245);
                  }
                }
              }
            }
          }
          .operate{
            margin-top: 30px;
            button:first-child{
              color: #E5511D;
              background-color: #FFE4D0;
            }
            button:last-child{
              color: #fff;
              background-color: #F40;
              margin-left: 10px;
            }
          }
        }
      }
      .bottom{
        display: flex;
        margin-top: 80px;
        .left{
          display: inline-block;
          max-width: 300px;
          min-width: 300px;
          .shop>ul{
            display: flex;
            flex-direction: column;
            .shop-image{
              text-align: center;
              >img{
                max-width: 100px;
                max-height: 100px;
                border-radius: 50px;
                border: 2px solid #325a96;
              }
            }
            .shop-name{
              color: #000;
              font-size: 18px;
              text-align: center;
            }
            .introduce{
              width: 100%;
              color: #333333;
              overflow: hidden;
              text-overflow: ellipsis;
              display: -webkit-box;
              -webkit-line-clamp: 4;
              -webkit-box-orient: vertical;
              padding-right: 20px;
              margin: 20px 0;
            }
            .sell-list{
              >h1{
                font-size: 18px;
                color: #222;
                letter-spacing: 2px;
                text-indent: 1em;
              }
            }
          }
        }
        .item-content{
          display: inline-flex;
          height: 100px;
          background-color: #f7f9fa;
          cursor: pointer;
          padding: 11px;
          margin: 0 12px 15px 0;
          border: 1px solid #f7f9fa;
          border-radius: 8px;
          &:nth-child(3n) {
            margin-right: 0;
          }
          .item-image{
            display:inline-block;
            width: 80px;
            video,img{
              width: 80px;
              height: 100%;
            }
          }
          .item-desc{
            display: inline-flex;
            flex-direction: column;
            justify-content: space-between;
            letter-spacing: 1px;
            flex-wrap: wrap;
            font-size: 12px;
            color: #333;
            margin-left: 8px;
            >div:first-child{
              overflow: hidden;
              text-overflow: ellipsis;
              display: -webkit-box;
              -webkit-line-clamp: 2;
              -webkit-box-orient: vertical;
            }
            :hover{
              color: #FF5000;
            }
            .item-price{
              color: #FF5000;
              font-size: 16px;
              em{
                font-size: 14px;
              }
            }
          }
        }
        .item-content:hover{
          border-color: #ff915e;
        }

        .right{
          .comment-input{
            display: inline-block;
            width: 100%;
            //height: 200px;
            margin-bottom: 10px;
          }
          .comments{
            padding-left: 10px;
          }
        }
      }
    }
    :deep(.arco-tabs-tab-active){
      color: #c76538;
    }
    :deep(.arco-tabs-nav-ink){
      background-color: #c76538;
    }
    .carousel-image {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
  }
</style>