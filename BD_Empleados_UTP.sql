CREATE DATABASE UTP_empleados;

USE UTP_empleados;

-- Modificar en un futuro para hacer foranea el codigo de cargo en lugar de nombre
-- De momento Empleados tiene 12 datos
CREATE TABLE Empleados(
CIP VARCHAR(20) NOT NULL,
Nombre VARCHAR(20),
Apellido VARCHAR(500),
Telefono VARCHAR(10),
Cargo_actual VARCHAR(200),
Sexo VARCHAR(1),
Fecha_nacimiento VARCHAR(10),
Formacion_academica VARCHAR(200),
Experiencia VARCHAR(100),
Referencias VARCHAR(200),
Centro_Regional VARCHAR(30),
Pruebas_psicotecnicas VARCHAR(200),

PRIMARY KEY (CIP)
-- FOREIGN KEY (Cargo_actual) REFERENCES Cargos(Nombre)
);

INSERT INTO Empleados VALUES ("8-333-3333","Maikol","Marin","63006000","Administrador de Soporte Tecnico","m","1998-07-20","Lic. Ing. de Sistemas y Computacion","2 anios - Administrador de Recursos Informaticos; 5 meses - Administrador de Soporte Tecnico","63000000 - Sra. Petronila","Panama Oeste","Administrador de Soporte Tecnico");

CREATE TABLE Cargos(
Codigo INT AUTO_INCREMENT NOT NULL ,
Nombre VARCHAR(200),
Familia VARCHAR(100),
Grupo_ocupacional VARCHAR(100),
Nivel_funcional VARCHAR(1000),
Grupo_laboral VARCHAR(10),
PDF  VARCHAR(1000),

PRIMARY KEY (Codigo)
);

-- INSERT INTO Cargos (Nombre, Familia, Grupo_ocupacional, Nivel_funcional, Grupo_laboral) VALUES ("ABOGADO","Servicios Legales, Seguridad Pública y Actividades Afines","Servicios Legales","Técnico y Profesional ","Profesional"); 
-- INSERT INTO Cargos (Nombre, Familia, Grupo_ocupacional, Nivel_funcional, Grupo_laboral) VALUES ("ABOGADO AUXILIAR","Servicios Legales, Seguridad Pública y Actividades Afines","Servicios Legales","Técnico y Profesional ","Técnico "); 
-- INSERT INTO Cargos (Nombre, Familia, Grupo_ocupacional, Nivel_funcional, Grupo_laboral) VALUES ("ADMINISTRADOR DE SOPORTE TECNICO","Administración, Asistencia Técnica y Actividades Afines","Procesamiento Electrónico de Datos","Técnico y Profesional ","Técnico "); 

-- SELECT * FROM Cargos;