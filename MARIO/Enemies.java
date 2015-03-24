import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

public abstract class Enemies extends Actor
{
    //public Mario mario=(Mario)getWorld().getObjects(Mario.class).get(0);
    public static List<Enemies> allEnemies = new ArrayList<Enemies>(); 

    protected int mapX;
    protected int mapY;

    GreenfootImage marioPic;
   
    public void act() 
    {

    }

    public void add(Enemies e)
    {
        allEnemies.add(e);
        for (int i=0;i<allEnemies.size();i++)
        {
            //System.out.println("Enemy added");
        }
    }

    
    public abstract void removeFromList();
    //public abstract void incrementCount();   
    
    public void removefromWorld()
    {
        getWorld().removeObject(this);  
    }
    
    
    public boolean canSee(Class clss)
    {
        Actor actor = getOneObjectAtOffset(0, 0, clss);
        //val= true;
        return actor != null;        
    }

    
    public int getX()
    {
        return mapX;
    }
    
    public int getY()
    {
        return mapY;
    }
}
//set countOfLives as state
