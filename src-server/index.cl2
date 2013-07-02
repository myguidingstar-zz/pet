(require ["express"]
         ["http"]
         ["sockjs"])

(load-file "../src/json.cl2")
(load-file "../node_modules/cl2-contrib/src/concurrency.cl2")
(load-file "../node_modules/cl2-contrib/src/timers.cl2")

(def socket
  (. sockjs
     (createServer
      {:websocket false
       :sockjs_url "http://cdn.sockjs.org/sockjs-0.3.min.js"})))

(. socket (on "connection" on-connection))

(def app (express))
(def server (. http (createServer app)))

(. socket (installHandlers server {:prefix "/socket"}))

(console.log " [*] Listening on 3000")
(. server (listen 3000))

(.
 app
 (configure
  #(doto app
     (.use (. express (static (+ __dirname "/target"))))
     (.use (:router app)))))

(. app
   (configure
    "development"
    #(. app
        (use
         (. express
            (errorHandler {:showStack true, :dumpExceptions true}))))))

(. app
   (configure
    "production"
    #(. app (use (. express errorHandler)))))
