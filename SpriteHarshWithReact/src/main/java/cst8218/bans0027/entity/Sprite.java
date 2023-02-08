/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.bans0027.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author harsh
 * Student Number - 041005659
 * This class basically creates or defines an entity in the database.
 */
@Entity
@XmlRootElement
public class Sprite implements Serializable{
    public static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   
    private Integer xValue;
    
    
    private Integer yValue;
    private Integer xSpeed;
    private Integer ySpeed;
    private List<Sprite> listOfSprites;
    
    /**
     * getter for id
     * @return -- the id of the entity of type Long
     */
    public Long getId(){
        return id;
    }
    
    /**
     * getter of xValue
     * @return type = Integer
     */
    public Integer getXValue(){
        return xValue;
    }
    
    public void setXValue(Integer xValue){
        this.xValue = xValue;
        
    }
    
    /**
     * getter of YValue
     * @return type = Integer
     */
    public Integer getYValue(){
        return yValue;
    }
    
     /**
     * setter of YValue
     * assigns that value in the parameter
     */
    public void setYValue(Integer yValue){
        this.yValue = yValue;
    }
    
     /**
     * getter of XSpeed
     * @return type = Integer
     */
    public Integer getXSpeed(){
        return xSpeed;
    }
    
    public void setXSpeed(Integer xSpeed){
        this.xSpeed = xSpeed;
    }
    
     /**
     * getter of YSpeed
     * @return type = Integer
     */
    public Integer getYSpeed(){
        return ySpeed;
    }
    
    public void setYSpeed(Integer ySpeed){
        this.ySpeed = ySpeed;
    }
    
    
    /**
     * This method changes position of the sprites
     */
    public void move(){
        xValue+=xSpeed;
        yValue+=ySpeed;
        
    }
    
    /**
     * This method bounces the sprite through the left wall. The matter of fact is that if the sprite is having an index out of range then it will make it's speed negative of that value
     * If the speed is -4 then it will make it -(-4) = 4
     */
    public void bounceLeftWall(){
        xSpeed = -xSpeed;
    }
    
    /**
     * This method bounces the sprite through the right wall
     */
    public void bounceRightWall(){
        xSpeed = -xSpeed;
    }
    
    /**
     * This method bounces the sprite through the top wall
     */
    public void bounceTopWall(){
        ySpeed = -ySpeed;
    }
    
    /**
     * This method bounces the sprite through the bottom wall
     */
    public void bounceBottomWall(){
        ySpeed = -ySpeed;
    }
    
    
    /**
     * This method is implemented in RESTful API's. It basically is updating the existing sprite by only changing the non-null values in the body of the request
     * @param newSprite -- the entity in the body of the request
     * @param oldSprite  -- the database entity that is updated
     */
    public void updateSprite(Sprite newSprite, Sprite oldSprite){  
        
        if(newSprite.getXValue() != null){
            oldSprite.setXValue(newSprite.getXValue());
            
        }
        if(newSprite.getYValue() != null){
            oldSprite.setYValue(newSprite.getYValue());
            
        }   
        if(newSprite.getXSpeed() != null){
            oldSprite.setXSpeed(newSprite.getXSpeed());
        }
        if(newSprite.getYSpeed() != null){
            oldSprite.setYSpeed(newSprite.getYSpeed());
        }
        
    }
    
    
    
    /**
     * This method is implemented in RESTful API's. It basically is replacing the existing sprite by replacing the existing sprite with the one in the body fo the request
     * @param newSprite -- the entity in the body of the request
     * @param oldSprite  -- the database entity that is replaced with newSprite values
     */
    public void replaceSprite(Sprite newSprite, Sprite oldSprite){
        if(newSprite.getXValue() != null){
            oldSprite.setXValue(newSprite.getXValue());
            
        }
        if(newSprite.getYValue() != null){
            oldSprite.setYValue(newSprite.getYValue());
            
        }   
        if(newSprite.getXSpeed() != null){
            oldSprite.setXSpeed(newSprite.getXSpeed());
        }
        if(newSprite.getYSpeed() != null){
            oldSprite.setYSpeed(newSprite.getYSpeed());
        }
        if(newSprite.getXValue() == null){
            oldSprite.setXValue(null);
            
        }
        if(newSprite.getYValue() == null){
            oldSprite.setYValue(null);
            
        }   
        if(newSprite.getXSpeed() == null){
            oldSprite.setXSpeed(null);
        }
        if(newSprite.getYSpeed() == null){
            oldSprite.setYSpeed(null);
        }
    }
   
    //bouncing the entities ---- on colliding with the wall then change their direction
}
