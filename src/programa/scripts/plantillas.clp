(clear)

; ***************************************************************************************************************************
; Los hechos son como las tablas de una BD relacional.
; Una plantilla define la estructura de un tipo de hecho.

; Nota: La funcion facts sirve para ver la agenda de hechos en la 'memoria de trabajo' del motor: (facts) 
; La funcion retract elimina el hecho del id dado como parametro: (retract fact_id), donde fact_id es el id del hecho.
; ***************************************************************************************************************************
; Formato de declaracion de plantillas:

; (deftemplate nombre-plantilla
;     [extends nombre-plantilla-en-la-que-se-basa]                      ;(opcional) es posible definir una plantilla basada en otra definida anteriormente, en cuyo caso se 'extiende' de esa plantilla
;     ["Descripcion de la plantilla (opcional)"]
;     [(declare  (slot-specific TRUE | FALSE)                           ;(opcional)
;                (backchain-reactive TRUE | FALSE)                      ;(opcional)
;                (from-class class name)                                ;(opcional)
;                (include-variables TRUE | FALSE)                       ;(opcional)
;                (ordered TRUE | FALSE))]                               ;(opcional)
;      (slot | multislot nombre-del-slot                                ;(el mulislot es opcional)      
;          ([(type ANY | INTEGER | FLOAT | NUMBER | SYMBOL | STRING |
;                 LEXEME | OBJECT | LONG)]                              ;(el tipo del slot es opcional)
;          [(default default value)]                                    ;(puedes asignar un valor por defecto a un slot en caso de no ser llenado)
;          [(default-dynamic expression)]                               ;(opcional)
;          [(allowed-values expression+)])*)                            ;(opcional)
;
; ***************************************************************************************************************************
; Ejemplo:

;(deftemplate auto
;  "Un auto en especifico."
;  (slot marca)
;  (slot modelo)
;  (slot anio (type INTEGER))
;  (slot color (default blanco)))
;
; Insercion de un hecho:
; (assert (auto (modelo LeBaron) (marca Chrysler) (anio 1997)))
;
; ***************************************************************************************************************************
; Declaracion de plantillas:

(deftemplate cargo "esta plantilla definira los hechos de que es un cargo"
             (slot nombre)
             (slot familia)
             (slot grupo_ocupacional)
             (slot nivel_funcional)
             (slot grupo_laboral)
)

(deftemplate empleado "esta plantilla definira los datos que puede tener un hecho que represente un empleado"
             (slot cedula)
             (slot nombre)
             (slot apellido)
             (slot telefono)
             (slot cargo_actual)
             (slot sexo)
             (slot f_nacimiento)
             (slot formacion_academica)
             (slot experiencia)
             (slot referencias_laborales)
             (slot centro_regional)
             (slot pruebas_psicotecnicas)
)

; ***************************************************************************************************************************
; Insercion de Hechos independientemente:
;(assert (cargo (nombre "ABOGADO")
;               (familia "Servicios Legales, Seguridad Pública y Actividades Afines")
;                (grupo_ocupacional "Servicios Legales")
;                (nivel_funcional "Técnico y Profesional")
;                (grupo_laboral "Profesional")))

;(assert (empleado (cedula "8-940-1565") 
;                    (nombre "Edwin") 
;                    (apellido "Gonzalez") 
;                    (telefono "62006000")
;                    (cargo_actual "Administrador de Recursos Informaticos")
;                    (sexo "m")
;                    (f_nacimiento "1999-02-06")
;                    (formacion_academica "Lic. Ing. de Sistemas y Computacion") 
;                    (experiencia "2 anios - Administrador de Recursos Informaticos")
;                    (referencias_laborales "63000000 - Sr. Paz") 
;                    (centro_regional "Panama Oeste")
;                    (pruebas_psicotecnicas "Administrador de Soporte Tecnico")))

;(assert (empleado (cedula "8-333-3333")
;                    (nombre "Maikol") 
;                    (apellido "Marin")  
;                    (telefono "63006000")
;                    (cargo_actual "Administrador de Soporte Tecnico")
;                    (sexo "m")
;                    (f_nacimiento "1998-07-20")
;                    (formacion_academica "Lic. Ing. de Sistemas y Computacion") 
;                    (experiencia "2 anios - Administrador de Recursos Informaticos; 5 meses - Administrador de Soporte Tecnico")
;                    (referencias_laborales "63000000 - Sra. Petronila") 
;                    (centro_regional "Panama Oeste")
;                    (pruebas_psicotecnicas "Administrador de Soporte Tecnico")))

; ***************************************************************************************************************************
; Insercion de varios hechos con una sola instruccion/comando:
 (deffacts cargo "insercion de algunos cargos"
  (nombre "ABOGADO" familia "Servicios Legales, Seguridad Pública y Actividades Afines" grupo_ocupacional "Servicios Legales" nivel_funcional "Técnico y Profesional" grupo_laboral "Profesional")
  (nombre "ADMINISTRADOR" familia "Administración, Asistencia Técnica y Actividades Afines" grupo_ocupacional "Administración General" nivel_funcional "Técnico y Profesional" grupo_laboral "Técnico")
  )

; ***************************************************************************************************************************
;(watch all)
(reset)
;(run)