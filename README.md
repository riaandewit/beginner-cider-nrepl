# beginner-cider-nrepl

Hopefully a quick and easy way for you to get Emacs talking to Clojure over nRepl

## Why?

Clojure is great. Emacs is great. When the two of them are talking, things get even better. Using nRepl (Network REPL) - you can be running your app from the command line, and talking to it from inside Emacs. You can then make changes to functions in source files, and see the effect immediately.

For me, the most valuable feature is to be able to "instrument" a function - so when it's called, I can pause execution, and make it step through the function, step by step. Nice way to learn what's going on :)

## Steps

### Install Emacs
### Install Clojure
### Install Leiningen
### Create a project
```
lein new app beginner-cider-nrepl
```
... should do the trick


Copyright © 2015 Ryan White

License: GPLv3 or later.
