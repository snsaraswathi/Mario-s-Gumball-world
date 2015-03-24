import greenfoot.*;
/**
anusha
 */
public class ConcreteHandler6 implements soundHandler {

    private soundHandler successor = null;
    private GreenfootSound sound = new GreenfootSound("death.wav");

   public void handleRequest( String request ) {
        if ( request.equalsIgnoreCase("death") )
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
