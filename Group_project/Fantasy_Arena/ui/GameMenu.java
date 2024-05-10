package Fantasy_Arena.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Fantasy_Arena.core.*;
import Fantasy_Arena.levels.LevelDatabase;

public class GameMenu extends JFrame {
    private JButton playButton;
    private Player player1;
    private Player player2;
    private Champion[] allChampions = {
        new Warrior(),
        new Mage(),
        new Assassin(),
        new Marksman(),
        new Tank(),
        new Support(),
        new Brawler()
    };
    private ChampionButton lastHighlightedPlayer1Button = null;
    private ChampionButton lastHighlightedPlayer2Button = null;
    private boolean isPlayer1Confirmed = false;
    private boolean isPlayer2Confirmed = false;

    public GameMenu() {
        setTitle("Fantasy Arena");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    
        Color backgroundColor = new Color(200, 200, 200);
        getContentPane().setBackground(backgroundColor);
    
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Fantasy Arena");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(backgroundColor);
    
        JPanel imagePanel = new JPanel();
        ImageIcon icon = new ImageIcon("img/title_screen.png");
        JLabel imageLabel = new JLabel(icon);
        imagePanel.add(imageLabel);
    
        Color buttonColor = new Color(50, 100, 200); 
        playButton = new JButton("Play");
        playButton.setBackground(buttonColor);
        playButton.setForeground(Color.WHITE);
        playButton.setPreferredSize(new Dimension(100, 40));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCharacterChoiceMenu();
                dispose();
            }
        });
    
        JPanel playPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        playPanel.setBackground(backgroundColor);
        playPanel.add(playButton);
    
        add(titlePanel, BorderLayout.NORTH);
        add(imagePanel, BorderLayout.CENTER);
        add(playPanel, BorderLayout.SOUTH);
    
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openCharacterChoiceMenu() {
        JFrame characterChoiceFrame = new JFrame("Character Choice Menu");
        characterChoiceFrame.setSize(800, 600);
        characterChoiceFrame.setLayout(new GridLayout(2, 1));

        JPanel player1Panel = createCharacterChoicePanel("Player 1", characterChoiceFrame);
        characterChoiceFrame.add(player1Panel);

        JPanel player2Panel = createCharacterChoicePanel("Player 2", characterChoiceFrame);
        characterChoiceFrame.add(player2Panel);

        characterChoiceFrame.setLocationRelativeTo(null);
        characterChoiceFrame.setVisible(true);
    }



    private JPanel createCharacterChoicePanel(String playerName, JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(1, allChampions.length + 1)); // +1 for confirm button
        JLabel label = new JLabel(playerName);
        panel.add(label);
    
        for (Champion champion : allChampions) {
            ChampionButton championButton = new ChampionButton(champion);
            championButton.setPreferredSize(new Dimension(150, 50));
            championButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    ChampionButton source = (ChampionButton) e.getSource();
                    if (playerName.equals("Player 1")) {
                        if(isPlayer1Confirmed)
                            return;
                        
                        if (lastHighlightedPlayer1Button != null) {
                            lastHighlightedPlayer1Button.unhighlight();
                        }
                        lastHighlightedPlayer1Button = source;
                        player1 = new Player(playerName, champion.copy());
                    } else {
                        if(isPlayer2Confirmed)
                            return;

                        if (lastHighlightedPlayer2Button != null) {
                            lastHighlightedPlayer2Button.unhighlight();
                        }
                        lastHighlightedPlayer2Button = source;
                        player2 = new Player(playerName, champion.copy());
                    }
    
                    source.highlight();
                }
            });
            panel.add(championButton);
        }
    
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playerName.equals("Player 1")){
                    lastHighlightedPlayer1Button.highlight(Color.RED);
                    isPlayer1Confirmed = true;
                } else if(playerName.equals("Player 2")){
                    lastHighlightedPlayer2Button.highlight(Color.RED);
                    isPlayer2Confirmed = true;
                }
                if(isPlayer1Confirmed && isPlayer2Confirmed){
                    openLevelChoosingMenu();
                    frame.dispose();
                }

            }
        });
        panel.add(confirmButton);
    
        return panel;
    }
    

    private void openLevelChoosingMenu() {
        JFrame levelChoosingFrame = new JFrame("Level Choosing Menu");
        levelChoosingFrame.setSize(300, 200); 
        levelChoosingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        levelChoosingFrame.setLayout(new BorderLayout());
        
        Color backgroundColor = new Color(200, 200, 200); 
        levelChoosingFrame.getContentPane().setBackground(backgroundColor);
        
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Choose a Level");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(backgroundColor); 
        
        JPanel levelButtonsPanel = new JPanel(new GridLayout(3, 1, 0, 10)); // Adjust rows, columns, and gaps
        levelButtonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        
        for(int i = 0; i < 3; i++) {
            final int level = i;
            JButton levelButton = new JButton("Level " + (i + 1));
            levelButton.setBackground(new Color(50, 100, 200)); 
            levelButton.setForeground(Color.WHITE); 
            levelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startGame(level);
                    levelChoosingFrame.dispose();
                }
            });
            levelButtonsPanel.add(levelButton);
        }
        
        levelChoosingFrame.add(titlePanel, BorderLayout.NORTH);
        levelChoosingFrame.add(levelButtonsPanel, BorderLayout.CENTER);
        
        levelChoosingFrame.setLocationRelativeTo(null); 
        levelChoosingFrame.setVisible(true);
    }

    private void startGame(int level) {
        LevelDatabase database = new LevelDatabase();
        Champion[] player1Team = database.getTeams(level)[0];
        Champion[] player2Team = database.getTeams(level)[1];
        try{
            new GameUI(new Game(player1, player1Team, player2, player2Team));
        } catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}

