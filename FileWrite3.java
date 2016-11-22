
import java.io.*;

/*****
 */

public class FileWrite {
    private int bufferSize=1024;
    private int writeSize=1024;
    //writeSize_update = writeSize * count
    private int writeSize_update=  1024;
    private boolean buffered = true;

    private String filename = "output.txt";
    private String datafile = "data.txt";

    // judge data format datafile
    private boolean first_write=true;
    //count is loop counts and test counts.
    private int count = 1;
    private long time;

    public boolean isBuffered() {
        return buffered;
    }

    public long getWriteSize(){
        return writeSize;
    }

    public static void main(String args[]) throws IOException {

        FileWrite fw = new FileWrite();
        fw.getOpts(args);
        for(int i=1; i <= fw.count; i++) {
            fw.writeSize_update=fw.writeSize * i;
            long start = System.currentTimeMillis();
            //
            fw.fileWrite();
            long end = System.currentTimeMillis();
            fw.time = end - start;
            System.out.println(fw.writeSize_update + " " + fw.time);
            fw.outputData();
        }
    }

    public void getOpts(String[] args) throws IOException {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.equals("-h")||arg.equals("--help")) {
                System.out.println("Usage:");
                System.out.println("[-a]              : add result datafile");
                System.out.println("[-b] (int)        : buffered write");
                System.out.println("[-c|--count] (int): loop counts");
                System.out.println("[-h|--help]       : show this");
                System.out.println("[-s|--size] (int) : write file size");
                System.out.println("[-u]              : unbuffered write");
            } else if (arg.equals("-a")) {
                first_write = false;
            } else if (arg.equals("-b")) {
                buffered = true;
                if (++i < args.length) {
                    bufferSize = Integer.parseInt(args[i]);
                }
            } else if (arg.equals("-u")) {
                buffered = false;
            } else if (arg.equals("-s")||arg.equals("--size")) {
                if (++i < args.length) {
                    writeSize = Integer.parseInt(args[i]);
                }
            }else if (arg.equals("-c")||arg.equals("--count")) {
                if (++i < args.length) {
                    count = Integer.parseInt(args[i]);
                }
            } else {
                System.out.println("not options: try add option '-h' or '--help' for more information");
            }
        }
    }

    public void fileWrite() throws IOException{
        // ファイル(の名前)を指定
        File file = new File(filename);
        // ファイルを書き出すためのclassを指定
        OutputStream os = new FileOutputStream(file);
        if(buffered){
            os = new BufferedOutputStream(os, bufferSize);
        }
        for(int i=0;i<writeSize_update;i++){
            os.write(0x30);
        }
        os.close();
    }

    public void outputData()throws IOException{
        File file = new File(datafile);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file,!first_write));
        if(first_write) first_write = !first_write;
        bw.write(String.valueOf(writeSize_update) + " " + String.valueOf(time));
        bw.newLine();
        bw.close();
    }
}
