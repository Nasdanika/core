package org.nasdanika.exec.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimpleImportManager implements ImportManager {
	
	// Short name -> Fully Qualified Name.
	private Map<String, String> imports = new HashMap<>();
	
	// Fully qualified names.
	private Set<String> implicitImports = new HashSet<>();
	
	public SimpleImportManager(Collection<String> implicitImports) {
		if (implicitImports != null) {
			this.implicitImports.addAll(implicitImports);
			for (String ii: this.implicitImports) {
				addImport(ii);
			}
		}
	}

	@Override
	public String addImport(String fullyQualifiedTypeName) {
		int lastDotIdx = fullyQualifiedTypeName.lastIndexOf('.');
		if (lastDotIdx == -1) {
			return fullyQualifiedTypeName;
		}
		int ltIdx = fullyQualifiedTypeName.indexOf('<', lastDotIdx);
		String shortName = fullyQualifiedTypeName.substring(lastDotIdx + 1, ltIdx == -1 ? fullyQualifiedTypeName.length() : ltIdx);		
		String pShortName = fullyQualifiedTypeName.substring(lastDotIdx + 1, fullyQualifiedTypeName.length());		
		String efqn = imports.get(shortName);
		if (efqn == null) {
			imports.put(shortName, fullyQualifiedTypeName);
			if ("java.lang".equals(fullyQualifiedTypeName.substring(0, lastDotIdx))) {
				implicitImports.add(fullyQualifiedTypeName);
			}
			return pShortName;
		}
		return efqn.equals(fullyQualifiedTypeName) ? pShortName : fullyQualifiedTypeName;
	}

	@Override
	public Collection<String> getImports() {
		List<String> ret = new ArrayList<>();
		for (String fqn: imports.values()) {
			if (!isImplicitImport(fqn)) {
				ret.add(fqn);
			}
		}
		Collections.sort(ret);
		return ret;
	}
	
	protected boolean isImplicitImport(String fullyQualifiedTypeName) {
		return implicitImports.contains(fullyQualifiedTypeName);
	}

}
