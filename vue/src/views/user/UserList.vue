<template>
    <div>
      <!-- 搜索表單 -->
      <div style="margin-bottom: 10px;">
        <el-input style="width: 240px;" placeholder="請輸入名稱" v-model="searchParams.name"/>
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
        <el-table-column prop="name" label="名稱"></el-table-column>
        <el-table-column prop="age" label="年齡"></el-table-column>
        <el-table-column prop="address" label="地址"></el-table-column>
        <el-table-column prop="phone" label="電話"></el-table-column>
        <el-table-column prop="sex" label="性別"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">編輯</el-button>
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
      <el-dialog title="編輯會員" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
        <el-form :model="editForm">
          <el-form-item label="姓名">
              <el-input v-model="editForm.name" placeholder="請輸入姓名"></el-input>
          </el-form-item>
          <el-form-item label="年齡">
              <el-input v-model="editForm.age" placeholder="請輸入年齡"></el-input>
          </el-form-item>
          <el-form-item label="性別">
              <el-radio label="1" v-model="editForm.sex">男</el-radio>
              <el-radio label="0" v-model="editForm.sex">女</el-radio>
          </el-form-item>
          <el-form-item label="電話">
              <el-input v-model="editForm.phone" placeholder="請輸入電話號碼"></el-input>
          </el-form-item>
          <el-form-item label="地址">
              <el-input v-model="editForm.address" placeholder="請輸入地址"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="updateUser">確定</el-button>
        </span>
      </el-dialog>
  
    </div>

  </template>
  
  <script>
  import request from "@/utils/request"
  
  export default {
    name: 'UserList',
    data(){
      return {
        tableData: [],
        searchParams:{
          name:'',
          phone:''
        },
        currentPage: 1,
        pageSize: 10,
        dialogFormVisible: false,
        editForm: {
          id: '',
          name:'',
          age:'',
          address:'',
          phone:'',
          sex:'1',
        },
        editIndex: 0,
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
        request.get('/user/listByCond',{params: this.searchParams}).then(res=>{
          this.tableData = res.data
        })
      },
      search(){
        this.currentPage = 1
        this.load()
      },
      reset(){
        this.searchParams = {name: '', phone: ''}
        this.currentPage = 1
        this.load()
      },
      updateUser(){
        request.post('/user/updateUser', this.editForm).then(res=>{
            if(res.code === '200'){
                this.$notify.success('修改成功')
            }else{
                this.$notify.error(res.msg);
            }
        })
        this.pagedTableData[this.editIndex].name = this.editForm.name
        this.pagedTableData[this.editIndex].age = this.editForm.age
        this.pagedTableData[this.editIndex].address = this.editForm.address
        this.pagedTableData[this.editIndex].phone = this.editForm.phone
        this.pagedTableData[this.editIndex].sex = this.editForm.sex
        this.dialogFormVisible = false
      },
      handleEdit(index, row) {
        this.editForm.id = row.id
        this.editForm.name = row.name
        this.editForm.age = row.age
        this.editForm.address = row.address
        this.editForm.phone = row.phone
        this.editForm.sex = String(row.sex)
        this.dialogFormVisible = true
        this.editIndex = index;
      },
      handleDelete(index, row) {
        console.log(index, row)
        this.$confirm('將永久刪除此會員，確定要繼續嗎?', '警告', {
          confirmButtonText: '確定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          request.delete(`/user/deleteUser/${row.id}`,).then(res=>{
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
  