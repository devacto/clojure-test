(ns clojure-test.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]))

(defn- hello-world [& _]
  {:status 200
   :body {:hello "Hello World"}})

(defn- calculate-sum [x1 x2]
  (let [result (+ x1 x2)]
    {:status 200
     :body {:result (Integer/toString result)}}))

(defroutes app-routes
  (GET "/sum/:x1/:x2" [x1 x2] (calculate-sum (read-string x1) (read-string x2)))
  (GET "/" [] hello-world)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-params)
      (middleware/wrap-json-response)))
