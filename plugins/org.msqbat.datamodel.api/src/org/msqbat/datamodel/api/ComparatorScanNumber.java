
package org.msqbat.datamodel.api;

import java.io.Serializable;
import java.util.Comparator;

import org.msqbat.datamodel.api.provider.ProviderMzScanNumber;

public class ComparatorScanNumber<T extends ProviderMzScanNumber> implements Comparator<T>, Serializable {

	private static final long serialVersionUID = -6096982521055061331L;

	@Override
	public int compare(final T o1, final T o2) {

		return Integer.compare(o1.getScanNumber(), o2.getScanNumber());
	}

}
