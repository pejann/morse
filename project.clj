(defproject morse "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/spec.alpha "0.2.176"]]
  :main ^:skip-aot morse.core
  :target-path "target/%s"
  :profiles {:dev {:dependencies [[midje/midje "1.9.6"]
                                  [criterium "0.4.4"]]
                   :plugins [[lein-midje "3.2.1"]]}
             :uberjar {:aot :all}})

  