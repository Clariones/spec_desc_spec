package com.cla.sds.eventupdateprofile;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class EventUpdateProfileSerializer extends SdsObjectPlainCustomSerializer<EventUpdateProfile>{

	@Override
	public void serialize(EventUpdateProfile eventUpdateProfile, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, eventUpdateProfile, provider);
		
	}
}


