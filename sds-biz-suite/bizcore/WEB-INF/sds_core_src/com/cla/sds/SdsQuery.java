package com.cla.sds;

import com.skynet.infrastructure.graphservice.BaseQuery;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class SdsQuery extends BaseQuery {
	
	public SdsQuery(Class startType, String ... pStart) {
        super(startType, pStart);
        super.setProject("sds");
  }

  public SdsQuery(Object start){
    this(start.getClass(), getId(start));
  }

  private static String getId(Object pStart) {
      BeanWrapper bw = new BeanWrapperImpl(pStart);
      return String.valueOf(bw.getPropertyValue("id"));
  }
}























