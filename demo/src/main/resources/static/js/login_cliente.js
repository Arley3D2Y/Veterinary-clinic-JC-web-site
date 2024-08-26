document.addEventListener('DOMContentLoaded', function() {
    // Selecciona el input y el label
    const input = document.querySelector('.form-input');
    const label = document.querySelector('.form-label');

    // Limpia el valor del input y el estado del checkbox al cargar la página
    if (input) {
        input.value = '';
        label.classList.remove('hidden');
    }

    // Evento para actualizar el diseño del label basado en el contenido del input
    input.addEventListener('input', function() {
        if (input.value.trim() !== '') {
            label.classList.add('has-content');
        } else {
            label.classList.remove('has-content');
        }
    });

    // Evento cuando se pierde el enfoque del input
    input.addEventListener('blur', function() {
        if (input.value.trim() === '') {
            label.classList.remove('has-content');
        }
    });

    // Evento para asegurar que se limpie al regresar de otra página
    window.addEventListener('pageshow', function() {
        if (input) {
            input.value = '';
            label.classList.remove('has-content');
        }
    });

    
});
