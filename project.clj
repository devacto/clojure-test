(defproject clojure-test "0.1.0-SNAPSHOT"
  :description "First Clojure Web Service"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]
                 [ring/ring-json "0.3.1"]]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler clojure-test.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
