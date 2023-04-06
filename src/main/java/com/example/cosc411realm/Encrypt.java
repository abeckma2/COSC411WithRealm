package com.example.cosc411realm;

public class Encrypt {
    String msg= "";
    String encryptedMsg = "";

    int stringLength = 0;
    int sumOfAsciiValues=0;
    int chosenCharPosition=0;
    int chosenCharValue = 0;

    final int ASCII_MAX_VALUE = 127;

    public Encrypt(String message)
    {
        this.msg = message;
        this.stringLength = message.length();
        this.sumOfAsciiValues = calculateAsciiSum();

        this.chosenCharPosition = this.sumOfAsciiValues % this.stringLength;
        if (this.chosenCharPosition==0)
            this.chosenCharPosition++;

        this.chosenCharValue = (int)this.msg.charAt(this.chosenCharPosition);

        this.encryptedMsg = assembleEncryptedChar();
    }

    public String getEncryptedMsg() {

        return this.encryptedMsg;
    }

    int calculateAsciiSum()
    {
        int sum=0;

        for (int i=0; i<this.stringLength; i++)
        {
            sum+=(int)this.msg.charAt(i);
        }

        return sum;
    }

    String assembleEncryptedChar()
    {
        String encryptedString = "";

        for (int i=0; i<this.stringLength; i++)
        {
            encryptedString += encryptChar(i);
        }

        return encryptedString;
    }

    char encryptChar(int charPosition)
    {
        int test = (int)this.msg.charAt(charPosition);
        int charValue = ((this.sumOfAsciiValues % this.stringLength) + this.chosenCharPosition * ((int)this.msg.charAt(charPosition) + this.chosenCharValue)) % this.ASCII_MAX_VALUE;
        return (char) charValue;
    }
}