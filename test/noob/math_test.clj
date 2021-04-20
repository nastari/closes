(ns noob.math-test
  (:require [clojure.test :refer :all :as test]
            [noob.math :refer :all :as math]))

(test/deftest is-number-test
  (test/testing "Verify the numbers returns true."
    (test/is (= true (math/number?? 5)))
    (test/is (= true (math/number?? -9)))
    (test/is (= true (math/number?? 4000)))
    (test/is (= true (math/number?? 0)))
    (test/is (= false (math/number?? "4")))
    (test/is (= false (math/number?? true)))
    (test/is (= false (math/number?? :3)))
    (test/is (= true (math/number?? (* -9999 9999))))))

(test/deftest absolute-value-test
  (test/testing "Verify absolute values."
    (test/is (= 19 (math/abs -19)))
    (test/is (= 19 (math/abs +19)))
    (test/is (= 0 (math/abs 0 )))))

  (test/deftest factorial-test
    (test/testing "Verify factorial values"
      (test/is (= 120 (math/fat 5)))
      (test/is (= 720 (math/fat 6)))
      (test/is (= 1 (math/fat 0)))
      (test/is (= 1 (math/fat 1)))
      (test/is (= 6 (math/fat 3)))))