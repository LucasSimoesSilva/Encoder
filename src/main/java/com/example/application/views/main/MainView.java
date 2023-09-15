package com.example.application.views.main;

import com.example.application.Util.Verifier;
import com.example.application.builder.HtmlElementBuilder;
import com.example.application.encoders.Decoder;
import com.example.application.encoders.Encoder;
import com.example.application.entity.InvalidCharacters;
import com.example.application.entity.TextEncoder;
import com.example.application.entity.VerifyText;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
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
        AtomicReference<String> textDescribeKeyAtomic = new AtomicReference<>();
        Text textDescribeKey = new Text("Click here to set your own code key: ");

        TextArea missingCharacters = elementBuilder.makeTextArea("Missing characters",
                "The characters that is missing in your personal code key", "200px");
        missingCharacters.setReadOnly(true);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setAlignItems(FlexComponent.Alignment.BASELINE);

        HorizontalLayout horizontalLayout2 = new HorizontalLayout();
        horizontalLayout2.setAlignItems(FlexComponent.Alignment.BASELINE);

        HorizontalLayout horizontalLayout3 = new HorizontalLayout();
        horizontalLayout3.setAlignItems(FlexComponent.Alignment.BASELINE);

        HorizontalLayout horizontalLayout4 = new HorizontalLayout();
        horizontalLayout4.setAlignItems(FlexComponent.Alignment.BASELINE);

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
        keyField.setLabel("Auto-generate Code key");
        keyField.setReadOnly(true);

        TextField personalKey = new TextField();
        personalKey.setLabel("Your Code Key");

        Button buttonKey = elementBuilder.makeButton("Set your own Code Key");
        buttonKey.addClickListener(
                buttonClickEvent -> {
                    if (buttonKey.getText().equals("Set your own Code Key")) {
                        keyField.setReadOnly(false);
                        buttonKey.setText("Generate Random Key");
                        textDescribeKeyAtomic.set("Click here to set the auto-generate code key: ");
                        textDescribeKey.setText(textDescribeKeyAtomic.get());
                        keyField.setLabel("Your own Code Key");
                    } else {
                        keyField.setReadOnly(true);
                        buttonKey.setText("Set your own Code Key");
                        textDescribeKeyAtomic.set("Click here to set your own code key: ");
                        textDescribeKey.setText(textDescribeKeyAtomic.get());
                        keyField.setLabel("Auto-generate Code key");
                    }
                    keyField.setValue("");

                    if(!keyField.isReadOnly()){
                        Verifier verifier = new Verifier();
                        keyField.setValueChangeMode(ValueChangeMode.EAGER);
                        keyField.addValueChangeListener(event -> {
                            boolean flag = verifier.verifyCharactersInText(keyField.getValue(), textAreaNormal.getValue()).isFlag();
                            if (!flag) {
                                keyField.setHelperText("Your key doesn't have all characters of the text.");
                            } else {
                                keyField.setHelperText("");
                                missingCharacters.setValue("");
                            }
                            missingCharacters.setValue(verifier.verifyCharactersInText(keyField.getValue(), textAreaNormal.getValue())
                                    .getInvalidCharacters().toString());
                        });
                    }else {
                        keyField.setHelperText("");
                        missingCharacters.setValue("");
                    }
                });

        Button buttonCoder = elementBuilder.makeButton("Code");
        buttonCoder.addClickListener(clickEvent -> {
            boolean random = true;
            if (!keyField.getValue().equals("") && buttonKey.getText().equals("Generate Random Key")) {
                alphabet = keyField.getValue();
                random = false;
            }

            VerifyText verifyText = encoder.codeText(textAreaNormal.getValue(), alphabet, random);
            boolean confirmation = verifyText.isTextConfirmed();
            if (confirmation) {
                TextEncoder textEncoder = verifyText.getTextEncoder();
                String textCoded = textEncoder.getText();
                codeKey.set(textEncoder.getKey());
                keyField.setValue(codeKey.get());
                textAreaEncoded.setValue(textCoded);
            } else {
                elementBuilder.createErrorNotification(verifyText.getMessageError());
            }
        });

        Button buttonDecoder = elementBuilder.makeButton("Decode");
        buttonDecoder.addClickListener(clickEvent -> {
            String textDecoded = decoder.decodeText(textAreaCoded.getValue(), personalKey.getValue());
            textAreaDecoded.setValue(textDecoded);
        });



        Button buttonCopyKey1 = elementBuilder.makeButton("Copy Code Key");
        buttonCopyKey1.addClickListener(
                e -> UI.getCurrent().getPage().executeJs("navigator.clipboard.writeText(($0));",
                        keyField.getValue()));

        Button buttonCopyKey2 = elementBuilder.makeButton("Copy Code Key");
        buttonCopyKey2.addClickListener(
                e -> UI.getCurrent().getPage().executeJs("navigator.clipboard.writeText(($0));",
                        personalKey.getValue()));

        Button buttonCopy1 = elementBuilder.makeButton("Copy to clipboard");
        buttonCopy1.addClickListener(
                e -> UI.getCurrent().getPage().executeJs("navigator.clipboard.writeText(($0));",
                        textAreaEncoded.getValue()));

        Button buttonCopy2 = elementBuilder.makeButton("Copy to clipboard");
        buttonCopy2.addClickListener(
                e -> UI.getCurrent().getPage().executeJs("navigator.clipboard.writeText(($0));", textAreaDecoded.getValue()));

        Button buttonClean1 = elementBuilder.makeButton("Clear text");
        buttonClean1.addClickListener(
                buttonClickEvent -> {
                    textAreaNormal.setValue("");
                    textAreaEncoded.setValue("");
                });

        Button buttonClean2 = elementBuilder.makeButton("Clear text");
        buttonClean2.addClickListener(
                buttonClickEvent -> {
                    textAreaCoded.setValue("");
                    textAreaDecoded.setValue("");
                });


        verticalLayoutButtons1.add(buttonCoder, buttonClean1);
        verticalLayoutButtons2.add(buttonDecoder, buttonClean2);

        verticalLastLayout1.add(buttonCopy1, keyField);
        verticalLastLayout2.add(buttonCopy2, personalKey);

        horizontalLayout.add(textAreaNormal, verticalLayoutButtons1, textAreaEncoded, verticalLastLayout1, buttonCopyKey1);
        horizontalLayout2.add(textAreaCoded, verticalLayoutButtons2, textAreaDecoded, verticalLastLayout2, buttonCopyKey2);
        horizontalLayout3.add(textDescribeKey);
        horizontalLayout3.add(buttonKey);
        horizontalLayout4.add();
        verticalLayout.add(heading, horizontalLayout, horizontalLayout2, horizontalLayout3, horizontalLayout4, missingCharacters);

        verticalLayout.setAlignItems(Alignment.CENTER);

    }
}