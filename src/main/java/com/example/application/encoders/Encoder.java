package com.example.application.encoders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Encoder {

    String alertMessage = "Only english characters. Pipe is not accept";

    private String codeSubTexts(String text) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ'\"!@#$%&*()_-+=[]{},.;:/?\\1234567890";
        StringBuilder sum = new StringBuilder();

        String letter;


        for (int i = 0; i < text.length(); i++) {

            String character = String.valueOf(text.charAt(i));


            if(!alphabet.contains(character)){
                return alertMessage;
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
            Matcher matcher = pattern.matcher(alphabet);

            while (matcher.find()) {
                for (int j = 0; j < matcher.start() + 1; j++) {
                    char c = alphabet.charAt(i);
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
        return builder.append(randomText).toString();
    }

    public String codeText(String text) {
        int maxLenght = 89;
        String subText = "";
        List<String> stringsList = new ArrayList();
        List<String> encodedList = new ArrayList<>();
        String subCodeText;
        StringBuilder encodedText = new StringBuilder();

        for (int i = 0; i < text.length(); i += maxLenght) {
            int endIndex = Math.min(i + maxLenght, text.length());
            subText = text.substring(i, endIndex);
            stringsList.add(subText);
        }

        for (String s : stringsList) {
            subCodeText = codeSubTexts(s);
            encodedList.add(subCodeText);
        }

        for (String encodedParts : encodedList) {
            encodedText.append(encodedParts);
            if (!(encodedList.size() == 1 && encodedParts.equals(alertMessage))){
                encodedText.append("~");
            }
        }


        return encodedText.toString();
    }

    public static String shuffle(String s) {
        List<Character> letters = s.chars().boxed().map(c -> (char) c.intValue()).collect(Collectors.toList());
        Collections.shuffle(letters);
        StringBuilder t = new StringBuilder(s.length());
        letters.forEach(t::append);
        return t.toString();
    }
}
