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
  -webkit-font-smoothing: antialiased
}
li{
  list-style: none;
}
a{
  text-decoration: none;
  color: #fff;
  cursor: pointer;
}
em{
  font-style: normal;
}
.content{
  min-width: 1200px;
}
h1{
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
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
