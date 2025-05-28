<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文献分类" prop="literaturesCategory">
        <el-input
          v-model="queryParams.literaturesCategory"
          placeholder="请输入文献分类"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文献名称" prop="literaturesName">
        <el-input
          v-model="queryParams.literaturesName"
          placeholder="请输入文献名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入作者"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关键字" prop="keywords">
        <el-input
          v-model="queryParams.keywords"
          placeholder="请输入关键字"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传时间" prop="uploadTime">
        <el-date-picker clearable
          v-model="queryParams.uploadTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="选择上传时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="期刊" prop="journal">
        <el-input
          v-model="queryParams.journal"
          placeholder="请输入期刊"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['literatures:literatures:add']"
        >添加参考文献</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="Sort"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="download"
          size = "mini"
          @click="handleExport"
          v-hasPermi="['literatures:literatures:export']"
        >导出excel</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="literaturesList"
      row-key="literaturesId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="文献分类" align="center" prop="literaturesCategory" />
      <el-table-column label="文献名称" align="center" prop="literaturesName" />
      <el-table-column label="作者" align="center" prop="author" />
      <el-table-column label="关键字" align="center" prop="keywords" />
      <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="期刊" align="center" prop="journal" />
      <el-table-column label="文件名" align="center" prop="fileName" />
      <el-table-column label="文件" align="center" prop="fileRoad">
        <template #default="scope">
          <el-button
            v-if="scope.row.fileRoad" 
            size="mini"
            type="text"
            @click="$download.resource(scope.row.fileRoad,false)"
          >
          下载
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['literatures:literatures:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['literatures:literatures:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改参考文献对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="literaturesRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类别" prop="parentId">
          <el-tree-select
            v-model="form.parentId"
            :data="literaturesOptions"
            :props="{ value: 'literaturesId', label: 'literaturesCategory', children: 'children' }"
            value-key="literaturesId"
            placeholder="请选择父文献id"
            check-strictly
          />
        </el-form-item>
        <el-form-item label="文献分类" prop="literaturesCategory">
          <el-input v-model="form.literaturesCategory" placeholder="请输入文献分类" />
        </el-form-item>
        <el-form-item label="文献名称" prop="literaturesName">
          <el-input v-model="form.literaturesName" placeholder="请输入文献名称" />
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者" />
        </el-form-item>
        <el-form-item label="关键字" prop="keywords">
          <el-input v-model="form.keywords" placeholder="请输入关键字" />
        </el-form-item>
        <el-form-item label="上传时间" prop="uploadTime">
          <el-date-picker clearable
            v-model="form.uploadTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="选择上传时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="期刊" prop="journal">
          <el-input v-model="form.journal" placeholder="请输入期刊" />
        </el-form-item>
        <el-form-item label="文件名" prop="fileName">
          <el-input v-model="form.fileName" placeholder="请输入文件名" />
        </el-form-item>
        <el-form-item label="文件" prop="fileRoad">
          <file-upload v-model="form.fileRoad"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Literatures">
import { listLiteratures, getLiteratures, delLiteratures, addLiteratures, updateLiteratures } from "@/api/literatures/literatures"

const { proxy } = getCurrentInstance()

const literaturesList = ref([])
const literaturesOptions = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const title = ref("")
const isExpandAll = ref(true)
const refreshTable = ref(true)

const data = reactive({
  form: {},
  queryParams: {
    literaturesCategory: null,
    literaturesName: null,
    author: null,
    keywords: null,
    uploadTime: null,
    journal: null,
  },
  rules: {
    literaturesCategory: [
      { required: true, message: "文献分类不能为空", trigger: "blur" }
    ],
    literaturesName: [
      { required: true, message: "文献名称不能为空", trigger: "blur" }
    ],
    author: [
      { required: true, message: "作者不能为空", trigger: "blur" }
    ],
    keywords: [
      { required: true, message: "关键字不能为空", trigger: "blur" }
    ],
    journal: [
      { required: true, message: "期刊不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询参考文献列表 */
function getList() {
  loading.value = true
  listLiteratures(queryParams.value).then(response => {
    literaturesList.value = proxy.handleTree(response.data, "literaturesId", "parentId")
    loading.value = false
  })
}

/** 查询参考文献下拉树结构 */
function getTreeselect() {
  listLiteratures().then(response => {
    literaturesOptions.value = []
    const data = { literaturesId: 0, literaturesCategory: '选择', children: [] }
    data.children = proxy.handleTree(response.data, "literaturesId", "parentId")
    literaturesOptions.value.push(data)
  })
}
	
// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    literaturesId: null,
    parentId: null,
    ancestors: null,
    literaturesCategory: null,
    literaturesName: null,
    author: null,
    keywords: null,
    uploadTime: null,
    journal: null,
    fileName: null,
    fileRoad: null,
    deptId: null,
    userId: null
  }
  proxy.resetForm("literaturesRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

/** 新增按钮操作 */
function handleAdd(row) {
  reset()
  getTreeselect()
  if (row != null && row.literaturesId) {
    form.value.parentId = row.literaturesId
  } else {
    form.value.parentId = 0
  }
  open.value = true
  title.value = "添加参考文献"
}

/** 展开/折叠操作 */
function toggleExpandAll() {
  refreshTable.value = false
  isExpandAll.value = !isExpandAll.value
  nextTick(() => {
    refreshTable.value = true
  })
}

/** 修改按钮操作 */
async function handleUpdate(row) {
  reset()
  await getTreeselect()
  if (row != null) {
    form.value.parentId = row.parentId
  }
  getLiteratures(row.literaturesId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改参考文献"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["literaturesRef"].validate(valid => {
    if (valid) {
      if (form.value.literaturesId != null) {
        updateLiteratures(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addLiteratures(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  proxy.$modal.confirm('是否确认删除').then(function() {
    return delLiteratures(row.literaturesId)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

function handleExport() {
  proxy.download(
    '/literatures/literatures/export',
    {
      ...queryParams
    },
    `参考文献列表_${new Date().toISOString().slice(0, 10)}.xlsx`
  )
}

getList()
</script>
