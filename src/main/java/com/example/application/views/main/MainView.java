package com.example.application.views.main;

import com.example.application.builder.HtmlElementBuilder;
import com.example.application.encoders.Decoder;
import com.example.application.encoders.Encoder;
import com.example.application.entity.TextEncoder;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.concurrent.atomic.AtomicReference;

@Route("")
public class MainView extends VerticalLayout {

    private String alphabet = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ'\"!@#$%&*()_-+=[]{},.;:/?\\1234567890";

    public MainView() {
        Encoder encoder = new Encoder();
        Decoder decoder = new Decoder();
        HtmlElementBuilder elementBuilder = new HtmlElementBuilder();
        AtomicReference<String> codeKey = new AtomicReference<>();

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setAlignItems(FlexComponent.Alignment.BASELINE);

        HorizontalLayout horizontalLayout2 = new HorizontalLayout();
        horizontalLayout2.setAlignItems(FlexComponent.Alignment.BASELINE);

        HorizontalLayout horizontalLayout3 = new HorizontalLayout();
        horizontalLayout3.setAlignItems(FlexComponent.Alignment.BASELINE);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        VerticalLayout verticalLayoutButtons1 = new VerticalLayout();
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        VerticalLayout verticalLayoutButtons2 = new VerticalLayout();
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        VerticalLayout verticalLastLayout1 = new VerticalLayout();
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        VerticalLayout verticalLastLayout2 = new VerticalLayout();
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        add(verticalLayout);
        add(horizontalLayout);
        add(horizontalLayout2);
        add(verticalLayoutButtons1);
        add(verticalLayoutButtons2);

        H1 heading = new H1("Hyper Encoder");

        TextArea textAreaNormal = elementBuilder.makeTextArea("Text for encoding", "Enter text here");
        TextArea textAreaEncoded = elementBuilder.makeTextArea("Text coded", "");
        textAreaEncoded.setReadOnly(true);

        TextArea textAreaCoded = elementBuilder.makeTextArea("Text for decoding", "Enter text here");
        TextArea textAreaDecoded = elementBuilder.makeTextArea("Text decoded", "");
        textAreaDecoded.setReadOnly(true);

        TextField keyField = new TextField();
        keyField.setLabel("Code key");
        keyField.setReadOnly(true);

        TextField personalKey = new TextField();
        personalKey.setLabel("Your key");

        Button buttonCoder = elementBuilder.makeButton("Code");
        buttonCoder.addClickListener(clickEvent -> {
            boolean random = true;
            if(!keyField.getValue().equals("")){
                alphabet = keyField.getValue();
                random = false;
            }
            TextEncoder textEncoder = encoder.codeText(textAreaNormal.getValue(), alphabet, random);
            String textCoded = textEncoder.getText();
            codeKey.set(textEncoder.getKey());
            keyField.setValue(codeKey.get());
            textAreaEncoded.setValue(textCoded);
        });

        Button buttonDecoder = elementBuilder.makeButton("Decode");
        buttonDecoder.addClickListener(clickEvent -> {
            String textDecoded = decoder.decodeText(textAreaCoded.getValue(), personalKey.getValue());
            textAreaDecoded.setValue(textDecoded);
        });

        Button buttonCopy1 = elementBuilder.makeButton("Copy to clipboard");
        buttonCopy1.addClickListener(
                e -> UI.getCurrent().getPage().executeJs("navigator.clipboard.writeText(($0));",
                        textAreaEncoded.getValue()));

        Button buttonCopy2 = elementBuilder.makeButton("Copy to clipboard");
        buttonCopy2.addClickListener(
                e -> UI.getCurrent().getPage().executeJs("navigator.clipboard.writeText(($0));", textAreaDecoded.getValue()));

        Button buttonClean1 = elementBuilder.makeButton("Clear text");
        buttonClean1.addClickListener(
                buttonClickEvent -> {textAreaNormal.setValue("");
                    textAreaEncoded.setValue("");
                });

        Button buttonClean2 = elementBuilder.makeButton("Clear text");
        buttonClean2.addClickListener(
                buttonClickEvent -> {textAreaCoded.setValue("");
                    textAreaDecoded.setValue("");
                });

        Button buttonKey = elementBuilder.makeButton("Set Key");
        buttonKey.addClickListener(
                buttonClickEvent -> {
                    if(buttonKey.getText().equals("Set Key")){
                        keyField.setReadOnly(false);
                        buttonKey.setText("Generate Random Key");
                    }else {
                        keyField.setReadOnly(true);
                        buttonKey.setText("Set Key");
                    }
                    keyField.setValue("");
                });

        verticalLayoutButtons1.add(buttonCoder,buttonClean1);
        verticalLayoutButtons2.add(buttonDecoder,buttonClean2);

        verticalLastLayout1.add(buttonCopy1, keyField);
        verticalLastLayout2.add(buttonCopy2, personalKey);

        horizontalLayout.add(textAreaNormal, verticalLayoutButtons1, textAreaEncoded, verticalLastLayout1);
        horizontalLayout2.add(textAreaCoded, verticalLayoutButtons2, textAreaDecoded, verticalLastLayout2);
        horizontalLayout3.add("Click here to switch to the key type you want: ");
        horizontalLayout3.add(buttonKey);
        verticalLayout.add(heading,horizontalLayout, horizontalLayout2,horizontalLayout3);

        verticalLayout.setAlignItems(Alignment.CENTER);

    }
}