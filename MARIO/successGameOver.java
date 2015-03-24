import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class successGameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class successGameOver extends GameOver
{
    /**
     * Act - do whatever the successGameOver wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    soundClient sc = new soundClient();
    Mario mario;
    //World world = getWorld();
    public successGameOver(Mario m)
    {
        mario = m;
        
    }
    
    public void act() {
        sc.handleSound("won");
        getImage().scale(600,400);
        
        GreenfootImage jump = new GreenfootImage("mariowin.png");
        jump.scale(250,250);
        getImage().drawImage(jump,30,70);
        
        String coinscollected =  "You have collected " + mario.getCoins() + " coins.";
        GreenfootImage coin = new GreenfootImage(coinscollected,30,Color.WHITE,Color.BLUE);
        getImage().drawImage(coin,280,120);
        
        int gm = mario.getCoins()/3;
        String gumballsGiven =  "You get " + gm + " gumballs.";
        GreenfootImage gumball = new GreenfootImage(gumballsGiven,30,Color.WHITE,Color.BLUE);
        getImage().drawImage(gumball,280,170);
        
        //GreenfootImage test = new GreenfootImage("1.png");
        //test.scale(10,10);
        //getImage().drawImage(test,100,350);
        
        int loop = 1;
        int x = 280;
        int y = 230;
        while(loop <= gm)
        {            
            int randnum = Greenfoot.getRandomNumber(5)+1;
            String img = randnum+".png";
            GreenfootImage gumballimg = new GreenfootImage(img);
            gumballimg.scale(30,30);
            getImage().drawImage(gumballimg,x,y);
            loop++;
            x = x+50;
            if (x>550) {
                x =180;
                y += 50;
            }
        }

    }
    
   
}