package mvc;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Utilities {

    public static boolean confirm(String query) {
        int result = JOptionPane.showConfirmDialog((Component)null, query, "Confirm", JOptionPane.OK_CANCEL_OPTION);
        return result == JOptionPane.OK_OPTION;
    }

    public static String ask(String query) {
        return JOptionPane.showInputDialog((Component)null, query);
    }

    public static void inform(String info) {
        JOptionPane.showMessageDialog((Component)null, info);
    }

    public static void inform(String[] items) {
        String helpString = "";

        for(int i = 0; i < items.length; ++i) {
            helpString = helpString + "\n" + items[i];
        }

        inform(helpString);
    }
    // save model
    public static void save(Model model, Boolean saveAs) {
        String fName = model.getFileName();
        if (fName == null || saveAs) {
            fName = getFileName(fName, false);
            model.setFileName(fName);
        }
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
            model.setUnsavedChanges(false);
            os.writeObject(model);
            os.close();
        } catch (Exception err) {
            model.setUnsavedChanges(true);
            Utilities.error(err);
        }
    }

    // open model
    public static Model open(Model model) {
        //saveChanges(model);
        String fName = getFileName(model.getFileName(), true);
        Model newModel = null;
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
            newModel = (Model)is.readObject();
            is.close();
        } catch (Exception err) {
            Utilities.error(err);
        }
        return newModel;
    }

    public static void error(String gripe) {
        JOptionPane.showMessageDialog((Component)null, gripe, "OOPS!", 0);
    }

    public static void error(Exception gripe) {
        gripe.printStackTrace();
        JOptionPane.showMessageDialog((Component)null, gripe.getMessage(), "OOPS!", 0);
    }

    public static JMenu makeMenu(String name, String[] items, ActionListener handler) {
        JMenu result = new JMenu(name);

        for(int i = 0; i < items.length; ++i) {
            JMenuItem item = new JMenuItem(items[i]);
            item.addActionListener(handler);
            result.add(item);
        }

        return result;
    }

    public static String getFileName(String fName, Boolean open) {
        JFileChooser chooser = new JFileChooser();
        String result = null;
        if (fName != null) {
            chooser.setCurrentDirectory(new File(fName));
        }

        int returnVal;
        if (open) {
            returnVal = chooser.showOpenDialog((Component)null);
            //user picks file location
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                result = chooser.getSelectedFile().getPath();
            } else {
                //user didnt pick file option
                //return result;
            }
        } else {
            returnVal = chooser.showSaveDialog((Component)null);
            //user picks file location
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                result = chooser.getSelectedFile().getPath();
            } else {
                //user didnt pick file option
                //return result;
            }
        }

        return result;
    }
}

