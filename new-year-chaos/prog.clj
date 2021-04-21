;; Complete the minimumBribes function below.
(defn swap
  ([q pos] (assoc q (- pos 1) (q pos) pos (q (- pos 1))))
  ([q pos steps]
   (loop [res q
          cur-pos pos
          taken 0]
     (if (= taken steps)
       res
       (recur (assoc res (- cur-pos 1) (res cur-pos) cur-pos (res (- cur-pos 1)))
              (- cur-pos 1)
              (+ taken 1))))))

;; (defn swap-two [q pos]
;;   (let [res (swap q pos)]
;;     (swap res (- pos 1))))

(def v [1 2 3 4])
(assoc v 1 (v 0) 0 (v 1) )
(swap v 2 2)
([1 2 3 4] 0)

(defn get-diff [q pos expected]
  (let [value (q pos)
        expected-position (.indexOf expected value)
        ]
    (- expected-position pos)))

(def test1 [1 2 5 3 7 8 6 4])
(def test1-expected [1 2 5 3 7 8 4 6])
(.indexOf test1-expected 6)
(get-diff [1 2 5 3 7 8 6 4] 6 test1-expected)
(get-diff [2 1 5 3 4] 1 [1 2 3 4 5])
(get-diff [1 2 3 4 5] 0 [1 2 3 4 5])


(.indexOf v 2)

(defn minimumBribes [q]
  (loop [i 0
         result 0
         expected (loop [r []
                         j 0]
                    (if (< j (count q))
                      (recur (conj r (+ j 1)) (+ j 1))
                      r))]
    (print "expected[" expected "] \n")
    (if (< i (count q))
      (let [current (q i)
            dif (get-diff q current expected)]
        (if (> dif 2)
          (print "Too chaotic\n")
          (if (<= dif 0)
            (recur (+ i 1) result expected)
            (recur (+ i 1) (+ result dif) (swap expected (.indexOf expected (q current)) dif))))
        )
      (print result "\n"))
    )

  )

(minimumBribes [2 1 5 3 4])

(def t (Integer/parseInt (clojure.string/trim (read-line))))

(doseq [t-itr (range t)]
  (def n (Integer/parseInt (clojure.string/trim (read-line))))

  (def q (vec (map #(Integer/parseInt %) (clojure.string/split (read-line) #" "))))

  (minimumBribes q)
  )
