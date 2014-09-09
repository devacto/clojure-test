(ns clojure-test.test.handler
  (:require [clojure.test :refer :all]
            [clojure-test.handler :refer :all]
            [ring.mock.request :as mock]))

(deftest test-app
  (testing "main route should return Hello World"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"hello\":\"Hello World\"}"))))
  
  (testing "sum should return correct result"
    (let [response (app (mock/request :get "/sum/2/3"))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"result\":\"5\"}"))))
  
  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404))))

  (testing "animals should get animals"
    (let [response (app (mock/request :get "/animals"))]
      (is (= (:status response) 200)))))
