package mvc;

public interface AppFactory {

    Model makeModel();
    View makeView(Model m);
    Command makeEditCommand(Model m, String s, Object o);

    String getTitle();
    String[] getHelp();
    String about();
    String[] getEditCommands();

}
