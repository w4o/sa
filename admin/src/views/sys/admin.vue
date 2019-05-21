<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.username" placeholder="Username" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">搜索</el-button>
      <el-button v-permission="['sys:admin:create']" v-waves class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="用户名">
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>
      <el-table-column label="头像" width="110" align="center">
        <template slot-scope="scope">
          <img v-if="scope.row.avatar" :src="scope.row.avatar" width="40" alt="avatar">
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-permission="['sys:admin:update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button v-permission="['sys:admin:delete']" type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.size"
      :page-sizes="[5,10,15]"
      @pagination="fetchData"
    />

    <el-dialog :title="dialogTitleMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="adminInfo" :rules="rules" :model="adminInfo" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="管理员名称" prop="username">
          <el-input v-model="adminInfo.username" :disabled="dialogUsernameDisabled" />
        </el-form-item>
        <el-form-item label="管理员密码" prop="password">
          <el-input v-model="adminInfo.password" :disabled="dialogPasswordDisabled" type="password" auto-complete="off" />
        </el-form-item>
        <el-form-item label="管理员头像" prop="avatar">
          <el-upload
            :headers="headers"
            :action="uploadPath"
            :show-file-list="false"
            :on-success="uploadAvatar"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif"
          >
            <img v-if="adminInfo.avatar" :src="adminInfo.avatar" class="avatar" alt="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>
        <el-form-item label="备注信息" prop="remark">
          <el-input v-model="adminInfo.remark" type="textarea" auto-complete="off" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus==='create'" type="primary" @click="createAdmin">确定</el-button>
        <el-button v-else type="primary" @click="updateAdmin">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #20a0ff;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 120px;
    line-height: 120px;
    text-align: center;
  }
  .avatar {
    width: 145px;
    height: 145px;
    display: block;
  }
</style>

<script>
import { getList, getInfo, createAdmin, updateAdmin, deleteAdmin } from '@/api/admin'
import waves from '@/directive/waves'
import permission from '@/directive/permission'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination'

export default {
  components: { Pagination },
  directives: { waves, permission },
  filters: {},
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        username: undefined,
        page: 1,
        size: 5
      },
      dialogStatus: '',
      dialogTitleMap: {
        update: '编辑',
        create: '创建'
      },
      dialogFormVisible: false,
      dialogPasswordDisabled: false,
      dialogUsernameDisabled: false,
      uploadPath: '/admin/upload',
      adminInfo: {
        username: '',
        password: '',
        remark: ''
      },
      rules: {
        username: [
          { required: true, message: '管理员名称不能为空', trigger: 'blur' },
          { min: 5, max: 20, message: '用户名长度为5～20', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 6, max: 40, message: '密码长度为6～40', trigger: 'blur' }
        ],
        remark: [{ max: 255, message: '备注信息最大长度255', trigger: 'blur' }]
      }
    }
  },
  computed: {
    headers() {
      return {
        'X-Token': getToken()
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
        this.listLoading = false
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.fetchData()
    },
    resetForm() {
      this.adminInfo = {
        id: undefined,
        username: undefined,
        password: undefined,
        avatar: undefined,
        remark: undefined
      }
      this.dialogPasswordDisabled = false
      this.$nextTick(() => {
        this.$refs['adminInfo'].clearValidate()
      })
    },
    handleCreate() {
      this.dialogStatus = 'create'
      this.resetForm()
      this.dialogFormVisible = true
      this.dialogPasswordDisabled = false
      this.dialogUsernameDisabled = false
    },
    handleUpdate(row) {
      this.resetForm()
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.getAdminInfo(row.id)
      this.dialogPasswordDisabled = true
      this.dialogUsernameDisabled = true
    },
    uploadAvatar(response) {
      this.adminInfo.avatar = response.data.url
    },
    handleDelete(index, row) {
      this.$confirm('要删除管理员[' + row.username + ']?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteAdmin(row.id).then(() => {
          this.$notify.success({
            title: '成功',
            message: '管理员[' + row.username + ']删除成功'
          })
          this.list.splice(index, 1)
        }).catch(response => {
          this.$notify.error({
            title: '失败',
            message: response.message
          })
        })
      }).catch(() => {})
    },
    getAdminInfo(id) {
      getInfo(id).then(response => {
        this.dialogPasswordDisabled = true
        this.adminInfo = response.data
        this.adminInfo.password = 'not-change'
      })
    },
    updateAdmin() {
      this.$refs['adminInfo'].validate(valid => {
        if (valid) {
          updateAdmin(this.adminInfo).then(() => {
            for (const v of this.list) {
              if (v.id === this.adminInfo.id) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.adminInfo)
                break
              }
            }
            this.dialogFormVisible = false
            this.$notify.success({
              title: '成功',
              message: '管理员[' + this.adminInfo.username + ']更新成功'
            })
          }).catch(response => {
            this.$notify.error({
              title: '失败',
              message: response.message
            })
          })
        }
      })
    },
    createAdmin() {
      this.$refs['adminInfo'].validate(valid => {
        if (valid) {
          createAdmin(this.adminInfo).then(response => {
            this.list.unshift(response.data)
            this.dialogFormVisible = false
            this.$notify.success({
              title: '成功',
              message: '添加管理员成功'
            })
          }).catch(response => {
            this.$notify.error({
              title: '失败',
              message: response.message
            })
          })
        }
      })
    }
  }
}
</script>
