import http from "./request/request";

export const loginByEmailApi = (data)=>http.request({
    url: "/user/login/email",
    method: "POST",
    data,
    
})

export const getEmailCodeApi = (data)=>http.request({
    url:"/user/email/code",
    method:"get",
    params:(data)
})