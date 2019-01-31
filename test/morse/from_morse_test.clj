(ns morse.from-morse-test
    (:use midje.sweet)
    (:require [morse.from-morse :as from-morse]))

(facts "get-pairs-by-code"
  (fact "should return the correct letter for the morse code .-"
    (from-morse/get-pairs-by-code ".-") => ["A" ".-"])

  (fact "should return the correct letter for the morse code -..."
    (from-morse/get-pairs-by-code "-...") => ["B" "-..."])    

  (fact "should return the correct letter for the morse code -.-."
    (from-morse/get-pairs-by-code "-.-.") => ["C" "-.-."])

  (fact "should return the correct letter for the morse code .----"
    (from-morse/get-pairs-by-code ".----") => ["1" ".----"])    

  (fact "should return the correct letter for the morse code ..---"
    (from-morse/get-pairs-by-code "..---") => ["2" "..---"])    
  
  (fact "should return pair of the input letter when the symbol is not known"
    (from-morse/get-pairs-by-code "x") => ["x" "x"]))

(facts "morse-to-letter"
  (fact "should convert a list of letters into morse code"
    (from-morse/morse-to-letter (list "..-." "---" "---")) => "FOO")

  (fact "should convert a list of letters into morse code, but return the same letter when it is not recognized"
    (from-morse/morse-to-letter (list "..-." "---" "---" "x")) => "FOOx"))

(facts "morse-to-words"
  (fact "should convert a list of morse codes into words"
    (from-morse/morse-to-words (list "..-. --- ---" "-... .- .-." "-... .- --..")) => "FOO BAR BAZ")

  (fact "should convert a list of morse codes into words, but return the same code when it is not recognized"
    (from-morse/morse-to-words (list "..-. --- ---" "-... .- .-." "-... .- --.." "x")) => "FOO BAR BAZ x"))    

(facts "to-text"
  (fact "should convert simple phrases"
    (from-morse/to-text "..-. --- ---  -... .- .-.  -... .- --..  .-.. --- .-. . --  .. .--. ... ..- --  -.. --- .-.. --- .-.") => 
      "FOO BAR BAZ LOREM IPSUM DOLOR")

  (fact "should convert simple phrases and numbers"
    (from-morse/to-text "..-. .-. .- ... .  .----") => "FRASE 1"))