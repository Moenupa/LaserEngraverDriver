package src;

import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import src.SerialPortUtil;

public class Com {
    SerialPort com = null;
    boolean returned = false;
    public byte[] returnVals = new byte[]{0, 0, 0, 0};
    int returnIntVal = 0;

    public int terminateType = 0;
    public int terminateCount = 0;

    public List<Byte> terminateBuffer = new ArrayList<Byte>();

    SerialPortEventListener windowsTerminate = e -> {
        if (e.getEventType() == 1) {
            Com com = this;
            synchronized (com) {
                try {
                    if (this.com == null) {
                        return;
                    }

                    byte[] tempReturns = SerialPortUtil.readData(this.com);
                    switch (this.terminateType) {
                        case 0: {
                            this.returned = true;
                            this.returnVals = tempReturns;
                            break;
                        }
                        case 1: {
                            break;
                        }
                        case 2: {
                            int i;
                            this.terminateCount += tempReturns.length;
                            for (i = 0; i < tempReturns.length; i++) {
                                this.terminateBuffer.add(tempReturns[i]);
                            }
                            if (this.terminateCount < 3) break;
                            this.returnVals = new byte[3];
                            for (i = 0; i < 3; i++) {
                                this.returnVals[i] = this.terminateBuffer.get(i);
                            }
                            this.returned = true;
                            break;
                        }
                        case 3: {
                            for (int i = 0; i < returnVals.length; i++) {
                                this.terminateBuffer.add(returnVals[i])
                            }
                        }
                    }


                }
            }
        }
    }
    
}