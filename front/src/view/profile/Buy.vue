<template>
  <div class="buy">
    <a-tabs>
      <a-tab-pane key="1">
        <template #title>
          所有订单
        </template>
        <div class="all-item">
          <div class="all-item-search">
            <a-input-search :style="{width:'360px'}" placeholder="输入商品名或订单号进行搜索" search-button/>
          </div>
          <div>
            <div class="all-item-list-header">
              <ul>
                <li>商品</li>
                <li>单价</li>
                <li>数量</li>
                <li>金额</li>
                <li>操作</li>
              </ul>
            </div>
            <div class="all-item-list">
              <PurchasedItem></PurchasedItem>
              <PurchasedItem></PurchasedItem>
            </div>
          </div>
        </div>
      </a-tab-pane>
      <a-tab-pane key="2">
        <template #title>
          待付款
        </template>
        Content of Tab Panel 2
      </a-tab-pane>
      <a-tab-pane key="3">
        <template #title>
          待发货
        </template>
        Content of Tab Panel 3
      </a-tab-pane>
      <a-tab-pane key="4">
        <template #title>
          待收货
        </template>
        Content of Tab Panel 3
      </a-tab-pane>
      <a-tab-pane key="5">
        <template #title>
          待评价
        </template>
        Content of Tab Panel 3
      </a-tab-pane>
      <a-tab-pane key="6">
        <template #title>
          待评价
        </template>
        Content of Tab Panel 3
      </a-tab-pane>
    </a-tabs>
    <div class="header">

    </div>
    <div class="content">

    </div>
  </div>
</template>

<script setup>

import PurchasedItem from "@/view/profile/componets/PurchasedItem.vue";
</script>

<style scoped lang="less">
.buy{
  :deep(.arco-tabs-tab-active){
    color: #c76538;
  }
  :deep(.arco-tabs-nav-ink){
    background-color: #c76538;
  }
  .all-item{
    display: inline-block;
    width:1000px;
    //margin-top: 10px;
    .all-item-search{
      margin-bottom: 20px;
    }
    .all-item-list-header{
      margin-bottom: 10px;
      >ul{
        display: flex;
        background-color: #d7d7e3;
        >li{
          flex: 1;
          text-align: center;
          font-family: tahoma,arial,Hiragino Sans GB,\\5B8B\4F53,sans-serif;
          padding: 10px 20px;
        }
        >li:first-child{
          flex: 3;
        }
      }
    }
    .all-item-list{
      margin-top: 20px;
    }
  }
}
</style>