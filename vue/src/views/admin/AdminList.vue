<template>
    <div>
      <!-- 搜索表單 -->
      <div style="margin-bottom: 10px;">
        <el-input style="width: 240px;" placeholder="請輸入用戶名" v-model="searchParams.name"/>
        <el-input style="width: 240px; margin-left: 5px;" placeholder="請輸入郵件" v-model="searchParams.email"/>        
        <el-input style="width: 240px; margin-left: 5px;" placeholder="請輸入電話" v-model="searchParams.phone"/>
        <el-button type="primary" style="margin-left: 5px;" @click="search">
          <i class="el-icon-search"></i>
          搜索
        </el-button>
        <el-button type="danger" style="margin-left: 5px;" @click="reset">
          <i class="el-icon-refresh-left"></i>
          清空
        </el-button>
      </div>
  
      <!-- 表格數據 -->
      <el-table :data="pagedTableData" stripe>
        <el-table-column prop="id" label="id" v-if="false"></el-table-column>
        <el-table-column prop="name" label="用戶名"></el-table-column>
        <el-table-column prop="email" label="郵箱"></el-table-column>
        <el-table-column prop="phone" label="電話"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">更改密碼</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">刪除</el-button>
          </template>
      </el-table-column>
      </el-table>
  
      <!-- 分頁 -->
      <div style="margin-top: 20px;">
        <el-pagination
          background
          layout="prev, pager, next"
          :total="tableData.length"
          :current-page="currentPage"
          @current-change="setPage">
        </el-pagination>
      </div>

      

      <!-- 編輯用戶窗口 -->
      <el-dialog title="修改密碼" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
        <el-form :model="editForm" :rules="inputRules" ref="editPasswordForm">
          <el-form-item label="舊密碼" prop="oldPassword">
              <el-input v-model="editForm.oldPassword" placeholder="請輸入舊密碼"></el-input>
          </el-form-item>
          <el-form-item label="新密碼" prop="newPassword">
              <el-input v-model="editForm.newPassword" placeholder="請輸入新密碼"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="updateAdminPassword">確定</el-button>
        </span>
      </el-dialog>
  
    </div>

  </template>
  
  <script>
  import request from "@/utils/request"
  
  export default {
    name: 'AdminList',
    data(){
      return {
        tableData: [],
        searchParams:{
          name:'',
          email:'',
          phone:''
        },
        currentPage: 1,
        pageSize: 10,
        dialogFormVisible: false,
        editForm: {
          id: '',
          oldPassword:'',
          newPassword:''
        },
        editIndex: 0,
        inputRules:{
          oldPassword:[
              {required: true, message: "密碼不能為空", trigger:"blur"},
              {min:4, max:15, message: "長度必須是4-15個字符之間", trigger:"blur"}
          ],
          newPassword:[
              {required: true, message: "密碼不能為空", trigger:"blur"},
              {min:4, max:15, message: "長度必須是4-15個字符之間", trigger:"blur"}
          ],
        }
      }
    },
    created(){
      this.load()
    },
    computed: {
      pagedTableData() {
        return this.tableData.slice(this.pageSize * (this.currentPage - 1), this.pageSize * this.currentPage)
      }
    },
    methods:{
      load(){
        // 不用axios的方式進行請求：
        // fetch('http://localhost:9090/user/list',this.searchParams).then(res=>res.json()).then(res=>{
        //   this.tableData = res.data;
        // })
  
        // 使用axios進行封裝後的方式進行請求：
        request.get('/admin/listByCond',{params: this.searchParams}).then(res=>{
          this.tableData = res.data
        })
      },
      search(){
        this.currentPage = 1
        this.load()
      },
      reset(){
        this.searchParams = {name: '', email:'', phone: ''}
        this.currentPage = 1
        this.load()
      },
      updateAdminPassword(){
        this.$refs["editPasswordForm"].validate(valid => {
          if(valid){            
            request.post('/admin/updateAdminPassword', this.editForm).then(res=>{
                if(res.code === '200'){
                    this.$notify.success('修改成功')                
                    this.dialogFormVisible = false
                }else{
                    this.$notify.error(res.msg);
                }
            })
          } 
        });
      },
      handleEdit(index, row) {
        this.editForm.id = row.id
        this.editForm.oldPassword = ""
        this.editForm.newPassword = ""
        this.dialogFormVisible = true
        this.editIndex = index;
      },
      handleDelete(index, row) {
        console.log(index, row)
        this.$confirm('將永久刪除此管理員，確定要繼續嗎?', '警告', {
          confirmButtonText: '確定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          request.delete(`/admin/deleteAdmin/${row.id}`,).then(res=>{
            console.log(res.code)
            if(res.code === '200'){
              this.$delete(this.pagedTableData, index);
              this.$message({
                type: 'success',
                message: '刪除成功'
              });
            }else{
              this.$message({
                type: 'info',
                message: res.msg
              });
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消刪除'
          });          
        });
      },
      setPage(val) {
        this.currentPage = val
      }
    }
  }
  </script>
  