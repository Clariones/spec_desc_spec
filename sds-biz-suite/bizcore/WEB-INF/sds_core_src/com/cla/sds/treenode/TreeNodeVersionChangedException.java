
package com.cla.sds.treenode;
import com.cla.sds.EntityNotFoundException;

public class TreeNodeVersionChangedException extends TreeNodeManagerException {
	private static final long serialVersionUID = 1L;
	public TreeNodeVersionChangedException(String string) {
		super(string);
	}


}


