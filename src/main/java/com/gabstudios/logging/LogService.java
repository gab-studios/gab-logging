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

import java.util.logging.Level;
import java.util.logging.Logger;

import com.gabstudios.validate.Validate;

/**
 * A service to handle logging. This uses the java logging.
 *
 *
 * @author Gregory Brown (sysdevone)
 */
public class LogService
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

	private static final int	METHOD_NAME_MAX_LENGTH	= 64;

	private LogSanitizer _sanitizer;

	{
		// create default does nothing sanitizer.  Just provides fall through.
		this._sanitizer = new LogSanitizer() 
		{
			public String sanitize( String untrustedData )
			{
				return( untrustedData );
			}
		};
	}


	/**
	 * Protected class. Should only be created by LogProvider.
	 */
	protected LogService()
	{
		// void - protectes class scope.
	}

	public void setSanitizer( LogSanitizer sanitizer )
	{
		this._sanitizer = sanitizer;
	}

	private LogSanitizer getSanitizer()
	{
		return(this._sanitizer);
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
	public final void logConfiguration(final Class<?> clazz, final String methodName, final String message)
	{
		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(LogService.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(LogService.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.CONFIG))
		{
			final LogSanitizer sanitizer = getSanitizer();
			final String sMethodName = sanitizer.sanitize(methodName);
			final String sMessage = sanitizer.sanitize(message);

			logger.logp(Level.CONFIG, fqcn, sMethodName, sMessage);
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
	public final void logDebug(final Class<?> clazz, final String methodName, final String message)
	{
		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(LogService.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(LogService.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.FINEST))
		{
			final LogSanitizer sanitizer = getSanitizer();
			final String sMethodName = sanitizer.sanitize(methodName);
			final String sMessage = sanitizer.sanitize(message);

			logger.logp(Level.FINEST, fqcn, sMethodName, sMessage);
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
	public final void logFailure(final Class<?> clazz, final String methodName, final String message)
	{

		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(LogService.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(LogService.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.SEVERE))
		{
			final LogSanitizer sanitizer = getSanitizer();
			final String sMethodName = sanitizer.sanitize(methodName);
			final String sMessage = sanitizer.sanitize(message);

			logger.logp(Level.SEVERE, fqcn, sMethodName, sMessage);
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
	public final void logFailure(final Class<?> clazz, final String methodName, final String message,
	        final Throwable thrown)
	{
		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(LogService.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(LogService.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineObject(thrown).testNotNull().throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.SEVERE))
		{
			final LogSanitizer sanitizer = getSanitizer();
			final String sMethodName = sanitizer.sanitize(methodName);
			final String sMessage = sanitizer.sanitize(message);

			logger.logp(Level.SEVERE, fqcn, sMethodName, sMessage, thrown);
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
	public final void logMessage(final Class<?> clazz, final String methodName, final String message)
	{
		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(LogService.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(LogService.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.INFO))
		{

			final LogSanitizer sanitizer = getSanitizer();
			final String sMethodName = sanitizer.sanitize(methodName);
			final String sMessage = sanitizer.sanitize(message);

			logger.logp(Level.INFO, fqcn, sMethodName, sMessage);
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
	public final void logSecurity(final Class<?> clazz, final String methodName, final String message)
	{

		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(LogService.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(LogService.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.WARNING))
		{
			final LogSanitizer sanitizer = getSanitizer();
			final String sMethodName = sanitizer.sanitize(methodName);
			final String sMessage = sanitizer.sanitize(message);

			logger.logp(SecurityLevel.SECURITY, fqcn, sMethodName, sMessage);
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
	public final void logSecurity(final Class<?> clazz, final String methodName, final String message,
	        final Throwable thrown)
	{

		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(LogService.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(LogService.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineObject(thrown).testNotNull().throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.WARNING))
		{
			final LogSanitizer sanitizer = getSanitizer();
			final String sMethodName = sanitizer.sanitize(methodName);
			final String sMessage = sanitizer.sanitize(message);

			logger.logp(SecurityLevel.SECURITY, fqcn, sMethodName, sMessage, thrown);
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
	public final void logWarning(final Class<?> clazz, final String methodName, final String message)
	{

		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(LogService.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(LogService.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.WARNING))
		{
			final LogSanitizer sanitizer = getSanitizer();
			final String sMethodName = sanitizer.sanitize(methodName);
			final String sMessage = sanitizer.sanitize(message);

			logger.logp(Level.WARNING, fqcn, sMethodName, sMessage);

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
	public final void logWarning(final Class<?> clazz, final String methodName, final String message,
	        final Throwable thrown)
	{

		Validate.defineString(clazz.getName()).testNotNullEmpty().throwValidationExceptionOnFail().validate();
		Validate.defineString(methodName).testNotNullEmpty().testMaxLength(LogService.METHOD_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineString(message).testNotNullEmpty().testMaxLength(LogService.MESSAGE_NAME_MAX_LENGTH)
		        .throwValidationExceptionOnFail().validate();
		Validate.defineObject(thrown).testNotNull().throwValidationExceptionOnFail().validate();

		final String fqcn = clazz.getName();
		final Logger logger = Logger.getLogger(fqcn);
		if (logger.isLoggable(Level.WARNING))
		{
			final LogSanitizer sanitizer = getSanitizer();
			final String sMethodName = sanitizer.sanitize(methodName);
			final String sMessage = sanitizer.sanitize(message);

			logger.logp(Level.WARNING, fqcn, sMethodName, sMessage, thrown);
		}
	}

}
