import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class SpecialCoin extends Collectible
{
    public SpecialCoin(int getMapX, int getMapY)   //Constructor calls for x and y map coordinate
    {
        mapX = getMapX; // Assign the mapX coordinate
        mapY = getMapY; // Assign the mapY coordinate
        getImage().scale(50,50);
    }
    
    public  void removeFromList()
    {
        Sky sky = (Sky)getWorld();
        sky.getSpecialCoins().remove(this);
    }

    public  void incrementCount()
    {
        Sky sky = (Sky)getWorld();
        sky.mario.numCoins += 10;
        sky.mario.setState1(this.getClass().getName());
        Greenfoot.playSound("ding.wav");
    }
}
