import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/admin/list',
    method: 'get',
    params
  })
}

export function getInfo(id) {
  return request({
    url: '/admin/read/' + id,
    method: 'get'
  })
}

export function createAdmin(data) {
  return request({
    url: '/admin/create',
    method: 'put',
    data: data
  })
}

export function updateAdmin(data) {
  return request({
    url: '/admin/update/' + data.id,
    method: 'post',
    data: data
  })
}

export function deleteAdmin(id) {
  return request({
    url: '/admin/delete/' + id,
    method: 'delete'
  })
}
