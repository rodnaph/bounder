
(ns bounder.html
  (:use net.cgrand.enlive-html)
  (:require [clojure.string :as s]
            [bounder.data :as data]))

(defn- version-url [project version]
  (format "projects/%s/%s/index.html"
          (:name project)
          version))

(defn- category-to-li [category]
  (fn [node]
    (at node
        [:.] (content (name category)))))

(defn- version-to-li [project version]
  (fn [node]
    (at node
        [:a] (do-> (content version)
                   (set-attr :href
                             (version-url project version))))))

(defn- project-to-li [project]
  (fn [node]
    (at node
        [:h3 :a] (do-> (content (:name project))
                       (set-attr :href (:url project)))
        [:.description] (content (:description project))
        [:.versions :li] (clone-for [version (:versions project)]
                                    (version-to-li project version))
        [:.categories :li] (clone-for [category (:categories project)]
                                                (category-to-li category)))))

(defn- to-static-url [attr]
  (fn [node]
    (update-in node [:attrs attr]
               #(s/replace %1 "../../" ""))))

(deftemplate index "index.html"
  [projects]
  [:link] (to-static-url :href)
  [:title] (content (format "Bounder (%d projects)"
                            (count projects)))
  [:.project] (clone-for [project projects]
                         (project-to-li project)))

;; Public
;; ------

(defn generate [projects]
  (spit "index.html"
         (s/join "" (index projects))))

