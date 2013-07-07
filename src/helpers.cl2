(defn gen-access-key
  "Generates access-id from some ids
  Access-keys are used to access data of services' whose data-structure
  is a map."
  [& ids]
  (.join ids ","))

(defn opinions->points!
  "Calculates points from opinions and template-items.
    Results are updated (via swap!) directly to given `points` atom."
  [opinions points-atom template-items]
  (reset! points-atom {})
  (doseq [opinion opinions
          :when (:approved opinion)]
    (let [lookup-value #(and (=== (:item-id opinion) (:id %))
                             (:value %))]
      (swap! points-atom
             (fn [m]
               (set! (get m (:item-id opinion))
                     (* (some lookup-value template-items)
                        (if (= "unchecked" opinion.type)
                          0
                          1)))
               m)))))
