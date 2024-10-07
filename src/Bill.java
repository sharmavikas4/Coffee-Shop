import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class Bill extends JFrame implements ActionListener {
    JLabel image;
    JPanel bill;
    JLabel b;
    JLabel Americano;
    JLabel BlackCoffee;
    JLabel Cappuccino;
    JLabel Espresso;
    JLabel Latte;
    JLabel total;
    String u;
    String p;
    JLabel message;
    JButton redirect;
    Connection conn = null;
    java.sql.Statement stmt = null;
    ResultSet rs = null;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost/coffee_shop";
    String username = "root";
    String password = "1Vikas@234";
    int am,bc,cap,esp,la;
    JButton submit;
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==submit){
            submit.setVisible(false);
            message.setVisible(true);
            redirect.setVisible(true);
        }
        else if (e.getSource()==redirect){

            this.dispose();
            Home h = new Home(u,p);
        }
    }
    Bill(String s,String t){
        u = s;
        p = t;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("conn=" + conn);
        } catch (Exception f) {
            System.out.println(f.getMessage());
        }
        String query = "select * from orders where username = " + "\"" + u + "\";";
        System.out.println(query);
        try {
            // create a statement
            stmt = conn.createStatement(); // create a statement object for sending SQL statements to database

            rs = stmt.executeQuery(query); // Executes the given SQL statement, which returns a single ResultSet
                                           // object.
            // extract data from the ResultSet
            while(rs.next()) {
                if (rs.getString(2)!=null){
                    am = Integer.parseInt(rs.getString(2));
                }
                else {
                    am = 0;
                }
                if (rs.getString(3)!=null){
                    bc = Integer.parseInt(rs.getString(3));
                }
                else {
                    bc = 0;
                }
                if (rs.getString(4)!=null){
                    cap = Integer.parseInt(rs.getString(4));
                }
                else {
                    cap = 0;
                }
                if (rs.getString(5)!=null){
                    esp = Integer.parseInt(rs.getString(5));
                }
                else {
                    esp = 0;
                }
                if (rs.getString(6)!=null){
                    la = Integer.parseInt(rs.getString(6));
                }
                else {
                    la = 0;
                }
            }
            System.out.println(am +  "," + bc + "," + cap + "," + esp + "," + la);
        } catch (Exception f) {
            System.out.println(f.getMessage());
            f.printStackTrace();
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
        this.setLayout(null);
        this.setTitle("The Coffee Shop");
        ImageIcon i = new ImageIcon(getClass().getResource("logo.png"));
        this.setIconImage(i.getImage());
        bill = new JPanel();
        bill.setLayout(null);
        bill.setBounds(900,150,400,550);
        bill.setBackground(new Color(0xffffff));
        b = new JLabel();
        b.setText("Bill");
        b.setBounds(170,20,100,30);
        b.setFont(new Font("Helvetica",Font.BOLD,35));
        bill.add(b);
        Americano = new JLabel("1. Americano x  " + am + " = Rs." + 200*am);
        Americano.setBounds(50,110,300,30);
        Americano.setFont(new Font("Helvetica",Font.ITALIC,20));
        BlackCoffee = new JLabel("2. BlackCoffee x " + bc + " = Rs." + 100*bc);
        BlackCoffee.setBounds(50,160,300,30);
        BlackCoffee.setFont(new Font("Helvetica",Font.ITALIC,20));
        Cappuccino = new JLabel("3. Cappuccino x " + cap + " = Rs." + 150*cap);
        Cappuccino.setBounds(50,210,300,30);
        Cappuccino.setFont(new Font("Helvetica",Font.ITALIC,20));
        Espresso = new JLabel("4. Espresso x " + esp + " = Rs." + 250*esp);
        Espresso.setBounds(50,260,300,30);
        Espresso.setFont(new Font("Helvetica",Font.ITALIC,20));
        Latte = new JLabel("5. Latte x " + la + " = Rs." + 180*la);
        Latte.setBounds(50,310,300,30);
        Latte.setFont(new Font("Helvetica",Font.ITALIC,20));
        int to = 200*am + 100*bc + 150*cap + 250*esp + 180*la;
        total = new JLabel("Total: Rs." + to);
        total.setBounds(120,410,300,30);
        total.setFont(new Font("Helvetica",Font.ITALIC,25));
        submit = new JButton("Submit");
        submit.setBounds(150,450,100,30);
        submit.setBackground(new Color(0xffffff));
        submit.setFocusable(false);
        submit.addActionListener(this);
        message = new JLabel("Thanks for ordering,Please visit again.");
        message.setBounds(50,450,340,30);
        message.setVisible(false);
        message.setFont(new Font("Helvetica",Font.BOLD,15));
        redirect = new JButton("Continue to Home..");
        redirect.setBounds(120,490,160,30);
        redirect.setBackground(new Color(0xffffff));
        redirect.setFocusable(false);
        redirect.addActionListener(this);
        redirect.setVisible(false);
        bill.add(redirect);
        bill.add(message);
        bill.add(submit);
        bill.add(Americano);
        bill.add(BlackCoffee);
        bill.add(Cappuccino);
        bill.add(Espresso);
        bill.add(Latte);
        bill.add(total);
        this.add(bill);
        ImageIcon icon = new ImageIcon(getClass().getResource("b.png"));
        image = new JLabel();
        image.setIcon(icon);
        image.setBounds(0,0,1600,850);
        this.add(image);
        this.setSize(1670,850);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
}
