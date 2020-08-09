package com.ryoua;

import com.ryoua.executor.command.LocalCommandExecutor;
import com.ryoua.executor.command.LocalCommandExecutorImpl;

import java.io.*;


/**
 * * @Author: RyouA
 * * @Date: 2020/8/5
 **/
class StreamGobbler extends Thread {
    InputStream is;
    String type;
    OutputStream os;

    StreamGobbler(InputStream is, String type) {
        this(is, type, null);
    }

    StreamGobbler(InputStream is, String type, OutputStream redirect) {
        this.is = is;
        this.type = type;
        this.os = redirect;
    }

    public void run() {
        try {
            PrintWriter pw = null;
            if (os != null)
                pw = new PrintWriter(os);

            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (pw != null)
                    pw.println(line);
                System.out.println(type + ">" + line);
            }
            if (pw != null)
                pw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String args[]) {
        try {
            File file = new File("/logs/a.log");
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream("/logs/a.log");
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("java -version");

            // 重定向输出流和错误流
            StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERROR");
            StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "OUTPUT", fos);

            errorGobbler.start();
            outputGobbler.start();
            int exitVal = proc.waitFor();
            System.out.println("ExitValue: " + exitVal);
            fos.flush();
            fos.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
