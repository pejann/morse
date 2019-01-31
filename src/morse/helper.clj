(ns morse.helper)

(defn get-pairs-by-letter-and-fn
    [alphabet letter func]
    (let [pair (first (filter (fn [item] (= (func item) letter)) alphabet))]
        (if (nil? pair)
            [letter letter]
            pair)))

(defn print-and-return
    [something]
    (println something)
    something)            