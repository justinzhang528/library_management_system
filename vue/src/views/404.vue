<template>
    <div style="text-align: center;">
        <div style="margin-top: 150px; font-size: 100px;">404</div>
        <div style="margin-bottom: 30px; font-size: 50px;">當前頁面不存在</div>
        <el-button type="danger" round size="medium" @click="$router.push('/')">返回主頁</el-button>
    </div>
</template>