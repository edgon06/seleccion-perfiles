% Reglas
%filtrar_cargo(CIP, Cargo):-.

%filtrar_cargo_anterior(CIP):-.
%filtrar_experiencia(CIP):-.
%filtrar_grupo_ocupacional(CIP):-.

%filtrar_cargo_cargo_anterior(CIP):-.
%filtrar_cargo_experiencia(CIP):-.

%filtrar_cargo_grupo_ocupacional(CIP):-.
%filtrar_cargo_anterior_experiencia(CIP):-.
%filtrar_cargo_anterior_grupo_ocupacional(CIP):-.


%filtrar_experiencia_grupo_ocupacional(CIP):-.
%filtrar_centro_regional():-.
%filtrar_edad():-.
%filtrar_sexo():-.
buscar1_(Grupo_ocupacional,C):- cargo(Cargo_actual,_,Grupo_ocupacional,_,_), empleado(C,_,_,_,Cargo_actual,_,_,_,_,_,_,_).


% Hechos
%
% cargo(nombre, familia, gupo_ocupacional, nivel_funcional,
% grupo_laboral).

% empleado(cedula, nombre, apellido, telefono, cargo_actual,
% sexo,f_nacimiento, formacion_academica, experiencia,
% referencias_laborales, centro_regional, pruebas_psicotecnicas).
%

cargo('ABOGADO','Servicios Legales, Seguridad Pública y Actividades Afines','Servicios Legales','Técnico y Profesional','Profesional').

empleado('8-940-1565','Edwin','Gonzalez','62006000','ABOGADO','m','1999-02-06','Lic. Ing. de Sistemas y Computacion','2 años - ABOGADO','63000000 - Sr. Paz','Panama Oeste','Administrador de Soporte Tecnico').

