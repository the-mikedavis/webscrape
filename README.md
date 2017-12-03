# WEBSCRAPE

A very simple, concurrent web-scraping tool and framework for Clojure. Clojure
is one of the best choices for web-scraping because of its concurrency. You
can load multiple pages at once. Clojure
is a completely functional list language that works on the Java Virtual
Machine.

## Installation

1. Download lein
    - For macOS: `brew install lein`
2. Clone this repo
    - HTTP: `git clone https://github.com/the-mikedavis/webscrape.git`
    - SSH: `git clone git@github.com:the-mikedavis/webscrape.git`

## Usage

You can execute the jar to run webscrape.
Execution takes this form.

    $ java -jar webscrape-0.1.0-standalone.jar [args]

## Options

There are 3 different ways to run:

1. No arguments: loads the titles of [Hacker News](https://news.ycombinator.com)
2. One argument: the path of a `CSV` file which contains a list of websites
3. Two arguments: the url and the HTML tag and CSS selector for the element.

The CSV takes this form:

    url,selector

## Examples

An example csv is found in `visits.csv`. The no argmument execution uses these
values: `http://news.ycombinator.com` and `a.storylink`.

### Bugs

It's very easy to get nothing back from this tool. Make sure the element you
want to scrape is programmatically identifiable (i.e. the elements you want
get should be the only ones with their `<tag><selector><identifier>` form.
[e.g. `a.storylink`]).

## License

Copyright © 2017 Michael C. Davis

Distributed under the Eclipse Public License version 1.0.
