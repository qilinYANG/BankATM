package view;
import javax.imageio.ImageIO;
import javax.swing.*;

//import org.jdatepicker.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;



public class test {

  public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics2D = resizedImage.createGraphics();
    graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
    graphics2D.dispose();
    return resizedImage;
}

    public static void main(String[] args) throws ParseException {



        /*LocalDate date = LocalDate.now();
        //System.out.println(date);

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
        }
        */    

        
        

        JFrame a = new JFrame();
        a.setSize(800, 800);
        
        class JPanelWithBackground extends JPanel {

            private Image backgroundImage;
          
            // Some code to initialize the background image.
            // Here, we use the constructor to load the image. This
            // can vary depending on the use case of the panel.
            public JPanelWithBackground(String fileName) throws IOException  {
              backgroundImage = ImageIO.read(new File(fileName));
            }
          
            public void paintComponent(Graphics g) {
              super.paintComponent(g);
          
              // Draw the background image.
              g.drawImage(backgroundImage, 0, 0, this);
            }
          }
        

        //UtilDateModel model = new UtilDateModel();
        //JDatePanelImpl datePanel = new JDatePanelImpl(model);
        //JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
 
        //a.add(datePicker);
        

          BufferedImage image;

            

          try {
            image = ImageIO.read(new File("pic/wang.jpg"));
            image = resizeImage(image, 600, 800);
            ImageIcon imageicon = new ImageIcon(image);
          JLabel l = new JLabel(imageicon);
          a.add(l);
          
          } catch (IOException e) {
            
            e.printStackTrace();
          }
          

        //JPanel b = new JPanel(new FlowLayout());

        //JButton c = new JButton("Confirm");
        //b.add(c);
        //a.add(b);
        a.setVisible(true);
        a.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
    }
}


    

