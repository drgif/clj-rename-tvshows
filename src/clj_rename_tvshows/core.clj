(ns clj-rename-tvshows.core
  (:require [clojure.java.io])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn- run
  "All the logic in one function"
  [basefolder]
  (->> basefolder
       (clojure.java.io/file)
       (file-seq)
       (map #(.getName %))))

(comment
  (run "./doc"))