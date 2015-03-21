package com.hh.rdp.view;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

import com.hh.rdp.util.FrameMessage;

public class OpenPreviewView implements IWorkbenchWindowActionDelegate {
	public static PreviewView viewPart;
	public static List<Map<String, Object>> mapList;
	public static String formJsonText;

	private IWorkbenchWindow window;

	public OpenPreviewView() {
	}

	public void run(IAction action) {
		if (window == null)
			return;
		IWorkbenchPage page = window.getActivePage();
		if (page == null)
			return;

		try {
	 page.showView(PreviewView.class.getName());
		} catch (PartInitException e) {
			FrameMessage.error(e);
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public static List<Map<String, Object>> getMapList() {
		return mapList;
	}

	public static void setMapList(List<Map<String, Object>> mapList) {
		OpenPreviewView.mapList = mapList;
	}

	public static String getFormJsonText() {
		return formJsonText;
	}

	public static void setFormJsonText(String formJsonText) {
		OpenPreviewView.formJsonText = formJsonText;
	}

}