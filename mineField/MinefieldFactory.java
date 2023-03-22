package mineField;

import mvc.*;

public class MinefieldFactory implements AppFactory {

    public Model makeModel() { return new Minefield(); }

    public View makeView(Model m) { return new MinefieldView((Minefield)m); }

    public String[] getEditCommands() { return new String[]{"NW","N","NE","W","E","SW","S","SE"}; }

    public Command makeEditCommand(Model model, String type,Object Source) {
        for( String s : getEditCommands()){
            if (type.equals(s)){ return new MineCommand(model,s); }
        }
        return null;
    }

    public String getTitle() { return "Mine Field"; }

    public String[] getHelp() {
        return new String[] {"N: Makes Sergeant Rock move North",
                "S: Makes Sergeant Rock move South",
                "W: Makes Sergeant Rock move West",
                "E: Makes Sergeant Rock move East",
                "NE: Makes Sergeant Rock move Northeast",
                "NW: Makes Sergeant Rock move Northwest",
                "SW: Makes Sergeant Rock move Southwest",
                "SE: Makes Sergeant Rock move Southeast"};
    }

    public String about() {
        return "Simone LoFaso, Victoria Le, Jason Lee, March 16, 2023";
    }
}
