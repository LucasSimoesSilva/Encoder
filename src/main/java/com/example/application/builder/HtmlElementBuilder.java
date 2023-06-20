package com.example.application.builder;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextArea;

public class HtmlElementBuilder implements ElementBuilder{


    @Override
    public TextArea makeTextArea(String label, String placeholder) {
        TextArea textArea = new TextArea();
        textArea.setWidthFull();
        textArea.setMinHeight("150px");
        textArea.setMaxHeight("200px");
        textArea.setMinWidth("350px");
        textArea.setLabel(label);
        textArea.setPlaceholder(placeholder);
        return textArea;
    }

    @Override
    public Button makeButton(String text) {
        Button button = new Button(text);
        button.setMinWidth("100px");
        return button;
    }

}
