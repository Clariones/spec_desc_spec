
package com.cla.sds.treenode;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.terapico.caf.baseelement.CandidateQuery;
import com.cla.sds.BaseDAO;
import com.cla.sds.BaseEntity;
import com.cla.sds.SmartList;
import com.cla.sds.MultipleAccessKey;
import com.cla.sds.SdsUserContext;




public interface TreeNodeDAO extends BaseDAO{

	public SmartList<TreeNode> loadAll();
	public TreeNode load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<TreeNode> treeNodeList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public TreeNode present(TreeNode treeNode,Map<String,Object> options) throws Exception;
	public TreeNode clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public TreeNode save(TreeNode treeNode,Map<String,Object> options);
	public SmartList<TreeNode> saveTreeNodeList(SmartList<TreeNode> treeNodeList,Map<String,Object> options);
	public SmartList<TreeNode> removeTreeNodeList(SmartList<TreeNode> treeNodeList,Map<String,Object> options);
	public SmartList<TreeNode> findTreeNodeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countTreeNodeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countTreeNodeWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String treeNodeId, int version) throws Exception;
	public TreeNode disconnectFromAll(String treeNodeId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<TreeNode> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateTreeNode executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;

}

















