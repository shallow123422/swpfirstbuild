import request from '@/utils/request'

// 查询我的论文列表
export function listPaperspublish(query) {
  return request({
    url: '/paperspublish/paperspublish/list',
    method: 'get',
    params: query
  })
}

// 查询我的论文详细
export function getPaperspublish(papersId) {
  return request({
    url: '/paperspublish/paperspublish/' + papersId,
    method: 'get'
  })
}

// 新增我的论文
export function addPaperspublish(data) {
  return request({
    url: '/paperspublish/paperspublish',
    method: 'post',
    data: data
  })
}

// 修改我的论文
export function updatePaperspublish(data) {
  return request({
    url: '/paperspublish/paperspublish',
    method: 'put',
    data: data
  })
}

// 删除我的论文
export function delPaperspublish(papersId) {
  return request({
    url: '/paperspublish/paperspublish/' + papersId,
    method: 'delete'
  })
}

// 导出论文查询
export function exportPaperspublish(query) {
  return request({
    url: '/paperspublish/paperspublish/export',
    method: 'get',
    responseType: 'blob',
    params: query
  })
}