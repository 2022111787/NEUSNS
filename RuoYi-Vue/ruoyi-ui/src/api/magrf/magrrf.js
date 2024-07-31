import request from '@/utils/request'

// 查询内推管理列表
export function getReviewedReferrals(query) {
  return request({
    url: '/api/admin/managerReferral/managerReviewedReferral/list',
    method: 'get',
    params: query
  })
}

export function searchReviewedReferral(query){
  return request({
    url:'/api/admin/managerReferral/managerReviewedReferral',
    method:"get",
    params:query
  })
}

// 查询内推管理详细
export function getReviewedReferral(referralID) {
  return request({
    url: '/api/admin/managerReferral/managerReviewedReferral/' + referralID,
    method: 'get'
  })
}


// 删除内推管理
export function deleteReviewedReferral(referralID) {
  return request({
    url: '/api/admin/managerReferral/managerReviewedReferral/' + referralID,
    method: 'delete'
  })
}
