package org.msqbat.datamodel.api.scan;

import java.io.Serializable;

import org.msqbat.datamodel.api.provider.ProviderRetentionTime;
import org.msqbat.datamodel.api.sample.SampleMSqBAT;

import net.sf.kerner.utils.Cloneable;

public interface ScanMSqBAT extends Cloneable<ScanMSqBAT>, ProviderRetentionTime, Serializable {

	String getName();

	SampleMSqBAT getSample();

	int getScanNumber();

	ScanMSqBAT setName(String name);

	ScanMSqBAT setSample(SampleMSqBAT sample);

	ScanMSqBAT setScanNumber(int scanNumber);
}
