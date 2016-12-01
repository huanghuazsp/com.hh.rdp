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
import com.hh.rdp.model.Column;
import com.hh.rdp.util.Check;
import com.hh.rdp.util.FrameMessage;
import com.hh.rdp.util.WidgetUtil;

public class TableEditPage extends Dialog {
	private PageGrid page;
	private Text nameText;
	private Text textText;
	private Table table;
	private Project project;
	private boolean edit;

	public TableEditPage(PageGrid page, Shell parentShell, Object object) {
		super(parentShell);
		this.page = page;
		if (object instanceof Table) {
			this.table = (Table) object;
			edit = true;
		} else if (object instanceof Project) {
			this.project = (Project) object;
			edit = false;
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		getShell().setText("表编辑");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		composite.setLayout(gridLayout);
		textText = WidgetUtil.createText(composite, "注释：");
		
		nameText = WidgetUtil.createText(composite, "名称：");

		if (edit) {
			nameText.setText(table.getName());
			textText.setText(table.getText());
		}
		return composite;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 160);
	}

	@Override
	protected void okPressed() {
		if (!checkName(nameText.getText())) {
			return;
		}
		if (edit) {
			huitian(table);
		} else {
			Table table = new Table();
			huitian(table);
			project.getChildren().add(table);
		}
		page.getViewer().refresh();
		nameText.setText("");
		textText.setText("");
		page.getEditorPartMain().setPageModified(true);
		this.close();
	}

	private void huitian(Table table) {
		table.setId(UUID.randomUUID().toString());
		table.setText(textText.getText());
		table.setName(nameText.getText());
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
