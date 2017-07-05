/* Creacion de Usuarios que Podran Utilizar la Base de datos*/
CREATE ROLE Administrador NOSUPERUSER;
CREATE ROLE Trabajador NOSUPERUSER;

/*** CREACION DE LA BASE DE DATOS ***/
CREATE DATABASE proyecto_bd TEMPLATE template1;

/*** CONEXION A LA BASE DE DATOS ***/
\c proyecto_bd

/*** CREACION DEL ESQUEMA ***/

CREATE SCHEMA p;

/* asignado permisos para la utilizacion del esquema*/
GRANT USAGE ON SCHEMA p TO Trabajador;
GRANT USAGE ON SCHEMA p TO Administrador;

/*** CREACION DE LOS DOMINIOS ***/

CREATE DOMAIN p.tipo_producto varchar (15)
				CHECK (VALUE IN ('Producto', 'Ingrediente'));
CREATE DOMAIN p.tipo_codigo varchar(11);
CREATE DOMAIN p.tipo_ci varchar(13);
CREATE DOMAIN p.tipo_funcion varchar(15) 
				CHECK (VALUE IN ('Despacho','Transporte','Recepcion'));
CREATE DOMAIN p.tipo_empresa varchar(15)
				CHECK (VALUE IN ('Procesadora', 'Chocolatera', 'Tienda'));
CREATE DOMAIN p.tipo_numerico numeric(20,4);
/*** CREACION DE LAS TABLAS ***/

/*** TABLAS PRIMARIAS ***/
CREATE TABLE p.evento(
	organizador varchar(60)  NOT NULL,
	nombre varchar(60)  NOT NULL,
	anho int  NOT NULL,
	PRIMARY KEY (organizador, nombre, anho)
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.evento TO Administrador;
GRANT SELECT ON TABLE p.evento TO Trabajador;

CREATE TABLE p.producto (
	codigo p.tipo_codigo  PRIMARY KEY NOT NULL,
	nombre varchar(60) NOT NULL,
	peso float  NOT NULL,
	precio p.tipo_numerico  NOT NULL,
	tipo p.tipo_producto NOT NULL
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.producto TO Administrador;
GRANT SELECT ON TABLE p.producto TO Trabajador;

CREATE TABLE p.maquinaria (
	serial p.tipo_codigo  PRIMARY KEY NOT NULL,
	precio p.tipo_numerico NOT NULL,
	pais varchar(60) NOT NULL 
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.maquinaria TO Administrador;
GRANT SELECT ON TABLE p.maquinaria TO Trabajador;

CREATE TABLE p.etapa
(
	id p.tipo_codigo PRIMARY KEY NOT NULL,
	nombre varchar(60) NOT NULL,
	fecah date NOT NULL,
	hora time NOT NULL,
	inspeccion varchar(50) NOT NULL,
	control_calidad varchar(30) NOT NULL,
	costo p.tipo_numerico NOT NULL
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.etapa TO Administrador;
GRANT SELECT ON TABLE p.etapa TO Trabajador;

CREATE TABLE p.empresa
(			
	rif p.tipo_ci PRIMARY KEY NOT NULL,
	tipo p.tipo_empresa NOT NULL,
	nombre varchar(60) NOT NULL,
	direccion varchar(30) NOT NULL,
	ciudad varchar(30) NOT NULL,
	estado varchar(30) NOT NULL
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.empresa TO Administrador;
GRANT SELECT ON TABLE p.empresa TO Trabajador;

CREATE TABLE p.persona
(
	ci p.tipo_ci PRIMARY KEY NOT NULL,
	nombre varchar(60) NOT NULL,
	fecha_nacimiento date NOT NULL
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.persona TO Administrador;
GRANT SELECT ON TABLE p.persona TO Trabajador;

/*** TABLAS SECUNDARIAS ***/
CREATE TABLE p.encargo
(
	codigo p.tipo_codigo PRIMARY KEY NOT NULL,
	rif p.tipo_ci REFERENCES p.empresa,		
	costo p.tipo_numerico NOT NULL,
	fecha date NOt NULL,
	incidente varchar(100),		
	ruta varchar(100) NOT NULL
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.encargo TO Administrador;
GRANT SELECT ON TABLE p.encargo TO Trabajador;

CREATE TABLE p.trabajador
(
	ci p.tipo_ci PRIMARY KEY NOT NULL,
	nombre varchar(60) NOT NULL,
	fecha_nacimiento date NOT NULL,
	cargo varchar(60) NOT NULL,
	sueldo p.tipo_numerico NOT NULL,  
	fecha_inicio date NOT NULL,
	fecha_fin date NOT NULL,
	rif p.tipo_ci NOT NULL REFERENCES p.empresa
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.trabajador TO Administrador;
GRANT SELECT ON TABLE p.trabajador TO Trabajador;


CREATE TABLE p.estudiante(
	ci p.tipo_ci PRIMARY KEY NOT NULL,
	nombre varchar(60) NOT NULL,
	fecha_nac date NOT NULL
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.estudiante TO Administrador;
GRANT SELECT ON TABLE p.estudiante TO Trabajador;

CREATE TABLE p.premio_conc (
	organizador varchar(60) NOT NULL,
	nombre varchar(60)  NOT NULL,
	anho int NOT NULL,
	premio varchar(50)  NOT NULL, 
	codigo p.tipo_codigo NOT NULL,
	PRIMARY KEY (organizador, nombre, anho, premio, codigo),
	FOREIGN KEY (organizador, nombre, anho) REFERENCES p.evento(organizador, nombre, anho)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	FOREIGN KEY (codigo) REFERENCES p.producto
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.premio_conc TO Administrador;
GRANT SELECT ON TABLE p.premio_conc TO Trabajador;
 
CREATE TABLE p.concursa (
	nombre varchar(60) NOT NULL,
	anho int NOT NULL,
	organizador varchar(60) NOT NULL,
	codigo p.tipo_codigo NOT NULL,
	PRIMARY KEY (nombre, anho, organizador, codigo),
	FOREIGN KEY (organizador, nombre, anho) REFERENCES p.evento(organizador, nombre, anho)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	FOREIGN KEY (codigo) REFERENCES p.producto
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.concursa TO Administrador;
GRANT SELECT ON TABLE p.concursa TO Trabajador;

CREATE TABLE p.participante_ev (
	organizador varchar(60)  NOT NULL,
	nombre varchar(60)  NOT NULL,
	anho int  NOT NULL, 
	participante varchar(60) NOT NULL,
	delegado varchar(60) NOT NULL,
	PRIMARY KEY (organizador, nombre, anho, participante),	
	FOREIGN KEY (organizador, nombre, anho) REFERENCES p.evento(organizador, nombre, anho)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.participante_ev TO Administrador;
GRANT SELECT ON TABLE p.participante_ev TO Trabajador;

CREATE TABLE p.utiliza
(
	serial p.tipo_codigo NOT NULL,
	id p.tipo_codigo NOT NULL,
	PRIMARY KEY (serial, id),
	FOREIGN KEY (id) REFERENCES p.etapa
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.utiliza TO Administrador;
GRANT SELECT ON TABLE p.utiliza TO Trabajador;

CREATE TABLE p.procesa
(
	id p.tipo_codigo NOT NULL,
	codigo p.tipo_codigo NOT NULL, 
	rif p.tipo_codigo NOT NULL,
	cantidad float NOT NULL,
	PRIMARY KEY (id, codigo,rif),
	FOREIGN KEY (id) REFERENCES p.etapa
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	FOREIGN KEY (codigo) REFERENCES p.producto
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	FOREIGN KEY (rif) REFERENCES p.empresa
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.procesa TO Administrador;
GRANT SELECT ON TABLE p.procesa TO Trabajador;

CREATE TABLE p.usa
(
	id p.tipo_codigo NOT NULL,
	codigo p.tipo_codigo NOT NULL, 
	cantidad float NOT NULL,
	PRIMARY KEY (id, codigo),
	FOREIGN KEY (id) REFERENCES p.etapa
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	FOREIGN KEY (codigo) REFERENCES p.producto
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.usa TO Administrador;
GRANT SELECT ON TABLE p.usa TO Trabajador;

CREATE TABLE p.envia
(
	encargo_codigo p.tipo_codigo NOT NULL,
	producto_codigo p.tipo_codigo NOT NULL, 
	cantidad_kg float NOT NULL,
	PRIMARY KEY (encargo_codigo,producto_codigo),
	FOREIGN KEY (encargo_codigo) REFERENCES p.encargo
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	FOREIGN KEY (producto_codigo) REFERENCES p.producto
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.envia TO Administrador;
GRANT SELECT ON TABLE p.envia TO Trabajador;

CREATE TABLE p.participa
(
	codigo p.tipo_codigo NOT NULL,
	ci p.tipo_ci NOT NULL,
	PRIMARY KEY(codigo, ci),
	FOREIGN KEY(codigo) REFERENCES p.encargo(codigo)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	FOREIGN KEY(ci) REFERENCES p.trabajador(ci)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.participa TO Administrador;
GRANT SELECT ON TABLE p.participa TO Trabajador;

CREATE TABLE p.funcion_part
(
	codigo p.tipo_codigo NOT NULL,
	ci p.tipo_ci NOT NULL,
	funcion p.tipo_funcion NOT NULL,
	PRIMARY KEY(codigo, ci, funcion),
	FOREIGN KEY(codigo) REFERENCES p.encargo
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	FOREIGN KEY(ci) REFERENCES p.trabajador
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.funcion_part TO Administrador;
GRANT SELECT ON TABLE p.funcion_part TO Trabajador;

CREATE TABLE p.labora
(
	id p.tipo_codigo NOT NULL,
	ci p.tipo_ci NOT NULL,
	PRIMARY KEY(id, ci),
	FOREIGN KEY(id) REFERENCES p.etapa
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	FOREIGN KEY(ci) REFERENCES p.trabajador
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.labora TO Administrador;
GRANT SELECT ON TABLE p.labora TO Trabajador;

CREATE TABLE p.especialidad_maestro(
	ci_maestro p.tipo_ci NOT NULL,
	especialidad varchar(30) NOT NULL,
	PRIMARY KEY (ci_maestro, especialidad),
	FOREIGN KEY (ci_maestro) REFERENCES p.trabajador
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.especialidad_maestro TO Administrador;
GRANT SELECT ON TABLE p.especialidad_maestro TO Trabajador;

CREATE TABLE p.curso(
	codigo p.tipo_codigo PRIMARY KEY NOT NULL,
	nombre varchar(60) NOT NULL,
	horario time NOT NULL,
	ci_maestro p.tipo_ci NOT NULL,
	FOREIGN KEY (ci_maestro) REFERENCES p.trabajador
		ON UPDATE CASCADE
		ON DELETE CASCADE
);	
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.curso TO Administrador;
GRANT SELECT ON TABLE p.curso TO Trabajador;

CREATE TABLE p.asiste(
	ci_estudiante p.tipo_ci NOT NULL,
	codigo p.tipo_codigo NOT NULL,		
	PRIMARY KEY (ci_estudiante, codigo),
	FOREIGN KEY (ci_estudiante) REFERENCES p.estudiante
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.asiste TO Administrador;
GRANT SELECT ON TABLE p.asiste TO Trabajador;

CREATE TABLE p.vende(
	rif p.tipo_ci NOT NULL,
	codigo p.tipo_codigo NOT NULL,
	ci p.tipo_ci NOT NULL,
	cantidad int NOT NULL,		/* esta cantidad no deberia ser en kg? */
	fecha date NOT NULL,
	costo p.tipo_numerico NOT NULL,
	PRIMARY KEY (rif, codigo, ci),
	FOREIGN KEY (rif) REFERENCES p.empresa
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	FOREIGN KEY (codigo) REFERENCES p.producto
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	FOREIGN KEY (ci) REFERENCES p.persona
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.vende TO Administrador;
GRANT SELECT ON TABLE p.vende TO Trabajador;

CREATE TABLE p.necesita(
	codigo_producto p.tipo_codigo NOT NULL,
	codigo_ingrediente p.tipo_codigo NOT NULL,
	cantidad int NOT NULL,		/* esta cantidad no deberia ser en kg? */
	PRIMARY KEY (codigo_producto, codigo_ingrediente),
	FOREIGN KEY (codigo_producto) REFERENCES p.producto
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	FOREIGN KEY (codigo_ingrediente) REFERENCES p.producto
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
/* Asignando los permisos necesarios a mis usuarios*/
GRANT ALL ON TABLE p.necesita TO Administrador;
GRANT SELECT ON TABLE p.necesita TO Trabajador;
