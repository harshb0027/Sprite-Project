/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spriteharsh;

import com.mycompany.spriteharsh.SpriteFacade;
import cst8218.bans0027.entity.Sprite;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;


/**
 *
 *@author harsh
 * Student Number - 041005659
 * This class implements the basic sprite logic. In this upon statrting the application, the thread id generated which will be active for 100 ms. This thread will retrieve all 
 * the sprites and run them under the area of 500 by 500. The position is changed continuosly until the thread is mot going to sleep. If the position comes out to be more than 
 * 500 or less than 0 then it bounces off from that wall. After the execution, the program goes to sleep.
 * 
 * This class is a singleton --only one object is needed to co-ordinate with the actions and shuld directly run on the STARTUP of the application
 */
@Startup
@Singleton
public class SpriteGame {
    
    private Integer xSize = 500;
    private Integer ySize = 500;
    private List<Sprite> spriteList;
    @Inject
    SpriteFacade appSession;               
    
    /*
    
    
    */
    //running the whole thread of the sprite session
    /**
     * function that will bounce the sprites. If the sprites will go out of range then it will bounce off the sprites and brings them in the range.
     * @param s -- Sprite entity being bounced
     */
    private void bounce(Sprite s){
        if((s.getXValue() < 0 ) && (s.getXSpeed() < 0)){
            s.bounceLeftWall();
        }
        if((s.getYValue() < 0 ) && (s.getYSpeed() < 0)){
            s.bounceTopWall();
        }
        if((s.getXValue() > xSize) && (s.getXSpeed() > 0) ){
            s.bounceRightWall();
        }
        if((s.getYValue() > ySize) && (s.getYSpeed() > 0)){
            s.bounceBottomWall();
        }
    }
    
    
    
    /**
     * This method runs directly after the constructor and will implement the whole program 
     * business logic:-  retrieve all the sprites from the database storing them into the list and move them all for a certain time period or number of times 
    and then sleep for certain period say 100 ms or 10s and then again do the same thing. So kind of thread is running for a certain time and then the thread will sleep
     */
    @PostConstruct
    public void go(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    spriteList = appSession.findAll();
                    //System.out.println("All sprites appear");
                    for(Sprite sprite:spriteList){
                        //System.out.println("X = " + sprite.getXValue() + " YValue = " + sprite.getYValue());
                        sprite.move();
                         //System.out.println("X = " + sprite.getXValue() + " YValue = " + sprite.getYValue());
                        //System.out.println("Sprites moved");
                        bounce(sprite);
                        appSession.edit(sprite);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SpriteGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }            
            }
        }).start();
    }

    
    
}
