package com.example.application.views.main;

import com.example.application.builder.HtmlElementBuilder;
import com.example.application.encoders.Decoder;
import com.example.application.encoders.Encoder;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        Encoder encoder = new Encoder();
        Decoder decoder = new Decoder();
        HtmlElementBuilder elementBuilder = new HtmlElementBuilder();

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setAlignItems(FlexComponent.Alignment.BASELINE);

        HorizontalLayout horizontalLayout2 = new HorizontalLayout();
        horizontalLayout2.setAlignItems(FlexComponent.Alignment.BASELINE);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        VerticalLayout verticalLayoutButtons1 = new VerticalLayout();
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        VerticalLayout verticalLayoutButtons2 = new VerticalLayout();
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

        Button buttonCoder = elementBuilder.makeButton("Code");
        buttonCoder.addClickListener(clickEvent -> {
            String textCoded = encoder.codeText(textAreaNormal.getValue());
            textAreaEncoded.setValue(textCoded);
        });

        Button buttonDecoder = elementBuilder.makeButton("Decode");
        buttonDecoder.addClickListener(clickEvent -> {
            String textDecoded = decoder.decodeText(textAreaCoded.getValue());
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

        verticalLayoutButtons1.add(buttonCoder,buttonClean1);
        verticalLayoutButtons2.add(buttonDecoder,buttonClean2);

        horizontalLayout.add(textAreaNormal, verticalLayoutButtons1, textAreaEncoded,buttonCopy1);
        horizontalLayout2.add(textAreaCoded, verticalLayoutButtons2, textAreaDecoded, buttonCopy2);
        verticalLayout.add(heading,horizontalLayout, horizontalLayout2);

        verticalLayout.setAlignItems(Alignment.CENTER);

    }
}