import { createRouter, createWebHistory } from 'vue-router'
import App from '../App.vue'
import HomeView from '../views/HomeView.vue'
import FlotaView from '@/views/FlotaView.vue'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import ForgotPasswordView from '@/views/ForgotPasswordView.vue'
import MyRentalsView from '@/views/MyRentalsView.vue'
import UpdatePasswordView from '@/views/UpdatePasswordView.vue'
import CarDetailsView from '@/views/CarDetailsView.vue'
import ServicesView from '@/views/ServicesView.vue'
import LegalWarningView from '@/views/LegalWarningView.vue'
import PrivacyPolicyView from '@/views/PrivacyPolicyView.vue'
import CookiesPolicyView from '@/views/CookiesPolicyView.vue'

const routes = [
  { path: '/', component: HomeView },
  { path: '/login', component: LoginView },
  { path: '/registro', component: RegisterView },
  { path: '/forgotpass', component: ForgotPasswordView },
  { path: '/updatepass', component: UpdatePasswordView },
  { path: '/flota', component: FlotaView },
  { path: '/cardetail/:id', component: CarDetailsView },
  { path: '/myrentals', component: MyRentalsView, meta: { requiresAuth: true } }, // Ruta protegida
  { path: '/services', component: ServicesView },
  { path: '/legal', component: LegalWarningView },
  { path: '/privacy', component: PrivacyPolicyView },
  { path: '/cookies', component: CookiesPolicyView },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
