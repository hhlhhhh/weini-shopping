<template>
  <div class="purchasedItem">
    <div class="item-header">
      <div class="item-checkbox">
        <input type="checkbox">
        <span>
          订单号：
          <i>1445157989625454</i>
        </span>
      </div>
      <div class="operate">
        <a-button>
          <template #icon>
            <icon-delete/>
          </template>
        </a-button>
      </div>
    </div>
    <div class="item-content">
      <ul>
        <li class="goods-item">
          <div class="item-img">
            <img src="https://img11.360buyimg.com/jdcms/s300x300_jfs/t1/1831/1/19778/132503/63842db6E811848c4/83b8de2b8679eddc.jpg.avif">
          </div>
          <div class="item-desc">
            <div class="text">
              <div class="item-name">桃木沙发，质量保证，坐着舒服，躺着安逸桃木沙发，质量保证，坐着舒服，躺着安逸桃木沙发，质量保证，坐着舒服，躺着安逸桃木沙发，质量保证，坐着舒服，躺着安逸桃木沙发，质量保证，坐着舒服，躺着安逸桃木沙发，质量保证，坐着舒服，躺着安逸</div>
              <div class="item-category">
                <span>颜色分类</span>
                <i>红色</i>
                <span>颜色分类</span>
                <i>红色</i>
                <span>颜色分类</span>
                <i>红色</i>
                <span>颜色分类</span>
                <i>红色</i>
                <span>颜色分类</span>
                <i>红色</i>
                <span>颜色分类</span>
                <i>红色</i>
                <span>颜色分类</span>
                <i>红色</i>
                <span>颜色分类</span>
                <i>红色</i>
                <span>颜色分类</span>
                <i>红色</i>
                <span>颜色分类</span>
                <i>红色</i>
                <span>颜色分类</span>
                <i>红色</i>

              </div>
            </div>
            <div class="supplement-text">
              发货时间
              <i> 04月15日 11.52分发货</i>
            </div>
          </div>
        </li>
        <li>
          <span class="price-unit">￥</span>
          <i class="price">12.2</i>
        </li>
        <li>3</li>
        <li>
          <span class="price-unit">￥</span>
          <i class="price">46.8</i>
        </li>
        <li>
          <div class="operate">
            <a-button type="primary">评价</a-button>
            <a-button type="primary">再来一单</a-button>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import {IconDelete} from "@arco-design/web-vue/es/icon";

</script>

<style scoped lang="less">

.purchasedItem{
  background-color: #bcbfd1;
  border: 0.1px solid #bcbfd1;
  margin-top: 20px;
  &:first-child{
    margin-top: 0;
  }
  &:hover{
    border: 0.1px solid #8092a2;
  }
  .item-header{
    display: flex;
    justify-content: space-between;
    height: 42px;
    color: #333;
    font-family: 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
    background-color: #d7d7e3;
    padding: 5px 10px 5px 20px;
    .item-checkbox{

    }
    .operate{
      .arco-btn-secondary[type='button']{
        color: #333;
        font-size: 18px;
        background-color: #d7d7e3;
      }
    }
  }
  .item-content{
    ul{
      display: flex;
      >li{
        flex: 1;
        text-align: center;
        font-family: tahoma,arial,Hiragino Sans GB,\\5B8B\4F53,sans-serif;
        padding: 10px 20px;
      }
      >li:first-child{
        flex: 3;
        text-align: start;
      }
      .goods-item{
        display: flex;
        .item-img{
          img{
            width: 90px;
          }
        }
        .item-desc{
          display: flex;
          flex-direction: column;
          justify-content: space-between;
          margin-left: 10px;
          .text{
            font-size: 12px;
            font-family: tahoma,arial,Hiragino Sans GB,\\5B8B\4F53,sans-serif;
            .item-name{
              overflow: hidden;
              text-overflow: ellipsis;
              display: -webkit-box;
              -webkit-line-clamp: 2;
              -webkit-box-orient: vertical;
              color: #333;
            }
            .item-category{
              overflow: hidden;
              text-overflow: ellipsis;
              display: -webkit-box;
              -webkit-line-clamp: 2;
              -webkit-box-orient: vertical;
              font-size: 10px;
              color: #666666;
              margin: 6px 0;
            }
          }
          .supplement-text{
            font-size: 10px;
            color: #413a3a;
          }
        }
      }
      .price-unit{
        font-family: arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
      }
      .price{
        color: #333333;
        font-family: 'Hiragino Sans GB',tahoma, arial, '\5b8b\4f53', sans-serif;
      }
      .operate{
        .arco-btn-primary{
          font-size: 14px;
          color: #333333;
          background-color: #f7f9fa;
          margin-top: 10px;
        }
      }
    }
  }
}

</style>