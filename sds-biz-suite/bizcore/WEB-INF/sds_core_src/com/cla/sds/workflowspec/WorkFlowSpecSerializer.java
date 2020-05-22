package com.cla.sds.workflowspec;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class WorkFlowSpecSerializer extends SdsObjectPlainCustomSerializer<WorkFlowSpec>{

	@Override
	public void serialize(WorkFlowSpec workFlowSpec, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, workFlowSpec, provider);
		
	}
}


