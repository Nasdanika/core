Specialization of Container which uses Java naming, e.g. ``org.nasdanika``. 
Package Java names are converted to file system names during generation, e.g. ``org/nasdanika``.

Configured with the following keys:

* ``contents`` - a content component, a string, or a list of content components.
* ``merger`` - ${javadoc/org.nasdanika.common.resources.Merger} implementation if ``reconcile-action`` is ``MERGE``. Optional there is a native merger.
* ``name`` - package name with dot separated segments, interpolated.
* ``reconcile-action`` - defines an action to take if a file with given name already exists. Default action is ``OVERWRITE``:
    * ``KEEP`` - Keep the existing file, discard generated contents.
    * ``APPEND`` - Append generated contents to the existing contents.
    * ``MERGE`` - Merge old and new contents. 
    * ``OVERWRITE`` - Replace the existing contents with the generated contents.
    * ``CANCEL`` -  Cancel generation if a file already exists.
 