<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.name" placeholder="角色名" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">搜索</el-button>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="角色名">
        <template slot-scope="scope">{{ scope.row.name }}</template>
      </el-table-column>
      <el-table-column label="说明">
        <template slot-scope="scope">{{ scope.row.description }}</template>
      </el-table-column>
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          <el-button type="primary" size="mini" @click="handlePermission(scope.row)">授权</el-button>
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

    <!-- 添加或修改对话框 -->
    <el-dialog :title="dialogTitleMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="角色名称" prop="name">
          <el-input v-model="dataForm.name" />
        </el-form-item>
        <el-form-item label="说明" prop="desc">
          <el-input v-model="dataForm.description" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus==='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>

    <!-- 权限配置对话框-->
    <el-dialog :visible.sync="permissionDialogFormVisible" title="权限配置">
      <el-tree
        ref="tree"
        :data="permissionsData"
        :default-checked-keys="assignedPermissions"
        show-checkbox
        check-strictly
        node-key="id"
        highlight-current>
        <span slot-scope="{ node, data }" class="custom-tree-node">
          <span>{{ data.label }}</span>
          <el-tag v-if="data.permission" type="success" size="mini">{{ data.permission }}</el-tag>
          <el-tag type="success" size="mini">{{ data.id }}</el-tag>
          <el-tag v-if="data.type===1" type="error" size="mini">菜单</el-tag>
          <el-tag v-if="data.type===2" type="warning" size="mini">按钮/接口</el-tag>
        </span>
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="permissionDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="updatePermission">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { getList, createRole, updateRole, deleteRole, getPermission, updatePermission } from '@/api/role'
import waves from '@/directive/waves'
import Pagination from '@/components/Pagination'

export default {
  components: { Pagination },
  directives: { waves },
  filters: {},
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        name: undefined,
        page: 1,
        size: 5
      },
      dialogStatus: '',
      dialogFormVisible: false,
      dialogTitleMap: {
        update: '编辑',
        create: '创建'
      },
      dataForm: {
        id: undefined,
        name: undefined,
        description: undefined
      },
      rules: {
        name: [{ required: true, message: '角色名不能为空' }]
      },
      permissionDialogFormVisible: false,
      permissionsData: null,
      assignedPermissions: null,
      permissionForm: {
        roleId: undefined,
        permissions: []
      }
    }
  },
  computed: {
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
      this.dataForm = {
        id: undefined,
        name: undefined,
        description: undefined
      }
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleCreate() {
      this.dialogStatus = 'create'
      this.resetForm()
      this.dialogFormVisible = true
    },
    handleUpdate(row) {
      this.resetForm()
      this.dialogStatus = 'update'
      this.dataForm = Object.assign({}, row)
      this.dialogFormVisible = true
    },
    handleDelete(index, row) {
      this.$confirm('要删除[' + row.name + ']角色?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteRole(row.id).then(() => {
          this.$notify.success({
            title: '成功',
            message: '[' + row.name + ']角色删除成功'
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
    createData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          createRole(this.dataForm).then(response => {
            this.list.unshift(response.data)
            this.dialogFormVisible = false
            this.$notify.success({
              title: '成功',
              message: '添加角色成功'
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
    updateData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          updateRole(this.dataForm).then(() => {
            for (const v of this.list) {
              if (v.id === this.dataForm.id) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.dataForm)
                break
              }
            }
            this.dialogFormVisible = false
            this.$notify.success({
              title: '成功',
              message: '[' + this.adminInfo.username + ']角色更新成功'
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
    handlePermission(row) {
      this.permissionDialogFormVisible = true
      this.permissionForm.roleId = row.id
      getPermission(row.id).then(response => {
        this.permissionsData = response.data.permissions
        this.assignedPermissions = response.data.assignedPermissions
      })
    },
    updatePermission() {
      this.permissionForm.permissions = this.$refs.tree.getCheckedKeys()
      updatePermission(this.permissionForm).then(() => {
        this.permissionDialogFormVisible = false
        this.$notify.success({
          title: '成功',
          message: '授权成功'
        })
      }).catch(error => {
        this.$notify.error({
          title: '失败',
          message: error
        })
      })
    }
  }
}
</script>
