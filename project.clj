(defproject beginner-cider-nrepl "0.1.0-SNAPSHOT"
  :description "quick intro on getting clojure talking to emacs through nrepl"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring "1.3.1"]
                 [org.clojure/tools.nrepl "0.2.12"]]

  :plugins [[lein-ring "0.9.6"]
            [cider/cider-nrepl "0.13.0-SNAPSHOT"]]
  :ring {:handler beginner-cider-nrepl.core/app
         :nrepl{:start? true}})
