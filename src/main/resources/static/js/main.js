const login = async (correo, contrasenia) => {
    const credentials = {
        correoElectronico: correo,
        contrasenia: contrasenia
    };

    try {
        const response = await fetch("http://localhost:8092/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(credentials)
        });

        if (!response.ok) {
            throw new Error("No se pudo autenticar el usuario.");
        }

        const data = await response.json();
        localStorage.setItem("token", data.jwTtoken);
        localStorage.setItem("usuarioId", 1); // Guardar ID del usuario

        console.log("Autenticado correctamente, token guardado:", data.jwTtoken);

        document.getElementById("login-container").style.display = "none";
        document.getElementById("topicos-container").style.display = "block";

        obtenerTopicos();
    } catch (error) {
        console.error("Error de autenticación:", error);
    }
};

// Función para obtener los tópicos
const obtenerTopicos = async () => {
    const token = localStorage.getItem("token");

    if (!token) {
        console.log("No se encuentra token de autenticación");
        return;
    }

    try {
        const response = await fetch("http://localhost:8092/topicos", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error("Error al obtener los tópicos.");
        }

        const data = await response.json();
        mostrarTopicos(data.content);  // Aquí pasamos los tópicos obtenidos

    } catch (error) {
        console.error("Error al obtener tópicos:", error);
    }
};

// Función para mostrar los tópicos en la interfaz en forma de tabla
const mostrarTopicos = (topicos) => {
    const listaTopicos = document.getElementById("topicos-list");
    listaTopicos.innerHTML = "";

    topicos.forEach(topico => {
        const fila = document.createElement("tr");

        const celdaId = document.createElement("td");
        celdaId.textContent = topico.id;
        fila.appendChild(celdaId);

        const celdaTitulo = document.createElement("td");
        celdaTitulo.textContent = topico.titulo;
        fila.appendChild(celdaTitulo);

        const celdaMensaje = document.createElement("td");
        celdaMensaje.textContent = topico.mensaje;
        fila.appendChild(celdaMensaje);

        const celdaFecha = document.createElement("td");
        celdaFecha.textContent = new Date(topico.fechaCreacion).toLocaleDateString();
        fila.appendChild(celdaFecha);

        const celdaAutor = document.createElement("td");
        celdaAutor.textContent = topico.autor.nombre;
        fila.appendChild(celdaAutor);

        // NUEVA CELDA CON BOTÓN DE ELIMINAR
        const celdaAcciones = document.createElement("td");
        const botonEliminar = document.createElement("button");
        botonEliminar.textContent = "Eliminar";
        botonEliminar.classList.add("btn-eliminar");
        botonEliminar.addEventListener("click", () => eliminarTopico(topico.id));
        celdaAcciones.appendChild(botonEliminar);
        fila.appendChild(celdaAcciones);

        listaTopicos.appendChild(fila);
    });
};


// Función para crear un nuevo tópico
const crearTopico = async (titulo, mensaje, curso) => {
    const token = localStorage.getItem("token");
    const usuarioId = 1;

    if (!token || !usuarioId) {
        console.log("No se encuentra token de autenticación o usuario ID");
        return;
    }

    // Construir el objeto con la estructura esperada por el backend
    const nuevoTopico = {
        titulo: titulo,
        mensaje: mensaje,
        fecha: new Date().toISOString(), // Fecha actual en formato ISO
        autor: {
            id: usuarioId  // Solo enviamos el ID del usuario
        },
        curso: curso // Enum seleccionado en el select
    };

    try {
        const response = await fetch("http://localhost:8092/topicos", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(nuevoTopico)
        });

        if (!response.ok) {
            throw new Error("Error al crear el tópico.");
        }

        const data = await response.json();
        console.log("Tópico creado:", data);

        // Actualizar la lista de tópicos
        obtenerTopicos();
    } catch (error) {
        console.error("Error al crear tópico:", error);
    }
};

// Asociamos el formulario de login al evento de envío
document.getElementById("login-form").addEventListener("submit", (event) => {
    event.preventDefault(); // Prevenir el comportamiento por defecto (recargar página)

    const correo = document.getElementById("correo").value;
    const contrasenia = document.getElementById("contrasenia").value;

    login(correo, contrasenia);
});

// Asociar evento al formulario de creación de tópicos
document.getElementById("crear-topico-form").addEventListener("submit", (event) => {
    event.preventDefault();

    const titulo = document.getElementById("titulo").value;
    const mensaje = document.getElementById("mensaje").value;
    const curso = document.getElementById("curso").value;

    crearTopico(titulo, mensaje, curso);
});

const eliminarTopico = async (id) => {
    const token = localStorage.getItem("token");

    if (!token) {
        console.log("No se encuentra token de autenticación");
        return;
    }

    const confirmacion = confirm("¿Estás seguro de que deseas eliminar este tópico?");
    if (!confirmacion) return;

    try {
        const response = await fetch(`http://localhost:8092/topicos/${id}`, {
            method: "DELETE",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error("Error al eliminar el tópico.");
        }

        console.log(`Tópico con ID ${id} eliminado correctamente.`);
        obtenerTopicos(); // Recargar lista
    } catch (error) {
        console.error("Error al eliminar tópico:", error);
    }
};