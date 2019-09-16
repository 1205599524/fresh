<template>
 <div class="fenlei">
     <div class="van">
  <van-button type="round" size="large" class="btn" @click="$router.push('/change')">按菜品分类</van-button>
  <van-button type="round" size="large" class="btn" @click="$router.push('/derict')">按店铺分类</van-button>
</div>
 </div>
</template>

<script>
import Vue from 'vue'
import { Toast } from 'vant';

Vue.use(Toast);
export default {
  created:function(){
      Toast({
           message:'请选择分类',
           position:"bottom",

        });
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.fenlei{
    display: flex;
    flex-direction:column;
    justify-content: space-between;
    margin: 0 auto}
.van{

    margin: 50px auto;
}
.btn{
    margin-top: 70px;
    width: 100%;
    
    
}
</style>
