package org.nasdanika.echarts;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.function.Function;

/**
 * Chart abstraction
 * @param <E> Data element type
 * @param <X> X axis type - String, Number, Date.
 * @param <Y> Y axis type
 * @param <S>
 */
public interface SeriesChart<E,X,Y,S> {
	
	/**
	 * Adds a series
	 * @param name
	 * @param data
	 * @param xMapper
	 * @param yMapper
	 * @return Series object for customization
	 */
	S addSeries(String name, Collection<E> data, Function<E,X> xMapper, Function<E,Y> yMapper);
	
	/**
	 * Writes a file
	 * @param output
	 * @param title
	 * @param open If true, opens the file using the default browser for the file URI.
	 * @throws IOException
	 */
	void write(File output, String title, boolean open) throws IOException;

}
