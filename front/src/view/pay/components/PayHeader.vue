<template>
  <div class="pay-header">
    <header>
      <div class="logo">
        <img src="@/assets/images/logo.png">
      </div>
      <div class="process-line">
        <a-timeline direction="horizontal" >
          <a-timeline-item label="1.确认订单信息" order-done="1">
            <template #dot>
              <div class="dot">
                1
              </div>
            </template>
          </a-timeline-item>
          <a-timeline-item label="2.付款到微信">
            <template #dot>
              <div class="dot">
                2
              </div>
            </template>
          </a-timeline-item>
          <a-timeline-item label="3.确认收货">
            <template #dot>
              <div class="dot">
                3
              </div>
            </template>
          </a-timeline-item>
          <a-timeline-item label="4.评论">
            <template #dot>
              <div class="dot">
                4
              </div>
            </template>
          </a-timeline-item>
        </a-timeline>
      </div>
    </header>
  </div>
</template>

<script setup>

</script>

<style scoped lang="less">
.pay-header{
  >header{
    display: flex;
    position: relative;
    justify-content: space-between;
    width: 1000px;
    height: 100px;
    margin: 0 auto;
    .logo{
      >img{
        width: 100px;
      }
    }
    .process-line{
      position: absolute;
      right: 0;
      top: 50%;
      //transform: translate(50%,50%);
      width: 600px;
      :deep(.arco-timeline-item-dot-custom){
        background-color: rgba(0,0,0,0);
      }
      :deep(.arco-timeline-item[order-done="1"] .arco-timeline-item-dot-line){
        border-color: #FF5000;
      }
      .dot{
        width: 30px;
        height: 30px;
        line-height: 30px;
        color: #FFF;
        background-color: #d7ceca;
        border-radius: 15px;
      }
      .arco-timeline-item[order-done="1"] .dot{
        background-color: #FF5000;
      }
    }
  }
}
</style>