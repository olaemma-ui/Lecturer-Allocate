import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

abstract class Dec{
    ArrayList<String> obj = new ArrayList<>();
    HashMap<Integer, String> map = new HashMap<>();
    DefaultListModel<String> item = new DefaultListModel<>();

    String empty = "----------------";
    int valid = 0;
    String message = "";
    int cl = 0;
    int deptNum,  lectNum, group;
    String lectName, lectDept, lectGender, lectMobile, deptName, deptLvl, deptStd, facName, facMobile, facGen;
    String url = "jdbc:mysql://localhost/allocate";
    String uname = "root";
    String psw = "";

    JLabel[] outPut = {new JLabel("Name: "), new JLabel("Department: "), new JLabel("Mobile: ")};
    JLabel[] $_outPut_response = {new JLabel(), new JLabel(), new JLabel()};

    JLabel[] outPutRand = {new JLabel("Name: "), new JLabel("Department: "), new JLabel("Mobile: ")};
    JLabel[] $_outPut_response_rand = {new JLabel(), new JLabel(), new JLabel()};

    String[] btnName = {"Supervisors","Supervisors", "facilitators", "departments", "allocate", "course"};
    String[] allName = {"Manual","Random"};
    JTextField[] src = {new JTextField(), new JTextField(), new JTextField(), new JTextField()};

    CardLayout view = new CardLayout();
    JPanel preview = new JPanel(view);
    JPanel preview2 = new JPanel(view);
//    JPanel
    JPanel[] resultView = {
        new JPanel(null){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(new Color(90,70,255));
                g.fillRoundRect(0,210,800,60,0,0);
                g.setColor(Color.WHITE);
                g.setFont(new Font("MONOSPACE", Font.BOLD, 30));
                g.drawString("Allocated Students", 10, 250);
            }
        },
        new JPanel(null){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(new Color(90,70,255));
                g.fillRoundRect(0,210,800,60,0,0);
                g.setColor(Color.WHITE);
                g.setFont(new Font("MONOSPACE", Font.BOLD, 30));
                g.drawString("Allocated Students", 10, 250);
            }
        }
    };

    String[] col = {"S/N","Department", "Level", "Group N0"};
    Object[][] manual_data = new String[500][4];
    JTable manual = new JTable(manual_data, col);
    JScrollPane maual_scroll = new JScrollPane(manual);

    String[] colRand = {"S/N","Department", "Level", "Group N0"};
    Object[][] rand_data = new String[500][4];
    JTable rand = new JTable(rand_data, colRand);
    JScrollPane rand_scroll = new JScrollPane(rand);

    JPanel[] $_resultView_table_panel = {
            new JPanel(new CardLayout()),
            new JPanel(new CardLayout())
    };

    JComboBox selecOption = new JComboBox();

    JPanel[] tablePanel = {new JPanel(new CardLayout()),
            new JPanel(new CardLayout()),
            new JPanel(new CardLayout()),
            new JPanel(new CardLayout()),
            new JPanel(new CardLayout()),
    };

    String[] dashColumns = {"S/N","Name", "Department", "Mobile", "Status"};
    String[] deptColumns = {"S/N","Department", "Course", "Total Students","Total Group", "Allocated", "Not Allocated"};
    String[] facColumns = {"S/N"," Name ", "Department", "Mobile", "Status"};
    String[][] dashData = new String[500][6];
    String[][] deptData = new String[500][7];
    String[][] facData = new String[500][7];

    JTable facTable = new JTable(facData, facColumns);
    JTable dashTable = new JTable(dashData, dashColumns);
    JTable deptTable = new JTable(deptData, deptColumns);
    JTable lectTable = new JTable(dashData, dashColumns);

    JScrollPane facScroll = new JScrollPane(facTable);
    JScrollPane dashScroll = new JScrollPane(dashTable);
    JScrollPane deptScroll = new JScrollPane(deptTable);
    JScrollPane lectScroll = new JScrollPane(lectTable);


    /**
     * Alert Message Panel
     */
    JPanel[] alert = {
        new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(transpanrent[cl]);
                g.fillRoundRect(880, 0, 300, 50, 15,15);
                g.setFont(new Font("Monospace", Font.PLAIN, 18));
                g.setColor(Color.BLACK);
                g.drawString(message, 890, 30);
            }
        },
        new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(transpanrent[cl]);
                g.fillRoundRect(880, 0, 300, 50, 15,15);
                g.setFont(new Font("Monospace", Font.PLAIN, 18));
                g.setColor(Color.BLACK);
                g.drawString(message, 890, 30);
            }
        },
        new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(transpanrent[cl]);
                g.fillRoundRect(10, 0, 270, 50, 15,15);
                g.setFont(new Font("Monospace", Font.BOLD, 15));
                g.setColor(Color.BLACK);
                g.drawString(message, 10, 30);
            }
        },
            new JPanel(){
                @Override
                public void paint(Graphics g) {
                    super.paint(g);
                    g.setColor(transpanrent[cl]);
                    g.fillRoundRect(0, 0, 290, 50, 15,15);
                    g.setFont(new Font("Monospace", Font.BOLD, 15));
                    g.setColor(Color.BLACK);
                    g.drawString(message, 10, 30);
                }
            },

    };

    JFrame frame = new JFrame();
    MaskFormatter mf = new MaskFormatter("#### ### ####");
    JPanel[] bg = {new JPanel(),new JPanel(), new JPanel(), new JPanel(), new JPanel()};
    JPanel[] tableHeader = {new JPanel(),new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel()};
    JLabel[] tableHeaderText = {new JLabel("Supervisors"), new JLabel("Supervisors"), new JLabel("Departments"),
            new JLabel("Allocate Supervisors"), new JLabel("Allocate Supervisors"), new JLabel("Allocated " +
            "Facilitators"),
            new JLabel("Facilitators")};


    /**
     * Departments Components
     */
    JTextField[] input2 = {new JTextField(), new JTextField()};
    JLabel[] txt2 = {new JLabel("Departments : "), new JLabel("Course : "), new JLabel("Total Students :")};
    JLabel header2 = new JLabel("Add Department");
    JButton submit2 = new JButton("Add ");
    ButtonGroup lvl = new ButtonGroup();
    JRadioButton[] level = {
        new JRadioButton("EED 126"),
        new JRadioButton("EED 216"),
        new JRadioButton("EED 316"),
        new JRadioButton("EED 416")
    };

    /**
     * Body, Side Nav & Top Nav Components
     */
    JPanel panel = new JPanel();
    JPanel nav = new JPanel();
    JLabel title = new JLabel("EED Admin");
    JPanel sideBar = new JPanel();
    JButton[] btn = {new JButton("Dashboard "), new JButton("Supervisor"), new JButton("Facilitators"), new JButton("Department"), new JButton("Allocate"), new JButton("Course")};

    Color[] transpanrent = {new Color(10,100,255, 100), new Color(0,120,20, 100) , new Color(253, 139, 0, 186)};
    Color[] colorTop = { new Color(0,50, 150) ,new Color(0,120,20), new Color(153, 83, 0)};
    Color[] colors = {new Color(0,120,20), new Color(100, 20,0), new Color(0,50, 150)};

    Container container = new Container();
    CardLayout cardLayout = new CardLayout();
    CardLayout crd = new CardLayout();
    String[] s = {"0", "0", "0"};
    String[] display = {"Supervisors", "Facilitators", " Departments"};

    int j = 0;

    /**
     * Supervisor Panel
     */
    JPanel lect = new JPanel() {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.BLACK);
            g.drawRoundRect(950, 120, 300, 390, 10, 10);
            repaint();
        }
    };

    /**
     * Department Panel
     **/
    JPanel dept = new JPanel() {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.BLACK);
            g.drawRoundRect(950, 120, 300, 340, 10, 10);
            repaint();
        }
    };

    /**
     * Dashboard Panel
    **/
    JPanel dash = new JPanel() {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < 3; i++) {
                g.setColor(transpanrent[i]);
                g.fillRoundRect(60 + (410 * i), 10, 340, 180, 10, 10);

                g.setColor(colorTop[i]);
                g.fillRoundRect(60 + (410 * i), 10, 340, 50, 30, 0);
                if (i == 1) {
                    j -= 20;
                }
                g.setColor(Color.WHITE);
                g.setFont(new Font("Monospace", Font.BOLD, 60));
                g.drawString(s[i], 130 + (380 * i) + j, 120);

                if (i >= 1) {
                    j -= 20;
                    if (i == 2){
                        j-=20;
                    }
                }
                g.setColor(Color.WHITE);
                g.setFont(new Font("Monospace", Font.PLAIN, 23));
                g.drawString(display[i], 70 + (410 * i) + j, 45);
                j += 40;
            }
            j = 0;
            repaint();
        }
    };

    /**
     * Supervisors Components
     */
    JTextField[] input = {new JTextField(), new JTextField()};
    JLabel[] txt = {new JLabel("Name:"), new JLabel("Departments:"), new JLabel("Gender:"), new JLabel("Mobile N0:")};
    JButton submit = new JButton("Add ");
    JLabel header = new JLabel("Add Supervisor");
    JFormattedTextField fm = new JFormattedTextField(mf);
    JRadioButton[] gender = {new JRadioButton("Male"), new JRadioButton("Female")};
    ButtonGroup radio = new ButtonGroup();

    /**
     * Allocate Components
     * */
    JPanel allocate = new JPanel();
    JLabel header3 = new JLabel("Add Facilitator");

    JPanel[] allocatePanel = {new JPanel(null),
        new JPanel(null){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(Color.BLACK);
                g.drawRoundRect(20, 120, 220, 120, 10,10);
                String[] label = {"Group: "};
                g.setFont(new Font("monospace", Font.BOLD, 17));
                g.drawString(label[0],30, 145);

            }
        }
    };
    JPanel border = new JPanel() {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.BLACK);
            g.drawRoundRect(20, 50, 270, 290, 15,15);
        }
    };
    JButton submit3 = new JButton();
    JLabel[] selectText = {new JLabel("Supervisor:"), new JLabel("Department:"), new JLabel("Groups :")};
    JComboBox[] allocateSelect = {new JComboBox()};
    JTextField[] input3 = {new JTextField(), new JTextField(), new JTextField()};
    JButton[] toggle = {new JButton("Manual"), new JButton("Random")};
    JButton[] toggle2 = {new JButton("Manual"), new JButton("Random")};
    JLabel header4 = new JLabel("Supervisors");
    JButton submit5 = new JButton("Allocate");

    /**
     * Facilitators
     * */
    JPanel facilitators = new JPanel(true){
        @Override
        public void paint(Graphics graphics) {
            super.paint(graphics);
            graphics.setColor(Color.BLACK);
            graphics.drawRoundRect(950, 120, 300,290,15,15);
        }
    };
    JTextField[] input4 = {new JTextField(), new JTextField()};
    JTextField num = new JTextField();
    JLabel[] txt3 = {new JLabel("Name: "), new JLabel("Mobile: "), new JLabel("Gender: ")};
    JButton submit4 = new JButton("Add");
    JRadioButton[] gender2 = {new JRadioButton("Male"), new JRadioButton("Female")};
    ButtonGroup grp = new ButtonGroup();


    protected Dec() throws ParseException {}
}
