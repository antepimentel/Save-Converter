package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    private static final String EXT_SAV = "sav";
    private static final String EXT_SRM = "srm";

    public static void main(String[] args) throws IOException {

        if(args.length == 0){
            System.out.println("Please provide an input file path.");
            return;
        }

        String inputPath = args[0];
        System.out.println(inputPath);
        String ext = getFileExtension(inputPath);

        Path inputLocation = Paths.get(inputPath).toAbsolutePath().normalize();
        byte[] data = Converter.readByte(inputLocation);
        String hexString = Converter.byteToHex(data);
        String newHexString = "";
        String outputPath = "";

        switch(ext.toLowerCase()){
            case EXT_SAV:
                newHexString = Converter.savToSrm(hexString);
                outputPath = changeFileExtension(inputPath, EXT_SRM);
                break;
            case EXT_SRM:
                newHexString = Converter.srmToSav(hexString);
                outputPath = changeFileExtension(inputPath, EXT_SAV);
                break;
            default:
                System.out.println("Unsupported file type: "+ext);
                return;
        }

        byte[] newData = Converter.hexToByte(newHexString);
        FileOutputStream out = new FileOutputStream(outputPath);
        out.write(newData);
        out.close();
    }

    private static String getFileExtension(String path){
        String[] tokens = path.split("\\.");
        return tokens[tokens.length-1];
    }

    private static String changeFileExtension(String path, String ext){
        String[] tokens = path.split("\\.");
        tokens[tokens.length-1] = ext;

        String result = "";
        for(String s: tokens){

            if(result.isEmpty()){
                result = s;
            } else{
                result = result + "." + s;
            }
        }
        return result;
    }
}
