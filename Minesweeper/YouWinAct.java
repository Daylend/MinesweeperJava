import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class YouWinAct here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class YouWinAct extends Actor
{
    /**
     * Act - do whatever the YouWinAct wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(new initMinesweeper());
        }
    }
}
