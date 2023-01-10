/*
 * Decompiled with CFR 0.150.
 */
package examples;

import examples.Com;
import examples.mainJFrame;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Geng_xin {
    private static String openFile(String filePath) {
        String ee = new String();
        try {
            URL url = new URL(filePath);
            URLConnection urlconn = url.openConnection();
            urlconn.connect();
            HttpURLConnection httpconn = (HttpURLConnection)urlconn;
            int HttpResult = httpconn.getResponseCode();
            if (HttpResult != 200) {
                System.out.print("\u65e0\u6cd5\u8fde\u63a5\u5230");
            } else {
                int filesize = urlconn.getContentLength();
                InputStreamReader isReader = new InputStreamReader(urlconn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(isReader);
                StringBuffer buffer = new StringBuffer();
                String line = reader.readLine();
                while (line != null) {
                    buffer.append(line);
                    buffer.append("\r\n");
                    line = reader.readLine();
                }
                System.out.print(buffer.toString());
                ee = buffer.toString();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return ee;
    }

    private static void browse2(String url) throws Exception {
        Desktop desktop = Desktop.getDesktop();
        if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
            URI uri = new URI(url);
            desktop.browse(uri);
        }
    }

    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");
        String[] versionArray2 = version2.split("\\.");
        int minLength = Math.min(versionArray1.length, versionArray2.length);
        int diff = 0;
        for (int idx = 0; idx < minLength && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0 && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0; ++idx) {
        }
        diff = diff != 0 ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    public static void geng_xin() {
        Runnable runnable2 = new Runnable(){

            @Override
            public void run() {
                block6: {
                    String gx = Geng_xin.openFile("http://jiakuo25.0594.bftii.com/geng_xin.txt");
                    String[] strArr = gx.split("\r\n");
                    if (strArr.length > 1 && strArr[0].equals("1")) {
                        try {
                            int n;
                            if (Geng_xin.compareVersion(strArr[1].toUpperCase(), mainJFrame.ban_ben.toUpperCase()) <= 0 || (n = JOptionPane.showConfirmDialog(null, mainJFrame.str_geng_xin, "", 0)) != 0) break block6;
                            try {
                                Properties props = System.getProperties();
                                String osName = props.getProperty("os.name");
                                if (osName.contains("Win")) {
                                    Geng_xin.browse2("http://jiakuo25.0594.bftii.com/Laser_java_win.zip");
                                    break block6;
                                }
                                Geng_xin.browse2("http://jiakuo25.0594.bftii.com/Laser_java_mac.zip");
                            }
                            catch (TooManyListenersException ex) {
                                Logger.getLogger(Com.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        catch (Exception ex) {
                            Logger.getLogger(Geng_xin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        };
        Thread thread2 = new Thread(runnable2);
        thread2.start();
    }
}

