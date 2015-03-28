package com.hh.rdp.dm;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.hh.rdp.dm.model.Project;
import com.hh.rdp.dm.model.Table;
import com.hh.rdp.model.Column;

public class PageContentProvider implements IStructuredContentProvider,
		ITreeContentProvider {
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof List) {
			List<Object> input = (List<Object>) inputElement;
			return input.toArray();
		}
		return new Object[0];
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Project) {
			Project node = (Project) parentElement;
			List<Table> list = node.getChildren();
			if (list == null) {
				return new Object[0];
			}
			return list.toArray();
		}else if (parentElement instanceof Table) {
			Table node = (Table) parentElement;
			List<Column> list = node.getChildren();
			if (list == null) {
				return new Object[0];
			}
			return list.toArray();
		} else {
			return new ArrayList<Column>().toArray();
		}

	}

	public boolean hasChildren(Object element) {
		if (element instanceof Project) {
			Project node = (Project) element;
			List<Table> list = node.getChildren();
			return !(list == null || list.isEmpty());
		} else if (element instanceof Table) {
			Table node = (Table) element;
			List<Column> list = node.getChildren();
			return !(list == null || list.isEmpty());
		} else {
			return false;
		}
	}

	public Object getParent(Object element) {
		return null;
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}

}