(ns contrived-example.server
  (:require [contrived-example.machine :as m]
            [ring.adapter.jetty :as jetty]
            [liberator.core :as l]
            [compojure.core :as c]
            [compojure.handler :as ch]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.params :as mw-params]))

(def machine-state {:inventory {:a1 {:name :taco
                                     :price 0.85
                                     :qty 10}}
                    :coins-inserted []
                    :coins-returned []
                    :dispensed nil
                    :err-msg nil})

(defn slow-poke
  [_]
  (Thread/sleep 50))

(defn questionable
  []
  (dorun (map slow-poke (range 10))))

(defn vend
  [ctx]
  (questionable)
  {::response "WAT"})

(defn handle-created
  [ctx]
  (::response ctx))

(l/defresource taco-vending-machine
  :allowed-methods [:post :get]
  :post! vend
  :handle-created handle-created
  :available-media-types ["application/json"]
  :available-languages ["es"]
  )

(c/defroutes app-routes
  (c/POST "/taco" [] taco-vending-machine))

(def app (ch/api #'app-routes))

(defn start [options]
  (jetty/run-jetty #'app (assoc options :join? false)))
