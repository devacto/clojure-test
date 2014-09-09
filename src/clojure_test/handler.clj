(ns clojure-test.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]))

(def animals (atom ()))

(defn add-animal
  [x]
  (swap! animals conj x))

(defn- get-animals [& _]
  {:status 200
   :body {:animals animals}})

(defn- hello-world [& _]
  {:status 200
   :body {:hello "Hello World"}})

(defn- calculate-sum [x1 x2]
  (let [result (+ x1 x2)]
    {:status 200
     :body {:result (Integer/toString result)}}))

(defn- victor []
  println "Hello!")

(defroutes app-routes
  (GET "/sum/:x1/:x2" [x1 x2] (calculate-sum (read-string x1) (read-string x2)))
  (GET "/" [] hello-world)
  (POST "/animal/:x1" [x1] (add-animal (read-string x1)))
  (GET "/animals" [] get-animals)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-params)
      (middleware/wrap-json-response)))
