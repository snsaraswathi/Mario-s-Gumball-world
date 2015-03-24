import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Write a description of class Sky here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sky extends World
{
    PlatformMap map = new PlatformMap();    //This is the image that the map will be drawn from
    GreenfootImage mapImg = map.getImage(); // Gets the image
    final int MAPIMGWIDTH = mapImg.getWidth()/5;  // The width of the map image
    final int MAPIMGHEIGHT  = mapImg.getHeight();   //The height of the map image
    Platform platformTemplate = new Platform(0,0);      //This is a template so we can see
    GreenfootImage pfImg = platformTemplate.getImage(); //how tall and wide the platform is
    final int PLATFORMHEIGHT = pfImg.getHeight();       //The width of the platforms
    final int PLATFORMWIDTH = pfImg.getWidth();         //The height of the platform
    final int MAPWIDTH = MAPIMGWIDTH * PLATFORMWIDTH;   //The height and width of the map is the size
    final int MAPHEIGHT = MAPIMGHEIGHT * PLATFORMHEIGHT;//of the image times the size of the platform
    private List<Platform> thePlatforms = new ArrayList<Platform>();//This list contains all the platforms
    int leftBound = 0;                      //This is where the left side of the screen is on the map
    int bottomBound = MAPHEIGHT;                          //Where the bottom of the screen is on the map
    int topBound = MAPHEIGHT - getHeight();                 //Where the top of the screen is on the map
    int rightBound = getWidth();            //Where the right side of the screen is on the map
    Mario mario = new Mario();    //create mario

    Coin coinTemplate = new Coin(0,0);      //This is a coin template so we can see
    GreenfootImage coinImg = coinTemplate.getImage(); //how tall and wide the coin is
    final int COINHEIGHT = coinImg.getHeight();       //The width of the coin
    final int COINWIDTH = coinImg.getWidth();         //The height of the coin
    private List<Collectible> theCoins = new ArrayList<Collectible>();//This list contains all the coins

    SpecialCoin specialCoinTemplate = new SpecialCoin(0,0);      //This is a coin template so we can see
    GreenfootImage specialCoinImg = specialCoinTemplate.getImage(); //how tall and wide the coin is
    final int SPECIALCOINHEIGHT = specialCoinImg.getHeight();       //The width of the coin
    final int SPECIALCOINWIDTH = specialCoinImg.getWidth();         //The height of the coin
    private List<Collectible> theSpecialCoins = new ArrayList<Collectible>();//This list contains all the coins

    Life lifeTemplate = new Life(0,0);      //This is a coin template so we can see
    GreenfootImage lifeImg = lifeTemplate.getImage(); //how tall and wide the coin is
    final int LIFEHEIGHT = lifeImg.getHeight();       //The width of the coin
    final int LIFEWIDTH = lifeImg.getWidth();         //The height of the coin
    private List<Collectible> theLives = new ArrayList<Collectible>();//This list contains all the coins

    //swati
    Enemy1 enemy1 = new Enemy1(0,0);
    GreenfootImage enemy1Img = enemy1.getImage(); 
    final int ENEMY1HEIGHT = enemy1Img.getHeight(); 
    final int ENEMY1WIDTH = enemy1Img.getWidth(); 
    private List<Enemies> theEnemy1 = new ArrayList<Enemies>();

    Enemy2 enemy2 = new Enemy2(0,0);
    GreenfootImage enemy2Img = enemy2.getImage(); 
    final int ENEMY2HEIGHT = enemy2Img.getHeight(); 
    final int ENEMY2WIDTH = enemy2Img.getWidth(); 
    private List<Enemies> theEnemy2 = new ArrayList<Enemies>();

    Enemy3 enemy3 = new Enemy3(0,0);
    GreenfootImage enemy3Img = enemy3.getImage(); 
    final int ENEMY3HEIGHT = enemy3Img.getHeight(); 
    final int ENEMY3WIDTH = enemy3Img.getWidth(); 
    private List<Enemies> theEnemy3 = new ArrayList<Enemies>();

    points pts = new points(mario); 
    Coin displayCoin = new Coin();
    CoinsCollected coinCltcd = new CoinsCollected(mario);

    LifeImage lifeimg = new LifeImage();
    LifeImage lifeimg1 = new LifeImage();
    LifeImage lifeimg2 = new LifeImage();

    public List<Collectible> getCoins()
    {
        return theCoins;
    }

    public List<Collectible> getSpecialCoins()
    {
        return theSpecialCoins;
    }

    public List<Collectible> getLives()
    {
        return theLives;
    }

    public List<Enemies> getEnemies1()
    {
        return theEnemy1;
    }

    public List<Enemies> getEnemies2()
    {
        return theEnemy2;
    }

    public List<Enemies> getEnemies3()
    {
        return theEnemy3;
    }

    /**
     * Constructor for objects of class Sky.
     * 
     */
    public Sky()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        // The program will work whatever size you make the screen.

        super(600, 400, 1, false); 
        makeMap();  //Calls the function that makes the map from the image
        addObject(mario, getWidth()/2, getHeight()-mario.getImage().getHeight()/2);
        // swati part added
        addObject(pts,103,13); //Add mario
        mario.attach(pts);
        displayCoin.getImage().scale(displayCoin.getImage().getWidth() - 30, displayCoin.getImage().getHeight() - 30);
        addObject(displayCoin,79,13);
        addObject(coinCltcd,99,8);
        mario.attach(coinCltcd);
        lifeimg.getImage().scale(lifeimg.getImage().getWidth() - 35, lifeimg.getImage().getHeight() - 35);
        addObject(lifeimg,113,14);
        lifeimg1.getImage().scale(lifeimg1.getImage().getWidth() - 35, lifeimg1.getImage().getHeight() - 35);
        addObject(lifeimg1,132,14);
        lifeimg2.getImage().scale(lifeimg2.getImage().getWidth() - 35, lifeimg2.getImage().getHeight() - 35);
        addObject(lifeimg2,151,14);
        mario.attach(lifeimg);

        update();   //Draws the platforms on the screen
        prepare();
    }

    /*
     * The MakeMap method uses the map image to put each platform into an the thePlatfoms array
     * and to give each platform its location on the map in its mapX and mapY field.
     * THIS METHOD WILL ONLY WORK WITH A GIF IMAGE WITH NO ANTI-ALIASING OR COLOR AVERAGING!
     */
    public void makeMap()
    {
        for(int y=0;y<MAPIMGHEIGHT;y++) //One for each row of pixels
        {
            for(int x=0;x<MAPIMGWIDTH;x++) //One for each pixel in the row
            {
                int colorRGB = mapImg.getColorAt(x, y).getRGB(); //Get the RGB color of the pixel
                if(colorRGB==Color.BLACK.getRGB()) //Check to see if it's black
                {
                    /*
                     * One pixel is the size of a platform, so we multiply the x and y of the pixel on the
                     * map image times the width and height of a platform. Depending on the proportions of the 
                     * platform you'll get a different shape of map. The current game uses a square
                     * platform.
                     */
                    int randNo = Greenfoot.getRandomNumber(100);
                    if(randNo < 70) {// insert coin with 20% probability
                        int mapX = x * PLATFORMWIDTH + PLATFORMWIDTH/2; //Pixel location times width of platform plus half its width
                        int mapY = y * PLATFORMHEIGHT +PLATFORMHEIGHT/2;//Pixel location times height of platform plus half its height
                        thePlatforms.add(new Platform(mapX,mapY));

                    }
                    //Adds the platform with its x and y coordinate
                    //in the map.

                }

                if(colorRGB != Color.BLACK.getRGB()) //Check to see if it's white
                {
                    int randNum = Greenfoot.getRandomNumber(100);
                    if(randNum < 3) {// insert coin with 20% probability
                        int mapX = x* COINWIDTH + COINWIDTH/2; //Pixel location times width of coin plus half its width
                        int mapY = y* COINHEIGHT + COINHEIGHT/2;//Pixel location times height of coin plus half its height
                        theCoins.add(new Coin(mapX,mapY));

                    }

                    int SpecialCoinProb = Greenfoot.getRandomNumber(1000);
                    if (SpecialCoinProb < 3){ // 0.2% probability
                        int mapX = x* SPECIALCOINWIDTH + SPECIALCOINWIDTH/2; //Pixel location times width of coin plus half its width
                        int mapY = y* SPECIALCOINHEIGHT + SPECIALCOINHEIGHT/2;//Pixel location times height of coin plus half its height
                        theSpecialCoins.add(new SpecialCoin(mapX,mapY));
                    }

                    int lifeProb = Greenfoot.getRandomNumber(1000);
                    if (lifeProb < 1.5){ // 0.2% probability
                        int mapX = x* LIFEWIDTH + LIFEWIDTH/2; //Pixel location times width of coin plus half its width
                        int mapY = y* LIFEHEIGHT + LIFEHEIGHT/2;//Pixel location times height of coin plus half its height
                        theLives.add(new Life(mapX,mapY));
                    }

                    int enemy1Prob = Greenfoot.getRandomNumber(100);
                    // System.out.println(enemyprob);
                    if(enemy1Prob < 0.1) {// insert coin with 20% probability

                        int mapX= x* PLATFORMWIDTH + PLATFORMWIDTH/2; //Pixel location times width of coin plus half its width
                        int mapY = y* PLATFORMHEIGHT +PLATFORMHEIGHT/2;
                        theEnemy1.add(new Enemy1(mapX,mapY));
                    }

                    int enemy2Prob = Greenfoot.getRandomNumber(100);
                    if(enemy2Prob < 0.1) {// insert coin with 20% probability

                        int mapX= x* PLATFORMWIDTH + PLATFORMWIDTH/2; //Pixel location times width of coin plus half its width
                        int mapY = y* PLATFORMHEIGHT +PLATFORMHEIGHT/2;
                        theEnemy2.add(new Enemy2(mapX,mapY));
                    }

                    int enemy3Prob = Greenfoot.getRandomNumber(100);
                    if(enemy3Prob < 0.1) {// insert coin with 20% probability

                        int mapX= x* PLATFORMWIDTH + PLATFORMWIDTH/2; //Pixel location times width of coin plus half its width
                        int mapY = y* PLATFORMHEIGHT +PLATFORMHEIGHT/2;
                        theEnemy3.add(new Enemy3(mapX,mapY));
                    }
                }
            }
        }

    }

    public void updateList(ListIterator<Collectible> l) {
        Actor thisElement;  // This will be the platform we are working with
        int thisElementX;      // This will be the X coordinate of the platform on the map
        int thisElementY;      // This will be the Y coordinate of the platform on the map
        int screenX;            // If the platform is on the screen, this will be its screen X coordinate
        int screenY;            // If the platform is on the screen, this will be its screen Y coordinate
        int WIDTH  ;
        int HEIGHT;

        //for(int i=0; i<thePlatforms.size(); i++) //Go through each of the platforms we created in makeMap()
        while(l.hasNext()) 
        {
            thisElement = l.next(); //Assign the current platform to thisPlatform variable
            thisElementX = thisElement.getX();  //Assign its map X coordinate to thisPlatformX
            thisElementY = thisElement.getY();  //Assign its map Y coordinate to thisElementY
            WIDTH = thisElement.getImage().getWidth();
            HEIGHT = thisElement.getImage().getHeight();

            /*
             * This conditional will compare the map X and Y coordinates to the left, right, top and
             * bottom bounds of the screen to see if it should be on the map.
             */

            //Line below changed, added +PLATFORMWIDTH etc...
            if(thisElementX+WIDTH>=leftBound && thisElementX-WIDTH<=rightBound && thisElementY + HEIGHT >=topBound && thisElementY-HEIGHT <= bottomBound)
            {                                           //If it belongs on the map...
                screenX = thisElementX - leftBound;    //...the screen X will be the map X minus left bound...
                screenY = thisElementY - topBound;     //... and the screen Y will be map Y minus left bound.
                if(thisElement.getWorld()==null)               //If it is not on the map...
                {
                    addObject(thisElement, screenX, screenY);  //...then add it with the right coordinates.
                } else {                                        //If it's already on the map...
                    thisElement.setLocation(screenX, screenY); //...then move it to the right coordinates.
                } 
            }else {                                     //If it doesn't belong on the map...
                if(thisElement.getWorld()!=null)   //...check to see if it's on the map.
                {                                   //If it is...
                    removeObject(thisElement);     //...then remove it.
                }
            }
        }

    }

    public void act() 
    {
        if (mario.isAtEdge() ) 
        {
            GameOver g = new successGameOver(mario);
            addObject(g, getWidth()/2, getHeight()/2);
            Greenfoot.stop();
        }
    }

    /*
     * The shiftScreen makes the screen scroll when we need it to. 
     */
    public void shiftScreen(int changeX, int changeY)   // This method shifts the screen up, down,
    {                                                   // left and right.
        leftBound += changeX;                       // Move the left bound by the change in X
        rightBound += changeX;                      // Move the right bound by the change in X
        if(leftBound <0)                            // If the left bound goes off the map...
        {                
            leftBound =0;                           // ...set it to the left side of the map...
            rightBound = getWidth();                // ...and move the right bound to the right place.
        } else if(rightBound >= MAPWIDTH)           // If the right bound goes off the map...
        {
            rightBound = MAPWIDTH;                  // ...set right bound to right side of map...
            leftBound = MAPWIDTH - getWidth();      // ...and move left bound to right place.
        }

        topBound -= changeY;                        // Move the top bound and the bottom bound
        bottomBound -= changeY;                     // by the change in Y. 
        if(topBound <0)                             // If the top bound moves off the map...
        {
            topBound =0;                            //...move the top bound to top of map
            bottomBound = getHeight();              //...and move the bottom bound to right place.
        } else if (bottomBound > MAPHEIGHT)         // If the bottom bound goes off the map...
        {
            bottomBound = MAPHEIGHT;                // ...move the bottom bound to bottom of map...
            topBound = MAPHEIGHT - getHeight();     // ...and move the top bound to right place.
        }

        update();                                       // Now redraw the screen. 
    }

    /*
     * The update() method draws each platform in the correct place, and moves each platform already
     * on the screen to the correct place. When a platform goes off the screen
     * it will remove it, and it will add a platform if it comes onto the screen.
     */
    public void update()
    {
        if (mario.isAtEdge()) {
            GameOver g = new GameOver();
            addObject(g, getWidth()/2, getHeight()/2);
            Greenfoot.stop();
        }

        
        Platform thisPlatform;  // This will be the platform we are working with
        int thisPlatformX;      // This will be the X coordinate of the platform on the map
        int thisPlatformY;      // This will be the Y coordinate of the platform on the map
        int screenX;            // If the platform is on the screen, this will be its screen X coordinate
        int screenY;            // If the platform is on the screen, this will be its screen Y coordinate

        for(int i=0; i<thePlatforms.size(); i++) //Go through each of the platforms we created in makeMap()
        {
            thisPlatform = thePlatforms.get(i); //Assign the current platform to thisPlatform variable
            thisPlatformX = thisPlatform.mapX;  //Assign its map X coordinate to thisPlatformX
            thisPlatformY = thisPlatform.mapY;  //Assign its map Y coordinate to thisPlatformY

            /*
             * This conditional will compare the map X and Y coordinates to the left, right, top and
             * bottom bounds of the screen to see if it should be on the map.
             */

            //Line below changed, added +PLATFORMWIDTH etc...
            if(thisPlatformX+PLATFORMWIDTH>=leftBound && thisPlatformX-PLATFORMWIDTH<=rightBound && thisPlatformY + PLATFORMHEIGHT >=topBound && thisPlatformY-PLATFORMWIDTH <= bottomBound)
            {                                           //If it belongs on the map...
                screenX = thisPlatformX - leftBound;    //...the screen X will be the map X minus left bound...
                screenY = thisPlatformY - topBound;     //... and the screen Y will be map Y minus left bound.
                if(thisPlatform.getWorld()==null)               //If it is not on the map...
                {
                    addObject(thisPlatform, screenX, screenY);  //...then add it with the right coordinates.
                } else {                                        //If it's already on the map...
                    thisPlatform.setLocation(screenX, screenY); //...then move it to the right coordinates.
                } 
            }else {                                     //If it doesn't belong on the map...
                if(thisPlatform.getWorld()!=null)   //...check to see if it's on the map.
                {                                   //If it is...
                    removeObject(thisPlatform);     //...then remove it.
                }
            }
        }

        updateList(theCoins.listIterator());
        updateList(theSpecialCoins.listIterator());
        updateList(theLives.listIterator());

        updateEnemyList(theEnemy1.listIterator());
        updateEnemyList(theEnemy2.listIterator());
        updateEnemyList(theEnemy3.listIterator());
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {

    }

    public void updateEnemyList(ListIterator<Enemies> l) {
        Actor thisElement;  // This will be the platform we are working with
        int thisElementX;      // This will be the X coordinate of the platform on the map
        int thisElementY;      // This will be the Y coordinate of the platform on the map
        int screenX;            // If the platform is on the screen, this will be its screen X coordinate
        int screenY;            // If the platform is on the screen, this will be its screen Y coordinate
        int WIDTH  ;
        int HEIGHT;

        //for(int i=0; i<thePlatforms.size(); i++) //Go through each of the platforms we created in makeMap()
        while(l.hasNext()) 
        {
            thisElement = l.next(); //Assign the current platform to thisPlatform variable
            thisElementX = thisElement.getX();  //Assign its map X coordinate to thisPlatformX
            thisElementY = thisElement.getY();  //Assign its map Y coordinate to thisElementY
            WIDTH = thisElement.getImage().getWidth();
            HEIGHT = thisElement.getImage().getHeight();

            /*
             * This conditional will compare the map X and Y coordinates to the left, right, top and
             * bottom bounds of the screen to see if it should be on the map.
             */

            //Line below changed, added +PLATFORMWIDTH etc...
            if(thisElementX+WIDTH>=leftBound && thisElementX-WIDTH<=rightBound && thisElementY + HEIGHT >=topBound && thisElementY-WIDTH <= bottomBound)
            {                                           //If it belongs on the map...
                screenX = thisElementX - leftBound;    //...the screen X will be the map X minus left bound...
                screenY = thisElementY - topBound;     //... and the screen Y will be map Y minus left bound.
                if(thisElement.getWorld()==null)               //If it is not on the map...
                {
                    addObject(thisElement, screenX, screenY);  //...then add it with the right coordinates.
                } else {                                        //If it's already on the map...
                    thisElement.setLocation(screenX, screenY); //...then move it to the right coordinates.
                } 
            }else {                                     //If it doesn't belong on the map...
                if(thisElement.getWorld()!=null)   //...check to see if it's on the map.
                {                                   //If it is...
                    removeObject(thisElement);     //...then remove it.
                }
            }
        }

    }
}
