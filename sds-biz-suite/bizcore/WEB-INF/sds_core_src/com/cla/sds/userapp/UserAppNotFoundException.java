
package com.cla.sds.userapp;
import com.cla.sds.EntityNotFoundException;
public class UserAppNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public UserAppNotFoundException(String string) {
		super(string);
	}

}

