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

(get { :a 0 :b 1 :c { :aa 11 :bb 22 }} :c)
; => {:aa 11, :bb 22}

(get-in { :a 0 :b 1 :c { :aa 11 :bb 22 }} [:c :aa])
; 11

(get-in { :a 0 :b 1 :c { :aa { :aaa 111 } :bb 22 }} [:c :aa :aaa])
; 111
(get-in { :a 0 :b 1 :c { :aa { :aaa 111 } :bb 22 }} [:c :aa :aab] "Sem retorno;")
; => "Sem retorno;"

; Pesquisar valor no mapa; mapa como função; chave como argumento;

({true 1 false 0} true)
; => 1

({ true 1 false 0} false)
; => 0

; ( :chaves -> procurando valor em uma estrutura )

(:false { :true 1 :false 0}) ; = (get { :true 1 :false 0} :false)
; => 0

(:falsiy { :true 1 :false 0} "Nop") 
; => "Nop"

; { :message "Cool!" :stars 5 :complain nil }
(defn feedback_user [{:keys [message stars] :as fb }]
  (if (or (> stars 5) (< stars 1) (> (count message) 255))
    "Sorry, maximum stars: 5. Maximum length message: 255"
    "Ok!"
    ))

(feedback_user {:message "Could be better" :stars 3 :complain "DENUNCIA!"})
; => Ok!

(feedback_user {:message "Could be better" :stars 1 :complain "DENUNCIA!"})
; => "Sorry, maximum stars: 5. Maximum length message: 255"

; { :message "Cool!" :stars 5 :complain nil }
(defn feedback_user [{:keys [message stars] :as fb }]
  (if (or (> stars 5) (< stars 1) (> (count message) 255))
    "Sorry, maximum stars: 5. Maximum length message: 255"
    (do
      (println "Cool")
      (let [complain (:complain fb)]
        (if complain 
            {:msg message :score stars :report complain}
            {:msg message :score stars })    
        )
      )
    ))

(feedback_user {:message "Could be better" :stars -1 :complain "ALERT!"})
; => "Sorry, maximum stars: 5. Maximum length message: 255"

(feedback_user {:message "Could be better" :stars 2 :complain "ALERT!"})
; => {:msg "Could be better", :score 2, :report "ALERT!"}

(feedback_user {:message "Could be better" :stars 2 })
; => {:msg "Could be better", :score 2}