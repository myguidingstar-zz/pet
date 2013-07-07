(defcontroller myCtrl
  [$scope entitiesService]
  ($->atom entities entitiesService.entities)
  (. entitiesService getEntities "boards" 0))

(defcontroller emptyCtrl
  [$scope])
