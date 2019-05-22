const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  token: state => state.auth.token,
  avatar: state => state.auth.avatar,
  name: state => state.auth.name,
  roles: state => state.auth.roles,
  perms: state => state.auth.perms,
  permission_routes: state => state.permission.routes
}
export default getters
