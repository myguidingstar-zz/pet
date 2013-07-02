(load-file "../node_modules/angular-cl2/src/angular.cl2")

(defapp myApp [])

;; don't have to specify app name as compiler remember the last app name
;; defined in `defapp`
(defroute
  "/default" ['myCtrl "partials/default.html" ]
  "/view-2"  {:controller 'emptyCtrl
              :template
              (hiccup
               [:h2
                "Hiccup rocks!"])}
  :default "/default")

(defdirective myDirective
  []
  ;; can be a directive-definition object or a link function
  (fn [scope elm attrs]
    (.
     scope
     ($watch
      (. attrs -myDirective)
      (fn [value] (. elm (text (+ value 4))))))))

(defcontroller myCtrl
  [$scope myService]
  (def$ someNumber 12)
  (defn$ addTwo [n] {:result (+ n 2)})
  (defn$ serviceAdd [n]
    (myService.addThree n)))

(defcontroller emptyCtrl
  [$scope])

;; example of specifying app name
(defservice myApp myService
 []
 (this->!)
 (defn! addThree [n] (+ n 3)))

;; generic defmodule usage
(defmodule myApp
  (:filter (myFilter [] [s] (+ s 5))))

(deffilter yourFilter []
  [s]
  (+ s 6))
