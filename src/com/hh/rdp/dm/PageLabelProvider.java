package com.hh.rdp.dm;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.hh.rdp.dm.model.Column;
import com.hh.rdp.dm.model.Project;
import com.hh.rdp.dm.model.Table;
import com.hh.rdp.util.image.ImageCache;
import com.hh.rdp.util.image.ImageKeys;

public class PageLabelProvider implements ITableLabelProvider {
	public Image getColumnImage(Object element, int columnIndex) {
		if (element instanceof Project) {
			switch (columnIndex) {
			case 0:
				return ImageCache.getInstance().getImage(
						ImageKeys.getImageDescriptor(ImageKeys.database_project));
			default:
				return null;
			}
		}else if (element instanceof Table) {
			switch (columnIndex) {
			case 0:
				return ImageCache.getInstance().getImage(
						ImageKeys.getImageDescriptor(ImageKeys.database_table));
			default:
				return null;
			}
		}  else {
			switch (columnIndex) {
			case 0:
				return ImageCache.getInstance().getImage(
						ImageKeys.getImageDescriptor(ImageKeys.database_field));
			default:
				return null;
			}
		}
	}

	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof Project) {
			Project table = ((Project) element);
			switch (columnIndex) {
			case 0:
				return table.getText();
			case 1:
				return table.getName();
			case 2:
				return table.getType();
			default:
				return "";
			}
		}else if (element instanceof Table) {
			Table table = ((Table) element);
			switch (columnIndex) {
			case 0:
				return table.getText();
			case 1:
				return table.getName();
			case 2:
				return table.getType();
			default:
				return "";
			}
		} else {
			Column column = ((Column) element);
			switch (columnIndex) {
			case 0:
				return column.getText();
			case 1:
				return column.getName();
			case 2:
				return column.getType();
			case 3:
				return column.getEmpty();
			case 4:
				return column.getLength();
			case 5:
				return column.getDefaultValue();
			default:
				return "";
			}
		}
	}

	public void addListener(ILabelProviderListener listener) {
	}

	public void dispose() {
	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {

	}
}