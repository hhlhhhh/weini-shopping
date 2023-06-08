<template>
  <div class="address">
    <div class="header">
      收货地址
    </div>
      <div style="color: #ee6223;margin: 30px 0;">
        <span v-if="addressForm.update" style="cursor: pointer"  @click="addressForm.update=!addressForm.update">修改收货地址</span>
        <span v-else >新增收货地址</span>
      </div>
      <div class="form">
        <ul>
          <li>
            <span class="label">地址信息：</span>
            <a-cascader
                size="large"
                :options="pcaTextArr"
                v-model="selectedOptions"
                placeholder="请选择您省/市/区"
                @change="getAddress"
                path-mode
            >
            </a-cascader>
          </li>
          <li>
            <span class="label">详细地址：</span>
            <a-textarea :max-length="200" v-model="addressForm.detail" :auto-size="{minRows: 3,maxRows: 4}" placeholder="请输入详细地址信息，如道路、门牌号、小区、楼栋号、单元等信息" />
          </li>
          <li>
            <span class="label">收货人姓名：</span>
            <a-input placeholder="长度不超过25个字符" v-model="addressForm.nickname"></a-input>
          </li>
          <li>
            <span class="label">手机号码：</span>
            <a-input-number placeholder="请输入收货人的联系方式" v-model="addressForm.phone"></a-input-number>
          </li>
          <li>
            <label class="label" for="default-address">默认地址：</label>
            <Checkbox v-model="addressForm.state" :binary="true" id="default-address"></Checkbox>
          </li>
          <li>
            <span class="label"></span>
            <a-button type="primary" @click="saveEvent">保存</a-button>
          </li>
        </ul>
      </div>
      <div class="address-table">
        <a-table :columns="columns" :data="addressData" :pagination="false">
          <template #address="{record}">
            <span>
              {{record.province}}/{{record.city}}/{{record.town}}
            </span>
          </template>
          <template #operate="{ record }">
            <a-button @click="updateAddress($event,record)">修改</a-button>
            <a-button @click="deleteAddress($event,record)">删除</a-button>
          </template>
          <template #state="{ record }">
            <span v-if="record.state==='1'">默认地址</span>
            <a v-else class="set-default-address" @click="setDefaultAddress($event,record)">设为默认</a>
          </template>
        </a-table>
      </div>
    </div>
</template>

<script setup>
import { pcaTextArr } from 'element-china-area-data'
import {onMounted, reactive, ref} from "vue";
import {
  addAddressApi,
  deleteAddressApi,
  getAddressListApi,
  setDefaultAddressApi,
  updateAddressApi
} from "@/http/address";
import {useToast} from "primevue/usetoast";

const toast = useToast()

const selectedOptions = ref([])
const addressForm = reactive({
  id:"",
  update:false,
  province:"",
  city:"",
  town:"",
  detail:"",
  nickname:"",
  phone:"",
  state:false
})

const columns = [
  {
    title: '收货人',
    dataIndex: 'nickname'
  },
  {
    title: '省/市/区',
    slotName: 'address'
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
    title: '默认地址',
    slotName: 'state'
  }
]

const addressData = reactive([])

const getAddress = (selectValue)=>{
  console.log(selectValue)
}

function clearForm(){
  addressForm.id=""
  addressForm.state=false
  addressForm.province=""
  addressForm.city=""
  addressForm.town=""
  addressForm.detail=""
  addressForm.nickname=""
  addressForm.phone=""
}``

const addAddress = ()=>{
  let reg = /^[\s\S]{1,50}$/
  if(!reg.test(addressForm.detail)){
    toast.add({severity: 'error', summary: "详细地址不超过50个字！" , life: 1500})
    return
  }
  reg = /^[\s\S]{5,25}$/
  console.log(addressForm.nickname)
  if(!reg.test(addressForm.nickname)){
    toast.add({severity: 'error', summary: "用户名应为5-25个字符！" , life: 1500})
    return
  }
  reg = /^(13\d|14[579]|15[012356789]|166|17[0135678]|18\d|19[89])\d{8}$/
  if(!reg.test(addressForm.phone)){
    toast.add({severity: 'error', summary: "手机号格式错误！" , life: 1500})
    return
  }
  addressForm.province=selectedOptions.value[0]
  addressForm.city=selectedOptions.value[1]
  addressForm.town=selectedOptions.value[2]
  console.log(selectedOptions.value[0])
  if(addressForm.province===""||addressForm.city===""||addressForm.town===""){
    toast.add({severity: 'error', summary: "请选择省/市/区！" , life: 1500})
    return
  }
  if (addressForm.state){
    addressForm.state="1"
  }else{
    addressForm.state="0"
  }
  addAddressApi(addressForm).then(({data})=>{
    if(data.code===200){
      toast.add({severity: 'success', summary: data.data , life: 1500})
      getAddressList()
      clearForm()
    }
  })
}


const updateAddress = (event,record)=>{
  addressForm.update=true
  addressForm.nickname=record.nickname
  addressForm.phone=parseInt(record.phone)
  addressForm.state=record.state==="1"
  addressForm.detail=record.detail
  selectedOptions.value[0]=record.province
  selectedOptions.value[1]=record.city
  selectedOptions.value[2]=record.town
  addressForm.id=record.id
}


const saveEvent = ()=>{
  if (addressForm.update){
    addressForm.province=selectedOptions.value[0]
    addressForm.city=selectedOptions.value[1]
    addressForm.town=selectedOptions.value[2]
    addressForm.state=addressForm.state?"1":"0"

    updateAddressApi(addressForm).then(({data})=>{
      if(data.code===200){
        toast.add({severity: 'success', summary: data.data , life: 1500})
        getAddressList()
        clearForm()
      }
    })
  }else addAddress()
}

const deleteAddress = (event,record)=>{
  deleteAddressApi(record.id).then(({data})=>{
    if(data.code===200){
      toast.add({severity: 'success', summary: data.data , life: 1500})
      getAddressList()
    }
  })
}
const setDefaultAddress = (event,record)=>{
  setDefaultAddressApi({id:record.id}).then(({data})=>{
    if(data.code===200){
      toast.add({severity: 'success', summary: data.data , life: 1500})
      getAddressList()
    }
  })
}

const getAddressList = ()=>{
  getAddressListApi().then(({data})=>{
    if(data.code === 200){
      addressData.length=0
      data.data.forEach(e=>{
        addressData.push(e)
      })
    }
  })
}

onMounted(()=>{
  getAddressList()
})

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