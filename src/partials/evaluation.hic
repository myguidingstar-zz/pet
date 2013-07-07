[:div.row
 [:div.span3.affix
  [:div.row
   [:h2.pull-right "{{total}}"]
   [:h2 "Result"]
   [:h2.pull-right "{{completion}}/{{totalItems}}"]
   [:h2 "Completion"]
   [:div.progress
    [:div.bar {:style "width:{{completion/totalItems*100}}%"}]]]
  [:div.row
   [:select
    {:ng-model "lang" :ng-options "l.id as l.name for l in langs"}]]
  [:div.row
   [:select
    {:ng-model "board" :ng-options "b.id as b.name for b in boards"}]]
  [:div.row
   [:select
    {:ng-model "projectId" :ng-options "p.id as p.name for p in projects"}]]
  [:div
   [:h2 "Debug:"]
   [:div "Lang: {{lang}}"]
   [:div "Project: {{projectId}}"]
   [:div "Board: {{board}}"]]]

 [:div.span9.offset3
  [:div.media.span8 {:ng-repeat "group in template.groups"}
   [:div
    [:h1 "{{group.name}}"]
    [:div
     {:ng-repeat "item in template.items|filterByKeyVal:'group-id':group.id"}
     [:div
      [:h1.pull-right "{{points[item.id]}}"]
      [:h2 " " [:i.icon-angle-right "  "]
       "{{item.name}} "
       [:i.icon-question-sign.icon-muted]]
      [:p
       ;; [:i.icon-info-sign.icon-large.text-info " "]
       "{{item.desc}}"]
      [:div
       {:ng-repeat "opinion in opinions|filterByKeyVal:'item-id':item.id"
        :ng-controller "opinionsCtrl"}
       [:div.media
        {:style "padding: 14px; border-radius: 4px"
         :ng-class "opinion.approved|approvedBox"}
        [:div.pull-right
         [:i.icon-large.icon-ok-circle.text-success
          {:ng-click "approveOpinion()"
           :ng-show "!opinion.approved&&opinion.type!=='comment'"}
          " "]
         [:i.icon-large.icon-ban-circle.text-warning
          {:ng-click "unapproveOpinion()"
           :ng-show "opinion.approved"} " "]
         [:i.icon-large.icon-remove-circle.text-error
          {:ng-click "removeOpinion(opinion.id)"}
          " "]]
        [:div.pull-left {:style "width: 4em"}
         [:i
          {:class
           "icon-4x {{opinion.approved|approvedIcon}} {{opinion.type|opinionType}}"}]]
        [:div.media-body
         [:div.pull-right {:ng-show "opinion.approved"}
          [:h4 "Approved by {{users[opinion['approved-by']].name||fetchUser(opinion['approved-by'])}}"]
          [:h5 "{{opinion['approved-ts'] | date:'MM/dd/yyyy @ h:mma'}}"]]
         [:h4 "{{users[opinion['user-id']].name||fetchUser(opinion['user-id'])}}"]
         [:p
          "{{opinion.message}}"]]]]
      [:div.media
       {:style "padding: 14px; border-radius: 4px"
        ;;:ng-class "opinion.approved|approvedBox"
        :ng-controller "newOpinionCtrl"}
       [:div.pull-left {:style "width: 4em"}
        [:i
         {:class
          "icon-4x {{opinion.approved|approvedIcon}} {{opinion.type|opinionType}}"}]]
       [:div.media-body ;; should have its own ctrl
        [:h4 "{{session.name}}"]
        [:select
         {:ng-model "opinion.type"
          :ng-options "n for n in ['checked', 'unchecked', 'comment']"}]
        [:textarea.span7 {:ng-model "opinion.message"}
         "{{opinion.message}}"]
        [:button.btn {:ng-click "addOpinion(session.id, item.id, opinion)"} "Add"]]]]]]]]]
