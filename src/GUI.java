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
    JMenuItem view, changeTheme;

    String[] fontFamilyValues = {"SansSerif", "Serif", "Monospaced", "Dialog", "Arial"};
    String[] fontStyleValues = {"Plain", "Bold", "Italic"};
    String[] fontSizeValues = {"8","12", "14", "16", "18", "20", "24", "28", "32", "36", "40"};

    JList<String> familyList = new JList<>(fontFamilyValues);
    JList<String> styleList = new JList<>(fontStyleValues);
    JList<String> sizeList = new JList<>(fontSizeValues);


    Color darkBg = new Color(0x1E, 0x1E, 0x1E);
    Color darkFg = new Color(0xD4, 0xD4, 0xD4);
    Color darkSelectionColor = new Color(0x26, 0x4F, 0x78);

    Color lightBg = Color.WHITE;
    Color lightFg = Color.BLACK;
    Color lightSelectionColor = new Color(0xA6, 0xD2, 0xFF);


    String currentFontFamily = "SansSerif";
    int currentFontStyle = Font.PLAIN;
    int currentFontSize = 20;

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
        textArea.setFont(new Font(currentFontFamily, currentFontStyle, currentFontSize));
        textArea.setBackground(lightBg);
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

        view = new JMenu("View");

        changeTheme = new JMenuItem("Change Theme");

        window.setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(format);
        menuBar.add(view);

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

        view.add(changeTheme);
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
        changeTheme.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK).getKeyChar());

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
        changeTheme.addActionListener(this);

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



        else if (ae.getActionCommand().equals("Font Family")){
            int response = JOptionPane.showConfirmDialog(window, new JScrollPane(familyList), "Choose Font Family", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (response == JOptionPane.OK_OPTION && familyList.getSelectedValue() != null) {
                currentFontFamily = familyList.getSelectedValue();
                textArea.setFont(new Font(currentFontFamily, currentFontStyle, currentFontSize));
            }
        }
        else if (ae.getActionCommand().equals("Font Style")) {
            int response = JOptionPane.showConfirmDialog(window, new JScrollPane(styleList), "Choose Font Style", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (response == JOptionPane.OK_OPTION && styleList.getSelectedValue() != null) {
                String styleStr = styleList.getSelectedValue();
                if (styleStr.equals("Bold")) {
                    currentFontStyle = Font.BOLD;
                } else if (styleStr.equals("Italic")) {
                    currentFontStyle = Font.ITALIC;
                } else {
                    currentFontStyle = Font.PLAIN;
                }
                textArea.setFont(new Font(currentFontFamily, currentFontStyle, currentFontSize));
            }
        }
        else if (ae.getActionCommand().equals("Font Size")) {
            int response = JOptionPane.showConfirmDialog(window, new JScrollPane(sizeList), "Choose Font Size", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (response == JOptionPane.OK_OPTION && sizeList.getSelectedValue() != null) {
                currentFontSize = Integer.parseInt(sizeList.getSelectedValue());
                textArea.setFont(new Font(currentFontFamily, currentFontStyle, currentFontSize));
            }
        }


        else if (ae.getActionCommand().equals("Change Theme")) {
            if (textArea.getBackground() == lightBg){
                textArea.setBackground(darkBg);
                textArea.setForeground(darkFg);
                textArea.setCaretColor(Color.WHITE);
                textArea.setSelectionColor(darkSelectionColor);

                scrollPane.getViewport().setBackground(darkBg);
            } else {
                textArea.setBackground(lightBg);
                textArea.setForeground(lightFg);
                textArea.setCaretColor(Color.BLACK);
                textArea.setSelectionColor(lightSelectionColor);

                scrollPane.getViewport().setBackground(lightBg);
            }

        }
    }
}

