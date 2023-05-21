import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/admin',
    name: 'home',
    component: HomeView
  },
  {
    path: '/admin/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },

  {
    path: '/:pathMatch(.*)*',
    name: 'PageNotFound',
    component: () => import('../components/PageNotFound.vue')
  },
]

const router = createRouter({
  base: "/admin",
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
