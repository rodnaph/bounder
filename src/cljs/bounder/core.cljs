
(ns bounder.core
  (:require [bounder.html :as html]
            [domina :refer [value]]
            [lowline.functions :refer [debounce]]
            [enfocus.core :refer [at]]
            [cljs.reader :as reader]
            [clojure.string :as s])
  (:require-macros [enfocus.macros :as em]))

(defn project-matches [query project]
  (let [categories (map name (:categories project))
        words (cons (:name project) categories)
        to-match (s/lower-case (s/join " " words))]
    (<= 0 (.indexOf to-match (s/lower-case query)))))

(defn filter-projects [projects evt]
  (let [query (value (.-currentTarget evt))
        to-show (filter (partial project-matches query) projects)]
    (html/render-projects to-show)))

(defn init-listeners [projects]
  (at js/document
    ["input"] (em/listen
                :keyup
                (debounce
                  (partial filter-projects projects)
                  500))
    [".category-links li"] (em/listen
                             :click
                             filterer)))

(defn init [projects-edn]
  (let [projects (reader/read-string projects-edn)]
    (init-listeners projects)
    (html/render-projects projects)))

