import request from '@/utils/request'

// 查询辅助功能列表
export function listHelp(query) {
  return request({
    url: '/help/help/list',
    method: 'get',
    params: query
  })
}

// 查询辅助功能详细
export function getHelp(papersId) {
  return request({
    url: '/help/help/' + papersId,
    method: 'get'
  })
}

// 新增辅助功能
export function addHelp(data) {
  return request({
    url: '/help/help',
    method: 'post',
    data: data
  })
}

// 修改辅助功能
export function updateHelp(data) {
  return request({
    url: '/help/help',
    method: 'put',
    data: data
  })
}

// 删除辅助功能
export function delHelp(papersId) {
  return request({
    url: '/help/help/' + papersId,
    method: 'delete'
  })
}

// 获取合作者关系
export function getCoAuthors(authorName) {
  return request({
    url: '/help/help/coAuthors/' + authorName,
    method: 'get'
  })
}

// 获取工作量统计
export function getWorkload(authorName) {
  return request({
    url: '/help/help/workload/' + authorName,
    method: 'get'
  })
}