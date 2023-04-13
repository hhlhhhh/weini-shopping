<template>
  <div class="card flex justify-content-center">
    <Button label="Submit" />
    <Login></Login>
  </div>
</template>
<script setup>
import Button from "primevue/button";
import Login from "./components/Login.vue";

</script>
<style scoped>
*{
  padding: 0;
  margin: 0;
}
li{
  list-style: none;
}
a{
  color: #fff;
}

</style>
