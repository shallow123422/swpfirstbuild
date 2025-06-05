<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="论文分类" prop="papersCategory">
        <el-input
          v-model="queryParams.papersCategory"
          placeholder="请输入论文分类"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="论文名称" prop="papersName">
        <el-input
          v-model="queryParams.papersName"
          placeholder="请输入论文名称"
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
          v-hasPermi="['paperspublish:paperspublish:export']"
        >导出excel</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="paperspublishList"
      row-key="papersId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="论文分类" align="center" prop="papersCategory" />
      <el-table-column label="论文名称" align="center" prop="papersName" />
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
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['paperspublish:paperspublish:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['paperspublish:paperspublish:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改我的论文对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="paperspublishRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="论文分类" prop="papersCategory">
          <el-input v-model="form.papersCategory" placeholder="请输入论文分类" />
        </el-form-item>
        <el-form-item label="论文名称" prop="papersName">
          <el-input v-model="form.papersName" placeholder="请输入论文名称" />
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
        <el-form-item label="文件路径" prop="fileRoad">
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

<script setup name="Paperspublish">
import { 
  listPaperspublish, 
  getPaperspublish, 
  delPaperspublish, 
  addPaperspublish, 
  updatePaperspublish,
  exportPaperspublish 
} from "@/api/paperspublish/paperspublish"

const { proxy } = getCurrentInstance()

const paperspublishList = ref([])
const paperspublishOptions = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const title = ref("")
const isExpandAll = ref(true)
const refreshTable = ref(true)

const data = reactive({
  form: {},
  queryParams: {
    papersCategory: null,
    papersName: null,
    author: null,
    keywords: null,
    uploadTime: null,
    journal: null,
  },
  rules: {
    papersName: [
      { required: true, message: "论文名称不能为空", trigger: "blur" }
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

/** 查询我的论文列表 */
function getList() {
  loading.value = true
  listPaperspublish(queryParams.value).then(response => {
    paperspublishList.value = proxy.handleTree(response.data, "papersId", "parentId")
    loading.value = false
  })
}

/** 查询我的论文下拉树结构 */
function getTreeselect() {
  listPaperspublish().then(response => {
    paperspublishOptions.value = []
    const data = { papersId: 0, papersCategory: '选择', children: [] }
    data.children = proxy.handleTree(response.data, "papersId", "parentId")
    paperspublishOptions.value.push(data)
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
    papersId: null,
    parentId: null,
    ancestors: null,
    papersCategory: null,
    papersName: null,
    author: null,
    keywords: null,
    uploadTime: null,
    journal: null,
    fileName: null,
    fileRoad: null,
    deptId: null,
    userId: null
  }
  proxy.resetForm("paperspublishRef")
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
  if (row != null && row.papersId) {
    form.value.parentId = row.papersId
  } else {
    form.value.parentId = 0
  }
  open.value = true
  title.value = "发表论文"
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
  getPaperspublish(row.papersId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改论文"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["paperspublishRef"].validate(valid => {
    if (valid) {
      if (form.value.papersId != null) {
        updatePaperspublish(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addPaperspublish(form.value).then(response => {
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
    return delPaperspublish(row.papersId)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.$modal.confirm('是否确认导出数据项?').then(() => {
    proxy.$modal.loading("正在导出数据，请稍候...");
    // 传递当前的查询参数
    exportPaperspublish(queryParams.value).then(response => {
      if (response.type === 'application/json') {
        // 如果返回的是json，说明可能是错误信息
        const reader = new FileReader();
        reader.onload = e => {
          const error = JSON.parse(e.target.result);
          proxy.$modal.msgError(error.msg || '导出失败');
        };
        reader.readAsText(response);
      } else {
        // 正常的excel文件下载
        const blob = new Blob([response], { type: 'application/vnd.ms-excel' });
        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = '我的论文数据.xlsx';
        link.click();
        window.URL.revokeObjectURL(link.href);
      }
      proxy.$modal.closeLoading();
    }).catch((error) => {
      console.error('Export error:', error);
      proxy.$modal.msgError(error.message || '导出失败，请检查网络或联系管理员');
      proxy.$modal.closeLoading();
    });
  });
}
getList()
</script>
