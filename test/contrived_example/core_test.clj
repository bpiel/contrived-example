(ns contrived-example.core-test
  (:require [clojure.test :refer :all]
            [contrived-example.core :as ce]))


(def test-vending-machine {:inventory {:a1 {:name :taco
                                            :price 0.85
                                            :qty 10}}
                           :coins-inserted []
                           :coins-returned []
                           :dispensed nil
                           :err-msg nil})

(defn test1 []
  (-> test-vending-machine
      (ce/insert-coin :quarter) ;; 25
      (ce/insert-coin :dime)    ;; 35
      (ce/insert-coin :nickel)  ;; 40
      (ce/insert-coin :penny)   ;; 41 cents
      (ce/press-button :a1)))   ;; taco costs 85 cents

(test1)
