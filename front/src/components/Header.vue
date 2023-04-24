<template>
  <div class="header">
    <div/>
    <div>
      <a-image
          width="70"
          src="src/assets/images/logo.png"
          :preview="false"
      />
    </div>
    <div>
      首页
    </div>
    <div>分类</div>
    <div>购物车</div>
    <div>
      <a-input-search :style="{width:'25vw'}" placeholder="请输入关键字"/>
    </div>
    <div>
      <a-button type="text" style="color: rgb(99, 126, 171);font-size: 16px">登录 / 注册</a-button>
    </div>
    <div/>
  </div>
</template>

<script setup>

</script>

<style scoped lang="less">
.header{
  display: flex;
  justify-items: center;
  align-items: center;
  font-size: 18px;
  font-weight: bolder;
  color: #413a3a;
  //margin-top: 20px;
  >div:first-child{
    flex:3
  }
  >div:nth-child(2){
    flex:2
  }
  >div:nth-child(3){
    flex:1.5;
    cursor: pointer;
  }
  >div:nth-child(3){
    flex:1.5;
    cursor: pointer;
  }
  >div:nth-child(4){
    flex: 1.5;
    cursor: pointer;
  }
  >div:nth-child(5){
    flex:2;
    cursor: pointer;
  }
  >div:nth-child(6){
    flex:6
  }
  >div:nth-child(7){
    flex:2;
    cursor: pointer;
  }
  >div:nth-child(8){
    flex:2
  }
}
</style>