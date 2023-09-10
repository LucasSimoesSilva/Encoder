package com.example.application.entity;

public class VerifyText {
    TextEncoder textEncoder;
    boolean textConfirmed;

    String messageError;


    public VerifyText(Object textToResponse, boolean textConfirmed) {
        if(textToResponse instanceof TextEncoder textEncoded){
            this.textEncoder = textEncoded;
        }else{
            this.messageError = (String) textToResponse;
        }
        this.textConfirmed = textConfirmed;
    }


    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public TextEncoder getTextEncoder() {
        return textEncoder;
    }

    public void setTextEncoder(TextEncoder textEncoder) {
        this.textEncoder = textEncoder;
    }

    public boolean isTextConfirmed() {
        return textConfirmed;
    }

    public void setTextConfirmed(boolean textConfirmed) {
        this.textConfirmed = textConfirmed;
    }
}
