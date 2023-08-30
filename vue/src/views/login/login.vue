<template>
    <div style="height: 100vh; overflow: hidden; background-image: url(https://www.reganagency.com/wp-content/uploads/2020/10/Library-Management.jpg)">
        <div style="width: 400px; height: 300px; background-color: white; opacity: 95%; border-radius: 10px; margin: 150px auto; padding: 50px">
            <div style="margin: 30px; text-align: center; font-size: 30px; font-weight: bold; color: dodgerblue">圖書管理系統</div>
            <el-form :model="form" :rules="inputRules" ref="loginForm">
                <el-form-item prop="username">
                    <el-input placeholder="請輸入用戶名" prefix-icon="el-icon-user" size="medium" v-model="form.username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input placeholder="請輸入密碼" show-password prefix-icon="el-icon-lock" size="medium" v-model="form.password"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button style="width: 100%;" size="medium" type="primary" @click="login">登錄</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import request from "@/utils/request"
import Cookies from 'js-cookie'

export default {
  name: 'LoginView',
  data(){
    return {
        form:{
            username:'',
            password:'',
        },
        inputRules:{
            username:[
                {required: true, message: "用戶名不能為空", trigger:"blur"},
                {min:4, max:15, message: "長度必須是4-15個字符之間", trigger:"blur"}
            ],
            password:[
                {required: true, message: "密碼不能為空", trigger:"blur"},
                {min:4, max:15, message: "長度必須是4-15個字符之間", trigger:"blur"}
            ]
        }
    }
  },
  methods:{
    login(){
        this.$refs["loginForm"].validate(valid => {
            if (valid) {
                // 使用axios進行封裝後的方式進行請求：
                request.post('/login', this.form).then(res=>{
                    if(res.code === '200'){
                        this.$notify.success('登錄成功')
                        if(res.data != null){
                            Cookies.set('admin',JSON.stringify(res.data))
                        }
                        this.$router.push('/')
                    }else{
                        this.$notify.error(res.msg);
                    }
                })
            } 
        });
    }
  }
}
</script>