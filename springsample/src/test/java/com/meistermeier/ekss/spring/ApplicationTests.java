package com.meistermeier.ekss.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"default","testing"})
public class ApplicationTests {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationTests.class);

	@Autowired
	private ProfileSampleBean profileSampleBean;

	@Test
	public void profileTest() {
		LOG.info(profileSampleBean.doWork());
	}

}
