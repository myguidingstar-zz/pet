(defcontroller myCtrl
  [$scope entitiesService]
  ($->atom boards entitiesService.entities.boards)
  (. entitiesService fetch "boards" 0)
  )

(defcontroller evalCtrl
  [$scope $location $routeParams
   session evaluation entitiesService]

  ;; Links some scope attrs to atoms from entities service
  ($->atom langs     entitiesService.entities.langs)
  ($->atom boards    entitiesService.entities.boards)
  ($->atom users     entitiesService.entities.users)
  ($->atom projects  entitiesService.entities.projects)
  ($->atom templates entitiesService.entities.templates)

  (. entitiesService fetch :langs)
  (. entitiesService fetch :boards)
  (. entitiesService fetch :users)
  (. entitiesService fetch :projects)
  (. entitiesService fetch :templates)

  ;; extracts board-id and project-id from URL
  ;; sets as scope's vars to share with child scopes
  (def$ boardId   (parseInt $routeParams.boardId))
  (def$ projectId (parseInt $routeParams.projectId))

  (def$ evalId (gen-access-key ($- boardId) ($- projectId)))

  ($->atom board    entitiesService.entities.boards
           (fn [boards]
             (get boards ($- boardId))))
  ($->atom project  entitiesService.entities.projects
           (fn [projects]
             (get projects ($- projectId))))
  ($->atom template entitiesService.entities.templates
           (fn [templates]
             (get templates (gen-access-key ($- templateId)
                                            ($- templateVersion)))))

  (defn filter-opinions [opinions]
    (find-entities* opinions
                    {:board-id    ($- boardId)
                     :project-id  ($- projectId)
                     :template-id ($- templateId)
                     :template-version ($- templateVersion)}))

  ($->atom opinions evaluation.opinions
           filter-opinions)

  ($->atom allOpinions evaluation.opinions)

  (defn update-template-id [projects]
    (get (or (get projects ($- projectId))
             {})
         :template-id))
  (defn update-template-version [projects]
    (get (or (get projects ($- projectId))
             {})
         :template-version))

  ($->atom templateId entitiesService.entities.projects
           update-template-id)
  ($->atom templateVersion entitiesService.entities.projects
           update-template-version)

  ;; (. entitiesService fetch "boards" ($- boardId))
  ;; (. entitiesService fetch "projects" ($- projectId))
  ;; (. entitiesService fetch "templates"
  ;;    (gen-access-key ($- templateId) ($- templateVersion)))

  (def$ session @session.current)

  ;; Stores points for each items in current evaluation
  (def points (atom {}))

  (def$ points @points)

  (defn$ fetchUser
    [id]
    ;; contact server
    ;; use ? as a placeholder
    "?"
    )

  )

(defcontroller emptyCtrl
  [$scope])

(defcontroller opinionsCtrl
  [$scope evaluation]
  (def points evaluation.points)
  (set! public $scope)

  (defn$ approveOpinion
    []
    (evaluation.approve-opinion ($- boardId) ($- projectId)
                                ($- item.id) ($- opinion.id)))

  (defn$ unapproveOpinion
    []
    (evaluation.unapprove-opinion ($- boardId) ($- projectId)
                                  ($- item.id) ($- opinion.id)))

  (defn$ removeOpinion [opinion-id]
    (evaluation.remove-opinion opinion-id))

  )

(defcontroller newOpinionCtrl
  [$scope evaluation]
  (def$ opinion {:type "comment"})
  (defn$ addOpinion
    [user-id item-id opinion]
    (when opinion.message
      (evaluation.add-opinion ($- boardId) ($- projectId)
                              ($- templateId) ($- templateVersion)
                              user-id item-id opinion)
      (def$ opinion {:type "comment"}))))
