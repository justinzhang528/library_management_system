<template>
  <div>
    用戶名：{{ admin.name }}<br>
    郵箱：{{ admin.email }}<br>
    電話：{{ admin.phone }}
  </div>
</template>

<script>
import Cookies from 'js-cookie'

export default {
  name: 'HomeView',
  data(){
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {}
    }
  },
}
</script>
