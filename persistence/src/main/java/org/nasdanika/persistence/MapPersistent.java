package org.nasdanika.persistence;

import java.util.Map;

/**
 * Something which can store and load its state to/from Map<String,Object>.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface MapPersistent<T> extends Persistent<T, String, Map<String, Object>> {

}
