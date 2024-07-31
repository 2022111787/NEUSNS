import request from '@/utils/request';

// 查询已审核博文列表
export function listManagerReviewedPost(query) {
  return request({
    url: '/api/admin/managerPost/managerReviewedPost',
    method: 'get',
    params: query
  });
}

// 查询已审核博文详细
export function getManagerReviewedPost(postID) {
  return request({
    url: '/api/admin/managerPost/managerReviewedPost/' + postID,
    method: 'get'
  });
}



// 删除已审核博文
export function delManagerReviewedPost(postID) {
  return request({
    url: '/api/admin/managerPost/managerReviewedPost/' + postID ,
    method: 'delete'
  });
}

//查询已审核博文的评论
export function getcommentlist(postID){
  return request({
    url:'/api/admin/managerPost/managerReviewedPost/' + postID + '/commentlist',
    method:'get'
  });
}
