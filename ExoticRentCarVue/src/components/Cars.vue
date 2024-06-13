<script setup>
import { ref, onMounted, computed, watchEffect } from 'vue';
import repository from '@/service/repository';

const cars = ref([]);
const selectedBrand = ref('');
const carsFiltered = ref([]);

const fetchCars = async () => {
    try {
        cars.value = await repository.cars.getAll();
        cars.value = cars.value.data;
    } catch (error) {
        console.log(error);
    }
}

const fetchCarByBrand = async () => {
    try {
        if (selectedBrand.value != '') {
            const response = await repository.cars.getByBrand(selectedBrand.value);
            carsFiltered.value = response.data;
        }
    } catch (error) {
        console.log(error);
    }
}

const carBrands = computed(() => cars.value.map(car => car.brand));
// Utilizamos un Set para obtener marcas únicas
const uniqueCarBrands = computed(() => Array.from(new Set(carBrands.value)));

const showAllCars = () => {
    carsFiltered.value = [];
    selectedBrand.value = ''
};

onMounted(() => {
    fetchCars();
});

watchEffect(() => {
    fetchCarByBrand();
});
</script>

<template>
    <div class="d-flex justify-content-center align-items-center flex-column mb-5">
        <div class="mb-4">
            <h1>Flota ExoticRentCar</h1>
        </div>
        <div class="text-center">
            <label class="mx-2" for="carBrand">Selecciona una marca</label>
            <select id="carBrand" class="custom-select mb-3" v-model="selectedBrand">
                <option v-for="brand in uniqueCarBrands" :key="brand" :value="brand">{{ brand }}</option>
            </select>
            <button @click="showAllCars" class="btn mx-2">Mostrar Todos</button>
        </div>
    </div>
    <div class="container">
        <div class="row d-flex flex-wrap row-gap-5">
            <router-link v-for="car in (carsFiltered.length > 0 ? carsFiltered : cars)" :to="`/cardetail/${car.id}`" :key="car.id" class="col-md-4" tag="div">
                <div class="car">
                    <div>
                        <img :src="`/assets/${car.image}`" alt="Car Image" class="card-img-top">
                    </div>
                    <div class="card-body">
                        <h4 class="px-1 mt-3">{{ car.brand }} {{ car.model }}</h4>
                    </div>
                    <div class="details text-center">
                        <div class="row">
                            <i class="bi bi-person col-md-6"></i>
                            <i class="bi bi-airplane-fill col-md-6"></i>
                            <p class="col-md-6">{{ car.ability }}</p>
                            <p class="col-md-6">{{ car.cv }}</p>
                        </div>
                    </div>
                    <div class="price">
                        <p>{{ car.pricePerDay }}€/día</p>
                    </div>
                </div>
            </router-link>
        </div>
    </div>
</template>

<style scoped>
h1 {
    font-weight: 700;
    word-spacing: 10px;
    letter-spacing: 5px;
}

img {
    width: 100%;
    height: 255px;
}

.car {
    position: relative;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.4);
    border-radius: 2%;
    overflow: hidden;
    display: flex;
    flex-direction: column;
    height: 100%;
}

.card-body {
    width: 100%;
    position: absolute;
    top: 12%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
    color: white;
}

h4 {
    font-weight: 600;
}

.details {
    width: 30%;
    position: absolute;
    top: 80%;
    color: white;
}

.price {
    width: 40%;
    position: absolute;
    top: 87%;
    left: 70%;
    color: white;
    font-size: 1.3em;
    font-weight: bold;
}

button {
    background-color:  rgba(239, 184, 16, 0.7);
    color: white;
    font-weight: 600;
}

button:hover {
    background-color:  rgba(239, 184, 16, 0.7);
    color: white;
    font-weight: 600;
}
</style>