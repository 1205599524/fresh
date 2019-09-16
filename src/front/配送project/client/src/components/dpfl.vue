<template>
 <div class="dpfl">
   <div class="head">
       <span>店铺：{{dianming}}</span>
       <span>日期：{{time}}</span>
   </div>
   <hr>
   <div>
       <table border="1">
           <tr>
               <th>菜品</th>
               <th>数量</th>
               <th>单价</th>
               <th>总价</th>
               <th>单位</th>
           </tr>
           <tr v-for="(item, i) in gesttest" :key="i">
               <td>{{item.commodityName}}</td>
               <td>{{item.amount}}</td>
               <td>{{item.price}}</td>
               <td>{{item.total}}</td>
               <td>{{item.unit}}</td>
           </tr>
       </table>
    <!-- <ul>
   <li class="boss">菜品</li>
   <li class="boss">数量</li>
   <li class="boss">单价</li>
   <li class="boss">总价</li>
   <li class="boss">单位</li>
   </ul>
   <ul v-for="(item, i) in gesttest" :key="i">
   <li class="small_size">{{item.commodityName}}</li>
   <li class="small_size">{{item.amount}}</li>
   <li class="small_size">{{item.price}}</li>
   <li class="small_size">{{item.total}}</li>
   <li class="small_size">{{item.unit}}</li>
   </ul> -->
   
   </div>
 </div>
</template>

<script>
import Vue from 'vue'
export default {
    data(){
        return {
            gesttest: JSON.parse(localStorage.getItem('onshop')),
            time:localStorage.day,
            dianming:localStorage.dianming
        }
    },
    methods:{
        getdata(){
            var gesttest=JSON.parse(localStorage.getItem('onshop'))
            console.log(gesttest);
        },
       created:function(){
            getdata();
        }
    }
  
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.head{
     display: flex;
    justify-content: space-between;
    font-family: "隶书";
    font-size: 20px;
    margin-bottom: 20px;
}

ul{
    display: flex;
    justify-content: space-between;
}
li{
    list-style: none;
    display: inline;
}
.boss{
    font-weight: 700;
}
.small_size{
    font-size: 14px;
}
table{
    
    text-align: center;
    width: 100%
}
tr th{
   padding-bottom: 10px;
   vertical-align:middle;
  
}
tr td{
    padding: 10px;
    font-size: 14px;
}
</style>