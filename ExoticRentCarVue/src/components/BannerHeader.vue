<template>
    <div class="full-width-image">
        <img src="../assets/maclaren3.webp" alt="Imagen decorativa de un coche deportivo">
        <div class="overlay-text">
            <h1>ALQUILER DE COCHES DEPORTIVOS</h1>
            <h6 class="mb-4">ACEPTAMOS BITCOIN <i class="bi bi-currency-bitcoin fs-5"></i></h6>
            <router-link to="/flota">
                <button class="btn fs-5">Flota</button>
            </router-link>
        </div>
    </div>
</template>

<style scoped>
h1 {
    font-weight: 600;
}

.full-width-image {
    position: relative;
    width: 100%;
    overflow: hidden;
}

.full-width-image img {
    width: 100%;
    height: 100vh;
}

.overlay-text {
    position: absolute;
    top: 40%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
    color: white;
}

.overlay-text h1 {
    font-size: 2.5em;
}

h6 i {
    color: #efb810;
}

.overlay-text button {
    padding: 1%;
    width: 15%;
    color: rgba(239, 184, 16, 0.8);
    background-color: rgba(255, 255, 255, 0.3);
    font-weight: bold;
}

@media (max-width: 768px) {
    .full-width-image img {
        /* Ajusta el tamaño de la imagen para dispositivos móviles */
        max-width: 100%;
        height: auto;
    }

    .overlay-text {
        top: 50%;
        left: 50%;
    }

    .overlay-text h1 {
        font-size: 2em;
    }

    .overlay-text button {
    width: 35%;
}
}
</style>