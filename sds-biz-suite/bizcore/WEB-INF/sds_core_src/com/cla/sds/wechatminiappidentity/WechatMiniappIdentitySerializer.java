package com.cla.sds.wechatminiappidentity;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class WechatMiniappIdentitySerializer extends SdsObjectPlainCustomSerializer<WechatMiniappIdentity>{

	@Override
	public void serialize(WechatMiniappIdentity wechatMiniappIdentity, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, wechatMiniappIdentity, provider);
		
	}
}


