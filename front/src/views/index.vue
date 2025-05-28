<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <blockquote class="text-warning" style="font-size: 14px">
          欢迎来到论文管理系统，开启你的论文管理之旅
          <br />
          本网站由王石，周天宇，刘泽宇制作
        </blockquote>

        <hr />

        <!-- 新增：插入图片 -->
        <div style="text-align: center; margin-top: 20px">
          <img
            src="@/assets/images/login-background1.jpg"
            alt="论文系统宣传图"
            style="width: 100%; height: auto; display: block; border-radius: 10px"
          />
        </div>
      </el-col>
    </el-row>
  </div>
</template>
