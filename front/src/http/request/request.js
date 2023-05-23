import axios from "axios";


// axios.defaults.withCredentials = false;
// // axios.defaults.headers.common['token'] =  AUTH_TOKEN
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
// axios.defaults.headers.put['Content-Type'] = 'application/json;charset=UTF-8';
// // 前端设置允许跨域
// axios.defaults.headers.post["Access-Control-Allow-Origin-Type"] = "*";

axios.defaults.headers.put['Authorization'] = "";

const http = axios.create({
    baseURL:process.env.BASE_URL,
    withCredentials: false, // 跨域请求时是否需要使用凭证
    timeout: 70000, // 请求超时时间
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
            return Promise.reject(response.msg);
    }
}
// 成功处理函数
function successHandle(response) {
    switch (response.status) {
        case 200:
            return Promise.resolve(response);
        default:
            return response;
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
        return successHandle(response);
    },
    (err) => {
        errorHandle(err).then(r => {
            console.log(r)
        });
    }
);

export default http;
