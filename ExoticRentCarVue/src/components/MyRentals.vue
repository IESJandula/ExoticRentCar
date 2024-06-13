<script setup>
import repository from '@/service/repository';
import { useAuthStore } from '@/stores/authStore.js';
import { ref, onMounted, computed } from 'vue';

const authStore = useAuthStore();

const rentals = ref([]);

const username = computed(() => authStore.username);

const formatDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    
    return `${year}-${month}-${day} - ${hours}:${minutes}:${seconds}`;
};

onMounted(async () => {
    try {
        const response = await repository.myRentals.getMyRentals(authStore.username, authStore.password);
        const sortedRentals = response.data.sort((a, b) => {
            // Convertir las fechas a objetos Date para compararlas
            const dateA = new Date(a.dateTimeRental);
            const dateB = new Date(b.dateTimeRental);
            // Ordenar en orden descendente (de la fecha más reciente a la más antigua)
            return dateB - dateA;
        });
        rentals.value = sortedRentals;
    } catch (error) {
        console.error('Error al obtener alquileres:', error.response.data);
    }
});
</script>

<template>
    <div class="text-center">
        <h1>¡Bienvenido, {{ username }}!</h1>
        <h3>Aquí tienes todos tus alquileres en ExoticRentCar <i class="bi bi-arrow-down"></i></h3>
    </div>
    <div class="container text-center mt-5">
        <table v-if="rentals.length > 0" class="table">
            <thead>
                <tr>
                    <th>Marca y Modelo</th>
                    <th>Fecha de Inicio</th>
                    <th>Fecha de Fin</th>
                    <th>Precio de Alquiler</th>
                    <th>Fecha de alquiler</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="rental in rentals" :key="rental.id">
                    <td>{{ rental.car.brand }} {{ rental.car.model }}</td>
                    <td>{{ rental.startDate }}</td>
                    <td>{{ rental.endDate }}</td>
                    <td>{{ rental.rentalPrice }}€</td>
                    <td>{{ formatDate(rental.dateTimeRental) }}</td>
                </tr>
            </tbody>
        </table>
        <p class="fs-5" v-else>Hasta este momento no has realizado ningún alquiler <i class="bi bi-emoji-frown"></i></p>
    </div>
</template>

<style scoped>
h1 {
    font-weight: bold;
}
</style>