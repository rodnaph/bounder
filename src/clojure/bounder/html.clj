
(ns bounder.html
  (:use net.cgrand.enlive-html)
  (:require [clojure.string :as s]
            [bounder.data :as data]))

(defn- to-static-url [attr]
  (fn [node]
    (update-in node [:attrs attr]
               #(s/replace %1 "../../" ""))))

(defn- projects-as-edn [projects]
  (html
    [:script {:type "text/javascript"}
     (format
       "bounder.core.init.call(null, '%s')"
       (pr-str projects))]))

(deftemplate index "index.html"
  [projects]
  [:link] (to-static-url :href)
  [:script] (to-static-url :src)
  [:body] (append (projects-as-edn projects))
  [:title] (content (format "Bounder (%d projects)"
                            (count projects))))

;; Public
;; ------

(defn generate [projects]
  (spit "index.html"
         (s/join "" (index projects))))

