<template>
    <div class="d-flex justify-content-center align-items-center flex-column redes mt-5">
        <div class="d-flex justify-content-center align-items-center">
            <div class="container-fluid d-flex justify-content-center align-items-center flex-column mt-3">
                <div class="text-center mt-4">
                    <h5>Síguenos en nuestras redes sociales</h5>
                    <p>No te pierdas ningún detalle de nuestra nueva flota.</p>
                </div>
                <div class="d-flex justify-content-center align-items-center py-1 mb-2 redes-sociales">
                    <a href="https://www.instagram.com/tucuenta" target="_blank"
                        class="text-white text-decoration-none me-3">
                        <i class="bi bi-instagram"></i>
                    </a>
                    <a href="https://www.facebook.com/tucuenta" target="_blank"
                        class="text-white text-decoration-none me-3">
                        <i class="bi bi-facebook"></i>
                    </a>
                    <a href="https://www.twitter.com/tucuenta" target="_blank" class="text-white text-decoration-none">
                        <i class="bi bi-twitter-x"></i>
                    </a>
                </div>
                <div>
                    <p class="text-center">Calle Invent 76, Madrid, España | +34 674 724 597</p>
                    <div class="d-flex justify-content-center align-items-center py-1 mb-2">
                        <i class="bi bi-paypal fs-5"></i>
                        <i class="bi bi-currency-bitcoin"></i>
                        <i class="bi bi-credit-card"></i>
                        <i class="bi bi-bank"></i>
                    </div>
                    <div class="d-flex justify-content-center align-items-center mb-3 text-decoration-none text-center mobile-footer">
                        <router-link to="/legal" class="text-decoration-none mx-3">
                            <span class="text-white hover-gold">Avsio legal</span>
                        </router-link>
                        <router-link class="text-decoration-none text-white hover-gold" to="/privacy">Política de
                            privacidad</router-link>
                        <router-link class="text-decoration-none text-white hover-gold mx-3" to="/cookies">Política de
                            cookies</router-link>
                    </div>
                    <p class="text-center copyright">&copy; Copyright 2024 ExoticRentCar</p>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
h5 {
    font-weight: bold;
}

.redes {
    background-color: black;
    color: white;
}

i {
    font-size: 2em;
    padding: 1%;
}

.copyright {
    font-size: 0.8em;
}

.hover-gold:hover {
    color: #efb810 !important;
}

@media (max-width: 768px) {
    .mobile-footer {
        flex-direction: column;
    }
}
</style>