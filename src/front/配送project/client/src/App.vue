<template>
  <div id="app">
     
    <div class="topbar">
      <!-- <img src="../src/assets/images/font.png" alt="" width="30%" height="" style="margin-top:10px;"> -->
      生鲜配送
    </div>
    <router-view></router-view>
    <div class="bottombar">
      <van-tabbar v-model="active">
  <van-tabbar-item icon="notes-o" @click="$router.push('/recode')" >订单录入</van-tabbar-item>
  
  
  <van-tabbar-item icon="balance-list-o" @click="$router.push('/all')">订单汇总</van-tabbar-item>
   <van-tabbar-item icon="gold-coin-o" @click="$router.push('/allcount')" >总汇总</van-tabbar-item>
</van-tabbar>
       <!-- <nav class="mui-bar mui-bar-tab">
         <router-link class="mui-tab-item " to='/recode'>
				<span class="mui-icon mui-icon-extra mui-icon-extra-order"></span>
				<span class="mui-tab-label">订单录入</span>
			</router-link> -->
			<!-- <router-link class="mui-tab-item" to="/derict">
				<span class="mui-icon  mui-icon-search"></span>
				<span class="mui-tab-label">订单审核</span>
			</router-link> -->
			<!-- <router-link class="mui-tab-item" to="/all">
				<span class="mui-icon mui-icon-extra mui-icon-extra-prech"></span>
				<span class="mui-tab-label">订单汇总</span>
			</router-link> -->
			<!-- <router-link class="mui-tab-item" to="/change">
				<span class="mui-icon mui-icon-extra mui-icon-extra-topic"></span>
				<span class="mui-tab-label">菜品信息修改</span>
			</router-link> -->
		<!-- </nav> -->
    </div>
  </div>
  
</template>

<script>
export default {
 data() {
    return {
      active: 0
    }
  }
}
</script>

<style>

.topbar{
  text-align: center;
  margin: 0 auto;
  font-size: 30px;
  font-weight: normal;
  height:60px;
  background-color: #23A8F2;
  line-height: 60px;
  margin-bottom: 20px;
  font-family: 隶书;
  color: aliceblue;
  /* background: url("../src/assets/images/bg2.png") */
  
}
/* .bottombar{
  width: 100%;
  background-color: #F6F7F6;
  position: absolute;
  bottom: 0;
  touch-action: none; 
  
} */

</style>
