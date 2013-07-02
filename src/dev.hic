[:html {:lang "en" :ng-app "myAppDev"}
 [:head
  [:meta  {:charset "utf-8"}]
  [:title "PET"]
  [:link {:rel "stylesheet" :href "css/bootstrap.min.css"}]
  [:link {:rel "stylesheet" :href "css/responsive.min.css"}]
  [:link
   {:href "components/angular-strap/vendor/bootstrap-datepicker.css"}]
  [:link
   {:href
    "http://fonts.googleapis.com/css?family=Arimo|Open+Sans+Condensed:300|Noto+Serif:700italic&subset=latin"
    :rel "stylesheet"}]
  [:script
   "document.write('<base href=\"' + document.location + '\" />')"]
  [:script {:src "components/jquery/jquery.min.js"}]
  [:script {:src "components/angular/angular.min.js"}]
  [:script {:src "components/angular/angular-mocks.js"}]
  [:script {:src "components/angular-bootstrap/ui-bootstrap-tpls.min.js"}]
  [:script {:src "components/angular-strap/dist/angular-strap.js"}]
  [:script {:src "components/angular-strap/vendor/bootstrap-datepicker.js"}]
  [:script {:src "core.js"}]
  [:script {:src "dev.js"}]]
 [:body
  [:div.navbar
   [:div.navbar-inner
    [:div.container
     [:a.brand {:href "/"} "My App Dev"]
     [:div#main-menu.nav-collapse.collapse
      [:ul.nav
       [:li [:a {:href "#/default"} "Default view"]]
       [:li [:a {:href "#/view-2"} "View 2"]]]]]]]
  [:div.container.row.span12
   [:div.span8.offset2
    [:ng-view]]]]]
