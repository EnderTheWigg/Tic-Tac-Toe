package source;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartScreen {
    private JFrame frame;
    private String botDifficulty;

    public StartScreen(){
        init();
    }

    private void init(){
        frame = new JFrame("Start Screen");
        frame.setSize(400, 400);
        String[] botOptions = {"None", "Random", "Semi-Random", "Perfect"};
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

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel(" "), gbc);

        JLabel comboBoxDescription = new JLabel("Choose AI: ");
        @SuppressWarnings({ "rawtypes", "unchecked" })
        JComboBox botOptionsBox = new JComboBox(botOptions);
        botOptionsBox.setSelectedIndex(0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(comboBoxDescription, gbc);
        
        botOptionsBox.addActionListener((ActionEvent e)-> {
            JComboBox cb = (JComboBox)e.getSource();
            botDifficulty = (String)cb.getSelectedItem();
            
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(botOptionsBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel(" "), gbc);

        JButton quitButton = new JButton("Quit Game");
        quitButton.addActionListener((ActionEvent e)-> {
            frame.dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(quitButton, gbc);

        
        frame.add(panel);
        frame.setVisible(true);
        panel.setVisible(true);

    }
}
