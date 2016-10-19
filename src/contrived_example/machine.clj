(ns contrived-example.machine
  (:require [contrived-example.inner-workings :as iw]))

(defn insert-coin
  [machine coin]
  (update-in machine
             [:coins-inserted]
             conj
             coin))

(defn press-button
  [machine button]
  (if (iw/valid-selection machine button)
    (iw/process-transaction machine button)
    (iw/show-err-message machine)))

(defn retrieve-dispensed
  [machine]
  [(:dispensed machine)
   (dissoc machine :dispensed)])

(defn retrieve-change-returned
  [machine]
  [(:change-returned machine)
   (dissoc machine :change-returned)])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
