/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.object;

/**
 *
 * @author HOME
 */
public class RandomID {
    public String getRandomString(int length) {
        String result = "";
        String upper = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String lower = upper.toLowerCase();
        String number = "1234567890";
        String randomString = upper + lower + number;

        for (int i = 0; i < length; i++) {
            int temp = (int) Math.round(Math.random() * randomString.length());
            result += randomString.charAt(temp);
        }
        return result;
    }
}
