
import java.io.BufferedOutputStream;
//import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 *
 */

public class FileWrite {//クラス

    static int size;//スタティック変数なので他のオブジェクトによって更新されると内容が共有される。
    static boolean buffer_flag;
    static boolean help_flag=false;
    static String outputFile = "outputFile";

    public static void main(String args[]) throws//例外 IOException {//メインメソッド
        long start = System.currentTimeMillis();//グリニッジ標準時からの経過時間を取得する静的メソッド
        getopts(args);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    public static void getopts(String[] args) throws  IOException {//ゲットトップメソッド
        if (args.length/*インスタンスメソッド*/ == 3) {
            if (args[1].equals("--size") && checkStringNumber(args[2])) {
                size = Integer.parseInt(args[2]);//args[2]１０進数に変換してint型で返す
                if (args[0].equals("-b")) {
                    buffer_flag = true;
                    System.out.println("buffer");
                    /*BufferedOutputStreamという名前のクラスのインスタンス(BufferedOutputStreamと同じ変数宣言を
                    自動で行い同じだけのメモリアドレス領域を確保する)を生成して
                    そのクラスのインスタンスを参照できるアドレスが入った変数がOSということなのかな？*/
                    BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(outputFile));
                    for (int i = 0; i < size; i++) {
                        os.write(97);//インスタンスメソッド
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
            // System.out.println("Usage");
            System.out.println("-u        :unbuffered write");
            System.out.println("-b        :buffered write");
            System.out.println("--help,-h :show this");
            System.out.println("--size    :write file size");
            help_flag = true;
        }
        else
            System.out.println("ERROR!");
    }


    public static int getWriteSize(){//getWriteSizeメソッド
        return (size);
    }

    public static boolean isHelped(){//isHelpedメソッド
        return (help_flag);
    }

    public static boolean isBuffered(){//isBufferedメソッド
        return(buffer_flag);
    }

    public static boolean checkStringNumber(String number) {//checkStringNumberメソッド
        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(number);
        return m.find();
    }
}
