(ns noob.math)

(defn number??
  "Returns true if x is a Number" [x]
  (instance? Number x))

(defn opst
  "Returns the value multiply by -1" [n]
  (cond
    (not (number?? n)) (throw (IllegalArgumentException. "opst requires a number"))
    :else (* n -1)))


(defn abs
  "(abs n) is the absolute value of n" [n]
  (cond
    (not (number?? n)) (throw (IllegalArgumentException. "abs requires a number"))
    (neg? n) (* n -1)
    :else n))





(defn fat
  "Returns the factorial of n" [n]
  (cond
    (not (number?? n)) (throw (IllegalArgumentException. "fat requires a number"))
    (neg? n) (throw (IllegalArgumentException. "fat requires a positive number"))
    :else
    (loop [acc 1 n n]
      (if (< n 2)
        acc
        (recur (* acc n) (dec n))))))






