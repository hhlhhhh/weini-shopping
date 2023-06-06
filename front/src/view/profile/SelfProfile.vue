<template>
  <div class="self-profile">
    <a-space direction="vertical" size="large" :style="{width: '600px'}">
      <a-form :model="userMes" layout="horizontal">
        <a-form-item field="name" label="头像">
          <a-avatar :size="100" shape="square">
            <img :src="userMes.avatarUrl"  width="100">
          </a-avatar>
        </a-form-item>
        <a-form-item field="name" label="昵称">
          <a-input v-model="userMes.nickname" placeholder="你的昵称" />
          <p style="margin-left: 40px;color: #4e5969">注：一周只能修改一次</p>
        </a-form-item>
        <a-form-item field="post" label="唯你号">
          <a-input v-model="userMes.id" disabled placeholder="唯你号" />
        </a-form-item>
        <a-form-item field="sex" label="性别">
          <a-radio-group v-model="userMes.sex">
            <a-radio value="男">男</a-radio>
            <a-radio value="女">女</a-radio>
            <a-radio value="保密">保密</a-radio>
          </a-radio-group>
        </a-form-item>
        <a-form-item field="phone" label="电话号码">
          <a-input v-model="userMes.phone" placeholder="你的电话号码" disabled/>
        </a-form-item>
        <a-form-item field="email" label="邮箱">
          <a-input v-model="userMes.email" placeholder="你的邮箱" disabled/>
        </a-form-item>
        <a-form-item field="signature" label="签名">
          <a-textarea max-length="200" v-model="userMes.signature" :auto-size="{minRows: 4,maxRows: 6}" placeholder="设置您的签名- ( ゜- ゜)つロ" />
        </a-form-item>
        <a-form-item>
          <a-button @click="updateUserMes">保存</a-button>
        </a-form-item>
      </a-form>
    </a-space>
  </div>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {getUserMesApi, updateUserMesApi} from "@/http/user";
import {useToast} from "primevue/usetoast";

const toast = useToast()

const form = reactive({
  id: "123",
  nickname: 'hehaolin',
  signature: '',
  sex: 0,
  avatar: 'http://localhost:8090/image/logo.png'
})

let userMes = ref({})

const getSelfMes = ()=>{
  let userMesStr = localStorage.getItem("userMes")
  if(userMesStr){
    userMes.value = JSON.parse(userMesStr)
    userMes.value.avatarUrl = process.env.BASE_MEDIA+userMes.value.avatar
    console.log(userMes)
  }
}


const updateUserMes = ()=>{
  updateUserMesApi({...userMes.value}).then(res => {
    if(res.data.code===200){
      toast.add({severity: 'success', summary: res.data.data , life: 1500})
      getUserMesApi().then(({data})=>{
        if(data.code===200){
          userMes.value=data.data
          localStorage.setItem("userMes",JSON.stringify(data.data))
          getSelfMes()
        }
      })
    }
  })
}

onMounted(()=>{
  getSelfMes()
})

</script>

<style scoped lang="less">
.self-profile{
  display: inline-block;
  width:1200px;
  .arco-input-wrapper{
    width: 220px;
  }
  .arco-textarea-wrapper .arco-textarea {
    resize: none!important;
  }
}
</style>