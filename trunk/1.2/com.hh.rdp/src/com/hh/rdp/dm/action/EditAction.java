package com.hh.rdp.dm.action;

import java.util.List;

import org.eclipse.jface.action.Action;

import com.hh.rdp.dm.ColumnEditPage;
import com.hh.rdp.dm.PageGrid;
import com.hh.rdp.dm.TableEditPage;
import com.hh.rdp.dm.model.Column;
import com.hh.rdp.dm.model.Table;
import com.hh.rdp.util.image.ImageKeys;

public class EditAction extends Action {
	private PageGrid page;

	public EditAction(PageGrid page) {
		super("修改", ImageKeys.getImageDescriptor(ImageKeys.database_edit));
		this.page = page;
	}

	public void run() {
		List<Object> selectObjectList = page.getSelectObjectList();
		if (selectObjectList.size() > 0) {
			if (selectObjectList.get(0) instanceof Table) {
				new TableEditPage(page, page.getEditor().getSite().getShell(),
						selectObjectList.get(0)).open();
			} else if (selectObjectList.get(0) instanceof Column) {
				new ColumnEditPage(page, page.getEditor().getSite().getShell(),
						selectObjectList.get(0)).open();
			}
		}
	}
}
