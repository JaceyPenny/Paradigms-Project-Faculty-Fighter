package com.jacemcpherson.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Console {

    private static SimpleDateFormat mFormatter = new SimpleDateFormat("dd MMM, hh:mm:ss");
    private static Scanner mScanner = new Scanner(System.in);

    private static boolean isAcceptingInput = true;

    public enum LogType {
        ERROR, DEBUG, INFO, WARNING
    }

    public static void init() {
        System.out.print("Terraria Paradigms v0.1alpha\n> ");
    }

    public static void out(LogType type, String message) {
        System.out.printf(
                "%s | %s: %s\n> ",
                mFormatter.format(new Date()),
                type.toString(),
                message
        );

    }

    public static void disableInput() {
        isAcceptingInput = false;
    }

    public static void enableInput() {
        isAcceptingInput = true;
    }

    public static String getLine() {
        String result = mScanner.nextLine();
        System.out.print("> ");
        return result;
    }

    public static String get() {
        String result = mScanner.next();
        System.out.print("> ");
        return result;
    }

    public static int getInt() {
        int result = mScanner.nextInt();
        System.out.print("> ");
        return result;
    }

    public static double getDouble() {
        double result = mScanner.nextDouble();
        System.out.print("> ");
        return result;
    }

    public static long getLong() {
        long result = mScanner.nextLong();
        System.out.print("> ");
        return result;
    }

    public static boolean getBoolean() {
        boolean result = mScanner.nextBoolean();
        System.out.print("> ");
        return result;
    }

    public static float getFloat() {
        float result = mScanner.nextFloat();
        System.out.print("> ");
        return result;
    }

    public static boolean hasNext() {
        return mScanner.hasNext();
    }

    public static void e(String message) {
        out(LogType.ERROR, message);
    }

    public static void d(String message) {
        out(LogType.DEBUG, message);
    }

    public static void i(String message) {
        out(LogType.INFO, message);
    }

    public static void w(String message) {
        out(LogType.WARNING, message);
    }

    public static void exception(Exception e) {
        e(e.getMessage());
    }
}
