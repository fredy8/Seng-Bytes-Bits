DROP SCHEMA SENG_BYTES;
CREATE SCHEMA SENG_BYTES;
USE SENG_BYTES;

CREATE TABLE Editor (
	id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	apellido VARCHAR(100) NOT NULL,
	tipo INT NOT NULL DEFAULT 0,
	fecha_de_miembro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(id)
);

CREATE TABLE Suscriptor (
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL,
	apellido VARCHAR(100) NOT NULL,
	direccion VARCHAR(100) NOT NULL,
	tarjeta_de_credito VARCHAR(100) NOT NULL,
	tiempo_restante INT NOT NULL DEFAULT 0,
	PRIMARY KEY(id)
);


CREATE TABLE Revista (
	id INT NOT NULL AUTO_INCREMENT,
	mes INT NOT NULL,
	anio INT NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE Articulo (
	id INT NOT NULL AUTO_INCREMENT,
	id_revista INT,
	texto VARCHAR(3000),
	titulo VARCHAR(100),
	fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(id),
	FOREIGN KEY(id_revista) REFERENCES Revista(id)
);

CREATE TABLE Votos (
	id_juez INT NOT NULL,
	id_articulo INT NOT NULL,
	FOREIGN KEY(id_articulo) REFERENCES Articulo(id),
	FOREIGN KEY(id_juez) REFERENCES Editor(id)
);

CREATE TABLE Editores_Articulos (
	id_articulo INT NOT NULL,
	id_editor INT NOT NULL,
	PRIMARY KEY(id_Articulo, id_Editor),
	FOREIGN KEY(id_articulo) REFERENCES Articulo(id),
	FOREIGN KEY(id_editor) REFERENCES Editor(id)
);

INSERT INTO Editor (username, password, nombre, apellido, tipo) VALUES ('tangipony', '1234', 'Luis', 'Lamadrid', 1);
INSERT INTO Editor (username, password, nombre, apellido) VALUES ('fredy', '1234', 'Alfredo', 'Altamirano');
INSERT INTO Editor (username, password, nombre, apellido, tipo) VALUES ('chief', '1234', 'chief', '...', 2);
