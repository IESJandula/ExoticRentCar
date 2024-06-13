<template>
    <div class="container d-flex justify-content-center align-items-center flex-column position-relative">
        <div class="mb-5">
            <h1 class="text-center">Servicios ExoticRentCar</h1>
        </div>
        <div class="d-flex justify-content-center align-items-center position-relative mb-5">
            <div class="w-100">
                <img src="../assets/bentley_interior.webp" alt="">
            </div>
            <div class="info d-flex justify-content-center flex-column p-4 position-absolute">
                <h2>Alquiler de coches deportivos</h2>
                <p>ExoticRentCar pone a su disposición las marcas más top del mercado automovilístico.</p>
                <button><router-link to="/flota" class="btn p-1">Flota</router-link></button>
            </div>
        </div>
        <div class="d-flex justify-content-center align-items-center position-relative mb-5">
            <div class="w-100">
                <img class="w-100" src="../assets/boda.webp" alt="">
            </div>
            <div class="info izq-boda w-50 d-flex justify-content-center flex-column p-4 position-absolute">
                <h2>Alquiler de coches clásicos para bodas y eventos</h2>
                <p>Disponemos de los mejores coches clásicos para sus eventos o bodas. Contáctenos para darle un presupuesto a medida.</p>
                <button class="width35">
                    <a href="https://api.whatsapp.com/send?phone=+34674724597&text=¡Hola! Necesito información sobre los coches clásicos para eventos o bodas" target="_blank" class="btn p-1">Contactar</a>
                </button>
            </div>
        </div>
        <div class="d-flex justify-content-center align-items-center position-relative mb-5">
            <div class="w-75 ruta">
                <img class="w-100" src="../assets/ruta.jpg" alt="">
            </div>
            <div class="info info-ruta d-flex justify-content-center flex-column p-4 position-absolute">
                <h2>Rutas en coches deportivos</h2>
                <p>Le ofrecemos las mejores rutas en coches deportivos a nivel nacional. Podrá disfrutar de una experiencia inolvidable.</p>
                <button class="width35">
                    <a href="https://api.whatsapp.com/send?phone=+34674724597&text=¡Hola! Necesito información sobre las rutas disponibles" target="_blank" class="btn p-1">Contactar</a>
                </button>
            </div>
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
    width: 75%;
}

.ruta {
    margin-left: -200px;
}

.info {
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.4);
    padding: 20px;
    top: 35%;
    left: 55%;
    background-color: white;
}

.info-ruta {
    top: 12%;
}

.izq-boda {
    top: 10%;
    left: -20%;
}

.width35 {
    width: 30%;
}

h2 {
    font-weight: 600;
}

button {
    position: relative;
    width: 25%;
    background-color: white;
    border: 2px solid #efb810;
    color: #efb810;
    overflow: hidden;
}

.btn {
    color: #efb810;
}

button::before, button::after {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    border: 2px solid transparent;
    transition: all 0.3s ease-in-out;
}

button::before {
    border-bottom-color: #efb810;
    border-right-color: #efb810;
}

button::after {
    border-top-color: #efb810;
    border-left-color: #efb810;
}

button:hover::before, button:hover::after {
    width: 0;
    height: 0;
}

button:hover::before {
    top: 100%;
    left: 100%;
}

button:hover::after {
    bottom: 100%;
    right: 100%;
}

@media (max-width: 768px) {
    img {
        width: 100% !important;
    }
    .info {
        width: 100% !important;
    }

    div {
        position: static !important;
        display: block !important;
    }

    .ruta {
        margin-left: 0;
        width: 100% !important;
    }
}
</style>