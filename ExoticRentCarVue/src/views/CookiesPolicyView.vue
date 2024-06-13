<script setup>
import HeaderC from '@/components/Header.vue';
import FooterC from '@/components/Footer.vue';
import ButtonWhatsapp from '@/components/ButtonWhatsapp.vue';
import CookiesPolicy from '../components/CookiesPolicy.vue';
</script>

<template>
    <header>
        <HeaderC></HeaderC>
    </header>
    <main>
        <CookiesPolicy></CookiesPolicy>
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