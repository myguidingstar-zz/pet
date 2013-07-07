(defservice entitiesService
  [$http]
  (this->!)
  (def! entities {})
  (doseq [entity-type [:boards :users :templates
                       :projects :langs]]
    (assoc! (!- entities) entity-type (atom {})))

  (defn! fetch [type id]
    (if (undefined? id)
      (.. $http
          (get (+ "/api/" type))
          (success (fn [data status]
                     (reset! (get (!- entities) type)
                             data)))
          (error   (fn [data status]
                     (alert (pr-str data)))))
      (.. $http
          (get (+ "/api/" type "/" id))
          (success (fn [data status]
                     (add-entity! (get (!- entities) type)
                                  id data)))
          (error   (fn [data status]
                     (alert (pr-str data))))))
    )
  )