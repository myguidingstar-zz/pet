;; All sample data (and Angular services data-structure) are tent
;; to be used with crud.cl2

(def sample-boards {0 {:id 0 :name "Guest"}
                    1 {:id 1 :name "GNU fans"}
                    2 {:id 2 :name "OSI fans"}})

(def sample-users
  {0 {:id 0 :username "guest"  :name "Guest"
      :groups [0] :boards [0 1]
      :lang "en"}
   1 {:id 1 :username "mr-foo" :name "Mr. Foo"
      :groups [1 2] :boards [1 2]
      :lang "vi"}
   2 {:id 2 :username "mr-bar" :name "Mr. Bar"
      :groups [1 2] :boards [1 2]
      :lang "en"}})

(def sample-opinions
  ;; all opinions are stored in an atom (and a DB table)
  ;; template-id and template-version are kept in opinions to support upgrade
  ;; templates
  {0 {:board-id 1 :project-id 0
      :template-id 0 :template-version "1.0"
      :id 0
      :user-id 1 :item-id 0
      :message "I agree because it is a very very long story"
      :type "checked" :approved true
      :approved-by 1  :approved-ts 1371412092436}
   1 {:board-id 1 :project-id 0
      :template-id 0 :template-version "1.0"
      :id 1
      :user-id 2 :item-id 0
      :message "I just think of it"
      :type "unchecked"}
   2 {:board-id 1 :project-id 0
      :template-id 0 :template-version "1.0"
      :id 2
      :user-id 1 :item-id 0
      :message "I just want to comment"
      :type "comment"}
   3 {:board-id 1 :project-id 0
      :template-id 0 :template-version "1.0"
      :id 3
      :user-id 1 :item-id 1
      :message "I agree because it is a very very long story"
      :type "checked" :approved true
      :approved-by 1  :approved-ts 1371412092436}
   4 {:board-id 1 :project-id 0
      :template-id 0 :template-version "1.0"
      :id 4
      :user-id 2 :item-id 1
      :message "I just think of it"
      :type "unchecked"}
   5 {:board-id 1 :project-id 0
      :template-id 0 :template-version "1.0"
      :id 5
      :user-id 1 :item-id 1
      :message "I just want to comment"
      :type "comment"}
   })

(def sample-templates
  ;; "template-id,version"
  {"0,1.0" { ;; id: id for each template-id+version
            ;; name: template name
            :id 0 :name "The Open source way"
            :template-id 0 :version "1.0"
            :groups [{:id 0 :name "License"}
                     {:id 1 :name "Size"}]
            :items  [{:id 0
                      :group-id 0
                      :name "Incompatible licenses"
                      :desc "Foo..."
                      :value 20}
                     {:id 1
                      :group-id 0
                      :name "No copy"
                      :desc "Foo..."
                      :value 50}
                     {:id 2
                      :group-id 1
                      :name "Big"
                      :desc "Foo..."
                      :value 5}
                     {:id 3
                      :group-id 1
                      :name "Big, compressed"
                      :desc "Foo..."
                      :value 5}]}
   "0,2.0" {:id 1 :name "The Open source way"
            :template-id 0 :version "2.0"
            :groups [{:id 0 :name "SCM"}
                     {:id 1 :name "Code"}]
            :items  [{:id 0
                      :group-id 0
                      :name "No SCM"
                      :desc "Foo..."
                      :value 20}
                     {:id 1
                      :group-id 0
                      :name "No web viewer"
                      :desc "Foo..."
                      :value 50}
                     {:id 2
                      :group-id 1
                      :name "Dos EOF"
                      :desc "Foo..."
                      :value 5}
                     {:id 3
                      :group-id 1
                      :name "Compiler dependent"
                      :desc "Foo..."
                      :value 5}]}})

(def sample-projects
  {0 {:id 0 :name "Sample project"
      :template-id "0,1.0"}
   1 {:id 0 :name "An other project"
      :template-id "0,2.0"}})

(def langs
  ;; First and second languages in user agent accepted langs
  ;; are automatically loaded. Others are loaded on-demand.
  {"en" {:id "en" :name "English"}
   "vi" {:id "vi" :name "Vietnamese"}})
