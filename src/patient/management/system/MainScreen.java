/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system;

import patient.management.system.function.CalendarWindow;
import patient.management.system.function.Appointment;
import patient.management.system.function.ConsultationHistory;
import patient.management.system.function.NewAppointment;
import patient.management.system.function.AccountApproval;
import patient.management.system.function.Account;
import patient.management.system.function.Rating;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author James Man
 * https://www.youtube.com/watch?v=uYRKNVPAc-w&ab_channel=BlessingSoftware slide
 * https://stackoverflow.com/questions/39072608/change-the-date-format-before-settext-of-textview date format
 * date to text
 *
 * menu
 */
public class MainScreen extends javax.swing.JFrame implements PropertyChangeListener {

    //JPanel Menu_panel = new JPanel();
    private JButton Rating_button = new JButton();
    private JButton Appointment_button = new JButton();
    private JButton Prescription_button = new JButton();
    private JButton Home_button = new JButton();
    private JButton History_button = new JButton();
    private CalendarWindow from = new CalendarWindow("from");
    private CalendarWindow to = new CalendarWindow("to");
    private SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
    private DefaultTableModel history_model;
    private DefaultTableModel model;
    private DefaultTableModel rating_model;
    private DefaultTableModel new_rating_model;
    private Gson gson;
    private Reader reader;
    private Reader history_reader;

    public static String name;
    public static String user_id;
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form MainScreen
     * UI design at main screen of patients
     */
    public MainScreen() throws IOException {
        initComponents();

        this.setLocationRelativeTo(null);
        Image icon = Toolkit.getDefaultToolkit().getImage("src/icon/icons8_hospital_3_30px.png");
        this.setIconImage(icon);
        Menu_panel.setVisible(false);

        Home_button.setBounds(0, 0, 250, 100);
        Home_button.setText("Home");
        Home_button.setFont(new Font("Arial", Font.BOLD, 25));
        Home_button.setForeground(Color.white);
        Home_button.setBorderPainted(false);
        Home_button.setFocusPainted(false);
        Home_button.setBackground(new Color(102, 102, 255));
        Home_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon Home_label_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/icon/icons8_home_50px.png"));
        Home_button.setIcon(Home_label_icon);

        Appointment_button.setBounds(0, 100, 250, 100);
        Appointment_button.setText("Appointment");
        Appointment_button.setFont(new Font("Arial", Font.BOLD, 25));
        Appointment_button.setForeground(Color.white);
        Appointment_button.setBorderPainted(false);
        Appointment_button.setFocusPainted(false);
        Appointment_button.setBackground(new Color(102, 102, 255));
        Appointment_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon Appointment_label_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/icon/icons8_calendar_plus_50px.png"));
        Appointment_button.setIcon(Appointment_label_icon);

        Rating_button.setBounds(0, 200, 250, 100);
        Rating_button.setText("Rating");
        Rating_button.setFont(new Font("Arial", Font.BOLD, 25));
        Rating_button.setForeground(Color.white);
        Rating_button.setBorderPainted(false);
        Rating_button.setFocusPainted(false);
        Rating_button.setBackground(new Color(102, 102, 255));
        Rating_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon Doctor_label_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/icon/icons8_star_half_empty_50px.png"));
        Rating_button.setIcon(Doctor_label_icon);

        History_button.setBounds(0, 300, 250, 100);
        History_button.setText("History");
        History_button.setFont(new Font("Arial", Font.BOLD, 25));
        History_button.setForeground(Color.white);
        History_button.setBorderPainted(false);
        History_button.setFocusPainted(false);
        History_button.setBackground(new Color(102, 102, 255));
        History_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon History_label_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/icon/icons8_time_machine_50px.png"));
        History_button.setIcon(History_label_icon);

        Home_button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                Home_button.setBackground(new Color(51, 51, 255));
            }

            public void mouseExited(MouseEvent evt) {
                Home_button.setBackground(new Color(102, 102, 255));
            }
        });

        Home_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home_action();
            }
        });

        Rating_button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                Rating_button.setBackground(new Color(51, 51, 255));
            }

            public void mouseExited(MouseEvent evt) {
                Rating_button.setBackground(new Color(102, 102, 255));
            }
        });

        Rating_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    rating_action();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Appointment_button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                Appointment_button.setBackground(new Color(51, 51, 255));
            }

            public void mouseExited(MouseEvent evt) {
                Appointment_button.setBackground(new Color(102, 102, 255));
            }
        });

        Appointment_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    appointment_action();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        History_button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                History_button.setBackground(new Color(51, 51, 255));
            }

            public void mouseExited(MouseEvent evt) {
                History_button.setBackground(new Color(102, 102, 255));
            }
        });

        History_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    history_action();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Menu_panel.add(Rating_button);
        Menu_panel.add(Appointment_button);
        Menu_panel.add(Prescription_button);
        Menu_panel.add(Home_button);
        Menu_panel.add(History_button);
        Name_label.setText(name);
        //User_id_label.setText(user_id);
        Appointment_userid_text.setText(user_id);
        Rating_userid_label.setText(user_id);
        Date_from_text.setText(sdf.format(new Date()));
        Date_to_text.setText(sdf.format(new Date()));
        Appointment_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));
        History_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));
        Rating_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));
        Rating_history_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));
        //wire a listener for the PropertyChange event of the calendar window
        from.addPropertyChangeListener(this);
        to.addPropertyChangeListener(this);

        gson = new GsonBuilder().create();
        reader = Files.newBufferedReader(Paths.get("src//data//account.json"));
        java.lang.reflect.Type doctor_listType = new TypeToken<ArrayList<Account>>() {
        }.getType();
        ArrayList<Account> doctors = gson.fromJson(reader, doctor_listType);

        for (Account d : doctors) {
            if (d.getLetter().equals("D")) {
                Doctor_combobox.addItem(d.getGiven_name() + " " + d.getSurname());
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * Button action, table action of main screen
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menu_panel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Navigation_panel = new javax.swing.JPanel();
        Menu_button = new javax.swing.JButton();
        Logout_button = new javax.swing.JButton();
        Name_label = new javax.swing.JLabel();
        User_id_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Terminate_button = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Main_panel = new javax.swing.JPanel();
        Home_rating_button = new javax.swing.JButton();
        Home_appointment_button = new javax.swing.JButton();
        Home_history_button = new javax.swing.JButton();
        Appointment_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Appointment_table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Appointment_userid_text = new javax.swing.JLabel();
        Doctor_combobox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        Date_from_text = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        Date_to_text = new javax.swing.JFormattedTextField();
        Request_appointment_button = new javax.swing.JButton();
        Rating_panel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Rating_table = new javax.swing.JTable();
        Rating_slider = new javax.swing.JSlider();
        jScrollPane4 = new javax.swing.JScrollPane();
        Rating_history_table = new javax.swing.JTable();
        Rating_doctor_button = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Doctor_name_label = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Rating_userid_label = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Feedback_text = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        History_no_label = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        History_panel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        History_table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Patient Management System");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(100, 100));
        setMaximumSize(new java.awt.Dimension(1500, 1000));
        setMinimumSize(new java.awt.Dimension(1500, 1000));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        Menu_panel.setBackground(new java.awt.Color(102, 102, 255));
        Menu_panel.setMinimumSize(new java.awt.Dimension(250, 1000));
        Menu_panel.setPreferredSize(new java.awt.Dimension(250, 1000));

        javax.swing.GroupLayout Menu_panelLayout = new javax.swing.GroupLayout(Menu_panel);
        Menu_panel.setLayout(Menu_panelLayout);
        Menu_panelLayout.setHorizontalGroup(
            Menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        Menu_panelLayout.setVerticalGroup(
            Menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1010, Short.MAX_VALUE)
        );

        getContentPane().add(Menu_panel);

        jPanel4.setPreferredSize(new java.awt.Dimension(1950, 1050));
        jPanel4.setLayout(new java.awt.BorderLayout());

        Navigation_panel.setBackground(new java.awt.Color(255, 255, 255));
        Navigation_panel.setPreferredSize(new java.awt.Dimension(61, 60));

        Menu_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_menu_40px_1.png"))); // NOI18N
        Menu_button.setBorder(null);
        Menu_button.setContentAreaFilled(false);
        Menu_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Menu_button.setFocusable(false);
        Menu_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_buttonActionPerformed(evt);
            }
        });

        Logout_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Logout_button.setForeground(new java.awt.Color(102, 102, 255));
        Logout_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_exit_40px.png"))); // NOI18N
        Logout_button.setText("Logout");
        Logout_button.setBorder(null);
        Logout_button.setContentAreaFilled(false);
        Logout_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logout_button.setFocusPainted(false);
        Logout_button.setFocusable(false);
        Logout_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout_buttonActionPerformed(evt);
            }
        });

        Name_label.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        Name_label.setForeground(new java.awt.Color(255, 51, 153));

        User_id_label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        User_id_label.setForeground(new java.awt.Color(255, 51, 153));
        User_id_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        User_id_label.setToolTipText("");
        User_id_label.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("Patient Management System (Patient)");
        jLabel1.setToolTipText("");

        Terminate_button.setBackground(new java.awt.Color(255, 102, 102));
        Terminate_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Terminate_button.setForeground(new java.awt.Color(255, 255, 255));
        Terminate_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_warning_30px.png"))); // NOI18N
        Terminate_button.setText("Terminate");
        Terminate_button.setBorderPainted(false);
        Terminate_button.setFocusPainted(false);
        Terminate_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Terminate_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Navigation_panelLayout = new javax.swing.GroupLayout(Navigation_panel);
        Navigation_panel.setLayout(Navigation_panelLayout);
        Navigation_panelLayout.setHorizontalGroup(
            Navigation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Navigation_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Menu_button)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
                .addComponent(User_id_label, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Terminate_button)
                .addGap(86, 86, 86)
                .addComponent(Name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Logout_button, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Navigation_panelLayout.setVerticalGroup(
            Navigation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Navigation_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Navigation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(User_id_label, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Navigation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Name_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Logout_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Menu_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Terminate_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        jPanel4.add(Navigation_panel, java.awt.BorderLayout.PAGE_START);
        Navigation_panel.getAccessibleContext().setAccessibleName("");

        jPanel1.setLayout(new java.awt.CardLayout());

        Main_panel.setBackground(new java.awt.Color(255, 255, 255));
        Main_panel.setMinimumSize(new java.awt.Dimension(1500, 900));
        Main_panel.setPreferredSize(new java.awt.Dimension(1500, 900));

        Home_rating_button.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        Home_rating_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_star_half_empty_90px.png"))); // NOI18N
        Home_rating_button.setText("Rating");
        Home_rating_button.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        Home_rating_button.setContentAreaFilled(false);
        Home_rating_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home_rating_button.setFocusPainted(false);
        Home_rating_button.setFocusable(false);
        Home_rating_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Home_rating_buttonActionPerformed(evt);
            }
        });

        Home_appointment_button.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        Home_appointment_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_calendar_plus_90px.png"))); // NOI18N
        Home_appointment_button.setText("Appointment");
        Home_appointment_button.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        Home_appointment_button.setContentAreaFilled(false);
        Home_appointment_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home_appointment_button.setFocusPainted(false);
        Home_appointment_button.setFocusable(false);
        Home_appointment_button.setMaximumSize(new java.awt.Dimension(300, 300));
        Home_appointment_button.setMinimumSize(new java.awt.Dimension(300, 300));
        Home_appointment_button.setPreferredSize(new java.awt.Dimension(300, 300));
        Home_appointment_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Home_appointment_buttonActionPerformed(evt);
            }
        });

        Home_history_button.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        Home_history_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_time_machine_90px_1.png"))); // NOI18N
        Home_history_button.setText("History");
        Home_history_button.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        Home_history_button.setContentAreaFilled(false);
        Home_history_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home_history_button.setFocusable(false);
        Home_history_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Home_history_buttonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Main_panelLayout = new javax.swing.GroupLayout(Main_panel);
        Main_panel.setLayout(Main_panelLayout);
        Main_panelLayout.setHorizontalGroup(
            Main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_panelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(Home_appointment_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(262, 262, 262)
                .addComponent(Home_rating_button, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(255, 255, 255)
                .addComponent(Home_history_button, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );
        Main_panelLayout.setVerticalGroup(
            Main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_panelLayout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addGroup(Main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Home_rating_button, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Home_appointment_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Home_history_button, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(401, Short.MAX_VALUE))
        );

        jPanel1.add(Main_panel, "card2");

        Appointment_panel.setBackground(new java.awt.Color(255, 255, 255));
        Appointment_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Appointment_panelMouseClicked(evt);
            }
        });

        Appointment_table.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Appointment_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Appointment No", "Doctor", "Potential Date From", "Potential Date From"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Appointment_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Appointment_table.setRequestFocusEnabled(false);
        Appointment_table.setRowHeight(25);
        Appointment_table.setSelectionBackground(new java.awt.Color(102, 102, 255));
        jScrollPane1.setViewportView(Appointment_table);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel2.setText("Appointment");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setLabelFor(Doctor_combobox);
        jLabel3.setText("Doctor");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("User ID");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_calendar_30px.png"))); // NOI18N
        jLabel5.setText("Potential Dates Range");

        Appointment_userid_text.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        Doctor_combobox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Doctor_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setLabelFor(Date_from_text);
        jLabel7.setText("From");

        Date_from_text.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        Date_from_text.setToolTipText("");
        Date_from_text.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Date_from_text.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Date_from_textMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setText("To");

        Date_to_text.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        Date_to_text.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Date_to_text.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Date_to_textMouseClicked(evt);
            }
        });

        Request_appointment_button.setBackground(new java.awt.Color(102, 102, 255));
        Request_appointment_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Request_appointment_button.setForeground(new java.awt.Color(255, 255, 255));
        Request_appointment_button.setText("Request Appointment");
        Request_appointment_button.setBorderPainted(false);
        Request_appointment_button.setFocusable(false);
        Request_appointment_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Request_appointment_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Appointment_panelLayout = new javax.swing.GroupLayout(Appointment_panel);
        Appointment_panel.setLayout(Appointment_panelLayout);
        Appointment_panelLayout.setHorizontalGroup(
            Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Appointment_panelLayout.createSequentialGroup()
                .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Appointment_panelLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Appointment_panelLayout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(Date_to_text))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Appointment_panelLayout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Appointment_panelLayout.createSequentialGroup()
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(33, 33, 33))
                                        .addGroup(Appointment_panelLayout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Appointment_userid_text, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Doctor_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Appointment_panelLayout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(Date_from_text, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(Appointment_panelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Appointment_panelLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(Request_appointment_button, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1438, Short.MAX_VALUE)
                .addContainerGap())
        );
        Appointment_panelLayout.setVerticalGroup(
            Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Appointment_panelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Appointment_panelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Appointment_userid_text, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Doctor_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Date_from_text, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Date_to_text, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addComponent(Request_appointment_button, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        jPanel1.add(Appointment_panel, "card4");

        Rating_panel.setBackground(new java.awt.Color(255, 255, 255));

        Rating_table.setAutoCreateRowSorter(true);
        Rating_table.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Rating_table.setForeground(new java.awt.Color(102, 102, 255));
        Rating_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doctor Name", "Average Rating"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Rating_table.setRowHeight(25);
        jScrollPane3.setViewportView(Rating_table);

        Rating_slider.setMaximum(5);
        Rating_slider.setMinimum(1);
        Rating_slider.setValue(3);

        Rating_history_table.setAutoCreateRowSorter(true);
        Rating_history_table.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Rating_history_table.setForeground(new java.awt.Color(102, 102, 255));
        Rating_history_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Date", "Doctor Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Rating_history_table.setRowHeight(25);
        Rating_history_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Rating_history_tableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(Rating_history_table);

        Rating_doctor_button.setBackground(new java.awt.Color(102, 102, 255));
        Rating_doctor_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Rating_doctor_button.setForeground(new java.awt.Color(255, 255, 255));
        Rating_doctor_button.setText("Rating");
        Rating_doctor_button.setBorderPainted(false);
        Rating_doctor_button.setFocusPainted(false);
        Rating_doctor_button.setFocusable(false);
        Rating_doctor_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rating_doctor_buttonActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setText("5");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel10.setText("1");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setText("Rating");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setText("Doctor");

        Doctor_name_label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel13.setText("Rating");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setText("User ID");

        Rating_userid_label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel16.setText("Feedback");

        Feedback_text.setColumns(20);
        Feedback_text.setRows(5);
        jScrollPane5.setViewportView(Feedback_text);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setText("Consultation History");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setText("History No");
        jLabel17.setToolTipText("");

        History_no_label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setText("Please select one history record in history table.");

        javax.swing.GroupLayout Rating_panelLayout = new javax.swing.GroupLayout(Rating_panel);
        Rating_panel.setLayout(Rating_panelLayout);
        Rating_panelLayout.setHorizontalGroup(
            Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Rating_panelLayout.createSequentialGroup()
                .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Rating_panelLayout.createSequentialGroup()
                        .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Rating_panelLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Rating_panelLayout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addGap(42, 42, 42)
                                .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Rating_doctor_button, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(Rating_panelLayout.createSequentialGroup()
                                        .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(Doctor_name_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Rating_userid_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Rating_panelLayout.createSequentialGroup()
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Rating_slider, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9))
                                    .addComponent(History_no_label, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Rating_panelLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1348, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addGroup(Rating_panelLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Rating_panelLayout.setVerticalGroup(
            Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Rating_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Rating_panelLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Rating_panelLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addGap(64, 64, 64)
                        .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Rating_panelLayout.createSequentialGroup()
                                .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Rating_userid_label, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54)
                                .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(History_no_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                                .addGap(52, 52, 52)
                                .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Doctor_name_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)
                                .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10))
                                    .addComponent(Rating_slider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Rating_panelLayout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Rating_panelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(Rating_doctor_button, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1.add(Rating_panel, "card3");

        History_panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel6.setText("History");

        History_table.setAutoCreateRowSorter(true);
        History_table.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        History_table.setForeground(new java.awt.Color(102, 102, 255));
        History_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Doctor Name", "Disease", "Medicine"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        History_table.setRowHeight(25);
        History_table.setSelectionBackground(new java.awt.Color(102, 102, 255));
        History_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                History_tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(History_table);

        javax.swing.GroupLayout History_panelLayout = new javax.swing.GroupLayout(History_panel);
        History_panel.setLayout(History_panelLayout);
        History_panelLayout.setHorizontalGroup(
            History_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(History_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(History_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1896, Short.MAX_VALUE)
                    .addGroup(History_panelLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        History_panelLayout.setVerticalGroup(
            History_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(History_panelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 863, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(History_panel, "card6");

        jPanel4.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel4);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Menu_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_buttonActionPerformed
        // TODO add your handling code here:

        if (Menu_panel.isVisible()) {
            Menu_panel.setVisible(false);
        } else {
            Menu_panel.setVisible(true);
        }

    }//GEN-LAST:event_Menu_buttonActionPerformed

    private void Logout_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout_buttonActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showOptionDialog(null, "Are you sure to logout?", "Logout", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if (choice == JOptionPane.OK_OPTION) {
            try {
                this.setVisible(false);
                new LoginScreen().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Logout_buttonActionPerformed

    private void Home_rating_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Home_rating_buttonActionPerformed
        try {
            // TODO add your handling code here:
            rating_action();
        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Home_rating_buttonActionPerformed

    private void Home_appointment_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Home_appointment_buttonActionPerformed
        try {
            // TODO add your handling code here:
            appointment_action();
        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Home_appointment_buttonActionPerformed

    private void Home_history_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home_history_buttonMouseClicked
        try {
            // TODO add your handling code here:
            history_action();
        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Home_history_buttonMouseClicked

    private void Appointment_panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Appointment_panelMouseClicked
        // TODO add your handling code here:
        /*from.setUndecorated(false);
        from.setVisible(false);
        to.setUndecorated(false);
        to.setVisible(false);*/
    }//GEN-LAST:event_Appointment_panelMouseClicked

    private void Date_to_textMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Date_to_textMouseClicked
        // TODO add your handling code here:
        to.setLocation(Date_to_text.getLocationOnScreen().x, (Date_to_text.getLocationOnScreen().y + Date_to_text.getHeight()));
        //get the Date and assign it to the calendar
        Date d = (Date) Date_from_text.getValue();

        to.resetSelection(d);
        to.setUndecorated(true);
        to.setVisible(true);
    }//GEN-LAST:event_Date_to_textMouseClicked

    private void Date_from_textMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Date_from_textMouseClicked
        // TODO add your handling code here:
        from.setLocation(Date_from_text.getLocationOnScreen().x, (Date_from_text.getLocationOnScreen().y + Date_from_text.getHeight()));
        //get the Date and assign it to the calendar
        Date d = new Date();

        from.resetSelection(d);
        from.setUndecorated(true);
        from.setVisible(true);
    }//GEN-LAST:event_Date_from_textMouseClicked

    private void Request_appointment_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Request_appointment_buttonActionPerformed
        // TODO add your handling code here:
        model = (DefaultTableModel) Appointment_table.getModel();

        if (Doctor_combobox.getSelectedIndex() != 0 && !Date_from_text.getText().isEmpty() && !Date_to_text.getText().isEmpty()) {
            try {
                Appointment new_appointment = new Appointment(0, Appointment_userid_text.getText(), Doctor_combobox.getSelectedItem().toString(), sdf.parse(Date_from_text.getText()), sdf.parse(Date_to_text.getText()), new Date(), false);
                new NewAppointment().addNewAppointment(new_appointment);
                this.Doctor_combobox.setSelectedIndex(-1);

                appointment_action();
            } catch (ParseException | IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //model.addRow(new Object[] {appointment_count, Doctor_combobox.getSelectedItem().toString(), Date_from_text.getText(), Date_to_text.getText()});
    }//GEN-LAST:event_Request_appointment_buttonActionPerformed

    private void Terminate_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Terminate_buttonActionPerformed
        // TODO add your handling code here:
        int first_choice = JOptionPane.showOptionDialog(null, "Are you sure to terminate your account? After termination, you cannot login the system again!", "Terminate", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if (first_choice == JOptionPane.OK_OPTION) {

            int second_choice = JOptionPane.showOptionDialog(null, "The system will logout immediately after this double confirm!", "Terminate", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (second_choice == JOptionPane.OK_OPTION) {
                try {
                    new AccountApproval().changeStatus(user_id, "Waiting For Delete");
                    this.setVisible(false);
                    new LoginScreen().setVisible(true);
                } catch (IOException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_Terminate_buttonActionPerformed

    private void History_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_History_tableMouseClicked
        // TODO add your handling code here:

        JFrame history_screen;
        try {
            history_screen = new HistoryScreen(user_id, History_table.getValueAt(History_table.getSelectedRow(), 1).toString(), History_table.getValueAt(History_table.getSelectedRow(), 2).toString(), History_table.getValueAt(History_table.getSelectedRow(), 0).toString(), name);
            history_screen.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_History_tableMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void Rating_doctor_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rating_doctor_buttonActionPerformed
        // TODO add your handling code here:
        if (!Feedback_text.getText().isEmpty() && !History_no_label.getText().isEmpty() && !Doctor_name_label.getText().isEmpty()) {
            Rating new_rating = new Rating(Doctor_name_label.getText(), Rating_userid_label.getText(), new Date(), Rating_slider.getValue(), Feedback_text.getText());
            try {
                new_rating.rate(Integer.parseInt(History_no_label.getText()));
                rating_action();
            } catch (IOException | NoSuchAlgorithmException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            Doctor_name_label.setText("");
            Feedback_text.setText("");
            History_no_label.setText("");
            Rating_slider.setValue(3);
        } else {
            JOptionPane.showMessageDialog(null, "Please fill in all data!", "WARNING!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_Rating_doctor_buttonActionPerformed

    private void Rating_history_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Rating_history_tableMouseClicked
        // TODO add your handling code here:
        Doctor_name_label.setText(Rating_history_table.getValueAt(Rating_history_table.getSelectedRow(), 2).toString());
        History_no_label.setText(Rating_history_table.getValueAt(Rating_history_table.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_Rating_history_tableMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainScreen().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * 
     * action at home panel
     */
    public void home_action() {
        Main_panel.setVisible(true);
        Rating_panel.setVisible(false);
        Appointment_panel.setVisible(false);
        Menu_panel.setVisible(false);
        History_panel.setVisible(false);
    }

    /**
     * 
     * action at rating panel
     */
    public void rating_action() throws IOException {
        Main_panel.setVisible(false);
        Rating_panel.setVisible(true);
        Appointment_panel.setVisible(false);
        Menu_panel.setVisible(false);
        History_panel.setVisible(false);

        gson = new GsonBuilder().create();
        rating_model = (DefaultTableModel) Rating_history_table.getModel();
        int row = Rating_history_table.getRowCount();

        for (int i = row - 1; i >= 0; i--) {
            rating_model.removeRow(i);
        }

        reader = Files.newBufferedReader(Paths.get("src//data//history.json"));
        java.lang.reflect.Type rating_listType = new TypeToken<ArrayList<ConsultationHistory>>() {
        }.getType();
        ArrayList<ConsultationHistory> histories = gson.fromJson(reader, rating_listType);

        for (ConsultationHistory h : histories) {
            if (h.isRating() == false) {
                rating_model.addRow(new Object[]{h.getHistory_no(), h.getDate(), h.getDoctor_name()});
            }
        }

        new_rating_model = (DefaultTableModel) Rating_table.getModel();
        int new_row = Rating_table.getRowCount();

        for (int i = new_row - 1; i >= 0; i--) {
            new_rating_model.removeRow(i);
        }

        reader = Files.newBufferedReader(Paths.get("src//data//account.json"));
        java.lang.reflect.Type doctor_listType = new TypeToken<ArrayList<Account>>() {
        }.getType();
        ArrayList<Account> doctors = gson.fromJson(reader, doctor_listType);

        for (Account d : doctors) {
            if (d.getLetter().equals("D")) {
                reader = Files.newBufferedReader(Paths.get("src//data//rating.json"));
                java.lang.reflect.Type new_rating_listType = new TypeToken<ArrayList<Rating>>() {
                }.getType();
                ArrayList<Rating> ratings = gson.fromJson(reader, new_rating_listType);

                double rating = 0;
                double number_of_rating = 0;
                for (Rating r : ratings) {
                    if (r.getDoctor_name().equals(d.getGiven_name() + " " + d.getSurname())) {
                        rating += r.getRating();
                        number_of_rating++;
                        
                    }
                }
                
                if(number_of_rating > 0) {
                    double average_rating = Math.round(rating/number_of_rating*10)/10.0;
                    new_rating_model.addRow(new Object[]{d.getGiven_name() + " " + d.getSurname(), average_rating});
                } else {
                    new_rating_model.addRow(new Object[]{d.getGiven_name() + " " + d.getSurname(), 0.0});
                }
            }

        }

    }

    /**
     * 
     * action at appointment panel
     */
    public void appointment_action() throws IOException {
        Main_panel.setVisible(false);
        Rating_panel.setVisible(false);
        Appointment_panel.setVisible(true);
        Menu_panel.setVisible(false);
        History_panel.setVisible(false);

        gson = new GsonBuilder().create();
        model = (DefaultTableModel) Appointment_table.getModel();
        int row = Appointment_table.getRowCount();

        for (int i = row - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        reader = Files.newBufferedReader(Paths.get("src//data//appointment.json"));
        java.lang.reflect.Type appointment_listType = new TypeToken<ArrayList<Appointment>>() {
        }.getType();
        ArrayList<Appointment> appointments = gson.fromJson(reader, appointment_listType);

        for (Appointment a : appointments) {
            if (a.getUser_id().equals(user_id)) {
                String date_from = sdf.format(a.getAppointment_date_from());
                String date_to = sdf.format(a.getAppointment_date_to());
                model.addRow(new Object[]{a.getAppointment_no(), a.getDoctor(), date_from, date_to});
            }
        }
    }

    /**
     * 
     * action at history panel
     */
    public void history_action() throws IOException {
        Main_panel.setVisible(false);
        Rating_panel.setVisible(false);
        Appointment_panel.setVisible(false);
        Menu_panel.setVisible(false);
        History_panel.setVisible(true);

        gson = new GsonBuilder().create();
        history_model = (DefaultTableModel) History_table.getModel();
        int row = History_table.getRowCount();

        for (int i = row - 1; i >= 0; i--) {
            history_model.removeRow(i);
        }

        history_reader = Files.newBufferedReader(Paths.get("src//data//history.json"));
        java.lang.reflect.Type history_listType = new TypeToken<ArrayList<ConsultationHistory>>() {
        }.getType();
        ArrayList<ConsultationHistory> histories = gson.fromJson(history_reader, history_listType);

        for (ConsultationHistory h : histories) {
            if (h.getUser_id().equals(user_id)) {
                history_model.addRow(new Object[]{sdf.format(h.getDate()), h.getDoctor_name(), h.getDisease(), h.getMedicines()});
            }
        }
    }
    
    /**
     * 
     * calendar function at appointment panel
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {

        //get the selected date from the calendar control and set it to the text field
        if (event.getPropertyName().equals("from_selectedDate")) {

            java.util.Calendar cal = (java.util.Calendar) event.getNewValue();
            Date from_selDate = cal.getTime();
            Date_from_text.setValue(from_selDate);
            Date_to_text.setValue(from_selDate);
        }

        if (event.getPropertyName().equals("to_selectedDate")) {

            java.util.Calendar cal = (java.util.Calendar) event.getNewValue();
            Date to_selDate = cal.getTime();
            Date_to_text.setValue(to_selDate);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Appointment_panel;
    private javax.swing.JTable Appointment_table;
    private javax.swing.JLabel Appointment_userid_text;
    private javax.swing.JFormattedTextField Date_from_text;
    private javax.swing.JFormattedTextField Date_to_text;
    private javax.swing.JComboBox<String> Doctor_combobox;
    private javax.swing.JLabel Doctor_name_label;
    private javax.swing.JTextArea Feedback_text;
    private javax.swing.JLabel History_no_label;
    public javax.swing.JPanel History_panel;
    private javax.swing.JTable History_table;
    private javax.swing.JButton Home_appointment_button;
    private javax.swing.JButton Home_history_button;
    private javax.swing.JButton Home_rating_button;
    private javax.swing.JButton Logout_button;
    private javax.swing.JPanel Main_panel;
    private javax.swing.JButton Menu_button;
    private javax.swing.JPanel Menu_panel;
    private javax.swing.JLabel Name_label;
    private javax.swing.JPanel Navigation_panel;
    private javax.swing.JButton Rating_doctor_button;
    private javax.swing.JTable Rating_history_table;
    private javax.swing.JPanel Rating_panel;
    private javax.swing.JSlider Rating_slider;
    private javax.swing.JTable Rating_table;
    private javax.swing.JLabel Rating_userid_label;
    private javax.swing.JButton Request_appointment_button;
    private javax.swing.JButton Terminate_button;
    private javax.swing.JLabel User_id_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables

}
