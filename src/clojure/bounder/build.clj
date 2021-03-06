
(ns bounder.build
  (:use [clojure.java.shell :only [sh with-sh-dir]])
  (:require [clojure.string :as s]
            [fs.core :as fs]
            [clj-jgit.porcelain :as git]))

(defn- local-path [project]
  (format "projects/%s/%s"
          (:name project)
          (:version project)))

(defn- sh-str [cmd]
  (apply sh (s/split cmd #"\s+")))

(defn- api-docs* [project local-path]
  (let [tmp-dir (fs/temp-dir (:name project))
        tmp-path (.getAbsolutePath tmp-dir)
        doc-path (format "%s/doc" tmp-path)
        result (git/git-clone-full (:git-url project) tmp-path)]
    (println "Building API docs for " (:name project))
    (println "tmp: " tmp-path)
    (println "doc: " doc-path)
    (git/git-checkout (:repo result)
                      (:version project))
    (with-sh-dir tmp-path
      (sh-str "lein doc"))
    (fs/copy-dir doc-path local-path)))

;; Public
;; ------

(defn api-docs [project]
  (let [local-path (local-path project)]
    (if (not (fs/exists? local-path))
      (api-docs* project local-path))))

