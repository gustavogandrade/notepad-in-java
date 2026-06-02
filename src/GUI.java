import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.net.URL;

public class GUI {
    JFrame window;
    JTextArea textArea;
    JScrollPane scrollPane;

    JMenu file,newdoc,open,save,print,exit;
    JMenu edit,copy,paste,cut,selectall;
    JMenu format,fontfamily,fontstyle,fontsize;

    static void main(String[] args){
        new GUI();
    }


    public GUI() {
        createWindow();
        createTextArea();
        createMenuBar();
        createShortcuts();

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


    public void createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        file = new JMenu("File");

        newdoc = new JMenu("New");
        open = new JMenu("Open");
        save = new JMenu("Save");
        print = new JMenu("Print");
        exit = new JMenu("Exit");

        edit = new JMenu("Edit");

        copy = new JMenu("Copy");
        paste = new JMenu("Paste");
        cut = new JMenu("Cut");
        selectall = new JMenu("Select All");

        format = new JMenu("Format");

        fontfamily = new JMenu("Font Family");
        fontstyle = new JMenu("Font Style");
        fontsize = new JMenu("Font Size");

        window.setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(format);

        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectall);

        format.add(fontfamily);
        format.add(fontstyle);
        format.add(fontsize);
    }


    public void createShortcuts(){
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,ActionEvent.CTRL_MASK));

        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));

    }
}
