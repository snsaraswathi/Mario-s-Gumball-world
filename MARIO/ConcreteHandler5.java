import greenfoot.*;
/**
anusha
 */
public class ConcreteHandler5 implements soundHandler {

    private soundHandler successor = null;
    private GreenfootSound sound = new GreenfootSound("gotgumball.wav");

   public void handleRequest( String request ) {
        if ( request.equalsIgnoreCase("gumball") )
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
