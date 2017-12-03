(ns webscrape.core
  (:require [net.cgrand.enlive-html :as html]
            [org.httpkit.client :as http]
            [clojure.string :as string])
  (:refer-clojure :exclude [class]) ; don't need class keyword
  (:gen-class))

(defn parse-csv
  "Read in the values from a csv file"
  [filename]
  (map #(string/split % #",")
       (string/split (slurp filename) #"\n")))

(defn url->dom
  "Gets the DOM of a url's response."
  [url]
  (html/html-snippet
    (:body @(http/get url {:insecure? true}))))

(defn extract
  "Extract from dom the tags with classes."
  [url identifier]
   (map (comp #(string/join " " %) :content)
        (html/select (url->dom url)
                     [(keyword identifier)])))

(defn print-list
  "Print out a list line by line."
  [list-arg]
  (println (string/join "\n" list-arg))
  (println))

(defn -main
  ([]
   (map println (extract (url->dom "http://news.ycombinator.com/")
                         "a.storylink")))
  ([filename]
   (doall (pmap #(print-list (apply extract %))
         (parse-csv filename))))
  ([url identifier]
   (print-list (extract url identifier))))
