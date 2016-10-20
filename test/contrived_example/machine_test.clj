(ns contrived-example.machine-test
  (:require [clojure.test :refer :all]
            [contrived-example.machine :as m]))


(def test-vending-machine {:inventory {:a1 {:name :taco
                                            :price 0.85
                                            :qty 10}}
                           :coins-inserted []
                           :coins-returned []
                           :dispensed nil
                           :err-msg nil})

(def expected-vending-machine {:inventory {:a1 {:name :taco
                                                :price 0.85
                                                :qty 10}}
                               :coins-inserted [:quarter :dime :nickel :penny]
                               :coins-returned []
                               :dispensed nil
                               :err-msg true})

(deftest test1
  (is (= (-> test-vending-machine
             (m/insert-coin :quarter) ;; 25
             (m/insert-coin :dime)    ;; 35
             (m/insert-coin :nickel)  ;; 40
             (m/insert-coin :penny)   ;; 41 cents
             (m/press-button :a1))    ;; taco costs 85 cents
         expected-vending-machine)))  
