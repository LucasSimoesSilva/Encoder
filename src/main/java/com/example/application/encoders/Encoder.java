package com.example.application.encoders;

import com.example.application.entity.TextEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Encoder {

    String alertMessage = "Only english characters. Pipe is not accept";

    public static TextEncoder codeSubTexts(String text, String alphabet) {
        TextEncoder textEncoder = new TextEncoder();
        String key = shuffle(alphabet);
        StringBuilder sum = new StringBuilder();

        String letter;

        for (int i = 0; i < text.length(); i++) {

            String character = String.valueOf(text.charAt(i));


            if(!key.contains(character)){
                textEncoder.setText("Only english characters. Pipe is not accept");
                textEncoder.setKey("");
                return textEncoder;
            }


            letter = switch (character) {
                case "[" -> "\\[";
                case "]" -> "\\]";
                case "." -> "\\.";
                case "$" -> "\\$";
                case "*" -> "\\*";
                case "(" -> "\\(";
                case ")" -> "\\)";
                case "{" -> "\\{";
                case "}" -> "\\}";
                case "+" -> "\\+";
                case "?" -> "\\?";
                case "\\" -> "\\\\";
                case "%" -> "\\%";
                case "'" -> "\\'";
                default -> character;
            };


            Pattern pattern = Pattern.compile(letter);
            Matcher matcher = pattern.matcher(key);

            while (matcher.find()) {
                for (int j = 0; j < matcher.start() + 1; j++) {
                    char c = key.charAt(i);
                    sum.append(c);
                }

            }

        }

        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < sum.length(); i++) {
            index.add(i);
        }
        Collections.shuffle(index);

        char[] characters = sum.toString().toCharArray();
        char[] randomText = new char[sum.length()];
        for (int i = 0; i < sum.length(); i++) {
            randomText[i] = characters[index.get(i)];
        }

        StringBuilder builder = new StringBuilder();
        textEncoder.setText(builder.append(randomText).toString());
        textEncoder.setKey(key);
        return textEncoder;
    }

    public static TextEncoder codeText(String text, String alphabet) {
        int maxLenght = alphabet.length();
        String subText = "";
        List<String> stringsList = new ArrayList<>();
        List<String> encodedList = new ArrayList<>();
        TextEncoder subCodeText;
        String key = "";
        StringBuilder encodedText = new StringBuilder();

        for (int i = 0; i < text.length(); i += maxLenght) {
            int endIndex = Math.min(i + maxLenght, text.length());
            subText = text.substring(i, endIndex);
            stringsList.add(subText);
        }

        for (String s : stringsList) {
            subCodeText = codeSubTexts(s,alphabet);
            key = subCodeText.getKey();
            encodedList.add(subCodeText.getText());
        }

        for (String encodedParts : encodedList) {
            encodedText.append(encodedParts).append("~");
        }


        return new TextEncoder(encodedText.toString(), key);
    }

    public static String shuffle(String s) {
        List<Character> letters = s.chars().boxed().map(c -> (char) c.intValue()).collect(Collectors.toList());
        Collections.shuffle(letters);
        StringBuilder t = new StringBuilder(s.length());
        letters.forEach(t::append);
        return t.toString();
    }
}
