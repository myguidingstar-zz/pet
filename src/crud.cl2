(defn matches-map?
  "Checks if all keys and values in m2 are in m1."
  [m1 m2]
  (= (select-keys m1 (keys m2))
     m2))

(defn add-entity!
  "Adds an entities to an atom of map type. If id is nil,
  get one from new-entity's :id value."
  [entities-atom id new-entity]
  (let [id (or id (:id new-entity))]
    (swap! entities-atom
           (fn [entities]
             (set! (get entities id) new-entity)
             entities))))

(defn remove-entity!
  "Removes an entities to an atom of map type"
  [entities-atom id]
  (swap! entities-atom
         (fn [entities]
           (delete (get entities id))
           entities)))

(defn find-entities
  "Finds entities that match given criteria."
  [entities-atom criteria]
  (let [pred (if (fn? criteria)
               criteria
               #(matches-map? % criteria))]
    (for [entity @entities-atom
          :when (pred entity)]
      entity)))

(defn find-&-update-entities!
  "Finds entities that match given criteria."
  [entities-atom criteria updater]
  (let [pred (if (fn? criteria)
               criteria
               #(matches-map? % criteria))
        ret {}]
    (doseq [[id entity] @entities-atom
            :when (pred entity)]
      (set! (get @entities-atom id)
            (updater entity)))))
