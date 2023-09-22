<template>
    <div style="width: 80%">
        <div style="margin:20px;">新增管理員</div>
        <el-form :inline="true" :model="form" :rules="inputRules" ref="addAdminForm" label-width="100px">
            <el-form-item label="用戶名" prop="name">
                <el-input v-model="form.name" placeholder="請輸入用戶名"></el-input>
            </el-form-item>
            <el-form-item label="密碼" prop="password">
                <el-input v-model="form.password" placeholder="請輸入密碼"></el-input>
            </el-form-item>
            <el-form-item label="郵箱" prop="email">
                <el-input v-model="form.email" placeholder="請輸入郵箱"></el-input>
            </el-form-item>
            <el-form-item label="電話" prop="phone">
                <el-input v-model="form.phone" type="number" min="0" placeholder="請輸入電話"></el-input>
            </el-form-item>
        </el-form>

        <div style="text-align: center; margin-top: 30px;">
            <el-button type="primary" @click="save" size="medium">提交</el-button>
        </div>
    </div>    
  </template>

<script>
import request from "@/utils/request"

export default {
  name: 'AddAdmin',
  data(){
    return {
      form:{
        name:'',
        password:'',
        email:'',
        phone:'',
      },
      inputRules:{
        name:[
            {required: true, message: "用戶名不能為空", trigger:"blur"},
            {min:4, max:15, message: "長度必須是4-15個字符之間", trigger:"blur"}
        ],
        password:[
            {required: true, message: "密碼不能為空", trigger:"blur"},
            {min:4, max:15, message: "長度必須是4-15個字符之間", trigger:"blur"}
        ],
        email:[
            {required: true, message: "郵箱不能為空", trigger:"blur"},
        ],
        phone:[
            {required: true, message: "電話不能為空", trigger:"blur"},
            {min:8, max:10, message: "長度必須是8-10個數字之間", trigger:"blur"}
        ],
      }
    }
  },
  methods:{
    save(){
      this.$refs["addAdminForm"].validate(valid => {
        if (valid) {            
            // 不用axios的方式進行請求：
            // fetch('http://localhost:9090/user/list',this.searchParams).then(res=>res.json()).then(res=>{
            //   this.tableData = res.data;
            // })
            // 使用axios進行封裝後的方式進行請求：
            request.post('/admin/addAdmin', this.form).then(res=>{
              if(res.code === '200'){
                  this.$notify.success('新增成功')
                  this.form = {name:'', password:'', email:'', phone:''}
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
  