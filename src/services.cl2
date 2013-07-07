(defservice entitiesService
  [$http]
  (this->!)
  (def! entities (atom {}))

  (defn! getEntities [type id]
    (.. $http
        (get (+ "/api/" type "/" id))
        (success (fn [data status]
                   (reset! (!- entities) data)))
        (error   (fn [data status]
                   (alert (pr-str data)))))
    nil)

  nil)
