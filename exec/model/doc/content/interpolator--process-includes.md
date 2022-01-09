If ``true`` (default) processes:

* ``${{{embedded-image/<image type>/<image url>}}}`` - reads image from the URL resolved relative to the base URL. Encodes as an embedded image. Example: ``${{{embedded-image/png/logo.png}}}``.
* ``${{{include/<resource url>}}}`` - reads, interpolates, and includes resource at the resource URL resolved relative to the base URL. Example: ``${{{include/report.html}}}``.
* ``${{{include-markdown/<resource url>}}}`` - reads a resource at the resource URL resolved relative to the base URL. Renders Markdown to HTML. Interpolates and includes. Example: ``${{{include-markdown/report.md}}}``.
* ``${{{include-styled-markdown/<resource url>}}}`` - reads a resource at the resource URL resolved relative to the base URL. Renders Markdown to HTML. Interpolates, wraps into a DIV with ``markdown-body`` class, and includes. Example: ``${{{include-styled-markdown/report.md}}}``.
