<!-- 定义模版对象 -->
<template>
  <div class="login_container">
    <div class="login_box">
      <!-- 头像区域-->
      <div class="avatar_box">
        <img src="../assets/logo.png" alt="VUE图片" />
      </div>

      <!-- 登陆表单区域
            ref代表当前表单引用对象,之后form表单可以通过ref进行引用
           :model 是表格中封装的对象
           :rules 对整个表单进行数据校验
      -->
      <el-form ref="loginFormRef" label-width="0" class="login_form"  :model="loginForm" :rules="rules">
        <!-- prop 表示需要校验的字段名 -->
        <el-form-item prop="username">
          <!-- prefix-icon 表示小图标 -->
          <el-input  prefix-icon="iconfont iconuser" v-model="loginForm.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input  prefix-icon="iconfont iconsuo" type="password" v-model="loginForm.password"></el-input>
        </el-form-item>
        <el-form-item class="btns">
          <el-button type="primary" round @click="loginBtn">登录</el-button>
          <el-button type="info" round @click="resetBtn">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<!-- 定义JS变量 -->
<script>
/* export 对外声明自己的属性信息 */
export default {
  data(){
    return {
      loginForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          /*
          required 是否为必填项,true表示是必填项
          message 提示信息
          trigger 触发条件，blur表示离焦触发
          */
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur' }
        ]

      }
    }
  },
  methods: {
    resetBtn(){
      //alert("点击生效")
      /* this.loginForm.username = '',
      this.loginForm.password = '' */
      /* console.log(this)
            this代表当前的Vue组件对象(是组件对象，不是Vue对象)
            $ 表示从vue对象中获取数据
            $refs 表示从vue组建中获取当前vue对象中的全部ref属性
            resetFields() 对整个表单进行重置，将所有字段值重置为初始值并移除校验结果
      */
      this.$refs.loginFormRef.resetFields()
    },
    loginBtn(){
      // alert("登录点击生效")
      // 获取表单数据
      this.$refs.loginFormRef.validate(valid=>{
        // alert(valid)  //返回值为 true或者false
        // 2.当程序没有通过校验时，程序终止 -- 直接return就是终止程序
        if(!valid) return

        alert("校验通过")

      })
    }
  }
}
</script>

<!-- 防止组件冲突 -->
<style lang="less" scoped>
.login_container{
  background-color: #2b4b6b;
  height: 100%;
}

.login_box {
  width: 450px;
  height: 300px;
  background-color: #FFFFFF;
  /* 设定圆弧角*/
  border-radius: 10px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);

  .avatar_box {
    height: 130px;
    width: 130px;
    border: 1px solid #EEEEEE;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #DDDDDD; /* 添加阴影*/
    position: absolute;           /* 绝对定位*/
    left: 50%;                    /* 距离左侧50%*/
    transform: translate(-50%,-50%);   /*回调50%*/
    background-color: #FFFFFF;
    img {
      height: 100%;
      width: 100%;
      border-radius: 50%;
      background-color: #EEEEEE;
    }
  }

  .btns {
    display: flex;
    justify-content: flex-end;

  }

  .login_form {
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;
  }
}
</style>
