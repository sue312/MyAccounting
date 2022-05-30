package com.android.myaccounting.databases;

public class Data {

    private static int balance;
    private static int expenditure;
    private static int income;
    private static long time;
    private static String reason;

    public static int getBalance() {
        return balance;
    }

    public static void setBalance(int balance) {
        Data.balance = balance;
    }

    public static int getExpenditure() {
        return expenditure;
    }

    public static void setExpenditure(int expenditure) {
        Data.expenditure = expenditure;
    }

    public static int getIncome() {
        return income;
    }

    public static void setIncome(int income) {
        Data.income = income;
    }

    public static long getTime() {
        return time;
    }

    public static void setTime(long time) {
        Data.time = time;
    }

    public static String getReason() {
        return reason;
    }

    public static void setReason(String reason) {
        Data.reason = reason;
    }
}
