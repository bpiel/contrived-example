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

#_ (smw/register-view! :lib-decide (sv/pred-sel-pairs->view [[[:name #"decide"] {:return true :selects {:name [:arg-map 'name]}}]]))
