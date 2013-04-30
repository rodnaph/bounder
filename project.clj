
(defproject bounder "0.1.0"
  :description "Clojure and ClojureScript Docs"
  :dependencies [; clojure
                 [org.clojure/clojure "1.5.1"]
                 [clj-jgit "0.3.5"]
                 [codox "0.6.4"]
                 [fs "1.3.3"]
                 [enlive "1.1.1"]
                 ; clojurescript
                 [domina "1.0.1"]
                 [leaf "0.0.2"]
                 [lowline "0.0.1"]
                 [enfocus "1.0.1"]]
  :source-paths ["src/clojure" "src/html"]
  :plugins [[lein-cljsbuild "0.3.0"]]
  :main bounder.core
  :cljsbuild {
    :builds [{
      :source-paths ["src/cljs"]
      :compiler {:output-to "assets/js/main.js"
                 :optimizations :whitespace
                 :pretty-print true}
    }]
  }
  :hooks [leiningen.cljsbuild])

