package com.example.application.Util;

import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class Util {
    public String detectMissingCharacters(TextField keyField, TextArea textToEncode, TextArea missingCharacters,
                                          Verifier verifier){
        if(!keyField.isReadOnly()){
            boolean flag = verifier.verifyCharactersInText(keyField.getValue(), textToEncode.getValue()).isFlag();
            if (!flag) {
                keyField.setHelperText("Your key doesn't have all characters of the text.");
            } else {
                keyField.setHelperText("");
                missingCharacters.setValue("");
            }
            return verifier.verifyCharactersInText(keyField.getValue(), textToEncode.getValue())
                    .getInvalidCharacters().toString();
        }else {
            return "";
        }

    }
}
