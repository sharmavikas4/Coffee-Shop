import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Orders extends JFrame implements ActionListener {
    String u;
    String p;
    JPanel navbar;
    JLabel title;
    JButton home;
    JButton about;
    JButton logout;
    JButton orders;
    JLabel image;
    JPanel bill;
    JLabel b;
    JLabel Americano;
    JLabel BlackCoffee;
    JLabel Cappuccino;
    JLabel Espresso;
    JLabel Latte;
    JLabel total;
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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logout) {
            this.dispose();
            Login l = new Login();
        } else if (e.getSource() == home) {
            this.dispose();
            Home h = new Home(u,p);
        } else if (e.getSource() == about) {
            this.dispose();
            About h = new About(u,p);
        }
        else if (e.getSource() == orders) {
            this.dispose();
            Orders h = new Orders(u,p);
        }
    }

    Orders(String s,String t){
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
        b.setText("My recent order");
        b.setBounds(60,20,280,50);
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
        bill.add(Americano);
        bill.add(BlackCoffee);
        bill.add(Cappuccino);
        bill.add(Espresso);
        bill.add(Latte);
        bill.add(total);
        this.add(bill);
        this.getContentPane().setBackground(new Color(0xeeeeee));
        navbar = new JPanel();
        navbar.setBackground(new Color(0xffffff));
        navbar.setBounds(27,20,1480,50);
        title = new JLabel();
        title.setText("THE COFFEE SHOP.");
        title.setFont(new Font("Helvetica", Font.BOLD, 28));
        title.setBounds(20, 10, 400,30);
        home = new JButton();
        home.setBackground(new Color(0xffffff));
        home.setText("Home");
        home.setFont(new Font("Helvetica", Font.BOLD, 23));
        home.setBounds(650,10, 100,30);
        home.setBorder(null);
        home.setFocusable(false);
        home.addActionListener(this);
        about = new JButton();
        about.setBackground(new Color(0xffffff));
        about.setText("About");
        about.setFont(new Font("Helvetica", Font.BOLD, 23));
        about.setBounds(800,10, 100,30);
        about.setBorder(null);
        about.setFocusable(false);
        about.addActionListener(this);
        orders = new JButton();
        orders.setBackground(new Color(0xffffff));
        orders.setText("Orders");
        orders.setFont(new Font("Helvetica", Font.BOLD, 23));
        orders.setBounds(950,10, 100,30);
        orders.setBorder(null);
        orders.setFocusable(false);
        orders.addActionListener(this);
        logout = new JButton();
        logout.setBackground(new Color(0xffffff));
        logout.setText("Logout");
        logout.setFont(new Font("Helvetica", Font.BOLD, 23));
        logout.setBounds(1360,10, 100,30);
        logout.setBorder(null);
        logout.setFocusable(false);
        logout.addActionListener(this);
        navbar.setLayout(null);
        navbar.add(title);
        navbar.add(home);
        navbar.add(about);
        navbar.add(logout);
        navbar.add(orders);
        this.add(navbar);
        ImageIcon icon = new ImageIcon(getClass().getResource("b.png"));
        image = new JLabel();
        image.setIcon(icon);
        image.setBounds(0,0,1600,850);
        this.add(image);
        this.setSize(1670,850);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
