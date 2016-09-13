
package org.msqbat.datamodel.api.ion;

import java.io.Serializable;

import org.msqbat.datamodel.api.peak.FeatureMSqBAT;
import org.msqbat.datamodel.api.provider.ProviderIntensity;
import org.msqbat.datamodel.api.provider.ProviderMzScanNumber;
import org.msqbat.datamodel.api.provider.ProviderRetentionTime;
import org.msqbat.datamodel.api.sample.SampleMSqBAT;
import org.msqbat.datamodel.api.scan.ScanMSqBAT;

import net.sf.kerner.utils.Cloneable;

public interface IonMSqBAT
		extends ProviderIntensity, ProviderMzScanNumber, ProviderRetentionTime, Cloneable<IonMSqBAT>, Serializable {

	IonMSqBAT cloneNewIntensity(double newIntensity);

	String getName();

	FeatureMSqBAT getPeak();

	SampleMSqBAT getSample();

	ScanMSqBAT getScan();

	IonMSqBAT setName(String name);

	/**
	 * This method is typically called by an instance of {@link FeatureMSqBAT}.
	 *
	 * @param peak
	 *            {@link FeatureMSqBAT} in which this ion is a member of
	 * @return {code this}
	 */
	IonMSqBAT setPeak(FeatureMSqBAT peak);

	IonMSqBAT setSample(SampleMSqBAT sample);

	IonMSqBAT setScan(ScanMSqBAT scan);

	IonMSqBAT setScanNumber(int scanNumber);

}
