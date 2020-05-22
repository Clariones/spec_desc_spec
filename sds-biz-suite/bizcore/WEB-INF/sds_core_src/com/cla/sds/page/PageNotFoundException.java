
package com.cla.sds.page;
import com.cla.sds.EntityNotFoundException;
public class PageNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public PageNotFoundException(String string) {
		super(string);
	}

}

