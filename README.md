
# Bounder

A repository testing some ideas for automatically generating API documentation
for Clojure and ClojureScript projects.  You can browse the site here:

http://rodnaph.github.io/bounder/

## The Problem

At the moment (imho) I see two problems using Clojure libraries.

1. It's quite difficult to find libraries to use (ie. I need a HTTP library...  or I need to access the filesystem...)
2. When you do find one of those libraries, the author usually hasn't had time to create good documentation.

## The Solution

So to try and help with this, I'm trying out some ideas for a super-each way to create
a useful index of projects, and then to be able to browse their documentation.  Bounder
has been designed to run as a completely static site, so all documentation and assets
are built and committed to Git.

## Isn't this just like site X?

I know of some other sites that aggregate libraries (http://clojurewerkz.org/, or http://clojure-libraries.appspot.com/)
but they don't provide any documentation.  I'd love to contribute to an existing
site to add this kind of thing, so I'll reiterate this is an experiment.  If it
is useful it could be folded in to an existing solution.

# Disclaimer

This might be rubbish, and at the moment only supports Clojure projects.  I
hope to make it support ClojureScript too soon.

# Usage

To use bounder just clone the repository and run:

```
lein run
```

This will create any missing documentation, and re-generate the static assets.

# Contributing

All the projects are listed in _src/bounder/data.clj_.  Just add your project to that
list and open a PR.

