import greenfoot.*;
/**
anusha
 */
public class ConcreteHandler4 implements soundHandler {

    private soundHandler successor = null;
    private GreenfootSound sound = new GreenfootSound("won.wav");

   public void handleRequest( String request ) {
        if ( request.equalsIgnoreCase("won") )
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
