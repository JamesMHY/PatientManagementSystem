/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system;

import patient.management.system.function.CalendarWindow;
import patient.management.system.function.AppointmentApproved;
import patient.management.system.function.Appointment;
import patient.management.system.function.Broker;
import patient.management.system.function.CreateAppointment;
import patient.management.system.function.MedicineRecord;
import patient.management.system.function.AccountApproval;
import patient.management.system.function.Account;
import patient.management.system.function.Order;
import patient.management.system.function.GivePatient;
import patient.management.system.function.MedicineRequest;
import patient.management.system.function.MedicineStock;
import patient.management.system.function.OrderNew;
import patient.management.system.function.Stock;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.awt.*;
import java.util.logging.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.security.NoSuchAlgorithmException;
import java.text.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author James Man menu
 * https://www.youtube.com/watch?v=F4wjbb91suQ&ab_channel=ProgrammingWizardsTV
 * combobox in table
 * https://stackoverflow.com/questions/6449350/make-jspinner-completely-numeric
 * spinner completely numeric
 * https://stackoverflow.com/questions/24881756/iterate-between-two-dates-including-start-date
 * all dates between two dates range
 */
public class MainScreen_Secretary extends javax.swing.JFrame {

    //JPanel Menu_panel = new JPanel();
    JButton Account_button = new JButton();
    JButton Appointment_button = new JButton();
    JButton Medicine_button = new JButton();
    JButton Home_button = new JButton();
    JButton History_button = new JButton();
    CalendarWindow from = new CalendarWindow("from");
    CalendarWindow to = new CalendarWindow("to");
    SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
    DefaultTableModel apply_model;
    DefaultTableModel approved_model;
    DefaultTableModel delete_model;
    DefaultTableModel stock_model;
    DefaultTableModel record_model;
    DefaultTableModel request_model;
    Gson gson;
    Reader reader;

    public static String name;
    public static String user_id;
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form MainScreen
     */
    public MainScreen_Secretary() throws IOException {
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

        Account_button.setBounds(0, 100, 250, 100);
        Account_button.setText("Account");
        Account_button.setFont(new Font("Arial", Font.BOLD, 25));
        Account_button.setForeground(Color.white);
        Account_button.setBorderPainted(false);
        Account_button.setFocusPainted(false);
        Account_button.setBackground(new Color(102, 102, 255));
        Account_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon Account_label_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/icon/icons8_registration_50px.png"));
        Account_button.setIcon(Account_label_icon);

        Appointment_button.setBounds(0, 200, 250, 100);
        Appointment_button.setText("Appointment");
        Appointment_button.setFont(new Font("Arial", Font.BOLD, 25));
        Appointment_button.setForeground(Color.white);
        Appointment_button.setBorderPainted(false);
        Appointment_button.setFocusPainted(false);
        Appointment_button.setBackground(new Color(102, 102, 255));
        Appointment_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon Appointment_label_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/icon/icons8_calendar_plus_50px.png"));
        Appointment_button.setIcon(Appointment_label_icon);

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

        Account_button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                Account_button.setBackground(new Color(51, 51, 255));
            }

            public void mouseExited(MouseEvent evt) {
                Account_button.setBackground(new Color(102, 102, 255));
            }
        });

        Account_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    account_action();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Menu_panel.add(Account_button);
        Menu_panel.add(Appointment_button);
        Menu_panel.add(Medicine_button);
        Menu_panel.add(Home_button);

        Name_label.setText(name);
        //User_id_label.setText(user_id);
        Appointment_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        Account_apply_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        Account_delete_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        Medicine_stock_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        Medicine_record_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        Medicine_request_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        Formal_appointment_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        JFormattedTextField quantity = ((JSpinner.NumberEditor) Request_quantity_spinner.getEditor()).getTextField();
        ((NumberFormatter) quantity.getFormatter()).setAllowsInvalid(false);

        gson = new GsonBuilder().create();
        reader = Files.newBufferedReader(Paths.get("src//data//account.json"));
        java.lang.reflect.Type patient_listType = new TypeToken<ArrayList<Account>>() {
        }.getType();
        ArrayList<Account> patients = gson.fromJson(reader, patient_listType);

        for (Account p : patients) {
            if (p.getLetter().equals("P")) {
                Patient_list_combobox.addItem(p.getGiven_name() + " " + p.getSurname() + "-" + p.getUnique_id_no());
            }
        }

        reader = Files.newBufferedReader(Paths.get("src//data//medicine_stock.json"));
        java.lang.reflect.Type medicine_stock_listType = new TypeToken<ArrayList<MedicineStock>>() {
        }.getType();
        ArrayList<MedicineStock> medicines_stock = gson.fromJson(reader, medicine_stock_listType);

        for (MedicineStock m : medicines_stock) {
            if (m.getQuantity() > 0) {
                Medicine_list_combobox.addItem(m.getMedicine_name());
            }
            New_medicine_list_combobox.addItem(m.getMedicine_name());
        }

        reader = Files.newBufferedReader(Paths.get("src//data//medicine_request.json"));
        java.lang.reflect.Type medicine_request_listType = new TypeToken<ArrayList<MedicineRequest>>() {
        }.getType();
        ArrayList<MedicineRequest> medicines_request = gson.fromJson(reader, medicine_request_listType);

        for (MedicineRequest m : medicines_request) {
            New_medicine_list_combobox.addItem(m.getMedicine_name() + " - new");
        }

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
        jPanel1 = new javax.swing.JPanel();
        Main_panel = new javax.swing.JPanel();
        Home_account_button = new javax.swing.JButton();
        Home_appointment_button = new javax.swing.JButton();
        Home_medicine_button = new javax.swing.JButton();
        Account_panel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Account_apply_table = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Account_delete_table = new javax.swing.JTable();
        Active_all_button = new javax.swing.JButton();
        Remove_active_button = new javax.swing.JButton();
        Active_button = new javax.swing.JButton();
        Remove_button = new javax.swing.JButton();
        Appointment_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Appointment_table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Appointment_userid_label = new javax.swing.JLabel();
        Doctor_combobox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        Create_appointment_button = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        Formal_appointment_table = new javax.swing.JTable();
        Date_combobox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        Appointment_no_label = new javax.swing.JLabel();
        Medicine_panel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Medicine_stock_table = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Medicine_record_table = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        Patient_list_combobox = new javax.swing.JComboBox<>();
        Medicine_list_combobox = new javax.swing.JComboBox<>();
        Request_quantity_spinner = new javax.swing.JSpinner();
        Give_patients_button = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        Medicine_request_table = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        New_medicine_list_combobox = new javax.swing.JComboBox<>();
        Order_quantity_spinner = new javax.swing.JSpinner();
        Order_medicine_button = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Patient Management System");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(100, 100));
        setMinimumSize(new java.awt.Dimension(1500, 1000));
        setSize(new java.awt.Dimension(1772, 1003));
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
            .addGap(0, 1077, Short.MAX_VALUE)
        );

        getContentPane().add(Menu_panel);

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
        jLabel1.setText("Patient Management System (Secretary)");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout Navigation_panelLayout = new javax.swing.GroupLayout(Navigation_panel);
        Navigation_panel.setLayout(Navigation_panelLayout);
        Navigation_panelLayout.setHorizontalGroup(
            Navigation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Navigation_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Menu_button)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 894, Short.MAX_VALUE)
                .addComponent(User_id_label, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Navigation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(User_id_label, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(Navigation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Name_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Logout_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Menu_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel4.add(Navigation_panel, java.awt.BorderLayout.PAGE_START);
        Navigation_panel.getAccessibleContext().setAccessibleName("");

        jPanel1.setLayout(new java.awt.CardLayout());

        Main_panel.setBackground(new java.awt.Color(255, 255, 255));
        Main_panel.setMinimumSize(new java.awt.Dimension(1500, 900));
        Main_panel.setPreferredSize(new java.awt.Dimension(1945, 990));

        Home_account_button.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        Home_account_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_registration_90px.png"))); // NOI18N
        Home_account_button.setText("Accout");
        Home_account_button.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        Home_account_button.setContentAreaFilled(false);
        Home_account_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home_account_button.setFocusPainted(false);
        Home_account_button.setFocusable(false);
        Home_account_button.setPreferredSize(new java.awt.Dimension(300, 300));
        Home_account_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Home_account_buttonActionPerformed(evt);
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
                .addGap(130, 130, 130)
                .addComponent(Home_account_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(302, 302, 302)
                .addComponent(Home_appointment_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(299, 299, 299)
                .addComponent(Home_medicine_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(115, 115, 115))
        );
        Main_panelLayout.setVerticalGroup(
            Main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_panelLayout.createSequentialGroup()
                .addGap(319, 319, 319)
                .addGroup(Main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Home_medicine_button, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Home_appointment_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Home_account_button, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(398, Short.MAX_VALUE))
        );

        jPanel1.add(Main_panel, "card2");

        Account_panel.setBackground(new java.awt.Color(255, 255, 255));

        Account_apply_table.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Account_apply_table.setForeground(new java.awt.Color(102, 102, 255));
        Account_apply_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Unique ID No", "Given Name", "Surname", "Sex", "Age", "User ID", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Account_apply_table.setRowHeight(25);
        Account_apply_table.setSelectionBackground(new java.awt.Color(102, 102, 255));
        jScrollPane2.setViewportView(Account_apply_table);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setText("Patient Accounts");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setText("Accounts Removal Request");
        jLabel9.setPreferredSize(new java.awt.Dimension(196, 28));

        Account_delete_table.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Account_delete_table.setForeground(new java.awt.Color(102, 102, 255));
        Account_delete_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Unique ID No", "Given Name", "Surname", "Sex", "Age", "User ID", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Account_delete_table.setRowHeight(25);
        Account_delete_table.setSelectionBackground(new java.awt.Color(102, 102, 255));
        jScrollPane3.setViewportView(Account_delete_table);

        Active_all_button.setBackground(new java.awt.Color(102, 102, 255));
        Active_all_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Active_all_button.setForeground(new java.awt.Color(255, 255, 255));
        Active_all_button.setText("Approve All");
        Active_all_button.setToolTipText("");
        Active_all_button.setBorder(null);
        Active_all_button.setBorderPainted(false);
        Active_all_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Active_all_button.setFocusPainted(false);
        Active_all_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Active_all_buttonActionPerformed(evt);
            }
        });

        Remove_active_button.setBackground(new java.awt.Color(102, 102, 255));
        Remove_active_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Remove_active_button.setForeground(new java.awt.Color(255, 255, 255));
        Remove_active_button.setText("Remove");
        Remove_active_button.setBorderPainted(false);
        Remove_active_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Remove_active_button.setFocusPainted(false);
        Remove_active_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Remove_active_buttonActionPerformed(evt);
            }
        });

        Active_button.setBackground(new java.awt.Color(102, 102, 255));
        Active_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Active_button.setForeground(new java.awt.Color(255, 255, 255));
        Active_button.setText("Approve");
        Active_button.setBorderPainted(false);
        Active_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Active_button.setFocusable(false);
        Active_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Active_buttonActionPerformed(evt);
            }
        });

        Remove_button.setBackground(new java.awt.Color(102, 102, 255));
        Remove_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Remove_button.setForeground(new java.awt.Color(255, 255, 255));
        Remove_button.setText("Approve");
        Remove_button.setToolTipText("");
        Remove_button.setBorderPainted(false);
        Remove_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Remove_button.setFocusable(false);
        Remove_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Remove_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Account_panelLayout = new javax.swing.GroupLayout(Account_panel);
        Account_panel.setLayout(Account_panelLayout);
        Account_panelLayout.setHorizontalGroup(
            Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1945, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
            .addGroup(Account_panelLayout.createSequentialGroup()
                .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Account_panelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Remove_button, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Account_panelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Active_all_button, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(Active_button, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(Remove_active_button, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        Account_panelLayout.setVerticalGroup(
            Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Account_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Active_all_button, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Active_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Remove_active_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Account_panelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(Remove_button, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(Account_panel, "card3");

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
                "User ID", "Appointment No", "Doctor Name", "Potential Date From", "Potential Date From"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Appointment_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Appointment_table.setRequestFocusEnabled(false);
        Appointment_table.setRowHeight(25);
        Appointment_table.setSelectionBackground(new java.awt.Color(102, 102, 255));
        Appointment_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Appointment_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Appointment_table);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel2.setText("Appointment");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setLabelFor(Doctor_combobox);
        jLabel3.setText("Doctor");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("User ID");

        Appointment_userid_label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        Doctor_combobox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Doctor_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setText("Date");

        Create_appointment_button.setBackground(new java.awt.Color(102, 102, 255));
        Create_appointment_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Create_appointment_button.setForeground(new java.awt.Color(255, 255, 255));
        Create_appointment_button.setText("Create Appointment");
        Create_appointment_button.setBorderPainted(false);
        Create_appointment_button.setFocusable(false);
        Create_appointment_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Create_appointment_buttonActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_down_50px_1.png"))); // NOI18N

        Formal_appointment_table.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Formal_appointment_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Appointment No", "Doctor Name", "Date"
            }
        ));
        Formal_appointment_table.setRowHeight(25);
        jScrollPane7.setViewportView(Formal_appointment_table);

        Date_combobox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Date_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setText("No");

        Appointment_no_label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        javax.swing.GroupLayout Appointment_panelLayout = new javax.swing.GroupLayout(Appointment_panel);
        Appointment_panel.setLayout(Appointment_panelLayout);
        Appointment_panelLayout.setHorizontalGroup(
            Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Appointment_panelLayout.createSequentialGroup()
                .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Appointment_panelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Appointment_panelLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(Appointment_panelLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(Doctor_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Appointment_panelLayout.createSequentialGroup()
                                .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Appointment_userid_label, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                    .addComponent(Appointment_no_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1467, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(Appointment_panelLayout.createSequentialGroup()
                .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Appointment_panelLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(Create_appointment_button, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Appointment_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Date_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1467, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Appointment_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(775, 775, 775))
        );
        Appointment_panelLayout.setVerticalGroup(
            Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Appointment_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Appointment_panelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Appointment_no_label, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Appointment_userid_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(111, 111, 111)
                        .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Doctor_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)))
                .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Appointment_panelLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(Appointment_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Date_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(103, 103, 103)
                        .addComponent(Create_appointment_button, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Appointment_panelLayout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1.add(Appointment_panel, "card4");

        Medicine_panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 40)); // NOI18N
        jLabel10.setText("Medicine Using Record and Stock");

        Medicine_stock_table.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Medicine_stock_table.setForeground(new java.awt.Color(102, 102, 255));
        Medicine_stock_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine Name", "Stock Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Medicine_stock_table.setRowHeight(25);
        Medicine_stock_table.setSelectionBackground(new java.awt.Color(102, 102, 255));
        jScrollPane4.setViewportView(Medicine_stock_table);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Patient Name");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Medicine Name");

        Medicine_record_table.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Medicine_record_table.setForeground(new java.awt.Color(102, 102, 255));
        Medicine_record_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine Name", "Patient Name", "Unique ID No", "Doctor Name", "Quatity", "Record Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Medicine_record_table.setRowHeight(25);
        Medicine_record_table.setSelectionBackground(new java.awt.Color(102, 102, 255));
        jScrollPane5.setViewportView(Medicine_record_table);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Quantity");
        jLabel13.setToolTipText("");

        Patient_list_combobox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Patient_list_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        Medicine_list_combobox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Medicine_list_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        Request_quantity_spinner.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Request_quantity_spinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 30, 1));
        Request_quantity_spinner.setEditor(new javax.swing.JSpinner.NumberEditor(Request_quantity_spinner, ""));

        Give_patients_button.setBackground(new java.awt.Color(102, 102, 255));
        Give_patients_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Give_patients_button.setForeground(new java.awt.Color(255, 255, 255));
        Give_patients_button.setText("Give Patients");
        Give_patients_button.setBorderPainted(false);
        Give_patients_button.setFocusPainted(false);
        Give_patients_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Give_patients_buttonActionPerformed(evt);
            }
        });

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
        jScrollPane6.setViewportView(Medicine_request_table);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setText("New Medicine Request");
        jLabel14.setToolTipText("");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel16.setText("Medicine Name");
        jLabel16.setToolTipText("");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Quantity");

        New_medicine_list_combobox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        New_medicine_list_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        Order_quantity_spinner.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Order_quantity_spinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 500, 1));
        Order_quantity_spinner.setEditor(new javax.swing.JSpinner.NumberEditor(Order_quantity_spinner, ""));

        Order_medicine_button.setBackground(new java.awt.Color(102, 102, 255));
        Order_medicine_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Order_medicine_button.setForeground(new java.awt.Color(255, 255, 255));
        Order_medicine_button.setText("Order");
        Order_medicine_button.setBorderPainted(false);
        Order_medicine_button.setFocusable(false);
        Order_medicine_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Order_medicine_buttonActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Max. 500");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText("Max. 30");

        javax.swing.GroupLayout Medicine_panelLayout = new javax.swing.GroupLayout(Medicine_panel);
        Medicine_panel.setLayout(Medicine_panelLayout);
        Medicine_panelLayout.setHorizontalGroup(
            Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Medicine_panelLayout.createSequentialGroup()
                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Medicine_panelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(Medicine_panelLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel10))
                            .addGroup(Medicine_panelLayout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(Give_patients_button, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Medicine_panelLayout.createSequentialGroup()
                                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(Medicine_panelLayout.createSequentialGroup()
                                        .addComponent(Request_quantity_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel18))
                                    .addComponent(Patient_list_combobox, 0, 260, Short.MAX_VALUE)
                                    .addComponent(Medicine_list_combobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Medicine_panelLayout.createSequentialGroup()
                                        .addComponent(Order_quantity_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(New_medicine_list_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Order_medicine_button, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 217, Short.MAX_VALUE))
                    .addGroup(Medicine_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5)
                        .addGap(18, 18, 18)))
                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        Medicine_panelLayout.setVerticalGroup(
            Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Medicine_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Medicine_panelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Medicine_panelLayout.createSequentialGroup()
                                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Patient_list_combobox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Medicine_list_combobox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                    .addComponent(Request_quantity_spinner)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Give_patients_button, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Medicine_panelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(Order_medicine_button, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Medicine_panelLayout.createSequentialGroup()
                                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(New_medicine_list_combobox)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Order_quantity_spinner)
                                        .addComponent(jLabel15)))))
                        .addGap(18, 18, 18))
                    .addGroup(Medicine_panelLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(9, 9, 9)))
                .addGroup(Medicine_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Medicine_panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane4)))
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
                Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Logout_buttonActionPerformed

    private void Appointment_panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Appointment_panelMouseClicked
        // TODO add your handling code here:
        /*from.setUndecorated(false);
        from.setVisible(false);
        to.setUndecorated(false);
        to.setVisible(false);*/
    }//GEN-LAST:event_Appointment_panelMouseClicked

    private void Create_appointment_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Create_appointment_buttonActionPerformed
        // TODO add your handling code here:
        apply_model = (DefaultTableModel) Appointment_table.getModel();

        if (Doctor_combobox.getSelectedIndex() > -1 && !Doctor_combobox.getSelectedItem().toString().isEmpty() && !Appointment_userid_label.getText().isEmpty()) {

            try {
                AppointmentApproved approved_approvement = new AppointmentApproved(0, Appointment_userid_label.getText(), Doctor_combobox.getSelectedItem().toString(), new SimpleDateFormat("d/M/y").parse(Date_combobox.getSelectedItem().toString()), new Date(), false);
                new CreateAppointment().CreateFormalAppointment(approved_approvement);
                new CreateAppointment().DeleteOldAppointment(Integer.parseInt(Appointment_no_label.getText()));
                Doctor_combobox.removeAllItems();
                Date_combobox.removeAllItems();
                Appointment_no_label.setText("");

                appointment_action();
            } catch (IOException | ParseException | NoSuchAlgorithmException ex) {
                Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Create_appointment_buttonActionPerformed

    private void Remove_active_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Remove_active_buttonActionPerformed
        // TODO add your handling code here:
        if (Account_apply_table.getSelectedRowCount() == 1) {
            int choice = JOptionPane.showOptionDialog(null, "Are you sure to delete this account (" + Account_apply_table.getValueAt(Account_apply_table.getSelectedRow(), 5) + ") ?", "Logout", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (choice == JOptionPane.OK_OPTION) {
                try {
                    boolean status = new AccountApproval().changeStatus(Account_apply_table.getValueAt(Account_apply_table.getSelectedRow(), 5).toString(), "Delete");          
                    if (status == true) {
                        apply_model.removeRow(Account_apply_table.getSelectedRow());
                        JOptionPane.showMessageDialog(null, "The account is removed successfully!", "Success", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (IOException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (Account_apply_table.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Please select a account before delete!", "WARNING!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_Remove_active_buttonActionPerformed

    private void Active_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Active_buttonActionPerformed
        // TODO add your handling code here:
        try {
            if (Account_apply_table.getValueAt(Account_apply_table.getSelectedRow(), 6).toString().equals("Not Active")) {
                boolean status = new AccountApproval().changeStatus(Account_apply_table.getValueAt(Account_apply_table.getSelectedRow(), 5).toString(), "Active");
                if (status == true) {
                    apply_model.setValueAt("Active", Account_apply_table.getSelectedRow(), 6);
                    JOptionPane.showMessageDialog(null, "The account is active successfully!", "Success", JOptionPane.PLAIN_MESSAGE);
                }
            }
        } catch (IOException | NoSuchAlgorithmException ex) {
            Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Active_buttonActionPerformed

    private void Active_all_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Active_all_buttonActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showOptionDialog(null, "Are you sure to active all patient accounts?", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if (choice == JOptionPane.OK_OPTION) {
            int rowCount = Account_apply_table.getRowCount();
            try {
                boolean status = new AccountApproval().activeAll();
                
                if(status == true) {
                    for (int i = 0; i < rowCount; i++) {
                        if (Account_apply_table.getValueAt(i, 6).toString().equals("Not Active")) {
                            apply_model.setValueAt("Active", i, 6);
                        }
                    }  
                }
            } catch (IOException | NoSuchAlgorithmException ex) {
                Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Active_all_buttonActionPerformed

    private void Remove_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Remove_buttonActionPerformed
        // TODO add your handling code here:
        if (Account_delete_table.getSelectedRowCount() == 1) {
            int choice = JOptionPane.showOptionDialog(null, "Are you sure to delete this account (" + Account_delete_table.getValueAt(Account_delete_table.getSelectedRow(), 5) + ") ?", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (choice == JOptionPane.OK_OPTION) {
                try {
                    boolean status = new AccountApproval().changeStatus(Account_delete_table.getValueAt(Account_delete_table.getSelectedRow(), 5).toString(), "Delete");
                    if (status == true) {
                        delete_model.removeRow(Account_delete_table.getSelectedRow());
                        JOptionPane.showMessageDialog(null, "The account is removed successfully!", "Success", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (IOException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (Account_delete_table.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Please select a account before delete!", "WARNING!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_Remove_buttonActionPerformed

    private void Give_patients_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Give_patients_buttonActionPerformed
        // TODO add your handling code here:
        int qty = (Integer) Request_quantity_spinner.getValue();

        if (Patient_list_combobox.getSelectedIndex() != 0 && Medicine_list_combobox.getSelectedIndex() != 0 && qty > 0 && qty <= 30) {
            int choice = JOptionPane.showOptionDialog(null, "Are you sure to give medicine to the patient?", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (choice == JOptionPane.OK_OPTION) {

                String[] patient_detail = Patient_list_combobox.getSelectedItem().toString().split("-");
                String patient_name = patient_detail[0];
                String unique_id_no = patient_detail[1];
                String medicine_name = Medicine_list_combobox.getSelectedItem().toString();

                Broker broker = new Broker();
                Stock stock = new Stock(medicine_name, qty);
                Order give_patient = new GivePatient(stock);

                try {
                    reader = Files.newBufferedReader(Paths.get("src//data//medicine_record.json"));
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
                }
                java.lang.reflect.Type record_listType = new TypeToken<ArrayList<MedicineRecord>>() {
                }.getType();
                ArrayList<MedicineRecord> records = gson.fromJson(reader, record_listType);

                records.add(new MedicineRecord(medicine_name, patient_name, unique_id_no, "By Secretary", qty, new Date()));
                record_model.addRow(new Object[]{medicine_name, patient_name, unique_id_no, "By Secretary", qty, sdf.format(new Date())});

                try ( Writer record_writer = new FileWriter("src//data//medicine_record.json")) {
                    gson.toJson(records, record_writer);
                } catch (Exception e) {
                    System.out.println("error meassage!");
                }
                //GivePatient give_patient = new GivePatient(stock);

                broker.takeOrder(give_patient);
                broker.placeOrders();
                Request_quantity_spinner.setValue(0);

                try {
                    medicine_action();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
                }

                Patient_list_combobox.setSelectedIndex(0);
                Medicine_list_combobox.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_Give_patients_buttonActionPerformed

    private void Order_medicine_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Order_medicine_buttonActionPerformed
        // TODO add your handling code here:
        int qty = (Integer) Order_quantity_spinner.getValue();

        if (New_medicine_list_combobox.getSelectedIndex() != 0 && qty > 0 && qty <= 500) {
            int choice = JOptionPane.showOptionDialog(null, "Are you sure to order this medicine?", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (choice == JOptionPane.OK_OPTION) {

                String medicine_name = New_medicine_list_combobox.getSelectedItem().toString();
                String[] medicine_detail = medicine_name.split(" - new");
                String medicine = medicine_detail[0];

                Broker broker = new Broker();
                Stock stock = new Stock(medicine_name, qty);
                Order order_new = new OrderNew(stock);

                broker.takeOrder(order_new);
                broker.placeOrders();

                if (medicine_name.contains("new")) {
                    New_medicine_list_combobox.removeItem(medicine_name);
                    New_medicine_list_combobox.addItem(medicine);
                }

                Order_quantity_spinner.setValue(0);

                try {
                    medicine_action();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
                }

                New_medicine_list_combobox.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_Order_medicine_buttonActionPerformed

    private void Home_account_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Home_account_buttonActionPerformed
        try {
            // TODO add your handling code here:
            account_action();
        } catch (IOException ex) {
            Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Home_account_buttonActionPerformed

    private void Home_appointment_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Home_appointment_buttonActionPerformed
        try {
            // TODO add your handling code here:
            appointment_action();
        } catch (IOException ex) {
            Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Home_appointment_buttonActionPerformed

    private void Home_medicine_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Home_medicine_buttonActionPerformed
        try {
            // TODO add your handling code here:
            medicine_action();
        } catch (IOException ex) {
            Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Home_medicine_buttonActionPerformed

    private void Appointment_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Appointment_tableMouseClicked
        // TODO add your handling code here:
        Date startDate;
        Date endDate;
        GregorianCalendar range = new GregorianCalendar();
        Date_combobox.removeAllItems();
        Doctor_combobox.removeAllItems();

        try {
            reader = Files.newBufferedReader(Paths.get("src//data//account.json"));
        } catch (IOException ex) {
            Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.lang.reflect.Type doctor_listType = new TypeToken<ArrayList<Account>>() {
        }.getType();
        ArrayList<Account> doctors = gson.fromJson(reader, doctor_listType);

        for (Account d : doctors) {
            if (d.getLetter().equals("D")) {
                Doctor_combobox.addItem(d.getGiven_name() + " " + d.getSurname());
            }
        }
        Doctor_combobox.setSelectedIndex(0);

        try {
            startDate = new SimpleDateFormat("d/M/y").parse(Appointment_table.getValueAt(Appointment_table.getSelectedRow(), 3).toString());
            endDate = new SimpleDateFormat("d/M/y").parse(Appointment_table.getValueAt(Appointment_table.getSelectedRow(), 4).toString());

            range.setTime(startDate);

            while (!range.getTime().after(endDate)) {
                Date d = range.getTime();
                Date_combobox.addItem(sdf.format(d));
                range.add(Calendar.DATE, 1);
            }

        } catch (ParseException ex) {
            Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
        }

        Appointment_userid_label.setText(Appointment_table.getValueAt(Appointment_table.getSelectedRow(), 0).toString());
        Appointment_no_label.setText(Appointment_table.getValueAt(Appointment_table.getSelectedRow(), 1).toString());
    }//GEN-LAST:event_Appointment_tableMouseClicked

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
            java.util.logging.Logger.getLogger(MainScreen_Secretary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen_Secretary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen_Secretary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen_Secretary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainScreen_Secretary().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
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
        Account_panel.setVisible(false);
        Appointment_panel.setVisible(false);
        Medicine_panel.setVisible(false);
        Menu_panel.setVisible(false);
    }

    public void account_action() throws IOException {
        Main_panel.setVisible(false);
        Account_panel.setVisible(true);
        Appointment_panel.setVisible(false);
        Medicine_panel.setVisible(false);
        Menu_panel.setVisible(false);

        gson = new GsonBuilder().create();
        apply_model = (DefaultTableModel) Account_apply_table.getModel();
        int apply_row = Account_apply_table.getRowCount();

        for (int i = apply_row - 1; i >= 0; i--) {
            apply_model.removeRow(i);
        }

        delete_model = (DefaultTableModel) Account_delete_table.getModel();
        int delete_row = Account_delete_table.getRowCount();

        for (int i = delete_row - 1; i >= 0; i--) {
            delete_model.removeRow(i);
        }

        reader = Files.newBufferedReader(Paths.get("src//data//account.json"));
        java.lang.reflect.Type account_listType = new TypeToken<ArrayList<Account>>() {
        }.getType();
        ArrayList<Account> accounts = gson.fromJson(reader, account_listType);

        //String[] account_apply = {"Active", "Delete"};
        //JComboBox apply_c = new JComboBox(account_apply);
        //Account_apply_table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(apply_c));
        for (Account a : accounts) {
            if (a.getLetter().equals("P") && (a.getStatus().equals("Not Active") || a.getStatus().equals("Active"))) {
                apply_model.addRow(new Object[]{a.getUnique_id_no(), a.getGiven_name(), a.getStatus(), a.getSex(), a.getAge(), a.getUser_id(), a.getStatus()});
            }
            if (a.getLetter().equals("P") && (a.getStatus().equals("Waiting For Delete"))) {
                delete_model.addRow(new Object[]{a.getUnique_id_no(), a.getGiven_name(), a.getSurname(), a.getSex(), a.getAge(), a.getUser_id(), a.getStatus()});
            }
        }

        /*Account_apply_table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            //public void valueChanged(ListSelectionEvent event) {
                apply_c.addItemListener(new ItemListener() {
                    public void itemStateChanged(final ItemEvent event) {
                        if (event.getSource() == apply_c) {
                            if (event.getStateChange() == ItemEvent.SELECTED) {
                                String item = (String) apply_c.getSelectedItem();
                                try {
                                    boolean approval = new AccountApproval().approve(Account_apply_table.getValueAt(Account_apply_table.getSelectedRow(), 5).toString(), item);
                                    if (approval == true) {
                                        //accounts.remove(Account_apply_table.getSelectedRow());
                                        //apply_model.removeRow(2);
                                        //apply_model.fireTableDataChanged();
                                    }
                                } catch (IOException | NoSuchAlgorithmException ex) {
                                    Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        if (apply_c.getSelectedItem().toString() != Account_apply_table.getModel().getValueAt(Account_apply_table.getSelectedRow(), 6)) {
                            try {
                                new AccountApproval().approve(Account_apply_table.getValueAt(Account_apply_table.getSelectedRow(), 5).toString(), apply_c.getSelectedItem().toString());
                                //apply_model.removeRow(Account_apply_table.getSelectedRow());
                            } catch (IOException | NoSuchAlgorithmException ex) {
                                Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });
            //}
        });*/
        //String[] account_delete = {"Waiting For Delete","Delete"};
        //JComboBox delete_c = new JComboBox(account_delete);
        //Account_delete_table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(delete_c));
    }

    public void appointment_action() throws IOException {
        Main_panel.setVisible(false);
        Account_panel.setVisible(false);
        Appointment_panel.setVisible(true);
        Medicine_panel.setVisible(false);
        Menu_panel.setVisible(false);

        gson = new GsonBuilder().create();
        apply_model = (DefaultTableModel) Appointment_table.getModel();
        approved_model = (DefaultTableModel) Formal_appointment_table.getModel();
        int old_row = Appointment_table.getRowCount();

        for (int i = old_row - 1; i >= 0; i--) {
            apply_model.removeRow(i);
        }

        reader = Files.newBufferedReader(Paths.get("src//data//appointment.json"));
        java.lang.reflect.Type appointment_listType = new TypeToken<ArrayList<Appointment>>() {
        }.getType();
        ArrayList<Appointment> appointments = gson.fromJson(reader, appointment_listType);

        for (Appointment a : appointments) {
            if (a.isStatus() == false) {
                String date_from = sdf.format(a.getAppointment_date_from());
                String date_to = sdf.format(a.getAppointment_date_to());
                apply_model.addRow(new Object[]{a.getUser_id(), a.getAppointment_no(), a.getDoctor(), date_from, date_to});
            }
        }

        Doctor_combobox.removeAllItems();

        reader = Files.newBufferedReader(Paths.get("src//data//account.json"));
        java.lang.reflect.Type doctor_listType = new TypeToken<ArrayList<Account>>() {
        }.getType();
        ArrayList<Account> doctors = gson.fromJson(reader, doctor_listType);

        for (Account d : doctors) {
            if (d.getLetter().equals("D")) {
                Doctor_combobox.addItem(d.getGiven_name() + " " + d.getSurname());
            }
        }
        Doctor_combobox.setSelectedIndex(-1);

        int new_row = Formal_appointment_table.getRowCount();

        for (int i = new_row - 1; i >= 0; i--) {
            approved_model.removeRow(i);
        }

        reader = Files.newBufferedReader(Paths.get("src//data//appointment_approve.json"));
        java.lang.reflect.Type approved_listType = new TypeToken<ArrayList<AppointmentApproved>>() {
        }.getType();
        ArrayList<AppointmentApproved> approved_appointments = gson.fromJson(reader, approved_listType);

        for (AppointmentApproved ap : approved_appointments) {
            if (ap.isStatus() == false) {
                approved_model.addRow(new Object[]{ap.getUser_id(), ap.getAppointment_no(), ap.getDoctor(), ap.getDate()});
            }
        }

    }

    public void medicine_action() throws IOException {
        Main_panel.setVisible(false);
        Account_panel.setVisible(false);
        Appointment_panel.setVisible(false);
        Medicine_panel.setVisible(true);
        Menu_panel.setVisible(false);

        gson = new GsonBuilder().create();
        stock_model = (DefaultTableModel) Medicine_stock_table.getModel();

        reader = Files.newBufferedReader(Paths.get("src//data//medicine_stock.json"));
        java.lang.reflect.Type medicine_stock_listType = new TypeToken<ArrayList<MedicineStock>>() {
        }.getType();
        ArrayList<MedicineStock> medicines_stock = gson.fromJson(reader, medicine_stock_listType);

        int stock_row = Medicine_stock_table.getRowCount();

        for (int i = stock_row - 1; i >= 0; i--) {
            stock_model.removeRow(i);
        }

        for (MedicineStock m : medicines_stock) {
            stock_model.addRow(new Object[]{m.getMedicine_name(), m.getQuantity()});
        }

        record_model = (DefaultTableModel) Medicine_record_table.getModel();
        reader = Files.newBufferedReader(Paths.get("src//data//medicine_record.json"));
        java.lang.reflect.Type medicine_record_listType = new TypeToken<ArrayList<MedicineRecord>>() {
        }.getType();
        ArrayList<MedicineRecord> medicines_record = gson.fromJson(reader, medicine_record_listType);

        int record_row = Medicine_record_table.getRowCount();

        for (int i = record_row - 1; i >= 0; i--) {
            record_model.removeRow(i);
        }

        for (MedicineRecord m : medicines_record) {
            record_model.addRow(new Object[]{m.getMedicine_name(), m.getPatient_name(), m.getUnique_id_no(), m.getDoctor_name(), m.getQuantity(), sdf.format(m.getRecord_date())});
        }

        request_model = (DefaultTableModel) Medicine_request_table.getModel();
        reader = Files.newBufferedReader(Paths.get("src//data//medicine_request.json"));
        java.lang.reflect.Type medicine_request_listType = new TypeToken<ArrayList<MedicineRequest>>() {
        }.getType();
        ArrayList<MedicineRequest> medicines_request = gson.fromJson(reader, medicine_request_listType);

        int request_row = Medicine_request_table.getRowCount();

        if (record_row > 0) {
            request_model.getDataVector().removeAllElements();
        }

        for (MedicineRequest m : medicines_request) {
            request_model.addRow(new Object[]{m.getDoctor_name(), m.getMedicine_name()});
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Account_apply_table;
    private javax.swing.JTable Account_delete_table;
    private javax.swing.JPanel Account_panel;
    private javax.swing.JButton Active_all_button;
    private javax.swing.JButton Active_button;
    private javax.swing.JLabel Appointment_no_label;
    private javax.swing.JPanel Appointment_panel;
    private javax.swing.JTable Appointment_table;
    private javax.swing.JLabel Appointment_userid_label;
    private javax.swing.JButton Create_appointment_button;
    private javax.swing.JComboBox<String> Date_combobox;
    private javax.swing.JComboBox<String> Doctor_combobox;
    private javax.swing.JTable Formal_appointment_table;
    private javax.swing.JButton Give_patients_button;
    private javax.swing.JButton Home_account_button;
    private javax.swing.JButton Home_appointment_button;
    private javax.swing.JButton Home_medicine_button;
    private javax.swing.JButton Logout_button;
    private javax.swing.JPanel Main_panel;
    private javax.swing.JComboBox<String> Medicine_list_combobox;
    private javax.swing.JPanel Medicine_panel;
    private javax.swing.JTable Medicine_record_table;
    private javax.swing.JTable Medicine_request_table;
    private javax.swing.JTable Medicine_stock_table;
    private javax.swing.JButton Menu_button;
    private javax.swing.JPanel Menu_panel;
    private javax.swing.JLabel Name_label;
    private javax.swing.JPanel Navigation_panel;
    private javax.swing.JComboBox<String> New_medicine_list_combobox;
    private javax.swing.JButton Order_medicine_button;
    private javax.swing.JSpinner Order_quantity_spinner;
    private javax.swing.JComboBox<String> Patient_list_combobox;
    private javax.swing.JButton Remove_active_button;
    private javax.swing.JButton Remove_button;
    private javax.swing.JSpinner Request_quantity_spinner;
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
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    // End of variables declaration//GEN-END:variables

}
