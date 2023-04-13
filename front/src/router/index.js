import {createRouter,createWebHistory} from 'vue-router'
import {filterRoutes} from "./authoruzation";
import asyncRoutes from "./routes";

const routes=[

]

const router=createRouter({
    history: createWebHistory(),
    routes
})


// //动态添加路由，这样就不会刷新路由消失了
// let userMesStr=localStorage.getItem('userMes')
// if(userMesStr!==""&&userMesStr){
//     let permissions = JSON.parse(userMesStr).authority;
//
//     let accessRoutes = filterRoutes( asyncRoutes , permissions )
//
//     accessRoutes.forEach((route)=> {
//         router.addRoute(route);
//     })
// }

export  default router
