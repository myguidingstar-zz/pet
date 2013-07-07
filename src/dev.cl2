(load-file-macros "../node_modules/angular-cl2/src/angular.cl2")
(load-file-macros "../node_modules/cl2-contrib/src/concurrency.cl2")
(load-file-macros "../node_modules/cl2-contrib/src/timers.cl2")
(load-file "./sample-data.cl2")

(defapp myAppDev [myApp ngMockE2E])

(defmacro serve-entities
  "Serves entities from sample-data.cl2 via $httpBackend."
  [& entity-types]
  `(do
     ~@(apply
        concat
        (for [entity-type entity-types
              :let [sample-entities
                    (symbol (str "sample-" entity-type))]]
          (list
           ;; dirty hack so that mock data can be served independently
           ;; via similar urls
           `(let [ms (for [[id data] ~sample-entities
                           :let [url (+ "/api/" ~entity-type "/" id)]]
                       (assoc {} url [200 (serialize data)]))
                  routes (apply merge ms)]
              (doseq [[url response] routes]
                (.. $httpBackend
                  (whenGET url)
                  (respond
                   (fn [_ url]
                     (get routes url))))))

           `(.. $httpBackend
                (whenGET (+ "/api/" ~entity-type))
                (respond
                 (fn []
                   [200 (serialize ~sample-entities)])))
           )))))

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
