
package com.cla.sds.user;
import com.cla.sds.EntityNotFoundException;
public class UserNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public UserNotFoundException(String string) {
		super(string);
	}

}

