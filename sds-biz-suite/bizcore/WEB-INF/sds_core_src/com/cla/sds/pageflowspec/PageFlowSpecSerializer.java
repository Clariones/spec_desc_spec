package com.cla.sds.pageflowspec;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class PageFlowSpecSerializer extends SdsObjectPlainCustomSerializer<PageFlowSpec>{

	@Override
	public void serialize(PageFlowSpec pageFlowSpec, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, pageFlowSpec, provider);
		
	}
}


