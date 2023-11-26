package pe.edu.cibertec.DSWII_CL3_BeltranLopez.exception;

public class MaxUploadSizeExceedException extends RuntimeException {
    public MaxUploadSizeExceedException (String message){
        super(message);
    }
}
