package org.nasdanika.echarts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import org.icepear.echarts.Line;
import org.icepear.echarts.charts.line.LineSeries;

public class LineSeriesChart<E,X,Y> extends AbstractSericesChart<E, X, Y, LineSeries, Line> {
	
	protected Comparator<X> xComparator;
	protected Function<X, String> xFormatter;
	
	public LineSeriesChart(Comparator<X> xComparator, Function<X,String> xFormatter) {
		this.xComparator = xComparator;
		this.xFormatter = xFormatter;
	}
	
	public interface LineSeriesBuilder<E> {
		
		LineSeries lineSeries();
		
		void addElement(E element);
		
	}
	
	protected record LineSeriesRecord<E,X,Y>(
			LineSeries lineSeries, 
			Collection<E> data,
			Function<E, X> xMapper,
			Function<E, Y> yMapper) implements LineSeriesBuilder<E> {

		@Override
		public void addElement(E element) {
			data().add(element);
		}
				
		public Stream<X> xStream() {
			return data.stream().map(xMapper);
		}
		
		public Y getY(X x) {
			return data()
				.stream()
				.filter(e -> Objects.equals(xMapper.apply(e), x))
				.findFirst()
				.map(yMapper)
				.orElse(null);
		}
		
	}
	
	protected List<LineSeriesRecord<E,X,Y>> series = new ArrayList<>();

	@Override
	public LineSeries addSeries(
			String name, 
			Collection<E> data, 
			Function<E, X> xMapper,
			Function<E, Y> yMapper) {
		
		LineSeries lineSeries = new LineSeries();
		lineSeries.setName(name);
		series.add(new LineSeriesRecord<>(lineSeries, data, xMapper, yMapper));		
		return lineSeries;
	}
	
	public LineSeriesBuilder<E> createSeries(
			String name, 
			Function<E, X> xMapper,
			Function<E, Y> yMapper) {
		
		LineSeries lineSeries = new LineSeries();
		lineSeries.setName(name);
		LineSeriesRecord<E, X, Y> lineSeriesRecord = new LineSeriesRecord<>(
				lineSeries, 
				new ArrayList<>(), 
				xMapper, 
				yMapper);
		series.add(lineSeriesRecord);		

		return lineSeriesRecord;
	}
	
	@Override
	protected Line generateChart() {
		Object[] xValues = series
				.stream()
				.flatMap(LineSeriesRecord::xStream)
				.distinct()
				.sorted(xComparator)
				.map(xFormatter)
				.toArray();
		
		@SuppressWarnings("unchecked")
		String[] xAxis = Stream.of(xValues)
				.map(x -> xFormatter.apply((X) x))
				.toArray(String[]::new);
		
		Line line = new Line()
			.addXAxis(xAxis)
			.addYAxis()
			.setTooltip("axis");							
		
		for (LineSeriesRecord<E, X, Y> se: series) {
			@SuppressWarnings("unchecked")
			Object[] values = Stream.of(xValues)
					.map(x -> se.getY((X) x))
					.toArray();
			se.lineSeries().setData(values);
			line.addSeries(se.lineSeries());			
		}
		line.setLegend();
		return line;
	}

}
