(deffilter filterByKeyVal []
  [coll k v]
  (filter #(=== v (get % k))
          coll))

(deffilter approvedIcon []
  [approved?]
  (if approved?
    "text-success"
    "icon-muted"))

(deffilter approvedBox []
  [approved?]
  (if approved?
    "alert-info"
    ""))

(deffilter opinionType []
  [opinion-type]
  (case opinion-type
    "checked" "icon-check"
    "unchecked" "icon-check-empty"
    "icon-comment-alt"))
