package main;

import jdbc.SkaneConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border; // Importa la clase Border

public class CobraMenuPanel extends JPanel implements ActionListener {

    CobraFrame frame;
    JButton startButton, optionsButton, exitButton, connectionButton, instructionsButton;
    JLabel connectionLabel, playerLabel;
    ConnectionPanel connectionPanel;
    JTextField playerField;
    JScrollPane scrollPane; // Panel de desplazamiento

    JPanel contentPanel; // Panel to hold all the components

    Music music;
    Music sfx;

    CobraMenuPanel(CobraFrame frame, Music music, Music sfx) {
        this.frame = frame;
        this.music = music;
        this.sfx = sfx;
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(0, 0, 0));
        setLayout(new BorderLayout()); // Use BorderLayout

        contentPanel = new JPanel(); // Create the content panel
        contentPanel.setLayout(null); // Use null layout for the content panel
        contentPanel.setPreferredSize(new Dimension(800, 800)); // Set the preferred size
        contentPanel.setBackground(new Color(154, 197, 3)); // Set the background color

        createLabelsAndButtons();

        refreshConnection();

        music.playMusic("src/main/resources/bazooka_badger.wav");

        // Add the components to the content panel first

        // Create an instance of GridBackground and add it to contentPanel
        GridBackground gridBackground = new GridBackground();
        gridBackground.setBounds(0, 0, contentPanel.getPreferredSize().width, contentPanel.getPreferredSize().height);
        contentPanel.add(gridBackground); // Agregamos el fondo de cuadrícula después

        // Create the JScrollPane with the content panel and add it to this panel
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // Add this line
        add(scrollPane, BorderLayout.CENTER);
    }

    // Clase interna para dibujar el fondo con cuadrículas
    class GridBackground extends JComponent {
        private static final int GRID_SIZE = 20;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Llama a super.paintComponent primero
            Dimension size = getSize();
            g.setColor(new Color(0, 0, 0, 100));

            // Draw vertical lines
            for (int x = 0; x < size.width; x += GRID_SIZE) {
                g.fillRect(x, 0, 1, size.height);
            }

            // Draw horizontal lines
            for (int y = 0; y < size.height; y += GRID_SIZE) {
                g.fillRect(0, y, size.width, 1);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            sfx.playSfx("src/main/resources/select.wav");
            frame.gamePanel.pauseP.setVisible(false);
            setVisible(false);
            frame.add(frame.gamePanel);
            frame.gamePanel.setVisible(true);
            frame.gamePanel.requestFocus();
            frame.gamePanel.startGameThread();
        }
        if (e.getSource() == optionsButton) {
            sfx.playSfx("src/main/resources/select.wav");
            setVisible(false);
            frame.remove(frame.opsPanel);
            frame.add(frame.opsPanel);
            frame.opsPanel.setVisible(true);
        }
        if (e.getSource() == exitButton) {
            sfx.playSfx("src/main/resources/select.wav");
            System.exit(0);
        }
        if (e.getSource() == connectionButton) {
            sfx.playSfx("src/main/resources/select.wav");
            connectionPanel.setVisible(true);
            disableComponents();
        }
        if (e.getSource() == connectionPanel.closeButton) {
            sfx.playSfx("src/main/resources/select.wav");
            connectionPanel.setVisible(false);
            enableComponents();
        }
        if (e.getSource() == connectionPanel.connectButton) {
            sfx.playSfx("src/main/resources/select.wav");
            String url = connectionPanel.urlField.getText();
            String user = connectionPanel.userField.getText();
            String pass = connectionPanel.passField.getText();
            frame.SkaneJDBC.setCon(SkaneConnection.getConnection(url, user, pass));
            refreshConnection();
        }
        if (e.getSource() == instructionsButton) {
            sfx.playSfx("src/main/resources/select.wav");
            setVisible(false);
            frame.instructionsPanel.setVisible(true);
            frame.add(frame.instructionsPanel);
        }
    }

    public void createLabelsAndButtons() {

        connectionPanel = new ConnectionPanel(this);

        // Define the new font to be used
        Font buttonFont = new Font("Press Start 2P", Font.BOLD, 25);

        // Define the thick border
        Border thickBorder = BorderFactory.createLineBorder(new Color(0, 0, 0), 3); // 3-pixel thick border

        // Calculate the horizontal center position for buttons
        int buttonWidth = 400;
        int panelWidth = 800;
        int centerX = (panelWidth - buttonWidth) / 2;

        // Calculate the horizontal center position for player label and field
        int labelWidth = 130;
        int centerXLabel = (panelWidth - labelWidth) / 2;

        playerLabel = new JLabel("Player:");
        playerLabel.setBounds(centerXLabel, 10, labelWidth, 50);
        playerLabel.setFont(new Font("Press Start 2P", Font.BOLD, 15));
        playerLabel.setForeground(Color.black);
        contentPanel.add(playerLabel);

        playerField = new JTextField("player1");
        playerField.setBounds(centerXLabel, 70, labelWidth, 50);
        playerField.setBackground(new Color(61, 79, 1, 255));
        playerField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        playerField.setForeground(Color.white);
        playerField.setFont(new Font("Arial", Font.BOLD, 20));
        contentPanel.add(playerField);


        ImageIcon imageIcon = new ImageIcon("imagenes/skane.png");
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(260, 70, 300, 200); // Adjust the position and size as needed
        contentPanel.add(imageLabel);

        Font font = new Font("Press Start 2P", Font.BOLD, 24); // Reduce the font size
        Dimension buttonSize = new Dimension(500, 70); // Reduce the button size


        int margin = 20;

        int buttonY = 250;

        // Botón Start
        ImageIcon iconoOriginal = new ImageIcon("imagenes/tocar.png");
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        startButton = new JButton("Start", iconoEscalado); // Crea el botón con el texto y el icono
        startButton.setFont(buttonFont);
        startButton.setForeground(Color.black);
        startButton.setBackground(new Color(154, 197, 3, 255));
        startButton.setBorder(thickBorder);
        startButton.setBounds(centerX, buttonY, buttonWidth, 80);
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        contentPanel.add(startButton);

        // Ajustar la posición y para el próximo botón
        buttonY += 80 + margin;

        // Botón Options
        ImageIcon iconoOriginalOptions = new ImageIcon("imagenes/opciones.png");
        Image imagenEscaladaOptions = iconoOriginalOptions.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon iconoEscaladoOptions = new ImageIcon(imagenEscaladaOptions);
        optionsButton = new JButton("Options", iconoEscaladoOptions); // Crea el botón con el texto y el icono
        optionsButton.setFont(buttonFont);
        optionsButton.setForeground(Color.black);
        optionsButton.setBackground(new Color(154, 197, 3, 255));
        optionsButton.setBorder(thickBorder);
        optionsButton.setBounds(centerX, buttonY, buttonWidth, 80);
        optionsButton.setFocusable(false);
        optionsButton.addActionListener(this);
        contentPanel.add(optionsButton);

        // Ajustar la posición y para el próximo botón
        buttonY += 80 + margin;

        // Botón Exit
        ImageIcon iconoOriginalExit = new ImageIcon("imagenes/exit.png");
        Image imagenEscaladaExit = iconoOriginalExit.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon iconoEscaladoExit = new ImageIcon(imagenEscaladaExit);
        exitButton = new JButton("Exit", iconoEscaladoExit); // Crea el botón con el texto y el icono
        exitButton.setFont(buttonFont);
        exitButton.setForeground(Color.black);
        exitButton.setBackground(new Color(154, 197, 3, 255));
        exitButton.setBorder(thickBorder);
        exitButton.setBounds(centerX, buttonY, buttonWidth, 80);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);
        contentPanel.add(exitButton);

        // Ajustar la posición y para el próximo botón
        buttonY += 80 + margin;

        // Botón Instructions
        ImageIcon iconoOriginalInstructions = new ImageIcon("imagenes/intrucciones.png");
        Image imagenEscaladaInstructions = iconoOriginalInstructions.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon iconoEscaladoInstructions = new ImageIcon(imagenEscaladaInstructions);
        instructionsButton = new JButton("Instructions", iconoEscaladoInstructions); // Crea el botón con el texto y el icono
        instructionsButton.setFont(buttonFont);
        instructionsButton.setForeground(Color.black);
        instructionsButton.setBackground(new Color(154, 197, 3, 255));
        instructionsButton.setBorder(thickBorder);
        instructionsButton.setBounds(centerX, buttonY, buttonWidth, 80);
        instructionsButton.setFocusable(false);
        instructionsButton.addActionListener(this);
        contentPanel.add(instructionsButton);

        // Botón Connection
        connectionButton = new JButton("Connection");
        connectionButton.setFont(new Font("Arial", Font.BOLD, 16)); // Smaller font for this button
        connectionButton.setForeground(Color.white);
        connectionButton.setBackground(new Color(61, 79, 1, 255));
        connectionButton.setBorder(thickBorder);
        connectionButton.setBounds(10, 10, 130, 30); // Reduced size
        connectionButton.setFocusable(false);
        connectionButton.addActionListener(this);
        contentPanel.add(connectionButton);




        connectionLabel = new JLabel();
        connectionLabel.setText(null);
        connectionLabel.setBounds(120, 10, 20, 20);
        connectionLabel.setBorder(thickBorder);
        connectionLabel.setOpaque(true);

        // Add the components to the content panel instead of this panel
        contentPanel.add(startButton);
        contentPanel.add(optionsButton);
        contentPanel.add(exitButton);
        contentPanel.add(playerLabel);
        contentPanel.add(playerField);
        contentPanel.add(connectionButton);
        contentPanel.add(instructionsButton);
        contentPanel.add(connectionLabel);

    }

    public void refreshConnection() {
        if (frame.SkaneJDBC.getCon() == null) connectionLabel.setBackground(Color.red);
        else connectionLabel.setBackground(Color.green);
    }

    public void disableComponents() {
        for (Component c : getComponents()) {
            c.setEnabled(false);
        }
    }

    public void enableComponents() {
        for (Component c : getComponents()) {
            c.setEnabled(true);
        }
    }
}
