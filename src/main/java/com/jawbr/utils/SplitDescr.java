package com.jawbr.utils;

import java.util.ArrayList;
import java.util.List;

import com.jawbr.entity.MagicItems;

public class SplitDescr {

	// Method to split the Descr, this is needed because of how the DB was made
	public static void splitDescr(List<MagicItems> items) {
		
		for (MagicItems item : items) {
			String[] splitString = item.getDescr().split("\n");
			List<String> listString = new ArrayList<>();
			
			for (String string : splitString) {
				listString.add(string);
			}
			
			item.setDescription(listString);
		}
		
	}
	
	public static void splitDescr(MagicItems item) {
		
		String[] splitString = item.getDescr().split("\n");
		List<String> listString = new ArrayList<>();
		
		for (String string : splitString) {
			listString.add(string);
		}
		
		item.setDescription(listString);
	}

}

