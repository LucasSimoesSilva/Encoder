package com.example.application.Util;

import com.example.application.entity.InvalidCharacters;

public class Verifier {
    public InvalidCharacters verifyCharactersInText(String alphabet, String textToCheck){
        boolean flag = true;
        InvalidCharacters invalidCharacters = new InvalidCharacters();

        for (int i = 0; i < textToCheck.length(); i++) {
            char c = textToCheck.charAt(i);
            if (!alphabet.contains(String.valueOf(c))) {
                flag = false;
                invalidCharacters.getCharacters().add(c);
            }
        }
        invalidCharacters.setFlag(flag);
        return invalidCharacters;
    }
}
