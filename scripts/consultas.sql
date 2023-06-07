SET search_path TO maneja_seguro;


-- a) Devolver clientes que solo han hecho un solo curso:

SELECT cliente.dni_cliente, cliente.nombre, asiste.cod_clase
FROM cliente
JOIN asiste ON cliente.dni_cliente = asiste.dni_cliente
GROUP BY cliente.dni_cliente, cliente.nombre, asiste.cod_clase
HAVING COUNT(asiste.cod_clase) = 1;

-- b) Listar los cursos con el total de costo de sus materiales:

SELECT clase.cod_clase, clase.nombre, material.descripcion, SUM(material.costo) AS total_costo_materiales
FROM clase
JOIN material ON clase.cod_clase = material.cod_clase
GROUP BY clase.cod_clase, clase.nombre, material.descripcion;


-- c) Listar los instructores (todos sus datos) que no han sido responsables de ningún curso:
SELECT instructor.*
FROM instructor
LEFT JOIN clase ON instructor.dni_instructor = clase.dni_instructor
WHERE clase.cod_clase IS NULL;



-- d) Consultas propias:

-- Obtener los cursos en los que el costo total de los materiales es superior a cierto valor:
SELECT clase.cod_clase, clase.nombre, SUM(material.costo) AS total_costo_materiales
FROM clase
JOIN material ON clase.cod_clase = material.cod_clase
GROUP BY clase.cod_clase, clase.nombre
HAVING SUM(material.costo) > 30;


-- Contar cuántos clientes han realizado al menos un curso:
SELECT COUNT(DISTINCT dni_cliente) AS cantidad_clientes
FROM asiste;

-- Obtener el número total de clientes inscriptos en cada clase:
SELECT cod_clase, COUNT(DISTINCT dni_cliente) AS total_clientes
FROM asiste
GROUP BY cod_clase;

-- Obtener la cantidad total de cursos que imparte cada instructor:
SELECT dni_instructor, COUNT(*) AS total_cursos
FROM clase
GROUP BY dni_instructor;





