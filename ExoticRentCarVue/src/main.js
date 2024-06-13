/* bootstrap */
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { useAuthStore } from '@/stores/authStore.js';
import App from './App.vue'
import router from './router'

const app = createApp(App)

// Crea una instancia de Pinia y úsala en la aplicación
const pinia = createPinia()
app.use(pinia)

// Configura la navegación de guardia global
router.beforeEach((to, from, next) => {
    const authStore = useAuthStore() // Usa el hook para obtener el store
    if (to.matched.some(record => record.meta.requiresAuth)) {
      // Verificar si el usuario está autenticado
      if (!authStore.isAuthenticated) {
        // Si no está autenticado, redirigir al inicio de sesión
        next('/login');
      } else {
        // Si está autenticado, permitir el acceso
        next();
      }
    } else {
      // Rutas públicas, permitir el acceso sin restricciones
      next();
    }
});

app.use(router)
app.mount('#app')
