import request from '@/utils/request'

// 查询参考文献列表
export function listLiteratures(query) {
  return request({
    url: '/literatures/literatures/list',
    method: 'get',
    params: query
  })
}

// 查询参考文献详细
export function getLiteratures(literaturesId) {
  return request({
    url: '/literatures/literatures/' + literaturesId,
    method: 'get'
  })
}

// 新增参考文献
export function addLiteratures(data) {
  return request({
    url: '/literatures/literatures',
    method: 'post',
    data: data
  })
}

// 修改参考文献
export function updateLiteratures(data) {
  return request({
    url: '/literatures/literatures',
    method: 'put',
    data: data
  })
}

// 删除参考文献
export function delLiteratures(literaturesId) {
  return request({
    url: '/literatures/literatures/' + literaturesId,
    method: 'delete'
  })
}

// 导出论文查询
export function exportLiteratures(query) {
  return request({
    url: '/literatures/literatures/export',
    method: 'get',
    responseType: 'blob',
    params: query
  })
}