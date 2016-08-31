
package org.msqbat.datamodel.api.scan;

import java.util.Comparator;

public class ComparatorScanByRI implements Comparator<ScanMSqBAT> {

	@Override
	public int compare(final ScanMSqBAT o1, final ScanMSqBAT o2) {
		return Integer.compare(o1.getScanNumber(), o2.getScanNumber());
	}
}
