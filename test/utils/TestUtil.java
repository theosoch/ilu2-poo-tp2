package utils;

import java.util.Random;

public class TestUtil {
	
	private static final int RANDOM_STRING_DEFAULT_LENGTH = 12;
	private static final String RANDOM_STRING_DEFAULT_PREFIX = "random-";
	
	//	
	
	public static String getRandomString() {
		return getRandomString(RANDOM_STRING_DEFAULT_LENGTH, RANDOM_STRING_DEFAULT_PREFIX);
	}
	
	public static String getRandomString(String prefix) {
		return getRandomString(RANDOM_STRING_DEFAULT_LENGTH, prefix);
	}
	
	public static String getRandomString(int length) {
		return getRandomString(length, RANDOM_STRING_DEFAULT_PREFIX);
	}
	
	public static String getRandomString(int length, String prefix) {
		StringBuilder randomNameBuilder = new StringBuilder();
		
		randomNameBuilder.append(prefix);
		
		for(int i = 0; i < length; i++) {
			randomNameBuilder.append((int) new Random().nextInt(10));
		}
		
		return randomNameBuilder.toString();
	}
	
	//
	
	public static void logThrowable(Throwable e) { logThrowable(0, e); }
	public static void logThrowable(int level, Throwable e) {
		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2 + level];
		System.err.print("[" + stackTraceElement.getClassName() + "] ");
		e.printStackTrace();
	}
	
	//
	
	public static void logBeforeEachStart() { logBeforeEachStart(0); }
	public static void logBeforeEachStart(int level) {
		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2 + level];
		System.out.println("[" + stackTraceElement.getClassName() + "] Initializing...");
	}
	
	public static void logBeforeEachEnd() { logBeforeEachEnd(0); }
	public static void logBeforeEachEnd(int level) {
		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2 + level];
		System.out.println("[" + stackTraceElement.getClassName() + "] Initialized.");
	}
	
	public static void wrapBeforeEach(Runnable beforeEachRunnable) {
		logBeforeEachStart(1);
		
		try {
			beforeEachRunnable.run();
		} catch(Throwable e) {
			logThrowable(1, e);
			throw e;
		}
		
		logBeforeEachEnd(1);
	}
	
	//	
	
	public static void logTestStart() { logTestStart(0); }
	public static void logTestStart(int level) {
		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2 + level];
		System.out.println("[" + stackTraceElement.getClassName() + "] running " + stackTraceElement.getMethodName() + "...");
	}
	
	public static void logTestEnd() { logTestEnd(0); }
	public static void logTestEnd(int level) {
		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2 + level];
		System.out.println("[" + stackTraceElement.getClassName() + "] " + stackTraceElement.getMethodName() + " done.");
	}
	
	public static void wrapTest(Runnable testRunnable) {
		logTestStart(1);
		
		try {
			testRunnable.run();
		} catch(Throwable e) {
			logThrowable(1, e);
			throw e;
		}
		
		logTestEnd(1);
	}
	
}
