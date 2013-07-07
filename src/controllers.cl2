(defcontroller myCtrl
  [$scope entitiesService]
  ($->atom entities entitiesService.entities)
  (. entitiesService getEntities "boards" 0)
  (def$ someNumber 12)
  (defn$ addTwo [n] {:result (+ n 2)})
  (defn$ serviceAdd [n]
    (myService.addThree n)))

(defcontroller emptyCtrl
  [$scope])
