package ru.electra;

import javax.swing.*;

public class MainForm extends JFrame{

    private JButton helpButton;
    private JButton exitButton;
    private JPanel rootPanel;
    private JTextField fieldComfort;
    private JTextField fieldSpeed;
    private JTextField fieldDesign;
    private JTextField fieldPrice;
    private JTextPane textPanePrice;
    private JTextPane textPaneComfort;
    private JTextPane textPaneSpeed;
    private JTextPane textPaneDesign;
    private JButton calculateButton;

    private Integer price,comfort,speed,design;
    Calc calc;


    private void createUIComponents() {
        // TODO: place custom component creation code here

    }

    private void Init() {
        setContentPane(rootPanel);
        setTitle("Ранжирование многокритериальных альтернатив по методу ELECTRE (18ВПм, Панов");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private MainForm() {
        Init();

        exitButton.addActionListener(actionEvent -> System.exit(0));
        calculateButton.addActionListener(actionEvent -> collectFields());
        //showMatrix.addActionListener(actionEvent -> generate());
    }

    private void collectFields(){
        price = Integer.parseInt(fieldPrice.getText());
        comfort = Integer.parseInt(fieldComfort.getText());
        speed = Integer.parseInt(fieldSpeed.getText());
        design = Integer.parseInt(fieldDesign.getText());

        calc = new Calc(price,comfort,speed,design);
        new ResultsForm(calc);
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //new Calc(5,4,3,3);
        //new Calc();
        //new Initializer();
        new MainForm();

    }
}