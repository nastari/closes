(ns noob.core
  (:gen-class))

(defn -main
  "Now a know a little."
  ; [& args]
  []
  "Working...")

(inc 2)
; => 3

(map inc [2 3 4])
; => (3 4 5)

(map inc '(22 33 44))
; => (23 34 45)

(def products [{:name "Tenis" :price 199.99} {:name "Celular" :price 299.99}])

products
; => [{:name "Tenis", :price 199.99} {:name "Celular", :price 299.99}]

(defn increase_shipping [{:keys [name price]}]
  {:name name :price (+ price 19)})

(map increase_shipping products)
; => ({:name "Tenis", :price 218.99} {:name "Celular", :price 318.99})

(def PROMOTION_FREE_SHIPPING 248.00)

PROMOTION_FREE_SHIPPING
; => 248.0

(defn free_shipping [{:keys [price name] :as product}]
  (if (> price PROMOTION_FREE_SHIPPING)
    {:name name :price (- price 19)}
    product))

(map free_shipping products)
; => (false true)

(defn increase_shipping_with_promo [{:keys [name price]}]
  (if (> price PROMOTION_FREE_SHIPPING)
    (do {:name name :price price})
    (do {:name name :price (+ price 19)})))

(map increase_shipping_with_promo products)
; => ({:name "Tenis", :price 218.99} {:name "Celular", :price 299.99})

(or 5 2)
; 5

(or 5 false)
; 5

(and false true)
; false

(or false true)
; true

products
; => [{:name "Tenis", :price 199.99} {:name "Celular", :price 299.99}]

(get {:name 2} :name)
; 2

(defn get_ [product]
  (get product :name))

(map get_ products)
; => ("Tenis" "Celular")

; Não existem funções especiais no Closure. Ele apenas as executa o que lhe é passado.

; https://www.4clojure.com/problem/3
; Clojure strings are Java strings.
; This means that you can use any of the Java string methods on Clojure strings

(= "HElLO WORLD" (.toUpperCase "hello world"))
; => false

(= "HELLO WORLD" (.toUpperCase "hello world"))
; => true

(conj '(3 4 1) 2 44 99)
; => (99 44 2 3 4 1)

(= '(1 2) (conj '(2) 1))
; true

(def points (conj '(3 4 1) 2 44 99))

points
; => (99 44 2 3 4 1)

(map inc points)
; => (100 45 3 4 5 2)

(list :banana :apple :rice :tomato)
; => (:banana :apple :rice :tomato)

(vector :banana :apple :rice :tomato)
; => [:banana :apple :rice :tomato]

(vec '(:a :c))
; => [:a :c]

(vec (list :banana :apple :rice :tomato))
; => [:banana :apple :rice :tomato]

; clojure reconhece dois tipos de estruturas: representações literais ( numeros,
;  strings, vetores ...) e operações.

5
; => 5

["a" "vector" "of" "strings"]
; => ["a" "vector" "of" "strings"]

(+)
; => 0

products
; => [{:name "Tenis", :price 199.99} {:name "Celular", :price 299.99}]

(defn shipping_charge [{:keys [name price]}]
  (when (< price PROMOTION_FREE_SHIPPING)
    {:item (str "Item: " name) :promotion false :price 19 :comparative (/ 19 price)}))

(map shipping_charge products)
; => ({:item "Item: Tenis", :promotion false, :price 19, :comparative 0.09500475023751187} nil)

(defn risk [severity]
  (str "O risco é: "
       (if (or (= severity :mild) (= severity :easy))
         "leve"
         "possivelmente alto!")))

(risk :warning)
; => "O risco é: possivelmente alto!"

(risk :easy)
; => "O risco é: leve"

(risk :mild)
; => "O risco é: leve"

(risk :caution)
; => "O risco é: possivelmente alto!"

(get {:a 0 :b 1 :c {:aa 11 :bb 22}} :c)
; => {:aa 11, :bb 22}

(get-in {:a 0 :b 1 :c {:aa 11 :bb 22}} [:c :aa])
; 11

(get-in {:a 0 :b 1 :c {:aa {:aaa 111} :bb 22}} [:c :aa :aaa])
; 111
(get-in {:a 0 :b 1 :c {:aa {:aaa 111} :bb 22}} [:c :aa :aab] "Sem retorno;")
; => "Sem retorno;"

; Pesquisar valor no mapa; mapa como função; chave como argumento;

({true 1 false 0} true)
; => 1

({true 1 false 0} false)
; => 0

; ( :chaves -> procurando valor em uma estrutura )

(:false {:true 1 :false 0}) ; = (get { :true 1 :false 0} :false)
; => 0

(:falsiy {:true 1 :false 0} "Nop")
; => "Nop"

; { :message "Cool!" :stars 5 :complain nil }
(defn feedback_user [{:keys [message stars] :as fb}]
  (if (or (> stars 5) (< stars 1) (> (count message) 255))
    "Sorry, maximum stars: 5. Maximum length message: 255"
    "Ok!"))

(feedback_user {:message "Could be better" :stars 3 :complain "ALERT!"})
; => Ok!

(feedback_user {:message "Could be better" :stars -1 :complain "ALERT!"})
; => "Sorry, maximum stars: 5. Maximum length message: 255"

; { :message "Cool!" :stars 5 :complain nil }
(defn feedback_user [{:keys [message stars] :as fb}]
  (if (or (> stars 5) (< stars 1) (> (count message) 255))
    "Sorry, maximum stars: 5. Maximum length message: 255"
    (do
      (println "Cool")
      (let [complain (:complain fb)]
        (if complain
          {:msg message :score stars :report complain}
          {:msg message :score stars})))))

(feedback_user {:message "Could be better" :stars -1 :complain "ALERT!"})
; => "Sorry, maximum stars: 5. Maximum length message: 255"

(feedback_user {:message "Could be better" :stars 2 :complain "ALERT!"})
; => {:msg "Could be better", :score 2, :report "ALERT!"}

(feedback_user {:message "Could be better" :stars 2})
; => {:msg "Could be better", :score 2}

(def notas [7 8 6 10 9])

notas
; => [7 8 6 10 9]

; recuperar elementos no vetor ~notas[2]

(get notas 2)
; 6

(conj notas 3)
; => [7 8 6 10 9 3] ;acrescenta no final do vetor.

(def notas_list '(7 8 6 10 9))

notas_list
; => (7 8 6 10 9)

(conj notas_list 3)
; => (3 7 8 6 10 9) ;acrescenta no início da lista.

; recuperar elementos na lista ~notas_list[2]

(nth notas_list 0)
; 7

(nth notas_list 1)
; 8

;desempenho ;"nth" percorre toda lista até indíce de interesse. "get" em vetores salta direto.

(read-string "(+ 1 2)")
;; => (+ 1 2)

(first (read-string "(+ 1 2)"))
;; => +

(get-in {:a 1 :b 10 :c {100 :d}} [:c 100])
;; => :d

;; keywords can be used as functions that look up the corresponding value in data structure.
(:a {:a 100 :b 101 :c 102})
;; => 100

(get [0 1 2] 0)
;; => 0

(get ["as" 1 2] 0)
;; => "as"

(vector "a" 20 {:b :c})
;; => ["a" 20 {:b :c}]

;; Sets; coleção de valores únicos;
#{1 2 2}
;; Syntax error reading source at (noob/src/noob/core.clj:2:9).
;; Duplicate key: 2

#{12 24}
;; => #{24 12}

(def exams [9 2 3 2])
;; => #'noob.core/exams

(set exams)
;; => #{3 2 9}

(contains? (set exams) 1)
;; => false
;; contains? para sets procura pelo elemento.

(contains? exams 4)
;; => false 
;; contains? para vetor procura pelo indice.

(contains? exams 3)
;; true

(hash-set 55 55 33 44 11 3 3 3 3)
;; => #{55 33 44 3 11} 
;; retorna valores únicos

(:a #{:a :b})
;; => :a

(:c #{:a :b})
;; => nil

(get #{:a :B} :B)
;; :B

;; Verificar o valor existe dentro do Set

(def conjuntos #{5 3 2 9 1})

(get conjuntos 11)
;; => nil

(get conjuntos 9)
;; => 9

(> 5 2 1 0 -1)
;; => true

(defn biggerThanFive [n]
  (if (> n 5)
    (println (str "5: Eu sou maior: " n))
    (println (str "5: Eu sou menor ou igual: " n))))
;; => #'noob.core/biggerThanFive

(def ar [5 9 3 8 1 2 11 3 4 99])
;; => #'noob.core/ar

(defn arrayBiggerThanFive [arr_]
  (map biggerThanFive arr_))
;; => #'noob.core/arrayBiggerThanFive

(arrayBiggerThanFive ar)
;; 5: Eu sou menor ou igual: 5
;; 5: Eu sou maior: 9
;; 5: Eu sou menor ou igual: 3
;; 5: Eu sou maior: 8
;; 5: Eu sou menor ou igual: 1
;; 5: Eu sou menor ou igual: 2
;; 5: Eu sou maior: 11
;; 5: Eu sou menor ou igual: 3
;; 5: Eu sou menor ou igual: 4
;; 5: Eu sou maior: 99
;; => (nil nil nil nil nil nil nil nil nil nil)

(clojure.string/join ", " ["a" "b" "c"])
;; => "a, b, c"


(clojure.string/join " -> " ["Brazil" "Rio de Janeiro" "Volta Redonda"])
;; => "Brazil -> Rio de Janeiro -> Volta Redonda"

(defn things [one & rest] rest )

(things 2 3 "a" { :foo "bar"})
;; => (3 "a" { :foo "bar"})

(defn my-first [[first]] first )

(my-first ["primeiro-elemento" 2])
;; => "primeiro-elemento"

(defn my-first [[first] & rest] { :first first :rest rest })

(my-first ["primeiro-elemento" 2] "a" 123)
;; => {:first "primeiro-elemento", :rest ("a" 123)}
;; o arg rest 'vira' uma lista

(map (fn [x] x) [23 4 2])
;; => (23 4 2)

(map (fn [x] x) '(99 100 101))
;; => (99 100 101)

(map (fn [x] x) #{99 343})
;; => (343 99)

(map (fn [x] x) {99 343})
;; => ([99 343])

(fn [x y] { :x x :y y})
;; => #function[noob.core/eval11225/fn--11226]

;; lembra do (operator operand1 operand2) então:

((fn [x y & rest] (println rest) {:x x :y y}) 50 2)
;; => {:x 50, :y 2}

#(* %1 %2)
;; => #function[noob.core/eval11368/fn--11369]

(#(* %1 %2) 20 2)
;; => 40

(map #(- % 2) [5 2])
;; => (3 0)


(map #(- %1 %2) [250 22] [240 21])
;; funcionamento interessante 250-240; 22 -21; ...;
;; => (10 1)
