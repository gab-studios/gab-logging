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

/**
 * A service to handle logging. This uses the java logging.
 * 
 * The log methods in descending order of log level are:
 * <ul>
 * <li>logSecurity (highest value)
 * <li>logFailure
 * <li>logWarning
 * <li>logMessage
 * <li>logConfiguration
 * <li>logDetail (lowest value)
 * </ul>
 * 
 * @author Gregory Brown (sysdevone)
 */
public abstract interface LogService {

	/**
	 * Call when you want to log debug information for debugging or tracing.
	 *
	 * @param clazz
	 *            The class that is calling this log method.
	 * @param methodName
	 *            The name of the class method that is calling this log method.
	 * @param message
	 *            The message to send to the log.
	 */
	public abstract void logDebug(final Class<?> clazz, final String methodName, final String message);

	/**
	 * Call when you want to log configuration information for debugging or tracing.
	 *
	 * @param clazz
	 *            The class that is calling this log method.
	 * @param methodName
	 *            The name of the class method that is calling this log method.
	 * @param message
	 *            The message to send to the log.
	 */
	public abstract void logConfiguration(final Class<?> clazz, final String methodName, final String message);

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
	public abstract void logFailure(final Class<?> clazz, final String methodName, final String message);

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
	public abstract void logFailure(final Class<?> clazz, final String methodName, final String message,
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
	public abstract void logMessage(final Class<?> clazz, final String methodName, final String message);

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
	public abstract void logWarning(final Class<?> clazz, final String methodName, final String message);

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
	public abstract void logWarning(final Class<?> clazz, final String methodName, final String message,
			final Throwable thrown);

	/**
	 * Call when you want to log a security message.
	 *
	 * @param clazz
	 *            The class that is calling this log method.
	 * @param methodName
	 *            The name of the class method that is calling this log method.
	 * @param message
	 *            The message to send to the log.
	 */
	public abstract void logSecurity(final Class<?> clazz, final String methodName, final String message);

	/**
	 * Call when you want to log a security message with a throwable details.
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
	public abstract void logSecurity(final Class<?> clazz, final String methodName, final String message,
			final Throwable thrown);
}
