function scrollLeft() {
    const container = document.querySelector('.scroll-cards');
    container.scrollBy({
        left: -container.offsetWidth / 2, // Ajusta la cantidad de desplazamiento según sea necesario
        behavior: 'smooth'
    });
}

function scrollRight() {
    const container = document.querySelector('.scroll-cards');
    container.scrollBy({
        left: container.offsetWidth / 2, // Ajusta la cantidad de desplazamiento según sea necesario
        behavior: 'smooth'
    });
}
