import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public  class Enemy2 extends Enemies
{
    public Enemy2(int getMapX, int getMapY)   //Constructor calls for x and y map coordinate
    {
        mapX = getMapX; // Assign the mapX coordinate
        mapY = getMapY; // Assign the mapY coordinate
        GreenfootImage img = getImage();
        img.scale(img.getWidth()-25,img.getHeight() - 25);
    }
    public void act() 
    {
       // GreenfootImage img = getImage();
       // img.scale(img.getWidth()-15,img.getHeight() - 35);
      //  getImage().scale(lifeimg.getImage().getWidth() - 35, lifeimg.getImage().getHeight() - 35);
        // Add your action code here.
    }   
    public  void removeFromList()
    {
        Sky sky = (Sky)getWorld();
        sky.getEnemies2().remove(this);
    }
}
