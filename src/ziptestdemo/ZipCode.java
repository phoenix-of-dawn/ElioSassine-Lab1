/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ziptestdemo;

/**
 *
 * @author Elio Sassine - 2493512
 * Lab-01: ZipCode
 * 8/27/2025
 */
public class ZipCode {
    public int Zip;
    
    // This is reversed because the GetBarCode function reverses it back
    private static final String[] reversedZipToBarcodeMap = {
        "00011", 
        "11000", 
        "10100", 
        "01100", 
        "10010", 
        "01010", 
        "00110",
        "10001",
        "01001",
        "00101"
    };
            
    public ZipCode(int zipCode) {
        if (zipCode > 99999 || zipCode < 0) {
            System.out.println("Error: Invalid zip code. " + zipCode + " is more than 5 digits.");
        }
        
        this.Zip = zipCode;
    }
    
    public ZipCode(String barCode) {
        if (barCode.length() != 27 
                || barCode.charAt(0) != '1' 
                || barCode.charAt(barCode.length() - 1) != '1') 
        {
            System.out.println("Error: Invalid barcode string. " + barCode + " is missing the leading 1s.");
        }
        
        this.Zip = parseBarCode(barCode);
    }
    
    /**
     * Gets the bar code based on the zip code
     * @return a bar code that is calculated based off the zip code
     */
    public String GetBarCode() {
        // Construct the barCode in reverse
        String reverseBarCode = "1";
        int ctr = 0;
        for (int tempZip = this.Zip; tempZip > 0; tempZip /= 10) {
            int currentDigit = tempZip % 10;
            reverseBarCode += reversedZipToBarcodeMap[currentDigit];
            ctr++;
        }
        
        for (int i = 0; i < 5 - ctr; i++) {
            reverseBarCode += reversedZipToBarcodeMap[0];
        }
        
        reverseBarCode += "1";
        
        // Reverse it
        String barCode = "";
        for (int i = reverseBarCode.length() - 1; i >= 0; i--) {
            barCode += reverseBarCode.charAt(i);
        }
        return barCode;
    }
    
    /**
     * Parses the bar code into a zip code
     * @param barCode the bar code to parse
     * @return the zip code based off the bar code
     */
    public static int parseBarCode(String barCode) {
        // Assume barcode is well-formed as the constructor handles the checks
        barCode = barCode.substring(1, 26);
        int result = 0;
        for (int i = 0; i < barCode.length() / 5; i++) {
            String numberInBinary = barCode.substring(i * 5, i * 5 + 5);
            
            int power = 1;
            for (int j = 0; j < barCode.length() / 5 - i - 1; j++) {
                power *= 10;
            }
            
            switch (numberInBinary) {
                case "11000" -> result += 0 * power;
                case "00011" -> result += 1 * power;
                case "00101" -> result += 2 * power;
                case "00110" -> result += 3 * power;
                case "01001" -> result += 4 * power;
                case "01010" -> result += 5 * power;
                case "01100" -> result += 6 * power;
                case "10001" -> result += 7 * power;
                case "10010" -> result += 8 * power;
                case "10100" -> result += 9 * power;
                default -> System.out.println("Error: parsing failed on " + numberInBinary);
            }
        }
        
        return result;
    } 
}
