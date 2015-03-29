package com.hh.rdp.util.widget;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public class Combox extends Combo {
	private Map<String, String> map;

	public Combox(Map<String, String> map, Composite parent, int style) {
		super(parent, style);
		this.map = map;
	}

	@Override
	protected void checkSubclass() {
		// // TODO Auto-generated method stub
		// super.checkSubclass();
	}

	public void setItemData(Map<String, String> map) {
		Set<String> keySet = map.keySet();
		int i = 0;
		for (String key : keySet) {
			this.add(map.get(key));
			this.setData(i + "", key);
			i++;
		}
	}
	
	public String getValue() {
		return  (String) this.getData(this.getSelectionIndex() + "");
	}
	

	@Override
	public void setText(String string) {
		if (map != null) {
			string = map.get(string);
		}
		super.setText(string);
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

}
