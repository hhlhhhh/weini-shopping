<template>
  <div class="collect">
    <div class="header">
      <a-input-search :style="{width:'360px'}" placeholder="输入商品名或商品描述进行搜索" search-button/>
    </div>
    <div class="content">
      <template v-for="item in goods" key="item.id">
        <ProfileGoodItem :goodItem="item"></ProfileGoodItem>
      </template>
    </div>
  </div>
</template>

<script setup>

import ProfileGoodItem from "@/view/profile/componets/ProfileGoodItem.vue";
import {reactive} from "vue";


const goods = reactive([
  {
    'id':1,
    'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
    'name': "眼镜",
    'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
    'price':24.9
  },
  {
    'id':1,
    'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
    'name': "眼镜",
    'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
    'price':24.9
  },
  {
    'id':1,
    'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
    'name': "眼镜",
    'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
    'price':24.9
  },
  {
    'id':1,
    'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
    'name': "眼镜",
    'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
    'price':24.9
  }
])

</script>

<style scoped lang="less">

  .collect{
    .header{
      margin-bottom: 20px;
    }
  }

</style>