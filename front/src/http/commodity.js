import http from "./request/request";

export const getCommoditiesByPage = (data) => http({
    url: "/commodity/list",
    method: "GET",
    params:data
})

export const getCommodityTypeOptionApi = (data) => http({
    url: "/commodity/type/list",
    method: "GET",
    params:data
})