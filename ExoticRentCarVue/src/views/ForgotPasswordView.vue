<script setup>
import HeaderC from '@/components/Header.vue';
import ButtonWhatsapp from '@/components/ButtonWhatsapp.vue';
import ForgotPassword from '@/components/ForgotPassword.vue';
</script>

<template>
    <header>
        <HeaderC></HeaderC>
    </header>
    <main class="d-flex flex-column">
        <ForgotPassword></ForgotPassword>
    </main>
    <footer>
        <ButtonWhatsapp></ButtonWhatsapp>
    </footer>
</template>

<style scoped>
header {
    margin-bottom: 6%;
}

@media (max-width: 768px) {
    header {
        margin-bottom: 29%;
    }
}
</style>