<script setup>
import repository from '@/service/repository';
import { useAuthStore } from '@/stores/authStore.js';
import { ref, onMounted, watch, computed } from 'vue';
import { useRouter } from 'vue-router';

const authStore = useAuthStore();
const router = useRouter();

const startDate = ref();
const endDate = ref();

const available = ref();
const priceCalculated = ref(null);
const car = ref(null);
const carId = router.currentRoute.value.params.id;

const minStartDate = computed(() => {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
});

const minEndDate = computed(() => {
    const today = new Date();
    today.setDate(today.getDate() + 1);
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
});

const fetchCarDetails = async () => {
    try {
        const response = await repository.cars.getById(carId);
        car.value = response.data;
    } catch (error) {
        console.error('Error al obtener los detalles del automóvil:', error);
    }
};

const checkAvailability = async () => {
    try {
        const response = await repository.cars.isCarAvailable(carId, startDate.value, endDate.value);
        if (response.status === 200) {
            available.value = true;
        } else if (response.status === 204) {
            available.value = false;
        }
    } catch (error) {
        console.error('Error al comprobar disponibilidad:', error);
    }
};

const calculatePrice = () => {
    if (startDate.value && endDate.value && car.value) {
        const start = new Date(startDate.value);
        const end = new Date(endDate.value);
        const differenceInTime = end.getTime() - start.getTime();
        const differenceInDays = differenceInTime / (1000 * 3600 * 24);
        priceCalculated.value = differenceInDays * car.value.pricePerDay;
    }
};

const handleRent = async () => {
    if (authStore.isAuthenticated) {
        try {
            const rentData = {
                username: authStore.username,
                car: carId,
                startDate: startDate.value,
                endDate: endDate.value
            };

            const response = await repository.myRentals.createRent(authStore.username, authStore.password, rentData);
            router.push("/myrentals");
        } catch (error) {
            console.error('Error al alquilar el coche:', error);
        }
    } else {
        router.push("/login");
    }
};

onMounted(fetchCarDetails);
watch([startDate, endDate, car], calculatePrice);
</script>

<template>
    <div class="container d-flex justify-content-center align-items-center mobile" v-if="car">
        <div class="w-75 img mobile-w100">
            <img class="mb-3" :src="`/assets/${car.image}`" alt="Car Image">
            <h1 class="text-center">{{ car.brand }} {{ car.model }}</h1>
            <div class="details d-flex justify-content-center align-items-center mt-4 fs-5">
                <div>
                    <i class="bi bi-person-fill px-1"></i>
                    <p>{{ car.ability }}</p>
                </div>
                <div>
                    <i class="bi bi-lightning-fill"></i>
                    <p>{{ car.cv }}CV</p>
                </div>
                <div>
                    <i class="bi bi-gear-fill px-1"></i>
                    <p>{{ car.motor }}</p>
                </div>
                <div>
                    <i class="bi bi-calendar4-range px-1"></i>
                    <p>{{ car.yearProduction }}</p>
                </div>
            </div>
        </div>
        <div class="alquiler w-25 d-flex justify-content-center align-items-center flex-column mx-5">
            <div class="price">
                <div v-if="priceCalculated">Total: {{ priceCalculated }}€</div>
                <div v-else>{{ car.pricePerDay }}€/día</div>
            </div>
            <div>
                <form @submit.prevent="handleSubmit"
                    class="d-flex justify-content-center align-items-center flex-column mb-3">
                    <div class="w-100">
                        <label for="startDate">Fecha de entrega</label>
                        <input type="date" name="startDate" id="startDate" v-model="startDate" :min="minStartDate">
                    </div>
                    <div class="w-100">
                        <label for="endDate">Fecha de devolución</label>
                        <input type="date" name="endDate" id="endDate" v-model="endDate" :min="minEndDate">
                    </div>
                    <div v-if="available"><i class="bi bi-check-lg check-green fs-1"></i></div>
                    <div v-if="available !== undefined && !available"><i class="bi bi-x-lg text-danger fs-1"></i></div>
                    <div>
                        <button @click="checkAvailability" class="btn bg-black text-white">Comprobar
                            Disponibilidad</button>
                    </div>
                    <div v-if="available">
                        <button class="btn alquilar" @click="handleRent">Alquilar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<style scoped>
img {
    width: 100%;
    border-radius: 2%;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.4);
}

h1 {
    font-weight: 600;
}

.details {
    border-bottom: 2px dotted #b4b1af;
    border-top: 2px dotted #b4b1af;
}

.details>div {
    display: flex;
    padding: 2%;
    padding-top: 4%;
}

form div {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    padding-top: 10%;
}

label {
    padding-bottom: 2%;
}

.alquiler {
    border: 1px solid #b4b1af;
    border-radius: 3%;
    overflow: hidden;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.4);
}

.price {
    background-color: black;
    width: 100%;
    color: white;
    text-align: center;
    font-size: 1.7em;
    font-weight: 600;
}

.price div {
    padding: 2%;
}

.check-green {
    color: green;
}

.alquilar {
    background-color: #efb810;
    color: black;
    font-weight: 700;
}

input {
    width: 100%;
}

@media (max-width: 768px) {
    img {
        width: 100% !important;
    }

    .mobile {
        position: static !important;
        display: block !important;
    }

    .mobile-w100 {
        width: 100% !important;
        margin-bottom: 6%;
    }

    .alquiler {
        width: 100% !important;
        margin: 0 !important;
    }
}
</style>