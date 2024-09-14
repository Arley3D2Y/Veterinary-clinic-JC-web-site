function validateForm() {
    // Obtener los valores de los campos de entrada
    const correo = document.getElementById("txtCorreo").value;
    const password = document.getElementById("txtPassword").value;
    let valid = true;

    // Limpiar los mensajes de error anteriores
    document.getElementById("emailError").style.display = "none";
    document.getElementById("passwordError").style.display = "none";

    // Validación del correo
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(correo)) {
        document.getElementById("emailError").innerText = "*El correo no es válido.";
        document.getElementById("emailError").style.display = "block";
        valid = false;
    }

    // Validación de la contraseña
    if (password.length < 4) {
        document.getElementById("passwordError").innerText = "*Minimo 4 carácteres.";
        document.getElementById("passwordError").style.display = "block";
        valid = false;
    }

    return valid;
}