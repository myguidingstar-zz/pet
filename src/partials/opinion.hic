[:div.media
 {:style "padding: 14px; border-radius: 4px"
  :ng-class "opinion.approved|approvedBox"}
 [:div.pull-right
  [:i.icon-large.icon-ok-circle.text-success
   {:ng-click "approveOpinion()"
    :ng-show
    #=(str "!opinion.approved &&"
           "opinion.type !== 'comment'")}
   " "]
  [:i.icon-large.icon-ban-circle.text-warning
   {:ng-click "unapproveOpinion()"
    :ng-show "opinion.approved"} " "]
  [:i.icon-large.icon-remove-circle.text-error
   {:ng-click "removeOpinion()"}
   " "]]
 [:div.pull-left {:style "width: 4em"}
  [:i
   {:class
    #=(str "icon-4x {{opinion.approved|approvedIcon}}"
           " {{opinion.type|opinionType}}")}]]
 [:div.media-body
  [:div.pull-right {:ng-show "opinion.approved"}
   [:h4
    #=(str "Approved by "
           "{{users[opinion['approved-by']].name"
           "||fetchUser(opinion['approved-by'])}}")]
   [:h5 #=(str "{{opinion['approved-ts']"
               "|date:'MM/dd/yyyy @ h:mma'}}")]]
  [:h4 #=(str "{{users[opinion['user-id']].name"
              "||fetchUser(opinion['user-id'])}}")]
  [:p
   "{{opinion.message}}"]]]
