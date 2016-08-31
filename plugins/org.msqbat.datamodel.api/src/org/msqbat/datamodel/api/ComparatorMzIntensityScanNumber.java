
package org.msqbat.datamodel.api;

import java.io.Serializable;
import java.util.Comparator;

import org.msqbat.datamodel.api.ion.IonMSqBAT;

import net.sf.kerner.utils.comparator.ComparatorMulti;

public class ComparatorMzIntensityScanNumber<T extends IonMSqBAT> implements Comparator<T>, Serializable {

	private static final long serialVersionUID = 2757286903097923955L;
	private final ComparatorMulti<T> delegate;

	@SuppressWarnings("unchecked")
	public ComparatorMzIntensityScanNumber() {
		this.delegate = new ComparatorMulti<T>(new ComparatorMz<T>(), new ComparatorIntensity<T>(),
				new ComparatorScanNumber<T>());
	}

	@Override
	public int compare(final T o1, final T o2) {

		return delegate.compare(o1, o2);
	}
}
