<script setup>
import { useAuthStore } from '@/stores/authStore.js';
import { computed } from 'vue';

const authStore = useAuthStore();

const isAuthenticated = computed(() => {
    return authStore.username !== '' && authStore.password !== '';
});

const handleLogout = () => {
    authStore.logout();
};
</script>

<template>
    <nav class="desktop fixed-top">
        <div class="d-flex align-items-center justify-content-center">
            <div class="logo">
                <img src="../assets/logo-sinfondo-blanco.png" alt="Logo ExoticRentCar">
            </div>
            <ul class="d-flex justify-content-center align-items-center">
                <li>
                    <router-link to="/" class="nav-link" :class="{ 'active': $route.path === '/' }">INICIO</router-link>
                </li>
                <li>
                    <router-link to="/flota" class="nav-link"
                        :class="{ 'active': $route.path === '/flota' }">FLOTA</router-link>
                </li>
                <li>
                    <router-link to="/services" class="nav-link"
                        :class="{ 'active': $route.path === '/services' }">SERVICIOS</router-link>
                </li>
                <div>
                    <div v-if="!isAuthenticated">
                        <!-- Contenido para usuarios no autenticados -->
                        <div class="credenciales d-flex justify-content-center align-items-center">
                            <i class="bi bi-people-fill fs-4"></i>
                            <li>
                                <router-link to="/login" class="nav-link"
                                    :class="{ 'active': $route.path === '/login' }">Iniciar Sesión</router-link>
                            </li>
                            <li>
                                <router-link to="/registro" class="nav-link"
                                    :class="{ 'active': $route.path === '/registro' }">Registrarse</router-link>
                            </li>
                        </div>
                    </div>
                    <div v-else class="mx-5">
                        <li class="nav-item dropdown">
                            <div class="" role="button" id="navbarDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="bi bi-person-circle fs-1"></i>
                            </div>
                            <ul class="dropdown-menu text-center px-2" aria-labelledby="navbarDropdown">
                                <router-link to="/myrentals" class="dropdown-item">Mis alquileres</router-link><hr>
                                <router-link to="/updatepass" class="dropdown-item">Actualizar contraseña</router-link><hr>
                                <li><button @click="handleLogout" class="btn btn-danger">Logout</button></li>
                            </ul>
                        </li>
                    </div>
                </div>
            </ul>
        </div>
    </nav>
    <!-- NavBar mobile -->
    <nav class="mobile navbar navbar-expand-lg navbar-dark fixed-top">
        <div class="container-fluid">
            <router-link to="/" class="navbar-brand">
                <img src="../assets/logo-sinfondo-blanco.png" alt="Logo ExoticRentCar">
            </router-link>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <router-link to="/" class="nav-link" :class="{ 'active': $route.path === '/' }">INICIO</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link to="/flota" class="nav-link" :class="{ 'active': $route.path === '/flota' }">FLOTA</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link to="/services" class="nav-link" :class="{ 'active': $route.path === '/services' }">SERVICIOS</router-link>
                    </li><hr>
                    <li v-if="!isAuthenticated" class="nav-item">
                        <router-link to="/login" class="nav-link">Iniciar Sesión</router-link>
                    </li>
                    <li v-if="!isAuthenticated" class="nav-item">
                        <router-link to="/registro" class="nav-link">Registrarse</router-link>
                    </li>
                    <li v-if="isAuthenticated" class="nav-item">
                        <router-link to="/myrentals" class="nav-link">Mis alquileres</router-link>
                    </li>
                    <li v-if="isAuthenticated" class="nav-item">
                        <router-link to="/updatepass" class="nav-link">Actualizar contraseña</router-link>
                    </li>
                    <li v-if="isAuthenticated" class="nav-item">
                        <button @click="handleLogout" class="nav-link text-bg-danger">Logout</button>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<style scoped>
nav {
    background-color: rgba(0, 0, 0, 0.8);
    color: white;
    font-weight: 600;
    font-style: normal;
}

.credenciales .nav-link {
    white-space: nowrap;
    /* Evita que el texto se divida en varias líneas */
}

.credenciales {
    margin-left: 40%;
}

ul {
    list-style: none;
    padding-top: 1rem;
    margin-left: 4%;
}

ul>li {
    padding: 3%;
}

img {
    width: 7rem;
    max-width: 200px;
}

.nav-link {
    position: relative;
    text-decoration: none;
    padding: 10px;
}

.nav-link::before,
.nav-link::after {
    content: '';
    position: absolute;
    width: 0;
    height: 2px;
    bottom: 0;
    background-color: #efb810;
    transition: width 0.3s;
}

.nav-link::before {
    left: 0;
}

.nav-link::after {
    right: 0;
}

.nav-link:hover::before,
.nav-link:hover::after {
    width: 50%;
    /* Ancho completo al pasar el ratón sobre el enlace */
}

.mobile {
    display: none;
}

@media (max-width: 768px) {
    .mobile {
        display: block;
    }

    .desktop {
        display: none;
    }

    nav {
        background-color: black;
    }
}

</style>