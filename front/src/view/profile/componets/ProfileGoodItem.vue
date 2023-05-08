<template>
  <div class="item-content">
    <div class="left">
      <div class="item-image">
        <img :src="goodItem['image-url']">
      </div>
      <div class="item-desc">
        <div class="item-desc-top">
          <div class="item-name">
            {{goodItem.name}}
          </div>
          <div class="desc">
            {{goodItem.desc}}
          </div>
        </div>
        <div>
          <span class="item-price">
            <em>¥</em>
            {{goodItem.price}}
          </span>
          <span class="browse-time">2023.06.14</span>
          <span class="star" @click="collectStar">
            <icon-star-fill v-if="star"/>
            <icon-star v-else/>
          </span>
        </div>
      </div>
    </div>
    <div class="right">
      <div class="operate">
        <a-button>立即购买</a-button>
        <a-button>加入购物车</a-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import {IconStarFill,IconStar} from "@arco-design/web-vue/es/icon";
import {ref} from 'vue'

let {goodItem} = defineProps(['goodItem'])

let star = ref(true)
//是否收藏
const collectStar = ()=>{
  star.value=!star.value
}


function init(){
  // console.log(goodItem)
}

init()
</script>

<style scoped lang="less">

.item-content{
  display: flex;
  justify-content: space-between;
  height: 172px;
  background-color: #f7f9fa;
  padding: 11px;
  margin: 0 12px 15px 0;
  border: 1px solid #f7f9fa;
  border-radius: 8px;
  .left{
    display: flex;
    .item-image{
      display:inline-block;
      width: 150px;
      img{
        width: 150px;
      }
    }
    .item-desc{
      display: inline-flex;
      flex-direction: column;
      justify-content: space-between;
      letter-spacing: 1px;
      font-size: 15px;
      color: #333;
      margin-left: 8px;
      .item-desc-top{
        width:600px;
        .item-name{
          overflow: hidden;
          text-overflow: ellipsis;
          display: -webkit-box;
          -webkit-line-clamp: 1;
          -webkit-box-orient: vertical;
        }
        .desc{
          overflow: hidden;
          text-overflow: ellipsis;
          display: -webkit-box;
          -webkit-line-clamp: 2;
          -webkit-box-orient: vertical;
          cursor: pointer;
          margin-top: 10px;
          &:hover{
            color: #FF5000;
          }
        }
      }
      .item-price{
        color: #FF5000;
        font-size: 22px;
        em{
          font-size: 14px;
        }
      }

      .browse-time{
        margin-left: 50px;
      }

      .star{
        font-size: 20px;
        cursor: pointer;
        margin-left: 10px;
        .arco-icon-star-fill{
          color: #FF5000;;
        }
      }
    }
  }

  .right{
    .operate{
      display: flex;
      flex-direction: column;
      justify-content: center;
      .arco-btn-secondary:first-child{
        margin-bottom: 15px;
      }
    }
  }
}
.item-content:hover{
  border-color: #ff915e;
}

</style>