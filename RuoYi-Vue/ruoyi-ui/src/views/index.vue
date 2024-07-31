<template>
    <div class="home-page">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <blockquote class="text-warning" style="font-size: 14px">
          领取阿里云通用云产品1888优惠券
          <br />
          <el-link
            href="https://www.aliyun.com/minisite/goods?userCode=brki8iof"
            type="primary"
            target="_blank"
            >https://www.aliyun.com/minisite/goods?userCode=brki8iof</el-link
          >
          <br />
          领取腾讯云通用云产品2860优惠券
          <br />
          <el-link
            href="https://cloud.tencent.com/redirect.php?redirect=1025&cps_key=198c8df2ed259157187173bc7f4f32fd&from=console"
            type="primary"
            target="_blank"
            >https://cloud.tencent.com/redirect.php?redirect=1025&cps_key=198c8df2ed259157187173bc7f4f32fd&from=console</el-link
          >
          <br />
          阿里云服务器折扣区
          <el-link href="http://aly.ruoyi.vip" type="primary" target="_blank"
            >>☛☛点我进入☚☚</el-link
          >
          &nbsp;&nbsp;&nbsp; 腾讯云服务器秒杀区
          <el-link href="http://txy.ruoyi.vip" type="primary" target="_blank"
            >>☛☛点我进入☚☚</el-link
          ><br />
          <h4 class="text-danger">
            云产品通用红包，可叠加官网常规优惠使用。(仅限新用户)
          </h4>
        </blockquote>
        <hr />
      </el-col>
    </el-row>
    <el-container>
      <el-aside width="400px">
        <el-card class="contact-card">
          <h3 class="t">联系方式</h3>
          <p>电话: 123-456-7890</p>
          <p>邮箱: contact@example.com</p>
          <p>地址: 北京市海淀区某某街道</p>
        </el-card>
      </el-aside>
      <el-main>
        <el-card class="box-card">
          <h2 class="title">中软国际SNS社交平台</h2>
          <p class="content">欢迎来到中软国际SNS社交平台！这个平台是由中软国际倾力打造的综合性社交平台，旨在为用户提供一个充满乐趣和多样化的线上交流与互动空间。无论你是希望分享生活中的美好瞬间，结识新朋友，还是参与各类兴趣小组和活动，这里都能满足你的需求。</p>
          <p class="content"> 我们的目标是为用户创建一个安全、便捷且高效的社交环境，让大家可以无障碍地沟通和互动。加入我们，享受无尽的社交乐趣，与全球的朋友们共同探索这片充满生机的社交天地吧！</p>
        </el-card>
        <el-divider />

        <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h3 class="t2">  技术选型</h3>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="10">
            <h4>后端技术</h4>
              <li>SpringBoot</li>
              <li>Spring Security</li>
              <li>JWT</li>
              <li>MyBatis</li>
              <li>Druid</li>
              <li>Fastjson</li>
              <li>...</li>
          </el-col>
          <el-col :span="10">
            <h4>前端技术</h4>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Sass</li>
              <li>Quill</li>
              <li>...</li>
 
          </el-col>
        </el-row>
      </el-col>


      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'HomePage',
  data() {
    return {
      activeIndex: '1',
    };
  },
};
</script>

<style scoped>
.home-page {
  font-family: 'Arial', sans-serif;
}

.el-header {
  background-color: #b3c0d1;
  color: #333;
}

.el-menu-demo {
  background-color: #b3c0d1;
  border: none;
}

.el-main {
  padding: 20px;
}

.box-card {
  width: 100%;
}

.title {
  font-size: 32px;
  color: green;
}

.t {
  font-size: 32px;
  color: #15b17b;
}

.t2 {
  font-size: 32px;
  color: rgb(13, 116, 172);
}
.content {
  font-size: 18px;
}

.contact-card {
  margin: 20px;
  padding: 20px;
}

.contact-card h3 {
  font-size: 20px;
  margin-bottom: 5px;
}

.contact-card p {
  font-size: 15px;
  margin: 2px 0;
}
</style>
