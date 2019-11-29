import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.lang.reflect.*;
import javax.swing.Timer;

/**
 * Write a description of class initMinesweeper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class initMinesweeper extends World
{

    /**
     * Constructor for objects of class initMinesweeper.
     * 
     */
    
    public initMinesweeper()
    {    
        // 9x9 with 10 mines is standard for minesweeper.
        super(9, 9, 37); 
        setBackground("bg.png");
        spawnBlocks();
        
        //Add a delay to avoid grabbing objects that don't exist.
        Greenfoot.delay(1);
        spawnMines();
        Greenfoot.setSpeed(65);
        //reiterateBlocks();
    }
    
    //Spawns the blocks
    public void spawnBlocks()
    {
        for(int x=0;x<=8;x++)
        {
            for(int y=0;y<=8;y++)
            {
                addObject(new block(),x,y);
            }
        }
    }

    //After the blocks have been placed, randomly choose where to place mines.
    public void spawnMines()  
    {  
        for (int mines=0; mines<10; mines++)  
        {  
            boolean found = false;  
            while (!found)  
            {  
                int x = Greenfoot.getRandomNumber(9);  
                int y = Greenfoot.getRandomNumber(9);  
                block b = (block)getObjectsAt(x, y, null).get(0);
                if (b.blockStatus != 6)
                {  
                    b.blockStatus = 6;
                    infectSurroundingBlocks(b);
                    found = true;  
                }
            }
        }
    }  

    //Increases the number of the blocks surrounding the mine.
    public void infectSurroundingBlocks(block block)
    {
        for(int x=0; x<3; x++)
        {
            for(int y=0; y<3; y++)
            {
                int iBlockx = block.getX() + x - 1;
                int iBlocky = block.getY() + y - 1;
                if(iBlockx>=0&&iBlocky>=0&&iBlockx<=8&&iBlocky<=8)
                {
                    block b = (block)getObjectsAt(iBlockx, iBlocky, null).get(0);
                    if(b.blockStatus!=6)
                    {
                        b.blockStatus++;
                    }
                }
            }
        }
    }
    
    //Goes through the grid and assigns numbers to vars
/*    public void reiterateBlocks()
    {
        for(int x=0;x<=8;x++)
        {
            for(int y=0;y<=8;y++)
            {
                block b = (block)getObjectsAt(x, y, null).get(0);
                if(b.blockStatus==6)
                {
                    for(int xx=0; xx<=2; xx++)
                    {
                        for(int yy=0; yy<=0; yy++)
                        {
                            if(((x+xx)-1)<8&&((x+xx)-1)>0)
                            {
                                block b2 = (block)getObjectsAt((x+xx)-1, (y+yy)+1, null).get(0);
                                if(b2.blockStatus!=6)
                                {
                                    b2.blockStatus++;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
*/
    
    public boolean checkVictory()
    {
        for(int x=0; x<8; x++)
        {
            for(int y=0; y<8; y++)
            {
                block b = (block)getObjectsAt(x, y, null).get(0);
                if(!b.isClicked && b.blockStatus!=6)
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void winGame()
    {
        Greenfoot.delay(500);
        Greenfoot.setWorld(new YouWin());
    }

    //You lost. Stop the game.
    public void loseGame()
    {
        Greenfoot.delay(750);
        Greenfoot.setWorld(new YouLose());
    }
}
