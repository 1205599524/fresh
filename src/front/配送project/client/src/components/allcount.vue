<template>
<div>
 <div class="allcount">
     <h3>汇总</h3>
    
     <ul v-for="(i,j) in item" :key="j">
         
         
         <li><i class="left">{{i.name}}</i><i class="right">{{i.total}}</i></li> <hr>
     </ul>
 </div>
 <li><i class="left">总金额：</i><i class="right">{{totals}}元</i></li>
 </div>
</template>

<script>
import Vue from 'vue'
import { Toast } from 'vant';
export default {
  data(){
      return{
          item:[],
          totals:''
      }
  },
  methods:{
      getdata(){
          this.$axios.get("/api/billShop/total").then(res=>{
              
              console.log(res.data.items)
              this.item=res.data.items
              console.log(this.item)
              this.totals=res.data.total
          })
          .catch(err=>{
               Toast({
           message:'服务器忙',
           position:"bottom",

        });
          })
      }
  },
  created:function(){
      this.getdata()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

li{
    list-style: none;
    display: flex;
    justify-content: space-between;
}
.allcount{
    width: 95%;
    text-align: center;
    overflow: auto;
    height: 500px;
    margin: 15px auto;
    /* background: url('../assets/images/paper.png'); */
    background-size: cover;
    
    
}
i{
    font-style: normal;
    font-family:"隶书";
    font-size: 16px;
    /* position: absolute; */
}
h3{
    font-family:"隶书";
    margin-bottom: 15px;
}


</style>