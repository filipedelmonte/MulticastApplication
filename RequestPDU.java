/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class RequestPDU {
    // Data declaration
    private String pduData;
    private int nroPDU;
    private int size;
    private String message;

    /** Creates a new instance of RequestPDU */
    public RequestPDU(int nro, String message) throws IllegalFormatException{

        // check ntoPDU
        if (nro < 0){ // error
            throw new IllegalFormatException();
        } else{
            // create new pdu
            this.nroPDU = nro;
            this.size = message.length();
            this.message = message;

            pduData = new String("MCDREQPDU "+nroPDU+" "+size+" "+this.message);
        }
    }

    /** Creates a new instance of RequestPDU from an array of bytes */
    public RequestPDU(byte[] data) throws IllegalFormatException{

        pduData = new String(data);

        // parse PDU
        String[] elements = pduData.trim().split(" ");

        if(elements.length != 4){
            throw new IllegalFormatException();
        }else{
            if(!elements[0].equals("MCDREQPDU")){
                throw new IllegalFormatException();
            } else{
                //check nroPDU
                try{
                    this.nroPDU = Integer.parseInt(elements[1]);
                    if (this.nroPDU < 0){ // error
                        throw new IllegalFormatException();
                    } else{
                        // create new pdu
                        this.size = Integer.parseInt(elements[2]);
                        this.message = elements[3];
                        pduData = new String("MCDREQPDU "+this.nroPDU+" "+this.size+" "+this.message);
                    }
                }catch(NumberFormatException nfe){
                    throw new IllegalFormatException();
                }

            }
        }
    }

    public byte[] getPDUData(){
        return pduData.getBytes();
    }
}
