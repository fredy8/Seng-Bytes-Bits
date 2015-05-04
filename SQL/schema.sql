DROP SCHEMA SENG_BYTES;
CREATE SCHEMA SENG_BYTES;
USE SENG_BYTES;

CREATE TABLE Usuario (
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL,
	apellido VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	fecha_de_nacimiento TIMESTAMP NOT NULL,
	direccion VARCHAR(100) NOT NULL,
	fecha_de_miembro TIMESTAMP NOT NULL,
	tarjeta_de_credito VARCHAR(100) NOT NULL,
	clave_de_tarjeta INT NOT NULL,
	fecha_expiracion_tarjeta DATE NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE Suscripcion (
	id INT NOT NULL AUTO_INCREMENT,
	id_usuario INT NOT NULL,
	precio FLOAT NOT NULL,
	fecha_vigencia TIMESTAMP NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(id_usuario) REFERENCES Usuario(id)
);

CREATE TABLE Pago (
	id INT NOT NULL AUTO_INCREMENT,
	id_suscripcion INT NOT NULL,
	fecha TIMESTAMP NOT NULL,
	cantidad FLOAT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(id_suscripcion) REFERENCES Suscripcion(id)
);

CREATE TABLE Juez (
	id INT NOT NULL AUTO_INCREMENT,
	id_usuario INT,
	PRIMARY KEY(id),
	FOREIGN KEY(id_usuario) REFERENCES Usuario(id)
);

CREATE TABLE Autor(
	id INT NOT NULL AUTO_INCREMENT,
	id_usuario INT NOT NULL,
	permiso VARCHAR(100) NOT NULL,
	tipo VARCHAR(100) NOT NULL,
	publicacion_este_anio BOOL NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(id_usuario) REFERENCES Usuario(id)
);

CREATE TABLE Articulo (
	id INT NOT NULL AUTO_INCREMENT,
	id_juez INT,
	id_autor INT,
	tipo INT NOT NULL,
	fecha_de_publicacion TIMESTAMP NOT NULL,
	publicado BOOL NOT NULL,
	autorizado BOOL NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(id_juez) REFERENCES Juez(id),
	FOREIGN KEY(id_autor) REFERENCES Autor(id)
);

CREATE TABLE Revista(
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL,
	tiempo_de_publicacion DATE NOT NULL,
	autorizado BOOLEAN NOT NULL,
	PRIMARY KEY(id)
);