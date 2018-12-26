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
public class LogServiceTest
{
    LogService _logService;
    
    @Before
    public void setUp()
    {
        this._logService = LogProvider.getProvider().getService();
    }
    
    @After
    public void tearDown()
    {
        LogProvider.getProvider().clear();
        this._logService = null;
    }
    
    @Test
    public void logDebug()
    {
        
        try
        {
            this._logService.logDebug(LogProviderTest.class, "logDetail",
                    "testing logDetail");
            
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            Assert.fail(e.toString());
        }
        
    }
    
    @Test
    public void logConfiguration()
    {
        
        try
        {
            this._logService.logConfiguration(LogProviderTest.class, "logConfiguration",
                    "testing logConfiguration");
            
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            Assert.fail(e.toString());
        }
        
    }
    
    @Test
    public void logMessage()
    {
        
        try
        {
            this._logService.logMessage(LogProviderTest.class, "logMessage",
                    "testing logMessage");
            
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            Assert.fail(e.toString());
        }
        
    }
    
    @Test
    public void logWarning()
    {
        
        try
        {
            this._logService.logWarning(LogProviderTest.class, "logWarning",
                    "testing logWarning");
            
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            Assert.fail(e.toString());
        }
        
    }
    
    @Test
    public void logWarningWithException()
    {
        
        try
        {
            this._logService.logWarning(LogProviderTest.class, "logWarningWithException",
                    "testing logWarning with Throwable", new Exception(
                            "logWarningWithException Exception"));
            
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            Assert.fail(e.toString());
        }
        
    }
    
    @Test
    public void logFailure()
    {
        
        try
        {
            this._logService.logFailure(LogProviderTest.class, "logFailure",
                    "testing logFailure");
            
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            Assert.fail(e.toString());
        }
        
    }
    
    @Test
    public void logFailureWithException()
    {
        
        try
        {
            this._logService.logFailure(LogProviderTest.class, "logFailureWithException",
                    "testing logFailure with Throwable", new Exception(
                            "logFailureWithException Exception"));
            
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            Assert.fail(e.toString());
        }
        
    }
    
    @Test
    public void logSecurity()
    {
        
        try
        {
            this._logService.logSecurity(LogProviderTest.class, "logSecurity",
                    "testing logSecurity");
            
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            Assert.fail(e.toString());
        }
        
    }
    
    @Test
    public void logSecurityWithException()
    {
        
        try
        {
            this._logService.logSecurity(LogProviderTest.class, "logSecurityWithException",
                    "testing logSecurity with Throwable", new Exception(
                            "logSecurityWithException Exception"));
            
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            Assert.fail(e.toString());
        }
        
    }
    
    
}
