package com.example.application.encoders;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decoder {
    private String decodeSubText(String text){
        String alfabeto = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ'\"!@#$%&*()_-+=[]{},.;:/?\\1234567890";
        char c;
        StringBuilder soma = new StringBuilder();
        String regex;


        for (int i = 0; i <alfabeto.length() ; i++) {
            String character = String.valueOf(alfabeto.charAt(i));
            int somaMat = 0;

            regex = switch (character) {
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


            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);


            while (matcher.find()){
                somaMat += -matcher.start() + matcher.end();
            }

            try {
                c = alfabeto.charAt(somaMat - 1);
                soma.append(c);
            }catch (StringIndexOutOfBoundsException e){
                break;
            }

        }
        return soma.toString();
    }


    public String decodeText (String codedText){
        List<StringBuilder> listToDecode = divideString(codedText);
        StringBuilder finalText = new StringBuilder("");
        for (StringBuilder text : listToDecode) {
            String textDecoded = decodeSubText(text.toString());
            finalText.append(textDecoded);
        }
        return finalText.toString();
    }

    private List<StringBuilder> divideString(String input) {
        List<StringBuilder> substrings = new ArrayList<>();

        int startIndex = 0;
        int endIndex;

        if (!input.endsWith("~")){
            input += "~";
        }

        while ((endIndex = input.indexOf('~', startIndex)) != -1) {
            String substring = input.substring(startIndex, endIndex);
            substrings.add(new StringBuilder(substring));
            startIndex = endIndex + 1;
        }

        return substrings;
    }
}
