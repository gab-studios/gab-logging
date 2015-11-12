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



/**
 * A service to handle logging. This uses the java logging.
 *
 * @author Gregory Brown (sysdevone)
 */
public abstract interface LogService
{
    
    /**
     * Call when you want to log detailed information for debugging or tracing.
     *
     * @param clazz
     *            The class that is calling this log method.
     * @param methodName
     *            The name of the class method that is calling this log method.
     * @param message
     *            The message to send to the log.
     */
    public abstract void logDetail(final Class<?> clazz,
            final String methodName, final String message);
    
    /**
     * Call when you want to log a failure message.
     *
     * @param clazz
     *            The class that is calling this log method.
     * @param methodName
     *            The name of the class method that is calling this log method.
     * @param message
     *            The message to send to the log.
     */
    public abstract void logFailure(final Class<?> clazz,
            final String methodName, final String message);
    
    /**
     * Call when you want to log a failure with throwable details.
     *
     * @param clazz
     *            The class that is calling this log method.
     * @param methodName
     *            The name of the class method that is calling this log method.
     * @param message
     *            The message to send to the log.
     * @param thrown
     *            The throwable to log details about.
     */
    public abstract void logFailure(final Class<?> clazz,
            final String methodName, final String message,
            final Throwable thrown);
    
    /**
     * Call when you want to log standard messages.
     *
     * @param clazz
     *            The class that is calling this log method.
     * @param methodName
     *            The name of the class method that is calling this log method.
     * @param message
     *            The message to send to the log.
     */
    public abstract void logMessage(final Class<?> clazz,
            final String methodName, final String message);
    
    /**
     * Logs a message containing the class name, method name and starting
     * timestamp;
     *
     * @param clazz
     *            The class that is calling this log method.
     * @param methodName
     *            The name of the class method that is calling this log method.
     */
//    public abstract void logMethodBegin(final Class<?> clazz,
//            final String methodName);
    
    /**
     * Logs a message containing the class name, method name, endinging
     * timestamp and difference time;
     *
     * @param clazz
     *            The class that is calling this log method.
     * @param methodName
     *            The name of the class method that is calling this log method.
     */
//    public abstract void logMethodEnd(final Class<?> clazz,
//            final String methodName);
    
    /**
     * Call when you want to log a warning message.
     *
     * @param clazz
     *            The class that is calling this log method.
     * @param methodName
     *            The name of the class method that is calling this log method.
     * @param message
     *            The message to send to the log.
     */
    public abstract void logWarning(final Class<?> clazz,
            final String methodName, final String message);
    
    /**
     * Call when you want to log a warning message with a throwable details.
     *
     * @param clazz
     *            The class that is calling this log method.
     * @param methodName
     *            The name of the class method that is calling this log method.
     * @param message
     *            The message to send to the log.
     * @param thrown
     *            The throwable to log details about.
     */
    public abstract void logWarning(final Class<?> clazz,
            final String methodName, final String message,
            final Throwable thrown);
}
