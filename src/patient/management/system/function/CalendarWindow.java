package patient.management.system.function;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

import javax.swing.JFrame;

import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.ThemeType;
import com.mindfusion.common.*;

/**
 *
 * @author James Man
 */
public class CalendarWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    java.util.Calendar from_selectedDate = java.util.Calendar.getInstance();
    java.util.Calendar to_selectedDate = java.util.Calendar.getInstance();
    java.util.Calendar next_selectedDate = java.util.Calendar.getInstance();
    
    
    Calendar calendar = new Calendar();
    protected PropertyChangeSupport changeSupport;

    public CalendarWindow(String date_cal) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(235, 200);
        setTitle("MindFusion.Scheduling Sample: Minimal Application");

        changeSupport = new PropertyChangeSupport(this);

        calendar.setTheme(ThemeType.Light);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(calendar, BorderLayout.CENTER);

        calendar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    //clear the selection
                    calendar.getSelection().reset();
                    //get the date that was double-clicked
                    DateTime pointedDate = calendar.getDateAt(e.getX(), e.getY());
                    //create a java.util.Calendar instance that points to the selected Date
                    java.util.Calendar cal = java.util.Calendar.getInstance();
                    cal.set(pointedDate.getYear(), pointedDate.getMonth() - 1, pointedDate.getDay());
                    //raise the event
                    if (date_cal == "from") {
                        setFromSelectedDate(cal);
                    } else if (date_cal == "to") {
                        setToSelectedDate(cal);
                    }  {
                        setNextSelectedDate(cal);
                    }
                    dispose();

                }

            }
        });
    }

    //getter of the selectedDate property
    public java.util.Calendar getFromSelectedDate() {
        return from_selectedDate;
    }
    
    public java.util.Calendar getToSelectedDate() {
        return to_selectedDate;
    }
    
    public java.util.Calendar getNextSelectedDate() {
        return next_selectedDate;
    }

    //set the selectedDate when typed in the text field
    public void resetSelection(Date date) {
        calendar.getSelection().reset();
        calendar.getSelection().set(new DateTime(date), new DateTime(date).addMinutes(2));
        calendar.setDate(new DateTime(date));
    }

    //raises the event that the selectedDate property has changed
    public void setFromSelectedDate(java.util.Calendar selDate) {

        java.util.Calendar oldValue = (java.util.Calendar) from_selectedDate.clone();
        from_selectedDate = selDate;

        changeSupport.firePropertyChange("from_selectedDate", oldValue, from_selectedDate);

    }
    
    public void setToSelectedDate(java.util.Calendar selDate) {

        java.util.Calendar oldValue = (java.util.Calendar) to_selectedDate.clone();
        to_selectedDate = selDate;

        changeSupport.firePropertyChange("to_selectedDate", oldValue, to_selectedDate);

    }
    
    public void setNextSelectedDate(java.util.Calendar selDate) {

        java.util.Calendar oldValue = (java.util.Calendar) next_selectedDate.clone();
        next_selectedDate = selDate;

        changeSupport.firePropertyChange("next_selectedDate", oldValue, next_selectedDate);

    }
    

    //adds a listener for the PropertyChange event
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

}
