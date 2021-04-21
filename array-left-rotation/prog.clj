
; Complete the rotLeft function below.
(defn rotLeft [a d]
  (let [rotations (mod d (count a))
        leng (count a)]
    (if (= d 0)
      a
      (loop [result []
           i rotations]
      (if (= (- d 1) i)
        (conj result (get a i))
        (recur (conj result (get a i)) (mod (+ i 1) leng)))
      ))
    )
)

(rotLeft [1 2 3 4 5] 1)
(rotLeft [1 2 3 4 5] 0)
(rotLeft [1 2 3 4 5] 2)
(rotLeft [1 2 3 4 5] 3)
(rotLeft [1 2 3 4 5] 4)

(def fptr (get (System/getenv) "OUTPUT_PATH"))

(def nd (clojure.string/split (read-line) #" "))

(def n (Integer/parseInt (clojure.string/trim (nth nd 0))))

(def d (Integer/parseInt (clojure.string/trim (nth nd 1))))

(def a (vec (map #(Integer/parseInt %) (clojure.string/split (read-line) #" "))))

(def result (rotLeft a d))

(spit fptr (clojure.string/join " " result) :append true)
(spit fptr "\n" :append true)
