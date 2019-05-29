import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/role',
    method: 'get',
    params
  })
}

export function createRole(data) {
  return request({
    url: '/role',
    method: 'post',
    data: data
  })
}

export function updateRole(data) {
  return request({
    url: '/role/' + data.id,
    method: 'put',
    data: data
  })
}

export function deleteRole(id) {
  return request({
    url: '/role/' + id,
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
    method: 'put',
    data: data.permissions
  })
}
