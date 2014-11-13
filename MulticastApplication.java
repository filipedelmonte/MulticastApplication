/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.Scanner;

public class MulticastApplication extends Thread implements MulticastServiceUserInterface{
    // declaring variables
    private MulticastServiceInterface msi;
    private int currentState;

    /**
     * Creates a new instance of ServiceUser
     */
    public MulticastApplication(MulticastServiceInterface ref) {
        currentState = 0;
        msi = ref;
    }

    public void mCDataInd(){
        // confirmation
        System.out.println("message sent");
        currentState = 0;
    }

    public void mCDataInd(short sourceAddress, String message){
		// write message
        System.out.println(String.valueOf(sourceAddress)+": "+message);
        currentState = 0;
    }

    public void run(){
		// variable declaration
        Scanner sc;
        String cmd;
        String[] servicePrim;
        int op1, op2;


        //inicialization
        sc = new Scanner(System.in);
        System.out.println(">>");
		// protocol user behaviour
        while (true){
            switch (currentState){
                case 0:
                    System.out.println(">> ");
                    cmd = sc.nextLine();
                    servicePrim = cmd.split(" ");
                    while( cmd.equals("") || (!servicePrim[0].equals("add") && !servicePrim[0].equals("sub") && !servicePrim[0].equals("div") && !servicePrim[0].equals("times") ) ){
                        System.out.println(">> Comando invalido: "+cmd);
                        cmd = sc.nextLine();
                        servicePrim = cmd.split(" ");
                    }

                    try{
                        //send request
                        String message = "teste";
                        short[] dest = new short[2];
                        dest[0] = 11;dest[0] = 22;
                        msi.mCDataReq(dest, message);

                        //update state
                        currentState = 1;

                    } catch(NumberFormatException nfe){
                        System.out.println("Formato invalido: "+nfe);
                    }

                    break;
                default:
                    try{
                        sleep(100);
                    } catch(InterruptedException  ie){
                        System.err.println("Erro durante a espera pela resposta: "+ie);
                    }
            }
         }
    }
}
