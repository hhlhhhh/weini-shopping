<template>
  <div class="collect">
    <div class="header">
      <a-input-search :style="{width:'360px'}" placeholder="输入商品名或商品描述进行搜索" search-button/>
    </div>
    <div class="content">
      <template v-for="(value,key) in goods" :key="key">
        <div class="date-show">
          {{value[0]}}
        </div>
        <template v-for="item in value[1]" :key="item.id">
          <FootPrintItem :goodItem="item"></FootPrintItem>
        </template>
      </template>
    </div>
  </div>
</template>

<script setup>
import FootPrintItem from "@/view/profile/componets/FootPrintItem.vue";
import {onMounted, reactive, ref} from "vue";


const goods = ref([])

function init(){
  let data = [
    {
      'id':1,
      'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
      'name': "眼镜",
      'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
      'price':24.9,
      'create-time': new Date('2023.1.6 20:20:12')
    },
    {
      'id':1,
      'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
      'name': "眼镜",
      'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
      'price':24.9,
      'create-time': new Date('2023.05.08 1:12:12')
    },
    {
      'id':1,
      'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
      'name': "眼镜",
      'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
      'price':24.5,
      'create-time': new Date('2023.1.6 11:12:02')
    },
    {
      'id':1,
      'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
      'name': "眼镜",
      'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
      'price':24.9,
      'create-time': new Date('2023-01-05 12:14:12')
    }
  ]

  let tmp = new Map();
  data.forEach(e=>{
    let time = e["create-time"].getFullYear()+"."+(e["create-time"].getMonth()+1)+"."+e["create-time"].getDate()
     if(tmp.has(time)) {
       tmp.get(time).push(e)
     }else{
       tmp.set(time,[e])
     }
  })
  goods.value=Array.from(tmp).sort((a,b)=>a[0]<b[0]?1:-1)

  let now = new Date()
  let nowTimeStr = now.getFullYear()+"."+(now.getMonth()+1)+"."+now.getDate()

  if(nowTimeStr===(goods.value[0][0])){
    goods.value[0][0] = "今天"
  }
}


onMounted(()=>{
  init()
})

</script>

<style scoped lang="less">

.collect{
  .header{
    margin-bottom: 20px;
  }
  .content{
    .date-show{
      color: #3c3c3c;
      font-size: 20px;
      font-family: 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
      font-weight: bold;
      margin-top: 30px;
    }
  }
}

</style>