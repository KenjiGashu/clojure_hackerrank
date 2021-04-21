(defn ensure-not-nil [n]
  (if n
    n
    0))
;; Complete the repeatedString function below.
(defn repeatedString [s n]
  (let [len (count s)
        rest (mod n len)
        occurences (ensure-not-nil ((frequencies s) \a))]
    ;;(print "rest[" rest "]")
    (+ (* (quot n len) occurences)
       (ensure-not-nil ((frequencies (subs s 0 rest)) \a)))
    ;; (loop [i 0
    ;;        it 0
    ;;        res 0]
    ;;   (if (< i n)
    ;;     (if (= \a (.charAt s it))
    ;;       (recur (+ i 1) (mod (+ it 1) len) (+ res 1))
    ;;       (recur (+ i 1) (mod (+ it 1) len) res))
    ;;     res))
    )

  )

(subs "abcde" 0 0)
((frequencies "") \a)

(repeatedString "abcde" 15)

(def fptr (get (System/getenv) "OUTPUT_PATH"))

(def s (read-line))

(def n (Long/parseLong (clojure.string/trim (read-line))))

(def result (repeatedString s n))

(spit fptr (str result "\n") :append true)
