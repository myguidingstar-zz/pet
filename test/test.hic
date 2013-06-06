[:html {:lang "en"}
 [:head
  [:meta  {:charset "utf-8"}]
  [:title "My App tests"]
  [:link {:rel "stylesheet" :href "/testem/qunit.css"}]
  [:script {:src "/testem/qunit.js"}]
  [:script {:src "/testem.js"}]
  [:script {:src "/target/components/angular/angular.min.js"}]
  [:script {:src "/test/test_runners.js"}]]
 [:body {:ng-app "myApp"}
  [:div#qunit-userAgent]
  [:ol#qunit-tests]
  [:div#qunit-fixture]]]
