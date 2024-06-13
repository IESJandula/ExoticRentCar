<script setup>
import repository from '@/service/repository';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const email = ref('');
const username = ref('');
const name = ref('');
const lastName = ref('');
const password = ref('');
const age = ref('');

const emailError = ref('');
const usernameError = ref('');
const nameError = ref('');
const lastNameError = ref('');
const passwordError = ref('');
const ageError = ref('');

const conflictError = ref('');
const serverError = ref('');

const handleSubmit = async () => {
    emailError.value = '';
    usernameError.value = '';
    nameError.value = '';
    lastNameError.value = '';
    passwordError.value = '';
    ageError.value = '';
    conflictError.value = '';
    serverError.value = '';

    if (!email.value) {
        emailError.value = 'Introduce tu email';
    }

    if (!username.value) {
        usernameError.value = 'Introduce tu nombre de usuario';
    }

    if (!name.value) {
        nameError.value = 'Introduce tu nombre';
    }

    if (!lastName.value) {
        lastNameError.value = 'Introduce tu apellido';
    }

    if (!password.value) {
        passwordError.value = 'Introduce tu contraseña';
    }

    if (!age.value) {
        ageError.value = 'Introduce tu edad';
    } else if (parseInt(age.value) < 18) {
        ageError.value = 'Debes ser mayor de 18 años';
    }

    // Comprobar que todos los campos están completos antes de realizar la llamada
    if (email.value && username.value && name.value && lastName.value && password.value && age.value && parseInt(age.value) >= 18) {
        try {
            const response = await repository.users.createUser({
                email: email.value,
                username: username.value,
                name: name.value,
                lastName: lastName.value,
                password: password.value,
                age: age.value
            });
            router.push("/login");
        } catch (error) {
            if (error.response.status === 409) {
                conflictError.value = 'Username no disponible';
            } else {
                serverError.value = 'Lo sentimos, algo salió mal. Por favor, inténtalo de nuevo más tarde.';
            }
        }
    }
}
</script>

<template>
    <div class="container">
        <div class="d-flex justify-content-center align-items-center mt-5">
            <div>
                <form @submit.prevent="handleSubmit" class="form">
                    <p id="heading">Registro</p>
                    <div class="field">
                        <label for="name">Nombre:</label>
                        <input v-model="name" autocomplete="off" placeholder="Nombre" class="input-field" type="text"
                            id="name">
                    </div>
                    <!-- Mostrar mensaje de error si name está vacío -->
                    <span v-if="nameError" class="error-message">{{ nameError }}</span>
                    <div class="field">
                        <label for="lastName">Apellidos:</label>
                        <input v-model="lastName" autocomplete="off" placeholder="Apellidos" class="input-field"
                            type="text" id="lastName">
                    </div>
                    <!-- Mostrar mensaje de error si lastName está vacío -->
                    <span v-if="lastNameError" class="error-message">{{ lastNameError }}</span>
                    <div class="field">
                        <label for="username">Username:</label>
                        <input v-model="username" autocomplete="off" placeholder="Username" class="input-field"
                            type="text" id="username">
                    </div>
                    <!-- Mostrar mensaje de error si username está vacío -->
                    <span v-if="usernameError" class="error-message">{{ usernameError }}</span>
                    <div class="field">
                        <label for="email">Email:</label>
                        <input v-model="email" autocomplete="off" placeholder="test@example.com" class="input-field"
                            type="email" id="email">
                    </div>
                    <!-- Mostrar mensaje de error si email está vacío -->
                    <span v-if="emailError" class="error-message">{{ emailError }}</span>
                    <div class="field">
                        <label for="password">Password:</label>
                        <input v-model="password" placeholder="Password" class="input-field" type="password"
                            id="password">
                    </div>
                    <!-- Mostrar mensaje de error si password está vacío -->
                    <span v-if="passwordError" class="error-message">{{ passwordError }}</span>
                    <div class="field">
                        <label for="age">Edad:</label>
                        <input v-model="age" placeholder="Edad" class="input-field" type="number" id="age">
                    </div>
                    <!-- Mostrar mensaje de error si age está vacío -->
                    <span v-if="ageError" class="error-message">{{ ageError }}</span>
                    <!-- Checkbox para aceptar términos y condiciones -->
                    <div class="d-flex align-items-center">
                        <input type="checkbox" id="terms" v-model="acceptTerms">
                        <label class="mx-2 text-white" for="terms">Acepto términos y condiciones de uso</label>
                    </div>

                    <!-- Mensaje de error para el caso de conflicto -->
                    <span v-if="conflictError" class="error-message">{{ conflictError }}</span>
                    <!-- Mensaje de error para el caso de internal server error -->
                    <span v-if="serverError" class="error-message">{{ serverError }}</span>

                    <div class="btn">
                        <button type="submit" class="button1 mb-3">Registrarse</button>
                    </div>
                </form>
            </div>
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
    width: 400px;
    /* Ancho del formulario */
    margin: auto;
    /* Centrar el formulario */
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
</style>