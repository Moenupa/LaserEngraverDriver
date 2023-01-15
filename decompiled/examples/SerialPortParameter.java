/*
 * Decompiled with CFR 0.150.
 */
package examples;

public final class SerialPortParameter {
    private String serialPortName;
    private int baudRate;
    private int dataBits;
    private int stopBits;
    private int parity;

    public SerialPortParameter(String serialPortName) {
        this.serialPortName = serialPortName;
        this.baudRate = 115200;
        this.dataBits = 8;
        this.stopBits = 1;
        this.parity = 0;
    }

    public SerialPortParameter(String serialPortName, int baudRate) {
        this.serialPortName = serialPortName;
        this.baudRate = baudRate;
        this.dataBits = 8;
        this.stopBits = 1;
        this.parity = 0;
    }

    public String getSerialPortName() {
        return this.serialPortName;
    }

    public void setSerialPortName(String serialPortName) {
        this.serialPortName = serialPortName;
    }

    public int getBaudRate() {
        return this.baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public int getDataBits() {
        return this.dataBits;
    }

    public void setDataBits(int dataBits) {
        this.dataBits = dataBits;
    }

    public int getStopBits() {
        return this.stopBits;
    }

    public void setStopBits(int stopBits) {
        this.stopBits = stopBits;
    }

    public int getParity() {
        return this.parity;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }
}

