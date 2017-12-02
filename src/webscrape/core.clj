(ns webscrape.core
  (:require [net.cgrand.enlive-html :as html]
            [org.httpkit.client :as http]
            [clojure.string :as string])
  (:refer-clojure :exclude [class]) ; don't need class keyword
  (:gen-class))

(defn ^:dynamic url->dom
  "Gets the DOM of a url's response."
  [url]
  (html/html-snippet
    (:body @(http/get url {:insecure? true}))))

(defn ^:dynamic extract
  "Extract from dom the tags with classes. Creates a lazy-seq."
  [dom tag class]
  (map (comp #(string/join " " %) :content)
       (html/select dom
                    [(keyword (str tag "." class))])))

(defn -main
  [url tag class]
  (let [captures (extract (url->dom url)
                          tag class)]
    (map println captures)))
