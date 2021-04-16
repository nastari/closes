(ns noob.math)

(defn number??
  "Returns true if x is a Number" [x]
  (instance? Number x))

(defn opst
  "Returns the value multiply by -1" [n]
  (cond
    (not (number? n)) (throw (IllegalArgumentException. "opst requires a number"))
    :else (* n -1)))


(defn abs 
  "(abs n) is the absolute value of n" [n]
  (cond
    (not (number? n)) (throw (IllegalArgumentException. "abs requires a number"))
    (neg? n) (* n -1)
    :else n))
