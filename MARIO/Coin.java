import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Coin extends Collectible
{
    
    public Coin(){} //added for swatis part
    public Coin(int getMapX, int getMapY)   //Constructor calls for x and y map coordinate
    {
        mapX = getMapX; // Assign the mapX coordinate
        mapY = getMapY; // Assign the mapY coordinate
    }

    public  void removeFromList()
    {
        Sky sky = (Sky)getWorld();
        sky.getCoins().remove(this);
    }

    public  void incrementCount()
    {
        Sky sky = (Sky)getWorld();
        sky.mario.numCoins++;
        sky.mario.setState1(this.getClass().getName());
    }

}
