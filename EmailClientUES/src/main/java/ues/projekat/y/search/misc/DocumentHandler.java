package ues.projekat.y.search.misc;

import java.io.File;


public abstract class DocumentHandler {
	
	public abstract IndexUnit getIndexUnit(File file);
	public abstract String getText(File file);

}
