import axios from "axios";

const http = axios.create({
    baseURL:process.env.BASE_URL,
    withCredentials: true, // 跨域请求时是否需要使用凭证
    timeout: 10000, // 请求超时时间
})


// 错误处理函数
function errorHandle(response) {
    switch (response.status) {
        case 401:{
            return Promise.reject("权限不够！")
        }
        case 404:
            return Promise.reject("无法访问此页面！")
        default:
            throw new Error(response.msg);
    }
}
// 成功处理函数
function successHandle(response) {
    switch (response.status) {
        case 200:
            return response.data;
        default:
            return;
    }
}

// 请求拦截器
http.interceptors.request.use(
    (config) => {
        return config;
    },
    (error) => {
        // 错误抛到业务代码
        error.data = {};
        error.data.msg = "服务器异常";
        return Promise.resolve(error);
    }
);

http.interceptors.response.use(
    (response) => {
        successHandle(response);
    },
    (err) => {
        errorHandle(err);
    }
);

export default http;
