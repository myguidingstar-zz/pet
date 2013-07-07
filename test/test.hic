[:html {:lang "en" :ng-app "myAppDev"}
 [:head
  [:meta  {:charset "utf-8"}]
  [:title "My App tests"]
  [:link {:rel "stylesheet" :href "/testem/qunit.css"}]
  [:script {:src "/testem/qunit.js"}]
  [:script {:src "/testem.js"}]
  [:script
   "document.write('<base href=\"' + document.location + '\" />')"]
  [:script {:src "/target/components/jquery/jquery.min.js"}]
  [:script {:src "/target/components/angular/angular.min.js"}]
  [:script {:src "/target/components/angular/angular-mocks.js"}]
  [:script {:src "/target/components/angular-bootstrap/ui-bootstrap-tpls.min.js"}]
  [:script {:src "/target/components/angular-strap/dist/angular-strap.js"}]
  [:script {:src "/target/components/angular-strap/vendor/bootstrap-datepicker.js"}]
  [:script {:src "/test/test_runners.js"}]]
 [:body
  [:div#qunit-userAgent]
  [:ol#qunit-tests]
  [:div#qunit-fixture]]]
