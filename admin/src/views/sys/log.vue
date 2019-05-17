<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="正在查询。。。"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="操作管理员" prop="admin"/>
      <el-table-column align="center" label="操作时间">
        <template slot-scope="scope">
          {{scope.row.createTime | parseTime('{y}-{m}-{d} {h}:{i}') }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="IP地址" prop="ip"/>
      <el-table-column align="center" label="操作类别" prop="type">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.type| typeFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作动作" prop="action"/>
      <el-table-column align="center" label="操作结果" prop="result"/>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.size"
      :page-sizes="[10,20,50]"
      @pagination="fetchData"
    />
  </div>
</template>

<script>
import { getList } from '@/api/log'
import Pagination from '@/components/Pagination'
import { parseTime } from '@/utils'

export default {
  components: { Pagination },
  filters: {
    parseTime,
    typeFilter(type) {
      const typeMap = {
        0: '一般操作',
        1: '安全操作',
        9: '其他操作'
      }
      return typeMap[type]
    }
  },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        size: 10
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
    }
  }
}
</script>
