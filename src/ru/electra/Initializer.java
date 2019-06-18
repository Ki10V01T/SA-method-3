package ru.electra;


class Initializer {
    private Price price;
    private Comfort comfort;
    private Speed speed;
    private Design design;

    private Object[][] matrixOfCritAlt = new ObjectCriteria[20][4];
    private Integer[] examplePrice = {30,30,30,30,25,25,25,25,20,20,20,20,15,15,15,15,10,10,10,10};
    private String[] exampleComfort = {"Ca", "Cb", "Ca", "Ca", "Cb", "Cb", "Cb", "Cb", "Ca", "Cb", "Ca", "Cb", "Cb", "Cc", "Ca",
            "Cb", "Ca","Cb", "Cb", "Cc"};
    private String[] exampleSpeed = {"Va", "Va", "Vb", "Va", "Va", "Vb", "Va", "Vb", "Vb", "Vc", "Vb", "Va", "Vc", "Vb", "Vc",
            "Va", "Vc","Vc", "Vc", "Vc"};
    private String[] exampleDesign = {"La", "La", "La", "Lb", "La", "La", "Lb", "La", "Lb", "Lc", "Lb", "Lc", "Lb", "Lb", "Lc",
            "Lc", "Lc","Lb", "Lc", "Lc"};
    private Integer[] verticalExampleWeight = {5,4,3,3};
    private String[] verticalExampleDirection = {"Min","Max","Max","Max"};

    Initializer(){
        initMatrix();
        //printMatrix();
    }

    private void initMatrix(){
        for (int i = 0; i<20; i++){
            for (int j = 0; j<4; j++) {
                switch (j) {
                    case (0):
                        price = new ObjectCriteria();
                        price.setPrice(examplePrice[i]);
                        price.setDirection(verticalExampleDirection[j]);
                        price.setWeight(verticalExampleWeight[j]);
                        matrixOfCritAlt[i][j] = price;
                        break;
                    case (1):
                        comfort = new ObjectCriteria();
                        comfort.setComfort(exampleComfort[i]);
                        comfort.setDirection(verticalExampleDirection[j]);
                        comfort.setWeight(verticalExampleWeight[j]);
                        matrixOfCritAlt[i][j] = comfort;
                        break;
                    case (2):
                        speed = new ObjectCriteria();
                        speed.setSpeed(exampleSpeed[i]);
                        speed.setDirection(verticalExampleDirection[j]);
                        speed.setWeight(verticalExampleWeight[j]);
                        matrixOfCritAlt[i][j] = speed;
                        break;
                    case (3):
                        design = new ObjectCriteria();
                        design.setDesign(exampleDesign[i]);
                        design.setDirection(verticalExampleDirection[j]);
                        design.setWeight(verticalExampleWeight[j]);
                        matrixOfCritAlt[i][j] = design;
                        break;
                }
            }
        }
    }

    private void printMatrix() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 4; j++) {
                switch (j){
                    case (0):
                        price = (Price)matrixOfCritAlt[i][j];
                        System.out.print(price.getPrice() + " + " + price.getDirection() + " ");
                        break;
                    case (1):
                        comfort = (Comfort) matrixOfCritAlt[i][j];
                        System.out.print(comfort.getComfort()+ " + " + comfort.getDirection() + " ");
                        break;
                    case (2):
                        speed = (Speed)matrixOfCritAlt[i][j];
                        System.out.print(speed.getSpeed() + " + " + speed.getDirection() + " ");
                        break;
                    case (3):
                        design = (Design)matrixOfCritAlt[i][j];
                        System.out.print(design.getDesign() + " + " + design.getDirection() + " ");
                        break;
                }
            }
            System.out.println();
        }
    }

    public Object getMatrixOfCritAlt(){return this.matrixOfCritAlt;}
    public Integer[] getVerticalExampleWeight(){return this.verticalExampleWeight;}
    public String[] getVerticalExampleDirection(){return this.verticalExampleDirection;}
}
