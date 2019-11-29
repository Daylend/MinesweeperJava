import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class block extends Actor
{
    public int blockStatus = 0;
    public boolean isClicked = false;
    public boolean isFlagged = false;
    
    public void updateBlock()
    {
        if(!this.isClicked&&this.isFlagged)
        {
            this.setImage("flag.png");
        }
        if(this.isClicked)
        {
            switch(this.blockStatus)
            {
                case 0:
                    this.setImage("bg.png");
                    break;
                case 1:
                    this.setImage("1.png");
                    break;
                case 2:
                    this.setImage("2.png");
                    break;
                case 3:
                    this.setImage("3.png");
                    break;
                case 4:
                    this.setImage("4.png");
                    break;
                case 5:
                    this.setImage("5.png");
                    break;
                case 6:
                    this.setImage("mine.png");
                    break;
            }
        }
    }
    
    public void onClick()
    {
        this.isClicked = true;
        this.updateBlock();
        //If you clicked on a mine, you lose. Otherwise keep playing.
        if(this.blockStatus==6)
        {
            initMinesweeper minesweeper = new initMinesweeper();
            minesweeper.loseGame();
        }
        //Could be a seperate method but this is easier. If nearby blocks also have a status of 0,
        //it simulates a click and does a check for nearby blocks again. Like a virus! :)
        else if(this.blockStatus==0)
        {
            for (Object obj : getNeighbours(1, true, block.class))
            {
                block b = (block) obj;
                if(!b.isClicked && b.blockStatus==0)
                {
                    b.onClick();
                    b.updateBlock();
                }
                else if (!b.isClicked && b.blockStatus<6 && b.blockStatus>0)
                {
                    b.isClicked = true;
                    b.updateBlock();
                }
            }
        }
    }
    

    
    //Checks for onClick and possibly keyDown later.
    public void act()
    {
        //If you clicked a block, executes onclick method and checks if you've won the game.
        //If you're also holding control it flags the block instead.
        if(Greenfoot.mouseClicked(this) && !Greenfoot.isKeyDown("control"))
        {
            this.onClick();
            Greenfoot.delay(1);
            initMinesweeper minesweeper = (initMinesweeper)getWorld();
            if(minesweeper.checkVictory())
            {
                minesweeper.winGame();
            }
        }
        else if (Greenfoot.mouseClicked(this) && Greenfoot.isKeyDown("control"))
        {
            this.isFlagged = true;
            this.updateBlock();
        }
    }    
}
