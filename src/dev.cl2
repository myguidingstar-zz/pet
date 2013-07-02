(load-file-macros "../node_modules/angular-cl2/src/angular.cl2")
(load-file-macros "../node_modules/cl2-contrib/src/concurrency.cl2")
(load-file-macros "../node_modules/cl2-contrib/src/timers.cl2")

(defapp myAppDev [myApp ngMockE2E])

(. myAppDev
   (run (fn-di [$httpBackend]
               (.. $httpBackend
                   (whenGET #"^partials/")
                   passThrough)
               )))
