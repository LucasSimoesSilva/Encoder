package com.example.application.entity;

public class VerifyText {
    TextEncoder textEncoder;
    boolean textConfirmed;

    String messageError;

    public VerifyText(TextEncoder textEncoder, boolean textConfirmed) {
        this.textEncoder = textEncoder;
        this.textConfirmed = textConfirmed;
    }

    public VerifyText( String messageError, boolean textConfirmed) {
        this.textConfirmed = textConfirmed;
        this.messageError = messageError;
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
