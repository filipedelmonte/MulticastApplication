/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class ConfirmPDU {
    // Data declaration
    private String pduData;
    private int nroPDU;

    /** Creates a new instance of RequestPDU */
    public ConfirmPDU(int nro) throws IllegalFormatException{

        // check opcode
        if (nro < 0){ // error
            throw new IllegalFormatException();
        } else{
            // create new pdu
            this.nroPDU = nro;
            pduData = new String("MCDCNFPDU "+nro);
        }
    }

    /** Creates a new instance of RequestPDU from an array of bytes */
    public ConfirmPDU(byte[] data) throws IllegalFormatException{

        pduData = new String(data);

        // parse PDU
        String [] elements = pduData.trim().split(" ");

        if(elements.length != 2){
            throw new IllegalFormatException();
        }else{
            if(!elements[0].equals("MCDCNFPDU")){
                throw new IllegalFormatException();
            } else{
                //check opcode
                try{
                    this.nroPDU = Integer.parseInt(elements[1]);
                    if (this.nroPDU < 0){ // error
                        throw new IllegalFormatException();
                    } else{
                        // create new pdu
                        pduData = new String("MCDCNFPDU "+this.nroPDU);
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
