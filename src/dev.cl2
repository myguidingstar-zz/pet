(load-file-macros "../node_modules/angular-cl2/src/angular.cl2")
(load-file-macros "../node_modules/cl2-contrib/src/concurrency.cl2")
(load-file-macros "../node_modules/cl2-contrib/src/timers.cl2")
(load-file "./sample-data.cl2")

(defapp myAppDev [myApp ngMockE2E])

(defmacro serve-entities
  "Serves entities from sample-data.cl2 via $httpBackend."
  [& entity-types]
  `(do
     ~@(for [entity-type entity-types]
         `(doseq [id (keys ~(symbol (str "sample-" entity-type)))]
            (.. $httpBackend
                (whenGET (+ "/api/" ~entity-type))
                (respond
                 (fn []
                   [200
                    (serialize ~(symbol (str "sample-" entity-type)))]
                   )))
            (.. $httpBackend
                (whenGET (+ "/api/" ~entity-type "/" id))
                (respond
                 (fn []
                   [200
                    (serialize (get ~(symbol (str "sample-" entity-type)) id))]
                   )))))))

(. myAppDev
   (run (fn-di [$httpBackend]
               (serve-entities "boards"
                               "users"
                               "templates"
                               "projects"
                               "langs")
               (.. $httpBackend
                   (whenGET #"^partials/")
                   passThrough)
               )))
