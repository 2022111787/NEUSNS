import request from '@/utils/request'

// 查询内推管理列表
export function getUnreviewedReferrals(query) {
  return request({
    url: '/api/admin/managerReferral/managerUnreviewedReferral/list',
    method: 'get',
    params: query
  })
}

// 查询内推管理详细
export function  getUnreviewedReferral(referralID) {
  return request({
    url: '/api/admin/managerReferral/managerUnreviewedReferral/' + referralID,
    method: 'get'
  })
}

// // 新增内推管理
// export function addReferral(data) {
//   return request({
//     url: '/managerReferral/managerUnreviewedReferral',
//     method: 'post',
//     data: data
//   })
// }



// 删除内推管理
export function deleteUnreviewedReferral(referralID) {
  return request({
    url: '/api/admin/managerReferral/managerUnreviewedReferral/' + referralID,
    method: 'delete'
  })
}

export function updateReferral(data) {
  return request({
    url: '/api/admin/managerReferral/managerUnreviewedReferral',
    method: 'put',
    data: data
  })
}
