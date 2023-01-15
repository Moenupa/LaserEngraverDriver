/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gnu.io.CommPort
 *  gnu.io.CommPortIdentifier
 *  gnu.io.NoSuchPortException
 *  gnu.io.PortInUseException
 *  gnu.io.SerialPort
 *  gnu.io.SerialPortEventListener
 *  gnu.io.UnsupportedCommOperationException
 */
package examples;

import examples.SerialPortParameter;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerialPortUtil {
    static Object suo = new Object();

    public static List<String> getSerialPortList() {
        ArrayList<String> systemPorts = new ArrayList<String>();
        Enumeration portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            String portName = ((CommPortIdentifier)portList.nextElement()).getName();
            systemPorts.add(portName);
        }
        return systemPorts;
    }

    public static SerialPort openSerialPort(String serialPortName) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException {
        SerialPortParameter parameter = new SerialPortParameter(serialPortName);
        return SerialPortUtil.openSerialPort(parameter);
    }

    public static SerialPort openSerialPort(String serialPortName, int baudRate) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException {
        SerialPortParameter parameter = new SerialPortParameter(serialPortName, baudRate);
        return SerialPortUtil.openSerialPort(parameter);
    }

    public static SerialPort openSerialPort(String serialPortName, int baudRate, int timeout) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException {
        SerialPortParameter parameter = new SerialPortParameter(serialPortName, baudRate);
        return SerialPortUtil.openSerialPort(parameter, timeout);
    }

    public static SerialPort openSerialPort(SerialPortParameter parameter) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException {
        return SerialPortUtil.openSerialPort(parameter, 2000);
    }

    public static SerialPort openSerialPort(SerialPortParameter parameter, int timeout) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier((String)parameter.getSerialPortName());
        CommPort commPort = portIdentifier.open(parameter.getSerialPortName(), timeout);
        if (commPort instanceof SerialPort) {
            SerialPort serialPort = (SerialPort)commPort;
            serialPort.setSerialPortParams(parameter.getBaudRate(), parameter.getDataBits(), parameter.getStopBits(), parameter.getParity());
            System.out.println("\u5f00\u542f\u4e32\u53e3\u6210\u529f\uff0c\u4e32\u53e3\u540d\u79f0\uff1a" + parameter.getSerialPortName());
            return serialPort;
        }
        throw new NoSuchPortException();
    }

    public static void closeSerialPort(SerialPort serialPort) {
        if (serialPort != null) {
            serialPort.close();
            System.out.println("\u5173\u95ed\u4e86\u4e32\u53e3\uff1a" + serialPort.getName());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void sendData(SerialPort serialPort, byte[] data) {
        Object object = suo;
        synchronized (object) {
            OutputStream os = null;
            try {
                os = serialPort.getOutputStream();
                os.write(data);
                os.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (os != null) {
                        os.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] readData(SerialPort serialPort) {
        InputStream is = null;
        byte[] bytes = null;
        try {
            is = serialPort.getInputStream();
            int bufflenth = is.available();
            while (bufflenth != 0) {
                bytes = new byte[bufflenth];
                is.read(bytes);
                bufflenth = is.available();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (is != null) {
                    is.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String readLine(SerialPort serialPort) {
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        Object bytes = null;
        try {
            is = serialPort.getInputStream();
            char bt = '\u0000';
            boolean ii = false;
            do {
                if ((bt = (char)is.read()) == '\uffff') {
                    break;
                }
                sb.append(bt);
            } while (bt != '\r');
        }
        catch (IOException ex) {
            Logger.getLogger(SerialPortUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if (is != null) {
                    is.close();
                }
            }
            catch (IOException iOException) {}
        }
        return sb.toString();
    }

    public static void setListenerToSerialPort(SerialPort serialPort, SerialPortEventListener listener) throws TooManyListenersException {
        serialPort.addEventListener(listener);
        serialPort.notifyOnDataAvailable(true);
        serialPort.notifyOnBreakInterrupt(true);
    }
}

