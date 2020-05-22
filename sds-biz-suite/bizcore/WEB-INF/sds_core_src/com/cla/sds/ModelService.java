package com.cla.sds;

import com.terapico.caf.BlobObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;

public class ModelService {

    public BlobObject downLoadModelFile() throws Exception {
        Resource classPathResource = new ClassPathResource("com/cla/sds/sds.model");
        InputStream in = classPathResource.getInputStream();
        Long length = classPathResource.contentLength();
        byte[] content = new byte[length.intValue()];
        in.read(content);
        in.close();

        BlobObject blobObject = new BlobObject();
        blobObject.setFileName("sds.xml");
        blobObject.setMimeType("text/xml");
        blobObject.setData(content);

        return blobObject;
    }
}















