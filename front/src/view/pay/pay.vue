<template>
  <div class="pay">
    <PayHeader/>
    <div class="content">
      <img src="@/assets/images/pay/weixin.jpg">
      <div>
        <a-button>我已支付</a-button>
      </div>
    </div>
  </div>
</template>

<script setup>

import PayHeader from "@/view/pay/components/PayHeader.vue";
</script>

<style scoped lang="less">
.pay{
  .content{
    margin-top: 100px;
    text-align: center;
    >img{
      width: 300px;
    }
    >div>button{
      margin-top: 50px;
    }
  }
}
</style>