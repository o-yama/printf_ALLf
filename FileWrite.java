

import java.io.BufferedOutputStream;
//import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by e145735 on 2015/11/30.
 */

public class FileWrite {

    static int size;
    static boolean buffer_flag;
    static boolean help_flag=false;
    static String outputFile = "outputFile";

    public static void main(String args[]) throws IOException {
        long start = System.currentTimeMillis();
        getopts(args);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    public static void getopts(String[] args) throws  IOException {
        if (args.length == 3) {
            if (args[1].equals("--size") && checkStringNumber(args[2])) {
                size = Integer.parseInt(args[2]);
                if (args[0].equals("-b")) {
                    buffer_flag = true;
                    System.out.println("buffer");
                    BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(outputFile));
                    for (int i = 0; i < size; i++) {
                        os.write(97);
                    }
                    os.close();
                }
                else if (args[0].equals("-u")) {
                    buffer_flag = false;
                    System.out.println("unbuffer");
                    FileOutputStream os = new FileOutputStream(outputFile);
                    for (int i = 0; i < size; i++) {
                        os.write(97);
                    }
                    os.close();
                }
            }
        }

        else if (args.length == 1 && (args[0].equals("-h")||args[0].equals("--help"))) {
            System.out.println("Usage");
            System.out.println("-u        :unbuffered write");
            System.out.println("-b        :buffered write");
            System.out.println("--help,-h :show this");
            System.out.println("--size    :write file size");
            help_flag = true;
        }
        else
            System.out.println("ERROR!");
    }


    public static int getWriteSize(){
        return (size);
    }

    public static boolean isHelped(){
        return (help_flag);
    }

    public static boolean isBuffered(){
        return(buffer_flag);
    }

    public static boolean checkStringNumber(String number) {
        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(number);
        return m.find();
    }
}
