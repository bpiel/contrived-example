(ns contrived-example.sayid
  (:require [com.billpiel.sayid.nrepl-middleware :as smw]
            [com.billpiel.sayid.view :as sv]))

(smw/register-view! :lib-decide (sv/pred-sel-pairs->view [[[:name #"decide"] {:return true :selects {:name [:arg-map 'name]}}]]))
