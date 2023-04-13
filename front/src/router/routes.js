export default [
    {
        path:"/login",
        name:"Login",
        meta:{
            permission:["user"]
        },
        component:()=>import("@/components/Login.vue")
    }
]