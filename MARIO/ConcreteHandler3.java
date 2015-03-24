import greenfoot.*;
/**
anusha
 */
public class ConcreteHandler3 implements soundHandler {

    private soundHandler successor = null;
    private GreenfootSound sound = new GreenfootSound("shooting.wav");
  
    public void handleRequest( String request ) {
        
        if ( request.equalsIgnoreCase("killenemy"))
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
