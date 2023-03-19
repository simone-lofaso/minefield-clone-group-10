package mvc;

import mineField.Heading;
import mineField.MineField;
import mineField.MinefieldView;
import mineField.SgtRock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * This class creates the AppPanel for the MineField game board, and contains inner class ControlPanel,
 * which contains the JButtons and menu for the game
 *
 * NOTE FOR TEAM: View still needs to be implemented. Control Panel should be finished so the
 * game board does not yet show up when you run it.
 */

public class AppPanel extends JPanel implements ActionListener{
    /**private ControlPanel controls;
    private MinefieldView view;
    private SgtRock rock;**/

    private String fName; //for save  saveAs


    public AppPanel() {
        SafeFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("Mine Field");
        frame.setSize(500, 300);
        frame.setVisible(true);

        rock = new SgtRock();
    }


    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"N", "S", "E", "W", "NE", "NW", "SW", "SE"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);


        return result;
    }

    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch (cmmd) {
                case "SaveAs" -> {
                    fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    //os.writeObject(turtle);
                    os.close();
                }
                case "Open" -> {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        //turtle = (Turtle) is.readObject();
                        //view.setTurtle(turtle);
                        is.close();
                    }

                }
                case "New" -> {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        //turtle = new Turtle();
                        //view.setTurtle(turtle);
                    }
                }
                case "Quit" -> {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                        System.exit(0);
                }
                case "About" -> {
                    Utilities.inform("Simone LoFaso, Victoria Le, Jason Lee, March 16, 2023");
                }
                case "Help" -> {
                    String[] cmmds = new String[]{
                            "N: Makes Sergeant Rock move North",
                            "S: Makes Sergeant Rock move South",
                            "W: Makes Sergeant Rock move West",
                            "E: Makes Sergeant Rock move East",
                            "NE: Makes Sergeant Rock move Northeast",
                            "NW: Makes Sergeant Rock move Northwest",
                            "SW: Makes Sergeant Rock move Southwest",
                            "SE: Makes Sergeant Rock move Southeast"

                    };
                    Utilities.inform(cmmds);

                }
                default -> {
                    //throw an exception
                }
            }
        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }

    public static void main(String[] args) {
        AppPanel app = new AppPanel();
        MineField board = new MineField();
        board.test();
    }
}
