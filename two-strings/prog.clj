;
; complete the 'twostrings' function below.
;
; the function is expected to return a string.
; the function accepts following parameters:
;  1. string s1
;  2. string s2
;
;

(use '[clojure.string :only [index-of]])

(defn string->character-map [s]
  (loop [char-map {}
         s s]
    (if (empty? s)
      char-map
      (recur (assoc char-map (keyword (subs s 0 1)) 1)
             (subs s 1)))))

(defn twoStrings [s1 s2]
  (let [all-char "abcdefghijklmnopqrstuvwxyz"]
    (loop [s-allchar all-char]
      (if (empty? s-allchar)
        "NO"
        (do
          (if (and (index-of s1 (subs s-allchar 0 1)) (index-of s2 (subs s-allchar 0 1)))
            "YES"
            (recur (subs s-allchar 1)))))
      )
    )
)

;;timeout
;; (defn twoStrings [s1 s2]
;;   (let [s1-map (frequencies s1)]
;;     (loop [s2 s2]
;;       (if (empty? s2)
;;         "no"
;;         (if (s1-map (.charat s2 0))
;;           "yes"
;;           (recur (subs s2 1))))))
;; )


(def fptr (get (System/getenv) "OUTPUT_PATH"))

(def q (Integer/parseInt (clojure.string/trim (read-line))))

(doseq [q-itr (range q)]
    (def s1 (read-line))

    (def s2 (read-line))

    (def result (twoStrings s1 s2))

    (spit fptr (str result "\n") :append true)
)
