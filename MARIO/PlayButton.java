import greenfoot.*; 

public class PlayButton extends Button implements Invoker{
 
    private Command PlayCommand;
    public PlayButton(String title){
        super(title);
    }
    

    public void invoke()
    {
        if (Greenfoot.mouseClicked(this)) 
        PlayCommand.execute();
     }
    
    public void setCommand(Command c){
        this.PlayCommand = c;
    }
 
}
