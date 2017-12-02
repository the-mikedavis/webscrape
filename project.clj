(defproject webscrape "0.1.0"
  :description "A Basic Webscraping Tool"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [enlive "1.1.6"]
                 [http-kit "2.1.18"]
                 [org.clojure/tools.trace "0.7.9"]]
  :main ^:skip-aot webscrape.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
