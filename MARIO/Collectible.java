import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Collectible extends Actor
{   
    protected int mapX;
    protected int mapY;
    public int getX()
    {
        return mapX;
    }
    public int getY()
    {
        return mapY;
    }

    public final void act()
    {
        turn(1);
        Actor actor = getOneIntersectingObject(Mario.class);
        if (actor != null) 
        {
            incrementCount();
            removeFromList();
            removefromWorld();
        }
    }

    public abstract void removeFromList();
    public abstract void incrementCount();   
    
    public void removefromWorld()
    {
        getWorld().removeObject(this);  
    }
    
}
