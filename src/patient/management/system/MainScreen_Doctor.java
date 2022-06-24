/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system;

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
import java.text.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import patient.management.system.function.AbstractNewMedicine;
import patient.management.system.function.Account;
import patient.management.system.function.AdminRating;
import patient.management.system.function.AppointmentApproved;
import patient.management.system.function.CalendarWindow;
import patient.management.system.function.ConsultationHistory;
import patient.management.system.ConsultationScreen;
import patient.management.system.HistoryScreen;
import patient.management.system.LoginScreen;
import patient.management.system.MainScreen;
import patient.management.system.function.MedicineRequest;
import patient.management.system.function.MedicineStock;
import patient.management.system.function.NewMedicine;

import java.beans.PropertyChangeListener;

/**
 *
 * @author James Man menu
 */
public class MainScreen_Doctor extends javax.swing.JFrame implements PropertyChangeListener {

    //JPanel Menu_panel = new JPanel();
    JButton Appointment_button = new JButton();
    JButton Medicine_button = new JButton();
    JButton Home_button = new JButton();
    JButton History_button = new JButton();
    CalendarWindow from = new CalendarWindow("from");
    CalendarWindow to = new CalendarWindow("to");
    SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
    DefaultTableModel stock_model;
    DefaultTableModel request_model;
    DefaultTableModel history_model;
    DefaultTableModel appointment_model;
    Gson gson;
    Reader appointment_reader;
    Reader account_reader;
    Reader medicine_stock_reader;
    Reader medicine_request_reader;
    Reader history_reader;
    Reader rating_reader;

    public static String name;
    public static String user_id;
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form MainScreen
     */
    public MainScreen_Doctor() throws IOException {
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

        History_button.setBounds(0, 200, 250, 100);
        History_button.setText("History");
        History_button.setFont(new Font("Arial", Font.BOLD, 25));
        History_button.setForeground(Color.white);
        History_button.setBorderPainted(false);
        History_button.setFocusPainted(false);
        History_button.setBackground(new Color(102, 102, 255));
        History_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon History_label_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/icon/icons8_time_machine_50px.png"));
        History_button.setIcon(History_label_icon);

        Medicine_button.setBounds(0, 300, 250, 100);
        Medicine_button.setText("Medicine");
        Medicine_button.setFont(new Font("Arial", Font.BOLD, 25));
        Medicine_button.setForeground(Color.white);
        Medicine_button.setBorderPainted(false);
        Medicine_button.setFocusPainted(false);
        Medicine_button.setBackground(new Color(102, 102, 255));
        Medicine_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon Medicine_label_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/icon/icons8_pills_50px.png"));
        Medicine_button.setIcon(Medicine_label_icon);

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
                    Logger.getLogger(MainScreen_Doctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Medicine_button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                Medicine_button.setBackground(new Color(51, 51, 255));
            }

            public void mouseExited(MouseEvent evt) {
                Medicine_button.setBackground(new Color(102, 102, 255));
            }
        });

        Medicine_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    medicine_action();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen_Doctor.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(MainScreen_Doctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Menu_panel.add(Appointment_button);
        Menu_panel.add(Medicine_button);
        Menu_panel.add(Home_button);
        Menu_panel.add(History_button);
        Name_label.setText(name);
        //User_id_label.setText(user_id);
        Appointment_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));
        Medicine_request_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        Medicine_stock_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        History_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));

        //wire a listener for the PropertyChange event of the calendar window
        from.addPropertyChangeListener(this);
        to.addPropertyChangeListener(this);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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
        Feedback_button = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Main_panel = new javax.swing.JPanel();
        Home_history_button = new javax.swing.JButton();
        Home_appointment_button = new javax.swing.JButton();
        Home_medicine_button = new javax.swing.JButton();
        Appointment_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Appointment_table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        History_panel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        History_table = new javax.swing.JTable();
        Medicine_panel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Medicine_request_table = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        Medicine_stock_table = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Medicine_name_text = new javax.swing.JTextField();
        Medicin_create_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Patient Management System");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(100, 100));
        setMinimumSize(new java.awt.Dimension(1500, 1000));
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
            .addGap(0, 1178, Short.MAX_VALUE)
        );

        getContentPane().add(Menu_panel);

        jPanel4.setPreferredSize(new java.awt.Dimension(1950, 990));
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
        jLabel1.setText("Patient Management System (Doctor)");
        jLabel1.setToolTipText("");

        Feedback_button.setBackground(new java.awt.Color(102, 102, 255));
        Feedback_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Feedback_button.setForeground(new java.awt.Color(255, 255, 255));
        Feedback_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_comments_30px.png"))); // NOI18N
        Feedback_button.setText("Feedback");
        Feedback_button.setBorderPainted(false);
        Feedback_button.setFocusable(false);
        Feedback_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Feedback_buttonActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 234, Short.MAX_VALUE)
                .addComponent(User_id_label, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(Feedback_button, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
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
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Navigation_panelLayout.createSequentialGroup()
                        .addGroup(Navigation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(User_id_label, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Navigation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Name_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Logout_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Menu_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Feedback_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );

        jPanel4.add(Navigation_panel, java.awt.BorderLayout.PAGE_START);
        Navigation_panel.getAccessibleContext().setAccessibleName("");

        jPanel1.setLayout(new java.awt.CardLayout());

        Main_panel.setBackground(new java.awt.Color(255, 255, 255));
        Main_panel.setMinimumSize(new java.awt.Dimension(1500, 900));
        Main_panel.setPreferredSize(new java.awt.Dimension(1500, 900));

        Home_history_button.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        Home_history_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_time_machine_90px_1.png"))); // NOI18N
        Home_history_button.setText("History");
        Home_history_button.setToolTipText("");
        Home_history_button.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        Home_history_button.setContentAreaFilled(false);
        Home_history_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home_history_button.setFocusPainted(false);
        Home_history_button.setFocusable(false);
        Home_history_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Home_history_buttonActionPerformed(evt);
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

        Home_medicine_button.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        Home_medicine_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_pills_90px.png"))); // NOI18N
        Home_medicine_button.setText("Medicine");
        Home_medicine_button.setToolTipText("");
        Home_medicine_button.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        Home_medicine_button.setContentAreaFilled(false);
        Home_medicine_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home_medicine_button.setFocusPainted(false);
        Home_medicine_button.setFocusable(false);
        Home_medicine_button.setMinimumSize(new java.awt.Dimension(300, 300));
        Home_medicine_button.setPreferredSize(new java.awt.Dimension(300, 300));
        Home_medicine_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Home_medicine_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Main_panelLayout = new javax.swing.GroupLayout(Main_panel);
        Main_panel.setLayout(Main_panelLayout);
        Main_panelLayout.setHorizontalGroup(
            Main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_panelLayout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(Home_appointment_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(172, 172, 172)
                .addComponent(Home_history_button, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addGap(159, 159, 159)
                .addComponent(Home_medicine_button, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addGap(124, 124, 124))
        );
        Main_panelLayout.setVerticalGroup(
            Main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_panelLayout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addGroup(Main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Home_appointment_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Home_medicine_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Home_history_button, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(514, 514, 514))
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
                "Appointment No", "Date", "User ID", "Patient Name", "Sex", "Age"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Appointment_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Appointment_table.setRequestFocusEnabled(false);
        Appointment_table.setRowHeight(30);
        Appointment_table.setSelectionBackground(new java.awt.Color(102, 102, 255));
        Appointment_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Appointment_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Appointment_table);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel2.setText("Appointment");

        javax.swing.GroupLayout Appointment_panelLayout = new javax.swing.GroupLayout(Appointment_panel);
        Appointment_panel.setLayout(Appointment_panelLayout);
        Appointment_panelLayout.setHorizontalGroup(
            Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Appointment_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Appointment_panelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1486, Short.MAX_VALUE))
                .addContainerGap())
        );
        Appointment_panelLayout.setVerticalGroup(
            Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Appointment_panelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1038, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(Appointment_panel, "card4");

        History_panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel12.setText("History");

        History_table.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        History_table.setForeground(new java.awt.Color(102, 102, 255));
        History_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Patient Name", "Date", "Doctor Name", "Disease", "Medicine"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        jScrollPane4.setViewportView(History_table);

        javax.swing.GroupLayout History_panelLayout = new javax.swing.GroupLayout(History_panel);
        History_panel.setLayout(History_panelLayout);
        History_panelLayout.setHorizontalGroup(
            History_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(History_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(History_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(History_panelLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1486, Short.MAX_VALUE))
                .addContainerGap())
        );
        History_panelLayout.setVerticalGroup(
            History_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(History_panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(History_panel, "card6");

        Medicine_panel.setBackground(new java.awt.Color(255, 255, 255));

        Medicine_request_table.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Medicine_request_table.setForeground(new java.awt.Color(102, 102, 255));
        Medicine_request_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doctor Name", "Medicine Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Medicine_request_table.setRowHeight(25);
        Medicine_request_table.setSelectionBackground(new java.awt.Color(102, 102, 255));
        jScrollPane2.setViewportView(Medicine_request_table);

        Medicine_stock_table.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Medicine_stock_table.setForeground(new java.awt.Color(102, 102, 255));
        Medicine_stock_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doctor Name", "Medicine Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Medicine_stock_table.setRowHeight(25);
        Medicine_stock_table.setSelectionBackground(new java.awt.Color(102, 102, 255));
        jScrollPane3.setViewportView(Medicine_stock_table);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setText("Medicine Request");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("Medicine Stock");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel10.setText("Create medicine");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setText("Medicine Name");

        Medicine_name_text.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        Medicin_create_button.setBackground(new java.awt.Color(102, 102, 255));
        Medicin_create_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Medicin_create_button.setForeground(new java.awt.Color(255, 255, 255));
        Medicin_create_button.setText("Create");
        Medicin_create_button.setBorderPainted(false);
        Medicin_create_button.setFocusable(false);
        Medicin_create_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Medicin_create_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Medicine_panelLayout = new javax.swing.GroupLayout(Medicine_panel);
        Medicine_panel.setLayout(Medicine_panelLayout);
        Medicine_panelLayout.setHorizontalGroup(
            Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Medicine_panelLayout.createSequentialGroup()
                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Medicine_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Medicine_panelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)))
                    .addGroup(Medicine_panelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(Medicine_panelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Medicine_name_text, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(Medicin_create_button, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Medicine_panelLayout.setVerticalGroup(
            Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Medicine_panelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Medicine_panelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Medicine_name_text, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Medicine_panelLayout.createSequentialGroup()
                        .addComponent(Medicin_create_button, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)))
                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        jPanel1.add(Medicine_panel, "card5");

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
                Logger.getLogger(MainScreen_Doctor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Logout_buttonActionPerformed

    private void Home_history_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Home_history_buttonActionPerformed
        try {
            // TODO add your handling code here:
            history_action();
        } catch (IOException ex) {
            Logger.getLogger(MainScreen_Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Home_history_buttonActionPerformed

    private void Home_appointment_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Home_appointment_buttonActionPerformed
        try {
            // TODO add your handling code here:
            appointment_action();
        } catch (IOException ex) {
            Logger.getLogger(MainScreen_Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Home_appointment_buttonActionPerformed

    private void Appointment_panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Appointment_panelMouseClicked
        // TODO add your handling code here:
        /*from.setUndecorated(false);
        from.setVisible(false);
        to.setUndecorated(false);
        to.setVisible(false);*/
    }//GEN-LAST:event_Appointment_panelMouseClicked

    private void Home_medicine_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Home_medicine_buttonActionPerformed
        try {
            // TODO add your handling code here:
            medicine_action();
        } catch (IOException ex) {
            Logger.getLogger(MainScreen_Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Home_medicine_buttonActionPerformed

    private void Medicin_create_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Medicin_create_buttonActionPerformed
        // TODO add your handling code here:
        if (!Medicine_name_text.getText().isEmpty()) {
            MedicineRequest medicine_request = new MedicineRequest(Medicine_name_text.getText(), name);
            try {
                AbstractNewMedicine new_medicine = NewMedicine.updateMedicine(Medicine_name_text.getText());

                new_medicine.createNewMedicine(medicine_request);

                medicine_action();
            } catch (IOException ex) {
                Logger.getLogger(MainScreen_Doctor.class.getName()).log(Level.SEVERE, null, ex);
            }
            Medicine_name_text.setText("");
        }
    }//GEN-LAST:event_Medicin_create_buttonActionPerformed

    private void History_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_History_tableMouseClicked
        // TODO add your handling code here:
        try {
            JFrame history_screen = new HistoryScreen(History_table.getValueAt(History_table.getSelectedRow(), 0).toString(), History_table.getValueAt(History_table.getSelectedRow(), 3).toString(), History_table.getValueAt(History_table.getSelectedRow(), 4).toString(), History_table.getValueAt(History_table.getSelectedRow(), 2).toString(), name);
            WindowAdapter adapter = new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.out.println("Focus Lost");
                }
            };

            history_screen.addWindowListener(adapter);
            history_screen.setVisible(true);

        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_History_tableMouseClicked

    private void Appointment_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Appointment_tableMouseClicked
        // TODO add your handling code here:
        try {
            JFrame consultation_screen = new ConsultationScreen(Appointment_table.getValueAt(Appointment_table.getSelectedRow(), 3).toString(), Appointment_table.getValueAt(Appointment_table.getSelectedRow(), 2).toString(), Appointment_table.getValueAt(Appointment_table.getSelectedRow(), 5).toString(), Appointment_table.getValueAt(Appointment_table.getSelectedRow(), 4).toString(), Appointment_table.getValueAt(Appointment_table.getSelectedRow(), 1).toString(), name, Integer.parseInt(Appointment_table.getValueAt(Appointment_table.getSelectedRow(), 0).toString()));
            consultation_screen.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(MainScreen_Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_Appointment_tableMouseClicked

    private void Feedback_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Feedback_buttonActionPerformed
        // TODO add your handling code here:
        try {
            gson = new GsonBuilder().create();
            rating_reader = Files.newBufferedReader(Paths.get("src//data//admin_rating.json"));
            java.lang.reflect.Type rating_listType = new TypeToken<ArrayList<AdminRating>>() {
            }.getType();
            ArrayList<AdminRating> rating = gson.fromJson(rating_reader, rating_listType);

            for (AdminRating ar : rating) {
                if (ar.getDoctor_name().equals(name)) {
                    JOptionPane.showMessageDialog(null,ar.getFeedback(), "Feedback", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MainScreen_Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_Feedback_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(MainScreen_Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen_Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen_Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen_Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainScreen_Doctor().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen_Doctor.class.getName()).log(Level.SEVERE, null, ex);
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

    public void home_action() {
        Main_panel.setVisible(true);
        Appointment_panel.setVisible(false);
        Medicine_panel.setVisible(false);
        Menu_panel.setVisible(false);
        History_panel.setVisible(false);
    }

    public void appointment_action() throws IOException {
        Main_panel.setVisible(false);
        Appointment_panel.setVisible(true);
        Medicine_panel.setVisible(false);
        Menu_panel.setVisible(false);
        History_panel.setVisible(false);

        gson = new GsonBuilder().create();
        appointment_model = (DefaultTableModel) Appointment_table.getModel();
        int row = Appointment_table.getRowCount();

        for (int i = row - 1; i >= 0; i--) {
            appointment_model.removeRow(i);
        }

        appointment_reader = Files.newBufferedReader(Paths.get("src//data//appointment_approve.json"));
        java.lang.reflect.Type appointment_listType = new TypeToken<ArrayList<AppointmentApproved>>() {
        }.getType();
        ArrayList<AppointmentApproved> appointments = gson.fromJson(appointment_reader, appointment_listType);

        for (AppointmentApproved a : appointments) {
            if (a.getDoctor().equals(name) && a.isStatus() == false) {
                account_reader = Files.newBufferedReader(Paths.get("src//data//account.json"));
                java.lang.reflect.Type account_listType = new TypeToken<ArrayList<Account>>() {
                }.getType();
                ArrayList<Account> accounts = gson.fromJson(account_reader, account_listType);

                for (Account ac : accounts) {
                    if (a.getUser_id().equals(ac.getUser_id())) {
                        appointment_model.addRow(new Object[]{a.getAppointment_no(), sdf.format(a.getDate()), a.getUser_id(), ac.getGiven_name() + " " + ac.getSurname(), ac.getSex(), ac.getAge()});
                    }
                }
            }
        }

        Timer timer = new Timer(5000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    appointment_refresh();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen_Doctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        timer.start();
    }

    public void appointment_refresh() throws IOException {
        try {
            gson = new GsonBuilder().create();
            appointment_model = (DefaultTableModel) Appointment_table.getModel();
            int row = Appointment_table.getRowCount();

            for (int i = row - 1; i >= 0; i--) {
                appointment_model.removeRow(i);
            }

            appointment_reader = Files.newBufferedReader(Paths.get("src//data//appointment_approve.json"));
            java.lang.reflect.Type appointment_listType = new TypeToken<ArrayList<AppointmentApproved>>() {
            }.getType();
            ArrayList<AppointmentApproved> appointments = gson.fromJson(appointment_reader, appointment_listType);

            for (AppointmentApproved a : appointments) {
                if (a.getDoctor().equals(name) && a.isStatus() == false) {
                    account_reader = Files.newBufferedReader(Paths.get("src//data//account.json"));
                    java.lang.reflect.Type account_listType = new TypeToken<ArrayList<Account>>() {
                    }.getType();
                    ArrayList<Account> accounts = gson.fromJson(account_reader, account_listType);

                    for (Account ac : accounts) {
                        if (a.getUser_id().equals(ac.getUser_id())) {
                            appointment_model.addRow(new Object[]{a.getAppointment_no(), sdf.format(a.getDate()), a.getUser_id(), ac.getGiven_name() + " " + ac.getSurname(), ac.getSex(), ac.getAge()});
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MainScreen_Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void medicine_action() throws IOException {
        Main_panel.setVisible(false);
        Appointment_panel.setVisible(false);
        Medicine_panel.setVisible(true);
        Menu_panel.setVisible(false);
        History_panel.setVisible(false);

        gson = new GsonBuilder().create();
        stock_model = (DefaultTableModel) Medicine_stock_table.getModel();

        medicine_stock_reader = Files.newBufferedReader(Paths.get("src//data//medicine_stock.json"));
        java.lang.reflect.Type medicine_stock_listType = new TypeToken<ArrayList<MedicineStock>>() {
        }.getType();
        ArrayList<MedicineStock> medicines_stock = gson.fromJson(medicine_stock_reader, medicine_stock_listType);

        int stock_row = Medicine_stock_table.getRowCount();

        for (int i = stock_row - 1; i >= 0; i--) {
            stock_model.removeRow(i);
        }

        for (MedicineStock m : medicines_stock) {
            stock_model.addRow(new Object[]{m.getMedicine_name(), m.getQuantity()});
        }

        request_model = (DefaultTableModel) Medicine_request_table.getModel();
        medicine_request_reader = Files.newBufferedReader(Paths.get("src//data//medicine_request.json"));
        java.lang.reflect.Type medicine_request_listType = new TypeToken<ArrayList<MedicineRequest>>() {
        }.getType();
        ArrayList<MedicineRequest> medicines_request = gson.fromJson(medicine_request_reader, medicine_request_listType);

        int request_row = Medicine_request_table.getRowCount();

        for (int i = request_row - 1; i >= 0; i--) {
            request_model.removeRow(i);
        }

        for (MedicineRequest m : medicines_request) {
            request_model.addRow(new Object[]{m.getDoctor_name(), m.getMedicine_name()});
        }
    }

    public void history_action() throws IOException {
        Main_panel.setVisible(false);
        Appointment_panel.setVisible(false);
        Medicine_panel.setVisible(false);
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
            history_model.addRow(new Object[]{h.getUser_id(), h.getPatient_name(), sdf.format(h.getDate()), h.getDoctor_name(), h.getDisease(), h.getMedicines()});
        }

        History_table.getColumnModel().getColumn(0).setPreferredWidth(30);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {

        //get the selected date from the calendar control and set it to the text field
        if (event.getPropertyName().equals("from_selectedDate")) {

            java.util.Calendar cal = (java.util.Calendar) event.getNewValue();
            Date from_selDate = cal.getTime();

        }

        if (event.getPropertyName().equals("to_selectedDate")) {

            java.util.Calendar cal = (java.util.Calendar) event.getNewValue();
            Date to_selDate = cal.getTime();

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Appointment_panel;
    private javax.swing.JTable Appointment_table;
    private javax.swing.JButton Feedback_button;
    private javax.swing.JPanel History_panel;
    private javax.swing.JTable History_table;
    private javax.swing.JButton Home_appointment_button;
    private javax.swing.JButton Home_history_button;
    private javax.swing.JButton Home_medicine_button;
    private javax.swing.JButton Logout_button;
    private javax.swing.JPanel Main_panel;
    private javax.swing.JButton Medicin_create_button;
    private javax.swing.JTextField Medicine_name_text;
    private javax.swing.JPanel Medicine_panel;
    private javax.swing.JTable Medicine_request_table;
    private javax.swing.JTable Medicine_stock_table;
    private javax.swing.JButton Menu_button;
    private javax.swing.JPanel Menu_panel;
    private javax.swing.JLabel Name_label;
    private javax.swing.JPanel Navigation_panel;
    private javax.swing.JLabel User_id_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables

}
