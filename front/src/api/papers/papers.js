import request from '@/utils/request'

// 查询论文管理列表
export function listPapers(query) {
  return request({
    url: '/papers/papers/list',
    method: 'get',
    params: query
  })
}

// 查询论文管理详细
export function getPapers(papersId) {
  return request({
    url: '/papers/papers/' + papersId,
    method: 'get'
  })
}

// 新增论文管理
export function addPapers(data) {
  return request({
    url: '/papers/papers',
    method: 'post',
    data: data
  })
}

// 修改论文管理
export function updatePapers(data) {
  return request({
    url: '/papers/papers',
    method: 'put',
    data: data
  })
}

// 删除论文管理
export function delPapers(papersId) {
  return request({
    url: '/papers/papers/' + papersId,
    method: 'delete'
  })
}
