package view.util;
import javax.swing.*;
import java.awt.*;

public class View {

    public static final Font font =  new Font("Calibri", 3, 33);
    public static void changeFont ( Component component, Font font )
    {
        component.setFont ( font );
        if ( component instanceof Container )
        {
            for ( Component child : ( ( Container ) component ).getComponents () )
            {
                changeFont ( child, font );
            }
        }
    }

    public static void changeFont(Component component){
        component.setFont(font);
        if ( component instanceof Container )
        {
            for ( Component child : ( ( Container ) component ).getComponents () )
            {
                changeFont ( child, font );
            }
        }
    }
}
