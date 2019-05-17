import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/log/list',
    method: 'get',
    params
  })
}
