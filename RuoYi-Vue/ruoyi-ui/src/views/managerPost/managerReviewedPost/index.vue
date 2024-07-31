<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="博文标题" prop="postTitle">
        <el-input
          v-model="queryParams.postTitle"
          placeholder="请输入博文标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="博文作者" prop="postAuthor">
        <el-input
          v-model="queryParams.postAuthor"
          placeholder="请输入博文作者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="博文发表日期" prop="postDate">
        <el-date-picker clearable
          v-model="queryParams.postDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择博文发表日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="managerReviewedPostList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="45" align="center" /> -->
      <el-table-column label="博文id" align="center" prop="postID" />
      <el-table-column label="博文标题" align="center" prop="postTitle" />
      <el-table-column label="博文作者" align="center" prop="postAuthor" />
      <el-table-column label="博文发表日期" align="center" prop="postDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.postDate, '{yyyy}-{mm}-{dd}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="用户id" align="center" prop="userID" />
      <el-table-column label="博文内容" align="center" prop="postContent" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleDetail(scope.row)"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!-- 查看详情对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
    <el-row :gutter="20">
      <el-col :span="12">
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-divider content-position="center">博客信息</el-divider>
          <el-form-item label="博文标题" prop="postTitle" class="custom-form-item">
            <el-input v-model="form.postTitle" readonly class="custom-input" />
          </el-form-item>
          <el-form-item label="博文作者" prop="postAuthor" class="custom-form-item">
            <el-input v-model="form.postAuthor" readonly class="custom-input" />
          </el-form-item>
          <el-form-item label="博文发表日期" prop="postDate" class="custom-form-item">
            <el-date-picker clearable v-model="form.postDate" type="date" value-format="yyyy-MM-dd" readonly class="custom-input" />
          </el-form-item>
          <el-form-item label="博文审核状态" prop="postStatus" class="custom-form-item">
            <el-input v-model="postStatusText" readonly class="custom-input" />
          </el-form-item>
          <el-form-item label="博文内容" prop="postContent">
            <el-input type="textarea" v-model="form.postContent" readonly class="custom-input"/>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="1">
        <div class="divider">
          <el-divider direction="vertical"></el-divider>
        </div>
      </el-col>
      <el-col :span="11">
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-divider content-position="center">评论</el-divider>
        </el-form> 
        <!-- <el-table v-loading="loading" :data="comments" >
      <el-table-column type="selection" width="45" align="center" /> -->
      <!-- <el-table-column label="作者" align="center" prop="commentAuthor" />
      <el-table-column label="内容" align="center" prop="commentContent" />
      </el-table> -->

      <div>
        <div v-for="com in comments" >
          <div style="color:green">{{ com.commentAuthor }}</div>
          <div>{{ com.commentContent }}</div>
        </div>
      </div>



      </el-col>
    </el-row>
    <div class="dialog-footer">
      <el-button @click="cancel">关闭</el-button>
    </div>
  </el-dialog>
  </div>
</template>

<style>
.centered-footer {
  display: flex;
  justify-content: center;
}
</style>



<script>
import { listManagerReviewedPost, getManagerReviewedPost, delManagerReviewedPost, updateManagerReviewedPost,getcommentlist } from "@/api/managerPost/managerReviewedPost";

export default {
  name: "ManagerReviewedPost",
  data() {
    return {
       // 表单参数
       form: {
        postStatus: 1  // 示例值为已通过
       },
       comments:[],
       commentTotal: 0,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 已审核博客表格数据
      managerReviewedPostList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        postID: null,
        postTitle: null,
        postAuthor: null,
        postDate: null,
      },
      // 表单校验
      rules: {
        postTitle: [
          { required: true, message: "博文标题不能为空", trigger: "blur" }
        ],
        postAuthor: [
          { required: true, message: "博文作者不能为空", trigger: "blur" }
        ],
        postDate: [
          { required: true, message: "博文发表日期不能为空", trigger: "blur" }
        ],
        postStatus: [
          { required: true, message: "博文审核状态不能为空", trigger: "change" }
        ],
        userID: [
          { required: true, message: "用户id不能为空", trigger: "blur" }
        ],
        postContent: [
          { required: true, message: "博文内容不能为空", trigger: "blur" }
        ]
        
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询已审核博客列表 */
    getList() {
      this.loading = true
      listManagerReviewedPost(this.queryParams).then(response => {
        this.managerReviewedPostList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        postID: null,
        postTitle: null,
        postAuthor: null,
        postDate: null,
        postStatus: "已通过",
        userID: null,
        postContent: null,
        commentAuthor: null,
        commentContent: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.searchByTitle();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // // 多选框选中数据
    // handleSelectionChange(selection) {
    //   this.ids = selection.map(item => item.postID)
    //   this.single = selection.length !== 1
    //   this.multiple = !selection.length
    // },
    /** 查看按钮操作 */
    handleDetail(row) {
      this.reset();
      getManagerReviewedPost(row.postID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "博文详细信息";

      });
      getcommentlist(row.postID).then(response =>{
        this.comments = response.rows;
        this.commentTotal = response.total;
      
    
      })

    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const postID = row.postID || this.ids
      this.$confirm(`是否确认删除博文id为"${postID}"的数据项?`, "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delManagerReviewedPost(postID);
      }).then(() => {
        this.getList();
        this.$message.success("删除成功");
      }).catch(function() {});
    },
    // 提交按钮
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const request = this.form.postID ? updateManagerReviewedPost : addManagerReviewedPost;
          request(this.form).then(() => {
            this.getList();
            this.$message.success("操作成功");
            this.open = false;
          });
        }
      });
    },
    // 重置表单
    resetForm(formName) {
      this.$refs[formName] && this.$refs[formName].resetFields();
    },
    // 时间解析
    parseTime(time, cFormat) {
      if (arguments.length === 0) {
        return null;
      }
      const format = cFormat || "{y}-{m}-{d} {h}:{i}:{s}";
      let date;
      if (typeof time === "object") {
        date = time;
      } else {
        if (typeof time === "string" && /^[0-9]+$/.test(time)) {
          time = parseInt(time);
        } else if (typeof time === "string") {
          time = time.replace(new RegExp(/-/gm), "/");
        }
        if (typeof time === "number" && time.toString().length === 10) {
          time = time * 1000;
        }
        date = new Date(time);
      }
      const formatObj = {
        y: date.getFullYear(),
        m: date.getMonth() + 1,
        d: date.getDate(),
        h: date.getHours(),
        i: date.getMinutes(),
        s: date.getSeconds(),
        a: date.getDay()
      };
      const time_str = format.replace(
        /{([ymdhisa])+}/g,
        (result, key) => {
          let value = formatObj[key];
          if (key === "a") {
            return ["日", "一", "二", "三", "四", "五", "六"][value];
          }
          if (result.length > 0 && value < 10) {
            value = "0" + value;
          }
          return value || 0;
        }
      );
      return time_str;
    }
  },
  computed: {
    postStatusText() {
      // 根据 postStatus 返回对应的文字描述
      return this.form.postStatus === 1 ? '已通过' : '未审核';
    }
  }
};
</script>


<style>
.custom-form-item {
  margin-bottom: 20px;
}
.custom-divider {
  font-size: 20px;
  font-weight: bold;
}
.green-divider {
  border-left: 5px solid green;
  height: 100%;
}
.center-divider {
  display: flex;
  justify-content: center;
  align-items: center;
}
.dialog-footer {
  text-align: center;
  margin-top: 20px;
}
.user-id-input {
  border: none;
  background: none;
  pointer-events: none;
}
</style>
