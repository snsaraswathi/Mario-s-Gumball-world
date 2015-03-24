import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Another world the buttons are in.
 * 
 * @author Rucha Brahmankar
 * @version 1.0
 */
public class HelpWorld extends World
{
  
    private BackButton bb;
    private BackCommand bc;
    
    
    /**
     * Constructor for objects of class OtherWorld.
     */
    
    public HelpWorld()
    {
        super(600, 400, 1);
        bb = new BackButton("Back");
        bc = new BackCommand();
        addObject(bb,100,300);
        prepare();
    
    }
    
    /**
     * All buttons in the MenuWorld can call this method.
     */
   
    private void prepare()
    {
       
        bb.setCommand(bc);
        bc.setReceiver(
        new Receiver()
        {
            public void doAction()
            {
                if(Greenfoot.mouseClicked(bb)){
                    World newWorld = new MenuWorld();
                    Greenfoot.setWorld(newWorld);
                }
            }
        } );
        
    }
}