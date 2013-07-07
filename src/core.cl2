(load-file "../node_modules/angular-cl2/src/angular.cl2")
(load-file "../node_modules/cl2-contrib/src/concurrency.cl2")
(load-file "../node_modules/cl2-contrib/src/timers.cl2")
(load-file "../node_modules/cl2-contrib/src/json.cl2")
(load-file "./crud.cl2")
(load-file "./helpers.cl2")

(defapp myApp [ui.bootstrap $strap.directives])

;; don't have to specify app name as compiler remember the last app name
;; defined in `defapp`
(defroute
  "/evaluation/:boardId/:projectId"
  ['evalCtrl "partials/evaluation.html" ]
  "/default" ['myCtrl "partials/default.html" ]
  "/view-2"  {:controller 'emptyCtrl
              :template
              (hiccup
               [:h2
                "Hiccup rocks!"])}
  :default "/default")

(load-file "./directives.cl2")
(load-file "./controllers.cl2")
(load-file "./services.cl2")
(load-file "./filters.cl2")
