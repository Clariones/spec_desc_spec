package com.cla.sds.pagetype;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class PageTypeSerializer extends SdsObjectPlainCustomSerializer<PageType>{

	@Override
	public void serialize(PageType pageType, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, pageType, provider);
		
	}
}


