# Introduction to webscrape

Using the tool is pretty straightforward. Modifying the tool to suit your
needs is harder (but still easy). All the work is really done in the
`extract` function. If you need something from the `<head>` tag of the page,
you have to change the `url->dom` method to get non-DOM tags.
