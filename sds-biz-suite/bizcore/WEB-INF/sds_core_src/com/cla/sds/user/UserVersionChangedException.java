
package com.cla.sds.user;
import com.cla.sds.EntityNotFoundException;

public class UserVersionChangedException extends UserManagerException {
	private static final long serialVersionUID = 1L;
	public UserVersionChangedException(String string) {
		super(string);
	}


}


