<template>
 <div class="dpfl">
   <div class="head">
       <span>菜品：{{caiming}}</span>
       <span>{{time}}</span>
   </div>
   <hr>
   <div>
       <table border="1">
           <tr>
               <th>店铺</th>
               <th>数量</th>
               <th>单价</th>
               <th>总价</th>
               <th>单位</th>
           </tr>
           <tr  v-for="(item, i) in gesttest" :key="i">
               <td>{{item.shopName}}</td>
               <td>{{item.amount}}</td>
               <td>{{item.price}}</td>
               <td>{{item.total}}</td>
               <td>{{item.unit}}</td>
           </tr>
       </table>
    <!-- <ul>
   <li class="boss">店铺</li>
   <li class="boss">数量</li>
   <li class="boss">单价</li>
   <li class="boss">总价</li>
   <li class="boss">单位</li>
   </ul>
   <ul v-for="(item, i) in gesttest" :key="i">
   <li>{{item.shopName}}</li>
   <li>{{item.amount}}</li>
   <li>{{item.price}}</li>
   <li>{{item.total}}</li>
   <li>{{item.unit}}</li>
   </ul> -->
   
   </div>
 </div>
</template>

<script>
export default {
   data(){
        return {
             gesttest: JSON.parse(localStorage.getItem('oncaiming')),
            // gesttest:[],
            time:localStorage.day,
            caiming:localStorage.caiming
        }
    },
    methods:{
        getdata(){
            var gesttest=JSON.parse(localStorage.getItem('oncaiming'))
        },
        created:function(){
            getdata()
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
    justify-content: space-around;
}
li{
    list-style: none;
    display: inline;
}
ul:nth-of-type(2) li{
    font-size: 14px;
}
.boss{
    font-weight: 700;
}
table{
    
    text-align: center;
    width: 100%
}
tr th{
   padding-bottom: 10px;
}
tr td{
    padding: 10px;
    font-size: 14px;
}
</style>