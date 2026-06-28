package org.nasdanika.groovy.dsl

import org.eclipse.emf.common.util.URI

/**
 * A DSL build/evaluation failure tagged with the source location it originated from: the resource
 * {@link URI} and, when recoverable from the Groovy runtime, the {@code line} (and {@code column},
 * where available) of the offending statement in the script.
 *
 * <p>The location is folded into {@link #getMessage()} in compiler style
 * ({@code <uri>:<line>:<column>: <message>}) so it shows up wherever the exception is logged or
 * wrapped (e.g. the {@code IOException} the resource handler reports). The raw {@link #uri},
 * {@link #line} and {@link #column} stay available for programmatic use; {@code line}/{@code column}
 * are {@code -1} when unknown. See {@link DslContext#located(Throwable)}.</p>
 */
class DslException extends RuntimeException {

    final URI uri
    final int line
    final int column

    DslException(String message, Throwable cause, URI uri, int line, int column) {
        super(location(uri, line, column) + message, cause)
        this.uri = uri
        this.line = line
        this.column = column
    }

    /** Compiler-style {@code uri:line:column: } prefix, or {@code ''} when nothing is known. */
    private static String location(URI uri, int line, int column) {
        StringBuilder sb = new StringBuilder()
        if (uri != null) {
            sb.append(uri.toString())
            if (line > 0) {
                sb.append(':').append(line)
                if (column > 0) {
                    sb.append(':').append(column)
                }
            }
        } else if (line > 0) {
            sb.append('line ').append(line)
            if (column > 0) {
                sb.append(', column ').append(column)
            }
        }
        sb.length() > 0 ? sb.append(': ').toString() : ''
    }

}
