
package org.msqbat.datamodel.impl.ion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.msqbat.datamodel.api.annotation.AnnotationSerializable;
import org.msqbat.datamodel.api.annotation.IonAnnotatable;
import org.msqbat.datamodel.api.ion.IonMSqBAT;
import org.msqbat.datamodel.api.peak.PeakMSqBAT;
import org.msqbat.datamodel.api.sample.SampleMSqBAT;
import org.msqbat.datamodel.api.scan.ScanMSqBAT;

import net.sf.kerner.utils.collections.ClonerImpl;

public class IonImpl implements IonAnnotatable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6094069110297241142L;
	private Collection<AnnotationSerializable> annotation = new ArrayList<>();
	private final double intensity;
	private final double mz;
	private String name;
	private PeakMSqBAT peak;
	private double[] retentionTimes;
	private ScanMSqBAT scan;

	private int scanNumber = -1;

	public IonImpl(final double mz, final double intensity) {
		super();
		this.mz = mz;
		this.intensity = intensity;
	}

	public IonImpl(final double mz, final double intensity, final int scanNumber) {
		super();
		this.mz = mz;
		this.intensity = intensity;
		this.scanNumber = scanNumber;
	}

	public IonImpl(final IonMSqBAT template) {
		this(template, template.getIntensity());
	}

	public IonImpl(final IonMSqBAT template, final double newIntensity) {
		this(template.getMz(), newIntensity, template.getScanNumber());
		if (template.getRetentionTimes() != null) {
			setRetentionTimes(Arrays.copyOf(template.getRetentionTimes(), template.getRetentionTimes().length));
		}
		setName(template.getName());
		if (template instanceof IonAnnotatable) {
			setAnnotation(new ClonerImpl<AnnotationSerializable>()
					.cloneList(new ArrayList<AnnotationSerializable>(((IonAnnotatable) template).getAnnotation())));
		}
	}

	/**
	 * Creates a deep copy of this {@code IonImpl}. {@code scan} and
	 * {@code peak} will be {@code null}.
	 */
	@Override
	public synchronized IonImpl clone() {

		return new IonImpl(this);
	}

	@Override
	public synchronized IonImpl cloneNewIntensity(final double newIntensity) {
		return new IonImpl(this, newIntensity);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof IonImpl)) {
			return false;
		}
		final IonImpl other = (IonImpl) obj;
		if (Double.doubleToLongBits(intensity) != Double.doubleToLongBits(other.intensity)) {
			return false;
		}
		if (Double.doubleToLongBits(mz) != Double.doubleToLongBits(other.mz)) {
			return false;
		}
		if (!Arrays.equals(retentionTimes, other.retentionTimes)) {
			return false;
		}
		if (scanNumber != other.scanNumber) {
			return false;
		}
		return true;
	}

	@Override
	public synchronized Collection<AnnotationSerializable> getAnnotation() {
		return annotation;
	}

	@Override
	public synchronized double getIntensity() {

		return intensity;
	}

	@Override
	public synchronized double getMz() {

		return mz;
	}

	@Override
	public synchronized String getName() {

		return name;
	}

	@Override
	public synchronized PeakMSqBAT getPeak() {

		return peak;
	}

	@Override
	public synchronized double[] getRetentionTimes() {

		return retentionTimes;
	}

	@Override
	public synchronized SampleMSqBAT getSample() {

		return getScan().getSample();
	}

	@Override
	public synchronized ScanMSqBAT getScan() {
		return scan;
	}

	@Override
	public synchronized int getScanNumber() {

		return scanNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(intensity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mz);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Arrays.hashCode(retentionTimes);
		result = prime * result + scanNumber;
		return result;
	}

	@Override
	public synchronized void setAnnotation(final Collection<AnnotationSerializable> annotation) {
		this.annotation = annotation;
	}

	@Override
	public synchronized IonImpl setName(final String name) {

		this.name = name;
		return this;
	}

	@Override
	public synchronized IonImpl setPeak(final PeakMSqBAT peak) {

		if (peak != null && !peak.getIons().contains(this)) {
			throw new IllegalArgumentException("This ion is not a member of this peak");
		}
		this.peak = peak;
		return this;
	}

	@Override
	public synchronized IonImpl setRetentionTimes(final double[] retentionTimes) {

		this.retentionTimes = retentionTimes;
		return this;
	}

	@Override
	public synchronized IonImpl setSample(final SampleMSqBAT sample) {

		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized IonMSqBAT setScan(final ScanMSqBAT scan) {
		this.scan = scan;
		return this;
	}

	@Override
	public synchronized IonImpl setScanNumber(final int scanNumber) {

		this.scanNumber = scanNumber;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("scan:" + scanNumber + ",mz:" + String.format("%8.4f", mz) + ",int:"
				+ String.format("%8.4f", intensity));
		if (retentionTimes != null) {
			sb.append(", rt:" + String.format("%3.2f", retentionTimes[0]));
		}
		return sb.toString();
	}
}
