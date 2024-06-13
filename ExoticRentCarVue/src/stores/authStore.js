// En un archivo como authStore.js
import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    isAuthenticated: false,
    username: '',
    password: '',
  }),
  actions: {
    login({ username, password }) {
      // Realiza la lógica de autenticación aquí
      // ...

      // Actualiza el estado si la autenticación es exitosa
      this.isAuthenticated = true;
      this.username = username;
      this.password = password;
    },
    logout() {
      // Realiza la lógica de cierre de sesión aquí
      // ...

      // Restablece el estado al cerrar sesión
      this.isAuthenticated = false;
      this.username = '';
      this.password = '';
    },
  },
});
