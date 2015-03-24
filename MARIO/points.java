import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class points here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class points extends Actor implements Observer
{
    /**
     * Act - do whatever the points wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private int score=0;
    protected Mario mario;
    public String coinName;
      
    public points(Mario thesubject)
    {
        mario = thesubject ;
        setImage(new GreenfootImage(200,30));
        update();
    }
    
    public void act()
    {
    }
    
    public void addScoreForCoins()
    {
        //System.out.println("add score called");
        score=score+3;
       // System.out.println("score"+score);
        update();
    }
    
    public void update()
    {
        GreenfootImage img = getImage();
        img.clear();
        img.setColor(Color.WHITE);
        img.setFont(new Font("Trebuchet MS", Font.BOLD,13));
        //img.setFont(img.getFont().deriveFont(Font.BOLD,15));
        img.drawString("Points : "+score,4,20);
    }
    public void addScoreForSpecialCoins()
    {
        //System.out.println("add score called");
        score=score+10;
       // System.out.println("score"+score);
        update();
    }
        public void addScore()
    {
        Sky sky = (Sky)getWorld();
        coinName = sky.mario.getState1();
        if(coinName == "Coin" )
        {
            addScoreForCoins();
        }
        else if(coinName == "SpecialCoin")
        {
            addScoreForSpecialCoins();
        }
    }
}
