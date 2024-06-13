<script setup>
import HeaderC from '@/components/Header.vue';
import FooterC from '@/components/Footer.vue';
import ButtonWhatsapp from '@/components/ButtonWhatsapp.vue';
import MyRentals from '@/components/MyRentals.vue';
</script>

<template>
    <header>
        <HeaderC></HeaderC>
    </header>
    <main>
        <MyRentals></MyRentals>
    </main>
    <footer>
        <FooterC></FooterC>
        <ButtonWhatsapp></ButtonWhatsapp>
    </footer>
</template>

<style scoped>
header {
    margin-bottom: 9%;
}

main {
    display: flex;
    flex-direction: column;
    min-height: 56vh;
}

@media (max-width: 768px) {
    header {
        margin-bottom: 39%;
    }
}
</style>