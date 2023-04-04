package com.example.cosc411realm;

public class Decrypt {
    String encryptedMsg= "";
    String decryptedMsg = "";

    int stringLength = 0;
    int sumOfAsciiValues=0;
    int chosenCharPosition=0;
    int chosenCharValue = 0;

    final int ASCII_MAX_VALUE = 127;

    public Decrypt(String message, int sSumOfAsciiValues, int sChosenCharPosition, int sChosenCharValue) {
        this.encryptedMsg = message;
        this.stringLength = message.length();
        this.sumOfAsciiValues = sSumOfAsciiValues;
        this.chosenCharPosition = sChosenCharPosition;
        this.chosenCharValue = sChosenCharValue;

        this.decryptedMsg = assembleDecryptedChar();
    }

    public String getDecryptedMsg()
    {
        return decryptedMsg;
    }

    private String assembleDecryptedChar()
    {
        String decryptedString = "";

        for (int i =0; i<this.stringLength; i++)
        {
            decryptedString += decryptChar(i);
        }

        return decryptedString;
    }

    private char decryptChar(int charPosition) {

        int charValue = -1;
        int equation;

        int Y = this.sumOfAsciiValues;
        int Z = this.chosenCharPosition;
        int W = this.chosenCharValue;
        int A = (int)this.encryptedMsg.charAt(charPosition);
        int B = this.stringLength;
        int C = this.ASCII_MAX_VALUE;

        for (int i=0; i<this.ASCII_MAX_VALUE; i++)
        {
            equation = (((Y%B) + Z*(i+W))%C)-A;

            if ( equation == 0)
            {
                charValue = i;
            }

            if(i==this.ASCII_MAX_VALUE-1 & charValue==-1)
                System.out.println("ERROR");
        }



        return (char) (charValue);
    }
}