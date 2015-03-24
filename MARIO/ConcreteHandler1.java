import greenfoot.*;
/**
anusha
 */
public class ConcreteHandler1 implements soundHandler {

    private soundHandler successor = null;
    private GreenfootSound sound = new GreenfootSound("jump.wav");;

	public void handleRequest( String request ) {
        if ( request.equalsIgnoreCase("jump") )
        {
            sound.play();
        }
        else
        {
            if ( successor != null )
                successor.handleRequest(request);
        }

	}

	public void setSuccessor(soundHandler next) {
        this.successor = next ;
	}
}
