/*****************************************************************************************
 * 
 * Copyright 2018 Gregory Brown. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 ***************************************************************************************** 
 */

package com.gabstudios.logging;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gabstudios.logging.LogProviderSysException;

/**
 * 
 * 
 * @author Gregory Brown (sysdevone)
 * 
 */
public class LogProviderSysExceptionTest {
	LogService _logService;

	@Before
	public void setUp() {
		// void
	}

	@After
	public void tearDown() {
		// void
	}

	@Test
	public void construation1Test() {
		try {
			Exception e = new LogProviderSysException("construation1Test exception");
			Assert.assertNotNull(e);
		} catch (Exception e) {
			Assert.fail(e.toString());
		}

	}
	
	@Test
	public void construation2Test() {
		try {
			Throwable t = new Exception( "Trigger Exception");
			Exception e = new LogProviderSysException("construation2Test exception", t);
			Assert.assertNotNull(e);
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

	
	@Test
	public void construation3Test() {
		try {
			Throwable t = new Exception( "Trigger Exception - construation3Test");
			Exception e = new LogProviderSysException(t);
			Assert.assertNotNull(e);
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}


}
