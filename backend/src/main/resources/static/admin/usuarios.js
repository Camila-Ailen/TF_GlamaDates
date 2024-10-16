


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