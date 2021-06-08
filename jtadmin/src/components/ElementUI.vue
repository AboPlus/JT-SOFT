<template>
  <div>
    <!-- 定义组件样式-->
     <el-menu width='200px'
          default-active="2"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
          <el-submenu index="1">
            <!-- 定义一级标题-->
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>导航一</span>
            </template>
            
            <!--定义二级标题-->
            <el-menu-item >
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>选项</span>
              </template>
            </el-menu-item>
          </el-submenu>
      </el-menu>

  </div>
</template>

<script>
</script>

<style>
</style>
