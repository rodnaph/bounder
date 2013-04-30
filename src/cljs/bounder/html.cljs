
(ns bounder.html
  (:require [enfocus.core :refer [at]])
  (:require-macros [enfocus.macros :as em]))

(defn version-url [project version]
  (format "projects/%s/%s/index.html"
          (:name project)
          version))

(em/defsnippet category-tpl :compiled
  "resources/views/index.html" [:.categories :li]
  [category]
  [:*] (em/content (name category)))

(em/defsnippet version-tpl :compiled
  "resources/views/index.html" [:.versions :li]
  [project version]
  [:a] (em/do-> (em/content version)
                (em/set-attr :href (version-url project version))))

(em/defsnippet project-tpl :compiled
  "resources/views/index.html" [:.projects :> :li]
  [project]
  [:h3 :a] (em/do-> (em/content (:name project))
                          (em/set-attr :href (:url project)))
  [:.description] (em/content (:description project))
  [:.versions] (em/content (map (partial version-tpl project)
                                (:versions project)))
  [:.categories] (em/content (map category-tpl
                                  (:categories project))))

;; Public
;; ------

(defn render-projects [projects]
  (at js/document
      [".projects"] (em/content
                      (map project-tpl projects))))

