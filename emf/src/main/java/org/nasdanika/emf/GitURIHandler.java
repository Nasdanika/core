package org.nasdanika.emf;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

/**
 * Handles resources from a Git revision tree.
 * URI format: <code>&ltscheme&gt;://&lt;reference&gt;/&lt;path&gt;</code>.
 * For example: <code>git://maven-2025.7.0/pom.xml</code>
 */
public class GitURIHandler extends URIHandlerImpl {
			
	private static final String GIT_SCHEME = "git";
	protected Repository repository;
	protected String scheme;	
	
	public GitURIHandler(Repository repository, String scheme) {
		this.repository = repository;
		this.scheme = scheme;		
	}
		
	public GitURIHandler(File dir, String scheme) throws IOException {
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		repository = builder
				.findGitDir(dir)
				.readEnvironment()
				.build();
				
		this.scheme = scheme;		
	}	
	
	public GitURIHandler(Repository repository) {
		this(repository, GIT_SCHEME);
	}
	
	public GitURIHandler(File dir) throws IOException {
		this(dir, GIT_SCHEME);
	}	
		
	public GitURIHandler(String scheme) throws IOException {
		this(new File("").getCanonicalFile(), scheme);
	}	
	
	public GitURIHandler() throws IOException {
		this(GIT_SCHEME);
	}	
	
	@Override
	public boolean canHandle(URI uri) {
		return scheme.equals(uri.scheme());
	}
	
	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		String revStr = uri.authority();
		ObjectId commitId = repository.resolve(revStr);
		if (commitId == null) {
			throw new IllegalArgumentException("Invalid revision string: " + revStr);
		}
		
		try (RevWalk revWalk = new RevWalk(repository); TreeWalk treeWalk = new TreeWalk(repository)) {
			treeWalk.addTree(revWalk.parseCommit(commitId).getTree());
			treeWalk.setRecursive(true);
			String path = uri.path().substring(1);
			treeWalk.setFilter(PathFilter.create(path));
			if (treeWalk.next()) {
				ObjectId objId = treeWalk.getObjectId(0);
				byte[] content = repository.open(objId).getBytes();
				return new ByteArrayInputStream(content);
			}
			
			throw new IllegalArgumentException("File not found: " + path);
		}
		
	}
	
	@Override
	public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
		throw new UnsupportedOperationException("Git URI Handler is read-only");
	}	

}
