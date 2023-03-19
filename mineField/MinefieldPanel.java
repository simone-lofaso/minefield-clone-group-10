package mineField;

import mvc.AppPanel;
import mvc.SafeFrame;
import mvc.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MinefieldPanel extends AppPanel {
    private ControlPanel controls;
    private MinefieldView view;
    private SgtRock rock;

    public MinefieldPanel() {
        controls = new ControlPanel();
        view = new MinefieldView();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controls);
        this.add(view);

        SafeFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("Mine Field");
        frame.setSize(600, 350);
        frame.setVisible(true);

        SgtRock rock = new SgtRock();
    }

    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch (cmmd) {
                case "N" -> {
                    rock.move(Heading.N);
                    if (rock.isGameDone()){
                        gameEnded();
                    }
                }
                case "E" -> {
                    rock.move(Heading.E);
                    if (rock.isGameDone()){
                        gameEnded();
                    }
                }
                case "W" -> {
                    rock.move(Heading.W);
                    if (rock.isGameDone()){
                        gameEnded();
                    }
                }
                case "S" -> {
                    rock.move(Heading.S);
                    if (rock.isGameDone()){
                        gameEnded();
                    }
                }
                case "NE" -> {
                    rock.move(Heading.NE);
                    if (rock.isGameDone()){
                        gameEnded();
                    }
                }
                case "NW" -> {
                    rock.move(Heading.NW);
                    if (rock.isGameDone()){
                        gameEnded();
                    }
                }
                case "SW" -> {
                    rock.move(Heading.SW);
                    if (rock.isGameDone()){
                        gameEnded();
                    }
                }
                case "SE" -> {
                    rock.move(Heading.SE);
                    if (rock.isGameDone()){
                        gameEnded();
                    }
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

    //throws dialogue box determining win or loss
    private void gameEnded(){
        if (rock.getxPos() == 19 && rock.getyPos() == 19){
            JOptionPane.showMessageDialog(null, "Congratulations! You win!");
        }
        else JOptionPane.showMessageDialog(null, "You died :(");
    }

    class ControlPanel extends JPanel {
        public ControlPanel() {
            setBackground(Color.WHITE);
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

            north.addActionListener(MinefieldPanel.this);
            panel.add(north);

            south.addActionListener(MinefieldPanel.this);
            panel.add(south);

            east.addActionListener(MinefieldPanel.this);
            panel.add(east);

            west.addActionListener(MinefieldPanel.this);
            panel.add(west);

            northwest.addActionListener(MinefieldPanel.this);
            panel.add(northwest);

            northeast.addActionListener(MinefieldPanel.this);
            panel.add(northeast);

            southwest.addActionListener(MinefieldPanel.this);
            panel.add(southwest);

            southeast.addActionListener(MinefieldPanel.this);
            panel.add(southeast);

            add(panel);
        }
    }

    public static void main(String[] args) {
        MinefieldPanel app = new MinefieldPanel();
        app.setVisible(true);
        MineField board = new MineField();
        board.test();
    }
}
