import request from '@/utils/request'

// 查询待审核博客列表
export function listManagerUnreviewedPost(query) {
  return request({
    url: '/api/admin/managerPost/managerUnreviewedPost/',
    method: 'get',
    params: query
  })
}

// 获取待审核博客详情
export function getManagerUnreviewedPost(postID) {
  return request({
    url: `/api/admin/managerPost/managerUnreviewedPost/${postID}`,
    method: 'get'
  })
}

// 删除待审核博客
export function delManagerUnreviewedPost(postIDs) {
  return request({
    url: `/api/admin/managerPost/managerUnreviewedPost/${postIDs}`,
    method: 'delete'
  })
}

// 更新待审核博客
export function updateManagerUnreviewedPost(data) {
  return request({
    url: '/api/admin/managerPost/managerUnreviewedPost',
    method: 'put',
    data: data
  })
}
