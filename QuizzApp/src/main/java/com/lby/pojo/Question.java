/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lby.pojo;

import com.lby.pojo.Category;
import com.lby.pojo.Choice;
import com.lby.pojo.Level;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Question {

    private int id;
    private String content;
    private String hint;
    private String image;
    private Category category;
    private Level level;
    private List<Choice> choices;

    private Question(Builder builder) {
        this.id = builder.id;
        this.content = builder.content;
        this.hint = builder.hint;
        this.image = builder.image;
        this.category = builder.category;
        this.level = builder.level;
        this.choices = builder.choices;
    }

    public static class Builder {
        private int id;
        private String content;
        private String hint;
        private String image;
        private Category category;
        private Level level;
        private List<Choice> choices = new ArrayList<>();
        
        public Builder(String content, Category category, Level level) throws Exception {
            if (content.isEmpty() || category == null || level == null)
                throw new Exception("Invalid data");
            
            this.content = content;
            this.category = category;
            this.level = level;
        }
        public Builder(int id,String content)  {
           this.id =id;
            this.content = content;
          
        }
        
        
        public Builder setHint(String h) {
            this.hint = h;
            return this;
        }
        
        public Builder setImage(String im) {
            this.image = im;
            return this;
        }
        
        public Builder addChoice(Choice c) {
            this.choices.add(c);
            return this;
        }
         public Builder addAllChoice(List<Choice> choices) {
            this.choices.addAll(choices);
            return this;
        }
        
        public Question build() {
            return new Question(this);
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the hint
     */
    public String getHint() {
        return hint;
    }

    /**
     * @param hint the hint to set
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * @return the choices
     */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * @param choices the choices to set
     */
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}