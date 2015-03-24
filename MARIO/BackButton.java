import greenfoot.*; 

public class BackButton extends Button implements Invoker{
 
    private Command BackCommand;
    public BackButton(String title){
        super(title);
    }
    

    public void invoke()
    {
        if (Greenfoot.mouseClicked(this)) 
        BackCommand.execute();
     }
    
    public void setCommand(Command c){
        this.BackCommand = c;
    }
 
}
