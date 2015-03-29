package com.jArtur79.training.xml.objekte;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 
 *
 */
public class Termine {
	
	private String name;

	private List<Termin> termine;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Termin> getTermine() {
		if(termine == null) {
			termine = new ArrayList<>();
		}
		return termine;
	}

	public void setTermine(List<Termin> termine) {
		this.termine = termine;
	}
	
	
}
