package presentation.Swing.LoginAndSignUp;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class SignUp extends JFrame {

    private ButtonGroup buttonGroup1;
    private JButton jButton1; //Sign up button
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JRadioButton jRadioButton1; //Guest
    private JRadioButton jRadioButton2; //Parent
    private JRadioButton jRadioButton3; //Child
    private JRadioButton jRadioButton4; //Stranger
    private JScrollBar jScrollBar1;
    private JTextField jTextField1; //Email
    private JTextField jTextField3; //Username
    private JPanel left;
    private JPanel right;
    private JPasswordField jPasswordField; //Password
    private JCheckBox showPasswordCheckBox;

    public SignUp() {
        initComponents();
    }

    public void setBackToLoginActionListener(ActionListener listener) {
        jButton1.addActionListener(listener);
    }

    private void initComponents() {

        buttonGroup1 = new ButtonGroup();

        jScrollBar1 = new JScrollBar();
        jButton2 = new JButton();
        jLabel4 = new JLabel();
        jPanel1 = new JPanel();
        left = new JPanel();
        jLabel1 = new JLabel();
        right = new JPanel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel5 = new JLabel();
        jTextField1 = new JTextField();
        jButton1 = new JButton();
        jRadioButton1 = new JRadioButton();
        jRadioButton2 = new JRadioButton();
        jRadioButton3 = new JRadioButton();
        jRadioButton4 = new JRadioButton();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel9 = new JLabel();
        jTextField3 = new JTextField();
        jPasswordField = new JPasswordField();
        showPasswordCheckBox = new JCheckBox("Show Password");

        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setText("Sign Up");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel4.setText("I don't have an account ");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign Up");
        setBackground(new java.awt.Color(0, 102, 102));
        setPreferredSize(new java.awt.Dimension(800, 550));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 800));
        jPanel1.setLayout(null);

        left.setBackground(new java.awt.Color(51, 153, 255));
        left.setPreferredSize(new java.awt.Dimension(400, 500));

        jLabel1.setIcon(new ImageIcon("src/main/java/presentation/Swing/LoginAndSignUp/smm.jpeg")); // NOI18N
        jLabel1.setText("jLabel1");

        GroupLayout leftLayout = new GroupLayout(left);
        left.setLayout(leftLayout);
        leftLayout.setHorizontalGroup(
            leftLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(leftLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        leftLayout.setVerticalGroup(
            leftLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, leftLayout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151))
        );

        jPanel1.add(left);
        left.setBounds(0, 0, 400, 540);

        right.setBackground(new java.awt.Color(255, 255, 255));
        right.setMinimumSize(new java.awt.Dimension(400, 500));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 255));
        jLabel2.setText("SIGN UP");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        jLabel3.setText("Type of User");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        jLabel5.setText("Password");

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton1.setText("Sign Up");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jRadioButton1.setText("Guest");

        jRadioButton2.setText("Parent");
        jRadioButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setText("Child");

        jRadioButton4.setText("Stranger");

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        jLabel7.setText("Email");

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        jLabel9.setText("Username");

        jTextField3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        buttonGroup1.add(jRadioButton4);

        showPasswordCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    jPasswordField.setEchoChar((char) 0); // Show password
                } else {
                    jPasswordField.setEchoChar('*'); // Hide password
                }
            }
        });

        GroupLayout rightLayout = new GroupLayout(right);
        right.setLayout(rightLayout);
        rightLayout.setHorizontalGroup(
            rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, rightLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(136, 136, 136))
            .addGroup(rightLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(rightLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(rightLayout.createSequentialGroup()
                        .addComponent(jRadioButton2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton1)
                        .addContainerGap(86, Short.MAX_VALUE))
                    .addGroup(rightLayout.createSequentialGroup()
                        .addGroup(rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(rightLayout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(284, 284, 284))
                            .addGroup(rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5)
                                .addComponent(jPasswordField)
                                .addComponent(jButton1)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9)
                                .addComponent(jTextField3, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                                .addComponent(jTextField1)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(GroupLayout.Alignment.TRAILING, rightLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showPasswordCheckBox)
                .addContainerGap())
        );
        rightLayout.setVerticalGroup(
            rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(rightLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(rightLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton1))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGap(18, 18, 18)
                .addComponent(showPasswordCheckBox)
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addGap(67, 67, 67))
        );

        jPanel1.add(right);
        right.setBounds(400, 0, 400, 540);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 538, GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        String username = jTextField3.getText();
        String email = jTextField1.getText();
        String password = new String(jPasswordField.getPassword());

        String userType = "";
        if (jRadioButton1.isSelected()) {
            userType = "Guest";
        } else if (jRadioButton2.isSelected()) {
            userType = "Parent";
        } else if (jRadioButton3.isSelected()) {
            userType = "Child";
        } else if (jRadioButton4.isSelected()) {
            userType = "Stranger";
        }

        // Validate input fields
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || userType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields.");
            return; // Exit the method if any field is empty
        }

        // Validate email format
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
            jTextField1.requestFocus();
            jTextField1.selectAll();
            return; // Exit the method if email format is invalid
        }

        String filePath = "database/Users.txt";
        File file = new File(filePath);

        // Check if the email already exists
        if (emailExists(email, file)) {
            int choice = JOptionPane.showConfirmDialog(this, "Email already exists. Do you want to login instead?", "Email Exists", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                // Show the login window
                LogIn login = new LogIn();
                login.setLocationRelativeTo(null); // Center the login window
                login.setVisible(true);
                this.dispose(); // Close the sign-up window
                return; // Exit the method
            } else {
                jTextField1.requestFocus();
                jTextField1.selectAll();
                return; // Exit the method if the user chooses not to login
            }
        }

        // Check if the username already exists
        if (usernameExists(username, file)) {
            JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.");
            jTextField3.requestFocus();
            jTextField3.selectAll();
            return; // Exit the method
        }

        // Assign default location "Entrance"
        String defaultLocation = "Entrance";

        // Write user information to text file
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.println(username + "|" + email + "|" + password + "|" + userType + "|" + defaultLocation);
            JOptionPane.showMessageDialog(this, "Registration Successful!");
            this.setVisible(false);

            // Show the login window
            LogIn login = new LogIn();
            login.setLocationRelativeTo(null); // Center the login window
            login.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while registering. Please try again later.");
        }
    }



    private boolean emailExists(String email, File file) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length > 1 && parts[1].equals(email)) {
                    return true; // Email exists
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false; // Email does not exist
    }

    private boolean usernameExists(String username, File file) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length > 0 && parts[0].equals(username)) {
                    return true; // Username exists
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false; // Username does not exist
    }


    private void jTextField1ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }
    public void setSignUpActionListener(ActionListener listener) {
        jButton2.addActionListener(listener);
    }
    private void jRadioButton2ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }                                             

    private void jTextField3ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    public boolean isValidEmail(String email) {
        // Regular expression to validate email format
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    // Main method for testing
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUp().setVisible(true);
            }
        });
    }
}
