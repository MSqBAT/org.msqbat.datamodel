
package org.msqbat.datamodel.api;

import org.msqbat.datamodel.api.provider.ProviderIntensity;

import com.google.common.collect.Range;

import net.sf.kerner.utils.collections.filter.Filter;

public class FilterProviderIntensity<T extends ProviderIntensity> implements Filter<T> {

	private final Range<Double> range;

	public FilterProviderIntensity(Range<Double> range) {
		super();
		this.range = range;
	}

	public FilterProviderIntensity(double low, double high) {
		super();
		this.range = Range.closed(low, high);
	}

	public FilterProviderIntensity(double exact) {
		super();
		this.range = Range.singleton(exact);
	}

	@Override
	public boolean filter(ProviderIntensity e) {

		return range.contains(e.getIntensity());
	}
}
