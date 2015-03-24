import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Whenever mario(subject) gets killed, he should notify the "life left" class objects (observers)
 * 
 */

public class Mario extends Actor implements Subject
{
    soundClient sc = new soundClient();
    int numLife = 0;
    int numCoins = 0; //changes when mario collects the coins
    private String coinState;
    
    private String subjectState;
    private ArrayList<LifeImage> observers = new ArrayList<LifeImage>() ;
    
    private ArrayList<Observer> observers1 = new ArrayList<Observer>() ;
    public int countOfLives = 3;
    boolean inTheAir = false;                       //Whether we're in the air or not
    int fallCount;                                  //Count whether it's time to fall
    int groundHeight = getImage().getHeight()/2;    //Distance from middle of mario  to ground
    int sideWidth = getImage().getWidth()/2;        //Added
    int deltaX = 0;                                 //How much we're moving left and right
    int deltaY = 0;                                 //How much we're moving up and down

    World myWorld;                              //create a variable for the world
    int worldHeight;                            //variable for the height of the world
    int worldWidth;                             //variable for the width of the world
    static GreenfootImage marioPic = new GreenfootImage ("hero.png");
    private GreenfootImage goomba_down = new GreenfootImage("goomba_down.png");

    GreenfootImage image ;
    GreenfootImage leftImage; 
    private boolean imageLeft = false;
    Actor enemyKillingMe;

    public  void setState1(String coinName){
        coinState = coinName;
        notifyObservers1();
    }
    
    public  String getState1(){
        return coinState;
    }
    public void attach(Observer obj) {
        observers1.add(obj) ;
    }

    public void detach(Observer obj) {
        observers1.remove(obj) ;
    }

    public void notifyObservers1() {
        for (Observer obj  : observers1)
        {
            obj.addScore();
        }
    }

    public Mario()
    {
        image = getImage();  
        //image.scale(image.getWidth()-10, image.getHeight() - 60);  
        setImage(image);
        sc.handleSound("titlesong");
    }

    public int getCoins()
    {

        return numCoins;
    }

    public void addedToWorld(World theWorld)    //When added to world...
    {                                           
        myWorld = theWorld;                     //Assign the world variable
        worldHeight = myWorld.getHeight();      //Assign world height variable
        worldWidth = myWorld.getWidth();        //Assign world width variable
    }

    public void act() 
    {
        GreenfootImage leftImage;
        
        if(canSee(Enemies.class) )
        {
            killMario();
        }
        
        if(inTheAir)                //If we're in the air                 
        {                           
            fall();                 //we'll fall
        } 
        else                      //If we're not
        {                           
            if(Greenfoot.isKeyDown("left"))         //If they press the left key
            {
                sc.handleSound("");
                deltaX = -4;   
                if (!imageLeft) {
                    marioPic.mirrorHorizontally();
                    setImage(marioPic);
                    imageLeft = true;
                }

                //deltaX will move us to left
            } 
            else if(Greenfoot.isKeyDown("right")) //If they press the right key
            {
                deltaX = 4;                         //deltaX will move us right
                if (imageLeft) {
                    marioPic.mirrorHorizontally();
                    //setImage(marioPic);
                    imageLeft = false;
                }

            } 

            else                                  //If neither key is down
            {                               
                deltaX=0;                           //Don't move at all
            }  

            if(Greenfoot.isKeyDown("up"))           //If we press the up key
            {                 
                sc.handleSound("jump");
                deltaY+=15;                         //Add 15 to deltaY to jump
            }    

        }  
        
        move();
    } 

    public void move()
    {
        int newX = getX() + deltaX;     // Set the new x and y coordinates by adding or subtracting the deltaX

        int newY = getY() - deltaY;     // and deltaY. newX and newY may change if we're on the ground or a platform.
        Actor platformBelow = getOneObjectAtOffset(0, groundHeight+5, Platform.class);  //Look for a platform below us
        Actor platformAbove = getOneObjectAtOffset(0, -groundHeight-5, Platform.class); //Look for a platform above us
        Actor platformToRight = getOneObjectAtOffset(sideWidth+5, 0, Platform.class); //Look for a platform to right of us
        Actor platformToLeft = getOneObjectAtOffset(-(sideWidth+5), 0, Platform.class); //Look for a platform to left of us
        if(platformBelow!=null)         //If we find a platform below us
        {                               
            if(deltaY<0)                //If we are falling
            {                           
                deltaY=0;               //Stop falling
                inTheAir = false;       //Declare we're on the ground
                GreenfootImage platformImage = platformBelow.getImage();                //Get the image of the platform
                int topOfPlatform = platformBelow.getY()-platformImage.getHeight()/2;   //Find the top of platform
                newY = topOfPlatform - groundHeight;                                    //Put our feet at the top of the platform
            }             
        } else if(getY() >= worldHeight - groundHeight) {   //If we hit the ground...
            if(deltaY<0)                                    //and if we're falling...
            {
                deltaY=0;                                   //Stop falling
                inTheAir = false;                           //Declare we're on the ground
                newY = worldHeight - groundHeight;          //Put bottom of actor right on ground
            }
        }  else                          //If there's no platform below
        {                               
            inTheAir = true;            //Then we will be in the air
        }
        if (platformAbove!=null)        //If there's a platform above us
        {                               
            if(deltaY>0)                //If we're going up
            {                           
                deltaY=0;               //Stop going up
                GreenfootImage platformImage = platformAbove.getImage();                //Get the impage of the platform
                int bottomOfPlatform = platformAbove.getY()+platformImage.getHeight()/2;//Find the bottom of the platform
                newY = bottomOfPlatform + groundHeight;                                 //Put the top of our head at the bottom
            }                           
        }
        if(getX()<=sideWidth)
        {
            deltaX=Math.abs(deltaX);
        }
        if(getX()>=worldWidth-sideWidth)
        {
            deltaX=Math.abs(deltaX)*-1;
        }
        if (platformToRight != null)                            //If there's a platform to the right
        {                                                       
            deltaX = Math.abs(deltaX)* -1;                      //Bounce to the left     
        }                                                       
        if (platformToLeft != null)                             //If there is a platform to the right
        {                                                       
            deltaX = Math.abs(deltaX);                          //Bounce to the right
        }                                                       

        setLocationWithScroll(newX, newY);   
    }    

    public void fall()
    {
        deltaY--;                     //Decrease our change in Y coordinate to make our actor fall
        Actor actor = getOneObjectAtOffset ( 0, (int) this.getImage().getHeight() / 2 , Enemies.class);
        if (actor!= null) 
        {
            Enemies enemy=(Enemies)getWorld().getObjects(Enemies.class).get(0);
            enemy = (Enemies)actor;
            enemy.removeFromList();
            getWorld().removeObject(actor);;
        }
    }

    public void setLocationWithScroll(int newX, int newY)
    {
        Sky mySky = (Sky)getWorld();                //Get the Sky world and type it to Sky class so we can call its methods
        if((newY<200&&mySky.topBound>0) || (newY>worldHeight-200&&mySky.bottomBound<mySky.MAPHEIGHT)) 
        {                                           //If we approach the top or bottom of screen
            int shiftY = newY - getY();             //set shiftY to change in Y
            mySky.shiftScreen(0, -shiftY);          //shift screen by shiftY
        } else {                                    //if we aren't near top or bottom of screen
            setLocation(getX(),newY);               //set Y location normally
        }                                           
        if((newX<200&&mySky.leftBound>0) || (newX>worldWidth-200&&mySky.rightBound<mySky.MAPWIDTH))
        {                                               //If we approach the right or left side of screen
            int shiftX = newX - getX();                 //shiftX will be how much our boundaries move left and right on the map
            mySky.shiftScreen(shiftX, 0);               //shift screen by shiftX
        } else {                                        //If we're not on top or bottom of screen
            setLocation(newX,getY());                   //set X location normally 
        }

    }

    public boolean canSee(Class clss)
    {
        Actor actor = getOneObjectAtOffset(0, 0, clss);
        enemyKillingMe = actor;
        return actor != null;        
    }

    public void eat(Class clss)
    {
        Enemies enemy=(Enemies)getWorld().getObjects(Enemies.class).get(0);
        if(enemyKillingMe != null)
        {
            enemy = (Enemies)enemyKillingMe;
            enemy.removeFromList();
            getWorld().removeObject(enemyKillingMe);
        }
    }

    public void killMario()
    {
        sc.handleSound("killenemy");
        eat(Enemies.class);
        setState("looseLife");
    }
    
    public void addLife()
    {
        setState("gainLife");
    }
    
    public String getState() {
        return subjectState ;
    }

    public void setState(String status) {
        subjectState = status ;
        notifyObservers();
    }

    public void attach(LifeImage obj) {
        observers.add(obj) ;
    }

    public void detach(LifeImage obj) {
        observers.remove(obj) ;
    }

    public void notifyObservers(){
        for (LifeImage obj  : observers)
        {
            obj.updateLife();
        }
    }

    public void showState()
    {
        System.out.println( "Subject: " + this.getClass().getName() + " = " + subjectState );
    }

}
