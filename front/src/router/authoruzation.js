
//认证权限的函数
function hasPermission( route, permissions ){

    if( route.meta && route.meta.permission ){
        let flag=false
        route.meta.permission.forEach(e=>{
            if(permissions.includes(e))
                flag = true;
            if( route.children && route.children.length ){
                route.children = filterRoutes( route.children, permissions )
            }
        })
        return flag
    }else{
        return true;
    }
}


export function filterRoutes( routes, permissions ){
    let accessRoutes = [];

    accessRoutes = routes.filter((route)=>{
        if( hasPermission( route, permissions ) ){
            return true;
        }else{
            return false;
        }
    })

    return accessRoutes;
}