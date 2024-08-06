let lastScrollTop = 0;
const container = document.querySelector('.container');

window.addEventListener('scroll', () => {
    let scrollTop = window.pageYOffset || document.documentElement.scrollTop;

    if (scrollTop > lastScrollTop) {
        // Scroll hacia abajo
        container.classList.add('hidden');
    } else if (scrollTop < 150) {
        // Scroll hacia arriba y cerca de la parte superior
        container.classList.remove('hidden');
    }
    lastScrollTop = scrollTop <= 0 ? 0 : scrollTop; // Evita valores negativos
});

window.addEventListener('mousemove', (e) => {
    if (e.clientY < 50) { // Si el mouse está cerca de la parte superior (50px)
        container.classList.remove('hidden');
    }
});

container.addEventListener('mouseleave', () => {
    if (window.pageYOffset > 50) {
        container.classList.add('hidden');
    }
});