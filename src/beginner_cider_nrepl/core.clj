(ns beginner-cider-nrepl.core)

(defn greet
  "a function called by app"
  [x]
  (concat "Hello there, " x " !!!..."))

(defn app [req]
  {:status 200
   :headers {"content-Type" "text/html"}
   :body (greet "Beginner :)")})
