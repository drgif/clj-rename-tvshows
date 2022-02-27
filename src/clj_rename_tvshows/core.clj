(ns clj-rename-tvshows.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn- parse-dir-contents [m type dir]
  (->> dir
       (io/file)
       (.listFiles)
       (map (fn [file] (merge m {type {:name (.getName file)
                                       :path (.getAbsolutePath file)}})))))

(defn- extract-numbers [s]
  (apply str (filter #(Character/isDigit %) s)))

(defn- new-filename [episode]
  (let [show-name (get-in episode [:show :name])
        season-x (str (extract-numbers (get-in episode [:season :name])) "x")
        episode-name (get-in episode [:episode :name])]
   (str (if-not (str/starts-with? episode-name show-name)
          (str show-name "."))
        (if-not (str/includes? episode-name season-x) 
          season-x)
        episode-name)))

(defn- new-filepath [episode]
  (str/replace (get-in episode [:episode :path])
               (get-in episode [:episode :name])
               (new-filename episode)))

(defn- rename-episode! [episode]
  (.renameTo (io/file (get-in episode [:episode :path]))
             (io/file (new-filepath episode))))

(defn- run
  "All the logic in one function"
  [basefolder]
  (let [shows (parse-dir-contents {} :show basefolder)
        seasons (mapcat #(parse-dir-contents % :season (:path (:show %))) shows)
        episodes (mapcat #(parse-dir-contents % :episode (:path (:season %))) seasons)]
    (run! rename-episode! episodes)))

(comment
  (run "./doc")
  (run "./target")
  (run "./examples"))