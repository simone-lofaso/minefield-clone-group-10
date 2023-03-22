package mvc;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JPanel implements PropertyChangeListener {

    protected Model model;
    public void propertyChange(PropertyChangeEvent evt) { repaint(); }

    public View(Model model){
        this.model = model;
        //view registers itself as a property change listener
        model.addPropertyChangeListener(this);
    }

    public void setModel(Model model){
        //when a new model is created
        //view needs to remove itself as a property change listener from the old model
        if(this.model != null){ this.model.removePropertyChangeListener(this); }

        //listeners are reallocated
        this.model.initSupport();

        //adds itself as a property change listener to the new model
        this.model.addPropertyChangeListener(this);
        repaint();
    }
}
