(clear)

; ***************************************************************************************************************************
; Una regla en Jess es algo asi como una sentencia if... then en un lenguaje de programacion convencional, pero no es
; utilizada de la misma forma.
; Mientras que la sentencia if() es ejecutada de acuerdo a cuando el programador las escribe, las reglas en Jess son ejecutadas
; cuando sea que la condicion dentro del if es satisfecha mientras que el motor de reglas este ejecutandose.    

; Nota: Es posible segmentar las reglas en "modulos", la funcion "(focus nombr_del_modulo)" sirve para que el motor trabaje con las reglas de ese modulo 
; Operadores logicos disponibles:
; < (less than)
; <= (less than or equal to)
; > (greater than)
; <= (greater than or equal to)
; == (equals)
; != (not equal to)
; <> (not equal to, alternate syntax)
; && (and)
; || (or)
; not () (De no ser lo que esta dentro del parentesis)
; exists () (De existir un hecho que sigue el patron dentro de los parentesis)
; test() (Para comprobar el resultado de una operacion matematica dentro de sus parentesis)
;
; ***************************************************************************************************************************
; Formato de declaracion de reglas:
; (defrule nombre-regla
;    ["Descripcion de la regla (opcional)"]
;    [(declare (salience value)             ;(opcional)
;              (node-index-hash value)      ;(opcional)
;              (auto-focus TRUE | FALSE)    ;(opcional)
;              (no-loop TRUE | FALSE))]     ;(opcional)
;    conditional element*
;    =>
;    function call* 
; ***************************************************************************************************************************
; Sea que existe esta plantilla de hechos:
; (deftemplate person (slot firstName) (slot lastName) (slot age))
;
; Ejemplo 1:
;
;(defrule hola-amiguitos
;    "Regla para dar un saludo especial a un nino menor a 3 anios"
;    (person {age < 3}) ; La condicion dentro del 'if()' no puede tener llamado a funciones, solo 'patrones' de hechos 
;    =>
;    (printout t "Hola amiguito!" crlf)) ; La parte de 'entonces' del 'if()' debe contener solo llamado a funciones
;
; Ejemplo 2:
; 
; (defrule teenager
;    ?p <- (person {age > 12 && age < 20} (firstName ?name)) ; Al hacer esto la variable ?p queda enlazada al hecho que se esta tratando 
;    =>
;    (printout t ?name " is " ?p.age " years old." crlf))
;
; Ejemplo 3:
;
; (defrule teenage-or-bob
;    (person {(age > 12 && age < 20) || firstName == Bob})
;    =>
;    (printout t "The person is a teenager, or is named 'Bob'." crlf))
;
; Ejemplo 4:
;
; (defrule two-same-age-different-name
;     "Regla con dos patrones"
;    ?person1 <- (person)
;    ?person2 <- (person {age == person1.age &&lastName != person1.lastName})
;    =>(printout t "Found two different " ?person1.age "-year-old people." crlf))
;
; Ejemplo 5:
;
; (defrule example-6
;  (declare (salience -100)) ; Es posible declarar un orden de 'prioridad' a las reglas, entre mayor el valor de silencio, mas prioridad tiene
;  (command exit-when-idle)
;  =>
;  (printout t "exiting..." crlf))
;
; Ejemplo 6:
;
;
; ***************************************************************************************************************************
; Declaracion de reglas:

; ***************************************************************************************************************************
;(watch all)    ; Imprime algunos diagnosticos que pueden ser utiles
(reset)
;(run)          ; Aunque las reglas se activen al introducir los hechos, no se ejecutan hasta que corras este comando