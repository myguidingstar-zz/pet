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
                     (println "Error fetching" (+ "/api/" type)
                              "Status: " status
                              "Data:"    data))))
      (.. $http
          (get (+ "/api/" type "/" id))
          (success (fn [data status]
                     (add-entity! (get (!- entities) type)
                                  id data)))
          (error   (fn [data status]
                     (println "Error fetching" (+ "/api/" type "/" id)
                              "Status: " status
                              "Data:"    data)))))
    )
  )

(defservice session
  "Stores current logged-in user's information."
  [entitiesService]
  (this->!)

  (set! (!- current) (atom nil))

  (defn! login!
    "Sends login credentials to server."
    [username password]
    )

  (defn! login-as!
    [user-id]
    (reset! (!- current)
            (get @entitiesService.entities.users user-id)))

  ;; initial stage: guest session
  ((!- login-as!) 1)
  )
