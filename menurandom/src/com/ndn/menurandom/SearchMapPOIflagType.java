/* 
 * NMapPOIflagType.java $version 2010. 1. 1
 * 
 * Copyright 2010 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */

package com.ndn.menurandom;

public class SearchMapPOIflagType {
	public static final int UNKNOWN = 0x0000;

	// Single POI icons
	private static final int SINGLE_POI_BASE = 0x0100;

	// Spot, Pin icons
	public static final int SPOT = SINGLE_POI_BASE + 1;
	public static final int PIN = SPOT + 1;

	// end of single marker icon
	public static final int SINGLE_MARKER_END = 0x04FF;

	// Direction Number icons
	private static final int MAX_NUMBER_COUNT = 1000;
	public static final int NUMBER_BASE = 0x1000; // set NUMBER_BASE + 1 for '1' number
	public static final int NUMBER_END = NUMBER_BASE + MAX_NUMBER_COUNT;

	// Custom POI icons
	private static final int MAX_CUSTOM_COUNT = 1000;
	public static final int CUSTOM_BASE = NUMBER_END;
	public static final int CUSTOM_END = CUSTOM_BASE + MAX_CUSTOM_COUNT;

	public static boolean isBoundsCentered(int markerId) {
		boolean boundsCentered = false;

		switch (markerId) {
			default:
				if (markerId >= SearchMapPOIflagType.NUMBER_BASE && markerId < SearchMapPOIflagType.NUMBER_END) {
					boundsCentered = true;
				}
				break;
		}

		return boundsCentered;
	}

	public static int getMarkerId(int poiFlagType, int iconIndex) {
		int markerId = poiFlagType + iconIndex;

		return markerId;
	}

	public static int getPOIflagType(int markerId) {
		int poiFlagType = UNKNOWN;

		// Alphabet POI icons
		if (markerId >= NUMBER_BASE && markerId < NUMBER_END) { // Direction Number icons
			poiFlagType = NUMBER_BASE;
		} else if (markerId >= CUSTOM_BASE && markerId < CUSTOM_END) { // Custom POI icons
			poiFlagType = CUSTOM_BASE;
		} else if (markerId > SINGLE_POI_BASE) {
			poiFlagType = markerId;
		}

		return poiFlagType;
	}

	public static int getPOIflagIconIndex(int markerId) {
		int iconIndex = 0;

		if (markerId >= NUMBER_BASE && markerId < NUMBER_END) { // Direction Number icons
			iconIndex = markerId - (NUMBER_BASE + 1);
		} else if (markerId >= CUSTOM_BASE && markerId < CUSTOM_END) { // Custom POI icons
			iconIndex = markerId - (CUSTOM_BASE + 1);
		} else if (markerId > SINGLE_POI_BASE) {
			iconIndex = 0;
		}

		return iconIndex;
	}
}
