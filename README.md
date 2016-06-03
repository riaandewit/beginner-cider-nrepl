# beginner-cider-nrepl

Hopefully a quick and easy way for you to get Emacs talking to Clojure over nRepl

## Why?

Clojure is great. Emacs is great. When the two of them are talking, things get even better. Using nRepl (Network REPL) - you can be running your app from the command line, and talking to it from inside Emacs. You can then make changes to functions in source files, and see the effect immediately.

For me, the most valuable feature is to be able to "instrument" a function - so when it's called, I can pause execution, and make it step through the function, one line at a time. Nice way to learn what's going on :)

## Steps

### Install Emacs
### Install CIDER
### Install Clojure
### Install Leiningen
### Create a project
```
lein new app beginner-cider-nrepl
```
... should do the trick

### Convert to ring with nRepl
#### Change /project.clj to this:

```
(defproject beginner-cider-nrepl "0.1.0-SNAPSHOT"
  :description "quick intro on getting clojure talking to emacs through nrepl"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring "1.3.1"]
                 [org.clojure/tools.nrepl "0.2.12"]]

  :plugins [[lein-ring "0.9.6"]
            [cider/cider-nrepl "0.13.0-SNAPSHOT"]]
  :ring {:handler beginner-cider-nrepl.core/app
         :nrepl{:start? true}})
```
#### Change /src/beginner_cider_nrepl/core.clj to this:
```
(ns beginner-cider-nrepl.core)

(defn greet
  "a function called by app"
  [x]
  (concat "Hello there, " x))

(defn app [req]
  {:status 200
   :headers {"content-Type" "text/html"}
   :body (greet "Beginner :)")})

```

### Run the project
In a terminal, get into the root directory of the project, and do:
```
lein ring server
```

After a bit, this will give feedback similar to:
```
Started nREPL server on port 49941
2015-09-09 20:19:18.630:INFO:oejs.Server:jetty-7.6.13.v20130916
2015-09-09 20:19:18.720:INFO:oejs.AbstractConnector:Started SelectChannelConnector@0.0.0.0:3000
Started server on port 3000
```

### Visit http://localhost:3000
You should see "Hello there, Beginner :)" in the page. Great stuff, it's working.
### Connect to the nRepl
Open Emacs and do
```
M-x cider-connect
```
You'll be prompted for Host (localhost) and Port (the one indicated above) - and asked whether you'd like to associate with local code (choose yes, and point to the root directory of your app/project)

If all goes well, you'll end up with a new buffer, with something like this in it:

```
; CIDER 0.10.0snapshot (package: 20150908.444) (Java 1.8.0_31, Clojure 1.6.0, nREPL 0.2.7)
user>
```

Great stuff - you're talking to the REPL of the app, running happily there.

Open a buffer to /src/beginner_cider_nrepl/core.clj and switch to the namespace of the file (```C-c M-n```) - your prompt should change to

```
beginner-cider-nrepl.core>
```
in the nrepl buffer.

### Change some code
Edit the greet function to look like this:
```
(defn greet
  "a function called by app"
  [x]
  (concat "Hello there, " x " !!! "))

```
(just added some exclamation marks at the end there)
To get this into the app, I just go past the last closing bracket and hit ```C-x C-e``` - cider-eval-last-sexp.

Go back to your browser and refresh the page. Those exclamation marks you added to the end will now be visible in the browser...

Cool!

But it gets even better :)

### Instrument greet function
Put cursor anywhere in the body of the greet function, and do
```
C-u C-M-x
```
cider-eval-defn-at-point is run, and because you did C-u before it, it recognises that it must instrument the function. You'll know when this is successful, because the name of the function will have a border around it (red on my machine) that indicates something special is going on.

If you refresh the browser now, and then go back to the core.clj buffer, you'll see that there's a little arrow to the left of the "(concat..." - and the interface is showing you that something has a value.

Hitting 'n' a couple of times steps through the concatenation process, building the string, and eventually terminating the function.

(I normally hit it a few too many times and end up with a bunch of "n" characters in the function!)

Quite unspectacular at this stage, I know - but what you've just done is step through the function. For shits 'n giggles, you can instrument the "app" function too, and watch how the whole call unfolds.

Hope this helps :)
Ryan


Copyright © 2015 Ryan White

License: GPLv3 or later.
