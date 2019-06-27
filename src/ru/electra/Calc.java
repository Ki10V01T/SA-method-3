package ru.electra;


import org.decimal4j.util.DoubleRounder;


public class Calc {

    /**
     * p - баллы за
     * n - баллы против
     * d - коэффициент значимости
     *
     * Рассмотрим, для примера, проекты 2 и 4 (i = 2, j = 4). По критерию «Цена» (вес критерия — 5 баллов)
     * проект 2 хуже проекта 4; по критерию «Комфортность» (вес — 4 балла) проект 2 лучше проекта 4; по критерию
     * «Скорость» (вес — 3 балла) проект 2 хуже проекта 4; по критерию «Дизайн» (вес — 3 балла) проект 2 лучше проекта 4.
     * Таким образом, имеем:
     * P24 = 0 + 4 + 0 + 3 = 7;
     * N24 = 5 + 0 + 3 + 0 = 8;
     * D24 =P24/N24 =7/8 = 0.875 < 1 — отбрасываем;
     */

    private Double p = 0.0;
    private Double n = 0.0;
    private Double d = 0.0;
    private Price price1, price2;
    private Comfort comfort1, comfort2;
    private Speed speed1, speed2;
    private Design design1, design2;
    private Initializer init = new Initializer();
    private Object[][] matrixOfCritAlt = init.getMatrixOfCritAlt();
    private Double[][] preferenceMatrix = new Double[20][20];

    Calc(Integer priceWeight, Integer comfortWeight, Integer speedWeight, Integer designWeight){
        setWeights(priceWeight, comfortWeight, speedWeight, designWeight);
        init_preferenceMatrix();
        print_preferenceMatrix();
    }

    private void setWeights(Integer priceWeight, Integer comfortWeight, Integer speedWeight, Integer designWeight){
        for (int i = 0; i<20; i++){
            for (int j = 0; j<4; j++) {
                switch (j) {
                    case (0):
                        price1 = (Price)matrixOfCritAlt[i][j];
                        price1.setWeight(priceWeight);
                        matrixOfCritAlt[i][j] = price1;
                        break;
                    case (1):
                        comfort1 = (Comfort)matrixOfCritAlt[i][j];
                        comfort1.setWeight(comfortWeight);
                        matrixOfCritAlt[i][j] = comfort1;
                        break;
                    case (2):
                        speed1 = (Speed)matrixOfCritAlt[i][j];
                        speed1.setWeight(speedWeight);
                        matrixOfCritAlt[i][j] = speed1;
                        break;
                    case (3):
                        design1 = (Design)matrixOfCritAlt[i][j];
                        design1.setWeight(designWeight);
                        matrixOfCritAlt[i][j] = design1;
                        break;
                }
            }
        }
    }

    private Double calculatePreference(Integer weightOfCrit1, Integer weightOfCrit2, Integer code, String direction){
        if (direction.equals("Min") && code.equals(1)){
            code = 0;
        }
        switch (code){
            case(0):{
                p += weightOfCrit1.doubleValue();
                n += 0.0;
                return 0.0;
            }
            case(1):{
                p += 0.0;
                n += weightOfCrit2.doubleValue();
                return 0.0;
            }
            case(2):{return -1111.0;}
            default:{return -1.0;}
        }
    }

    /**
     *
     * @param crit1
     * @param crit2
     * @param type 0 = price, 1 = comfort, 2 = speed, 3 = design;
     * @return
     */
    private Double calculator(Object crit1, Object crit2, Integer type){
        switch (type){
            case (0):{
                price1 = (Price)crit1;
                price2 = (Price)crit2;
                return (calculatePreference(price1.getWeight(), price2.getWeight(), comparator(price1.getPrice(), price2.getPrice(), "Integer"), price1.getDirection()));

            }
            case (1):{
                comfort1 = (Comfort)crit1;
                comfort2 = (Comfort)crit2;
                return (calculatePreference(comfort1.getWeight(),comfort2.getWeight(), comparator(comfort1.getComfort(), comfort2.getComfort(), "String"), comfort1.getDirection()));
            }
            case (2):{
                speed1 = (Speed)crit1;
                speed2 = (Speed)crit2;
                return (calculatePreference(speed1.getWeight(),speed2.getWeight(), comparator(speed1.getSpeed(), speed2.getSpeed(), "String"), speed1.getDirection()));
            }
            case (3):{
                design1 = (Design)crit1;
                design2 = (Design)crit2;
                return (calculatePreference(design1.getWeight(),design2.getWeight(), comparator(design1.getDesign(), design2.getDesign(), "String"), design1.getDirection()));
            }
            default:{return -1.0;}
        }
    }

    private void ins_value(int i, int j){
        d = p/n;
        if (d.isInfinite() || d < 1 || d.isNaN()){
            preferenceMatrix[i][j] = 0.0;
        }
        else{
            preferenceMatrix[i][j] = DoubleRounder.round(d,2);
            //preferenceMatrix[i][j] = d;
        }
        p = n = d = 0.0;
    }

    private void init_preferenceMatrix() {
        try {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (i == j){break;}
                        switch ((calculator(matrixOfCritAlt[i][k], matrixOfCritAlt[j][k], k)).intValue()) {
                            case (0): {
                                break;
                            }
                            case (-1): {
                                throw new Exception();
                            }
                            case (-1111): {
                                //preferenceMatrix[i][j] = 0.0;
                                break;
                            }
                        }
                    }
                    ins_value(i,j);
                }
            }
        }
        catch (Exception ex){
            System.out.print("Ошибка" + ex.getMessage());
        }
    }

    private void print_preferenceMatrix(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(preferenceMatrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private Integer comparator(Object valOfCrit1, Object valOfCrit2, String type){
        if(type.equals("Integer")){
            return compare_IntegerValues((Integer)valOfCrit1,(Integer)valOfCrit2);
        }
        else {
            return compare_StringValues((String)valOfCrit1, (String)valOfCrit2);
        }
    }

    /**
     *
     * @param valOfCrit1 Значение строкового критерия 1
     * @param valOfCrit2 Значение строкового критерия 2
     * @return Код результата сравнения: 0, если Критерий 1 > Критерия 2. 1 в противном случае. Если значения критериев равны, то код 2.
     * В случае ошибки - код 3.
     */
    private Integer compare_StringValues(String valOfCrit1, String valOfCrit2) {
        if (valOfCrit1.equals(valOfCrit2)){ return 2; }
        else {
            switch (valOfCrit1.charAt(1)) {
                case ('a'):{ return 0; }
                case ('b'):{ if (valOfCrit2.charAt(1) == 'a'){ return 1; }
                             else { return 0; } }
                case ('c'):{ return 1;}
                default:{return 3;}
            }
        }
    }

    private Integer compare_IntegerValues(Integer valOfCrit1, Integer valOfCrit2) {
        if(valOfCrit1.equals(valOfCrit2)){ return 2; }
        else{
            if (valOfCrit1 > valOfCrit2){ return 0; }
            else { return 1; }
        }
    }

    public Object[][] getMatrixOfCritAlt(){return this.matrixOfCritAlt;}
    public Double[][] getPreferenceMatrix(){return this.preferenceMatrix;}

}
