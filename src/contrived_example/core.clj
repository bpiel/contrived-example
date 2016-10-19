(ns contrived-example.core
  (:require [contrived-example.server :as server]))

(defn -main
  ([port-str]
   (let [port (Integer/parseInt port-str)]
     (println (format "Starting http server on port %s" port))
     (server/start {:port port})
     (println (format "STARTED http server on port %s" port))))
  ([]
   (-main "8000")))

#_ (-main)

