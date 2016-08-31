
package org.msqbat.datamodel.api;

import org.msqbat.datamodel.api.provider.ProviderScanNumber;

import com.google.common.collect.Range;

import net.sf.kerner.utils.collections.filter.Filter;

public class FilterProviderScanNumber<T extends ProviderScanNumber> implements Filter<T> {

	private final Range<Integer> rangeScanNumber;

	public FilterProviderScanNumber(final int exact) {
		super();
		this.rangeScanNumber = Range.singleton(exact);
	}

	public FilterProviderScanNumber(final int low, final int high) {
		super();
		this.rangeScanNumber = Range.closed(low, high);
	}

	public FilterProviderScanNumber(final Range<Integer> range) {
		super();
		this.rangeScanNumber = range;
	}

	@Override
	public boolean filter(final ProviderScanNumber e) {

		return rangeScanNumber.contains(e.getScanNumber());
	}

}
