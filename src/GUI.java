import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.net.URL;


public class GUI extends JFrame implements ActionListener {
    JFrame window;
    JTextArea textArea;
    JScrollPane scrollPane;
    String text;

    JMenuItem file, newDoc, open, save, print, exit;
    JMenuItem edit, copy, paste, cut, selectAll;
    JMenuItem format, fontFamily, fontStyle, fontSize;
    private Object fontFamilyList;

    static void main(String[] args) {
        new GUI();
    }


    public GUI() {
        createWindow();
        createTextArea();
        createMenuBar();
        createShortcuts();
        addActionEvents();

        window.setVisible(true);
    }


    public void createWindow() {
        window = new JFrame("Notepad");
        window.setSize(670, 670);

        URL iconURL = getClass().getClassLoader().getResource("notepad-icon.png");
        assert iconURL != null;
        ImageIcon image = new ImageIcon(iconURL);
        window.setIconImage(image.getImage());
        System.out.println("Warning: notepad-icon.png not found in resources!");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void createTextArea() {
        textArea = new JTextArea();
        textArea.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        window.add(textArea);

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        window.add(scrollPane);

        scrollPane.setBorder(BorderFactory.createEmptyBorder());
    }


    public void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        file = new JMenu("File");

        newDoc = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        print = new JMenuItem("Print");
        exit = new JMenuItem("Exit");

        edit = new JMenu("Edit");

        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        cut = new JMenuItem("Cut");
        selectAll = new JMenuItem("Select All");

        format = new JMenu("Format");

        fontFamily = new JMenuItem("Font Family");
        fontStyle = new JMenuItem("Font Style");
        fontSize = new JMenuItem("Font Size");

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


    public void createShortcuts() {
        newDoc.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK).getKeyChar());

        open.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK).getKeyChar());
        save.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK).getKeyChar());
        exit.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, InputEvent.CTRL_DOWN_MASK).getKeyChar());

        copy.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK).getKeyChar());
        paste.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK).getKeyChar());
        cut.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK).getKeyChar());
        selectAll.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK).getKeyChar());

    }

    public void addActionEvents() {
        newDoc.addActionListener(this);
        save.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        cut.addActionListener(this);
        selectAll.addActionListener(this);
        open.addActionListener(this);
        fontFamily.addActionListener(this);
        fontSize.addActionListener(this);
        fontStyle.addActionListener(this);
    }


    public void actionPerformed(ActionEvent ae) {

        if (ae.getActionCommand().equals("New")) {
            textArea.setText("");
        }

        else if (ae.getActionCommand().equals("Open")){
            JFileChooser chooser = new JFileChooser("C:/");
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only  .txt files", "txt");
            chooser.addChoosableFileFilter(restrict);

            int result = chooser.showOpenDialog(window);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();

                try {
                    FileReader reader = new FileReader(file);
                    BufferedReader br = new BufferedReader(reader);
                    textArea.read(br, null);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        else if (ae.getActionCommand().equals("Save")) {
            final JFileChooser SaveAs = new JFileChooser();
            SaveAs.setApproveButtonText("Save");
            int actionDialog = SaveAs.showOpenDialog(window);
            if (actionDialog != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File fileName = new File(SaveAs.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(fileName));
                textArea.write(outFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (ae.getActionCommand().equals("Print")) {
            try {
                textArea.print();
            } catch (Exception e) {}
        }

        else if (ae.getActionCommand().equals("Exit")){
            window.dispose();
        }

        else if (ae.getActionCommand().equals("Copy")){
            text = textArea.getSelectedText();
        }

        else if (ae.getActionCommand().equals("Paste")){
            textArea.insert(text,textArea.getCaretPosition());
        }

        else if (ae.getActionCommand().equals("Cut")){
            text = textArea.getSelectedText();
            textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
        }

        else if (ae.getActionCommand().equals("Select All")){
            textArea.selectAll();
        }

//        else if (ae.getActionCommand().equals("Font Family")){
//            JOptionPane.showConfirmDialog(null,fontFamilyList, "Choose Font Family", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//            fontFamily = String.valueOf(fontFamilyList.getSelectedValue());
//            newFont = new Font(fontFamily,fontStyle, fontSize);
//            textArea.setFont(newFont);
//        }


    }
}
