<script setup>
import { ref, computed } from 'vue';

const whatsappLink = computed(() => {
    // Reemplaza "123456789" con el número de teléfono real y el mensaje que desees
    return `https://wa.me/+34674724597?text=Hola, necesito ayuda/información con`;
});
</script>

<template>
    <div class="whatsapp-button text-center">
        <a :href="whatsappLink" target="_blank" rel="noopener noreferrer">
            <i class="bi bi-whatsapp"></i>
        </a>
    </div>
</template>

<style scoped>
.whatsapp-button {
    width: 4%;
    position: fixed;
    bottom: 20px;
    left: 20px;
    z-index: 1000;
    border-radius: 50%;
    padding: 10px;
    font-size: 24px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

i {
    color: #25D366;
    font-size: 2.5em;
}
</style>