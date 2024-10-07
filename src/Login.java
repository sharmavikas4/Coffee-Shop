import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
    JButton b1;
    JPanel panel;
    JTextField field1;
    JPasswordField field2;
    JLabel label1;
    JLabel label2;
    JLabel label;
    JButton button1;
    JButton button2;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost/coffee_shop";
    String username = "root";
    String password = "1Vikas@234";
    Home h;
    JButton pass;
    JLabel image;
    ImageIcon show;
    ImageIcon hide;
    boolean select;
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == button1) {
            if (field1.getText().equals("") || field2.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields", "Message", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Class.forName(driver);
                    conn = DriverManager.getConnection(url, username, password);
                    System.out.println("conn=" + conn);
                } catch (Exception f) {
                    System.out.println(f.getMessage());
                }
                String query = "select * from user where username = " + "\"" + field1.getText() + "\" and password = \""
                        + field2.getText() + "\"";
                try {
                    // create a statement
                    stmt = conn.createStatement(); // create a statement object for sending SQL statements to database

                    rs = stmt.executeQuery(query); // Executes the given SQL statement, which returns a single ResultSet
                                                   // object.
                    // extract data from the ResultSet
                    if (rs.next()) {
                        String user = rs.getString(1);
                        String pa = rs.getString(2);
                        System.out.println(user + " " + pa);
                        if (user.equals(field1.getText())){
                            h = new Home(user,pa);
                            this.dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(this,"User does not exist!","Message",JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this,"User does not exist!","Message",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception f) {
                    System.out.println(f.getMessage());
                } finally {
                    // release database resources
                    try {
                        rs.close();
                        stmt.close();
                        conn.close();
                    } catch (SQLException f) {
                        f.printStackTrace();
                    }
                }
                System.out.println("Username : " + field1.getText() + " and password: " + field2.getText());
            }
        } else if (e.getSource() == button2) {
            if (field1.getText().equals("") || field2.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields", "Message", JOptionPane.ERROR_MESSAGE);
            }
            else{
                try {
                    Class.forName(driver);
                    conn = DriverManager.getConnection(url, username, password);
                    System.out.println("conn=" + conn);
                } catch (Exception f) {
                    System.out.println(f.getMessage());
                }
                String query = "select * from user where username = " + "\"" + field1.getText() + "\" and password = \""
                        + field2.getText() + "\"";
                try {
                    // create a statement
                    stmt = conn.createStatement(); // create a statement object for sending SQL statements to database
    
                    rs = stmt.executeQuery(query); // Executes the given SQL statement, which returns a single ResultSet
                                                   // object.
    
                    // extract data from the ResultSet
                    if (rs.next()) {
                        String user = rs.getString(1);
                        String pa = rs.getString(2);
                        JOptionPane.showMessageDialog(this,"The user already exist!","Message",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        query = "INSERT into user VALUES(\"" + field1.getText() + "\",\"" + field2.getText() + "\");";
                        stmt.executeUpdate(query);
                        h = new Home(field1.getText(),field2.getText());
                        this.dispose();
                    }
                } catch (Exception f) {
                    System.out.println(f.getMessage());
                } finally {
                    // release database resources
                    try {
                        rs.close();
                        stmt.close();
                        conn.close();
                    } catch (SQLException f) {
                        f.printStackTrace();
                    }
                }
            }
        }
        else if (e.getSource()==pass){
            select = !select;
            if (select){
                pass.setIcon(hide);
                field2.setEchoChar((char)0);
            }
            else {
                pass.setIcon(show);
                field2.setEchoChar('\u2022');
            }
        }
        // else if (e.getSource()==b1){
        //     JOptionPane.showMessageDialog(this, "Message","Hello",JOptionPane.INFORMATION_MESSAGE);
        // }
    }

    Login() {
        this.setLayout(null);
        this.setTitle("The Coffee Shop");
        ImageIcon i = new ImageIcon(getClass().getResource("logo.png"));
        this.setIconImage(i.getImage());
        this.getContentPane().setBackground(new Color(0xeeeeee));
        // this.setSize(500, 500);
        this.setSize(1700,850);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0xffffff));
        panel.setBounds(620, 230, 300, 230);
        field1 = new JTextField();
        field2 = new JPasswordField(8);
        label1 = new JLabel("Enter username");
        label2 = new JLabel("Enter password");
        show = new ImageIcon(getClass().getResource("s.png"));
        hide = new ImageIcon(getClass().getResource("h.png"));
        pass = new JButton();
        pass.setIcon(show);
        pass.addActionListener(this);
        pass.setBounds(210,110,50,30);
        pass.setBackground(new Color(0xffffff));
        pass.setBorder(null);
        pass.setFocusable(false);
        label = new JLabel("Login");
        button1 = new JButton("Log in");
        button2 = new JButton("Sign Up");
        button1.addActionListener(this);
        button2.addActionListener(this);
        label.setFont(new Font("Helvetica", Font.BOLD, 25));
        label1.setBounds(50, 50, 200, 30);
        field1.setBounds(50, 80, 200, 30);
        label2.setBounds(50, 110, 200, 30);
        field2.setBounds(50, 140, 200, 30);
        button1.setBounds(70, 180, 70, 30);
        button2.setBounds(150, 180, 80, 30);
        label.setBounds(110, 20, 90, 30);
        panel.add(pass);
        panel.add(label);
        panel.add(label1);
        panel.add(field1);
        panel.add(label2);
        panel.add(field2);
        panel.add(button1);
        panel.add(button2);
        this.add(panel);
        // b1=new JButton("hello");
        // b1.setBounds(100, 200, 300, 200);
        // b1.addActionListener(this);

        // this.add(b1);

        ImageIcon icon = new ImageIcon(getClass().getResource("coffee.png"));
        image = new JLabel();
        image.setIcon(icon);
        image.setBounds(-35,0,1600,850);
        this.add(image);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
