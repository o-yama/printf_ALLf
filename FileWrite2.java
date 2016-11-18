package jp.ac.uryukyu.ie.e155730;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by e155730 on 11/1/16.
 */


public class FileWrite {

    public boolean isBuffered(){
        return buffered;
    }

    public int getWriteSize(){
        return  writeSize;
    }

    private boolean buffered;
    private int bufferSize = 4096;
    private int writeSize = 4096 * 100;
    private String filename = "output.txt";

    public static void main(String args[]) throws IOException {
        FileWrite fileWrite = new FileWrite();
        fileWrite.getOpts(args);
        fileWrite.fileWrite();

    }

    public void getOpts(String[] args){
        for (int i=0 ; i< args.length; i++){
            String arg = args[i];
            if(arg.equals("--help")||arg.equals("-h")){
                showHelp();
            } else if(arg.equals("-b")) {
                if(++i < args.length) {
                    bufferSize = Integer.parseInt(args[i]);
                }
                buffered = true;
            } else if(arg.equals("-u")){
                buffered = false;
            } else if (arg.equals("-s")||arg.equals("--size")){
                if(++i < args.length){
                    writeSize = Integer.parseInt(args[i]);
                }
            }
        }
    }


    public void showHelp(){
        System.out.print("-b:buffered write mode ,-u:unbuffered write mode -s,");
        System.out.println("--size max filesize --helo,-h:show help");
    }

    public void fileWrite() throws IOException {


        if(buffered){
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(filename),bufferSize);

            long start = System.nanoTime();


            for(int i=0;i<writeSize;i++){
                os.write(0x20);
            }

            os.close();

            long end = System.nanoTime();

            System.out.println(end - start );

        }else{
            FileOutputStream os = new FileOutputStream(filename);

            long start = System.nanoTime();


            for(int i=0;i< writeSize;i++){
                os.write(0x20);
            }

            os.close();

            long end = System.nanoTime();

            System.out.println(end - start );

        }



    }
}
