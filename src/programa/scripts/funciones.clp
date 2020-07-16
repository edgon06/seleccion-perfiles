(clear)

; ***************************************************************************************************************************
; Nota: La funcion bind sirve para asignar valor a variables en Jess: (bind ?x 3)
; Jess está basado en CLIPS, el cual tiene una sintaxis
; ***************************************************************************************************************************
; Formato de declaracion de funciones:

; (deffunction <nombre_de_funcion> [<"Descripcion de la funcion (opcional)">] (<Parametro(s)>*)
;   <Expresion(es)>* [<return variable_a_retornar>])
;
; ***************************************************************************************************************************
; Ejemplo:
;
;(deffunction max (?a ?b)
; (if (> ?a ?b) then
;      (return ?a)
;  else
;      (return ?b)))
;
; ***************************************************************************************************************************
; Declaracion de funciones:

; ***************************************************************************************************************************
;(watch all)
(reset)
;(run)