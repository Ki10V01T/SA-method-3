package ru.electra;

class FinishAlteratives {
    private Integer idAlt1, idAlt2;
    private Double value;

    public void setId(Integer idAlt1, Integer idAlt2){
        this.idAlt1 = idAlt1;
        this.idAlt2 = idAlt2;
    }

    public void setValue(Double value){
        this.value = value;
    }

    public Integer getIdAlt1(){return idAlt1;}
    public Integer getIdAlt2(){return idAlt2;}
    public Double getValue(){return value;}
}
