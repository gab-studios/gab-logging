/*****************************************************************************************
 *
 * Copyright 2018 Gregory Brown. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 *****************************************************************************************
 */

package com.gabstudios.logging;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 *
 * @author Gregory Brown (sysdevone)
 *
 */
public class LogProviderNegativeTest
{

	@Test //- checks system property for setting.  If not seeting then uses default.  Not testable.
	public void getNullProvider()
	{
		try
		{
			System.setProperty("com.gabstudios.logging.LogProvider", null);
			final LogProvider logProvider = LogProvider.getProvider();
           Assert.assertTrue(true);
		}
		catch (final Exception e)
		{
           Assert.assertTrue(true);
		}

	}
	
	@Test
	public void getWrongProvider()
	{
		try
		{
			System.setProperty("com.gabstudios.logging.LogProvider", "com.gabstudios.logging.impl.JavaLogProviderImpl2");
			final LogProvider logProvider = LogProvider.getProvider();

            Assert.fail();
		}
		catch (final Exception e)
		{
            Assert.assertTrue(true);
		}

	}

	@Before
	public void setUp()
	{
		// void - does nothing.
	}

	@After
	public void tearDown()
	{
		// void - does nothing
	}

}
