Git component executes resource components in the context of a Git repository.
Before executing resource components it can pull or clone repository from a remote origin.
It can also checkout a branch create it if doesn't exist off the current HEAD.
 
After executing the resource components this component commits changes to the repository.
It can tag the commit and optionally force and force push.
If repository was cloned or pulled then upon execution of resource components the commit is pushed to the origin.
 
If working directory was not specified and origin was specified then execution is performed in a temporary directory.
 
### Usage scenarios
 
* Generation of a new code and push to a remote repository for subsequent build and deploy. 
* Automated update of previously generated code - pull, generate, merge/patch, push. This scenario has two sub-scenarios: 
    * Generators or generator specifications have changes - e.g. new functionality. 
    * nput has changed - e.g. change in a choice of a database or a messaging system or adding/removing solution components.

### Configuration

All configuration values are interpolated with input.

* ``add-pattern`` - Add file pattern(s). String or list. Defaults to ``.``.
* ``author`` - Commit author. 
    * ``name`` - Author name.
    * ``e-mail`` - Author e-mail.
* ``branch`` - Branch to check out. String or map. If a branch does not exist it will be created unless there is no current ``HEAD``, e.g. in the case of an empty repository. If a branch cannot be created a warning is issued and generated content is committed and pushed to the repository without branch creation. I.e. for an empty repository the content will be committed and pushed to the default branch, e.g. ``master``.
    * If string - branch name. If remote branch does not exist a new one is created off the current ``HEAD``.
    * If map - branch specification. Supports two keys:
        * ``name`` - branch name, required.
        * ``start-point`` - branch start point, optional. If not provided then it is the same as string branch specification and a new branch will be started off the current ``HEAD``. If provided, the interpolated value will be used as branch start point. Start point is ignored for existing branches.
* ``clean`` - If true the repo directory is cleaned up before any other operation, i.e. it is always clone.
* ``commit-message`` - Commit message.
* ``config`` - [Git configuration](https://git-scm.com/docs/git-config) 3-level map - section, sub-section, name. See example below.
* ``contents`` - Resource components creating/modifying repository contents.
* ``credentials`` - Credentials for clone/pull/push.
    * ``user`` - User name.
    * ``password`` - Password.
* ``force-tag`` - If true the tag is forced and force pushed.
* ``on-push`` - Optional content component or a list of content components to be executed after push and tag push. For example, a custom content component may trigger a build or some other action on the pushed code.
On-push behavior can also be introduced via creating a custom Git component by sub-classing ${javadoc/org.nasdanika.exec.resources.Git} and overriding ``onPush()`` method.
* ``origin`` - Origin to pull from if repoDir is null or does not exist. If origin is not null then a push is performed after commit.
* ``repository`` - Git repository working directory. If null then a temporary directory is created. In this case origin must be provided for cloning.
* ``tag`` - Optional tag for the commit.

#### Config example

```yaml
   config:
      http:
         "https://github.com": 
            sslVerify: false            
```

### Example

#### YAML specification

Java specification modified to push generated sources to a Git repository.

```yaml
git:
   origin: https://github.com/Nasdanika/git-supplier-test.git
   branch: feature/test-component
   credentials:
      user: ${user}
      password: ${auth-token}
   commit-message: Test
   author:
     name: ${author/name}
     e-mail: ${author/e-mail}
   contents:
      - source-folder:
         name: src
         contents:
            - package:
               name: org.nasdanika.test
               contents:
                  - compilation-unit:
                     name: MyClass
                     contents:
                        interpolator:
                           resource: java-template.txt
                  - compilation-unit:
                     name: MyOtherClass
                     imports:
                        - org.nasdanika.common.ConsumerFactory
                     contents:
                        interpolator:
                           resource: java-template.txt
                  - class:
                     name: TopLevelClass
                     imports:
                        - org.nasdanika.common.SupplierFactory
                        - java.io.InputStream
                     modifiers: public
                     super-types:
                        - java.lang.Object
                        - SupplierFactory<InputStream>                           
                  - compilation-unit:
                     name: FineGrainedClass
                     contents:
                        - class:
                           name: FineGrainedClass
                           modifiers: public
                           super-types:
                              - java.lang.Object
                              - '${import/org.nasdanika.common.SupplierFactory}<${import/java.io.InputStream}>'
                           body: 
                              - field:
                                 name: myImportantField
                                 modifiers:
                                    - private
                                 comment: My rather important field
                                 type: '${import/java.util.Collection}<${import/java.lang.String}>'
                                 body: 'new ${import/java.util.ArrayList}<>()'
                                 annotations: ${myFieldAnnotations}
                              - field:
                                 name: anotherField
                                 modifiers:
                                    - private
                                 comment: Field with imports
                                 type: ConsumerFactory<BinaryEntityContainer>
                                 annotations: ${myFieldAnnotations}
                                 imports:
                                    - org.nasdanika.common.ConsumerFactory
                                    - org.nasdanika.common.resources.BinaryEntityContainer
```

##### Branch specification

Below an example of a map branch specification:

```yaml
git:
   branch: 
      name: feature/off-develop
      start-point: origin/develop
```

#### Java code

In the below snippet the context is constructed from environment variables starting with ``test-git-supplier-`` and then composed with a singleton context providing Java annotations.
``callCommand()`` is a helper method which executes the command lifecycle - diagnose, execute, commit/rollback, close.

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object git = loader.loadYaml(TestExec.class.getResource("git-spec.yml"), monitor);

Collection<String> annotations = new ArrayList<>();
annotations.add("Override");
annotations.add("${import/org.nasdanika.TestAnnotation}");		
Context context = Context.wrap(System.getenv()::get).map(key -> "test-git-supplier-" + key).compose(Context.singleton("myFieldAnnotations", annotations));
callCommand(context, monitor, git);
```
    