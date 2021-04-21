(defn swap-arr [r i j]
  (assoc r i (r j) j (r i)))

(swap-arr [1 2] 0 1)


; Complete the minimumSwaps function below.
(defn minimumSwaps [arr]
  (let [len (count arr)]
    (loop [i 0
           arr-ok (apply vector (map #(- % 1) arr))
           res 0]
      (if (< i len)
        (if (= i (arr-ok i))
          (recur (+ i 1) arr-ok res)
          (recur i (swap-arr arr-ok i (arr-ok i)) (+ res 1)))
        res)))
)


(def fptr (get (System/getenv) "OUTPUT_PATH"))

(def n (Integer/parseInt (clojure.string/trim (read-line))))

(def arr (vec (map #(Integer/parseInt %) (clojure.string/split (read-line) #" "))))

(def res (minimumSwaps arr))

(spit fptr (str res "\n") :append true)
