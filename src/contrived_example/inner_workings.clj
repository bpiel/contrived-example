(ns contrived-example.inner-workings)

(def coin-values
  {:quarter 0.25
   :dime 0.10
   :nickel 0.05
   :penny 0.01})

(defn- calc-coin-value
  [coins]
  (->> coins
       (keep coin-values)
       (apply +)))

(defn- round-to-pennies
  "Because floating-point inaccuracy"
  [v]
  (-> v
      (* 100)
      Math/round
      (/ 100.0)))

(defn- calc-change-to-return*
  [amount-to-return coins]
  (let [value (calc-coin-value coins)]
    (cond
      (= value amount-to-return) coins
      (> value amount-to-return) nil
      :else (->> coin-values
                 (map first)
                 (map #(conj coins %))
                 (keep #(calc-change-to-return* amount-to-return %))
                 first))))

(defn- calc-change-to-return
  [machine selection]
  (let [amount-to-return (-> machine
                             :coins-inserted
                             calc-coin-value
                             (- (:price selection))
                             round-to-pennies)]
    (calc-change-to-return* amount-to-return [])))

(defn- get-selection
  [machine button]
  (-> machine
      :inventory
      button))

(defn valid-selection
  [machine button]
  (when-let [selection (get-selection machine button)]
    (and (some-> selection :qty (>= 1))
         (>= (-> machine
                 :coins-inserted
                 calc-coin-value)
             (:price selection)))))


;; dispense item
;; decrement qty
;; return change

(defn process-transaction
  [machine button]
  (let [selection (get-selection machine button)]
    (-> machine
        (update-in [:inventory button :qty]
                   dec)
        (assoc :dispensed
               selection)
        (assoc :coins-returned
               (calc-change-to-return machine
                                      selection))
        (assoc :coins-inserted []))))

(defn show-err-message
  [machine]
  (assoc machine :err-msg true))
