<template>
  <div class="app-container">
      <el-alert
      title="请输入作者名称查询论文关系网络并计算工作量分数"
      type="info"
      :closable="false"
      show-icon
      style="margin-bottom: 20px;"
    />
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="作者" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入作者"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 分析结果展示区域 -->
    <div v-if="showAnalysis" class="analysis-container">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-card class="box-card">
            <template #header>
              <div class="card-header">
                <span>合作关系图</span>
              </div>
            </template>
            <div id="coAuthorsChart" ref="coAuthorsChartRef" style="height: 400px;"></div>
          </el-card>
        </el-col>
        
        <el-col :span="12">
          <el-card class="box-card">
            <template #header>
              <div class="card-header">
                <span>工作量统计</span>
              </div>
            </template>
            <div class="workload-container">
              <div class="total-score">
                总分：<span class="score-number">{{ workloadData.totalScore }}</span>
              </div>
              <el-divider />
              <div class="score-grid">
                <div class="score-item">
                  <div class="category">一区论文</div>
                  <div class="count">{{ workloadData.q1Count }} 篇</div>
                  <div class="points">300分/篇</div>
                </div>
                <div class="score-item">
                  <div class="category">二区论文</div>
                  <div class="count">{{ workloadData.q2Count }} 篇</div>
                  <div class="points">150分/篇</div>
                </div>
                <div class="score-item">
                  <div class="category">三区论文</div>
                  <div class="count">{{ workloadData.q3Count }} 篇</div>
                  <div class="points">100分/篇</div>
                </div>
                <div class="score-item">
                  <div class="category">四区论文</div>
                  <div class="count">{{ workloadData.q4Count }} 篇</div>
                  <div class="points">60分/篇</div>
                </div>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="helpRef" :model="form" :rules="rules" label-width="80px">
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

<script setup name="Help">
import { getCurrentInstance, ref, reactive, toRefs, nextTick, onMounted, onUnmounted, watch } from 'vue'
import { listHelp, getHelp, delHelp, addHelp, updateHelp, getCoAuthors, getWorkload } from "@/api/help/help"
import * as echarts from 'echarts'

const { proxy } = getCurrentInstance()

const helpList = ref([])
const helpOptions = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const title = ref("")
const isExpandAll = ref(true)
const refreshTable = ref(true)
const showAnalysis = ref(false)
const coAuthorsChartRef = ref(null)
let chartInstance = null

// 工作量数据
const workloadData = ref({
  totalScore: 0,
  q1Count: 0,
  q2Count: 0,
  q3Count: 0,
  q4Count: 0
})

const data = reactive({
  form: {},
  queryParams: {
    author: null,
    journal: null,
  },
  rules: {
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询列表 */
function getList() {
  loading.value = true
  listHelp(queryParams.value).then(response => {
    helpList.value = proxy.handleTree(response.data, "papersId", "parentId")
    loading.value = false
    
    // 如果有作者查询，获取分析数据
    if (queryParams.value.author) {
      showAnalysis.value = true
      getAnalysisData()
    } else {
      showAnalysis.value = false
    }
  })
}

/** 获取分析数据 */
function getAnalysisData() {
  // 获取合作者数据
  getCoAuthors(queryParams.value.author).then(response => {
    console.log('后端返回的原始数据:', response)
    const coAuthorData = response.data.map(item => ({
      coAuthor: item.coAuthor || item.name,
      count: item.count || item.value
    }))
    
    nextTick(() => {
      initCoAuthorsChart(coAuthorData)
    })
  }).catch(error => {
    console.error('获取合作者数据失败:', error)
  })

  // 获取工作量数据
  getWorkload(queryParams.value.author).then(response => {
    workloadData.value = response.data
  }).catch(error => {
    console.error('获取工作量数据失败:', error)
  })
}

/** 初始化合作关系图表 */
function initCoAuthorsChart(data) {
  const chartDom = document.getElementById('coAuthorsChart')
  if (!chartDom) {
    console.error('找不到图表DOM元素')
    return
  }

  if (chartInstance) {
    chartInstance.dispose()
  }

  chartInstance = echarts.init(chartDom)
  console.log('图表实例:', chartInstance)

  const chartData = data.map(item => ({
    name: item.coAuthor,
    value: item.count
  }))
  console.log('处理后的图表数据:', chartData)

  const option = {
    title: {
      text: '合作关系网络',
      left: 'center'
    },
    tooltip: {
      trigger: 'item',
      formatter: '{b}: {c} 次合作'
    },
    legend: {
      orient: 'vertical',
      left: 'left',
      top: 'middle'
    },
    series: [
      {
        name: '合作次数',
        type: 'pie',
        radius: ['40%', '70%'],
        avoidLabelOverlap: true,
        itemStyle: {
          borderRadius: 10,
          borderColor: '#fff',
          borderWidth: 2
        },
        label: {
          show: true,
          formatter: '{b}\n{c}次'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: '18',
            fontWeight: 'bold'
          }
        },
        labelLine: {
          show: true
        },
        data: chartData
      }
    ]
  }

  chartInstance.setOption(option)
}

/** 处理窗口大小变化 */
function handleResize() {
  if (chartInstance) {
    chartInstance.resize()
  }
}

/** 取消按钮 */
function cancel() {
  open.value = false
  reset()
}

/** 表单重置 */
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
  proxy.resetForm("helpRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  showAnalysis.value = false
  handleQuery()
}

/** 新增按钮操作 */
function handleAdd(row) {
  reset()
  if (row != null && row.papersId) {
    form.value.parentId = row.papersId
  } else {
    form.value.parentId = 0
  }
  open.value = true
  title.value = "添加辅助功能"
}

/** 修改按钮操作 */
async function handleUpdate(row) {
  reset()
  if (row != null) {
    form.value.parentId = row.parentId
  }
  getHelp(row.papersId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改辅助功能"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["helpRef"].validate(valid => {
    if (valid) {
      if (form.value.papersId != null) {
        updateHelp(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addHelp(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除辅助功能编号为"' + row.papersId + '"的数据项？').then(function() {
    return delHelp(row.papersId)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

// 生命周期钩子
onMounted(() => {
  window.addEventListener('resize', handleResize)
  getList()
})

onUnmounted(() => {
  window.removeEventListener('resize', handleResize)
  if (chartInstance) {
    chartInstance.dispose()
  }
})

// 监听显示状态变化
watch(() => showAnalysis.value, (newVal) => {
  if (newVal) {
    nextTick(() => {
      if (chartInstance) {
        chartInstance.resize()
      }
    })
  }
})
</script>

<style scoped>
.analysis-container {
  margin-bottom: 20px;
}

.workload-container {
  padding: 20px;
}

.total-score {
  text-align: center;
  font-size: 18px;
  margin-bottom: 20px;
}

.score-number {
  color: #409EFF;
  font-size: 24px;
  font-weight: bold;
  margin-left: 8px;
}

.score-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.score-item {
  background-color: #f5f7fa;
  border-radius: 4px;
  padding: 15px;
  text-align: center;
}

.category {
  font-weight: bold;
  margin-bottom: 8px;
}

.count {
  color: #409EFF;
  font-size: 16px;
  margin-bottom: 4px;
}

.points {
  color: #909399;
  font-size: 12px;
}

#coAuthorsChart {
  width: 100%;
  height: 400px;
  margin: 0 auto;
}
</style>