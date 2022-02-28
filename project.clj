(defproject clj-rename-tvshows "0.1.0"
  :description "Renames a hierarchy of TV Show files for converting with mnamer"
  :url "https://github.com/mwiederhold/clj-rename-tvshows"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :main ^:skip-aot clj-rename-tvshows.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
