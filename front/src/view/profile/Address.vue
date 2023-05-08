<template>
  <div class="address">
    <div class="header">
      收货地址
    </div>
      <div style="color: #ee6223;margin: 30px 0;">新增收货地址</div>
      <div class="form">
        <ul>
          <li>
            <span class="label">地址信息：</span>
            <a-cascader
                size="large"
                :options="pcaTextArr"
                v-model="selectedOptions"
                placeholder="请选择您省/市/区"
            >
            </a-cascader>
          </li>
          <li>
            <span class="label">详细地址：</span>
            <a-textarea :max-length="200" v-model="addressForm.detail" :auto-size="{minRows: 3,maxRows: 4}" placeholder="请输入详细地址信息，如道路、门牌号、小区、楼栋号、单元等信息" />
          </li>
          <li>
            <span class="label">收货人姓名：</span>
            <a-input placeholder="长度不超过25个字符"></a-input>
          </li>
          <li>
            <span class="label">手机号码：</span>
            <a-input-number placeholder="请输入收货人的联系方式"></a-input-number>
          </li>
          <li>
            <label class="label" for="default-address">默认地址：</label>
            <Checkbox v-model="addressForm.default" :binary="true" id="default-address"></Checkbox>
          </li>
          <li>
            <span class="label"></span>
            <a-button type="primary">保存</a-button>
          </li>
        </ul>
      </div>
      <div class="address-table">
        <a-table :columns="columns" :data="data" :pagination="false">
          <template #operate="{ record }">
            <a-button @click="updateAddress($event,record)">修改</a-button>
            <a-button @click="deleteAddress($event,record)">删除</a-button>
          </template>
          <template #defaultAddress="{ record }">
            <a class="set-default-address" @click="setDefaultAddress($event,record)">设为默认</a>
          </template>
        </a-table>
      </div>
    </div>
</template>

<script setup>
import { pcaTextArr } from 'element-china-area-data'
import {reactive,ref} from "vue";

const selectedOptions = ref("")
const addressForm = reactive({})

const columns = [
  {
    title: '收货人',
    dataIndex: 'name'
  },
  {
    title: '所在地区',
    dataIndex: 'address'
  },
  {
    title: '详细地址',
    dataIndex: 'detail'
  },
  {
    title: '手机/电话',
    dataIndex: 'phone'
  },
  {
    title: '操作',
    slotName: 'operate'
  },
  {
    title: 'xs',
    slotName: 'defaultAddress'
  }
]

const data = reactive([
  {
    key: '1',
    name: 'Jane Doe',
    address: '32 Park Road, London',
    detail: '西南航空港经济开发区学府路一段24号',
    phone: '13345863254'
  },
  {
    key: '1',
    name: 'Jane Doe',
    address: '32 Park Road, London',
    detail: '西南航空港经济开发区学府路一段24号',
    phone: '13345863254'
  },
  {
    key: '1',
    name: 'Jane Doe',
    address: '32 Park Road, London',
    detail: '西南航空港经济开发区学府路一段24号',
    phone: '13345863254'
  },
  {
    key: '1',
    name: 'Jane Doe',
    address: '32 Park Road, London',
    detail: '西南航空港经济开发区学府路一段24号',
    phone: '13345863254'
  },
  {
    key: '1',
    name: 'Jane Doe',
    address: '32 Park Road, London',
    detail: '西南航空港经济开发区学府路一段24号',
    phone: '13345863254'
  },
  {
    key: '1',
    name: 'Jane Doe',
    address: '32 Park Road, London',
    detail: '西南航空港经济开发区学府路一段24号',
    phone: '13345863254'
  },
  {
    key: '1',
    name: 'Jane Doe',
    address: '32 Park Road, London',
    detail: '西南航空港经济开发区学府路一段24号',
    phone: '13345863254'
  },
  {
    key: '1',
    name: 'Jane Doe',
    address: '32 Park Road, London',
    detail: '西南航空港经济开发区学府路一段24号',
  }
])

const updateAddress = (event,record)=>{

}
const deleteAddress = (event,record)=>{

}
const setDefaultAddress = (event,record)=>{

}

</script>
<style scoped lang="less">
.address{
  .header{
    color: #014d7f;
    background-color: #f3f8fe;
    padding: 5px;
  }
  .form>ul{
    >li{
      display: flex;
      width: 500px;
      margin: 20px 0;
      .label{
        display: inline-block;
        text-align: right;
        width: 100px;
        min-width: 100px;
        max-width: 100px;
        color: #333333;
        padding-right: 5px;
      }
      :deep(.arco-input-suffix){
        display: none!important;
      }
      :deep(.arco-btn-primary){
        background-color: #1165e5;
        &:hover{
          background-color: rgb(var(--primary-5));
        }
      }
    }
  }
  .address-table{
    margin-top: 40px;
    margin-bottom: 50px;
    .set-default-address{
      color: #666666;
      font-size: 16px;
      &:hover{
        color: #ff915e;
      }
    }
  }
}
</style>