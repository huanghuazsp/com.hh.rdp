package com.hh.rdp.dm;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.hh.rdp.dm.model.Project;
import com.hh.rdp.dm.model.Table;
import com.hh.rdp.util.Check;
import com.hh.rdp.util.FrameMessage;

public class CreateSourceDialog extends Dialog {
	private Page page;
	private Text basePackageText;
	private Text domainPackageText;
	private Text conditionPackageText;
	private Text managerPackageText;
	private Text serviceRestPackageText;
	private Text listPagePackageText;
	private Text editPagePackageText;

	private Text doMaimClassNameText;

	private Text projectText;
	private Project project;

	public CreateSourceDialog(Page page, Shell parentShell) {
		super(parentShell);
		this.page = page;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		project = (Project) page.getViewer().getTree().getItem(0).getData();
		Composite composite = new Composite(parent, SWT.NONE);
		return composite;
	}

	private int findCharIndex(String name, String cr, int index) {
		int a = 0;
		for (int i = 0; i < name.length(); i++) {
			String charStr = name.substring(i, i + 1);
			if (charStr.equals(cr)) {
				a++;
			}
			if (a == index) {
				return i;
			}
		}
		return -1;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(540, 330);
	}

	@Override
	protected void okPressed() {
	
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
