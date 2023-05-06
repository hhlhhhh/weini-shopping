import { createApp } from 'vue'
import App from './App.vue'
import PrimeVue from "primevue/config";
import store from "./store";
import router from "./router";

import '@arco-design/web-vue/dist/arco.css';

import "primevue/resources/themes/lara-light-indigo/theme.css";//theme
import "primevue/resources/primevue.min.css";//core
import "primeicons/primeicons.css";//icons

import Button from "primevue/button";
import InputNumber from "primevue/inputnumber";
import ToastService from "primevue/toastservice";

const app = createApp(App)

app.use(PrimeVue, { ripple: true });
app.use(store)
app.use(router)
app.use(ToastService)



// app.config.globalProperties.$toast=useToast()
/**
 * primeVue start
 */
app.component("Button",Button)
app.component("InputNumber",InputNumber)
/**
 * primeVue end
 */

app.mount('#app')
