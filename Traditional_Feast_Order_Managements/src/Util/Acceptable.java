/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Util;

/**
 *
 * @author ttuan
 */

public interface Acceptable {
    String CUSTOMER_ID = "^[CGK]\\d{4}$";
    String CUSTOMER_NAME = "^.{2,25}$";
    String PHONE_NUMBER = "^(03[2-9]|05[2-9]|07[0-9]|08[1-9]|09[0-9])\\d{7}$";
    String EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    String MENU_CODE = "^PW\\d{3}$";
    String TABLE_NUMBER = "^[1-9]\\d*$";
    String INTEGER_VALID = "^-?\\d+$";
    String DOUBLE_VALID = "^-?\\d+(\\.\\d+)?$";
    static boolean isValid(String data, String pattern) {
        return data != null && data.matches(pattern);
    }
}
