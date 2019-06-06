package ru.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame{

    private JButton helpButton;
    private JButton exitButton;
    private JPanel rootPanel;


    private void createUIComponents() {
        // TODO: place custom component creation code here

    }

    private void Init(){
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private MainForm() {
        Init();

        exitButton.addActionListener(actionEvent -> System.exit(0));
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        new MainForm();

    }
}
