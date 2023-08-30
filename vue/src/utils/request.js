import router from '@/router';
import axios from 'axios'
import Cookies from 'js-cookie';

const request = axios.create({
    baseURL: 'http://localhost:9090/api',
    timeout: 5000
})

// request攔截器
// 可以自請求發送前對請求做一些處理
// 比如統一加token，對請求參數統一加密
request.interceptors.request.use(config=>{
    config.headers['Content-Type'] = 'application/json;charaset=utf-8';

    const adminJson = Cookies.get('admin')
    if(adminJson){
        config.headers['token'] = JSON.parse(adminJson).token
    }

    return config
},error=>{
    return Promise.reject(error);
});

// response
// 可以在接口相應後統一處理結果
request.interceptors.response.use(response=>{
    let res = response.data
    //兼容服務端返回的字符串數據
    if(typeof res === 'string'){
        res = res ? JSON.parse(res) : res
    }
    if(res.code === '401'){
        router.push('/login')
    }
    console.log(res)
    return res;
},error=>{
    console.log('err' + error);
    return Promise.reject(error);
});

export default request