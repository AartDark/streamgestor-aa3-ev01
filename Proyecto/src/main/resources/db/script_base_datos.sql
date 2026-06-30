CREATE DATABASE IF NOT EXISTS streamgestor_db;
USE streamgestor_db;

CREATE TABLE usuarios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(120) NOT NULL UNIQUE,
    clave VARCHAR(80) NOT NULL,
    rol VARCHAR(30) NOT NULL
);

CREATE TABLE clientes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    documento VARCHAR(15) NOT NULL UNIQUE,
    telefono VARCHAR(15) NOT NULL,
    correo VARCHAR(120),
    estado VARCHAR(20) NOT NULL
);

CREATE TABLE categorias (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(80) NOT NULL UNIQUE,
    descripcion VARCHAR(250)
);

CREATE TABLE peliculas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(120) NOT NULL,
    descripcion VARCHAR(400),
    anio INT NOT NULL,
    estado VARCHAR(20) NOT NULL,
    categoria_id BIGINT NOT NULL,
    CONSTRAINT fk_pelicula_categoria FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

CREATE TABLE reservas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cliente_id BIGINT NOT NULL,
    pelicula_id BIGINT NOT NULL,
    fecha_reserva DATE NOT NULL,
    fecha_devolucion DATE,
    estado VARCHAR(20) NOT NULL,
    CONSTRAINT fk_reserva_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    CONSTRAINT fk_reserva_pelicula FOREIGN KEY (pelicula_id) REFERENCES peliculas(id)
);

INSERT INTO usuarios (nombre, correo, clave, rol) VALUES
('Administrador', 'admin@streaminggestor.com', 'admin123', 'ADMIN');
