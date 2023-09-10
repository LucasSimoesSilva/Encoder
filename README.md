# Encoder and Decoder

This software can encode or decode English texts with an encryption
created by me

## Explanation
  - The encoding software works based on a key that will serve as the basis for creating the encoding.
  - If you want to create your own key, it will need to have all the characters present in the text.
    - Example:
      - Code Key: "auLc", Text for encoding: "Lucas the good"
        - In this example an error will occur, because the key does not contain the character "s"," ","t","h","e","g","o","d" and the text does.
      - Code Key: "cLsau hte zymal", Text for encoding: "Luca the"
        - However, in this example everything will go well, as there is no problem with the code key having characters that the text does not have.
  - In this software, all characters are counted, including spaces. Below you will find the characters allowed in the auto-generate key mode and the characters not accepted in any of the encryption modes.
    - Allowed caracteres in the auto-generated key mode: abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ'"!@#$%&*()_-+=[]{},.;:/?\1234567890
    - Characters not accepted: "|"(pipe), "~"(tilde)
<hr>

- Operational System:
    - Windows 10
<br>

## Technologies

- Language: Java JDK 17.0.3.1
- IDE: IntelliJ IDEA 2022.2.3 (Ultimate Edition)
- Auxiliary technologies: Vaadin 24.0.5
