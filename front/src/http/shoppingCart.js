import http from "./request/request";

export const getShoppingCartList = (data) => http.request({
    url: "/shopping-cart/list",
    method: "GET",
    params:data
})

export const getCommodityTypeChoiceApi = (data) => http.request({
    url: "/shopping-cart/type-choice/list",
    method: "GET",
    params:data
})


export const addToShoppingCartApi = (data) => http.post("/shopping-cart/add",data,{
    headers: {
        'Content-Type': 'application/json',
    }
})


export const removeFromShoppingCartApi = (data) => http.request({
    url: "/shopping-cart/remove",
    method: "DELETE",
    params:data,
    headers: {
        'Content-Type': 'application/json',
    }
})


