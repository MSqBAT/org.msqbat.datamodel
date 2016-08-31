
package org.msqbat.datamodel.api;

import java.text.NumberFormat;

import org.msqbat.datamodel.api.provider.ProviderMz;

import com.google.common.collect.Range;

import net.sf.kerner.utils.collections.filter.Filter;

public class FilterProviderMz<T extends ProviderMz> implements Filter<T> {

	private final Range<Double> range;

	public FilterProviderMz(final double exact) {
		super();
		this.range = Range.singleton(exact);
	}

	public FilterProviderMz(final double low, final double high) {
		super();
		this.range = Range.closed(low, high);
	}

	public FilterProviderMz(final Range<Double> range) {
		super();
		this.range = range;
	}

	@Override
	public boolean filter(final ProviderMz e) {

		return range.contains(e.getMz());
	}

	@Override
	public String toString() {
		final Double f = range.lowerEndpoint();
		final Double l = range.upperEndpoint();
		NumberFormat.getInstance().format(f);
		return NumberFormat.getInstance().format(f) + "->" + NumberFormat.getInstance().format(l);
	}
}
