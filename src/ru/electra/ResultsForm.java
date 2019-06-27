package ru.electra;

import javax.swing.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class ResultsForm extends JFrame{
    private JPanel rootPanel;
    private JButton closeButton;
    private JButton viewButton;
    private JTextArea TextArea1;
    private JTextArea textArea2;

    private FinishAlteratives objFi;
    private HashSet<Double> alternatives = new HashSet<>();
    private ArrayList<FinishAlteratives> fi = new ArrayList<>();
    private Double[][] preferenceMatrix;

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }

    private void Init() {
        setContentPane(rootPanel);
        setTitle("Ранжирование многокритериальных альтернатив по методу ELECTRE (18ВПм, Панов");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public ResultsForm(Calc calc) {
        Init();

        closeButton.addActionListener(actionEvent -> this.dispose());
        viewButton.addActionListener(actionEvent -> range_alternatives(calc));
    }

    private void range_alternatives(Calc calc){
        textArea2.removeAll();
        preferenceMatrix = calc.getPreferenceMatrix();

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                alternatives.add(preferenceMatrix[i][j]);
                }
            }

        foundMas();

        for (FinishAlteratives fa : fi){
            this.textArea2.append(fa.getIdAlt1().toString() + " в сравнении с " + fa.getIdAlt2() + ": " + fa.getValue().toString() + "\n");
        }
    }

    private void foundMas(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                for (Double o : alternatives) {
                    if(preferenceMatrix[i][j].equals(o)){
                        if(preferenceMatrix[i][j].equals(0.0)){break;}
                        objFi = new FinishAlteratives();
                        objFi.setId(i,j);
                        objFi.setValue(o);
                        fi.add(objFi);
                    }
                }
            }
        }
    }

}
