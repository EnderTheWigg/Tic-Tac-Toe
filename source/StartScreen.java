package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartScreen {
    private JFrame frame;
    private String botDifficulty;

    public StartScreen(){
        init();
    }

    private void init(){
        frame = new JFrame("Start Screen");
        frame.setSize(400, 400);
        String[] botOptions = {"AI Difficulty", "None", "Random", "Semi-Random", "Perfect"};
        botDifficulty = botOptions[0];

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);
        

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener((ActionEvent e)-> {
            new GameBoard(botDifficulty);
            frame.dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(startButton, gbc);

        JComboBox botOptionsBox = new JComboBox(botOptions);
        botOptionsBox.setSelectedIndex(0);
        
        botOptionsBox.addActionListener((ActionEvent e)-> {
            JComboBox cb = (JComboBox)e.getSource();
            botDifficulty = (String)cb.getSelectedItem();
            
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(botOptionsBox, gbc);

        JButton quitButton = new JButton("Quit Game");
        quitButton.addActionListener((ActionEvent e)-> {
            frame.dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(quitButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
        panel.setVisible(true);

    }
}
