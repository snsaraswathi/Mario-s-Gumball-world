import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public  class Enemy3 extends Enemies
{
    public Enemy3(int getMapX, int getMapY)   //Constructor calls for x and y map coordinate
    {
        mapX = getMapX; // Assign the mapX coordinate
        mapY = getMapY; // Assign the mapY coordinate
        GreenfootImage img = getImage();
        img.scale(img.getWidth()-25,img.getHeight() - 25);
    }
    public void act() 
    {
       //move(-1);
    }  
    public  void removeFromList()
    {
        Sky sky = (Sky)getWorld();
        sky.getEnemies3().remove(this);
    }
}
