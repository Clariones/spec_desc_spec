package com.cla.sds.treenode;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class TreeNodeSerializer extends SdsObjectPlainCustomSerializer<TreeNode>{

	@Override
	public void serialize(TreeNode treeNode, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, treeNode, provider);
		
	}
}

















