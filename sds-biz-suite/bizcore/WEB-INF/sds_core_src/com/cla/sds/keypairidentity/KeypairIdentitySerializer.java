package com.cla.sds.keypairidentity;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class KeypairIdentitySerializer extends SdsObjectPlainCustomSerializer<KeypairIdentity>{

	@Override
	public void serialize(KeypairIdentity keypairIdentity, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, keypairIdentity, provider);
		
	}
}


