(defproject contrived-example "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [liberator "0.14.1"]
                 [compojure "1.5.1"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [ring/ring-json "0.4.0"]
                 [swiss-arrows "1.0.0"]]
  :main ^:skip-aot contrived-example.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
