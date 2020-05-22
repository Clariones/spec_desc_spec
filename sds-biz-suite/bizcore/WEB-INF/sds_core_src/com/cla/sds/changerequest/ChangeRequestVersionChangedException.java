
package com.cla.sds.changerequest;
import com.cla.sds.EntityNotFoundException;

public class ChangeRequestVersionChangedException extends ChangeRequestManagerException {
	private static final long serialVersionUID = 1L;
	public ChangeRequestVersionChangedException(String string) {
		super(string);
	}


}


