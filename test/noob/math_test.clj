(ns noob.math-test
  (:require [clojure.test :refer :all :as test]
            [noob.math :refer :all :as math]))

(test/deftest is-number-test
  (test/testing "Verificando se numeros retornam verdadeiro."
    (test/is (= true (math/number?? 5)))
    (test/is (= true (math/number?? -9)))
    (test/is (= true (math/number?? 4000)))
    (test/is (= true (math/number?? 0)))
    (test/is (= true (math/number?? (* -9999 9999))))))

(test/deftest absolute-value-test
  (test/testing "Verificando se numeros retornam seu valor absoluto."
    (test/is (= 19 (math/abs -19)))
    (test/is (= 19 (math/abs +19)))
    (test/is (= 0 (math/abs 0 )))))

  (test/deftest fatorail-test
    (test/testing "Verify factorial"
      (test/is (= 120 (math/fat 5)))
      (test/is (= 720 (math/fat 6)))
      (test/is (= 1 (math/fat 0)))
      (test/is (= 1 (math/fat 1)))
      (test/is (= 6 (math/fat 3)))))