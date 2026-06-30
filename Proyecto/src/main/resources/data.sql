INSERT INTO usuarios (id, nombre, correo, clave, rol) VALUES
(1, 'Administrador', 'admin@streaminggestor.com', 'admin123', 'ADMIN');

INSERT INTO categorias (id, nombre, descripcion) VALUES
(1, 'Acción', 'Películas con escenas dinámicas y aventuras.'),
(2, 'Comedia', 'Contenido para entretenimiento familiar.'),
(3, 'Drama', 'Historias con enfoque emocional y narrativo.');

INSERT INTO clientes (id, nombre, documento, telefono, correo, estado) VALUES
(1, 'Carlos Ramírez', '1020304050', '3167457611', 'carlos@example.com', 'Activo'),
(2, 'Laura Gómez', '1098765432', '3205554444', 'laura@example.com', 'Activo');

INSERT INTO peliculas (id, titulo, descripcion, anio, estado, categoria_id) VALUES
(1, 'Ruta Final', 'Película de acción para usuarios registrados.', 2024, 'Disponible', 1),
(2, 'Risas en Casa', 'Comedia recomendada para compartir en familia.', 2023, 'Disponible', 2);

INSERT INTO reservas (id, cliente_id, pelicula_id, fecha_reserva, fecha_devolucion, estado) VALUES
(1, 1, 1, CURRENT_DATE, NULL, 'Activa');
