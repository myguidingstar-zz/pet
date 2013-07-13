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
    {:ng-model "projectId"
     :ng-options "p.id as p.name for p in projects"}]]
  [:div
   [:h2 "Debug items:"]
   [:div "Template-id: {{templateId}}"]
   [:div "Project-id: {{projectId}}"]
   [:div "Board-id: {{boardId}}"]
   [:div "Eval-id: {{evalId}}"]]]

 [:div.span9.offset3
  [:div
   [:h2 "Debug items:"]
   [:div "Template-id: {{templateId}}"]
   [:div "Template-version: {{templateVersion}}"]
   [:div "Template: {{template}}"]
   [:div "Project: {{project}}"]
   [:div "Board: {{board}}"]
   [:div "Eval: {{eval}}"]]
  [:div
   [:h2 "Debug colls:"]
   [:div "Langs: {{langs}}"]
   [:div "Templates: {{templates}}"]
   [:div "Projects: {{projects}}"]
   [:div "Boards: {{boards}}"]
   [:div "Opinions: {{filterCurrent(allOpinions)}}"]
   [:div "All Opinions: {{allOpinions}}"]]
  [:div.media.span8 {:ng-repeat "group in template.groups"}
   [:div
    [:h1 "{{group.name}}"]
    [:div
     {:ng-repeat #=(str "item in template.items"
                        "|filterByKeyVal:'group-id':group.id")}
     [:div
      [:h1.pull-right "{{points[item.id]}}"]
      [:h2 " " [:i.icon-angle-right "  "]
       "{{item.name}} "
       [:i.icon-question-sign.icon-muted]]
      [:p
       ;; [:i.icon-info-sign.icon-large.text-info " "]
       "{{item.desc}}"]
      [:div
       {:ng-repeat #=(str "opinion in filterCurrent(allOpinions)"
                          "|filterByKeyVal:'item-id':item.id")
        :ng-controller "opinionsCtrl"}
       [:ng-include {:src "'partials/opinion.html'"}]]
      [:div.media
       {:style "padding: 14px; border-radius: 4px"
        ;;:ng-class "opinion.approved|approvedBox"
        :ng-controller "newOpinionCtrl"}
       [:div.pull-left {:style "width: 4em"}
        [:i
         {:class
          #=(str "icon-4x {{opinion.approved|approvedIcon}}"
                 " {{opinion.type|opinionType}}")}]]
       [:div.media-body ;; should have its own ctrl
        [:h4 "{{session.name}}"]
        [:select
         {:ng-model "opinion.type"
          :ng-options "n for n in ['checked', 'unchecked', 'comment']"}]
        [:textarea.span7 {:ng-model "opinion.message"}
         "{{opinion.message}}"]
        [:button.btn
         {:ng-click "addOpinion(session.id, item.id, opinion)"}
         "Add"]]]]]]]]]
