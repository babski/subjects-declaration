package mbabski.gui;

import mbabski.core.DataBase;

public abstract class GUI {
    public GUI(){
        DataBase.getInstance().loadSubjects();
        DataBase.getInstance().loadCourses();
    }
    public abstract void showMainMenu();
}
