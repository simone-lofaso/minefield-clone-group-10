package mvc;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import javax.swing.*;

public class AppPanel extends JPanel implements ActionListener, PropertyChangeListener {

    protected Model model;
    protected View view;
    protected JPanel controlPanel;
    protected AppFactory factory;

    private JFrame frame;

    public AppPanel(AppFactory factory) {
        this.factory = factory;
        model = this.factory.makeModel();
        addPropertyChangeListener(this);
        controlPanel = new JPanel();
        view = this.factory.makeView(model);

        setLayout((new GridLayout(1, 2)));
        add(controlPanel);
        add(view);

        view.setBackground(Color.GRAY);

        frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(this.factory.getTitle());
        frame.setSize(600, 350);
        frame.setLocationRelativeTo(null);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[] {"New",  "Save", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu =
                Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[] {"About", "Help"}, this);
        result.add(helpMenu);

        return result;
    }

    public void display() {
        frame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){ }

    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try{
            switch(cmmd) {
                case "Save": {

                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.model);
                    os.close();
                    break;
                }

                case "SaveAs": {
                    String fName = Utilities.getFileName((String) null, true);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.model);
                    os.close();
                    break;
                }

                case "Open": {

                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        this.model = (Model) is.readObject();
                        this.view.setModel(model);
                        is.close();
                    }
                    break;

                }

                case "New": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        model = factory.makeModel();
                        view.setModel(model);
                        model.changed();
                    }
                    break;
                }

                case "Quit": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                        System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform(factory.about());
                    break;
                }

                case "Help": {
                    String[] cmmds = factory.getHelp();
                    Utilities.inform(cmmds);
                    break;

                }
                case "N", "S", "W", "E", "NE", "NW", "SE", "SW" : {
                    for (String s : factory.getEditCommands()) {
                        if (cmmd.equals(s)) {
                            factory.makeEditCommand(model, cmmd, null).execute();
                        }
                    }
                }
            }
        }catch (Exception error) {
            Utilities.error(error);
        }
        revalidate();
        repaint();
    }

}
