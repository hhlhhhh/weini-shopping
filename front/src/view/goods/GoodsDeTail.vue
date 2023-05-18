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
              <a-carousel-item v-for="image in images">
                <img
                  :src="image"
                  :style="{
                    maxWidth: '100%',
                    maxHeight: '100%'
                  }"
                />
              </a-carousel-item>
            </a-carousel>
          </div>
        </div>
        <div class="right">
          <div class="item-name">
            这是一本二手书，linux二手书
          </div>
          <div class="item-detail">
            <ul>
              <li class="price">
                <span>价格</span>
                <span>
                  <p>￥</p>
                  <i>27.5</i>
                </span>
              </li>
              <li class="address">
                <span>配送</span>
                <span>成都信息工程大学</span>
              </li>
              <li>
                <span>数量</span>
                <a-input-number mode="button"></a-input-number>
              </li>
            </ul>
          </div>
          <div class="operate">
            <a-button>立即购买</a-button>
            <a-button>加入购物车</a-button>
          </div>
        </div>
      </div>
      <div class="bottom">
        <div class="left">
          <div class="shop">
            <ul>
              <li class="shop-image">
                <img src="@/assets/images/logo.png">
              </li>
              <li class="shop-name">
                唯你购物官方旗舰店
              </li>
              <li class="introduce">
                <p>
                  这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺这是一家店铺
                </p>
              </li>
              <li class="sell-list">
                <h1>售卖榜首</h1>
                <ul>
                  <li>
                    <a>
                      <div class="item-content">
                        <div class="item-image">
                          <img src="https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg">
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
                  <li>
                    <a>
                      <div class="item-content">
                        <div class="item-image">
                          <img src="https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg">
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
                  <li>
                    <a>
                      <div class="item-content">
                        <div class="item-image">
                          <img src="https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg">
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
                <span>详细描述</span>
                <p>
                  Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女
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
import {onMounted, reactive} from "vue";


const images = [
  'https://gw.alicdn.com/imgextra/i4/O1CN01a71ilU1T2RCTvUXDY_!!6000000002324-0-tps-846-472.jpg',
  'https://img.alicdn.com/imgextra/i2/O1CN01XZC8Dd1IUs179sweg_!!6000000000897-2-tps-846-472.png',
  'https://img.alicdn.com/imgextra/i3/O1CN01Sf6dER1zbJ3uVQ0lE_!!6000000006732-0-tps-846-472.jpg',
  'https://img.alicdn.com/imgextra/i1/O1CN01YGUXOM1k5VKBPo5J3_!!6000000004632-2-tps-846-472.png'
]

onMounted(() => {

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
            img{
              width: 80px;
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