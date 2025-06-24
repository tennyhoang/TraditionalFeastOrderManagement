/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 *
 * @author ttuan
 */
public class Inputter {
    private static final Scanner sc = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    /**
    * Đọc chuỗi đầu vào mà không cần xác thực.
    *
    * @param message Tin nhắn nhắc
    * ​​@return Chuỗi đầu vào đã cắt bớt
    */
    public static String getString(String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }

    /**
    * Đọc chuỗi đầu vào và xác thực nó theo mẫu biểu thức chính quy đã cho.
    *
    * @param message Tin nhắn nhắc
    * ​​@param pattern Mẫu biểu thức chính quy để xác thực
    * @return Chuỗi đầu vào hợp lệ
    */
    public static String getStringWithPattern(String message, String pattern) {
        String input;
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (Acceptable.isValid(input, pattern)) {
                return input;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        } while (true);
    }

    /**
    * Đọc đầu vào số nguyên.
    *
    * @param message Tin nhắn nhắc
    * ​​@return Đầu vào số nguyên hợp lệ
    */
    public static int getInt(String message) {
        int number;
        while (true) {
            try {
                System.out.print(message);
                number = Integer.parseInt(sc.nextLine().trim());
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        } 
    }

    /**
     * Reads a double input.
     *
     * @param message Prompt message
     * @return Valid double input
     */
    public static double getDouble(String message) {
        double number;
        while (true) {
            try {
                System.out.print(message);
                number = Double.parseDouble(sc.nextLine().trim());
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a decimal number.");
            }
        }
    }

    /**
    * Đọc dữ liệu đầu vào ngày và xác thực đó là ngày trong tương lai.
    *
    * @param message Tin nhắn nhắc
    * ​​@return Ngày trong tương lai hợp lệ
    */
    public static Date getFutureDate(String message) {
        Date date;
        while (true) {
            try {
                System.out.print(message);
                date = dateFormat.parse(sc.nextLine().trim());
                if (date.after(new Date())) return date;
                else System.out.println("Date must be in the future.");
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter in dd/MM/yyyy.");
            }
        }
    }


}