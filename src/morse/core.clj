(ns morse.core
  (:require [clojure.string :as str]
            [morse.to-morse :as to-morse]
            [morse.from-morse :as from-morse])
  (:gen-class))

(defn -main
  "Execute the app with 2 arguments. First is the function to execute and the 
  second is the text (in form of phrase or morse code)
  
  Examples
  >>> lein run to-morse \"Foo Bar Baz\"
  ..-. --- ---  -... .- .-.  -... .- --..
  >>> lein run to-phrase \"..-. --- ---  -... .- .-.  -... .- --..\"
  Foo Bar Baz"
  [& [func text]]
  (cond
    (= "to-morse" func) (println (to-morse/from-text text))
    (= "to-phrase" func) (println (from-morse/to-text text))
    :else (throw (ex-info "Função não identificada" {}))))