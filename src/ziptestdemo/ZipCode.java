/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ziptestdemo;

/**
 *
 * @author Elio Sassine - 2493512
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
            System.out.println("Error: Invalid zip code");
            return;
        }
        
        this.Zip = zipCode;
    }
    
    public ZipCode(String barCode) {
        if (barCode.length() != 27) {
            System.out.println("Error: Invalid barcode string");
            return;
        }
        
        // TODO: Convert to int
    }
    
    public String GetBarCode() {
        String reverseBarCode = "1";
        for (int tempZip = this.Zip; tempZip > 0; tempZip /= 10) {
            int currentDigit = tempZip % 10;
            reverseBarCode += reversedZipToBarcodeMap[currentDigit];
        }
        reverseBarCode += "1";
        
        String barCode = "";
        for (int i = barCode.length() - 1; i >= 0; i--) {
            barCode += reverseBarCode.charAt(i);
        }
        return barCode;
    }
}
