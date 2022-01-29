package org.nasdanika.emf.persistence;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.ncore.GitMarker;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.NcoreFactory;

/**
 * Creates a git marker if location is in a git repository or a regular marker otherwise.
 * @author Pavel
 *
 */
public class GitMarkerFactory implements BiFunction<String, ProgressMonitor, org.nasdanika.ncore.Marker> {
	
	private Map<File, GitMarker> templates = new HashMap<>();

	@Override
	public Marker apply(String location, ProgressMonitor progressMonitor) {		
		try {
			if (!Util.isBlank(location) && location.startsWith("file:/")) {
				URI locationURI = new URL(location).toURI();
				Path path = Paths.get(locationURI);
				File file = path.toFile().getCanonicalFile();
				for (Entry<File, GitMarker> re: templates.entrySet()) {
					if (re.getValue() == null) {
						// We already know that there is no git repo for this location
						org.nasdanika.ncore.Marker marker = NcoreFactory.eINSTANCE.createMarker();
						marker.setLocation(location);
						return marker;
					}
					if (isAncestor(file, re.getKey())) {
						GitMarker marker = EcoreUtil.copy(re.getValue());
						URI repoWorkTreeURI = re.getKey().toURI();					
						marker.setPath(repoWorkTreeURI.relativize(locationURI).toString());
						marker.setLocation(location);
						return marker;					
					}
				}
				FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder().readEnvironment();
				repositoryBuilder.findGitDir(file);
				if (repositoryBuilder.getGitDir() == null) {
					templates.put(file.getParentFile(), null);
				}				
				try (Repository repository = repositoryBuilder.build()) {
					GitMarker template = NcoreFactory.eINSTANCE.createGitMarker();
					StoredConfig config = repository.getConfig();
					for (String remote: repository.getRemoteNames()) {
						template.getRemotes().put(remote, config.getString("remote", "origin", "url"));
					}
					template.setBranch(repository.getBranch());
					for (Ref ref: repository.getRefDatabase().getRefs()) {
						if (ref.getName().equals(Constants.HEAD)) {
							template.setHead(ref.getObjectId().getName());
							for (Ref refTwo: repository.getRefDatabase().getRefs()) {
								if (!refTwo.getName().equals(Constants.HEAD) && refTwo.getObjectId().equals(ref.getObjectId())) {
									template.getHeadRefs().add(refTwo.getName());
								}
							}							
						}
					}
					templates.put(repository.getWorkTree(), template);
					GitMarker marker = EcoreUtil.copy(template);
					URI repoWorkTreeURI = repository.getWorkTree().toURI();					
					marker.setPath(repoWorkTreeURI.relativize(locationURI).toString());
					marker.setLocation(location);
					return marker;					
				}
			}
		} catch (Exception e) {
			progressMonitor.worked(Status.ERROR, 1, "Error creating a file from location '" + location +"': " + e, e);
		}
		org.nasdanika.ncore.Marker marker = NcoreFactory.eINSTANCE.createMarker();
		marker.setLocation(location);
		return marker;	
	}
	
	private boolean isAncestor(File file, File ancestor) {
		File parent = file.getParentFile();
		if (parent == null) {
			return false;
		}
		if (parent.equals(ancestor)) {
			return true;
		}
		return isAncestor(parent, ancestor);
	}

}
