(ns morse.to-morse
  (:require [clojure.string :as str]
            [morse.alphabet :as alphabet]
            [morse.helper :as helper]
            [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as stest]))

(defn get-pairs-by-letter
  "Recover a pair [letter morse-code] by letter, in case the letter is not recognized a vector 
  with [letter letter] is returned

  Examples
  >>> (get-pairs-by-letter \"A\")
  [A .-]
  >>> (get-pairs-by-letter \"1\")
  [1 .----]
  >>> (get-pairs-by-letter \"|\")
  [| |]
  >>> (get-pairs-by-letter \"É\")
  [É É]"
  [letter]
  (helper/get-pairs-by-letter-and-fn alphabet/morse-dictionary letter first))

(def get-pairs-by-letter-memo (memoize get-pairs-by-letter))  

(defn letters-to-morse
  "Converts a list of letters into morse code, in case the letter is not recognized the letter itself is returned

  Examples
  >>> (letters-to-morse (list \"F\" \"O\" \"O\"))
  ..-. --- ---
  >>> (letters-to-morse (list \"F\" \"O\" \"O\" \"|\"))
  ..-. --- --- |"
  [letters]
  (->> letters
    (map (fn [item] (last (get-pairs-by-letter-memo item))) ,,,)
    (str/join " " ,,,)))

(defn words-to-morse
  "Converts a list of words into morse code, in the case of one letter is not recognized, the letter itself is returned
  
  Examples
  >>> (words-to-morse (list \"FOO\" \"BAR\" \"BAZ\"))
  ..-. --- ---  -... .- .-.  -... .- --..
  >>> (words-to-morse (list \"FOO\" \"BAR\" \"BAZ\" \"|\"))
  ..-. --- ---  -... .- .-.  -... .- --..  |"
  [words]
  (->> words
    (map (fn [item] (letters-to-morse (str/split item #""))) ,,,)
    (str/join "  " ,,,)))

(defn from-text
  "Transforms a sentence into morse code. This function automatically converts the string to uppercase string. 
  In the case of one letter is not recognized, the letter itself is returned
  
  Examples
  >>> (from-text \"Foo Bar Baz Lorem Ipsum Dolor\")
  ..-. --- ---  -... .- .-.  -... .- --..  .-.. --- .-. . --  .. .--. ... ..- --  -.. --- .-.. --- .-."
  [text]
  (let [words (str/split (str/upper-case text) #" ")]
    (words-to-morse words)))

(s/fdef get-pairs-by-letter
  :args (s/cat :text string?)
  :ret (s/coll-of string? :kind vector? :count 2))

(s/fdef letters-to-morse
  :args (s/cat :letters (s/coll-of string?))
  :ret string?)

(s/fdef words-to-morse
  :args (s/cat :letters (s/coll-of string?))
  :ret string?)

(s/fdef from-text
  :args (s/cat :text string?)
  :ret string?)

(stest/instrument `from-text)
(stest/instrument `get-pairs-by-letter)
(stest/instrument `letters-to-morse)
(stest/instrument `words-to-morse)




