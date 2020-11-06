CREATE DATABASE IF NOT EXISTS db_gestionproyectos;
USE db_gestionproyectos;

DROP TABLE IF EXISTS PROYECTOS;
CREATE TABLE IF NOT EXISTS PROYECTOS(
	proyecto_id int AUTO_INCREMENT,
	nombre_proyecto varchar(150) NOT NULL UNIQUE,
	createAt timestamp NOT NULL,
	ultima_modificacion timestamp NOT NULL,
	descripcion varchar(500),
	PRIMARY KEY(proyecto_id)
);


INSERT INTO PROYECTOS (nombre_proyecto, createAt, ultima_modificacion, descripcion) VALUES
('BIGMATIC', '2020-10-26 07:58', '2020-10-26 08:00', 'Proyecto de prueba');
INSERT INTO PROYECTOS (nombre_proyecto, createAt, ultima_modificacion, descripcion) VALUES
('JAZZTEL', '2020-10-24 08:29', '2020-10-24 09:00', 'Proyecto de prueba de jazztel');
INSERT INTO PROYECTOS (nombre_proyecto, createAt, ultima_modificacion, descripcion) VALUES
('VODAFONE', '2020-10-25 08:23','2020-10-26 08:35', 'Proyecto de prueba de vodafone');
INSERT INTO PROYECTOS (nombre_proyecto, createAt, ultima_modificacion, descripcion) VALUES
('PEPEPHONE', '2020-10-23 08:54', '2020-10-25 09:25', 'Proyecto de prueba de pepephone');
INSERT INTO PROYECTOS (nombre_proyecto, createAt, ultima_modificacion, descripcion) VALUES
('VALLADOLID', '2020-10-26 08:55', '2020-10-26 12:45', 'Proyecto de prueba de valladolid');



DROP TABLE IF EXISTS PROV_LOC_KM;
CREATE TABLE IF NOT EXISTS PROV_LOC_KM(
	id_reg int AUTO_INCREMENT,
	localidad varchar(150) NOT NULL UNIQUE,
	provincia varchar(150) NOT NULL,
	kilometros double not null,
	PRIMARY KEY(id_reg)
);

INSERT INTO PROV_LOC_KM (localidad, provincia, kilometros) VALUES ('Palencia', 'Palencia', 63.2);