<template>
  <div id="app">
    <!-- 頭部區域 -->
    <div style="height: 60px; line-height: 60px; background-color: white; margin-bottom: 2px;">
      <img src="@/assets/logo.png" alt="logo" style="width: 40px; position: relative; top: 10px; left: 20px;">
      <span style="margin-left: 25px; font-size:x-large;">圖書管理系統</span>
    </div>

    <!-- 側邊欄和主體 -->
    <div style="display: flex;">
      <!-- 側邊欄導航 -->
      <div style="width: 200px; min-height: calc(100vh - 62px); overflow: hidden; margin-left: 2px; background-color: white;">
        <el-menu :default-active="$route.path" :default-openeds="['1']" router class="el-menu-demo">
          <el-menu-item index="/">
            <i class="el-icon-s-home"></i>
            首頁
          </el-menu-item>
          <el-submenu index="1">
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
        </el-menu>
      </div>

      <!-- 主體數據 -->
      <div style="flex: 1; background-color: white; margin-left: 2px; padding: 5px;">        
        <router-view/>
      </div>

    </div>
  </div>
</template>