import greenfoot.*;
/**
anusha
 */
public class ConcreteHandler2 implements soundHandler {

    private soundHandler successor = null;
    private GreenfootSound sound = new GreenfootSound("titleSong.mp3");
 
    public void handleRequest( String request ) {   
        if ( request == "stop")
            {
               sound.stop();
            }
            
        if ( request == "titlesong")
            {
                //System.out.println( "soundHandler2 got request");
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
