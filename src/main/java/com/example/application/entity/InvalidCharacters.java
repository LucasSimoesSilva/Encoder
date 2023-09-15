package com.example.application.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class InvalidCharacters {
    private boolean flag;
    private List<Character> characters = new ArrayList<>();

    public InvalidCharacters(boolean flag, List<Character> characters) {
        this.flag = flag;
        this.characters = characters;
    }

    public InvalidCharacters() {
    }

    public StringBuilder getInvalidCharacters(){
        StringBuilder invalidCharacters = new StringBuilder();
        StringJoiner concatCharacters = new StringJoiner(",");
        for (Character c: characters){
            concatCharacters.add(c.toString());
        }
        invalidCharacters.append(concatCharacters);
        return invalidCharacters;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
