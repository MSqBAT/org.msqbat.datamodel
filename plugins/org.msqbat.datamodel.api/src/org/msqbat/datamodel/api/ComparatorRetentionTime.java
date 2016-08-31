
package org.msqbat.datamodel.api;

import java.util.Comparator;

import org.msqbat.datamodel.api.provider.ProviderRetentionTime;

public class ComparatorRetentionTime implements Comparator<ProviderRetentionTime> {
	
	final static int DEFAULT_RT_DIMENSION = 0;
	
	private int rtDimension = DEFAULT_RT_DIMENSION;

	
	public int getRTDimension() {
	
		return rtDimension;
	}

	
	public void setRTDimension(int rtDimension) {
	
		this.rtDimension = rtDimension;
	}

	@Override
	public int compare(ProviderRetentionTime o1, ProviderRetentionTime o2) {

		return Double.compare(o1.getRetentionTimes()[getRTDimension()], o2.getRetentionTimes()[getRTDimension()]);
	}
}
