
package com.cla.sds.page;
import com.cla.sds.EntityNotFoundException;

public class PageVersionChangedException extends PageManagerException {
	private static final long serialVersionUID = 1L;
	public PageVersionChangedException(String string) {
		super(string);
	}


}


