
package com.cla.sds.company;
import com.cla.sds.EntityNotFoundException;

public class CompanyVersionChangedException extends CompanyManagerException {
	private static final long serialVersionUID = 1L;
	public CompanyVersionChangedException(String string) {
		super(string);
	}


}


