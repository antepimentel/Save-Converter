package main;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Converter {

    private Converter(){

    }

    /**
     * Fill a byte[] from a file at specified path (file).
     *
     * @param file
     *            The path to the file being read
     *
     * @throws IOException
     * @return temporary
     */
    public static byte[] readByte(Path file) throws IOException {

        byte[] temporary = Files.readAllBytes(file);

        return temporary;
    }

    /**
     * Convert byte[] into a String of Hex values.
     *
     * @param data
     *            byte[] of read information from a file.
     *
     * @return temporary
     */
    public static String byteToHex(byte[] data) {

        java.lang.String temporary = DatatypeConverter.printHexBinary(data);

        return temporary;
    }

    /**
     * Convert String of Hex values into byte[].
     *
     * @param hexString
     *            String of hex values
     *
     * @return temporary
     */
    public static byte[] hexToByte(String hexString) {

        byte[] temporary = DatatypeConverter.parseHexBinary(hexString);

        return temporary;
    }

    /**
     * Convert .srm to .sav format by removing appropriate empty data.
     *
     * @param hexString
     *            String of hex values
     *
     * @return hexString
     */
    public static String srmToSav(String hexString) {

        int charsToRemove = 16384;

        //Update hexString as a substring of itself, removing the excess data
        hexString = hexString.substring(0, hexString.length() - charsToRemove);

        return hexString;
    }

    /**
     * Convert .sav to .srm format by adding appropriate empty data.
     *
     * @param hexString
     *            String of hex values
     *
     * @return hexString
     */
    public static String savToSrm(String hexString) {

        int charsToAdd = 16384;

        //Loop to add appropriate data to hex String.
        for (int i = 0; i < charsToAdd; i++) {
            hexString += 'f';
        }

        return hexString;
    }

    /**
     * Print String of Hex values as they would appear in a hex editor, this
     * method is purely for debugging purposes. Although it will be included in
     * the release it will not be used.
     *
     * @param hexString
     *            String of hex values
     */
    public static void printHexData(String hexString) {

        //Print out all characters within hexChars
        for (int i = 0; i < hexString.length(); i++) {

            //Make a space every two chars
            if ((i % 2 == 0) && (i != 0)) {
                System.out.print(' ');
            }

            //Go down a line after every 32 chars
            if ((i % 32 == 0) && (i != 0)) {
                System.out.println();
            }

            System.out.print(hexString.charAt(i));
        }
    }
}
