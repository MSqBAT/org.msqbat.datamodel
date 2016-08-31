
package org.msqbat.datamodel.api;

import java.io.Serializable;
import java.util.Comparator;

import org.msqbat.datamodel.api.provider.ProviderMzScanNumber;

import net.sf.kerner.utils.comparator.ComparatorMulti;

public class ComparatorMzScanNumber<T extends ProviderMzScanNumber> implements Comparator<T>, Serializable {

	private static final long serialVersionUID = -3850255241182738545L;
	private final ComparatorMulti<T> delegate;

	@SuppressWarnings("unchecked")
	public ComparatorMzScanNumber() {
		this.delegate = new ComparatorMulti<T>(new ComparatorMz<T>(), new ComparatorScanNumber<T>());
	}

	@Override
	public int compare(final T o1, final T o2) {

		return delegate.compare(o1, o2);
	}
}
