import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/role/list',
    method: 'get',
    params
  })
}

export function createRole(data) {
  return request({
    url: '/role/create',
    method: 'put',
    data: data
  })
}

export function updateRole(data) {
  return request({
    url: '/role/update/' + data.id,
    method: 'post',
    data: data
  })
}

export function deleteRole(id) {
  return request({
    url: '/role/delete/' + id,
    method: 'delete'
  })
}

export function getPermission(id) {
  return request({
    url: '/role/' + id + '/permissions',
    method: 'get'
  })
}

export function updatePermission(data) {
  return request({
    url: 'role/' + data.roleId + '/permissions',
    method: 'post',
    data: data.permissions
  })
}
