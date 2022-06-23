package org.nasdanika.common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public abstract class AbstractSplitJoinSet<S,C,E> extends AbstractSplitJoinCollection<S, C, E> implements Set<E> {
	
	@Override
	public boolean add(E e) {
		if (contains(e)) {
			return false;
		}
		return super.add(e);
	}
	
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Set)) {
            return false;
        }
        
        Set<?> s = (Set<?>) o;
        return s.size() == size() && containsAll(s);
    }

    public int hashCode() {
        int ret = 0;
        for (E e: this) {
        	if (e != null) {
        		ret ^= e.hashCode();
        	}
        }
        return ret;
    }

    public boolean removeAll(Collection<?> c) {
    	if (c == null) {
    		return false;
    	}
        boolean ret = false;
        if (size() < c.size()) {
        	Iterator<?> i = iterator();
            while (i.hasNext()) {
                if (c.contains(i.next())) {
                    i.remove();
                    ret = true;
                }
            }
        } else {
            for (Object e : c) {
                ret = remove(e) | ret;
            }
        }
        return ret;
    }	

}
