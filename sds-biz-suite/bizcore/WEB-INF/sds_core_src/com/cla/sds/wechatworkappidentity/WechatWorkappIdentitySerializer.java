package com.cla.sds.wechatworkappidentity;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class WechatWorkappIdentitySerializer extends SdsObjectPlainCustomSerializer<WechatWorkappIdentity>{

	@Override
	public void serialize(WechatWorkappIdentity wechatWorkappIdentity, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, wechatWorkappIdentity, provider);
		
	}
}


