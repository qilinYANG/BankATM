package view.util;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Greetings extends JFrame {

    private BufferedImage wang;
    private JLabel greeting;
    private JButton love;
    private Font font = new Font("Calibri", 3, 44);

    public Greetings() throws IOException {

        input();

        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setLayout(new GridLayout(1, 3));
        wang = resizeImage(wang, 600, 800);
        ImageIcon imageicon = new ImageIcon(wang);
        JLabel l = new JLabel(imageicon);
        JPanel p1 = new JPanel(new FlowLayout());
        p1.add(l);
        greeting = new JLabel("Merry Christmas!!!");
        JPanel p2 = new JPanel(new GridLayout(3, 1));
        JPanel p4 = new JPanel(new FlowLayout());
        JPanel p5 = new JPanel(new FlowLayout());
        JPanel p6 = new JPanel(new FlowLayout());
        p4.add(new JLabel(" "));
        p5.add(new JLabel("Merry Christmas!!!"));
        p6.add(new JLabel(" "));
        p2.add(p4);
        p2.add(p5);
        p2.add(p6);
        love = new JButton("Love you!");

        love.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }

        });
        JPanel p3 = new JPanel(new GridLayout(3, 1));
        JPanel p7 = new JPanel(new FlowLayout());
        JPanel p8 = new JPanel(new FlowLayout());
        JPanel p9 = new JPanel(new FlowLayout());
        p7.add(new JLabel(" "));
        p8.add(love);
        p9.add(new JLabel(" "));
        p3.add(p7);
        p3.add(p8);
        p3.add(p9);

        View.changeFont(p2, font);
        View.changeFont(p3, font);
        add(p2);
        add(p1);
        add(p3);

    }

    private void input() {
        try {
            wang = ImageIO.read(new File("pic/wang.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ;

    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight)
            throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    public static void main(String[] args) {
        try {
            Greetings next = new Greetings();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}