
(ns bounder.data
  (:require [clojure.string :as s]))

(defn- git-url [project-name]
  (format "https://github.com/%s.git" project-name))

(defn- github-url [project-name]
  (format "https://github.com/%s" project-name))

(defn- github
  ([project-name] (github project-name {}))
  ([project-name options]
    (let [[user repo] (s/split project-name #"/")]
      (merge {:name repo
              :url (github-url project-name)
              :git-url (git-url project-name)}
             options))))

;; Public
;; ------

(def projects [

  (github "rodnaph/decker"
          {:categories [:database]
           :versions ["0.0.2"]})

  (github "rodnaph/confo"
          {:categories [:configuration]
           :versions ["0.4.0"]})

  (github "rodnaph/gmailer"
          {:categories [:email]
           :versions ["0.0.1"]})

  (github "clj-jgit/clj-jgit"
          {:categories [:tools :git]
           :versions ["v0.0.3"]})

  (github "Raynes/fs"
          {:categories [:filesystem]
           :versions ["1.3.2"]})

  (github "weavejester/codox"
          {:categories [:documentation]
           :versions ["0.6.4" "0.6.3"]})

  (github "cgrand/enlive"
          {:categories [:templating]
           :versions ["master"]})

])

