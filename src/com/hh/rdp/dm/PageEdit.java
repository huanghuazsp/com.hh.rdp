package com.hh.rdp.dm;

import java.util.UUID;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.hh.rdp.dm.model.Column;
import com.hh.rdp.dm.model.Table;
import com.hh.rdp.util.Check;
import com.hh.rdp.util.FrameMessage;

public class PageEdit extends Dialog {
	private Page page;
	private Text nameText;

	public PageEdit(Page page, Shell parentShell) {
		super(parentShell);
		this.page = page;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		getShell().setText("添加字段");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		composite.setLayout(gridLayout);
		Label label = new Label(composite, SWT.NONE);
		label.setText("字段名称：");
		nameText = new Text(composite, SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		return composite;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 120);
	}

	@Override
	protected void okPressed() {
		if (!checkName(nameText.getText())) {
			return;
		}
		Column column = new Column();
		column.setId(UUID.randomUUID().toString());
		column.setText(nameText.getText());
		column.setName(nameText.getText());
		Table table = (Table) page.getViewer().getTree().getItem(0).getData();
		table.getChildren().add(column);
		page.getViewer().refresh();
		nameText.setText("");
		page.getEditorPartMain().setPageModified(true);
//		TreeItem treeItem = page.getViewer().getTree().getItem(0);
//		page.getViewer().getTree()
//				.setSelection(treeItem.getItem(treeItem.getItemCount() - 1));
		this.close();
	}

	public boolean checkName(String name) {
		boolean checkResult = true;
		if (Check.isEmpty(name)) {
			FrameMessage.info("字段名不能为空！");
			checkResult = false;
		}
		return checkResult;
	}
}
