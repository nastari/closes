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

(defn things [one & rest] rest)

(things 2 3 "a" {:foo "bar"})
;; => (3 "a" { :foo "bar"})

(defn my-first [[first]] first)

(my-first ["primeiro-elemento" 2])
;; => "primeiro-elemento"

(defn my-first [[first] & rest] {:first first :rest rest})

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

(fn [x y] {:x x :y y})
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

(#(* 2 %) 2)
;; 4

(defn bigger-than-maker
  [num]
  #(> % num))

(def big-than-5 (bigger-than-maker 5))

(big-than-5 2)
;; => false

(big-than-5 19)
;; => true

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])
;; => #'noob.core/asym-hobbit-body-parts

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(matching-part {:name "left-ear" :size 1})
;; => {:name "right-ear", :size 1}

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"

  [asym-body-parts]

  (loop [remaining-asym-parts asym-body-parts final-body-parts []]

    (if (empty? remaining-asym-parts)

      final-body-parts

      (let [[part & remaining] remaining-asym-parts]

        (recur remaining

               (into final-body-parts

                     (set [part (matching-part part)])))))))

(symmetrize-body-parts asym-hobbit-body-parts)
; => [{:name "head", :size 3}
      ;; {:name "left-eye", :size 1}
      ;; {:name "right-eye", :size 1}
      ;; {:name "left-ear", :size 1}
      ;; {:name "right-ear", :size 1}
      ;; {:name "mouth", :size 1}
      ;; {:name "nose", :size 1}
      ;; {:name "neck", :size 2}
      ;; {:name "left-shoulder", :size 3}
      ;; {:name "right-shoulder", :size 3}
      ;; {:name "left-upper-arm", :size 3}
      ;; {:name "right-upper-arm", :size 3}
      ;; {:name "chest", :size 10}
      ;; {:name "back", :size 10}
      ;; {:name "left-forearm", :size 3}
      ;; {:name "right-forearm", :size 3}
      ;; {:name "abdomen", :size 6}
      ;; {:name "left-kidney", :size 1}
      ;; {:name "right-kidney", :size 1}
      ;; {:name "left-hand", :size 2}
      ;; {:name "right-hand", :size 2}
      ;; {:name "left-knee", :size 2}
      ;; {:name "right-knee", :size 2}
      ;; {:name "left-thigh", :size 4}
      ;; {:name "right-thigh", :size 4}
      ;; {:name "left-lower-leg", :size 3}
      ;; {:name "right-lower-leg", :size 3}
      ;; {:name "left-achilles", :size 1}
      ;; {:name "right-achilles", :size 1}
      ;; {:name "left-foot", :size 2}
      ;; {:name "right-foot", :size 2}]

(let [x 3]
  (println x)
  (+ x 1))
;; 4

(def dogs ["Ace" "Bob" "Doug"])

(let [[first & rest] dogs]
  {:firstDog first
   :restDog rest})
;; => {:firstDog "Ace", :restDog ("Bob" "Doug")}

(into [:a :b] #{:c :d})
;; => [:a :b :c :d]

(into [:a :b] #{:e :a :b})
;; => [:a :b :e :b :a]

(loop [i 0]
  (println i)
  (if (> i 3)
    (println "bye")
    (recur (inc i))))
;; 0
;; 1
;; 2
;; 3
;; 4
;; bye
;; => nil

(defn recursive-printer
  ([] (recursive-printer 0))
  ([iteration]
   (println iteration)
   (if (> iteration 2)
     (println "GoodBye!")
     (recursive-printer (inc iteration)))))
;; => #'noob.core/recursive-printer

(recursive-printer)
;; 0
;; 1
;; 2
;; 3
;; GoodBye!
;; => nil

;; #"regular-expression"

(re-find #"^coca" "coca-cola")
;; => coca

(re-find #"^coca" "acoca-cola")
;; => nil

(clojure.string/replace "coca-cola" #"^coca" "free")
;; => free-cola

(def strings ["_coca-lola" "-free" "-pepsi-cola"])

(defn fixed-string [str_]
  (clojure.string/replace str_ #"^-" "_"))

(map fixed-string strings)
;; => ("_coca-lola" "_free" "_pepsi-cola")

(defn fixed-string [str_]
  {:name (clojure.string/replace str_ #"^-" "_")})

(map fixed-string strings)
;; => ({:name "_coca-lola"} {:name "_free"} {:name "_pepsi-cola"})

(reduce - [5 4 2 1]) ;iterate
;; => -2

(- 5 4 2 1)
;; => -2

(reduce + 15 [1 1 1])
;; => 18

(reduce - 1 [1 1 1]) ; = (- (- 1 1) 1)
;; => -1

(reduce - 1 [1 1 1]) ; = (- (- (- 1 1) 1) 1)
;; => -2

(empty? [1 2])
;; => false

(defn mapset [f l]
  (let [l_ (set l)]
    (map f l_)))

(mapset inc [2 3 2])
;; => (4 3)

(mapset #(+ % 10) [2 3 2])
;; => (13 12)

(mapset #(* % 10) [2 3 2])
;; => (30 20)

;; multiplica uma lista por 10;

(map #(* % 10) [1,2,3])
;; => (10 20 30)

(defn simitrize [lista-completa]
  (loop [restante lista-completa resultado-final []]
    (if (empty? restante)
      resultado-final
      (let [[cabeca-restante & cauda-restante] restante]
        (recur cauda-restante
               (into resultado-final (set [cabeca-restante (...)])))))))

;; loop-recur caminham juntos.

(defn counter []
  (loop [i 0 x 0]
    (println i)
    (println x)
    (if (> i 2)
      (println "saiu")
      (recur (inc i) (- x 1)))))

(counter)
;; 0
;; 0
;; 1
;; -1
;; 2
;; -2
;; 3
;; -3
;; saiu
;; => nil

(defn counter []
  (loop [i 0 x 0]
    (println i)
    (println x)
    (if (> i 2)
      (println "saiu")
      (recur (inc i) (- x 1)))))
; => #'noob.core/counter

;; loop tem 'dois estados' , o recur precisa tbm, obviamente. 

;; => input (1,2,3,4,5)
;; => output ( { :n 2 :n2 4 :n3 8 })

; (defn mult [listagem]
;   (loop [n listagem table []]

;     (println listagem)

;     (if (empty? n)
;       table
;       (let [[first & rest] listagem]
;        (recur rest (into table (set [first (identity {:n first , :n2 (* first first)})]))))
;     ))
;   )

; (mult [2 4 5])

(+ 2 1)

(defn words-policy [word]
  (clojure.string/lower-case word))

(words-policy "I LoVe YoU!")
; => "i love you!"

(defn words-policy [word]
  (re-matches #"hate you" (clojure.string/lower-case word)))
; => #'noob.core/words-policy
; 
(words-policy "I love yOU")
; => nil

(words-policy "HatE You")
; => "hate you"

(defn words-policy [word]
  (re-matches #"hate you(.*)" (clojure.string/lower-case word)))

(words-policy "HatE You Soo Much")
; => ["hate you soo much" " soo much"]

(defn words-policy [word]
  (re-matches #"(.*)hate you(.*)" (clojure.string/lower-case word)))

(words-policy "I rEallY HatE You Soo Much")
; => ["i really hate you soo much" "i really " " soo much"]

(re-seq #"ss" "miseissseippssi")
; => ("ss" "ss")

(re-seq #"s+" "miseissseippssi")
; => ("s" "sss" "ss")

(def bad-terms ["hate you" "vasco da gama" "kill you"])
; => #'noob.core/bad-terms

(def sent ["lOVE u" "Cool" "dOnt Like yOu" "hatE you"])
; => #'noob.core/sent

(defn lower [x] (clojure.string/lower-case x))

(map lower sent)
; => ("i love you" "you are my heart" "dont like you" "kill you")

(map words-policy bad-terms)
; => (["hate you" "" ""] nil nil)

(map words-policy sent)
; => (nil nil nil ["hate you" "" ""])

(#(* % 450) 2)
; 900

((fn [x] (* x 450)) 2)
; 900

(defn exp [x n]
  (loop [result 1 n n]
    (if (zero? n)
      result
      (recur (* x result) (dec n)))))

(exp 3 4)

(defn number?? [n] (instance? Number n))

(number?? 3N)
; => true

(number?? "3")
; => false

(defn abs
  "(abs n) is the absolute value of n"
  [n]
  (if (number?? n)
    (do (if (< n 0)
          (* n -1)
          n))
    "Not a number"))

(abs 3)
; => 3
; 
(abs -3)
; => 3

(abs -9)
; => 9

(abs "1")
; => "Not a number"

(next (next (next '(5 21241 22 1 -1122))))
; => (1 -1122)


(do (if false (do (println "Calculando rand") (rand))))
;; => nil

;; better constructor to handle one codition
(when true (println "Calculando rand")
      (rand))

;; use def to global bindings
;; use let to local bindings

(def x 10)

x

(let [x 20] x)
;; => 20

((fn [x] (* x x x)) 5)
;; => 125

(#(* % % %) 5)
;; => 125

(defn square [x] (* x x)) ; == (def square (fn [x] (* x x)))


(square 5)
;; => 25


(defn meditate
  "Meditate function"
  ([s]
   (println (str "i dont know.... " s)))
  ([s calm]
   (println "Ok... you give the calm position")
   (if calm
     (println (clojure.string/capitalize s))
     (println (str (clojure.string/upper-case s) "!!!")))))

(meditate "eu to calmo" false)

(meditate "hey!")

(doc meditate)

(defn co2-estimate [year]
  (+ 382 (* 2 (- year 2006))))

;; ganho de legibilidade para conceitos que tem algum sentido como -> year-diff
(defn co2-estimate [year]
  (let [year-diff (- year 2006)]
    (+ 382 (* 2 year-diff))))

(co2-estimate 2050)
;; 470

(true? 4)
;; =>

(false? nil)
;; => false

(nil? false)
;; => false

(nil? (println "Hi!"))
;; Hi! ;println returns nil
;; => true

;; and return the first falsey
(and (println "I return nil, so i'm falsey value") 5)
;; I return nil, so i'm falsey value
;; => nil

;; returns first falsey value
(and 5 3 4 nil 4)
;; => nil

(and 5 3 451)
;; => 451

;; returns first truthy value
(or 5 3 4 nil 4)
;; => 5

(or false nil)
;; => nil

(or nil false)
;; => false

(= "a" "a")
;; => true

(<= 4 2)
;; => false

;; not returns true if the return is falsey value
(not (< 5 2))
;; => true

(defn biggerThanFive [x]
  (not (<= x 5)))

(biggerThanFive 12)

(mod 0 10)

((fn [x]
   (if (or (and (>= x 1) (<= x 100)) (= 0 (mod x 100)))
     (println "valid")
     (println "not-valid"))) 151)

((fn [x]
   (if (or (<= 1 x 100) (= 0 (mod x 100)))
     (println "valid")
     (println "not-valid"))) 151)

;; the floating point are 'contagious' 

(/ 3 4)
;; 3/4

(/ 3 4.0)
;; 0.75

(int \a)

(defn encode-letter
  [s x]
  (let [code (Math/pow (+ x (int (first (char-array s)))) 2)]
    (str "#" (int code))))

(defn encode
  [s]
  (let [number-of-words (count (clojure.string/split s #"s"))]
    (clojure.string/replace s #"\w" (fn [s] (encode-letter s number-of-words)))))

(encode "How aree youu?")

{:sport "soccer", :team "flamengo"}

(def favorite-fruit {:name "Kiwi", :color "Green", :kcal_per_100g 61 :distinguish_mark "Hairy"})

(get favorite-fruit :name)

(:name favorite-fruit)
