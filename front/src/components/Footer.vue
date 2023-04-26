<template>
  <footer class="footer">
    <div class="contact">
      <div class="twoDimensionalCode">
        <img src="@/assets/images/footer/qq.jpg">
        <img src="@/assets/images/footer/weixin.jpg">
      </div>
      <a-divider :margin="5" direction="vertical"></a-divider>
      <div class="contact-number">
        <h3>联系我们</h3>
        <span>客服举报电话： 15228632335</span>
        <span>合作微信： weini-shopping</span>
        <span>加入开发团队（qq）： 548520199</span>
        <span>唯你购物网站合作开发团队</span>
      </div>
    </div>
    <div class="endnote">
      <p>Copyright 2023-2023 weini.com 版权所有 (好吧，其实没有版权，它就是一个工程实践项目)</p>
      <p>开发团队： 唯你购物网站合作开发团队</p>
      <p>
        <span class="record-img"></span>
        蜀公网安备 000000000000011号（蜀）网械平台备字[2018]第00003号蜀ICP备15014535号-43|营业执照（都是假的）
      </p>
    </div>
  </footer>
</template>

<script setup>

</script>

<style scoped lang="less">

.footer{
  display: flex;
  width: 100%;
  flex-wrap: wrap;
  flex-direction: column;
  align-items: center;
  .contact{
    display: flex;
    .twoDimensionalCode{
      display: inline-block;
      height: 200px;
      padding: 30px 20px 20px;
      img{
        width: 160px;
        &:last-child{
          margin-left: 10px;
        }
      }
    }
    .contact-number{
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: flex-start;
      margin-left: 20px;
      margin-top: 25px;
      h3{
        font-size: 24px;
        color: #4C4D4D;
        margin-bottom: 5px
      }
      span{
        color: #4d4d4d;
        font-size:16px;
        margin-top: 18px;
      }
    }
    .arco-divider-vertical{
      height: 240px!important;
    }
  }
  .endnote{
    text-align: center;
    margin: 30px 0 100px;
    p{
      height: 20px;
      line-height: 20px;
      font-size: 14px;
      color: #4d4d4d;
      margin-top: 4px;
    }
    .record-img{
      display: inline-block;
      background: url("https://cdn.pinduoduo.com/upload/home/img/common/pdd_sprite_v1.png") -57px 0;
      background-size: 197px 64px;
      height: 20px;
      line-height:20px;
      width: 20px;
    }
  }
}

</style>