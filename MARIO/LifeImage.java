import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class lifeImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LifeImage extends Actor
{
    /**
     * Act - do whatever the lifeImage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //int lifeVal = 3;

    String observerState="";
    int countOfLives = 3;
    soundClient sc = new soundClient();
    
    public void updateLife()
    {
        
        Mario mario=(Mario)getWorld().getObjects(Mario.class).get(0);
        Sky sky = (Sky)getWorld();
        observerState = sky.mario.getState();
        if(observerState == "looseLife")        //Reduce the the number of lives left by 1
        {       
            if (countOfLives == 3)
            {
                countOfLives--;
                getWorld().removeObjects(getWorld().getObjectsAt(151,14,LifeImage.class));
            }
            else if (countOfLives == 2)
            {
                countOfLives--;
                getWorld().removeObjects(getWorld().getObjectsAt(132,14,LifeImage.class));
            }
            else if (countOfLives == 1)
            {
                countOfLives--;
                sc.handleSound("death");
                sc.handleSound("stop");
                sky.getEnemies1().clear();
                sky.getEnemies2().clear();
                sky.getEnemies3().clear();
                GameOver gameOver = new GameOver();
                getWorld().addObject(gameOver, getWorld().getWidth()/2, getWorld().getHeight()/2); 

                Greenfoot.stop();
            }
        }
        else if(observerState == "gainLife")    //Increase the the number of lives left by 1
        {
            LifeImage img  = new LifeImage();
            img.getImage().scale(img.getImage().getWidth() - 35, img.getImage().getHeight() - 35);
            if(countOfLives == 2)
            {
                countOfLives++;
                getWorld().addObject(img, 151,14 );
            }
            else if(countOfLives == 1)
            {
                countOfLives++;
                getWorld().addObject(img, 132,14 );
            }
        }
    }   


}
