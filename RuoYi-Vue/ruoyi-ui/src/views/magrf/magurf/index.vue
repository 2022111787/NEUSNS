<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="内推ID" prop="referralID">
        <el-input
          v-model="queryParams.referralID"
          placeholder="请输入内推ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="职位名称" prop="jobTitle">
        <el-input
          v-model="queryParams.jobTitle"
          placeholder="请输入职位名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="referralList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="内推ID" align="center" prop="referralID" />
      <el-table-column label="公司名称" align="center" prop="companyName" />
      <el-table-column label="职位名称" align="center" prop="jobTitle" />
      <el-table-column label="所在城市名称" align="center" prop="address" />
      <!-- <el-table-column label="薪资" align="center" prop="salary" />
      <el-table-column label="联系人电话" align="center" prop="contactNumber" />
      <el-table-column label="联系人名字" align="center" prop="contactName" />
      <el-table-column label="技术要求描述" align="center" prop="techRquirements" /> -->
      <!-- <el-table-column label="开始时间" align="center" prop="startTime" width="180"> -->
        <!-- <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template> -->
      <!-- </el-table-column> -->
      <el-table-column label="博文审核状态" align="center" prop="referral_status"/>
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
            icon="el-icon-check"
            @click="handleApprove(scope.row)"
          >通过</el-button>
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

    <!-- 查看内推管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px" class="custom-form">
        <el-divider content-position="center">内推信息</el-divider>
        <el-row :gutter="10" class="mb8">
           <el-col :span="24">
             <el-form-item label="公司名称" prop="companyName" class="custom-form-item">
               <el-input v-model="form.companyName" readonly class="custom-input"/>
             </el-form-item>
           </el-col>
           <el-col :span="24">
            <el-form-item label="职位名称" prop="jobTitle" class="custom-form-item">
              <el-input v-model="form.jobTitle" readonly class="custom-input"/>
             </el-form-item>
           </el-col>
           <el-col :span="24">
           <el-form-item label="所在城市名称" prop="address" class="custom-form-item">
            <el-input v-model="form.address" readonly class="custom-input" />
             </el-form-item>
           </el-col>
           <el-col :span="24">
           <el-form-item label="薪资" prop="salary" class="custom-form-item">
            <el-input v-model="form.salary"  readonly class="custom-input"/>
             </el-form-item>
           </el-col>
           <el-col :span="24">
            <el-form-item label="联系人电话" prop="contactNumber" class="custom-form-item">
              <el-input v-model="form.contactNumber" readonly class="custom-input" />
             </el-form-item>
           </el-col>
           <el-col :span="24">
            <el-form-item label="联系人名字" prop="contactName" class="custom-form-item">
              <el-input v-model="form.contactName" readonly class="custom-input"/>
             </el-form-item>
           </el-col>
           <el-col :span="24">
            <el-form-item label="技术要求描述" prop="techRquirement" class="custom-form-item">
              <el-input v-model="form.techRquirement" readonly class="custom-input" />
             </el-form-item>
           </el-col>
           <el-col :span="24">
            <el-form-item label="开始时间" prop="startTime" class="custom-form-item">
              <el-date-picker v-model="form.startTime" type="date" placeholder="选择开始时间" readonly class="custom-input" />
             </el-form-item>
           </el-col>
           <el-col :span="24">
            <el-form-item label="结束时间" prop="endTime" class="custom-form-item">
              <el-date-picker v-model="form.endTime" type="date" placeholder="选择结束时间" readonly class="custom-input" />
             </el-form-item>
           </el-col>
           <el-col :span="24">
            <el-form-item label="审核状态" prop="referral_status" class="custom-form-item">
              <el-input v-model="this.form.referral_status" readonly class="custom-input"/>
             </el-form-item>
           </el-col>
        </el-row>
      </el-form>
    </el-dialog>
  </div>
</template>






<script>
import { getUnreviewedReferrals, getUnreviewedReferral, deleteUnreviewedReferral, addReferral,  updateReferral} from "@/api/magrf/magurf";

export default {
  name: "Referral",
  data() {
    return {
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
      // 内推管理表格数据
      referralList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        referralID: null,
        companyName: null,
        jobTitle: null,
        address: null,
        salary: null,
      },
      // 表单参数
      form: {
        referralID: null,
        companyName: null,
        jobTitle: null,
        address: null,
        salary: null,
        contactNumber: null,
        contactName: null,
        techRquirements: null,
        startTime: null,
        endTime: null,
        referralstatus: 0
      },
      // 表单校验
      rules: {
        companyName: [
          { required: true, message: "公司名称不能为空", trigger: "blur" }
        ],
        jobTitle: [
          { required: true, message: "职位名称不能为空", trigger: "blur" }
        ],
        address: [
          { required: true, message: "所在城市名称不能为空", trigger: "blur" }
        ],
        salary: [
          { required: true, message: "薪资不能为空", trigger: "blur" }
        ],
        contactNumber: [
          { required: true, message: "联系人电话不能为空", trigger: "blur" }
        ],
        contactName: [
          { required: true, message: "联系人名字不能为空", trigger: "blur" }
        ],
        techRquirements: [
          { required: true, message: "技术要求描述不能为空", trigger: "blur" }
        ],
        startTime: [
          { required: true, message: "开始时间不能为空", trigger: "blur" }
        ],
        endTime: [
          { required: true, message: "结束时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    formatReferralStatus(row, column) {
      if (row.referralstatus === 0) {
        return '待审核';
      }
      return row.referralstatus;
    },
    parseTime(time, cFormat) {
      // formatPostStatus(row, colum){
      //   if(row.)
      // }
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
      const time_str = format.replace(/{([ymdhisa])+}/g, (result, key) => {
        const value = formatObj[key];
        if (key === "a") {
          return ["日", "一", "二", "三", "四", "五", "六"][value];
        }
        return value.toString().padStart(2, "0");
      });
      return time_str;
    },
    /** 查询内推列表 */
    getList() {
      this.loading = true;
      getUnreviewedReferrals(this.queryParams).then(response => {
        this.referralList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.referralList.forEach(element => {
            element.referral_status = this.changeText(element.referral_status)
        });
      });
    },
    changeText(code){
        if(code === 0){
          return '待审核'
        }
        return '已审核'
        
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        referralID: null,
        companyName: null,
        jobTitle: null,
        address: null,
        salary: null,
      };
      this.getList();
    },
    /** 选中数组操作 */
     handleSelectionChange(selection) {
     this.ids = selection.map(item => item.referralID);
     this.single = selection.length !== 1;
     this.multiple = !selection.length;
    },
    /** 详情按钮操作 */
    handleDetail(row) {
      getUnreviewedReferral(row.referralID).then(response => {
        this.form = response.data;
        this.title = "内推详情";
        this.open = true;
        this.form.referral_status = this.changeText(this.form.referral_status)
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$confirm('是否确认删除此内推信息?', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteUnreviewedReferral(row.referralID).then(response => {
          this.$message({
            type: 'success',
            message: '删除成功'
          });
          this.getList();
        });
      }).catch(() => {});
    },
    /** 通过按钮操作 */
    handleApprove(row) {
      this.$confirm(`是否确认通过内推id为"${row.referralID}"的数据项?`, "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        row.referral_status=0;
        updateReferral({ ...row, referralstatus: 1 }).then(() => {
          this.getList();
          this.$message.success("操作成功");
        });
      }).catch(function() {});
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
          const formData = this.form;
          const request = formData.referralID ? updateReferral(formData) : addReferral(formData);
          request.then(response => {
            this.$message({
              type: 'success',
              message: '操作成功'
            });
            this.open = false;
            this.getList();
          });
        }
      });
    },

    /** 取消按钮 */
    cancel() {
      this.open = false;
    }
  },
  mounted() {
    this.getList();
  },
  computed : {
    referralstatusText() {
      return this.form.referralstatus === 0 ? '待审核' : '已通过';
  }
}
};
</script>

<style scoped>
.fixed-width {
  width: 180px;
}
.small-padding {
  padding: 5px;
}
</style>
