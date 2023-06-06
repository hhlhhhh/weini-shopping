import http from "./request/request";

export const loginByEmailApi = (data)=>http.request({
    url: "/account/login/email",
    method: "POST",
    data,
    
})

export const getEmailCodeApi = (data)=>http.request({
    url:"/account/email/code",
    method:"get",
    params:(data)
})

export const loginByPasswordApi = (data)=>http.request({
    url: "/account/login/phone",
    method: "POST",
    data,

})

export const getUserMesApi = ()=>http.request({
    url: "/user/detail",
    method: "GET"
})

export const updateUserMesApi = (data)=>http.put("/user/update",JSON.stringify(data),{
    headers: {
        'Content-Type': 'application/json',
    }
})