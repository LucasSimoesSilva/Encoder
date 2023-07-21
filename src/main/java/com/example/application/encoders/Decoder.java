package com.example.application.encoders;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decoder {
    public static String decodeSubText(String text, String key){
//        String alfabeto = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ'\"!@#$%&*()_-+=[]{},.;:/?\\1234567890";
        String alphabet = key;
        char c;
        StringBuilder soma = new StringBuilder();
        String regex;


        for (int i = 0; i <alphabet.length() ; i++) {
            String character = String.valueOf(alphabet.charAt(i));
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
                c = alphabet.charAt(somaMat - 1);
                soma.append(c);
            }catch (StringIndexOutOfBoundsException e){
                break;
            }

        }
        return soma.toString();
    }


    public static String decodeText (String codedText, String key){
        List<StringBuilder> listToDecode = divideString(codedText);
        StringBuilder finalText = new StringBuilder("");
        for (StringBuilder text : listToDecode) {
            String textDecoded = decodeSubText(text.toString(),key);
            finalText.append(textDecoded);
        }
        return finalText.toString();
    }

    public static List<StringBuilder> divideString(String input) {
        List<StringBuilder> substrings = new ArrayList<>();

        int startIndex = 0;
        int endIndex;

        if (!input.substring(input.length()-1).equals("~")){
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
