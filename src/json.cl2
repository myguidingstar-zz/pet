(defn serialize
  "Converts a message object to JSON strings so that it can be transfered
over the network."
  [msg]
  (. JSON (stringify msg)))

(defn deserialize
  "Converts a serialized message back to object"
  [data]
  ;;TODO: error handler
  (. JSON (parse data)))
