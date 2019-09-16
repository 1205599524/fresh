<template>
 <div class="main" >
    <div class="bg" v-show="database.see">
      <div class="content" style="overflow:auto;">
        <div class="h3" ><p >请确认录入信息</p></div>
        <div class="message">
          <!-- <ul class="master">
            <li> <h4>菜品</h4></li>
            <li> <h4>数量</h4></li>
            <li> <h4>单价</h4></li>
            <li> <h4>总价</h4></li>
            <li> <h4>单位</h4></li>
          
          </ul> -->
          <table style="width:100%">
            <tr>
              <th>菜品</th>
              <th>数量</th>
              <th>单价</th>
              <th>总价</th>
              <th>单位</th>
            </tr>
            <tr style="text-align:center" v-for="(item,i) in urllist" :key="i+1">
              <td>{{item.commodityName}}</td>
              <td>{{item.amount}}</td>
              <td>{{item.price}}</td>
              <td>{{item.total}}</td>
              <td>{{item.unit}}</td>
            </tr>
          </table>
         
          <!-- <ul class="master" v-for="(item,i) in urllist" :key="i+1" style="text-align:center">
            <li> <h5>{{item.commodityName}}</h5></li>
            <li> <h5>{{item.amount}}</h5></li>
            <li> <h5>{{item.price}}</h5></li>
            <li> <h5>{{item.total}}</h5></li>
            <li> <h5>{{item.unit}}</h5></li>
          
          </ul> -->
        
          <!-- <ul  v-for="(item,i) in databasearr" :key="i"><li class="li_msg">{{item}}</li>
          </ul> -->
         
        </div>
         <el-button type="danger" class="cancel2_btn"  @click="nosee" >返回修改</el-button>
          <el-button type="success" class="btn2"   @click="shopfun" >确认提交</el-button>
      </div>
      
    </div>
  <div class="add">
      <el-upload
  action="https://jsonplaceholder.typicode.com/posts/"
  list-type="picture-card"
  :on-preview="handlePictureCardPreview"
  :on-remove="handleRemove"
  :limit='1'
  :auto-upload='false'
  class="inpu"
  :on-change='hidd'
  :on-exceed='tost'
 
  >
  <i class="min_i">点击上传图片</i>
</el-upload>

<el-dialog :visible.sync="dialogVisible"  >
  <img width="100%" :src="dialogImageUrl" alt="">
</el-dialog>
      <el-form :model="database"  :rules="rules" ref="databaseform" label-width="100px" class="databaseform">
        
        <el-form-item label="" prop="name" class="item1">
          <el-input  v-model="database.name" name="name" autocomplete="on"   placeholder="请输入店家名称" class="topin"></el-input>
        </el-form-item>
         <el-form-item label="" prop="describe" class="item1">
          <el-input  v-model="database.describe" autocomplete="off" placeholder="请语音输入配送内容" type='textarea' class="botin" @blur="submitForm('databaseform')"></el-input>
        </el-form-item>
      
        <el-form-item>
      <el-button type="primary" class="cancel_btn"  @click="see"  >核对</el-button>
      <div class="price">总价：{{database.empty}}元</div>
      <router-view></router-view>

    <!-- <el-button type="primary" class="submit_btn"   @click="submitForm('databaseform')">提交</el-button> -->

  </el-form-item>
</el-form>
  </div>
 
 </div>
</template>

<script>
import Vue from 'vue'
import { Toast } from 'vant';

Vue.use(Toast);
export default {
    data() {
      
       return { 
         recodemsg:{},
         shopmsg:{},
         urllist:"",
         alldetail:{ detail:""},
         database:{
       name:"",
       describe:"",
       empty:'',
       see:false
      
     },
     databasearr:[],
      dialogImageUrl: '',
        dialogVisible: false,
     rules:{
      name:[{
         required:true,
         message:"店家名称不能为空",
         trigger:"blur"
       },
       ],
       describe:[{
         
         required:true,
         message:"配送内容不能为空",
         trigger:"blur"
       }],
     
      
     },
     
   }
    },
//    mounted:function(){
//      const f = document.getElementById("file")
//      f.onchange=()=>{
//        console.log(f.file)
// }
//      console.log(f)

//    },
    methods: {
      shopfun(){
        this.recodemsg.shopName=this.database.name
        this.recodemsg.items=this.urllist
        this.recodemsg.detail=this.database.describe
        this.recodemsg.total=this.database.empty
       this.$axios.post("/api/billOrder",this.recodemsg).then(res=>{
          Toast({
           message:'提交成功',
           position:"bottom",
        
        })

         console.log(res)
          this.database.see=false;
       })
      },
      
      tost(){
       Toast({
           message:'只能上传一张图片',
           position:"bottom",

        });
      },
     hidd(){

     },
      see(){
        this.database.see=true;
        var data=this.database.describe;
        console.log(data)
        this.databasearr=data.split('，')
        console.log(this.databasearr)
      },
      nosee(){
        this.database.see=false;
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.alldetail.detail=this.database.describe
            this.$axios.post("/api/billOrder/check",this.alldetail)
            .then(res=>{
              console.log(res.data.total)
              this.database.empty=res.data.total
              this.urllist=res.data.items
             
              console.log(this.urllist)
            })
            .catch(err=>{
              Toast({
           message:'服务器忙',
           position:"bottom",

        });
            })
            // alert('提交成功');
            // this.database.name=''
            // this.database.describe=''
          } else {
            console.log('error submit!!');
            return false;
          }
        });
        this.database.see=false;
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      
    }
  }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
tr th{
  padding-bottom: 10px 
}
tr td{
 padding-bottom: 10px;
 font-size: 14px;
}
.master{
  display: flex;
  justify-content: space-around;
}
.master li{
  display: inline-block;
}
.databaseform{
  width: 85%;
 
}
.price{
  position: absolute;
  width: 100%;
  height: 40px;
  border: 1px solid black;
  left: -20px;
  top: 60px;
  border-radius: 40px;
  text-align: center;
}
.li_msg{
  font-size: 18px;

}
.submit_btn{
    position: absolute;
    right: 0;
}
a{
    text-decoration: none;
    color: white;
}
.inpu {
 /* background-color: #DDD7D7; */
  margin-left: 15%;
  margin-bottom: 20px;

}
.inpu div.el-upload--picture-card{
background-color: black
}
.topin{
  
}
.botin{
 
}
.min_i{
  font-size: 16px;
  font-style:normal
}
.item1{
  margin-left: -40px;
}
.cancel_btn{
  position: absolute;
  left: -20px;
  width: 100%
}
.bg{
  background-color: grey;
  position: absolute;
  top: 60px;
  width: 100%;
  height: 580px;
  background:rgba(0, 0, 0, 0.6);
 z-index: 999;
  position: flex;
  justify-content:space-around;
  
}
.content{
  margin: 10px auto;
  height: 500px;
  width: 100%;
  background-color: white;
/* background:url('../assets/images/bg.jpeg'); */
background-position-y: -60px;
/* text-align: center; */

}
 .content .h3{
  font-family: "隶书";
  /* margin: 0 auto;
  width: 200px; */
  text-align: center;
  font-size: 17px;

}
.message{
 border-top: 2px solid #DDD7D7;
  width: 100%;
}
li{
  list-style: none;
}
.cancel2_btn{
  width: 50%;
  
  position: absolute;
  left: 0;
  bottom: 28px;
}
.btn2{
   width: 50%;
    bottom: 28px;
    right: 0;
  
  position: absolute;
}
p{
  font-size: 25px;
  color: black
}

</style>
