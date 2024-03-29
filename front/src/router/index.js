import {createRouter,createWebHistory} from 'vue-router'

const routes=[
    {
        path:"/",
        name:"root",
        redirect:{
            name:"Home"
        }
    },
    {
        path:"/login",
        name:"Login",
        meta:{
            permission:["user"]
        },
        component:()=>import("@/view/Login.vue")
    },
    {
        path:"/register",
        name:"Register",
        meta:{
            permission:["user"]
        },
        component:()=>import("@/view/register.vue")
    },
    {
        path:"/home",
        name:"Home",
        meta:{
            permission:["user"]
        },
        component:()=>import("@/view/Home.vue")
    },
    {
        path:"/shopping_cart",
        name:"ShoppingCart",
        meta:{
            permission:["user"]
        },
        component:()=>import("@/view/ShoppingCart.vue")
    },
    {
        path:"/profile",
        name:"Profile",
        meta:{
            permission:["user"]
        },
        redirect: "/profile/detail",
        component:()=>import("@/view/Profile.vue"),
        children:[
            {
                path: "detail",
                name: "Detail",
                component:()=>import("@/view/profile/SelfProfile.vue")
            },
            {
                path: "shopping-cart",
                name: "ProfileShoppingCart",
                component:()=>import("@/view/profile/ShoppingCart.vue")
            },
            {
                path: "buy",
                name: "Buy",
                component:()=>import("@/view/profile/Buy.vue")
            },
            {
                path: "collect",
                name: "Collect",
                component:()=>import("@/view/profile/Collect.vue")
            },
            {
                path: "footprint",
                name: "Footprint",
                component:()=>import("@/view/profile/Footprint.vue")
            },
            {
                path: "address",
                name: "Address",
                component:()=>import("@/view/profile/Address.vue")
            }
        ]
    },
    {
        path:"/good-detail",
        name:"GoodDetail",
        meta:{
            permission:["user"]
        },
        component:()=>import("@/view/goods/GoodsDeTail.vue")
    },
    {
        path:"/user",
        name:"UserDetail",
        meta:{
            permission:["user"]
        },
        component:()=>import("@/view/profile/UserDetail.vue")
    },
    {
        path:"/order",
        name:"Order",
        meta:{
            permission:["user"]
        },
        component:()=>import("@/view/pay/order.vue"),
    },
    {
        path:"/pay",
        name:"Pay",
        meta:{
            permission:["user"]
        },
        component:()=>import("@/view/pay/pay.vue")
    }
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
