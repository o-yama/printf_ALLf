
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 */
/*I/O(入出力)関係の例外処理*/

public class FileWrite2 {

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
        FileWrite2 fileWrite = new FileWrite2();     
        /*FileWrite2のclass全体をfileWriteという名前にしてる*/
        fileWrite.getOpts(args);       //それぞれ実行
        fileWrite.fileWrite();              

    }

/*args.lengthの数だけ処理を行う. -s など一致すればその処理を行う. */


    public void getOpts(String[] args){ 
        for (int i=0 ; i< args.length; i++){    //args.length:引数の数
            String arg = args[i];
            if(arg.equals("--help")||arg.equals("-h")){
                showHelp();
            } else if(arg.equals("-b")) {
                if(++i < args.length) {
                    /*引数に指定された文字列を整数の値として解析してint型として返す
                    bufferSize = (-b の次の文字列の数)*/
                    bufferSize = Integer.parseInt(args[i]);
                }
                buffered = true;
            } else if(arg.equals("-u")){
                buffered = false;   //-uと-bはオプション?
            } else if (arg.equals("-s")||arg.equals("--size")){
                if(++i < args.length){
                    /*-s の後の指定した数値がwriteSizeになる*/
                    writeSize = Integer.parseInt(args[i]);
                }
            }
        }
    }

/*使い方の説明*/

    public void showHelp(){
        System.out.print("-b:buffered write mode ,-u:unbuffered write mode -s,");
        System.out.println("--size max filesize --helo,-h:show help");
    }


    public void fileWrite() throws IOException {

    //bufferedだった場合
        if(buffered){
            /*BufferedOutputStream, */
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(filename),bufferSize);

            long start = System.nanoTime();


            for(int i=0;i<writeSize;i++){
                os.write(0x20);                     /*引数で指定したwriteSizeの文だけ書込み*/
            }                                               /*ファイルに書き込み. スペースを*/

            os.close();

            long end = System.nanoTime();
            
            System.out.println(end - start );       //for文を回している間の時間の計測

    //unbufferedだった場合
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
