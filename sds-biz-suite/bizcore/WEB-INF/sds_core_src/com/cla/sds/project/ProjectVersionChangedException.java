
package com.cla.sds.project;
import com.cla.sds.EntityNotFoundException;

public class ProjectVersionChangedException extends ProjectManagerException {
	private static final long serialVersionUID = 1L;
	public ProjectVersionChangedException(String string) {
		super(string);
	}


}


