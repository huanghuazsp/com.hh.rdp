package com.hh.rdp.dm.action;

import org.eclipse.jface.action.Action;

import com.hh.rdp.dm.CreateSourceDialog;
import com.hh.rdp.dm.PageGrid;
import com.hh.rdp.dm.model.Project;
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
		Project project = (Project) page.getViewer().getTree().getItem(0).getData();
		if (Check.isEmpty(project.getName())) {
			FrameMessage.info("表名不能为空！");
			return;
		}
		new CreateSourceDialog(page, page.getEditor().getSite().getShell())
				.open();
	}
}
