(ns morse.from-morse
  (:require [clojure.string :as str]
            [morse.alphabet :as alphabet]
            [morse.helper :as helper]
            [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as stest]))

(defn get-pairs-by-code
  "Recover a pair [letter morse-code] by letter, in case the letter is not recognized a vector 
  with [letter letter] is returned

  Examples
  >>> (get-pairs-by-code \".-\")
  [A .-]
  >>> (get-pairs-by-code \".----\")
  [1 .----]
  >>> (get-pairs-by-code \"x\")
  [x x]"
  [letter]
  (helper/get-pairs-by-letter-and-fn alphabet/morse-dictionary letter last))  
  
(defn morse-to-letter
  "Converts a list of morse codes into letters, in case the morse code is not recognized the code itself is returned

  Examples
  >>> (morse-to-letter (list \"..-.\" \"---\" \"---\"))
  FOO
  >>> (morse-to-letter (list \"..-.\" \"---\" \"---\" \"x\"))
  FOOx"
  [letters]
  (->> letters
      (map (fn [item] (first (get-pairs-by-code item))) ,,,)
      (str/join "" ,,,)))

(defn morse-to-words
  "Converts a list of morse code words into words, in the case of the morse code is not recognized, the code itself is returned
  
  Examples
  >>> (morse-to-words (list \"..-. --- ---\" \"-... .- .-.\" \"-... .- --..\"))
  FOO BAR BAZ
  >>> (morse-to-words (list \"..-. --- ---\" \"-... .- .-.\" \"-... .- --..\" \"x\"))
  FOO BAR BAZ x"
  [words]
  (->> words
    (map (fn [item] (morse-to-letter (str/split item #" "))) ,,,)
    (str/join " " ,,,)))

(defn to-text
  "Transforms a morse code sentence into sentence. In the case of one letter is not recognized, the letter itself is returned
  
  Examples
  >>> (to-text \"..-. --- ---  -... .- .-.  -... .- --..  .-.. --- .-. . --  .. .--. ... ..- --  -.. --- .-.. --- .-.\")
  Foo Bar Baz Lorem Ipsum Dolor"
  [text]
  (let [words (str/split text #"  ")]    
    (morse-to-words words)))

(s/fdef get-pairs-by-code
  :args (s/cat :text string?)
  :ret (s/coll-of string? :kind vector? :count 2))

(s/fdef morse-to-letter
  :args (s/cat :letters (s/coll-of string?))
  :ret string?)

(s/fdef morse-to-words
  :args (s/cat :letters (s/coll-of string?))
  :ret string?)

(s/fdef to-text
  :args (s/cat :text string?)
  :ret string?)

(stest/instrument `to-text)
(stest/instrument `get-pairs-by-code)
(stest/instrument `morse-to-letter)
(stest/instrument `morse-to-words)

(comment 
    (to-text 5))        