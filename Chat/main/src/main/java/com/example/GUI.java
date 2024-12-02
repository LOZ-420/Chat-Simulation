package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GUI extends JFrame {
    public static final Color Background = Color.GRAY;
    private static final int MAX_MESSAGES = 100; // Limit number of messages

    public GUI() {
        super("Chat Notepad");
        setSize(500, 600);
        setLayout(null);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Background);

        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setBounds(0, 515, 430, 50);
        inputField.setBorder(null);
        inputField.setForeground(Color.BLACK);
        add(inputField);

        JButton sendBtn = new JButton(loadImage("/assets/Send.jpg"));
        sendBtn.setBounds(429, 515, 70, 50);
        sendBtn.setBorderPainted(false);
        add(sendBtn);

        JLabel NameLabl = new JLabel("Name:");
        NameLabl.setFont(new Font("Arial", Font.PLAIN, 20));
        NameLabl.setForeground(Color.BLACK);
        NameLabl.setBounds(180, 10, 100, 30);  // Corrected bounds
        add(NameLabl);

        JTextField NameField = new JTextField();
        NameField.setFont(new Font("Arial", Font.PLAIN, 20));
        NameField.setBackground(null);
        NameField.setForeground(Color.black);
        NameField.setBounds(250, 7, 100, 30);
        add(NameField);

        JTextArea Chat = new JTextArea("");
        Chat.setFont(new Font("Arial", Font.PLAIN, 20));
        Chat.setForeground(Color.BLACK);
        Chat.setDisabledTextColor(Color.BLACK);
        Chat.setBackground(Color.gray);
        Chat.setEnabled(false);
        Chat.setLineWrap(true);
        Chat.setWrapStyleWord(true);

        // Add JTextArea to JScrollPane
        JScrollPane scrollPane = new JScrollPane(Chat);
        scrollPane.setBounds(0, 50, 495, 450);
        add(scrollPane);

        sendBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage(Chat, inputField, NameField);
            }
        });

        inputField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage(Chat, inputField, NameField);
                }
            }
        });
    }

    private void sendMessage(JTextArea Chat, JTextField inputField, JTextField NameField) {
        String userMessage = inputField.getText();
        String userName = NameField.getText();
        String message = userName + ": " + userMessage;

        // Check if userName and userMessage are not empty
        if (!userName.isEmpty() && !userMessage.isEmpty()) {
            // Append message to chat
            Chat.append(message + "\n");

            // Limit the number of messages
            String chatText = Chat.getText();
            String[] lines = chatText.split("\n");

            if (lines.length > MAX_MESSAGES) {
                StringBuilder newChatText = new StringBuilder();
                for (int i = lines.length - MAX_MESSAGES; i < lines.length; i++) {
                    newChatText.append(lines[i]).append("\n");
                }
                Chat.setText(newChatText.toString());
            }

            // Clear input field
            inputField.setText("");
        }
    }

    private ImageIcon loadImage(String resourcePath) {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(resourcePath));
            // Scale image to button size
            BufferedImage scaledImage = new BufferedImage(70, 50, BufferedImage.TYPE_INT_ARGB);
            scaledImage.getGraphics().drawImage(image, 0, 0, 70, 50, null);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        new GUI().setVisible(true);
    }
}
