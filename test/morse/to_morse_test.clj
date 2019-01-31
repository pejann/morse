(ns morse.to-morse-test
    (:use midje.sweet)
    (:require [morse.to-morse :as to-morse]))

(facts "get-pairs-by-letter"
  (fact "should return the correct morse code for the letter A"
    (to-morse/get-pairs-by-letter "A") => ["A" ".-"])

  (fact "should return the correct morse code for the letter B"
    (to-morse/get-pairs-by-letter "B") => ["B" "-..."])    

  (fact "should return the correct morse code for the letter C"
    (to-morse/get-pairs-by-letter "C") => ["C" "-.-."])

  (fact "should return the correct morse code for the number 1 as string"
    (to-morse/get-pairs-by-letter "1") => ["1" ".----"])    

  (fact "should return the correct morse code for the number 2 as string"
    (to-morse/get-pairs-by-letter "2") => ["2" "..---"])    
  
  (fact "should return a pair of the input letter when the symbol is not known"
    (to-morse/get-pairs-by-letter "|") => ["|" "|"]))

(facts "letters-to-morse"
  (fact "should convert a list of letters into morse code"
    (to-morse/letters-to-morse (list "F" "O" "O")) => "..-. --- ---")

  (fact "should convert a list of letters into morse code, but return the same letter when it is not recognized"
    (to-morse/letters-to-morse (list "F" "O" "O" "|")) => "..-. --- --- |"))

(facts "words-to-morse"
  (fact "should convert a list of words into morse code"
    (to-morse/words-to-morse (list "FOO" "BAR" "BAZ")) => "..-. --- ---  -... .- .-.  -... .- --..")

  (fact "should convert a list of words into morse code, but return the same letter when it is not recognized"
    (to-morse/words-to-morse (list "FOO" "BAR" "BAZ" "|")) => "..-. --- ---  -... .- .-.  -... .- --..  |"))

(facts "from-text"
  (fact "should convert simple phrases"
    (to-morse/from-text "Foo Bar Baz Lorem Ipsum Dolor") => 
      "..-. --- ---  -... .- .-.  -... .- --..  .-.. --- .-. . --  .. .--. ... ..- --  -.. --- .-.. --- .-.")

  (fact "should convert simple phrases and numbers"
    (to-morse/from-text "FRASE 1") => "..-. .-. .- ... .  .----")

  (fact "should convert lowercase text to uppercase text"
    (to-morse/from-text "Frase 1") => "..-. .-. .- ... .  .----")

  (fact "should not convert unknown symbols"
    (to-morse/from-text "Frase É | Mais caracteres") => "..-. .-. .- ... .  É  |  -- .- .. ...  -.-. .- .-. .- -.-. - . .-. . ..."))    