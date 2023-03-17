package mvc;

public class Model extends Bean{
  
    private boolean unsavedChanges = false;
    private String fileName = null;

    public void changed() {
        this.unsavedChanges = true;
        firePropertyChange("unsavedChanges", false, true);
    }

    public void setUnsavedChanges(boolean change){
        this.unsavedChanges = change;
        boolean unchanged = this.unsavedChanges;
        this.firePropertyChange("unsavedChanges", unchanged, this.unsavedChanges);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        String name = this.fileName;
        this.firePropertyChange("fileName", name, this.fileName);
    }

    public boolean getUnsavedChanges() { return unsavedChanges; }
    public String getFileName() { return fileName; }
  
}
