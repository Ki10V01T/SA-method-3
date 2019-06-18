package ru.electra;


interface Price {
    void setWeight(Integer weight);
    void setDirection(String direction);
    void setPrice(Integer price);
    Integer getPrice();
    Integer getWeight();
    String getDirection();
}

interface Comfort {
    void setWeight(Integer weight);
    void setDirection(String direction);
    void setComfort(String comfort);
    String getComfort();
    Integer getWeight();
    String getDirection();
}

interface Speed {
    void setWeight(Integer weight);
    void setDirection(String direction);
    void setSpeed(String speed);
    String getSpeed();
    Integer getWeight();
    String getDirection();
}

interface Design {
    void setWeight(Integer weight);
    void setDirection(String direction);
    void setDesign(String design);
    String getDesign();
    Integer getWeight();
    String getDirection();
}
