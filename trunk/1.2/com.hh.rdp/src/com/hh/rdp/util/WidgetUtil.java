package com.hh.rdp.util;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.hh.rdp.util.widget.Combox;

public class WidgetUtil {
	private static Color whiteColor = new Color(Display.getCurrent(), 255, 255,
			255);// 声明颜色对象
	private static GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true,
			false, 1, 1);

	public static Label createLabel(Composite composite, String text) {
		Label label = new Label(composite, SWT.NONE);
//		label.setBackground(whiteColor);
		label.setText(text);
		label.setToolTipText(text);
		return label;
	}


	public static Text createText(Composite composite, String text) {
		WidgetUtil.createLabel(composite, text);
		Text textField = new Text(composite, SWT.BORDER);
		textField.setLayoutData(gridData);
		return textField;
	}

	public static Combo createCombo(Composite composite, String text,
			String[] strings) {
		WidgetUtil.createLabel(composite, text);
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setLayoutData(gridData);
		if (strings != null) {
			combo.setItems(strings);
		}
		return combo;
	}

	public static Combox createCombox(Composite composite, String text,
			Map<String, String> map) {
		WidgetUtil.createLabel(composite, text);
		Combox combo = new Combox(map, composite,SWT.READ_ONLY);
		combo.setLayoutData(gridData);
		if (map != null) {
			combo.setItemData(map);
		}
		return combo;
	}
}
