package com.jawbr.utils;

import java.util.List;

public class MergeDescription {
	
	// Method to merge the List description that comes from the JSON
	public static String mergeDescription(List<String> description) {
		String mergedDescr = String.join("\n", description);
		return mergedDescr;
	}
}
