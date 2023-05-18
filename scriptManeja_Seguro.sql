DROP SCHEMA if exists maneja_seguro;
CREATE SCHEMA maneja_seguro;

SET search_path TO maneja_seguro;

DROP TABLE IF EXISTS cliente;
CREATE TABLE cliente (
    dni_cliente integer NOT NULL PRIMARY KEY,
    nombre varchar(20) NOT NULL,
    apellido varchar(20) NOT NULL,
    direccion varchar(50) NOT NULL,
	CONSTRAINT pkCliente PRIMARY KEY (dni_cliente)
);

DROP TABLE IF EXISTS secretaria;
CREATE TABLE secretaria (
    dni_secretaria integer NOT NULL PRIMARY KEY,
    nombre varchar(20) NOT NULL,
    apellido varchar(20) NOT NULL,
    direccion varchar(50) NOT NULL,
    titulo varchar(20) NOT NULL,
	CONSTRAINT pkSecretaria PRIMARY KEY (dni_secretaria)
);

DROP TABLE IF EXISTS mecanico;
CREATE TABLE mecanico (
    dni_mecanico integer NOT NULL PRIMARY KEY,
    nombre varchar(20) NOT NULL,
    apellido varchar(20) NOT NULL,
    direccion varchar(50) NOT NULL,
	CONSTRAINT pkMecanico PRIMARY KEY (dni_mecanico)
);

DROP TABLE IF EXISTS telefono;
CREATE TABLE telefono (
    nro_telefono integer NOT NULL,
    dni_mecanico integer NOT NULL,
    CONSTRAINT pktelefono PRIMARY KEY (nro_telefono, dni_mecanico),
    CONSTRAINT fkmecanico FOREIGN KEY (dni_mecanico) REFERENCES mecanico ON DELETE CASCADE
);

DROP TABLE IF EXISTS cargo;
CREATE TABLE cargo (
    cod_cargo serial NOT NULL PRIMARY KEY,
    descripcion varchar(100) NOT NULL,
	CONSTRAINT pkCod_cargo PRIMARY KEY (cod_cargo)
);

DROP TABLE IF EXISTS instructor;
CREATE TABLE instructor (
    dni_instructor integer NOT NULL PRIMARY KEY,
    nombre varchar(20) NOT NULL,
    apellido varchar(20) NOT NULL,
    direccion varchar(50) NOT NULL,
    carnet varchar(20) NOT NULL,
    cod_cargo integer NOT NULL,
	CONSTRAINT pkInstructor PRIMARY KEY (dni_instructor);
    CONSTRAINT tipo_carnet CHECK (carnet IN ('B2','B3','C1')),
    CONSTRAINT fkcargo FOREIGN KEY (cod_cargo) REFERENCES cargo ON DELETE CASCADE
);

DROP TABLE IF EXISTS clase;
CREATE TABLE clase (
    cod_clase serial NOT NULL PRIMARY KEY,
    nombre varchar(20) NOT NULL,
    descripcion varchar(100) NOT NULL,
    cupo_max integer NOT NULL,
    dni_secretaria integer NOT NULL,
    dni_instructor integer NOT NULL,
    CONSTRAINT pkCod_clase PRIMARY KEY (cod_clase),
    CONSTRAINT fksecretaria FOREIGN KEY (dni_secretaria) REFERENCES secretaria ON DELETE CASCADE,
    CONSTRAINT fkinstructor_res FOREIGN KEY (dni_instructor) REFERENCES instructor ON DELETE CASCADE
);

DROP TABLE IF EXISTS asiste;
CREATE TABLE asiste (
    dni_cliente integer NOT NULL,
    cod_clase integer NOT NULL,
    CONSTRAINT pkasiste PRIMARY KEY (dni_cliente, cod_clase),
    CONSTRAINT fkcliente FOREIGN KEY (dni_cliente) REFERENCES cliente ON DELETE CASCADE,
    CONSTRAINT fkclase_asis FOREIGN KEY (cod_clase) REFERENCES clase ON DELETE CASCADE
);

DROP TABLE IF EXISTS dicta;
CREATE TABLE dicta (
    dni_instructor integer NOT NULL,
    cod_clase integer NOT NULL,
    CONSTRAINT pkdicta PRIMARY KEY (dni_instructor, cod_clase),
    CONSTRAINT fkinstructor_dicta FOREIGN KEY (dni_instructor) REFERENCES instructor ON DELETE CASCADE,
    CONSTRAINT fkclase_dicta FOREIGN KEY (cod_clase) REFERENCES clase ON DELETE CASCADE
);

DROP TABLE IF EXISTS material;
CREATE TABLE material (
    nro_material integer NOT NULL PRIMARY KEY,
    descripcion varchar(100) NOT NULL,
    costo integer NOT NULL,
    dni_instructor integer NOT NULL,
    cod_clase integer NOT NULL,
    CONSTRAINT pkNro_material PRIMARY KEY (nro_material),
    CONSTRAINT fkdicta FOREIGN KEY (dni_instructor, cod_clase) REFERENCES dicta ON DELETE CASCADE
);

DROP TABLE IF EXISTS auditoria_costos;
CREATE TABLE auditoria_costos (
    id serial PRIMARY KEY,
    nro_material integer NOT NULL,
    fecha_del_cambio timestamp NOT NULL,
    costo_anterior integer NOT NULL,
    costo_nuevo integer NOT NULL,
    usuario varchar(50) NOT NULL
);

create or replace function cambios_auditoria() 
returns Trigger as 
$$
	begin
		INSERT INTO auditoria_costos (nro_material, fecha_del_cambio, costo_anterior, costo_nuevo, usuario)
    	VALUES (NEW.nro_material, NOW(), OLD.costo, NEW.costo, current_user);
			RETURN NEW;
	end
$$ LANGUAGE 'plpgsql';

create or replace trigger cambios_auditoria
after update on material
for each row
execute function cambios_auditoria();

