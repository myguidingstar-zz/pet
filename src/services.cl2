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

(defservice evaluation
  "Stores evaluation template and opinions by members"
  [entitiesService]
  (this->!)
  (def! opinions (atom {}))

  (defn! add-opinion
    [board-id project-id template-id template-version
     user-id item-id opinion]
    (add-entity!
     (!- opinions) nil
     (assoc
      opinion
      :id
      (inc (or (apply max
                      (map parseInt (keys @(!- opinions))))
               0))
      :board-id board-id
      :project-id project-id
      :template-id template-id
      :template-version template-version
      :user-id user-id
      :item-id item-id)))

  (defn! remove-opinion
    [id]
    (remove-entity! (!- opinions) id))

  (defn! approve-opinion
    [board-id project-id template-id template-version
     item-id opinion-id]
    (find-&-update-entities!
     (!- opinions)
     {:board-id board-id
      :project-id project-id
      :template-id template-id
      :template-version template-version
      :item-id item-id}
     (fn [opinion]
       (if (=== opinion.id opinion-id)
         (assoc opinion
           :approved    true
           ;; set by server, not client
           :approved-by 1
           :approved-ts (.now Date))
         (assoc opinion
           :approved    nil
           :approved-by nil
           :approved-ts nil)))))

  (defn! unapprove-opinion
    [board-id project-id template-id template-version
     item-id opinion-id]
    (find-&-update-entities!
     (!- opinions)
     {:board-id board-id
      :project-id project-id
      :template-id template-id
      :template-version template-version
      :item-id item-id}
     (fn [opinion]
       (assoc opinion
         :approved    nil
         :approved-by nil
         :approved-ts nil))))
  )
