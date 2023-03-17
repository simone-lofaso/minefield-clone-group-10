package mvc;

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
    private ControlPanel controls;
    private View view;

    private String fName; //for save  saveAs


    public AppPanel() {
        view = new View();
        controls = new ControlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controls);
        //this.add(view);

        SafeFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("Mine Field");
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"North", "South", "East", "West", "Northeast", "Northwest", "Southwest", "Southeast"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);


        return result;
    }

    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch (cmmd) {
                case "North" -> {

                }
                case "East" -> {

                }
                case "West" -> {

                }
                case "South" -> {

                }
                case "Northeast" -> {

                }
                case "Northwest" -> {

                }
                case "Southwest" -> {

                }
                case "Southeast" -> {

                }
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
                            "North: Makes Sergeant Rock move North",
                            "South: Makes Sergeant Rock move South",
                            "West: Makes Sergeant Rock move West",
                            "East: Makes Sergeant Rock move East",
                            "Northeast: Makes Sergeant Rock move Northeast",
                            "Northwest: Makes Sergeant Rock move Northwest",
                            "Southwest: Makes Sergeant Rock move Southwest",
                            "Southeast: Makes Sergeant Rock move Southeast"

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


    class ControlPanel extends JPanel {
        public ControlPanel() {
            setBackground(Color.PINK);
            JPanel panel = new JPanel(new BorderLayout());
            panel.setLayout(new GridLayout(4, 2));
            JButton north = new JButton("N");
            JButton south = new JButton("S");
            JButton east = new JButton("E");
            JButton west = new JButton("W");

            JButton northeast = new JButton("NE");
            JButton northwest = new JButton("NW");
            JButton southwest = new JButton("SW");
            JButton southeast = new JButton("SE");

            north.addActionListener(AppPanel.this);
            panel.add(north);

            south.addActionListener(AppPanel.this);
            panel.add(south);

            east.addActionListener(AppPanel.this);
            panel.add(east);

            west.addActionListener(AppPanel.this);
            panel.add(west);

            northwest.addActionListener(AppPanel.this);
            panel.add(northwest);

            northeast.addActionListener(AppPanel.this);
            panel.add(northeast);

            southwest.addActionListener(AppPanel.this);
            panel.add(southwest);

            southeast.addActionListener(AppPanel.this);
            panel.add(southeast);

            add(panel);
        }
    }

    public static void main(String[] args) {
        AppPanel app = new AppPanel();
    }

}
