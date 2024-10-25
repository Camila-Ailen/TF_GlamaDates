function showNotification(message, type = 'success') {
    const messageContainer = document.createElement('div');
    messageContainer.className = `fixed top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 p-4 rounded-lg shadow-lg ${type === 'success' ? 'bg-green-500 text-white' : 'bg-red-500 text-white'}`;
    messageContainer.style.zIndex = 9999; // Asegúrate de que el z-index sea alto
    messageContainer.textContent = message;
    document.body.appendChild(messageContainer);

    setTimeout(() => {
        messageContainer.remove();
    }, 3000);
}

function loadContent(url) {
    fetch(url)
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
        })
        .catch(error => console.error('Error loading content:', error));
}


//recargar la tabla de usuarios

function handleFormSubmit(event) {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);

    fetch(form.action, {
        method: form.method,
        body: formData,
    })
        .then(response => {
            if (response.ok) {
                showNotification('Usuario creado exitosamente');
                setTimeout(() => {
                    window.location.reload();
                }, 3000); // Espera 3 segundos antes de recargar la página
            } else {
                showNotification('Error al agregar usuario', 'error');
            }
        })
        .catch(error => {
            showNotification('Error: ' + error.message, 'error');
        });
}

function confirmDelete(form) {
    const id = form.getAttribute('data-id');
    const nombre = form.getAttribute('data-nombre');
    const apellido = form.getAttribute('data-apellido');

    return confirm(`¿Estás seguro de que deseas eliminar al usuario con ID ${id}, nombre ${nombre} ${apellido}?`);
}


    document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('simple-search');
    const table = document.getElementById('userTable');
    const rows = table.getElementsByTagName('tr');

    searchInput.addEventListener('input', function() {
    const filter = searchInput.value.toLowerCase();
    for (let i = 1; i < rows.length; i++) { // Empieza en 1 para saltar el encabezado
    const cells = rows[i].getElementsByTagName('td');
    let match = false;
    for (let j = 0; j < cells.length; j++) {
    if (cells[j].innerText.toLowerCase().includes(filter)) {
    match = true;
    break;
}
}
    rows[i].style.display = match ? '' : 'none';
}
});
});