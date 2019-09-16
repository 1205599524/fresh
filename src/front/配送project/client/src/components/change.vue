<template>
 <div class="container">
     <span>菜品分类:</span>
     <span class="time">{{time}}</span>
     
   <van-tree-select
   height="350"
   v-model="msg"
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
      commodityList : [],
      shopid:'',
      time:localStorage.day,
      getallvegname:[],
      getallshopname:[],
      senddata:{},
      shopList:[],
      msg:[],
      dps:[],
      items:[
      // {  text: '白菜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}, {  text: '土菜馆', id: 4 },{ text: '菜馆',id: 5}]},
      // {  text: '土豆',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '西瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '黄瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '苦瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '苦瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '苦瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '苦瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '苦瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '苦瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '苦瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '苦瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '苦瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '苦瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '苦瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '苦瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '苦瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
      // {  text: '苦瓜',  children: [{ text: '我家菜馆', id: 1, }, {  text: '土菜馆', id: 2 },{ text: '菜馆',id: 3}]},
  
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
         this.senddata.commodityId=this.shopId
         this.senddata.shopIds=this.dps
          var finnalsenddata=JSON.stringify(this.senddata)
          var number=[{uni:'kg'},{niu:'gk'}]
         //   var testnumber=JSON.stringify(number[0],number[1])
         //  var number2=number.join(',')
          localStorage.setItem('test',JSON.stringify(number));
          var gesttest=JSON.parse(localStorage.getItem('test'))
          console.log(gesttest)
             console.log(this.senddata)
          this.$axios.post("/api/billShop/query",this.senddata).then(res=>{
            console.log(res)
          localStorage.setItem('oncaiming',JSON.stringify(res.data));
          // console.log(strarr)
          
          })
        //    .catch(err=>{
        //        Toast({
        //    message:'服务器忙',
        //    position:"bottom",
        // });
      
        //   })
            this.$router.push('/cpfl')
      //   var strarr=localStorage.test.split(',')
      //    console.log(strarr)
        //  this.$router.push('/cpfl')
      },
    getshopdata(){
   this.$axios.get("/api/billCommodity/list").then(res=>{
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
          //  location.reload() 
    },
    getdata(){
   this.$axios.get("/api/billShop/list").then(res=>{
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
    },
    cp(index){
      console.log(this.items[index].text)
      localStorage.setItem('caiming',this.items[index].text)
       this.activeIds=[0]
       this.dps=[]
        this.shopId = this.shopList[index].id
      //  this.shopid=index.toString()
      console.log(index)
      this.msg.cai=index
      // location.reload() 
    },
    dp(data){
    //  console.log(data)
    //     this.dps.push(data)
    // console.log(this.dps)
    // this.msg.dian=data
     var c = this.commodityList[data.id - 1];
      console.log(this.msg)
      this.dps.push(c.id);
        let resultarr = [...new Set(this.dps)]; 
        this.dps=resultarr
    console.log(this.dps)
    
    }
  },
   created:function(){
     this.getshopdata()
      this.getdata()
     
      
  },
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.time{
  float:right;
  font-size: 20px;
}
.container{}
.btn{
  margin-top: 5px;
}
span{
   font-family: "隶书";
   font-size: 25px;
   color: #DC624F;
   
   
}
</style>
