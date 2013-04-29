
(ns bounder.core
  (:require [bounder.html :as html]
            [bounder.data :as data]
            [bounder.build :as build]))

(defn- build-projects []
  (doseq [project data/projects]
    (doseq [version (:versions project)]
      (println (format "Checking %s v%s"
                       (:name project)
                       version))
      (build/api-docs
        (assoc project :version version)))))

(defn- build-html []
  (html/generate data/projects))

;; Public
;; ------

(defn -main []
  (build-projects)
  (build-html))

