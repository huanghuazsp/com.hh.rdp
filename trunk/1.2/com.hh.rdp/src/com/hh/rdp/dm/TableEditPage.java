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

import com.hh.rdp.dm.model.Project;
import com.hh.rdp.dm.model.Table;
import com.hh.rdp.util.Check;
import com.hh.rdp.util.FrameMessage;

public class TableEditPage extends Dialog {
	private Page page;
	private Text nameText;

	public TableEditPage(Page page, Shell parentShell) {
		super(parentShell);
		this.page = page;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		getShell().setText("表编辑");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		composite.setLayout(gridLayout);
		Label label = new Label(composite, SWT.NONE);
		label.setText("名称：");
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
		Table table = new Table();
		table.setId(UUID.randomUUID().toString());
		table.setText(nameText.getText());
		table.setName(nameText.getText());
		Project project = (Project) page.getViewer().getTree().getItem(0).getData();
		project.getChildren().add(table);
		page.getViewer().refresh();
		nameText.setText("");
		page.getEditorPartMain().setPageModified(true);
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
