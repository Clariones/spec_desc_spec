
package com.cla.sds.treenode;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface TreeNodeManager extends BaseManager{

		

	public TreeNode createTreeNode(SdsUserContext userContext, String nodeId,String nodeType,int leftValue,int rightValue) throws Exception;
	public TreeNode updateTreeNode(SdsUserContext userContext,String treeNodeId, int treeNodeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public TreeNode loadTreeNode(SdsUserContext userContext, String treeNodeId, String [] tokensExpr) throws Exception;
	public TreeNode internalSaveTreeNode(SdsUserContext userContext, TreeNode treeNode) throws Exception;
	public TreeNode internalSaveTreeNode(SdsUserContext userContext, TreeNode treeNode,Map<String,Object>option) throws Exception;



	public void delete(SdsUserContext userContext, String treeNodeId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, TreeNode newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/




}





















