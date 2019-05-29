import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/admin',
    method: 'get',
    params
  })
}

export function getInfo(id) {
  return request({
    url: '/admin/' + id,
    method: 'get'
  })
}

export function createAdmin(data) {
  return request({
    url: '/admin',
    method: 'post',
    data: data
  })
}

export function updateAdmin(data) {
  return request({
    url: '/admin/' + data.id,
    method: 'put',
    data: data
  })
}

export function deleteAdmin(id) {
  return request({
    url: '/admin/' + id,
    method: 'delete'
  })
}
