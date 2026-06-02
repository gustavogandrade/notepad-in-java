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

    JMenu file,newDoc,open,save,print,exit;
    JMenu edit,copy,paste,cut,selectAll;
    JMenu format,fontFamily,fontStyle,fontSize;

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
        assert iconURL != null;
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

        newDoc = new JMenu("New");
        open = new JMenu("Open");
        save = new JMenu("Save");
        print = new JMenu("Print");
        exit = new JMenu("Exit");

        edit = new JMenu("Edit");

        copy = new JMenu("Copy");
        paste = new JMenu("Paste");
        cut = new JMenu("Cut");
        selectAll = new JMenu("Select All");

        format = new JMenu("Format");

        fontFamily = new JMenu("Font Family");
        fontStyle = new JMenu("Font Style");
        fontSize = new JMenu("Font Size");

        window.setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(format);

        file.add(newDoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectAll);

        format.add(fontFamily);
        format.add(fontStyle);
        format.add(fontSize);
    }


    public void createShortcuts(){
        newDoc.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK).getKeyChar());

        open.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_DOWN_MASK).getKeyChar());
        save.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK).getKeyChar());
        exit.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,InputEvent.CTRL_DOWN_MASK).getKeyChar());

        copy.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_DOWN_MASK).getKeyChar());
        paste.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_DOWN_MASK).getKeyChar());
        cut.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_DOWN_MASK).getKeyChar());
        selectAll.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_DOWN_MASK).getKeyChar());

    }
}
