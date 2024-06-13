<script setup>
import { ref } from 'vue';

const email = ref('');
const emailError = ref('');
const emailOk = ref('');

const handleSubmit = async () => {
    emailError.value = '';

    if (!email.value) {
        emailError.value = 'Introduce tu email';
        return;
    }

    emailOk.value = "Enviado! Revisa tu email"
};
</script>

<template>
    <div class="d-flex justify-content-center align-items-center mt-5">
        <div>
            <form @submit.prevent="handleSubmit" class="form">
                <p id="heading">Recuperar Contraseña</p>
                <div class="field">
                    <label for="email">Email:</label>
                    <input v-model="email" autocomplete="off" placeholder="Introduce tu email" class="input-field"
                        type="email">
                </div>
                <!-- Mostrar mensaje de error si email está vacío -->
                <span v-if="emailError" class="error-message">{{ emailError }}</span>

                <!-- Mostrar mensaje de confirmación -->
                <span v-if="emailOk" class="email-ok mt-3">{{ emailOk }}</span>

                <div class="btn">
                    <button @click="updatePassword" class="button1 mb-3">Enviar</button>
                </div>
            </form>
        </div>
    </div>
</template>

<style scoped>
.form {
    display: flex;
    flex-direction: column;
    gap: 10px;
    padding-left: 2em;
    padding-right: 2em;
    padding-bottom: 0.4em;
    background-color: #171717;
    border-radius: 25px;
    transition: .4s ease-in-out;
}

.form:hover {
    transform: scale(1.05);
    border: 1px solid black;
}

#heading {
    text-align: center;
    margin: 2em;
    color: rgb(255, 255, 255);
    font-size: 1.2em;
}

.field {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 0.5em;
    border-radius: 25px;
    padding: 0.6em;
    border: none;
    outline: none;
    color: white;
    background-color: #171717;
    box-shadow: inset 2px 5px 10px rgb(5, 5, 5);
}

.input-icon {
    height: 1.3em;
    width: 1.3em;
    fill: white;
}

.input-field {
    background: none;
    border: none;
    outline: none;
    width: 100%;
    color: #d3d3d3;
}

.form .btn {
    display: flex;
    justify-content: center;
    flex-direction: row;
    margin-top: 2.5em;
}

.button1 {
    padding: 0.5em;
    padding-left: 1.1em;
    padding-right: 1.1em;
    border-radius: 5px;
    margin-right: 0.5em;
    border: none;
    outline: none;
    transition: .4s ease-in-out;
    background-color: #252525;
    color: white;
}

.button1:hover {
    background-color: black;
    color: white;
}

.button2 {
    padding: 0.5em;
    padding-left: 2.3em;
    padding-right: 2.3em;
    border-radius: 5px;
    border: none;
    outline: none;
    transition: .4s ease-in-out;
    background-color: #252525;
    color: white;
    text-decoration: none;
}

.button2:hover {
    background-color: black;
    color: white;
}

.button3 {
    margin-bottom: 3em;
    padding: 0.5em;
    border-radius: 5px;
    border: none;
    outline: none;
    transition: .4s ease-in-out;
    background-color: #252525;
    color: white;
}

.button3:hover {
    background-color: red;
    color: white;
}

span {
    color: red;
    padding-left: 4%;
}

.email-ok {
    color: green;
    font-weight: 500;
}
</style>