package com.meistermeier.ekss.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScopedBean {

	private static final Logger LOG = LoggerFactory.getLogger(ScopedBean.class);

	public ScopedBean() {
		LOG.info("creating new instance of this bean");
	}

}
