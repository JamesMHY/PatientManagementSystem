/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system;

import patient.management.system.function.AdminRating;
import patient.management.system.function.AccountRequest;
import patient.management.system.function.GiveFeedback;
import patient.management.system.function.JTextFieldCharLimit;
import patient.management.system.function.AccountApproval;
import patient.management.system.function.Account;
import patient.management.system.function.NewAccountFactory;
import patient.management.system.function.NewAccount;
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
import java.io.Reader;
import java.nio.file.*;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author James Man menu
 */
public class MainScreen_Administrator extends javax.swing.JFrame {

    //JPanel Menu_panel = new JPanel();
    JButton Account_button = new JButton();
    JButton Rating_button = new JButton();
    JButton Home_button = new JButton();
    SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
    DefaultTableModel account_model;
    DefaultTableModel rating_model;
    Gson gson;
    Reader reader;
    String Type;

    public static String name;
    public static String user_id;
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form MainScreen
     */
    public MainScreen_Administrator() throws IOException {
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

        Rating_button.setBounds(0, 200, 250, 100);
        Rating_button.setText("Rating");
        Rating_button.setFont(new Font("Arial", Font.BOLD, 25));
        Rating_button.setForeground(Color.white);
        Rating_button.setBorderPainted(false);
        Rating_button.setFocusPainted(false);
        Rating_button.setBackground(new Color(102, 102, 255));
        Rating_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon Rating_label_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/icon/icons8_star_half_empty_50px.png"));
        Rating_button.setIcon(Rating_label_icon);

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
                    Logger.getLogger(MainScreen_Administrator.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                    Logger.getLogger(MainScreen_Administrator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Menu_panel.add(Account_button);
        Menu_panel.add(Rating_button);
        Menu_panel.add(Home_button);
        Name_label.setText(name);
        //User_id_label.setText(user_id);
        Account_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        Rating_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        User_id_text.setDocument(new JTextFieldCharLimit(16));
        Password_text.setDocument(new JTextFieldCharLimit(14));
        Age_text.setDocument(new JTextFieldCharLimit(2));
        
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
        Home_rating_button = new javax.swing.JButton();
        Account_panel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Sex_combobox = new javax.swing.JComboBox<>();
        Type_combobox = new javax.swing.JComboBox<>();
        User_id_text = new javax.swing.JTextField();
        GivenName_text = new javax.swing.JTextField();
        Surname_text = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        Age_text = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        Account_table = new javax.swing.JTable();
        Create_button = new javax.swing.JButton();
        Remove_account_button = new javax.swing.JButton();
        Password_text = new javax.swing.JPasswordField();
        Invalid_age_label = new javax.swing.JLabel();
        Invalid_name_label = new javax.swing.JLabel();
        Clear_button = new javax.swing.JButton();
        Reminder_label = new javax.swing.JLabel();
        Rating_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Rating_table = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        Doctor_combobox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Feedback_text = new javax.swing.JTextArea();
        Give_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Patient Management System");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(100, 100));
        setMaximumSize(new java.awt.Dimension(1500, 1000));
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
            .addGap(0, 1003, Short.MAX_VALUE)
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
        jLabel1.setText("Patient Management System (Administrator)");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 405, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(Navigation_panel, java.awt.BorderLayout.PAGE_START);
        Navigation_panel.getAccessibleContext().setAccessibleName("");

        jPanel1.setLayout(new java.awt.CardLayout());

        Main_panel.setBackground(new java.awt.Color(255, 255, 255));
        Main_panel.setMinimumSize(new java.awt.Dimension(1500, 900));
        Main_panel.setPreferredSize(new java.awt.Dimension(1950, 990));

        Home_account_button.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        Home_account_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_registration_90px.png"))); // NOI18N
        Home_account_button.setText("Accout");
        Home_account_button.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        Home_account_button.setContentAreaFilled(false);
        Home_account_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home_account_button.setFocusPainted(false);
        Home_account_button.setFocusable(false);
        Home_account_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Home_account_buttonActionPerformed(evt);
            }
        });

        Home_rating_button.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        Home_rating_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_star_half_empty_90px.png"))); // NOI18N
        Home_rating_button.setText("Rating");
        Home_rating_button.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        Home_rating_button.setContentAreaFilled(false);
        Home_rating_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home_rating_button.setFocusPainted(false);
        Home_rating_button.setFocusable(false);
        Home_rating_button.setMaximumSize(new java.awt.Dimension(300, 300));
        Home_rating_button.setMinimumSize(new java.awt.Dimension(300, 300));
        Home_rating_button.setPreferredSize(new java.awt.Dimension(300, 300));
        Home_rating_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Home_rating_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Main_panelLayout = new javax.swing.GroupLayout(Main_panel);
        Main_panel.setLayout(Main_panelLayout);
        Main_panelLayout.setHorizontalGroup(
            Main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_panelLayout.createSequentialGroup()
                .addGap(356, 356, 356)
                .addComponent(Home_account_button, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(204, 204, 204)
                .addComponent(Home_rating_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(346, 346, 346))
        );
        Main_panelLayout.setVerticalGroup(
            Main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_panelLayout.createSequentialGroup()
                .addGap(258, 258, 258)
                .addGroup(Main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Home_account_button, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Home_rating_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(385, Short.MAX_VALUE))
        );

        jPanel1.add(Main_panel, "card2");

        Account_panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setText("Create Account");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("User ID");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Password");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Given Name");
        jLabel11.setToolTipText("");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Surname");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Type");
        jLabel13.setToolTipText("");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Sex");

        Sex_combobox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Sex_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "M", "F" }));
        Sex_combobox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Type_combobox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Type_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Administrator", "Doctor", "Secretary" }));
        Type_combobox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        User_id_text.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        User_id_text.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        GivenName_text.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        GivenName_text.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        GivenName_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GivenName_textKeyReleased(evt);
            }
        });

        Surname_text.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Surname_text.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Surname_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Surname_textKeyReleased(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Age");
        jLabel15.setToolTipText("");

        Age_text.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Age_text.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Age_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Age_textKeyReleased(evt);
            }
        });

        Account_table.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Account_table.setForeground(new java.awt.Color(102, 102, 255));
        Account_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Unique ID No", "Role", "User ID", "Given Name", "Surname", "Sex", "Age"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Account_table.setRowHeight(25);
        Account_table.setSelectionBackground(new java.awt.Color(102, 102, 255));
        jScrollPane3.setViewportView(Account_table);

        Create_button.setBackground(new java.awt.Color(102, 102, 255));
        Create_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Create_button.setForeground(new java.awt.Color(255, 255, 255));
        Create_button.setText("Create");
        Create_button.setBorderPainted(false);
        Create_button.setFocusPainted(false);
        Create_button.setFocusable(false);
        Create_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Create_buttonActionPerformed(evt);
            }
        });

        Remove_account_button.setBackground(new java.awt.Color(102, 102, 255));
        Remove_account_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Remove_account_button.setForeground(new java.awt.Color(255, 255, 255));
        Remove_account_button.setText("Remove");
        Remove_account_button.setBorderPainted(false);
        Remove_account_button.setFocusPainted(false);
        Remove_account_button.setFocusable(false);
        Remove_account_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Remove_account_buttonActionPerformed(evt);
            }
        });

        Password_text.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Password_text.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Password_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Password_textKeyReleased(evt);
            }
        });

        Invalid_age_label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Invalid_age_label.setForeground(new java.awt.Color(255, 0, 0));

        Invalid_name_label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Invalid_name_label.setForeground(new java.awt.Color(255, 0, 0));

        Clear_button.setBackground(new java.awt.Color(255, 102, 102));
        Clear_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Clear_button.setForeground(new java.awt.Color(255, 255, 255));
        Clear_button.setText("Clear");
        Clear_button.setBorderPainted(false);
        Clear_button.setFocusPainted(false);
        Clear_button.setFocusable(false);
        Clear_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Clear_buttonActionPerformed(evt);
            }
        });

        Reminder_label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Reminder_label.setText("6-14 characters, including upper and lower letters and numbers");

        javax.swing.GroupLayout Account_panelLayout = new javax.swing.GroupLayout(Account_panel);
        Account_panel.setLayout(Account_panelLayout);
        Account_panelLayout.setHorizontalGroup(
            Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Account_panelLayout.createSequentialGroup()
                .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Account_panelLayout.createSequentialGroup()
                        .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Account_panelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(Account_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Account_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(41, 41, 41)
                        .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Invalid_name_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(User_id_text)
                            .addComponent(Type_combobox, 0, 257, Short.MAX_VALUE)
                            .addComponent(GivenName_text)
                            .addComponent(Surname_text)
                            .addComponent(Password_text)))
                    .addGroup(Account_panelLayout.createSequentialGroup()
                        .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Account_panelLayout.createSequentialGroup()
                                .addGap(188, 188, 188)
                                .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Age_text, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Sex_combobox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(Account_panelLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(Clear_button, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Invalid_age_label, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Create_button, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Account_panelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Account_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Reminder_label)))
                .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Account_panelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Account_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Remove_account_button, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        Account_panelLayout.setVerticalGroup(
            Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(Account_panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Account_panelLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(Remove_account_button, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Account_panelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Type_combobox)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(User_id_text))
                        .addGap(36, 36, 36)
                        .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(Password_text))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Reminder_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(GivenName_text)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Invalid_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(Surname_text))
                        .addGap(39, 39, 39)
                        .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Sex_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Age_text, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Invalid_age_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(Account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Create_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Clear_button, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                        .addContainerGap(240, Short.MAX_VALUE))
                    .addGroup(Account_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)
                        .addContainerGap())))
        );

        jPanel1.add(Account_panel, "card3");

        Rating_panel.setBackground(new java.awt.Color(255, 255, 255));
        Rating_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Rating_panelMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel2.setText("Rating");

        Rating_table.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Rating_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doctor Name", "Patient User ID", "Rating", "Feedback", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Rating_table.setRowHeight(25);
        jScrollPane1.setViewportView(Rating_table);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("Doctor Name");

        Doctor_combobox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Doctor_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("Feedback");

        Feedback_text.setColumns(20);
        Feedback_text.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Feedback_text.setRows(5);
        jScrollPane2.setViewportView(Feedback_text);

        Give_button.setBackground(new java.awt.Color(102, 102, 255));
        Give_button.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Give_button.setForeground(new java.awt.Color(255, 255, 255));
        Give_button.setText("Give FeedBack");
        Give_button.setBorderPainted(false);
        Give_button.setFocusable(false);
        Give_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Give_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Rating_panelLayout = new javax.swing.GroupLayout(Rating_panel);
        Rating_panel.setLayout(Rating_panelLayout);
        Rating_panelLayout.setHorizontalGroup(
            Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Rating_panelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Rating_panelLayout.createSequentialGroup()
                        .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                            .addComponent(Doctor_combobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Give_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        Rating_panelLayout.setVerticalGroup(
            Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Rating_panelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Rating_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(Rating_panelLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Doctor_combobox, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addGroup(Rating_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(Give_button, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel1.add(Rating_panel, "card4");

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
                Logger.getLogger(MainScreen_Administrator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Logout_buttonActionPerformed

    private void Home_account_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Home_account_buttonActionPerformed
        try {
            // TODO add your handling code here:
            account_action();
        } catch (IOException ex) {
            Logger.getLogger(MainScreen_Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Home_account_buttonActionPerformed

    private void Home_rating_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Home_rating_buttonActionPerformed
        try {
            // TODO add your handling code here:
            rating_action();
        } catch (IOException ex) {
            Logger.getLogger(MainScreen_Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Home_rating_buttonActionPerformed

    private void Rating_panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Rating_panelMouseClicked
        // TODO add your handling code here:
        /*from.setUndecorated(false);
        from.setVisible(false);
        to.setUndecorated(false);
        to.setVisible(false);*/
    }//GEN-LAST:event_Rating_panelMouseClicked

    private void Create_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Create_buttonActionPerformed
        // TODO add your handling code here:
        String userid = User_id_text.getText();
        String given_name = GivenName_text.getText();
        String surname = Surname_text.getText();
        String sex = Sex_combobox.getSelectedItem().toString();
        String type = Type_combobox.getSelectedItem().toString();
        String age = Age_text.getText();
        String password = new String(Password_text.getPassword());
        String address = "Fictitious Clinic, Diagon Alley, Drake Circus, Plymouth, PL4 8AA.";
        String letter = null;

        if (type.equals("Administrator")) {
            letter = "A";
        } else if (type.equals("Doctor")) {
            letter = "D";
        } else if (type.equals("Secretary")) {
            letter = "S";
        }

        if (!age.isEmpty() && !given_name.isEmpty() && !surname.isEmpty() && !userid.isEmpty() && !password.isEmpty() && !sex.isEmpty() && !type.isEmpty()) {
            if (Invalid_age_label.getText().isEmpty() == false || Invalid_name_label.getText().isEmpty() == false || Password_text.getBorder().getBorderInsets(this).top > 1) {
                JOptionPane.showMessageDialog(null, "There is an error in filling in the information!", "WARNING!", JOptionPane.WARNING_MESSAGE);
            } else {
                Account new_account = new Account(userid, password, surname, given_name, address, letter, Integer.parseInt(age), sex, "", "Active");
                NewAccountFactory newAccountFactory = new NewAccountFactory();
                
                NewAccount newAccount = newAccountFactory.getRole(letter);
                try {
                    boolean success = newAccount.register(new_account, new AccountRequest(userid, new Date()));
                    if (success) {
                        account_action();
                    } else {
                        JOptionPane.showMessageDialog(null, "Account has been registered!", "WARNING!", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (IOException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                ClearData();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please fill in all input boxes!", "WARNING!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_Create_buttonActionPerformed

    private void Age_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Age_textKeyReleased
        // TODO add your handling code here:
        int age = 0;
        if (!Age_text.getText().isEmpty()) {
            try {
                if (Integer.parseInt(Age_text.getText()) < 0) {
                    Invalid_age_label.setText("Invalid age");
                } else {
                    age = Integer.parseInt(Age_text.getText());
                    Invalid_age_label.setText("");
                }
            } catch (NumberFormatException e) {
                Invalid_age_label.setText("Invalid age");
            }
        }
    }//GEN-LAST:event_Age_textKeyReleased

    private void GivenName_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GivenName_textKeyReleased
        // TODO add your handling code here:
        if (GivenName_text.getText().matches(".*\\d+.*") || Surname_text.getText().matches(".*\\d+.*")) {
            Invalid_name_label.setText("Do not enter a number");
        } else {
            Invalid_name_label.setText("");
        }
    }//GEN-LAST:event_GivenName_textKeyReleased

    private void Surname_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Surname_textKeyReleased
        // TODO add your handling code here:
        if (Surname_text.getText().matches(".*\\d+.*") || GivenName_text.getText().matches(".*\\d+.*")) {
            Invalid_name_label.setText("Do not enter a number");
        } else {
            Invalid_name_label.setText("");
        }
    }//GEN-LAST:event_Surname_textKeyReleased

    private void Clear_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Clear_buttonActionPerformed
        // TODO add your handling code here:
        ClearData();
    }//GEN-LAST:event_Clear_buttonActionPerformed

    private void Password_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Password_textKeyReleased
        // TODO add your handling code here:
        String password = new String(Password_text.getPassword());

        if ((!checkString(password) || password.length() < 6) && !password.isEmpty()) {
            this.Reminder_label.setForeground(Color.red);
            this.Password_text.setBorder(new LineBorder(Color.red, 2));
        } else {
            this.Reminder_label.setForeground(Color.black);
            this.Password_text.setBorder(new LineBorder(Color.black, 1));
        }
    }//GEN-LAST:event_Password_textKeyReleased

    private void Remove_account_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Remove_account_buttonActionPerformed
        // TODO add your handling code here:
        if (Account_table.getSelectedRowCount() == 1) {
            int choice = JOptionPane.showOptionDialog(null, "Are you sure to delete this account (" + Account_table.getValueAt(Account_table.getSelectedRow(), 2) + ") ?", "Delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (choice == JOptionPane.OK_OPTION) {
                try {
                    boolean status = new AccountApproval().changeStatus(Account_table.getValueAt(Account_table.getSelectedRow(), 2).toString(), "Delete");
                    if (status == true) {
                        account_model.removeRow(Account_table.getSelectedRow());
                    }
                } catch (IOException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(MainScreen_Secretary.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (Account_table.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Please select a account before delete!", "WARNING!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_Remove_account_buttonActionPerformed

    private void Give_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Give_buttonActionPerformed
        // TODO add your handling code here:
        if (!Doctor_combobox.getSelectedItem().toString().isEmpty() && !Feedback_text.getText().isEmpty()) {
            AdminRating adminrating = new AdminRating(Doctor_combobox.getSelectedItem().toString(), new Date(), Feedback_text.getText());
            try {
                new GiveFeedback().AdminGiveFeedback(adminrating);
                Doctor_combobox.setSelectedIndex(-1);
                Feedback_text.setText("");
            } catch (NoSuchAlgorithmException | IOException ex) {
                Logger.getLogger(MainScreen_Administrator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Give_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(MainScreen_Administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen_Administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen_Administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen_Administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainScreen_Administrator().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen_Administrator.class.getName()).log(Level.SEVERE, null, ex);
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
        Rating_panel.setVisible(false);
        Menu_panel.setVisible(false);
    }

    public void account_action() throws IOException {
        Main_panel.setVisible(false);
        Account_panel.setVisible(true);
        Rating_panel.setVisible(false);
        Menu_panel.setVisible(false);

        gson = new GsonBuilder().create();
        reader = Files.newBufferedReader(Paths.get("src//data//account.json"));
        java.lang.reflect.Type accounts_listType = new TypeToken<ArrayList<Account>>() {
        }.getType();
        ArrayList<Account> accounts = gson.fromJson(reader, accounts_listType);
        account_model = (DefaultTableModel) Account_table.getModel();

        int row = Account_table.getRowCount();

        for (int i = row - 1; i >= 0; i--) {
            account_model.removeRow(i);
        }

        for (Account a : accounts) {
            if (a.getLetter().equals("D") || a.getLetter().equals("S")) {
                if (a.getLetter().equals("D")) {
                    Type = "Doctor";
                } else {
                    Type = "Secretary";
                }
                account_model.addRow(new Object[]{a.getUnique_id_no(), Type, a.getUser_id(), a.getGiven_name(), a.getSurname(), a.getSex(), a.getAge()});
            }
        }
    }

    public void rating_action() throws IOException {
        Main_panel.setVisible(false);
        Account_panel.setVisible(false);
        Rating_panel.setVisible(true);
        Menu_panel.setVisible(false);
        
        gson = new GsonBuilder().create();
        reader = Files.newBufferedReader(Paths.get("src//data//rating.json"));
        java.lang.reflect.Type ratings_listType = new TypeToken<ArrayList<Rating>>() {
        }.getType();
        ArrayList<Rating> ratings = gson.fromJson(reader, ratings_listType);
        rating_model = (DefaultTableModel) Rating_table.getModel();

        int row = Rating_table.getRowCount();

        for (int i = row - 1; i >= 0; i--) {
            rating_model.removeRow(i);
        }

        for (Rating r : ratings) {
            rating_model.addRow(new Object[]{r.getDoctor_name(), r.getUser_id(), r.getRating(), r.getFeedback(), sdf.format(r.getDate())});
        }
    }

    public void ClearData() {
        Age_text.setText("");
        GivenName_text.setText("");
        Surname_text.setText("");
        User_id_text.setText("");
        Password_text.setText("");
        Sex_combobox.setSelectedIndex(0);
        Type_combobox.setSelectedIndex(0);
        Invalid_name_label.setText("");
        Invalid_age_label.setText("");
        Reminder_label.setForeground(Color.black);
        Password_text.setBorder(new LineBorder(Color.black, 1));
    }

    private static boolean checkString(String str) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if (numberFlag && capitalFlag && lowerCaseFlag) {
                return true;
            }
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Account_panel;
    private javax.swing.JTable Account_table;
    private javax.swing.JTextField Age_text;
    private javax.swing.JButton Clear_button;
    private javax.swing.JButton Create_button;
    private javax.swing.JComboBox<String> Doctor_combobox;
    private javax.swing.JTextArea Feedback_text;
    private javax.swing.JButton Give_button;
    private javax.swing.JTextField GivenName_text;
    private javax.swing.JButton Home_account_button;
    private javax.swing.JButton Home_rating_button;
    private javax.swing.JLabel Invalid_age_label;
    private javax.swing.JLabel Invalid_name_label;
    private javax.swing.JButton Logout_button;
    private javax.swing.JPanel Main_panel;
    private javax.swing.JButton Menu_button;
    private javax.swing.JPanel Menu_panel;
    private javax.swing.JLabel Name_label;
    private javax.swing.JPanel Navigation_panel;
    private javax.swing.JPasswordField Password_text;
    private javax.swing.JPanel Rating_panel;
    private javax.swing.JTable Rating_table;
    private javax.swing.JLabel Reminder_label;
    private javax.swing.JButton Remove_account_button;
    private javax.swing.JComboBox<String> Sex_combobox;
    private javax.swing.JTextField Surname_text;
    private javax.swing.JComboBox<String> Type_combobox;
    private javax.swing.JLabel User_id_label;
    private javax.swing.JTextField User_id_text;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

}
