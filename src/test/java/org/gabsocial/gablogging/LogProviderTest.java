/*****************************************************************************************
 * 
 * Copyright 2015 Gregory Brown. All Rights Reserved.
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

package org.gabsocial.gablogging;

import org.gabsocial.gablogging.impl.JavaLogProviderImpl;
import org.gabsocial.gablogging.impl.JavaLogServiceImpl;
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
public class LogProviderTest
{
    
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
    
    @Test
    public void getProvider()
    {
        try
        {
            LogProvider logProvider = LogProvider.getProvider();
            
            Assert.assertTrue(logProvider != null);
            Assert.assertTrue(logProvider instanceof JavaLogProviderImpl);
        }
        catch (Exception e)
        {
            Assert.fail(e.toString());
        }
        
    }
    
    
    @Test
    public void getService()
    {
        try
        {
            LogProvider logProvider = LogProvider.getProvider();
            LogService logService = logProvider.getService();
            
            Assert.assertTrue(logService != null);
            Assert.assertTrue(logService instanceof JavaLogServiceImpl);
        }
        catch (Exception e)
        {
            Assert.fail(e.toString());
        }
        
    }
    
    @Test
    public void clear()
    {
        
        try
        {
            LogProvider.getProvider().clear();
            
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            Assert.fail(e.toString());
        }
        
    }
    
}
