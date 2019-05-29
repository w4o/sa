import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/log',
    method: 'get',
    params
  })
}
