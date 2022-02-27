(ns clj-rename-tvshows.core
  (:require [clojure.java.io])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn- parse-dir-contents [m type dir]
  (->> dir
       (clojure.java.io/file)
       (.listFiles)
       (map (fn [file] (merge m {type {:name (.getName file)
                                     :path (.getPath file)}})))))

(defn- run
  "All the logic in one function"
  [basefolder]
  (let [shows (parse-dir-contents {} :show basefolder)
        seasons (mapcat #(parse-dir-contents % :season (:path (:show %))) shows)
        episodes (mapcat #(parse-dir-contents % :episode (:path (:season %))) seasons)]
    episodes))

(comment
  (run "./doc")
  (run "./target")
  (run "./examples"))