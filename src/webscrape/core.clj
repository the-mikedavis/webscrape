(ns webscrape.core
  (:require [net.cgrand.enlive-html :as html]
            [org.httpkit.client :as http]
            [clojure.string :as string])
  (:refer-clojure :exclude [class]) ; don't need class keyword
  (:gen-class))

(defn parse-csv
  "Read in the values from a csv file"
  [filename]
  (remove nil?
          (map #(string/split % #",")
               (string/split (slurp filename) #"\n"))))

(defn url->dom
  "Gets the DOM of a url's response."
  [url]
  (html/html-snippet
    (:body @(http/get url {:insecure? true}))))

(defn extract
  "Extract from dom the tags with classes."
  [url tag class]
   (map (comp #(string/join " " %) :content)
        (html/select (url->dom url)
                     [(keyword (str tag "." class))])))

(defn print-list
  "Print out a list line by line."
  [list-arg]
  (map println list-arg))

(defn -main
  ([]
   (map println (extract (url->dom "http://news.ycombinator.com/")
                         "a" "storylink")))
  ([filename]
   (doseq [visit (parse-csv filename)]
     (print-list (apply extract visit))))
  ([url tag class]
   (print-list (extract url)
                        tag class)))
