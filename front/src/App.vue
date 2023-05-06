<template>
  <div class="app-content">
    <Toast></Toast>
    <router-view></router-view>
  </div>
  <div class="bgc"></div>
</template>
<script setup>
import Toast from 'primevue/toast';
</script>
<style>
*{
  padding: 0;
  margin: 0;
  box-sizing: border-box;
  -webkit-font-smoothing: antialiased;
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
i{
  font-style: normal;
}
.app-content{
  display: block;
  min-width: 1200px;
}
.bgc{
  display: block;
  position: fixed;
  left: 0;
  top: 0;
  width:100%;
  height:100vh;
  background: linear-gradient(200deg, #f3e7e9, #325a96);
  z-index: -1;
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
