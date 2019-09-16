<template>
 <div>
    <div class="all_list">
       <h4 @click='dayshow'>日汇总</h4>
       <h4 @click='monthshow'> 月汇总</h4>
       <h4 @click='yearshow'>年汇总</h4>
    </div>
    <div>
       <h3></h3>
      <van-picker :columns="columns" :visible-item-count=5  v-show="year" v-model="date"  show-toolbar  @confirm='confirm'  @change="cag"  @cancel="nothing" confirm-button-text='按菜品分类'
  cancel-button-text='按店铺分类'/>
       <van-datetime-picker
  v-model="date"
  type="year-month"
  @confirm='confirm'
   v-show="month"
    @change="cag"
    @cancel="nothing"
  confirm-button-text='按菜品分类'
  cancel-button-text='按店铺分类'
/>
       <van-datetime-picker
  
  type="date"
  v-model="date"
  @confirm='confirm'
  v-show="day"
   @cancel="nothing"
   @change="cag"
   confirm-button-text='按菜品分类'
  cancel-button-text='按店铺分类'
/>
    </div>
   
 </div>
</template>

<script>
var type
import Vue from 'vue'
import { Toast } from 'vant';

Vue.use(Toast);
  export default {
     
   data() {
     
    return {
       dataduration:{},
       finaldate:'',
       columns: ['2018','2019','2020','2021','2022','2023','2024','2025','2026','2027','2028','2029','2030', '2031','2032','2033','2034', '2035','2036','2037','2038', ],
       date:"",
       year:false,
      day:false,
      month:false

    };
  },
  methods:{
     cag(picker){
       var index= picker.getValues()
      //  console.log(index)
      var newday= index.join('-')
      var corr=newday.substr(newday.length-1,1)
     var new_corr= newday.substring(0,newday.length - 1);
   if(newday.length==10){
      var ten=newday.substring(8)
      var ten_before=newday.substring(0,8)
      console.log(ten)
       if(ten=='10'){
         ten='09'
         var corr2=ten_before.concat(ten)
         this.dataduration.startDate=corr2
         this.dataduration.endDate=newday
    console.log(this.dataduration)
       }else if(ten=='20'){
         ten='19'
         var corr2=ten_before.concat(ten)
         this.dataduration.startDate=corr2
         this.dataduration.endDate=newday
    console.log(this.dataduration)
       }else if(ten=='30'){
        ten='29'
         var corr2=ten_before.concat(ten)
         this.dataduration.startDate=corr2
         this.dataduration.endDate=newday
    console.log(this.dataduration)
       }
      else{var str2=corr-1;
     var corr2=new_corr.concat(str2)
     this.dataduration.startDate=corr2
     this.dataduration.endDate=newday
    console.log(this.dataduration)}
        
     
     
     
   }
   if(newday.length==7){
      var str3='-01'
      var str4='-31'
      var new_str3=newday.concat(str3)
      var new_str4=newday.concat(str4)
      this.dataduration.startDate=new_str3
     this.dataduration.endDate=new_str4
     console.log(this.dataduration)
   }  
   if(newday.length==4){
      var str5='-01-01'
      var str6='-12-31'
      var new_str5=newday.concat(str5)
      var new_str6=newday.concat(str6)
      this.dataduration.startDate=new_str5
     this.dataduration.endDate=new_str6
     console.log(this.dataduration)
   }
    localStorage.setItem('startdate',this.dataduration.startDate)
    localStorage.setItem('enddate',this.dataduration.endDate)
    
    
      this.finaldate=newday
        localStorage.setItem('day',this.finaldate)
        console.log(localStorage.day.length)  
     },
     
     nothing(){
      //  this.year=false;
      //  this.day=false;
      //  this.month=false
       type=1;
      localStorage.setItem('type',type)
      console.log(localStorage.day+'================='+localStorage.type)
     this.$router.push('/derict')

     },
     onconfirm(value){
        
        this.date=value;
        console.log(this.date)
        
        
         
        
     },
     confirm(value){
        type=0;
        localStorage.setItem('type',type)
      console.log(localStorage.day+'================='+localStorage.type)
     
      this.$router.push('/change')
     },
     dayshow(){
        this.day=true;
        this.year=false;
        this.month=false;
        localStorage.setItem('date','value')
        Toast({
           message:'请选择日期',
           position:"bottom",

        });
     },
     yearshow(){
         this.day=false;
        this.year=true;
        this.month=false;
       Toast({
           message:'请选择年份',
           position:"bottom",
           
        });
     },
     monthshow(){
         this.day=false;
        this.year=false;
        this.month=true;
        Toast({
           message:'请选择月份',
           position:"bottom",
           
        });
     },
     

  }
  };
  
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.all_list{
  
   width: 100%;
   height: 35px;
   line-height: 35px;
   color: white;
   display: flex;
   justify-content:space-around;
   background-color: #96B97D;
   
}
h4{
   
  margin-top: 0px;
   display: inline;
   
}
h4:hover{
   color: red;
}
</style>
