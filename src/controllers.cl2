(defcontroller myCtrl
  [$scope entitiesService]
  ($->atom boards entitiesService.entities.boards)
  (. entitiesService fetch "boards" 0)
  )

(defcontroller emptyCtrl
  [$scope])
