import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Life extends Collectible
{
    public Life(int getMapX, int getMapY)   //Constructor calls for x and y map coordinate
    {
        mapX = getMapX; // Assign the mapX coordinate
        mapY = getMapY; // Assign the mapY coordinate
    }

    public  void removeFromList()
    {
        Sky sky = (Sky)getWorld();
        sky.getLives().remove(this);
    }

    public  void incrementCount()
    {
        Mario mario = (Mario)getWorld().getObjects(Mario.class).get(0);
        mario.addLife();
    }
}