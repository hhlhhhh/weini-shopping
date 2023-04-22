<template>
  <div class="content">
    <Toast></Toast>
    <router-view></router-view>
  </div>
</template>
<script setup>
import Toast from 'primevue/toast';
</script>
<style>
*{
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
li{
  list-style: none;
}
a{
  text-decoration: none;
  color: #fff;
}
.content{
  min-width: 1200px;
}
.p-toast {
  font-size: 16px;
  width: 300px;
  height: 40px;
}
.p-toast-message-success .p-toast-message-icon::before {
  font-size: 20px;
}
</style>
