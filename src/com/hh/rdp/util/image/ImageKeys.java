package com.hh.rdp.util.image;

import org.eclipse.jface.resource.ImageDescriptor;

import com.hh.rdp.Activator;

public final class ImageKeys {
	public static final String world = "icons/world.png";
	public static final String database_field = "icons/database_field.gif";
	public static final String database = "icons/database.png";
	public static final String database_table = "icons/database_table.png";
	public static final String database_project = "icons/database_project.png";
	public static final String database_add = "icons/database_add.png";
	public static final String database_delete = "icons/database_delete.png";
	public static final String database_save = "icons/database_save.png";
	public static final String database_import = "icons/database_import.gif";
	public static final String database_go = "icons/database_go.png";
	public static final String database_lightning = "icons/database_lightning.png";
	public static final String project = "icons/project.png";
	public static final String add = "icons/add.png";
	public static final String delete = "icons/delete.gif";
	public static final String refresh = "icons/refresh.png";

	public static ImageDescriptor getImageDescriptor(String path) {
		return Activator.getImageDescriptor(path);
	}
}
