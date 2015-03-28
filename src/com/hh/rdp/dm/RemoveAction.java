package com.hh.rdp.dm;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hh.rdp.dm.model.Column;
import com.hh.rdp.dm.model.Table;
import com.hh.rdp.util.FrameMessage;

public class RemoveAction extends Action {
	private PageGrid page = null;
	private ISelectionChangedListener listener = new ISelectionChangedListener() {
		public void selectionChanged(SelectionChangedEvent e) {
			setEnabled(!e.getSelection().isEmpty());
		}
	};

	public RemoveAction(PageGrid page, String text) {
		super(text, PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		setEnabled(false);
		page.getViewer().addSelectionChangedListener(listener);
		ImageDescriptor disableRemoveImage = PlatformUI.getWorkbench()
				.getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED);
		this.setDisabledImageDescriptor(disableRemoveImage);
		this.page = page;
	}

	public void run() {
		doDelete();
	}

	public void doDelete() {
		String msg = "您确定删除选中的字段吗？";
		if (!FrameMessage.question(msg)) {
			return;
		}
		List<Object> objects = this.page.getSelectObjectList();
		for (Object object : objects) {
			if (object instanceof Column) {
				Column column = (Column) object;
				column.getParent().getChildren().remove(object);
			} else if (object instanceof Table) {
				Table table = (Table) object;
				table.getParent().getChildren().remove(object);
			}
		}

		this.page.getViewer().refresh();
		page.getEditorPartMain().setPageModified(true);
	}
}
