(ns contrived-example.server
  (:require [contrived-example.machine :as m]
            [ring.adapter.jetty :as jetty]
            [liberator.core :as l]
            [compojure.core :as c]
            [compojure.handler :as ch]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.params :as mw-params]))

(defn slow-poke
  [_]
  (Thread/sleep 50))

(defn questionable
  []
  (dorun (map slow-poke (range 10))))

(defn vend
  [ctx]
  (questionable)
  :TACO!)

(defn handle-created
  [ctx]
  #_(::response ctx)
  "hi")

(l/defresource taco-vending-machine
  :allowed-methods [:post :get]
  :post! vend
  :handle-created handle-created
  :available-languages ["es"])

(c/defroutes app-routes
  (c/POST "/taco" [] taco-vending-machine))

(def app (ch/api #'app-routes))

(defn start [options]
  (jetty/run-jetty #'app (assoc options :join? false)))
