

;; Complete the checkMagazine function below.
(defn generate-words-map [magazine]
  (loop [magazine magazine
         magazine-words {}]
    (if (empty? magazine)
      magazine-words
      (let [w (first magazine)
            kw (keyword w)]
        (if (kw magazine-words)
          (recur (rest magazine)
                 (assoc magazine-words kw (+ (kw magazine-words) 1)))
          (recur (rest magazine)
                 (assoc magazine-words kw 1)))))))

;;test generate-words-map
(generate-words-map ["oi" "ai" "Ei" "Oi" "oi" "Ei"])

(defn decrem-or-remove [kw hashm]
  (let [val (kw hashm)]
    (if (> val 1)
      (assoc hashm kw (- (kw hashm) 1))
      (dissoc hashm kw))))

(decrem-or-remove :oi {:oi 2})
(decrem-or-remove :oi {:oi 1})
(dissoc {:oi 1} :oi)

(defn checkMagazine [magazine note]
  (let [m-words (generate-words-map magazine)]
    (loop [note note
           m-words m-words]
      (if (empty? note)
        (print "Yes\n")
        (if ((keyword (first note)) m-words)
          (recur (rest note)
                 (decrem-or-remove (keyword (first note)) m-words))
          (print "No\n")))))

  )

(assoc {} :Yes 1 :yes 2)

(def mn (clojure.string/split (read-line) #" "))

(def m (Integer/parseInt (clojure.string/trim (nth mn 0))))

(def n (Integer/parseInt (clojure.string/trim (nth mn 1))))

(def magazine (clojure.string/split (read-line) #" "))

(def note (clojure.string/split (read-line) #" "))

(checkMagazine magazine note)
