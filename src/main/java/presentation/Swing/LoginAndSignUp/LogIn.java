package presentation.Swing.LoginAndSignUp;

import domain.user.LoggedInUser;
import presentation.Swing.MainFrame;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LogIn extends javax.swing.JFrame {

    private SignUp signUpView;

    /**
     * Creates new form LogInFrame
     */
    public LogIn() {
        initComponents();
        signUpView = new SignUp();
    }

    public void setSignUpActionListener(ActionListener listener) {
        jButton2.addActionListener(listener);
    }

    public void setLoginActionListener(ActionListener listener) {
        jButton1.addActionListener(listener);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel1 = new javax.swing.JPanel();
        left = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        right = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setBackground(new java.awt.Color(0, 102, 102));
        setPreferredSize(new java.awt.Dimension(800, 550));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 800));
        jPanel1.setLayout(null);

        left.setBackground(new java.awt.Color(51, 153, 255));
        left.setPreferredSize(new java.awt.Dimension(400, 500));

        jLabel1.setIcon(new javax.swing.ImageIcon("src/main/java/presentation/Swing/LoginAndSignUp/smm.jpeg")); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout leftLayout = new javax.swing.GroupLayout(left);
        left.setLayout(leftLayout);
        leftLayout.setHorizontalGroup(
                leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(leftLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(50, Short.MAX_VALUE))
        );
        leftLayout.setVerticalGroup(
                leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftLayout.createSequentialGroup()
                                .addContainerGap(148, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(151, 151, 151))
        );

        jPanel1.add(left);
        left.setBounds(0, 0, 400, 540);

        right.setBackground(new java.awt.Color(255, 255, 255));
        right.setMinimumSize(new java.awt.Dimension(800, 500));

        jLabel2.setBackground(new java.awt.Color(51, 153, 255));
        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 255));
        jLabel2.setText("Login");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        jLabel5.setText("Password");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel4.setText("I don't have an account ");

        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setText("Sign Up");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        jLabel3.setText("Email");

        javax.swing.GroupLayout rightLayout = new javax.swing.GroupLayout(right);
        right.setLayout(rightLayout);
        rightLayout.setHorizontalGroup(
                rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rightLayout.createSequentialGroup()
                                .addGroup(rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(rightLayout.createSequentialGroup()
                                                .addGap(38, 38, 38)
                                                .addGroup(rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(rightLayout.createSequentialGroup()
                                                                .addComponent(jLabel4)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jButton2)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightLayout.createSequentialGroup()
                                                                .addComponent(jLabel6)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel3)
                                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel5)
                                                                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jButton1)))))
                                        .addGroup(rightLayout.createSequentialGroup()
                                                .addGap(128, 128, 128)
                                                .addComponent(jLabel2)))
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        rightLayout.setVerticalGroup(
                rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rightLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel2)
                                .addGroup(rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(rightLayout.createSequentialGroup()
                                                .addGap(108, 108, 108)
                                                .addComponent(jLabel6)
                                                .addGap(32, 32, 32))
                                        .addGroup(rightLayout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)))
                                .addComponent(jLabel5)
                                .addGap(34, 34, 34)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jButton1)
                                .addGap(80, 80, 80)
                                .addGroup(rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jButton2))
                                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel1.add(right);
        right.setBounds(400, 0, 410, 540);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        signUpView.setVisible(true);
        this.setVisible(false); // Hide the login view
        signUpView.setSignUpActionListener(e -> {
            // After signing up, show the login page again
            this.setVisible(true);
            signUpView.dispose(); // Close the sign-up view
        });
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String email = getEmailText();
        String password = getPasswordText();
        String username = ""; // Variable to store the retrieved username
        String userType = ""; // Assuming userType needs to be retrieved from some other component in your GUI

        String filePath = "database/Users.txt";
        File file = new File(filePath);

        LoggedInUser loggedInUser = null;

        // Check if the provided email and password match any entry in the file
        boolean found = false;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String storedEmail = "";
                String storedPassword = "";
                if (parts.length >= 3) {
                    storedEmail = parts[1];
                    storedPassword = parts[2];

                }

                if (email.equals(storedEmail) && password.equals(storedPassword)) {
                    found = true;
                    loggedInUser = new LoggedInUser(parts[0], parts[3]);
                    // If the email and password match, retrieve the associated username
                    username = parts[0]; // Assuming username is the first part of each line in Users.txt
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while logging in.");
            return;
        }

        if (found) {
            // Clear the content of userNameLoggedIn.txt before writing username
            try (PrintWriter writer = new PrintWriter(new FileWriter("database/userNameLoggedIn.txt", false))) {
                writer.print(""); // Truncate the file by writing an empty string
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error occurred while clearing username file.");
                return;
            }

            // Write username to external file
            try (PrintWriter writer = new PrintWriter(new FileWriter("database/userNameLoggedIn.txt", true))) {
                writer.println(username);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error occurred while writing username to file.");
                return;
            }

            JOptionPane.showMessageDialog(this, "Successfully logged in");

            // Create and display the main frame
            MainFrame mainFrame = new MainFrame(loggedInUser);
            mainFrame.showMainFrame();

            // Hide the login frame
            this.setVisible(false);
            return; // Exit the method to prevent further execution
        }

        // If the login failed, show error message
        JOptionPane.showMessageDialog(this, "Invalid email or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
    }


    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        String email = getEmailText();
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
            jTextField1.requestFocus();
            jTextField1.selectAll();
        }
    }

    public boolean isValidEmail(String email) {
        // Regular expression to validate email format
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public String getEmailText() {
        return jTextField1.getText();
    }

    public String getPasswordText() {
        return new String(jPasswordField1.getPassword());
    }



    // Variables declaration - do not modify                     
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1; //Login button
    private javax.swing.JButton jButton2; //Sign up button
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar1;
    public javax.swing.JTextField jTextField1; //Email Entry
    private javax.swing.JPasswordField jPasswordField1; // Password Entry
    private javax.swing.JPanel left;
    private javax.swing.JPanel right;
    // End of variables declaration                   
}

