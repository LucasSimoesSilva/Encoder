package com.example.application.builder;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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

    @Override
    public void createErrorNotification(String text) {
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        Div textMessage = new Div(new Text(text));

        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(event -> {
            notification.close();
        });

        HorizontalLayout layout = new HorizontalLayout(textMessage, closeButton);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        notification.add(layout);
        notification.open();
    }
}
