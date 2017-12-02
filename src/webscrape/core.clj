(ns webscrape.core
  (:require [net.cgrand.enlive-html :as html]
            [org.httpkit.client :as http]
            [clojure.string :as string])
  (:refer-clojure :exclude [class]) ; don't need class keyword
  (:gen-class))

(defn url->dom
  "Gets the DOM of a url's response."
  [url]
  (html/html-snippet
    (:body @(http/get url {:insecure? true}))))

(defn extract
  "Extract from dom the tags with classes. Creates a lazy-seq."
  [dom tag class]
  (map (comp #(string/join " " %) :content)
       (html/select dom
                    [(keyword (str tag "." class))])))

(defn -main
  ([]
   (map println (extract (url->dom "http://news.ycombinator.com/")
                         "a" "storylink")))
  ([url tag class]
   (let [captures (extract (url->dom url)
                           tag class)]
    (map println captures))))
