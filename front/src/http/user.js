import http from "./request/request";

export const loginByEmailApi = (data)=>http.post("/user/login?type=email",data)

export const getEmailCodeApi = (data)=>http.request({
    url:"/user/email/code",
    method:"get",
    params:(data)
})