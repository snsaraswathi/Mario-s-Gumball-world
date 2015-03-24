import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class Coins here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CoinsCollected extends Actor implements Observer
{
   private int score=0;
    protected Mario mario;
      
    public CoinsCollected(Mario thesubject)
    {
        mario = thesubject ;
        setImage(new GreenfootImage(20,21));
        update();
    }
    
    public void act()
    {
    }
    
    public void addScore()
    {
        //System.out.println("add score called");
        score++;
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
        img.drawString(""+score,4,20);
    }
}
