<script setup>
import BannerHeader from '@/components/BannerHeader.vue';
import HeaderC from '@/components/Header.vue';
import Trends from '@/components/Trends.vue';
import FooterC from '@/components/Footer.vue';
import ButtonWhatsapp from '@/components/ButtonWhatsapp.vue';
</script>

<template>
  <header>
    <HeaderC></HeaderC>
  </header>
  <main>
    <BannerHeader></BannerHeader>
    <Trends></Trends>
  </main>
  <footer>
    <FooterC></FooterC>
    <ButtonWhatsapp></ButtonWhatsapp>
  </footer>
</template>

<style scoped>
@media (max-width: 768px) {
  header {
    margin-bottom: 31%;
  }
}
</style>

<style>
body {
  font-family: "Poppins", sans-serif;
  font-weight: 400;
  font-style: normal;
}
</style>
