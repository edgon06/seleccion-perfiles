(clear)

; ***************************************************************************************************************************
; La 'memoria de trabajo' de Jess es similar a una base de datos, está llena de datos estructurados e indexados.
; La funcion defquery te permite crear un tipo especial de regla. Mientras que una regla normal actua de forma espontánea, las
; consultas son usadas para buscar dentro de la memoria de trabajo de forma directamente programada. Una consulta te devuelve un
; objeto tipo jess.QueryResult que te da acceso a una lista con todos los resultados que concidan con tus parametros dados.
;
; Nota: 
; 
; ***************************************************************************************************************************
; Formato de declaracion de consultas:
; (defquery nombre-consulta
;     ["Descripcion de la consulta (opcional)"]
;     [(declare (variables variable+)             ; Se declara(n) la(s) variable(s) que sera(n) utilizadas como parametros de entrada para la consulta
;               (node-index-hash value)           ; (opcional)
;               (max-background-rules value))]    ; (opcional)
;     conditional element* )                      ; Condicion que se debe cumplir para que un hecho sea un resultado de la consulta
;
; ***************************************************************************************************************************
; Sean los siguientes hechos:
;(deftemplate person (slot firstName) (slot lastName) (slot age))
;(deffacts data
;  (person (firstName Fred)   (lastName Smith)    (age 12))
;  (person (firstName Fred)   (lastName Jones)    (age 9))
;  (person (firstName Bob)    (lastName Thomas)   (age 32))
;  (person (firstName Bob)    (lastName Smith)    (age 22))
;  (person (firstName Pete)   (lastName Best)     (age 21))
;  (person (firstName Pete)   (lastName Smith)    (age 44))
;  (person (firstName George) (lastName Smithson) (age 1))
;  )
;
; Ejemplo:
;
;(defquery buscar-por-nombre
;  "Encuentra personas con un dado apellido"
;  (declare (variables ?ln))                            ; Se declara la variable a utilizar como parametro de busqueda
;  (person (lastName ?ln) (firstName ?fn) (age ?age)))  
; Se especifica el patron que debe seguir el hecho para ser valido para la consulta, 
; se guardan los datos en variables para ser consultadas despues


; ***************************************************************************************************************************
; Declaracion de consultas:

; ***************************************************************************************************************************
;(watch all)
(reset)
;(run)