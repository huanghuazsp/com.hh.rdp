package com.hh.rdp.dm.action;

import java.util.List;

import org.eclipse.jface.action.Action;

import com.hh.rdp.dm.ColumnEditPage;
import com.hh.rdp.dm.CreateSourceDialog;
import com.hh.rdp.dm.PageGrid;
import com.hh.rdp.dm.TableEditPage;
import com.hh.rdp.dm.model.Project;
import com.hh.rdp.dm.model.Table;
import com.hh.rdp.model.Column;
import com.hh.rdp.util.Check;
import com.hh.rdp.util.FrameMessage;
import com.hh.rdp.util.image.ImageKeys;

public class CreateSourceAction extends Action {
	private PageGrid page;

	public CreateSourceAction(PageGrid page) {
		super("生成代码", ImageKeys
				.getImageDescriptor(ImageKeys.database_lightning));
		this.page = page;
	}

	public void run() {
		List<Object> selectObjectList = page.getSelectObjectList();
		if (selectObjectList.size() > 0) {
			if (selectObjectList.get(0) instanceof Table) {
				new CreateSourceDialog(page, page.getEditor().getSite()
						.getShell(),(Table) selectObjectList.get(0)).open();
			} else {
				FrameMessage.info("请选中table！");
			}
		} else {
			FrameMessage.info("请选中一条数据！");
		}
	}
}
