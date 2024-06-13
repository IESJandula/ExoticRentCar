<script setup>
import HeaderC from '@/components/Header.vue';
import FooterC from '@/components/Footer.vue';
import ButtonWhatsapp from '@/components/ButtonWhatsapp.vue';
import Services from '@/components/Services.vue';
</script>

<template>
    <header>
        <HeaderC></HeaderC>
    </header>
    <main>
        <Services></Services>
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

@media (max-width: 768px) {
    header {
        margin-bottom: 39%;
    }
}
</style>