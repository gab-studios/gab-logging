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

package com.gabstudios.logging.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.gabstudios.logging.LogService;
import com.gabstudios.validate.Validate;

/**
 * A service to handle logging. This uses the java logging.
 *
 *
 * @author Gregory Brown (sysdevone)
 */
public class JavaLogServiceImpl implements LogService
{
	/**
	 * The SecurityLevel class defines a security logging level that can be used to control logging output by
	 * subclassing the existing Level class. The levels in descending order are:
	 * <ul>
	 * <li>SECURITY (highest value)
	 * <li>SEVERE
	 * <li>WARNING
	 * <li>INFO
	 * <li>CONFIG
	 * <li>FINE
	 * <li>FINER
	 * <li>FINEST (lowest value)
	 * </ul>
	 * This level should blend into the existing logging system.
	 *
	 */
	public static class SecurityLevel extends Level
	{

		/**
		 * Serialization
		 */
		private static final long	serialVersionUID	= -3101561540429862423L;

		private static final String	defaultBundle		= "sun.util.logging.resources.logging";

		/**
		 * SECURITY is a message level indicating a security failure.
		 * <p>
		 * In general SECURITY messages should describe events that are of considerable importance and which will
		 * prevent normal program execution. They should be reasonably intelligible to end users and to system
		 * administrators. Security level events should always go to the log. This level is initialized to
		 * <CODE>2000</CODE>.
		 */
		public static final Level	SECURITY			= new SecurityLevel("SECURITY",
		        (Level.SEVERE.intValue() + 1000), SecurityLevel.defaultBundle);

		protected SecurityLevel(final String name, final int value)
		{
			super(name, value);
		}

		protected SecurityLevel(final String name, final int value, final String resourceBundleName)
		{
			super(name, value, resourceBundleName);
		}

	}

	private static final int	MESSAGE_NAME_MAX_LENGTH	= 256;

	// private static String createJSONLogMethodMessage(final String fqcn,
	// final String methodName, final Long timeInMillis,
	// final String messageString)
	// {
	//
	// /*
	// * validity check.
	// */
	// assert (fqcn != null) : "The parameter 'fqcn' is NULL.";
	// assert (fqcn.length() > 0) : "The parameter 'fqcn' is empty.";
	// assert (methodName != null) : "The parameter 'methodName' is NULL.";
	// assert (methodName.length() > 0) : "The parameter 'methodName' is empty.";
	// assert (timeInMillis > -1L) : "The parameter 'timeInMillis' less than zero.";
	// assert (messageString != null) : "The parameter 'messageString' is NULL.";
	// assert (messageString.length() > 0) : "The parameter 'messageString' is
	// empty.";
	//
	// /*
	// * Create the json message.
	// */
	// final StringBuilder message = new StringBuilder();
	// message.append("{");
	// message.append("'fqcn':'" + fqcn + "',");
	// message.append("'method':'" + methodName + "',");
	// message.append("'threadId':'" + Thread.currentThread().getId() + "',");
	// try
	// {
	// message.append("'ipAddress':'" + InetAddress.getLocalHost() + "',");
	// }
	// catch (final UnknownHostException e)
	// {
	// message.append("'ipAddress':'unknown',");
	// }
	// message.append("'message':'" + messageString + "',");
	// message.append("'timeInMillis':" + timeInMillis);
	//
	// message.append("}");
	// return (message.toString());
	// }

	private static final int	METHOD_NAME_MAX_LENGTH	= 64;

	/**
	 * Protected class. Should only be created by LogProvider.
	 */
	protected JavaLogServiceImpl()
	{
		// void - protectes class scope.
	}

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
	@Override
	public final void logConfiguration(final Class<?> clazz, final String methodName, final String message)
	{
		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.CONFIG))
		{
			logger.logp(Level.CONFIG, fqcn, methodName, message);
		}
	}

	/**
	 * Call when you want to log debug information for debugging or tracing.
	 *
	 * @param clazz
	 *            The class that is calling this log method.
	 * @param methodName
	 *            The name of the class method that is calling this log method. The length must not be null or empty and
	 *            less than 64
	 * @param message
	 *            The message to send to the log. The length must not be null or empty and less than 256 chars
	 */
	@Override
	public final void logDebug(final Class<?> clazz, final String methodName, final String message)
	{
		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.FINEST))
		{
			logger.logp(Level.FINEST, fqcn, methodName, message);
		}
	}

	/**
	 * Call when you want to log a failure message.
	 *
	 * @param clazz
	 *            The class that is calling this log method.
	 * @param methodName
	 *            The name of the class method that is calling this log method. The length must not be null or empty and
	 *            less than 64
	 * @param message
	 *            The message to send to the log. The length must not be null or empty and less than 256 chars
	 */
	@Override
	public final void logFailure(final Class<?> clazz, final String methodName, final String message)
	{

		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.SEVERE))
		{
			logger.logp(Level.SEVERE, fqcn, methodName, message);
		}
	}

	/**
	 * Call when you want to log a failure with throwable details.
	 *
	 * @param clazz
	 *            The class that is calling this log method.
	 * @param methodName
	 *            The name of the class method that is calling this log method. The length must not be null or empty and
	 *            less than 64
	 * @param message
	 *            The message to send to the log. The length must not be null or empty and less than 256 chars
	 * @param thrown
	 *            The throwable to log details about.
	 */
	@Override
	public final void logFailure(final Class<?> clazz, final String methodName, final String message,
	        final Throwable thrown)
	{
		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineObject(thrown).testNotNull().throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.SEVERE))
		{
			logger.logp(Level.SEVERE, fqcn, methodName, message, thrown);
		}
	}

	/**
	 * Call when you want to log standard messages.
	 *
	 * @param clazz
	 *            The class that is calling this log method.
	 * @param methodName
	 *            The name of the class method that is calling this log method. The length must not be null or empty and
	 *            less than 64
	 * @param message
	 *            The message to send to the log. The length must not be null or empty and less than 256 chars
	 */
	@Override
	public final void logMessage(final Class<?> clazz, final String methodName, final String message)
	{
		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.INFO))
		{
			logger.logp(Level.INFO, fqcn, methodName, message);
		}
	}

	/**
	 * Call when you want to log a security message.
	 *
	 * @param clazz
	 *            The class that is calling this log method.
	 * @param methodName
	 *            The name of the class method that is calling this log method. The length must not be null or empty and
	 *            less than 64
	 * @param message
	 *            The message to send to the log. The length must not be null or empty and less than 256 chars
	 */
	@Override
	public final void logSecurity(final Class<?> clazz, final String methodName, final String message)
	{

		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.WARNING))
		{
			logger.logp(SecurityLevel.SECURITY, fqcn, methodName, message);
		}
	}

	/**
	 * Call when you want to log a security message with a throwable details.
	 *
	 * @param clazz
	 *            The class that is calling this log method.
	 * @param methodName
	 *            The name of the class method that is calling this log method. The length must not be null or empty and
	 *            less than 64
	 * @param message
	 *            The message to send to the log. The length must not be null or empty and less than 256 chars
	 * @param thrown
	 *            The throwable to log details about.
	 */
	@Override
	public final void logSecurity(final Class<?> clazz, final String methodName, final String message,
	        final Throwable thrown)
	{

		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineObject(thrown).testNotNull().throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.WARNING))
		{
			logger.logp(SecurityLevel.SECURITY, fqcn, methodName, message, thrown);
		}
	}

	/**
	 * Call when you want to log a warning message.
	 *
	 * @param clazz
	 *            The class that is calling this log method.
	 * @param methodName
	 *            The name of the class method that is calling this log method. The length must not be null or empty and
	 *            less than 64
	 * @param message
	 *            The message to send to the log. The length must not be null or empty and less than 256 chars
	 */
	@Override
	public final void logWarning(final Class<?> clazz, final String methodName, final String message)
	{

		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.WARNING))
		{
			logger.logp(Level.WARNING, fqcn, methodName, message);
		}
	}

	/**
	 * Call when you want to log a warning message with a throwable details.
	 *
	 * @param clazz
	 *            The class that is calling this log method.
	 * @param methodName
	 *            The name of the class method that is calling this log method. The length must not be null or empty and
	 *            less than 64
	 * @param message
	 *            The message to send to the log. The length must not be null or empty and less than 256 chars
	 * @param thrown
	 *            The throwable to log details about.
	 */
	@Override
	public final void logWarning(final Class<?> clazz, final String methodName, final String message,
	        final Throwable thrown)
	{

		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(JavaLogServiceImpl.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineObject(thrown).testNotNull().throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.WARNING))
		{
			logger.logp(Level.WARNING, fqcn, methodName, message, thrown);
		}
	}

}
