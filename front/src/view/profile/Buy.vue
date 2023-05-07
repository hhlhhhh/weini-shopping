<template>
  <div class="buy">
    <a-tabs>
      <a-tab-pane key="1">
        <template #title>
          所有订单
        </template>
        <div class="item">
          <div class="all-item-search">
            <a-input-search :style="{width:'360px'}" placeholder="输入商品名或订单号进行搜索" search-button/>
          </div>
          <div>
            <div class="item-list-header">
              <ul>
                <li>商品</li>
                <li>单价</li>
                <li>数量</li>
                <li>金额</li>
                <li>操作</li>
              </ul>
            </div>
            <div class="item-list">
              <PurchasedItem>
                <template #operate>
                  <a-button type="primary">评价</a-button>
                  <a-button type="primary">再来一单</a-button>
                </template>
              </PurchasedItem>
            </div>
          </div>
        </div>
      </a-tab-pane>
      <a-tab-pane key="2">
        <template #title>
          待付款
        </template>
        <div class="item">
          <div>
            <div class="item-list-header">
              <ul>
                <li>商品</li>
                <li>单价</li>
                <li>数量</li>
                <li>金额</li>
                <li>操作</li>
              </ul>
            </div>
            <div class="item-list">
              <PurchasedItem>
                <template #operate>
                  <a-button type="primary">去付款</a-button>
                </template>
              </PurchasedItem>
            </div>
          </div>
        </div>
      </a-tab-pane>
      <a-tab-pane key="3">
        <template #title>
          待发货
        </template>
        <div class="item">
          <div>
            <div class="item-list-header">
              <ul>
                <li>商品</li>
                <li>单价</li>
                <li>数量</li>
                <li>金额</li>
                <li>操作</li>
              </ul>
            </div>
            <div class="item-list">
              <PurchasedItem>
                <template #operate>
                  <a-button type="primary">申请退款</a-button>
                </template>
              </PurchasedItem>
            </div>
          </div>
        </div>
      </a-tab-pane>
      <a-tab-pane key="4">
        <template #title>
          运输中
        </template>
        <div class="item">
          <div>
            <div class="item-list-header">
              <ul>
                <li>商品</li>
                <li>单价</li>
                <li>数量</li>
                <li>金额</li>
                <li>操作</li>
              </ul>
            </div>
            <div class="item-list">
              <PurchasedItem>
<!--                <template #operate>-->
<!--                  <a-button type="primary">去付款</a-button>-->
<!--                </template>-->
              </PurchasedItem>
            </div>
          </div>
        </div>
      </a-tab-pane>
      <a-tab-pane key="5">
        <template #title>
          待收货
        </template>
        <div class="item">
          <div>
            <div class="item-list-header">
              <ul>
                <li>商品</li>
                <li>单价</li>
                <li>数量</li>
                <li>金额</li>
                <li>操作</li>
              </ul>
            </div>
            <div class="item-list">
              <PurchasedItem>
                <template #operate>
                  <a-button type="primary">确认收货</a-button>
                </template>
              </PurchasedItem>
            </div>
          </div>
        </div>
      </a-tab-pane>
      <a-tab-pane key="6">
        <template #title>
          待评价
        </template>
        <div class="item">
          <div>
            <div class="item-list-header">
              <ul>
                <li>商品</li>
                <li>单价</li>
                <li>数量</li>
                <li>金额</li>
                <li>操作</li>
              </ul>
            </div>
            <div class="item-list">
              <PurchasedItem>
                <template #operate>
                  <a-button type="primary">去评价</a-button>
                  <a-button type="primary">再来一单</a-button>
                </template>
              </PurchasedItem>
            </div>
          </div>
        </div>
      </a-tab-pane>
    </a-tabs>
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
  .item{
    display: inline-block;
    width:1000px;
    //margin-top: 10px;
    .item-search{
      margin-bottom: 20px;
    }
    .item-list-header{
      margin: 10px 0;
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
    .item-list{
      margin-top: 20px;
      .arco-btn-primary{
        font-size: 14px;
        color: #333333;
        background-color: #f7f9fa;
        margin-top: 10px;
      }
    }
  }
}
</style>