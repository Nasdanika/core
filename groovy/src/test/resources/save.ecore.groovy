ePackage {

    name 'savemodel'
    nsURI 'urn:test-save'
    nsPrefix 'test.save'

    eClass {
        name 'Thing'
    }

}

// Self-writing script: on save, write the original source back with an appended DSL fragment. A real
// script would first do its side effects (persist to Jira/SQL) and could regenerate fragments for
// new/modified objects; here we simply append a comment to prove the source was provided and can be
// modified on write-back.
onSave { source, outputStream, options ->
    System.out.println("onSave: source length = ${source.length()}, options = ${options}")
    outputStream.write(source.getBytes('UTF-8'))
    outputStream.write('\n// appended on save\n'.getBytes('UTF-8'))
}
