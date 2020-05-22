package com.cla.sds.pagecontentspec;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class PageContentSpecSerializer extends SdsObjectPlainCustomSerializer<PageContentSpec>{

	@Override
	public void serialize(PageContentSpec pageContentSpec, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, pageContentSpec, provider);
		
	}
}


