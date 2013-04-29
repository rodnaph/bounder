
(ns bounder.data
  (:require [clojure.string :as s]))

(defn- git-url [project-name]
  (format "https://github.com/%s.git" project-name))

(defn- github
  ([project-name] (github project-name {}))
  ([project-name options]
    (let [[user repo] (s/split project-name #"/")]
      (merge {:name repo
              :git-url (git-url project-name)}
             options))))

;; Public
;; ------

(def projects [
  (github "rodnaph/decker"
          {:categories [:tools :database]
           :versions ["0.0.2"]})
])

