
package org.msqbat.datamodel.api.sample;

import java.util.Collection;

import org.msqbat.datamodel.api.ion.IonMSqBAT;
import org.msqbat.datamodel.api.provider.ProviderIonsTree;
import org.msqbat.datamodel.api.scan.ScanMSqBAT;

public interface SampleIons extends SampleMSqBAT, ProviderIonsTree<IonMSqBAT> {

	SampleIons addScans(Collection<? extends ScanMSqBAT> scans);

	SampleIons addScans(ScanMSqBAT... scans);

	SampleIons cloneWithoutIons();

	Collection<ScanMSqBAT> getScans();
}
