import http from "./request/request";

export const getShopMes = (data) => http.request({
    url: "/shop/detail",
    method: "GET",
    params:data
})

export const getSelledTop = (data) => http.request({
    url: "/shop/sell/top",
    method: "GET",
    params:data
})
