package com.rarnau.fastquickproto.test.config;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringTestConfig.class })
@TestExecutionListeners(value = DependencyInjectionTestExecutionListener.class)
public abstract class AbstractTest {

}
