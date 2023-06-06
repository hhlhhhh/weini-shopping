import http from "@/http/request/request";

export const getAddressListApi = ()=>http.get("/address/list")

export const addAddressApi = (data)=>http.post("/address/add",data,{
    headers:{
        'Content-Type': 'application/json'
    }
})

export const setDefaultAddressApi = (data)=>http.put("/address/update/default",JSON.stringify(data),{
    headers:{
        'Content-Type': 'application/json'
    }
})

export const updateAddressApi = (data)=>http.put("/address/update",JSON.stringify(data),{
    headers:{
        'Content-Type': 'application/json'
    }
})

export const deleteAddressApi = (id)=>http.delete("/address/del?id="+id)