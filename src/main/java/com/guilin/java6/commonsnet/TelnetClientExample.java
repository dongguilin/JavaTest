package com.guilin.java6.commonsnet;

import org.apache.commons.net.telnet.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;

/**
 * Created by guilin1 on 15/7/29.
 */
public class TelnetClientExample implements Runnable, TelnetNotificationHandler {
    static TelnetClient tc = null;

    /**
     * Main for the TelnetClientExample.
     * *
     */
    public static void main(String[] args) throws Exception {
        FileOutputStream fout = null;


        String remoteip = "10.0.1.1";

        int remoteport = 23;

        try {
            fout = new FileOutputStream("/Users/guilin1/Documents/spy.log", true);
        } catch (IOException e) {
            System.err.println(
                    "Exception while opening the spy file: "
                            + e.getMessage());
        }

        tc = new TelnetClient();

        TerminalTypeOptionHandler ttopt = new TerminalTypeOptionHandler("VT100", false, false, true, false);
        EchoOptionHandler echoopt = new EchoOptionHandler(true, false, true, false);
        SuppressGAOptionHandler gaopt = new SuppressGAOptionHandler(true, true, true, true);

        try {
            tc.addOptionHandler(ttopt);
            tc.addOptionHandler(echoopt);
            tc.addOptionHandler(gaopt);
        } catch (InvalidTelnetOptionException e) {
            System.err.println("Error registering option handlers: " + e.getMessage());
        }

        while (true) {
            boolean end_loop = false;
            try {
                tc.connect(remoteip, remoteport);


                Thread reader = new Thread(new TelnetClientExample());
                tc.registerNotifHandler(new TelnetClientExample());
//                System.out.println("TelnetClientExample");
//                System.out.println("Type AYT to send an AYT telnet command");
//                System.out.println("Type OPT to print a report of status of options (0-24)");
//                System.out.println("Type REGISTER to register a new SimpleOptionHandler");
//                System.out.println("Type UNREGISTER to unregister an OptionHandler");
//                System.out.println("Type SPY to register the spy (connect to port 3333 to spy)");
//                System.out.println("Type UNSPY to stop spying the connection");

                reader.start();
                OutputStream outstr = tc.getOutputStream();
//                readUntil("Username:");
//                tc.getOutputStream().write("admin".getBytes());
//                readUntil("Password:");
//                tc.getOutputStream().write("yhxt@123".getBytes());
//                byte[] buff = new byte[1024];
//                int ret_read = 0;
//                while(true){
//                    ret_read = tc.getInputStream().read(buff);
//                    if(ret_read>0)
//                    System.out.println(ret_read);
//                }

//                try {
//                    tc.disconnect();
//                } catch (IOException e) {
//                    System.err.println("Exception while connecting:" + e.getMessage());
//                }

                byte[] buff = new byte[1024];
                int ret_read = 0;

                do {
                    try {
                        ret_read = System.in.read(buff);
                        if (ret_read > 0) {
                            if ((new String(buff, 0, ret_read)).startsWith("AYT")) {
                                try {
                                    System.out.println("Sending AYT");

                                    System.out.println("AYT response:" + tc.sendAYT(5000));
                                } catch (IOException e) {
                                    System.err.println("Exception waiting AYT response: " + e.getMessage());
                                }
                            } else if ((new String(buff, 0, ret_read)).startsWith("OPT")) {
                                System.out.println("Status of options:");
                                for (int ii = 0; ii < 25; ii++) {
                                    System.out.println("Local Option " + ii + ":" + tc.getLocalOptionState(ii) + " Remote Option " + ii + ":" + tc.getRemoteOptionState(ii));
                                }
                            } else if ((new String(buff, 0, ret_read)).startsWith("REGISTER")) {
                                StringTokenizer st = new StringTokenizer(new String(buff));
                                try {
                                    st.nextToken();
                                    int opcode = Integer.parseInt(st.nextToken());
                                    boolean initlocal = Boolean.parseBoolean(st.nextToken());
                                    boolean initremote = Boolean.parseBoolean(st.nextToken());
                                    boolean acceptlocal = Boolean.parseBoolean(st.nextToken());
                                    boolean acceptremote = Boolean.parseBoolean(st.nextToken());
                                    SimpleOptionHandler opthand = new SimpleOptionHandler(opcode, initlocal, initremote,
                                            acceptlocal, acceptremote);
                                    tc.addOptionHandler(opthand);
                                } catch (Exception e) {
                                    if (e instanceof InvalidTelnetOptionException) {
                                        System.err.println("Error registering option: " + e.getMessage());
                                    } else {
                                        System.err.println("Invalid REGISTER command.");
                                        System.err.println("Use REGISTER optcode initlocal initremote acceptlocal acceptremote");
                                        System.err.println("(optcode is an integer.)");
                                        System.err.println("(initlocal, initremote, acceptlocal, acceptremote are boolean)");
                                    }
                                }
                            } else if ((new String(buff, 0, ret_read)).startsWith("UNREGISTER")) {
                                StringTokenizer st = new StringTokenizer(new String(buff));
                                try {
                                    st.nextToken();
                                    int opcode = (new Integer(st.nextToken())).intValue();
                                    tc.deleteOptionHandler(opcode);
                                } catch (Exception e) {
                                    if (e instanceof InvalidTelnetOptionException) {
                                        System.err.println("Error unregistering option: " + e.getMessage());
                                    } else {
                                        System.err.println("Invalid UNREGISTER command.");
                                        System.err.println("Use UNREGISTER optcode");
                                        System.err.println("(optcode is an integer)");
                                    }
                                }
                            } else if ((new String(buff, 0, ret_read)).startsWith("SPY")) {
                                tc.registerSpyStream(fout);
                            } else if ((new String(buff, 0, ret_read)).startsWith("UNSPY")) {
                                tc.stopSpyStream();
                            } else {
                                try {
                                    outstr.write(buff, 0, ret_read);
                                    outstr.flush();
                                } catch (IOException e) {
                                    end_loop = true;
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Exception while reading keyboard:" + e.getMessage());
                        end_loop = true;
                    }
                }
                while ((ret_read > 0) && (end_loop == false));


            } catch (IOException e) {
                System.err.println("Exception while connecting:" + e.getMessage());
                System.exit(1);
            }
        }
    }


    /**
     * Callback method called when TelnetClient receives an option
     * negotiation command.
     * <p/>
     *
     * @param negotiation_code - type of negotiation command received
     *                         (RECEIVED_DO, RECEIVED_DONT, RECEIVED_WILL, RECEIVED_WONT)
     *                         <p/>
     * @param option_code      - code of the option negotiated
     *                         <p/>
     *                         *
     */
//    @Override
    public void receivedNegotiation(int negotiation_code, int option_code) {
        String command = null;
        if (negotiation_code == TelnetNotificationHandler.RECEIVED_DO) {
            command = "DO";
        } else if (negotiation_code == TelnetNotificationHandler.RECEIVED_DONT) {
            command = "DONT";
        } else if (negotiation_code == TelnetNotificationHandler.RECEIVED_WILL) {
            command = "WILL";
        } else if (negotiation_code == TelnetNotificationHandler.RECEIVED_WONT) {
            command = "WONT";
        }
        System.out.println("Received " + command + " for option code " + option_code);
    }

    /**
     * Reader thread.
     * Reads lines from the TelnetClient and echoes them
     * on the screen.
     * *
     */
//    @Override
    public void run() {
        InputStream instr = tc.getInputStream();

        try {
            byte[] buff = new byte[1024];
            int ret_read = 0;

            do {
                ret_read = instr.read(buff);
                if (ret_read > 0) {
                    String str = new String(buff, 0, ret_read);
                    System.out.print(str);
                    if (str.endsWith("Username:")) {
                        System.out.println("hehe");
                    }
                }
            }
            while (ret_read >= 0);
        } catch (IOException e) {
            System.err.println("Exception while reading socket:" + e.getMessage());
        }

        try {
            tc.disconnect();
        } catch (IOException e) {
            System.err.println("Exception while closing telnet:" + e.getMessage());
        }
    }

    public static void readUntil(String str) {
        InputStream instr = tc.getInputStream();
        try {
            byte[] buff = new byte[1024];
            int ret_read;
            boolean flag = true;
            while (flag) {
                ret_read = instr.read(buff);
                if (ret_read > 0) {
                    String temp = new String(buff, 0, ret_read);
                    if (temp.endsWith(str)) {
                        flag = false;
                        buff = null;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Exception while reading socket:" + e.getMessage());
        }
    }


}
