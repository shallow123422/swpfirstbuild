import request from '@/utils/request'

// 查询论文查询列表
export function listPapersfound(query) {
  return request({
    url: '/papersfound/papersfound/list',
    method: 'get',
    params: query
  })
}

// 查询论文查询详细
export function getPapersfound(papersId) {
  return request({
    url: '/papersfound/papersfound/' + papersId,
    method: 'get'
  })
}

// 新增论文查询
export function addPapersfound(data) {
  return request({
    url: '/papersfound/papersfound',
    method: 'post',
    data: data
  })
}

// 修改论文查询
export function updatePapersfound(data) {
  return request({
    url: '/papersfound/papersfound',
    method: 'put',
    data: data
  })
}

// 删除论文查询
export function delPapersfound(papersId) {
  return request({
    url: '/papersfound/papersfound/' + papersId,
    method: 'delete'
  })
}
