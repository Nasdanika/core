package org.nasdanika.common;

import java.util.Objects;

public class SectionReference implements Comparable<SectionReference> {
	
	@Override
	public int hashCode() {
		return Objects.hash(id, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SectionReference other = (SectionReference) obj;
		return Objects.equals(id, other.id) && Objects.equals(title, other.title);
	}

	private String title;
	private String id;
	
	public SectionReference() {
		
	}
	
	public SectionReference(String title, String id) {
		this.title = title;
		this.id = id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	@Override
	public int compareTo(SectionReference o) {
		if (o == null) {
			return -1;
		}
		if (equals(o)) {
			return 0;
		}
		String ot = o.getTitle();
		if (Util.isBlank(ot)) {
			if (!Util.isBlank(getTitle())) {
				return -1;
			}
		}
		if (Util.isBlank(getTitle())) {
			return 1;
		}
		int tcmp = getTitle().compareTo(ot);
		if (tcmp != 0) {
			return tcmp;
		}
		
		String oid = o.getId();		
		if (Util.isBlank(oid)) {
			if (!Util.isBlank(getId())) {
				return -1;
			}
		}
		if (Util.isBlank(getId())) {
			return 1;
		}
		
		return getId().compareTo(oid);
	}

}
