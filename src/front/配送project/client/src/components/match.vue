<template>
 <div class="bg ">
   <h4>请确认配送信息</h4>
   <el-button type="danger" class="cancel_btn"   @click="$router.push('/recode')">取消</el-button>
 </div>
</template>

<script>
export default {
  
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
div{
    border: 5px solid grey;
    width: 100%;
    height: 200px;
    position: absolute;
}
</style>