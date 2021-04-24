; Complete the arrayManipulation function below.
;; O(n*n)
(defn minus [v]
  (assoc v 0 (- (v 0) 1) 1 (- (v 1) 1)))

(defn split-range-1 [cur r]
  (let [[cur-a cur-b cur-k] cur
        [a b k] r]
    (if (>= (- cur-b (+ b 1) ) 0)
      ;; three splits [a cur-a] [cur-a b] [b cur-b]
      (conj []
          [a (- cur-a 1) k]
          [cur-a b (+ cur-k k)]
          [(+ b 1) cur-b cur-k])
      ;; two splits [a cur-a] [cur-a b] (when b==cur-b) there should be no third split
      (conj []
          [a (- cur-a 1) k]
          [cur-a b (+ cur-k k)]))
    ))

;;testes
(split-range-1 [5 10 100] [0 5 1]) ;; (when b==cur-a)
(split-range-1 [5 10 100] [0 6 1]) ;; (when b>cur-a && b<cur-b)
(split-range-1 [5 10 100] [0 10 1]) ;; (when b==cur-b)

;; O(n*n)
(defn split-range-2 [cur r]
  (let [[cur-a cur-b cur-k] cur
        [a b k] r]
    (if (>= (- (- a 1) cur-a) 0)
      ;;three splits
      (conj []
            [cur-a (- a 1) cur-k]
            [a cur-b (+ cur-k k)]
            [(+ cur-b 1) b k])
      ;; two splits
      (conj []
          [a cur-b (+ cur-k k)]
          [(+ cur-b 1) b k]))
    ))

(split-range-2 [ 5 10 100 ] [ 5  15 1 ]) ;; when a=cur-a
(split-range-2 [ 5 10 100 ] [ 8  15 1 ]) ;; when a>cur-a && a<cur-b
(split-range-2 [ 5 10 100 ] [ 10 15 1 ]) ;; when a==cur-b

;; O(n*n)
(defn insert-inside [cur r]
  ())

;;this is O(n*n), sadge
(defn insert-query [cur r]
  (let [[cur-a cur-b cur-k] cur
        [a b k] r]
    (print cur-a cur-b a b)
    (cond
      (and (< a cur-a) (< b cur-a)) [r cur]
      (and (> a cur-a) (< b cur-b))
      (and (< a cur-a) (and (>= b cur-a) (<= b cur-b))) (split-range-1 cur r)
      (and (and (>= a cur-a) (<= a cur-b)) (> b cur-b)) (split-range-2 cur r)

      )))

;; all tests
(is-inside [ 5 10 100 ] [ 0  3  1 ]) ;; when ab < cur-a cur-b
(is-inside [ 5 10 100 ] [ 0  5  1 ]) ;; (when b==cur-a)
(is-inside [ 5 10 100 ] [ 0  6  1 ]) ;; (when b>cur-a && b<cur-b)
(is-inside [ 5 10 100 ] [ 0  10 1 ]) ;; (when b==cur-b)
(is-inside [ 5 10 100 ] [ 5  15 1 ]) ;; when a=cur-a
(is-inside [ 5 10 100 ] [ 8  15 1 ]) ;; when a>cur-a && a<cur-b
(is-inside [ 5 10 100 ] [ 10 15 1 ]) ;; when a==cur-b

;; O(n*n)
;; (defn arrayManipulation [n queries]
;;   (loop [res []
;;          queries queries]
;;     (if (empty? queries)
;;       res
;;       (let [q (first queries)]
;;         (if (empty? res)
;;         (conj res q)
;;         (recur (is-inside res q) (rest queries))))))
;; )

;;faster solution that i got from hackerrank comments
(defn minus-one-vec [my-vec]
  (vec [(- (my-vec 0) 1) (- (my-vec 1) 1) (my-vec 2)]))

(defn get-resp [arr]
  (loop [arr arr
         m 0
         c 0]
    (if (empty? arr)
      m
      (recur (rest arr)
             (max m (+ c (first arr)))
             (+ c (first arr))))))

;;the right way
(defn arrayManipulation [n queries]
  (let [resposta (vec (repeat 10000002 0))]
    (loop [queries queries
           accumulated resposta]
      (if (empty? queries)
        (get-resp accumulated)
        (let [[a b k] (first queries)]
          (recur (rest queries)
                 (assoc accumulated
                        a (+ (accumulated a) k)
                        (+ b 1) (- (accumulated (+ b 1)) k)))))
      )))

(arrayManipulation 0 [[0 100 1] [2 10 5]])

(map )

(def fptr (get (System/getenv) "OUTPUT_PATH"))

(def nm (clojure.string/split (read-line) #" "))

(def n (Integer/parseInt (clojure.string/trim (nth nm 0))))

(def m (Integer/parseInt (clojure.string/trim (nth nm 1))))

(def queries [])

(doseq [_ (range m)]
    (def queries (conj queries (vec (map #(Integer/parseInt %) (clojure.string/split (read-line) #" ")))))
)

(def result (arrayManipulation n queries))

(spit fptr (str result "\n") :append true)
