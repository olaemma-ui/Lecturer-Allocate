import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Control extends Design implements ActionListener, KeyListener {
    $Database db = new $Database();
    Action action = new Action();
    /**
     * Action Listener
     * */
    void listener(){
        for (int i = 0; i < btn.length; i++) {
            btn[i].addActionListener(this);
        }
        for (JTextField f: input) {
            f.addKeyListener(this);
        }
        for (JTextField t: input2) {
            t.addKeyListener(this);
        }
        for (int i = 0; i < toggle.length; i++) {
            toggle[i].addActionListener(this);
            toggle2[i].addActionListener(this);
        }
        allocateSelect[0].addActionListener(this);
        for (int i = 0; i < input3.length; i++) {
            input3[i].addKeyListener(this);
            if (i < 2)
                input4[i].addKeyListener(this);
        }
        fm.addKeyListener(this);
        submit.addActionListener(this);
        submit2.addActionListener(this);
        submit3.addActionListener(this);
        submit4.addActionListener(this);
        selecOption.addActionListener(this);
    }
    /**
     * Input Validation
     * */
    void validate(JButton vd){
        if (vd == submit){
            valid = 0;
            if (input[0].getText().isEmpty() || input[0].getText().equalsIgnoreCase(""))
                input[0].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            else {
                input[0].setBorder(BorderFactory.createLineBorder(colorTop[1], 3));
                valid++;
            }
            if (selecOption.getSelectedObjects()[0].toString().equalsIgnoreCase("") || selecOption.getSelectedObjects()[0].toString().equalsIgnoreCase(empty))
                selecOption.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            else{
                selecOption.setBorder(BorderFactory.createLineBorder(colorTop[1], 3));
                valid++;
            }
            if (!fm.getText().startsWith("0") || !fm.isEditValid()) {
                fm.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            }
            else {
                valid++;
                fm.setBorder(BorderFactory.createLineBorder(colorTop[1], 3));
                lectName = input[0].getText().trim();
                lectDept = selecOption.getSelectedObjects()[0].toString();
                lectMobile = fm.getText();
            }
            for (JRadioButton g: gender) {
                if (g.isSelected()){
                    valid++;
                    lectGender = g.getText().trim();
                }
            }
            if (valid == 4){
                alertMessage(1,1, "Lecturer Added");
                if (db.dbAction("INSERT INTO lecturers values('0', '"+lectName+"', '"+lectDept+"', '"+lectGender+"' ," +
                        " " +
                        "'"+lectMobile+ "' , '0', 'l', '"+action.lecturerID()+"')")) {
                    for (JTextField t: input) {
                        t.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
                        t.setText("");
                        selecOption.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
                        fm.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
                        fm.setText("");
                        radio.clearSelection();
                    }
                }
            }else
                alertMessage(1, 2, "All fields ar Required");
        }
        if (vd == submit2){
            valid = 0;
            for (int i = 0; i < input2.length; i++) {
                if (input2[i].getText().equalsIgnoreCase("")|| input2[i].getText().isEmpty()){
                    input2[i].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    alertMessage(0,2,"All fields are required");
                }else{
                    valid++;
                    input2[i].setBorder(BorderFactory.createLineBorder(colorTop[1], 3));
                    deptName = input2[0].getText().trim();
                    deptStd = input2[1].getText().trim();
                }
            }
            for (JRadioButton r: level) {
                if (r.isSelected()){
                    valid++;
                    deptLvl = r.getText().trim();
                }
            }
            if (valid == 3){
                int grp_no = 0;
                if (deptLvl.contains("216")){
                    grp_no = Integer.parseInt(deptStd)/10;
                }
                if (deptLvl.contains("416")){
                    grp_no = Integer.parseInt(deptStd)/5;
                }
                alertMessage(0,1,"Department Added");
                if (db.dbAction("INSERT INTO departments VALUES('0','"+deptName+"','"+deptLvl+"', '"+Integer.parseInt(deptStd)+"', '"+grp_no+ "', '"+0+ "', '"+grp_no+ "', '"+action.departmentID()+"', '"+0+"')")) {
                    lvl.clearSelection();
                    for (JTextField t: input2) {
                        t.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
                        t.setText("");
                    }
                }
            }
            select("Departments");
        }
        if (vd == submit3){
            valid = 0;
            for (int i = 0; i < allocateSelect.length; i++) {
                if (allocateSelect[i].getSelectedObjects().toString().equalsIgnoreCase(empty) || allocateSelect[i].getSelectedItem().toString().equalsIgnoreCase(empty)){
                    allocateSelect[i].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                }else{
                    allocateSelect[i].setBorder(BorderFactory.createLineBorder(colorTop[1], 3));
                    valid++;
                }
            }
            for (int i = 0; i < input3.length; i++) {
                if (input3[i].getText().length() <= 0 || Integer.parseInt(input3[i].getText()) <= 0){
                    input3[i].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                }else{
                    input3[i].setBorder(BorderFactory.createLineBorder(colorTop[1], 3));
                    valid++;
                }
            }

            if (valid == 3){
                lectNum = allocateSelect[0].getSelectedIndex();
                deptNum = Integer.parseInt(input3[0].getText());
                group = Integer.parseInt(input3[1].getText());
                check(lectNum, deptNum, group);

                for (int i = 0; i < input3.length; i++) {
                    input3[i].setText("");
                    allocateSelect[0].setSelectedItem(empty);
                }
            }
        }
        if (vd == submit4){
            valid = 0;
            for (int i = 0; i < input4.length; i++) {
                if (input4[i].getText().equalsIgnoreCase("") || input4[i].getText().isEmpty()) {
                    input4[i].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    System.out.println(i);
                }
                else {
                    input4[i].setBorder(BorderFactory.createLineBorder(colorTop[1], 3));
                    valid++;
                    facName = input4[0].getText().trim();
                    facMobile = input4[1].getText().trim();
                }
                if (gender2[i].isSelected()){
                    valid++;
                    facGen = gender[i].getText().trim();
                }
            }
            if (valid == 3) {
                if (db.dbAction("INSERT INTO lecturers values('0', '"+facName+"', 'MAPCED', '"+facGen+"' ," +
                        " '"+facMobile+ "' , '0', '"+1+"', '"+action.lecturerID()+"')")) {
                    alertMessage(3, 1, "Facilitator Added");
                    facSelect();
                    for (JTextField t: input4) {
                        t.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
                        t.setText("");
                        selecOption.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
                        grp.clearSelection();
                    }
                }

            }
            else
                alertMessage(3, 2, "All fields are Required");
        }

        for (JPanel a: alert) {
            a.repaint();
        }
    }

    void facSelect(){
        String select = "SELECT * FROM lecturers WHERE type = '1' ORDER BY ID DESC";
        db.dbConnect();
        try{
            ResultSet result = db.query.executeQuery(select);
            int i = 0, a = 0, b = 0;
            result = db.query.executeQuery(select);
            allocateSelect[0].removeAllItems();
            allocateSelect[0].addItem(empty);
            allocateSelect[0].repaint();
            map.clear();
            clearTable(dashData);
            while (result.next()) {
                allocateSelect[0].addItem(result.getString("name"));
                map.put(i+1, result.getString("lectId"));
                facData[i][0] = "" + (i+1) + "";
                facData[i][1] = result.getString("name");
                facData[i][2] = result.getString("departments");
                facData[i][3] = result.getString("mobile");
                if (result.getString("status").equalsIgnoreCase("1")) {
                    facData[i][4] = "Allocated";
                    a++;
                }
                if (result.getString("status").equalsIgnoreCase("0")) {
                    facData[i][4] = "Not Allocated";
                    b++;
                }
                i++;
            }
            s[0] = String.valueOf(i);
            s[1] = String.valueOf(a);
            s[2] = String.valueOf(b);

            if (i >= 499) {
                facData = new String[facData.length + 1][7];
            }
            result.close();

        }catch (Exception ex){/***/}
    }


    /**
     * Manual Allocate
     * */
    boolean check(int index, int Dnum, int grp){
        db.dbConnect();
        boolean bool = false, ck = false;
        String selectLect = "SELECT * FROM LECTURERS where lectId = '"+map.get(index)+"'";
        ResultSet result = null;
        int rowCount = 0;

//        HashMap<String, Integer> dept_manual_allocate = new HashMap<>();
        ArrayList<String> $_deptID = new ArrayList<>();
        ArrayList<Integer> $_deptID_Std = new ArrayList<>();
        ArrayList<Integer> $_deptID_Std_allocated = new ArrayList<>();
        try{
            result = db.query.executeQuery(selectLect);
            String selectDept= "SELECT * FROM departments WHERE not_allocated >= '"+grp+"'";
            result = db.query.executeQuery(selectDept);

            while (result.next()){
                $_deptID.add(result.getString("deptId"));
                $_deptID_Std.add(result.getInt("not_allocated"));
                $_deptID_Std_allocated.add(result.getInt("allocated"));
                rowCount++;
            }
            if (rowCount >= Dnum){
                ck = true;
                String $_get_ID, update, insert;
                int num, fin = 1, ans;
                int[] $_Key = action.$_Key_Gen(Dnum, rowCount);
                int id = allocateSelect[0].getSelectedIndex();
                for (int i = 0; i < $_Key.length; i++) {
                    $_get_ID = $_deptID.get($_Key[i]);
                    num = $_deptID_Std.get($_Key[i]);
                    ans = num - grp;
                    fin = $_deptID_Std_allocated.get($_Key[i])+(num - ans);

                    if (num >= grp) {
                        update = "UPDATE departments SET Not_Allocated = '"+ans+"', allocated = '"+fin+"' WHERE deptId = '"+$_get_ID+"'";
                        db.query.executeUpdate(update);
                        String s = ((fin+1)-grp) + " - "+ fin;
                        insert = "INSERT INTO allocated values('0','"+$_get_ID+"', '"+map.get(id)+"', '"+fin+"', '"+s+"')";
                        db.query.executeUpdate(insert);
                    }
                    update = "UPDATE lecturers SET status = '"+1+"' WHERE lectId = '"+map.get(id)+"'";
                    db.query.executeUpdate(update);
                    deptTable.repaint();
                }
                show(map.get(id));
                alertMessage(2,1,"Lecturer Allocated.");
            }else{
                ck = false;
                alertMessage(2,2,"There're "+rowCount+" Departments less than "+grp+"");
            }

        }catch (Exception e){e.printStackTrace();}
        return ck;
    }
    void show (String $_show_id){
        db.dbConnect();
        ResultSet result = null;
        try{
            String response = "SELECT * FROM lecturers WHERE lectid = '"+$_show_id+"'";
            result = db.query.executeQuery(response);
            while (result.next()) {
                $_outPut_response[0].setText(result.getString("name"));
                $_outPut_response[1].setText(result.getString("departments"));
                $_outPut_response[2].setText(result.getString("mobile"));
                $_outPut_response[0].repaint();
            }
            response = "SELECT * FROM allocated INNER JOIN departments ON allocated.deptId = departments.deptId WHERE lectId = '"+$_show_id+"'";
            result = db.query.executeQuery(response);
            int i = 0;
            clearTable(manual_data);
            while (result.next()){
                manual_data[i][0] = ""+(i+1)+"";
                manual_data[i][1] = result.getString("department");
                if (result.getString("course").equalsIgnoreCase("eed 216")) {
                    manual_data[i][2] = "ND 2";
                }
                if (result.getString("course").equalsIgnoreCase("eed 416")) {
                    manual_data[i][2] = "HND 2";
                }
                manual_data[i][3] = result.getString("matric_no");
                i++;
            }
            manual.repaint();
            System.out.println("acton");
        }
        catch (Exception ex){/**/}
    }
    void select(String tbl){
        db.dbConnect();
        obj.clear();
        ResultSet result = null;
        try{
            int i = 0, a = 0, b = 0;
            if (tbl.equalsIgnoreCase("Lecturers")) {
                String select = "SELECT * FROM "+tbl+" WHERE type = '0' ORDER BY ID DESC";
                result = db.query.executeQuery(select);
                allocateSelect[0].removeAllItems();
                allocateSelect[0].addItem(empty);
                allocateSelect[0].repaint();
                map.clear();
                clearTable(dashData);
                while (result.next()) {
                    allocateSelect[0].addItem(result.getString("name"));
                    map.put(i+1, result.getString("lectId"));
                    dashData[i][0] = "" + (i+1) + "";
                    dashData[i][1] = result.getString("name");
                    dashData[i][2] = result.getString("departments");
                    dashData[i][3] = result.getString("mobile");
                    if (result.getString("status").equalsIgnoreCase("1")) {
                        dashData[i][4] = "Allocated";
                        a++;
                    }
                    if (result.getString("status").equalsIgnoreCase("0")) {
                        dashData[i][4] = "Not Allocated";
                        b++;
                    }
                    dashTable.add(new JButton("Click me"));
                    i++;
                }
                s[0] = String.valueOf(i);
                s[1] = String.valueOf(a);
                s[2] = String.valueOf(b);

                if (i >= 499) {
                    dashData = new String[dashData.length + 1][7];
                }
                result.close();
            }

            if (tbl.equalsIgnoreCase("Departments")) {
                String select = "SELECT * FROM "+tbl+" ORDER BY ID DESC";
                result = db.query.executeQuery(select);
                clearTable(deptData);
                obj.add(empty);
                while (result.next()) {
                    obj.add(result.getString("department"));
                    deptData[i][0] = "" + (i+1) + "";
                    deptData[i][1] = result.getString("department");
                    deptData[i][2] = result.getString("course");
                    deptData[i][3] = result.getString("totalstd");
                    deptData[i][4] = result.getString("total_grp");
                    deptData[i][5] = result.getString("allocated");
                    deptData[i][6] = result.getString("not_allocated");
                    i++;
                }
                if (i >= 499) {
                    deptData = new String[deptData.length + 1][6];
                }
                selecOption.removeAllItems();
                int m = 0;
                if (obj.size() > 0)
                    for (int k = 0; k < obj.size(); k++) {
                        for (int l = 0; l < obj.size() ; l++) {
                            if (obj.get(l).equalsIgnoreCase(obj.get(k))){
                                m++;
                            }
                        }
                        if (m > 1){
                            obj.remove(k);
                        }
                        m = 0;
                    }
                for (String s: obj) {
                    selecOption.addItem(s);
                }
                result.close();
            }
        }catch (Exception ex){ ex.printStackTrace(); }
    }

    void clearTable(Object[][] tbl){
        for (int k = 0; k < tbl.length; k++) {
            for (int l = 0; l < tbl[k].length; l++) {
                tbl[k][l] = null;
            }
        }

    }

    Control() throws Exception{
        select("Lecturers");
        select("departments");
        db.dbConnect();
        listener();
        activeToggle2(toggle2[0], true);
        activeToggle(toggle[0], true);
        view.show(preview, "manual");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(JButton act: btn){
            if (e.getSource() == act) {
                active(act, true);
                cardLayout.show(container, String.valueOf(act.getText()));
                select(String.valueOf(act.getName()));
                manual.repaint();
            }
        }
        for(JButton b: toggle){
            if (e.getSource() == b) {
                activeToggle(b, true);
                crd.show(allocate, String.valueOf(b.getName()));
            }
        }
        for(JButton b: toggle2){
            if (e.getSource() == b) {
                activeToggle(b, true);
                crd.show(allocate, String.valueOf(b.getName()));
            }
        }
        for (JButton b: toggle) {
            if (e.getSource() == b){
                activeToggle(b, true);
            }
        }
        if (e.getSource() == toggle2[0]){
            activeToggle(toggle[0], true);
        }
        if (e.getSource() == toggle[1]){
            activeToggle2(toggle2[1], true);
        }

        for (JRadioButton r: level) {
            r.addActionListener(this);
        }
        if (e.getSource() == submit){
            validate(submit);
            select("lecturers");
        }
        if (e.getSource() == submit2){
            validate(submit2);
            select("departments");
        }
        if (e.getSource() == submit3){
            validate(submit3);
        }
        if (e.getSource() == submit4){
            validate(submit4);
        }
    }
    public static void main(String[] args) throws Exception {
        new Control();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for (JTextField t: input) {
            if (!Character.isAlphabetic(e.getKeyChar()) && e.getSource() == t && e.getKeyChar() != KeyEvent.VK_SPACE) {
                e.consume();
            }
        }
        if (!Character.isAlphabetic(e.getKeyChar()) && e.getSource() == input2[0] && e.getKeyChar() != KeyEvent.VK_SPACE) {
            e.consume();
        }
        if (!Character.isDigit(e.getKeyChar()) && e.getSource() == input2[1]) {
            e.consume();
        }
        for (int i = 0; i < input3.length; i++) {
            if (!Character.isDigit(e.getKeyChar()) && e.getSource() == input3[i]) {
                e.consume();
            }
        }
        if (!Character.isDigit(e.getKeyChar()) && e.getSource() == input4[1])
            e.consume();
        if (!Character.isAlphabetic(e.getKeyChar()) && e.getSource() == input4[0]  && e.getKeyChar() != KeyEvent.VK_SPACE)
            e.consume();

        for (int i = 1; i < 13; i++) {
            if (input4[1].getText().length() == 4) {
                input4[1].setText(input4[1].getText() + " - ");
            }
            if (input4[1].getText().length() == 10) {
                input4[1].setText(input4[1].getText() + " - ");
            }
            if (input4[1].getText().length() == 17 && e.getSource() == input4[1]) {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
