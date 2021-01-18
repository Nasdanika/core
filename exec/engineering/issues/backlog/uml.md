Add support of UML diagrams with PlantUML:

* UML content/filter - takes PlantUML text and produces an image tag with encoded image content plus an image map if there are links. Reuse the logic from EMF documentation generator.
* Markdown uml fenced block - call the above code for generation of the image tag and image map. Process as a before advice (pre-processor) or an around advice. Pre-process - find uml fenced blocks. Generate image tag. Options:
    * Replace in the text and pass to markdown.
    * Replace the fenced block with some unique char sequence which would not be touched by the markdown processor. E.g. ``uml-diagram-<generated UUID>``. Then post-process by replacing the unique string with the image tag and a map tag.