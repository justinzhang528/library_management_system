<template>
    <div style="width: 80%">
        <div style="margin:20px;">新增用戶</div>
        <el-form :inline="true" :model="form" label-width="100px">
            <el-form-item label="姓名">
                <el-input v-model="form.name" placeholder="請輸入姓名"></el-input>
            </el-form-item>
            <el-form-item label="年齡">
                <el-input v-model="form.age" placeholder="請輸入年齡"></el-input>
            </el-form-item>
            <el-form-item label="性別">
                <el-radio label="1" v-model="form.sex">男</el-radio>
                <el-radio label="0" v-model="form.sex">女</el-radio>
            </el-form-item>
            <el-form-item label="電話">
                <el-input v-model="form.phone" placeholder="請輸入電話號碼"></el-input>
            </el-form-item>
            <el-form-item label="地址">
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
    }
  },
  methods:{
    save(){
        console.log(this.form)
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
  }
}
</script>
  