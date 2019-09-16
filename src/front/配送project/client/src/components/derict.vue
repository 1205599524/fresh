<template>
 <div class="derict">
    <span>店铺分类:</span>
     <span class="time">{{time}}</span>
   <van-tree-select
   height="350"
  :items="items"
   @click-nav='cp'
   @click-item='dp'
  :active-id.sync="activeIds"
  :main-active-index.sync="activeIndex"
/>
 <van-button  type="round" size="large" class="btn" @click="sendandjump">确定</van-button>
 </div>
 
</template>

<script>
import Vue from 'vue'
import { Toast } from 'vant';
export default {
   data() {
    return {
      // shopId:'',
       time:localStorage.day,
       getallvegname:[],
       getallshopname:[],
       shopList : [],
       commodityList : [],
       shopid:'',
       senddata:{},
       dps:[],
      items:[
      {  text: '我家菜馆',  children: [{ text: '番茄', id: 1, }, {  text: '西红柿', id: 2 },{ text: '鸡蛋',id: 3}]},
      {  text: '我家菜馆',  children: [{ text: '番茄', id: 1, }, {  text: '西红柿', id: 2 },{ text: '鸡蛋',id: 3}]},
      {  text: '我家菜馆',  children: [{ text: '番茄', id: 1, }, {  text: '西红柿', id: 2 },{ text: '鸡蛋',id: 3}]},
      {  text: '我家菜馆',  children: [{ text: '番茄', id: 1, }, {  text: '西红柿', id: 2 },{ text: '鸡蛋',id: 3}]},
      {  text: '我家菜馆',  children: [{ text: '番茄', id: 1, }, {  text: '西红柿', id: 2 },{ text: '鸡蛋',id: 3}]},
      {  text: '土菜馆',  children: [{ text: '芒果', id: 1, }, {  text: '橘子', id: 2 },{ text: '豆皮',id: 3}]},
      {  text: '土菜馆',  children: [{ text: '芒果', id: 1, }, {  text: '橘子', id: 2 },{ text: '豆皮',id: 3}]},
      {  text: '土菜馆',  children: [{ text: '芒果', id: 1, }, {  text: '橘子', id: 2 },{ text: '豆皮',id: 3}]},
      {  text: '土菜馆',  children: [{ text: '芒果', id: 1, }, {  text: '橘子', id: 2 },{ text: '豆皮',id: 3}]},
      {  text: '土菜馆',  children: [{ text: '芒果', id: 1, }, {  text: '橘子', id: 2 },{ text: '豆皮',id: 3}]},
      {  text: '土菜馆',  children: [{ text: '生鲜', id: 1, }, {  text: '西红柿', id: 2 },{ text: '西红柿',id: 3}]},
      {  text: '菜馆',  children: [{ text: '芒果', id: 1, }, {  text: '芒果', id: 2 },{ text: '芒果',id: 3}]},
      {  text: '菜馆',  children: [{ text: '芒果', id: 1, }, {  text: '芒果', id: 2 },{ text: '芒果',id: 3}]},
  
],
      activeIds: [0],
      activeIndex: -1
    };
  },
   methods:{
      sendandjump(){
         this.senddata.startDate=localStorage.startdate
         this.senddata.endDate=localStorage.enddate
         this.senddata.type=parseInt(localStorage.type)
         this.senddata.shopId=this.shopId
         this.senddata.commodityIds=this.dps
          var finnalsenddata=JSON.stringify(this.senddata)
          var number=[{uni:'kg'},{niu:'gk'}]
         //   var testnumber=JSON.stringify(number[0],number[1])
         //  var number2=number.join(',')
          localStorage.setItem('test',JSON.stringify(number));
          var gesttest=JSON.parse(localStorage.getItem('test'))
          console.log(gesttest)
             console.log(this.senddata)
             console.log(this.getallshopname)
          this.$axios.post("/api/billShop/query",this.senddata).then(res=>{
            console.log(res)
          localStorage.setItem('onshop',JSON.stringify(res.data));
          //console.log(strarr)
         
          })
           .catch(err=>{
               Toast({
           message:'服务器忙',
           position:"bottom",
        });
        
          })
          this.$router.push('/dpfl')
      
      //   var strarr=localStorage.test.split(',')
      //    console.log(strarr)
         
      },
    cp(index){
        localStorage.setItem('dianming',this.items[index].text)
       this.activeIds=[0]
       this.dps=[]
       //this.shopid=index.toString()
      this.shopId = this.shopList[index].id
      console.log(index)
    },
    dp(data){
   //   console.log(data)
   // console.log(this.activeIds)
        //this.dps.push(data.id)
        var c = this.commodityList[data.id - 1];
        console.log(c);
        this.dps.push(c.id);
        let resultarr = [...new Set(this.dps)]; 
        this.dps=resultarr
        console.log(this)
        console.log(data)
    console.log(this.dps);
    },
    getshopdata(){
   this.$axios.get("/api/billShop/list").then(res=>{
      //  console.log(res.data)
     var shopobj
     for(var j=0;j<res.data.length;j++){
       shopobj={text:res.data[j].name};
       this.getallshopname.push(shopobj)
     }
     this.shopList = res.data;
   })
   .catch(err=>{
               Toast({
           message:'服务器忙',
           position:"bottom",
        });
          })
    },
    getdata(){
   this.$axios.get("/api/billCommodity/list").then(res=>{
      console.log(res)
      var vegobj
     for(var i=0;i<res.data.length;i++){
        vegobj={text:res.data[i].name,id:i+1}
        this.getallvegname.push(vegobj)
        for(var k=0;k<this.getallshopname.length;k++){
        this.getallshopname[k].children=this.getallvegname
      }
      this.commodityList = res.data;
     }
   //   console.log('getallvegname='+this.getallvegname)
       })
        .catch(err=>{
               Toast({
           message:'服务器忙',
           position:"bottom",
        });
          })
     
      // for(var k=0;k<this.getallshopname.length;k++){
      //   this.getallshopname[k].children=this.getallvegname
       
      // }
//  console.log('test'+this.getallshopname)
      this.items=this.getallshopname
      
      // console.log('items'+this.items)
    }
  },
  created:function(){
       this.getdata()
      this.getshopdata()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.time{
  float:right;
  font-size: 20px;
}
span{
   font-family: "隶书";
   font-size: 25px;
   color: #DC624F;
   
   
}
.btn{
   margin-top: 5px;
}
</style>
