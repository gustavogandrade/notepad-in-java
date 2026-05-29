import javax.swing.*;
import java.net.URL;

public class GUI {

    JFrame window;
    JTextArea textArea;
    JScrollPane scrollPane;

    public static void main(String[] args){

        new GUI();
    }
    public GUI() {

        createWindow();
        createTextArea();
        window.setVisible(true);
    }

    public void createWindow() {

        window = new JFrame("Notepad");
        window.setSize(670,670);

        URL iconURL = getClass().getClassLoader().getResource("notepad-icon.png");
            ImageIcon image = new ImageIcon(iconURL);
            window.setIconImage(image.getImage());
            System.out.println("Warning: notepad-icon.png not found in resources!");



        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void createTextArea() {
        textArea = new JTextArea();
        window.add(textArea);
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        window.add(scrollPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
    }
}
