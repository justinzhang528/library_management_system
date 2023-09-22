<template>
    <div>
        <!-- 頭部區域 -->
        <div style="height: 60px; line-height: 60px; background-color: white; margin-bottom: 2px; display: flex;">
            <div style="width: 300px;">
                <img src="@/assets/logo.png" alt="logo" style="width: 40px; position: relative; top: 10px; left: 20px;">
                <span style="margin-left: 25px; font-size:x-large;">圖書管理系統</span>
            </div>
            <div style="flex: 1; text-align: right; padding-right: 20px;">
                <el-dropdown trigger="click">
                    <span class="el-dropdown-link" style="cursor: pointer;">
                        管理員: {{ admin.name }}<i class="el-icon-arrow-down el-icon--right"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item><div @click="logout">登出</div></el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>

        <!-- 側邊欄和主體 -->
        <div style="display: flex;">
            <!-- 側邊欄導航 -->
            <div style="width: 200px; min-height: calc(100vh - 62px); overflow: hidden; margin-left: 2px; background-color: white;">
                <el-menu :default-active="$route.path" :default-openeds="['user','admin']" router class="el-menu-demo">
                <el-menu-item index="/home">
                    <i class="el-icon-s-home"></i>
                    首頁
                </el-menu-item>
                <!-- 會員管理 -->
                <el-submenu index="user">
                    <template slot="title">
                    <i class="el-icon-user-solid"></i>
                    會員管理
                    </template>
                    <el-menu-item index="/addUser">
                    <i class="el-icon-plus"></i>
                    會員添加
                    </el-menu-item>
                    <el-menu-item index="/userList">
                    <i class="el-icon-s-fold"></i>
                    會員列表
                    </el-menu-item>
                </el-submenu>
                <!-- 管理員管理 -->
                <el-submenu index="admin">
                    <template slot="title">
                    <i class="el-icon-s-custom"></i>
                    管理員管理
                    </template>
                    <el-menu-item index="/addAdmin">
                    <i class="el-icon-plus"></i>
                    管理員添加
                    </el-menu-item>
                    <el-menu-item index="/adminList">
                    <i class="el-icon-s-fold"></i>
                    管理員列表
                    </el-menu-item>
                </el-submenu>
                </el-menu>
            </div>

            <!-- 主體數據 -->
            <div style="flex: 1; background-color: white; margin-left: 2px; padding: 5px;">        
                <router-view/>
            </div>

        </div>
    </div>
</template>

<script>
import Cookies from 'js-cookie'

export default {
  data(){
    return {
        admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {}
    }
  },
  name: 'LayoutView',
  methods:{
    logout(){
        Cookies.remove('admin')
        this.$router.push('/login')
    }
  }
}
</script>