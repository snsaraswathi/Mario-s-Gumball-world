import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * The world the buttons are in.
 * 
 * @author Rucha Brahmankar
 * @version 1.0
 */
public class MenuWorld extends World
{
    /**
     * Constructor for objects of class MenuWorld.
     */
    
    private PlayButton pb;
    private PlayCommand pc;
    private HelpButton hb;
    private HelpCommand hc;
    public MenuWorld()
    {
        super(600, 400, 1);
        pb = new PlayButton("Play"); //invoker PlayButton
        pc = new PlayCommand(); //menu world created a concrete command object.
        addObject(pb,100,100);
        hb = new HelpButton("Help");
        hc = new HelpCommand();
        addObject(hb,100, 300);
        prepare();      
    }
    
  
    public void prepare(){
     
                pb.setCommand(pc); //invoker object stores the concretecommand object in this case playcommand
                pc.setReceiver(   //command object has reference to the receiver object.
                new Receiver() 
                {
                    
                    public void doAction() //This is actual action which should happen on click on Play Button
                    {
                     if(Greenfoot.mouseClicked(pb)){
                        World newWorld = new Sky();
                        Greenfoot.setWorld(newWorld);
                    }
                }
            });
            
              hb.setCommand(hc);
              hc.setReceiver(
              new Receiver() 
                {
                    
                    public void doAction()
                    {
                     if(Greenfoot.mouseClicked(hb)){
                       
                        World newWorld = new HelpWorld();
                        Greenfoot.setWorld(newWorld);
                    }
                    
                }
            });
          
        }
            
    
}


    
    
    
    
 