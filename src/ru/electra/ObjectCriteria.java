package ru.electra;

class ObjectCriteria implements Price, Comfort, Speed, Design {

    /**
     * direction направление для каждого критерия (Max/Min)
     */

    private String direction;
    private Integer weight;
    private Integer price;
    private String comfort, speed, design;
    private Integer value;


    public void setValue(Integer value){
        this.value = value;
    }

    public void setWeight(Integer weight){
        this.weight = weight;
    }

    public void setDirection(String direction){
        this.direction = direction;
    }

    @Override
    public void setPrice(Integer price){
        this.price = price;
    }

    @Override
    public void setComfort(String comfort){
        this.comfort = comfort;
    }

    @Override
    public void setSpeed(String speed){
        this.speed = speed;
    }

    @Override
    public void setDesign(String  design){
        this.design = design;
    }


    public Integer getValue(){return value;}
    public Integer getWeight(){return weight;}
    public String getDirection(){return direction;}

    @Override
    public Integer getPrice(){return price;}
    @Override
    public String getComfort(){return comfort;}
    @Override
    public String getSpeed(){return speed;}
    @Override
    public String getDesign(){return design;}
}
