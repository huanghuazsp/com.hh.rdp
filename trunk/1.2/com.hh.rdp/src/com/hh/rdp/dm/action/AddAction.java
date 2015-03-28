package com.hh.rdp.dm.action;

import java.util.List;

import org.eclipse.jface.action.Action;

import com.hh.rdp.dm.ColumnEditPage;
import com.hh.rdp.dm.PageGrid;
import com.hh.rdp.dm.TableEditPage;
import com.hh.rdp.dm.model.Table;
import com.hh.rdp.util.image.ImageKeys;

public class AddAction extends Action {
	private PageGrid page;

	public AddAction(PageGrid page) {
		super("添加", ImageKeys.getImageDescriptor(ImageKeys.database_add));
		this.page = page;
	}

	public void run() {
		List<Object> selectObjectList = page.getSelectObjectList();
		if (selectObjectList.size() > 0
				&& selectObjectList.get(0) instanceof Table) {
			new ColumnEditPage(page, page.getEditor().getSite().getShell(),
					selectObjectList.get(0)).open();
		} else {
			new TableEditPage(page, page.getEditor().getSite().getShell(), page
					.getViewer().getTree().getItem(0).getData()).open();
		}
	}
}
