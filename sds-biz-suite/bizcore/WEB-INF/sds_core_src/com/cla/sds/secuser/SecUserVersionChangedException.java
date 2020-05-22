
package com.cla.sds.secuser;
import com.cla.sds.EntityNotFoundException;

public class SecUserVersionChangedException extends SecUserManagerException {
	private static final long serialVersionUID = 1L;
	public SecUserVersionChangedException(String string) {
		super(string);
	}


}


