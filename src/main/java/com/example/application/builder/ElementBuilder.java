package com.example.application.builder;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextArea;

public interface ElementBuilder {
    TextArea makeTextArea(String label, String placeholder);

    TextArea makeTextArea(String label, String placeholder, String maxWidth);

    Button makeButton(String text);

    void createErrorNotification(String text);

}
