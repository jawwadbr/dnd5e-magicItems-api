package com.jawbr.utils;

import java.util.ArrayList;
import java.util.List;

import com.jawbr.entity.MagicItem;

public class SplitDescr {

	// Method to split the Descr, this is needed because of how the DB was made
	public static void splitDescr(List<MagicItem> items) {
		
		for (MagicItem item : items) {
			String[] splitString = item.getDescr().split("\n");
			List<String> listString = new ArrayList<>();
			
			for (String string : splitString) {
				listString.add(string);
			}
			
			item.setDescription(listString);
		}
		
	}
	
	public static void splitDescr(MagicItem item) {
		
		String[] splitString = item.getDescr().split("\n");
		List<String> listString = new ArrayList<>();
		
		for (String string : splitString) {
			listString.add(string);
		}
		
		item.setDescription(listString);
	}

}

