[:html {:lang "en" :ng-app "myApp"}
 [:head
  [:meta  {:charset "utf-8"}]
  [:title "My App"]
  [:link {:rel "stylesheet" :href "css/bootstrap.min.css"}]
  [:link {:rel "stylesheet" :href "css/responsive.min.css"}]
  [:link
   {:href
    "http://fonts.googleapis.com/css?family=Arimo|Open+Sans+Condensed:300|Noto+Serif:700italic&subset=latin"
    :rel "stylesheet"}]
  [:script
   "document.write('<base href=\"' + document.location + '\" />')"]
  [:script {:src "components/angular/angular.min.js"}]
  [:script {:src "core.js"}]]
 [:body
  [:div.navbar
   [:div.navbar-inner
    [:div.container
     [:a.brand {:href "/"} "My App"]
     [:div#main-menu.nav-collapse.collapse
      [:ul.nav
       [:li [:a {:href "#/default"} "Default view"]]
       [:li [:a {:href "#/view-2"} "View 2"]]]]]]]
  [:div.container.row.span12
   [:div.span8.offset2
    [:ng-view]]]]]
