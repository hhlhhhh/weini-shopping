import http from "./request/request";

export const loginByEmail = (data)=>http.post("/user/login/email",data)