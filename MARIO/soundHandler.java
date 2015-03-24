import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
anusha
 */
public interface soundHandler {

    void handleRequest( String request );
    void setSuccessor(soundHandler next);

}


