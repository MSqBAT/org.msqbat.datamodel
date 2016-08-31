
package org.msqbat.datamodel.api.ion;

import java.math.BigDecimal;

import org.apache.commons.math3.util.Precision;

public class UtilIon {

	public final static int DEFAULT_SCALE = 6;

	/**
	 * Converts an PPM delta to according absolute delta.
	 *
	 * @param parent
	 * @param ppmDelta
	 * @return converted delta
	 */
	public static double getAbs(final double parent, final double ppm) {

		return ppm * parent / 1.0E+6;
	}

	public static double getDeltaMass(final double p1, final double p2, final boolean ppm) {

		double result;
		result = Math.abs(p1 - p2);
		if (ppm) {
			result = getPpm(p1, result);
		}
		return result;
	}

	/**
	 * Converts an absolute delta to according PPM delta.
	 *
	 * @return converted delta
	 */
	public static double getPpm(final double parent, final double abs) {

		return 1.0E+6 * abs / parent;
	}

	public static int getRoundedDown(final double mz) {
		return (int) Precision.round(mz, DEFAULT_SCALE, BigDecimal.ROUND_FLOOR);
	}

	public static int getRoundedUp(final double mz) {
		return (int) Precision.round(mz, DEFAULT_SCALE, BigDecimal.ROUND_CEILING);
	}
}
