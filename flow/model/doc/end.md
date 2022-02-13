End is a final [pseudo-state](PseudoState.html) with only inputs and no outputs indicating completion of a [flow](Flow.html).
It can be used in flows with ``explicit-end`` [modifier](PackageElement.html#EAttribute-modifiers) to explicitly specify the final state
in cases where it is not inferred correctly. It can also be used to specify several final states.