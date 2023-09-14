package com.example.application.Util;

public class Verifier {
    public boolean verifyCharactersInText(String alphabet,String textToCheck){
        boolean flag = true;

        for (int i = 0; i < textToCheck.length(); i++) {
            char c = textToCheck.charAt(i);
            if (!alphabet.contains(String.valueOf(c))) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
