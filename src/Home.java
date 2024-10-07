import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.security.auth.login.LoginContext;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import com.mysql.cj.xdevapi.Statement;

public class Home extends JFrame implements ActionListener {
    String u;
    String p;
    JLabel label;
    JPanel panel;
    JLabel menu;
    JCheckBox c1;
    JCheckBox c2;
    JCheckBox c3;
    JCheckBox c4;
    JCheckBox c5;
    JLabel Americano;
    JLabel BlackCoffee;
    JLabel Cappuccino;
    JLabel Espresso;
    JLabel Latte;
    JPanel p1;
    JPanel p2;
    JPanel p3;
    JPanel p4;
    JPanel p5;
    JTextField t1;
    JTextField t2;
    JTextField t3;
    JTextField t4;
    JTextField t5;
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    JLabel l5;
    JButton button;
    JLabel order;
    JLabel order1;
    JLabel image;
    JPanel navbar;
    JLabel title;
    JButton home;
    JButton about;
    JButton logout;
    JButton orders;
    JButton resubmit;
    JButton bill;
    Connection conn = null;
    java.sql.Statement stmt = null;
    ResultSet rs = null;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost/coffee_shop";
    String username = "root";
    String password = "1Vikas@234";

    public void actionPerformed(ActionEvent e) {
        if (c1.isSelected() || c2.isSelected() || c3.isSelected() || c4.isSelected() || c5.isSelected()) {
            button.setVisible(true);
            panel.setBounds(890, 330, 400, 230);
        } else {
            button.setVisible(false);
            panel.setBounds(890, 330, 250, 190);
        }
        if (e.getSource() == c1) {
            if (c1.isSelected()) {
                p1.setVisible(true);
            } else {
                t1.setText("");
                p1.setVisible(false);
            }
        } else if (e.getSource() == c2) {
            if (c2.isSelected()) {
                p2.setVisible(true);
            } else {
                t2.setText("");
                p2.setVisible(false);
            }
        } else if (e.getSource() == c3) {
            if (c3.isSelected()) {
                p3.setVisible(true);
            } else {
                t3.setText("");
                p3.setVisible(false);
            }
        } else if (e.getSource() == c4) {
            if (c4.isSelected()) {
                p4.setVisible(true);
            } else {
                t4.setText("");
                p4.setVisible(false);
            }
        } else if (e.getSource() == c5) {
            if (c5.isSelected()) {
                p5.setVisible(true);
            } else {
                t5.setText("");
                p5.setVisible(false);
            }
        } else if (e.getSource() == button) {
            String s1 = t1.getText();
            String s2 = t2.getText();
            String s3 = t3.getText();
            String s4 = t4.getText();
            String s5 = t5.getText();
            int a[] = new int[5];
            String s[] = { "Americano", "BlackCoffee", "Cappuccino", "Espresso", "Latte" };
            if (!s1.equals("")) {
                try {
                    a[0] = Integer.parseInt(s1);
                } catch (Exception f) {
                    JOptionPane.showMessageDialog(this, "Please enter valid quantity", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (!s2.equals("")) {
                try {
                    a[1] = Integer.parseInt(s2);
                } catch (Exception f) {
                    JOptionPane.showMessageDialog(this, "Please enter valid quantity", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (!s3.equals("")) {
                try {
                    a[2] = Integer.parseInt(s3);
                } catch (Exception f) {
                    JOptionPane.showMessageDialog(this, "Please enter valid quantity", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (!s4.equals("")) {
                try {
                    a[3] = Integer.parseInt(s4);
                } catch (Exception f) {
                    JOptionPane.showMessageDialog(this, "Please enter valid quantity", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (!s5.equals("")) {
                try {
                    a[4] = Integer.parseInt(s5);
                } catch (Exception f) {
                    JOptionPane.showMessageDialog(this, "Please enter valid quantity", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            String o = "Your Order: ";
            String o1 = "";
            int i = 0;
            int c = 0;
            for (i = 0; i < 5; i++) {
                if (a[i] != 0) {
                    if (c < 2) {
                        o = o + s[i] + " x " + Integer.toString(a[i]);
                        c++;
                        if (i != 4 && c != 2) {
                            for (int j = i + 1; j < 5; j++) {
                                if (a[j] != 0) {
                                    o = o + ", ";
                                    break;
                                }
                            }
                        }
                    } else if (c >= 2) {
                        o1 = o1 + s[i] + " x " + Integer.toString(a[i]);
                        if (i != 4) {
                            for (int j = i + 1; j < 5; j++) {
                                if (a[j] != 0) {
                                    o1 = o1 + ", ";
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            order.setText(o);
            order1.setText(o1);
            button.setVisible(false);
            p1.setVisible(false);
            p2.setVisible(false);
            p3.setVisible(false);
            p4.setVisible(false);
            p5.setVisible(false);
            c1.setEnabled(false);
            c2.setEnabled(false);
            c3.setEnabled(false);
            c4.setEnabled(false);
            c5.setEnabled(false);
            panel.setBounds(890, 330, 400, 250);
            resubmit.setVisible(true);
            bill.setVisible(true);
            // order.setVisible(true);
            // order1.setVisible(true);
        } else if (e.getSource() == logout) {
            this.dispose();
            Login l = new Login();
        } else if (e.getSource() == home) {
            this.dispose();
            Home h = new Home(u, p);
        } else if (e.getSource() == about) {
            this.dispose();
            About h = new About(u, p);
        } else if (e.getSource() == orders) {

            this.dispose();
            Orders h = new Orders(u, p);
        } else if (e.getSource() == resubmit) {
            c1.setEnabled(true);
            c2.setEnabled(true);
            c3.setEnabled(true);
            c4.setEnabled(true);
            c5.setEnabled(true);
            c1.setSelected(false);
            c2.setSelected(false);
            c3.setSelected(false);
            c4.setSelected(false);
            c5.setSelected(false);
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            panel.setBounds(890, 330, 250, 190);
            resubmit.setVisible(false);
            bill.setVisible(false);
            button.setVisible(false);
        } else if (e.getSource() == bill) {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("conn=" + conn);
            } catch (Exception f) {
                System.out.println(f.getMessage());
            }
            String a;
            String b;
            String c;
            String es;
            String l;
            if (t1.getText().equals("")) {
                a = null;
            } else {
                a = "\"" + t1.getText() + "\"";
            }
            if (t2.getText().equals("")) {
                b = null;
            } else {
                b = "\"" + t2.getText() + "\"";
            }
            if (t3.getText().equals("")) {
                c = null;
            } else {
                c = "\"" + t3.getText() + "\"";
            }
            if (t4.getText().equals("")) {
                es = null;
            } else {
                es = "\"" + t4.getText() + "\"";
            }
            if (t5.getText().equals("")) {
                l = null;
            } else {
                l = "\"" + t5.getText() + "\"";
            }
            System.out.println(a + b + c + es + l);
            String query = "INSERT into orders VALUES(\"" + u + "\"," + a + "," + b + "," + c + "," + es + "," + l
                        + ");";
                try {
                    // create a statement
                    stmt = conn.createStatement();
                    stmt.executeUpdate(query);
                } catch (Exception f) {
                    System.out.println(f.getMessage());
                } finally {
                    // release database resources
                    try {
                        // rs.close();
                        stmt.close();
                        conn.close();
                    } catch (SQLException f) {
                        System.out.println(f.getMessage());
                    }
                }
                Bill bi = new Bill(u, p);
                this.dispose();
        }
    }

    Home(String s, String t) {
        u = s;
        p = t;
        this.setTitle("The Coffee Shop");
        ImageIcon i = new ImageIcon(getClass().getResource("logo.png"));
        this.setIconImage(i.getImage());
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0xeeeeee));
        navbar = new JPanel();
        navbar.setBackground(new Color(0xffffff));
        navbar.setBounds(27, 20, 1480, 50);
        title = new JLabel();
        title.setText("THE COFFEE SHOP.");
        title.setFont(new Font("Helvetica", Font.BOLD, 28));
        title.setBounds(20, 10, 400, 30);
        home = new JButton();
        home.setBackground(new Color(0xffffff));
        home.setText("Home");
        home.setFont(new Font("Helvetica", Font.BOLD, 23));
        home.setBounds(650, 10, 100, 30);
        home.setBorder(null);
        home.setFocusable(false);
        home.addActionListener(this);
        about = new JButton();
        about.setBackground(new Color(0xffffff));
        about.setText("About");
        about.setFont(new Font("Helvetica", Font.BOLD, 23));
        about.setBounds(800, 10, 100, 30);
        about.setBorder(null);
        about.setFocusable(false);
        about.addActionListener(this);
        orders = new JButton();
        orders.setBackground(new Color(0xffffff));
        orders.setText("Orders");
        orders.setFont(new Font("Helvetica", Font.BOLD, 23));
        orders.setBounds(950, 10, 100, 30);
        orders.setBorder(null);
        orders.setFocusable(false);
        orders.addActionListener(this);
        logout = new JButton();
        logout.setBackground(new Color(0xffffff));
        logout.setText("Logout");
        logout.setFont(new Font("Helvetica", Font.BOLD, 23));
        logout.setBounds(1360, 10, 100, 30);
        logout.setBorder(null);
        logout.setFocusable(false);
        logout.addActionListener(this);
        // JButton b1 = new JButton("Remove");
        // b1.setBounds(200,10,100,30);
        // navbar.add(b1);
        navbar.setLayout(null);
        navbar.add(title);
        navbar.add(home);
        navbar.add(about);
        navbar.add(orders);
        navbar.add(logout);
        panel = new JPanel();
        panel.setBounds(890, 330, 250, 190);
        panel.setLayout(null);
        panel.setBackground(new Color(0xffffff));
        // label = new JLabel("Welcome to this amazing coffee shop");
        // label.setBounds(20, 20, 360, 30);
        // label.setFont(new Font("Helvetica", Font.BOLD, 20));
        // menu = new JLabel("Menu");
        // menu.setFont(new Font("Helvetica", Font.ITALIC, 18));
        // menu.setBounds(170, 50, 60, 30);
        Americano = new JLabel("Americano Rs.200");
        BlackCoffee = new JLabel("BlackCoffee Rs.100");
        Cappuccino = new JLabel("Cappuccino Rs.150");
        Espresso = new JLabel("Espresso Rs.250");
        Latte = new JLabel("Latte Rs.180");
        Americano.setFont(new Font("Helvetica", Font.ITALIC, 18));
        BlackCoffee.setFont(new Font("Helvetica", Font.ITALIC, 18));
        Cappuccino.setFont(new Font("Helvetica", Font.ITALIC, 18));
        Espresso.setFont(new Font("Helvetica", Font.ITALIC, 18));
        Latte.setFont(new Font("Helvetica", Font.ITALIC, 18));
        Americano.setBounds(50, 20, 200, 30);
        BlackCoffee.setBounds(50, 50, 200, 30);
        Cappuccino.setBounds(50, 80, 200, 30);
        Espresso.setBounds(50, 110, 200, 30);
        Latte.setBounds(50, 140, 200, 30);
        c1 = new JCheckBox();
        c2 = new JCheckBox();
        c3 = new JCheckBox();
        c4 = new JCheckBox();
        c5 = new JCheckBox();
        c1.setFocusable(false);
        c2.setFocusable(false);
        c3.setFocusable(false);
        c4.setFocusable(false);
        c5.setFocusable(false);
        c1.addActionListener(this);
        c2.addActionListener(this);
        c3.addActionListener(this);
        c4.addActionListener(this);
        c5.addActionListener(this);
        c1.setBounds(20, 20, 20, 30);
        c1.setBackground(new Color(0xffffff));
        c2.setBounds(20, 50, 20, 30);
        c2.setBackground(new Color(0xffffff));
        c3.setBounds(20, 80, 20, 30);
        c3.setBackground(new Color(0xffffff));
        c4.setBounds(20, 110, 20, 30);
        c4.setBackground(new Color(0xffffff));
        c5.setBounds(20, 140, 20, 30);
        c5.setBackground(new Color(0xffffff));
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        l1 = new JLabel("Qty:");
        l2 = new JLabel("Qty:");
        l3 = new JLabel("Qty:");
        l4 = new JLabel("Qty:");
        l5 = new JLabel("Qty:");
        l1.setFont(new Font("Helvetica", Font.ITALIC, 12));
        l2.setFont(new Font("Helvetica", Font.ITALIC, 12));
        l3.setFont(new Font("Helvetica", Font.ITALIC, 12));
        l4.setFont(new Font("Helvetica", Font.ITALIC, 12));
        l5.setFont(new Font("Helvetica", Font.ITALIC, 12));
        p1.setLayout(null);
        p2.setLayout(null);
        p3.setLayout(null);
        p4.setLayout(null);
        p5.setLayout(null);
        p1.setBounds(220, 20, 100, 30);
        t1.setBounds(60, 0, 40, 30);
        l1.setBounds(30, 0, 30, 30);
        p1.setBackground(new Color(0xffffff));
        p1.add(t1);
        p1.add(l1);
        p1.setVisible(false);
        p2.setBounds(220, 50, 100, 30);
        t2.setBounds(60, 0, 40, 30);
        l2.setBounds(30, 0, 30, 30);
        p2.setBackground(new Color(0xffffff));
        p2.add(t2);
        p2.add(l2);
        p2.setVisible(false);
        p3.setBounds(220, 80, 100, 30);
        t3.setBounds(60, 0, 40, 30);
        l3.setBounds(30, 0, 30, 30);
        p3.setBackground(new Color(0xffffff));
        p3.add(t3);
        p3.add(l3);
        p3.setVisible(false);
        p4.setBounds(220, 110, 100, 30);
        t4.setBounds(60, 0, 40, 30);
        l4.setBounds(30, 0, 30, 30);
        p4.setBackground(new Color(0xffffff));
        p4.add(t4);
        p4.add(l4);
        p4.setVisible(false);
        p5.setBounds(220, 140, 100, 30);
        t5.setBounds(60, 0, 40, 30);
        l5.setBounds(30, 0, 30, 30);
        p5.setBackground(new Color(0xffffff));
        p5.add(t5);
        p5.add(l5);
        p5.setVisible(false);
        resubmit = new JButton("Re-Submit");
        resubmit.addActionListener(this);
        resubmit.setBounds(100, 180, 100, 30);
        resubmit.setVisible(false);
        resubmit.setFocusable(false);
        resubmit.setBackground(new Color(0xffffff));
        bill = new JButton("Bill");
        bill.addActionListener(this);
        bill.setBounds(210, 180, 100, 30);
        bill.setVisible(false);
        bill.setFocusable(false);
        bill.setBackground(new Color(0xffffff));
        button = new JButton("Submit");
        button.addActionListener(this);
        button.setBounds(150, 180, 100, 30);
        button.setVisible(false);
        order = new JLabel();
        order.setBounds(40, 170, 340, 30);
        order.setBackground(new Color(0xffffff));
        order.setFont(new Font("Helvetica", Font.BOLD, 15));
        order.setVisible(false);
        order1 = new JLabel();
        order1.setBounds(40, 200, 340, 30);
        order1.setBackground(new Color(0xffffff));
        order1.setFont(new Font("Helvetica", Font.BOLD, 15));
        order1.setVisible(false);
        panel.add(order);
        panel.add(order1);
        panel.add(button);
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);
        panel.add(p5);
        panel.add(c1);
        panel.add(c2);
        panel.add(c3);
        panel.add(c4);
        panel.add(c5);
        panel.add(Americano);
        panel.add(BlackCoffee);
        panel.add(Cappuccino);
        panel.add(Espresso);
        panel.add(Latte);
        panel.add(resubmit);
        panel.add(bill);
        // panel.add(label);
        // panel.add(menu);
        this.add(panel);
        this.add(navbar);
        ImageIcon icon = new ImageIcon(getClass().getResource("Home.png"));
        image = new JLabel();
        image.setIcon(icon);
        image.setBounds(0, 0, 1600, 850);
        this.add(image);
        this.setSize(1670, 850);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
