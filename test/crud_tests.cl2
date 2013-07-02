(deftest add-entity!-tests
  (def entities (atom {}))
  (is (= {} @entities))
  (add-entity! entities 0 {:id 0 :foo "Good" :type "X"})
  (is (= {0 {:id 0 :foo "Good" :type "X"}}
         @entities))
  (add-entity! entities 1 {:id 1 :foo "Bad"  :type "Y"})
  (is (= {0 {:id 0 :foo "Good" :type "X"}
          1 {:id 1 :foo "Bad"  :type "Y"}}
         @entities))
  (add-entity! entities nil {:id 2 :foo "Bla"  :type "Y"})
  (is (= {0 {:id 0 :foo "Good" :type "X"}
          1 {:id 1 :foo "Bad"  :type "Y"}
          2 {:id 2 :foo "Bla"  :type "Y"}}
         @entities)))

(deftest remove-entity!-tests
  (def entities (atom {0 {:id 0 :foo "Good" :type "X"}
                       1 {:id 1 :foo "Bad"  :type "Y"}}))
  (is (= {0 {:id 0 :foo "Good" :type "X"}
          1 {:id 1 :foo "Bad"  :type "Y"}}
         @entities))
  (remove-entity! entities 1)
  (is (= {0 {:id 0 :foo "Good" :type "X"}}
         @entities))
  (remove-entity! entities 0)
  (is (= {}
         @entities)))

(deftest find-entities-tests
  (def entities (atom {0 {:id 0 :foo "Good" :type "X"}
                       1 {:id 1 :foo "Bad"  :type "Y"}
                       2 {:id 2 :foo "But"  :type "Y"}
                       3 {:id 3 :foo "Golf" :type "Y"}}))
  (is (= (find-entities entities {:type "Y"})
         [{:id 1 :foo "Bad"  :type "Y"}
          {:id 2 :foo "But"  :type "Y"}
          {:id 3 :foo "Golf" :type "Y"}]))
  (is (= (find-entities entities #(< 0 (:id %)))
         [{:id 1 :foo "Bad"  :type "Y"}
          {:id 2 :foo "But"  :type "Y"}
          {:id 3 :foo "Golf" :type "Y"}])))

(deftest find-&-update-entities!-tests
  (def entities (atom {0 {:id 0 :foo "Good" :value 2}
                       1 {:id 1 :foo "Bad"  :value 3}
                       2 {:id 2 :foo "Good" :value 5}
                       3 {:id 3 :foo "Good" :value 1}}))
  (find-&-update-entities!
   entities {:foo "Good"}
   #(merge % {:value (inc (:value %))}))
  (is (= {0 {:id 0 :foo "Good" :value 3}
          1 {:id 1 :foo "Bad"  :value 3}
          2 {:id 2 :foo "Good" :value 6}
          3 {:id 3 :foo "Good" :value 2}}
         @entities)))
