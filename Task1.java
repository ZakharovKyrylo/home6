package org.sourceit.zakharov.home06;

import java.io.*;


public class Task1 {

    public static void main(String[] args) {

        Clear delete = new Clear();
        SupperFile supperFile = new SupperFile();
        CreateFile file = new CreateFile();
        Thread[] runFile =new Thread[9];




        for (int i=0;i<runFile.length; i++) {
            runFile[i]= new Thread(file);
        }

        delete.clearAll();

        for (int i=0;i<runFile.length;i++){
            runFile[i].start();
        }

        for (int i=0;i<runFile.length;i++){
            try {
                runFile[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i=0;i<runFile.length; i++) {
            runFile[i]= new Thread(supperFile);
        }

        for (int i=0;i<runFile.length;i++){
            runFile[i].start();
        }

    }
}

class Clear{
    public void clearAll(){
        for (int i=1;i<10;i++){
            String fileName = new String(i + ".txt");
            String fileSupperName = new String("supper.txt");
            File file = new File(fileName);
            File fileSupper = new File(fileSupperName);
            file.delete();
            fileSupper.delete();

        }

    }
}

class SupperFile implements Runnable{

    public void readWright() {
        try {
            String[] string = new String[10];
            int i = (int) Thread.currentThread().getId() - 19;
            String fileName = new String(i + ".txt");
            FileReader fileReader = new FileReader(fileName);


            // perevod faila v stroky
            if (fileReader.ready()) {
                char[] chars = new char[1000000];
                fileReader.read(chars);
                string[i] = new String(chars);
            }


            String file = new String("supper.txt");
            FileOutputStream stream = new FileOutputStream(new File(file));
            DataOutputStream dataOutputStream = new DataOutputStream(stream);
            dataOutputStream.writeChars(string[i]);

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void run() {
        readWright();
    }
}


class CreateFile implements Runnable{

    public void createFile(){
        try {

            int i=(int)Thread.currentThread().getId()-10;
            String file = new String(i + ".txt");


            FileOutputStream stream = new FileOutputStream(new File(file));
            DataOutputStream dataOutputStream = new DataOutputStream(stream);
            for (int j =0;j<100;j++){
                for (int k=0;k<100;k++)
                    dataOutputStream.writeChars(String.valueOf(i));
                dataOutputStream.writeChars("\n");
            }

    } catch (FileNotFoundException e1) {
        e1.printStackTrace();
    } catch (IOException e1) {
        e1.printStackTrace();
    }

    }

    @Override
    public void run() {
       createFile();
    }


}