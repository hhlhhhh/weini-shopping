<template>
  <div class="item-content">
    <div class="item-image">
      <img :src="goodItem['image-url']">
    </div>
    <div class="item-desc">
      <div>
        {{goodItem.desc}}
      </div>
      <div class="item-price">
        <em>¥</em>
        {{goodItem.price}}
      </div>
    </div>
  </div>
</template>

<script setup>
let {goodItem} = defineProps(['goodItem'])

function init(){
  // console.log(goodItem)
}

init()
</script>

<style scoped lang="less">

.item-content{
  flex: 1;
  display: inline-flex;
  width: calc((100% - 24px) / 3);
  height: 172px;
  background-color: #f7f9fa;
  min-width: calc((100% - 24px) / 3);
  max-width: calc((100% - 24px) / 3);
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
    flex-wrap: wrap;
    font-size: 15px;
    color: #333;
    margin-left: 8px;
    :hover{
      color: #FF5000;
    }
    .item-price{
      color: #FF5000;
      font-size: 22px;
      em{
        font-size: 14px;
      }
    }
  }
}
.item-content:hover{
  border-color: #ff915e;
}

</style>