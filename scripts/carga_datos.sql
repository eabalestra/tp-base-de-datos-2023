SET search_path TO maneja_seguro;

-- Insertar registros en la tabla "cliente"
INSERT INTO cliente (dni_cliente, nombre, apellido, direccion) 
VALUES (1, 'Juan', 'Pérez', 'Calle A 123');

INSERT INTO cliente (dni_cliente, nombre, apellido, direccion) 
VALUES (2, 'María', 'González', 'Calle B 456');

INSERT INTO cliente (dni_cliente, nombre, apellido, direccion) 
VALUES (3, 'Carlos', 'López', 'Calle C 789');

INSERT INTO cliente (dni_cliente, nombre, apellido, direccion) 
VALUES (4, 'Agustín', 'Balestra', 'Calle SM 1144');

-- Insertar registros en la tabla "secretaria"
INSERT INTO secretaria (dni_secretaria, nombre, apellido, direccion, titulo) 
VALUES (1, 'Laura', 'Gómez', 'Calle D 123', 'Secretaria');

INSERT INTO secretaria (dni_secretaria, nombre, apellido, direccion, titulo) 
VALUES (2, 'Gimena', 'Perez', 'Calle AD 453', 'Secretaria');

-- Insertar registros en la tabla "mecanico"
INSERT INTO mecanico (dni_mecanico, nombre, apellido, direccion) 
VALUES (1, 'Pedro', 'García', 'Calle E 789');

INSERT INTO mecanico (dni_mecanico, nombre, apellido, direccion) 
VALUES (2, 'Luis', 'Martínez', 'Calle F 456');

-- Insertar registros en la tabla "telefono"
INSERT INTO telefono (nro_telefono, dni_mecanico) 
VALUES (123456789, 1);

INSERT INTO telefono (nro_telefono, dni_mecanico) 
VALUES (987654321, 2);

-- Insertar registros en la tabla "cargo"
INSERT INTO cargo (cod_cargo, descripcion) 
VALUES (1, 'Profesor Teórico');

INSERT INTO cargo (cod_cargo, descripcion) 
VALUES (2, 'Instructor de Manejo');

-- Insertar registros en la tabla "instructor"
INSERT INTO instructor (dni_instructor, nombre, apellido, direccion, carnet, cod_cargo) 
VALUES (1, 'Juan', 'López', 'Calle G 789', 'B2', 1);

INSERT INTO instructor (dni_instructor, nombre, apellido, direccion, carnet, cod_cargo) 
VALUES (2, 'Ana', 'Rodríguez', 'Calle H 456', 'B2', 2);

-- Insertar registros en la tabla "clase"
INSERT INTO clase (cod_clase, nombre, descripcion, cupo_max, dni_secretaria, dni_instructor) 
VALUES (1, 'Clase de Manejo Básico', 'Fundamentos de manejo de vehículos', 10, 1, 2);

INSERT INTO clase (cod_clase, nombre, descripcion, cupo_max, dni_secretaria, dni_instructor) 
VALUES (2, 'Clase de Manejo Avanzado', 'Manejo en situaciones de tráfico complejas', 7, 2, 2);

-- Insertar registros en la tabla "asiste"
INSERT INTO asiste (dni_cliente, cod_clase)
VALUES (1, 1);

INSERT INTO asiste (dni_cliente, cod_clase) 
VALUES (2, 1);

INSERT INTO asiste (dni_cliente, cod_clase) 
VALUES (3, 2);

-- Insertar registros en la tabla "dicta"
INSERT INTO dicta (dni_instructor, cod_clase)
VALUES (1, 1);

INSERT INTO dicta (dni_instructor, cod_clase)
VALUES (2, 2);

-- Insertar registros en la tabla "material"
INSERT INTO material (nro_material, descripcion, costo, dni_instructor, cod_clase)
VALUES (1, 'Manual de conducción', 50, 1, 1);

INSERT INTO material (nro_material, descripcion, costo, dni_instructor, cod_clase)
VALUES (2, 'Conos de señalización', 30, 2, 2);
