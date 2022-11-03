package com.example.projectud1_agg_prl;

public class Skill {

    public Skill(String name, int level, int id) {
        this.name = name;
        this.level = level;
        this.id = id;
    }

    public String name;

    public int level;

    public int id;


    public String getName() {
        return name;
    }


    public int getLevel() {
        return level;
    }

    public int getId() {
        return id;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
