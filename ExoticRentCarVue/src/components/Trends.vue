<template>
    <div class="text-center mt-5 mb-4">
        <h2>Tendencias</h2>
        <p class="grey">¿Te apasiona el motor? Este es tu lugar</p>
    </div>
    <div class="container">
        <div class="row d-flex flex-wrap row-gap-5">
            <div class="col-md-4">
                <article>
                    <div>
                        <img src="../assets/roll.webp" alt="Rolls-Royce Arcadia Droptail">
                    </div>
                    <div class="mt-3 px-3 mb-3">
                        <h4>Nuevo Rolls-Royce Arcadia Droptail - 30M €</h4>
                        <p>Adéntrate la última creación que se ha gestado en los talleres de Coachbuild, los especialistas en personalización han dado forma a una de las peticiones más exigentes de un cliente con un resultado abrumador.</p>
                        <a href="https://www.motor.es/noticias/rolls-royce-arcadia-droptail-2024100739.html" target="_blank">Leer más</a>
                    </div>
                </article>
            </div>
            <div class="col-md-4">
                <article>
                    <div>
                        <img src="../assets/mansory.webp" alt="Rolls-Royce Arcadia Droptail">
                    </div>
                    <div class="mt-3 px-3 mb-3">
                        <h4>Mansory convierte el Lamborghini Urus en un SUV todavía más bestial</h4>
                        <p>No te pierdas el espectacular juego de colores para la carrocería y, sobre todo, para su habitáculo. El exterior es espectacular gracias a la gran cantidad de piezas de fibra de carbono forjadas.</p>
                        <a href="https://www.motor.es/noticias/mansory-lamborghini-urus-venatus-preparacion-suv-bestial-2024100526.html" target="_blank">Leer más</a>
                    </div>
                </article>
            </div>
            <div class="col-md-4">
                <article>
                    <div>
                        <img src="../assets/ferrari.webp" alt="Rolls-Royce Arcadia Droptail">
                    </div>
                    <div class="mt-3 px-3 mb-3">
                        <h4>Novitec aporta distinción al Ferrari 296 GTB con un kit aerodinámico a la altura de sus casi 900 CV</h4>
                        <p class="mb-5">Casi un año han tardado los alemanes de Novitec en desarrollar el kit aerodinámico para una de las grandes estrellas de la marca del Cavallino Rampante.</p>
                        <a href="https://www.motor.es/noticias/ferrari-296-gtb-kit-novitec-202499891.html" target="_blank">Leer más</a>
                    </div>
                </article>
            </div>
        </div>
    </div>
</template>

<style scoped>
h2 {
    font-weight: bold;
}

.grey {
    color: #939190;
}

img {
    width: 100%;
    height: 270px;
}

article {
    position: relative;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.4);
    border-radius: 2%;
    overflow: hidden;
    display: flex;
    flex-direction: column;
    height: 100%;
}

a {
    bottom: 0;
    left: 0;
    position: absolute;
    text-decoration: none;
    color: #b4b1af;
    padding: 4%;
}
</style>