package com.cla.sds.candidatecontainer;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.cla.sds.SdsObjectPlainCustomSerializer;
public class CandidateContainerSerializer extends SdsObjectPlainCustomSerializer<CandidateContainer>{

	@Override
	public void serialize(CandidateContainer candidateContainer, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, candidateContainer, provider);
		
	}
}


