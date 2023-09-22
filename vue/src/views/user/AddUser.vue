<template>
    <div style="width: 80%">
        <div style="margin:20px;">新增用戶</div>
        <el-form :inline="true" :model="form" :rules="inputRules" ref="addUserForm" label-width="100px">
            <el-form-item label="姓名" prop="name">
                <el-input v-model="form.name" placeholder="請輸入姓名"></el-input>
            </el-form-item>
            <el-form-item label="年齡" prop="age">
                <el-input v-model="form.age" type="number" min="0" placeholder="請輸入年齡"></el-input>
            </el-form-item>
            <el-form-item label="性別" prop="sex">
                <el-radio label="1" v-model="form.sex">男</el-radio>
                <el-radio label="0" v-model="form.sex">女</el-radio>
            </el-form-item>
            <el-form-item label="電話" prop="phone">
                <el-input v-model="form.phone" type="number" min="0" placeholder="請輸入電話號碼"></el-input>
            </el-form-item>
            <el-form-item label="地址" prop="address">
                <el-input v-model="form.address" placeholder="請輸入地址"></el-input>
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
  name: 'AddUser',
  data(){
    return {
      form:{
        name:'',
        age:'',
        address:'',
        phone:'',
        sex:'1',
      },
      inputRules:{
        name:[
            {required: true, message: "姓名不能為空", trigger:"blur"},
        ],
        age:[
            {required: true, message: "年齡不能為空", trigger:"blur"},
        ],
        phone:[
            {required: true, message: "電話不能為空", trigger:"blur"},
            {min:8, max:10, message: "長度必須是8-10個數字之間", trigger:"blur"}
        ],
        address:[
            {required: true, message: "地址不能為空", trigger:"blur"},
        ],
      }
    }
  },
  methods:{
    save(){
        this.$refs["addUserForm"].validate(valid => {
            if (valid) {            
                // 不用axios的方式進行請求：
                // fetch('http://localhost:9090/user/list',this.searchParams).then(res=>res.json()).then(res=>{
                //   this.tableData = res.data;
                // })
                // 使用axios進行封裝後的方式進行請求：
                request.post('/user/addUser', this.form).then(res=>{
                    if(res.code === '200'){
                        this.$notify.success('新增成功')
                        this.form = {name:'', age:'', address:'', phone:'', sex:'1'}
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
  