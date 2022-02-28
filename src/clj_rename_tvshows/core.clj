(ns clj-rename-tvshows.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(defn- parse-dir-contents 
  "Merges information about folder contents with the information of the parent folder"
  [m type dir]
  (->> dir
       (io/file)
       (.listFiles)
       (map (fn [file] (merge m {type {:name (.getName file)
                                       :path (.getAbsolutePath file)}})))))

(defn- extract-numbers
  "Strips all non-digit characters from a string"
  [s]
  (apply str (filter #(Character/isDigit %) s)))

(defn- new-filename 
  "Returns the new filename"
  [episode]
  (let [show-name (get-in episode [:show :name])
        season-x (str (extract-numbers (get-in episode [:season :name])) "x")
        episode-name (get-in episode [:episode :name])]
   (str (if-not (str/starts-with? episode-name show-name)
          (str show-name "."))
        (if-not (str/includes? episode-name season-x) 
          season-x)
        episode-name)))

(defn- new-filepath 
  "Returns the new absolute filepath"
  [episode]
  (str/replace (get-in episode [:episode :path])
               (get-in episode [:episode :name])
               (new-filename episode)))

(defn- rename-episode!
  "Executes the actual renaming"
  [episode]
  (.renameTo (io/file (get-in episode [:episode :path]))
             (io/file (new-filepath episode))))

(defn- rename-episodes
  "Main program logic"
  [basepath]
  (let [shows (parse-dir-contents {} :show basepath)
        seasons (mapcat #(parse-dir-contents % :season (:path (:show %))) shows)
        episodes (mapcat #(parse-dir-contents % :episode (:path (:season %))) seasons)]
    (run! rename-episode! episodes)))

(defn- is-folder?
  "Checks if a string corresponds to a (relative or absolute) path to a folder"
  [path]
  (->> path
       (io/file)
       (.isDirectory)))

(defn -main
  "Renames TV Show episodes in a folder hierarchy under basepath provided by first argument"
  [& args]
  (let [basepath (first args)]
    (cond
      (nil? basepath) (println "Please provide a basepath to TV Shows")
      (not (is-folder? basepath)) (println (str basepath " is not a valid directory"))
      :else (rename-episodes basepath))))


(comment
  (rename-episodes "./doc")
  (rename-episodes "./target")
  (rename-episodes "./examples")
  (is-folder? "./doc")
  (is-folder? "foo")
  (-main)
  (-main "foo")
  (-main "examples"))
