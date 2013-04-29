
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

(defn- api-docs* [project]
  (let [tmp-dir (fs/temp-dir (:name project))
        tmp-path (.getAbsolutePath tmp-dir)
        doc-path (format "%s/doc" tmp-path)]
    (git/git-clone-full (:git-url project) tmp-path)
    (with-sh-dir tmp-path
      (sh-str "lein doc"))
    (fs/copy-dir doc-path local-path)))

;; Public
;; ------

(defn api-docs [project]
  (let [local-path (local-path project)]
    (if (not (fs/exists? local-path))
      (api-docs* project local-path))))

