import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Control extends Design implements ActionListener, KeyListener, ListSelectionListener, MouseListener {
    $Database db = new $Database();
    Action action = new Action();
    LinkedList <String> $data_list = new LinkedList <>();
    String tbl_act, cnt, page = "", table = "", id = "", $_course_allocate = "";
    int type;

    /**
    * Lecturer type select
    * */
    void  type_select(int type){
        $data_list.clear();
        String select = "SELECT * FROM lecturers WHERE type = '"+type+"' ORDER BY id DESC";
        ResultSet result;
        try {
            result = db.query.executeQuery(select);
            while (result.next()) {
                $data_list.add(result.getString("lectId"));
            }
        } catch (Exception ex) {/***/}
    }

    /**
     *
     * Couser Allocate Select
     * */
    void course_allocate_select(int state){
        String upd = "UPDATE course_allocated SET state = '"+state+"' WHERE lectId = '"+$_course_allocate+"'";
        db.dbAction(upd);
//        if (){
            upd = "UPDATE lecturers SET state = '"+state+"' WHERE lectId = '"+$_course_allocate+"'";
            if (db.dbAction(upd)) {
                String sel = "SELECT * FROM lecturers WHERE lectId = '"+$_course_allocate+"'";
                ResultSet result;
                try {
                    result = db.query.executeQuery(sel);
                    while (result.next()) {
                        if (state == 0)
                            item3.removeElement(result.getString("name"));
                        if (state == 1)
                            item3.addElement(result.getString("name"));
                    }
                    list3.repaint();
                    System.out.println(item3);
                }catch(Exception ex){/**/}

            }
//        }
    }


    /**
     * Dashboard data
     * */
    void dashboardCount(){
        int i = 0, a = 0, b = 0;
        allocateSelect[0].removeAllItems();
        allocateSelect[0].addItem(empty);
        allocateSelect[0].repaint();

        allocateSelect[1].removeAllItems();
        allocateSelect[1].addItem(empty);
        allocateSelect[1].repaint();
        map.clear();
        try{
            String select = "SELECT * FROM lecturers ORDER BY id DESC";
            ResultSet result = db.query.executeQuery(select);
            int k = 0;
            item.removeAllElements();
            while (result.next()) {
                allocateSelect[0].addItem(result.getString("name"));
                allocateSelect[1].addItem(result.getString("name"));
                item.addElement(result.getString("name"));
                map.put(k+1, result.getString("lectId"));
                if (result.getString("type").equalsIgnoreCase("1")) {
                    b++;
                }
                if (result.getString("type").equalsIgnoreCase("0")) {
                    a++;
                }
                k++;
            }
            list.repaint();
            list2.repaint();
            obj.clear();
            obj.add(empty);
            select = "SELECT * FROM departments";
            result = db.query.executeQuery(select);
            while (result.next()){
                obj.add(result.getString("department"));
                i++;
            }

            result.close();

        }catch (Exception ex){/**/}
        s[0] = String.valueOf(a);
        s[1] = String.valueOf(b);
        s[2] = String.valueOf(i);

        allocateSelect[1].removeAllItems();
        selecOption.removeAllItems();
        int m = 0;
        if (obj.size() > 0)
            for (int k = 0; k < obj.size(); k++) {
                for (String value : obj) {
                    if (value.equalsIgnoreCase(obj.get(k))) {
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
            allocateSelect[1].addItem(s);
        }
        selecOption.repaint();
    }

    /**
     * Action Listener
     * */
    void listener(){
        for (JButton jButton : btn) {
            jButton.addActionListener(this);
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
            input3[i].addActionListener(this);
            if (i < 2) {
                input4[i].addKeyListener(this);
                input4[i].addActionListener(this);
                action_btn[i].addActionListener(this);
                txt_edit[i].addKeyListener(this);
            }
        }
        for (JTable t: tbl) {
            t.addMouseListener(this);
        }
        for (JRadioButton r: level) {
            r.addActionListener(this);
        }
        input5.addKeyListener(this);
        num.addActionListener(this);
        num.addKeyListener(this);
        fm.addKeyListener(this);
        submit.addActionListener(this);
        submit2.addActionListener(this);
        submit3.addActionListener(this);
        submit4.addActionListener(this);
        submit5.addActionListener(this);
        submit6.addActionListener(this);
        selecOption.addActionListener(this);
        list.addListSelectionListener(this);
        list2.addListSelectionListener(this);
        list3.addListSelectionListener(this);
        close.addActionListener(this);
        close2.addActionListener(this);
        update.addActionListener(this);
        edit.addActionListener(this);
        select.addActionListener(this);
        select_edit.addActionListener(this);
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
                if (db.dbAction("INSERT INTO lecturers values('0', '"+lectName+"', '"+lectDept+"', '"+lectGender+"' ," +
                        " " +
                        "'"+lectMobile+ "' , '0', '0', '"+action.lecturerID()+"', '0')")) {
                    alertMessage(1,1, "Lecturer Added");
                    type_select(0);
                    for (JTextField t: input) {
                        t.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
                        t.setText("");
                        selecOption.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
                        fm.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
                        fm.setText("");
                        radio.clearSelection();
                    }
                }else alertMessage(1,2, "Error Occured");
            }else
                alertMessage(1, 2, "All fields ar Required");
        }
        if (vd == submit2){
            valid = 0;
            for (JTextField jTextField : input2) {
                if (jTextField.getText().equalsIgnoreCase("") || jTextField.getText().isEmpty()) {
                    jTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    alertMessage(0, 2, "All fields are required");
                } else {
                    valid++;
                    jTextField.setBorder(BorderFactory.createLineBorder(colorTop[1], 3));
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
            for (JRadioButton r: section) {
                if (r.isSelected()){
                    valid++;
                    deptSect = r.getText().trim();
                }
            }
            if (valid == 4){
                int grp_no = 0, gpn = 0;
                if (deptLvl.contains("216")){
                    grp_no = Integer.parseInt(deptStd)/10;
                    gpn = 10;
                }
                if (deptLvl.contains("416")){
                    grp_no = Integer.parseInt(deptStd)/5;
                    gpn = 5;
                }
                System.out.println(deptStd);
                if (db.dbAction("INSERT INTO departments VALUES('0','"+deptName+"','"+deptLvl+"', '"+deptSect+"', '"+Integer.parseInt(deptStd)+"', '"+grp_no+ "', '"+0+ "', '"+grp_no+ "', '"+action.departmentID()+"', '"+0+"', '"+(Integer.parseInt(deptStd) - (grp_no*gpn))+"')")) {
                    alertMessage(0,1,"Department Added");
                    lvl.clearSelection();
                    sect.clearSelection();
                    for (JTextField t: input2) {
                        t.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
                        t.setText("");
                    }
                }else alertMessage(0,2,"Department Not Added");
            }
            select("Departments");
        }
        if (vd == submit3){
            valid = 0;
            for (JComboBox jComboBox : allocateSelect) {
                if (jComboBox.getSelectedObjects().toString().equalsIgnoreCase(empty) || jComboBox.getSelectedItem().toString().equalsIgnoreCase(empty)) {
                    jComboBox.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                } else {
                    jComboBox.setBorder(BorderFactory.createLineBorder(colorTop[1], 3));
                    valid++;
                }
            }
            for (JTextField textField : input3) {
                if (textField.getText().length() <= 0 || Integer.parseInt(textField.getText()) <= 0) {
                    textField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                } else {
                    textField.setBorder(BorderFactory.createLineBorder(colorTop[1], 3));
                    valid++;
                }
            }

            if (valid == 3){
                lectNum = allocateSelect[0].getSelectedIndex();
                deptNum = Integer.parseInt(input3[0].getText());
                group = Integer.parseInt(input3[1].getText());
                check(lectNum, deptNum, group);

                for (JTextField jTextField : input3) {
                    jTextField.setText("");
                    allocateSelect[0].setSelectedItem(empty);
                }
            }
        }
        if (vd == submit4){
            valid = 0;
            for (int i = 0; i < input4.length; i++) {
                if (input4[i].getText().equalsIgnoreCase("") || input4[i].getText().isEmpty()) {
                    input4[i].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
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
//                alertMessage(3, 2, "Input a valid Number");
            if (valid == 3) {
                if (db.dbAction("INSERT INTO lecturers values('0', '"+facName+"', 'MAPCED', '"+facGen+"' ," +
                        " '"+facMobile+ "' , '0', '"+1+"', '"+action.lecturerID()+"', '0')")) {
                    alertMessage(3, 1, "Facilitator Added");
                    facSelect();
                    type_select(1);
                    for (JTextField t: input4) {
                        t.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
                        t.setText("");
                        selecOption.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
                        grp.clearSelection();
                    }
                }else alertMessage(3, 2, "Sorry Error Occured");
            }
            else
                alertMessage(3, 2, "All fields are Required");
        }
        if (vd == submit5){
            if (!num.getText().equalsIgnoreCase("") && !num.getText().isEmpty()){
                int g = Integer.parseInt(num.getText());
                randomAllocate(g);
                num.setBorder(BorderFactory.createLineBorder(colorTop[1], 3));
            }else num.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        }

        for (JPanel a: alert) {
            a.repaint();
        }
        if (vd == submit6){
            if (!input5.getText().isEmpty()){
                input5.setBorder(BorderFactory.createLineBorder(colorTop[1], 3));
                course_allocate(Integer.parseInt(input5.getText()));
            }else
                input5.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        }
    }

    /**
     * Facilitator
     * */
    void facSelect(){
        String select = "SELECT * FROM lecturers WHERE type = '1' ORDER BY ID DESC";
        type_select(1);
        dashboardCount();
        db.dbConnect();
        map2.clear();
        try{
            ResultSet result = db.query.executeQuery(select);
            int i = 0;
            item2.removeAllElements();
            action.clearTable(facData);
            while (result.next()) {
                facData[i][0] = "" + (i+1) + "";
                facData[i][1] = result.getString("name");
                item2.addElement(result.getString("name"));
                map2.put(i+1, result.getString("lectId"));

                facData[i][2] = result.getString("departments");
                facData[i][3] = result.getString("mobile");
                if (result.getString("status").equalsIgnoreCase("1")) {
                    facData[i][4] = "Allocated";
                }
                if (result.getString("status").equalsIgnoreCase("0")) {
                    facData[i][4] = "Not Allocated";
                }
                i++;
                if (i >= facData.length) {
                    facData = new String[facData.length + 1][7];
                }
            }
            type_select(1);
//            System.out.println("Facselect = "+map2);
            facTable.repaint();
            result.close();
        }catch (Exception ex){/**/}
    }
    void lectSelect(){
        String select = "SELECT * FROM lecturers WHERE type = '0' ORDER BY ID DESC";
        ResultSet result = null;
        try{
            result = db.query.executeQuery(select);
            int i = 0;
            action.clearTable(dashData);
            while (result.next()) {
                dashData[i][0] = "" + (i+1) + "";
                dashData[i][1] = result.getString("name");
                dashData[i][2] = result.getString("departments");
                dashData[i][3] = result.getString("mobile");
                if (result.getString("status").equalsIgnoreCase("1")) {
                    dashData[i][4] = "Allocated";
                }
                if (result.getString("status").equalsIgnoreCase("0")) {
                    dashData[i][4] = "Not Allocated";
                }
                i++;
                if (i >= dashData.length) {
                    dashData = new String[dashData.length + 1][7];
                }
            }
            result.close();
            dashTable.repaint();
        }catch (Exception ex){/***/}
        dashboardCount();
    }
    void deptSelect(){
        String select = "SELECT * FROM departments ORDER BY ID DESC";
        ResultSet result;
        try {
            result = db.query.executeQuery(select);
            action.clearTable(deptData);
            int i = 0;
            $data_list.clear();
            while (result.next()) {
                deptData[i][0] = "" + (i + 1) + "";
                deptData[i][1] = result.getString("department");
                deptData[i][2] = result.getString("course");
                deptData[i][3] = result.getString("section");
                deptData[i][4] = result.getString("totalstd");
                deptData[i][5] = result.getString("total_grp");
                deptData[i][6] = result.getString("allocated");
                deptData[i][7] = result.getString("not_allocated");
                deptData[i][8] = result.getString("un_grp");
                $data_list.add(result.getString("deptId"));
                if (i >= 49) {
                    deptData = new String[deptData.length + 1][6];
                }
                i++;
            }
        }catch (Exception ex){/**/}
        deptTable.repaint();
        dashboardCount();
        System.out.println($data_list);
    }


    void randomAllocate(int grp_no){
        String select = "SELECT * FROM departments, lecturers WHERE departments.not_allocated > '"+grp_no+"'";
        HashMap<String, Integer> departments = new HashMap<>();
        ArrayList<String> lecturers = new ArrayList<>();
        HashMap<String,Integer> total = new HashMap<>();
        ArrayList<String> groupID = new ArrayList<>();
        String dp = null, $_lecturers_id = null, sub = null;
        int fin = 1;
        try{
            ResultSet result = db.query.executeQuery(select);
            while (result.next()){
                departments.put(result.getString("deptid"), result.getInt("not_allocated"));
                total.put(result.getString("deptid"), result.getInt("allocated"));
                lecturers.add(result.getString("lectid"));

            }
            for (int i = 1; i < lecturers.size(); i++) {
                boolean bool = true;
                while (bool){
                    bool = !bool;
                    if (lecturers.get(i).equalsIgnoreCase(lecturers.get(i-1))){
                        lecturers.remove(i);
                    }
                }
            }
            for (String s: departments.keySet()) {
                groupID.add(s);
            }
            int[] $key = action.$_Key_Gen(groupID.size(),groupID.size());
            int t = 0, ans = 0;
            for (int k = 0; k < $key.length; k++) {
                for (int i = 0; i < lecturers.size(); i++) {
                    dp = groupID.get(k);
                    ans = departments.get(dp) - grp_no;
                    fin = total.get(dp) + grp_no;
                    sub = (((fin)-grp_no)+1) + " - "+ (fin);
                    $_lecturers_id = lecturers.get(i);
                    if (departments.get(dp)>= grp_no || ans >= grp_no) {
                        String insert = "INSERT INTO allocated values('0', '"+dp+"', '"+$_lecturers_id+"','"+grp_no+"', '"+sub+"')";
                        String update = "UPDATE departments SET not_allocated = '" + ans + "', allocated = '" + fin + "' WHERE deptid = '" + dp + "'";

                        if (db.dbAction(update) && db.dbAction(insert)) {
                            departments.replace(dp, ans);
                            total.replace(dp,fin);
//                            System.out.println(departments + " t = " + t);
                        } else {
                            System.out.println("Not Inserted");
//                            System.out.println(departments.values());
                        }
                    }
                }
            }
        }catch (Exception ex){/**/}
    }

    /**
     * Manual Allocate
     * */
    boolean check(int index, int Dnum, int grp){
        dashboardCount();
        db.dbConnect();
        boolean ck = false;
        ResultSet result = null;
        int rowCount = 0;

        ArrayList<String> $_deptID = new ArrayList<>();
        ArrayList<Integer> $_deptID_Std = new ArrayList<>();
        ArrayList<Integer> $_deptID_Std_allocated = new ArrayList<>();
        try{
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
                int id = index;
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
                show(map.get(id), 0);
                alertMessage(2,1,"Lecturer Allocated.");
            }else{
                ck = false;
                alertMessage(2,2,"There're "+rowCount+" Departments less than "+grp+"");
            }
            dashboardCount();
        }catch (Exception e){e.printStackTrace();}
        return ck;
    }
    void show (String $_show_id, int table){
        $_course_allocate = $_show_id;

        ResultSet result = null;
        try{
            String response = "SELECT * FROM lecturers WHERE lectid = '"+$_show_id+"'";
            result = db.query.executeQuery(response);
            while (result.next()) {
                if (table == 0){
                    $_outPut_response[0].setText(result.getString("name"));
                    $_outPut_response[1].setText(result.getString("departments"));
                    $_outPut_response[2].setText(result.getString("mobile"));
                }
                if (table == 1){
                    $_outPut_response_rand[0].setText(result.getString("name"));
                    $_outPut_response_rand[1].setText(result.getString("departments"));
                    $_outPut_response_rand[2].setText(result.getString("mobile"));
                }
                if (table == 2){
                    course_label_response[0].setText(result.getString("name"));
                    course_label_response[1].setText(result.getString("departments"));
                    course_label_response[2].setText(result.getString("mobile"));
                }
            }
            if (table < 2) {
                response = "SELECT * FROM allocated INNER JOIN departments ON allocated.deptId = departments.deptId WHERE lectId = '" + $_show_id + "'";
                result = db.query.executeQuery(response);
                int i = 0;
                action.clearTable(manual_data);
                action.clearTable(rand_data);

                while (result.next()) {
                    if (table == 0) {
                        manual_data[i][0] = "" + (i + 1) + "";
                        manual_data[i][1] = result.getString("department");
                        manual_data[i][2] = result.getString("section");

                        if (result.getString("course").equalsIgnoreCase("eed 216")) {
                            manual_data[i][3] = "ND 2";
                        }
                        if (result.getString("course").equalsIgnoreCase("eed 416")) {
                            manual_data[i][3] = "HND 2";
                        }
                        manual_data[i][4] = result.getString("matric_no");
                    }
                    if (table == 1) {
                        rand_data[i][0] = "" + (i + 1) + "";
                        rand_data[i][1] = result.getString("department");
                        rand_data[i][2] = result.getString("section");
//                        System.out.println(result.getString("department"));
                        if (result.getString("course").equalsIgnoreCase("eed 216")) {
                            rand_data[i][3] = "ND 2";
                        }
                        if (result.getString("course").equalsIgnoreCase("eed 416")) {
                            rand_data[i][3] = "HND 2";
                        }
                        rand_data[i][4] = result.getString("matric_no");
                    }
                    i++;
                }
            }
            if (table == 2){
                response = "SELECT * FROM course_allocated INNER JOIN departments ON course_allocated.deptId = " +
                        "departments.deptId WHERE lectId = '" + $_show_id + "'";
                result = db.query.executeQuery(response);
                int i = 0;
                HashMap<String, String> cus = new HashMap<>();
                cus.put("EED 126", "Introduction to Entrepreneurship Education");cus.put("EED 216", "Entrepreneurship Education II");
                cus.put( "EED 326", "Entrepreneurship Education III");cus.put("EED 416", "Entrepreneurship Education IV");
                action.clearTable(courseData);
                while (result.next()){
                    courseData[i][0] = (i+1)+"";
                    courseData[i][1] = cus.get(result.getString("departments.course"));
                    courseData[i][2] = result.getString("departments.course");
                    courseData[i][3] = result.getString("department");
                    courseData[i][4] = result.getString("section");
                    if (result.getString("course").equalsIgnoreCase("eed 216")) {
                        courseData[i][5] = "ND 2";
                    }
                    if (result.getString("course").equalsIgnoreCase("eed 416")) {
                        courseData[i][5] = "HND 2";
                    }
                    if (result.getString("course").equalsIgnoreCase("eed 126")) {
                        courseData[i][5] = "ND 1";
                    }
                    if (result.getString("course").equalsIgnoreCase("eed 326")) {
                        courseData[i][5] = "HND 1";
                    }
                    i++;
                }
            }
            courseTable.repaint();
            rand.repaint();
            manual.repaint();
            dashboardCount();
        }
        catch (Exception ex){/**/}
    }
    void select(String tbl){
        db.dbConnect();
        ResultSet result = null;
        try{
            int i = 0;
            if (tbl.equalsIgnoreCase("Lecturers")) {
                String select = "SELECT * FROM "+tbl+" WHERE type = '0' ORDER BY ID DESC";
                result = db.query.executeQuery(select);
                action.clearTable(dashData);
                while (result.next()) {
                    dashData[i][0] = "" + (i+1) + "";
                    dashData[i][1] = result.getString("name");
                    dashData[i][2] = result.getString("departments");
                    dashData[i][3] = result.getString("mobile");
                    if (result.getString("status").equalsIgnoreCase("1")) {
                        dashData[i][4] = "Allocated";
                    }
                    if (result.getString("status").equalsIgnoreCase("0")) {
                        dashData[i][4] = "Not Allocated";
                    }
                    i++;
                }
                if (i >= 49) {
                    dashData = new String[dashData.length + 1][7];
                }
                result.close();
                dashTable.repaint();
            }

            if (tbl.equalsIgnoreCase("Departments")) {
                String select = "SELECT * FROM "+tbl+" ORDER BY ID DESC";
                result = db.query.executeQuery(select);
                action.clearTable(deptData);
                $data_list.clear();
                while (result.next()) {
                    deptData[i][0] = "" + (i + 1) + "";
                    deptData[i][1] = result.getString("department");
                    deptData[i][2] = result.getString("course");
                    deptData[i][3] = result.getString("section");
                    deptData[i][4] = result.getString("totalstd");
                    deptData[i][5] = result.getString("total_grp");
                    deptData[i][6] = result.getString("allocated");
                    deptData[i][7] = result.getString("not_allocated");
                    deptData[i][8] = result.getString("un_grp");
                    $data_list.add(result.getString("deptId"));
                    i++;
                }
                if (i >= 49) {
                    deptData = new String[deptData.length + 1][6];
                }
                deptTable.repaint();
                result.close();
            }

            String select = "";
            obj.clear();
            obj.add(empty);
            select = "SELECT * FROM departments";
            result = db.query.executeQuery(select);
            while (result.next()){
                obj.add(result.getString("department"));
                i++;
            }

            result.close();
        }catch (Exception ex){ ex.printStackTrace(); }

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
            allocateSelect[1].addItem(s);
        }
        selecOption.repaint();
    }

    void course_allocate(int unit){
        ResultSet result = null;
        ArrayList<String> dep_rand = new ArrayList<>();
        ArrayList<String> lect_course = new ArrayList<>();
        try {
            String trunc = "Delete FROM course_allocated WHERE state = '0'";
            db.dbAction(trunc);
//            if (db.dbAction(trunc)) {
                System.out.println("success");
                String select = "SELECT * FROM Lecturers WHERE type = '1'";
                result = db.query.executeQuery(select);
                while (result.next()) {
                    lect_course.add(result.getString("lectId"));
                }
                int[] lect_rand = action.$_Key_Gen(lect_course.size(), lect_course.size());
                int success = 0;
                select = "SELECT * FROM departments where status = '0'";
                result = db.query.executeQuery(select);
                dep_rand.clear();
                while (result.next()) {
                    dep_rand.add(result.getString("deptId"));
                }
                int[] rand = action.$_Key_Gen(unit / 2, dep_rand.size());
                for (int i = 0; i < lect_course.size(); i++) {
                    for (int j = 0; j < (unit / 2); j++) {
                        if (dep_rand.size() > 0 && dep_rand.size() >= unit / 2) {

                            System.out.println(dep_rand.size());
                            System.out.println(rand.length);
                            for (int k : rand) {
                                System.out.println("Random Keys = " + k);
                                String insert =
                                        "INSERT INTO course_allocated values ('0', '" + lect_course.get(lect_rand[i]) + "', '" + dep_rand.get(k) +
                                                "', '0')";
                                if (db.dbAction(insert)) {
                                    success++;
                                    dep_rand.remove(k);
                                }
                            }
                        }
                        if (success == lect_course.size())
                            JOptionPane.showMessageDialog(frame, "Allocated");
                    }
                }
//            }
        }catch (Exception ex){ex.printStackTrace();}
        deptSelect();
    }

    void $_E_D_action(String $ed_action, String $ed_tbl, String $ed_id) {
        if ($ed_action.equalsIgnoreCase("delete")) {
            String delete = "";
            if ($ed_tbl.equalsIgnoreCase("lecturers")) {
                delete = "DELETE FROM "+$ed_tbl+" WHERE lectid = '"+$ed_id+"'";
            }else if($ed_tbl.equalsIgnoreCase("departments")){
                delete = "DELETE FROM "+$ed_tbl+" WHERE deptid = '"+$ed_id+"'";
            }
            try {
                if (db.dbAction(delete)){
                    facSelect();
                    deptSelect();
                    lectSelect();
                    JOptionPane.showMessageDialog(frame, "Deleted");
                }
            } catch (Exception ex) {/**/}
        }
        else if($ed_action.equalsIgnoreCase("edit")){
            Edit($ed_tbl, $ed_id, page);
        }
    }

    void Edit (String tbl, String id, String page) {
        System.out.println(id);
        ResultSet result;
        f.setVisible(true);
        frame.setVisible(false);
        this.id = id;
        this.table = tbl;
        try{
            String select = "";
            if (tbl.equalsIgnoreCase("lecturers")){
                select = "SELECT * FROM "+tbl+" WHERE lectid = '"+id+"'";
                result = db.query.executeQuery(select);
                obj.clear();
                while (result.next()){
                    txt_edit[0].setText(result.getString("name"));
                    txt_edit[1].setText(result.getString("mobile"));
                    obj.add(result.getString("departments"));
                    for (JRadioButton rad : gen_edit) {
                        if (result.getString("gender").equalsIgnoreCase(rad.getText()))
                            rad.setSelected(true);
                    }
                }
            }
            select = "SELECT * FROM departments";
            result = db.query.executeQuery(select);
            int i = 0;
            while (result.next()){
                obj.add(result.getString("department"));
                i++;
            }
            allocateSelect[1].removeAllItems();
            int m = 0;
            if (obj.size() > 0)
                for (int k = 0; k < obj.size(); k++) {
                    for (String value : obj) {
                        if (value.equalsIgnoreCase(obj.get(k))) {
                            m++;
                        }
                    }
                    if (m > 1){
                        obj.remove(k);
                    }
                    m = 0;
                }
            for (String s: obj) {
                allocateSelect[1].addItem(s);
            }
            allocateSelect[1].repaint();

            result.close();
        }catch (Exception e){/**/}

    }
    void  Edit(String tbl, String id){
        this.id = id;
        this.table = tbl;
        String select = "SELECT * FROM departments WHERE deptId = '"+id+"'";
        ResultSet result = null;
        try{
            result = db.query.executeQuery(select);
            while(result.next()){
                input2[0].setText(result.getString("department"));
                input2[1].setText(result.getString("totalstd"));
                for (JRadioButton crs: level) {
                    if (crs.getText().equalsIgnoreCase(result.getString("course"))){
                        crs.setSelected(true);
                    }
                }
                for (JRadioButton crs: section) {
                    if (crs.getText().equalsIgnoreCase(result.getString("section"))){
                        crs.setSelected(true);
                    }
                }
            }
        }catch (Exception ex){/**/}
    }
    void UPDATE(String id){
        this.id = id;
        int grp_no = 0, gpn = 0;

        for (JRadioButton crs: section) {
            if (crs.isSelected())
                deptSect = crs.getText();
        }
        for (JRadioButton crs: level) {
            if (crs.isSelected())
                deptLvl = crs.getText();
        }
        if (deptLvl.contains("216")) {
            grp_no = Integer.parseInt(input2[1].getText()) / 10;
            gpn = 10;
        }

        if (deptLvl.contains("416")) {
            grp_no = Integer.parseInt(input2[1].getText()) / 5;
            gpn = 5;
        }

        if (db.dbAction("UPDATE departments SET department = '"+input2[0].getText()+"',course = '"+deptLvl+"', " +
                "not_allocated = '"+grp_no+ "', un_grp = '"+(Integer.parseInt(deptStd) - (grp_no*gpn))+"', section = '"+deptSect+"',allocated = '0'," +
                " totalstd = '"+input2[1].getText()+"', total_grp = '"+grp_no+"' WHERE deptId = '"+id+"'")) {
            db.dbAction("DELETE FROM allocated WHERE deptId = '"+id+"'");
            alertMessage(0,1,"Department Updated");
            deptSelect();
            lvl.clearSelection();
            sect.clearSelection();
            for (JTextField t: input2) {
                t.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
                t.setText("");
                deptSect = "";
            }
        }else{ alertMessage(0,2,"Department Not Updated");}
    }

    Control() throws Exception{
        db.dbConnect();
        course_allocate_select(1);
        select("departments");
        facSelect();
        dashboardCount();
        select("Lecturers");
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
                page = act.getText();
                manual.repaint();
                select("departments");
                deptSelect();
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

        if (e.getSource() == submit){
            validate(submit);
            select("lecturers");
            select("departments");
            type_select(0);
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
        if (e.getSource() == submit5){
            validate(submit5);
        }
        if (e.getSource() == submit6){
            validate(submit6);
        }
        try {
            if (e.getSource() == btn[1]) {
                $data_list.clear();
                type = 0;
                tbl_act = "lecturers";
                for (int i = 0; i < action_btn.length; i++) {
                    action_btn[i].setBounds(1000 + (i * 110), 520, 100, 40);
                    lect.add(action_btn[i]);
                }
                type_select(0);
            }

            if (e.getSource() == btn[2]) {
                $data_list.clear();
                type = 1;
                tbl_act = "lecturers";
                for (int i = 0; i < action_btn.length; i++) {
                    action_btn[i].setBounds(1000 + (i * 110), 420, 100, 40);
                    facilitators.add(action_btn[i]);
                }
                type_select(1);
            }

            if (e.getSource() == btn[3]) {
                $data_list.clear();
                tbl_act = "departments";
                for (int i = 0; i < action_btn.length; i++) {
                    action_btn[i].setBounds(1000 + (i * 110), 500, 100, 40);
                    dept.add(action_btn[i]);
                }
                edit.setBounds(1050, 560, 100, 40);
                edit.setForeground(Color.WHITE);
                edit.setFont(new Font("MONOSPACE",Font.PLAIN, 15));
                edit.setBackground(colorTop[1]);
                dept.add(edit);
                deptSelect();
            }

            if (e.getSource() == action_btn[1]) {
                $_E_D_action("delete", tbl_act, cnt);
                System.out.println(cnt);
                deptSelect();
                select("departments");
                type_select(type);
            }
            if (e.getSource() == action_btn[0] && !tbl_act.equalsIgnoreCase("departments")) {
                $_E_D_action("edit", tbl_act, cnt);
                type_select(type);
            }
            if (e.getSource() == action_btn[0] && tbl_act.equalsIgnoreCase("departments")) {
                deptSelect();
                Edit(tbl_act, cnt);
            }
            if(e.getSource() == close){
                f.dispose();
                frame.setVisible(true);
            }
            if(e.getSource() == close2){
                f2.dispose();
                frame.setVisible(true);
            }
            if (e.getSource() == update){
                String update = "";
                if (table.equalsIgnoreCase("lecturers")){
                    String dep = allocateSelect[1].getSelectedItem().toString();
                    String g = "";
                    int stat = 0;
                    if (dep.equalsIgnoreCase("MAPCED")) {
                        stat = 1;
                    }
                    for (JRadioButton b : gen_edit)
                        if (b.isSelected())
                            g = b.getText();
                    update = "UPDATE lecturers SET name = '"+txt_edit[0].getText()+"', departments = '"+dep+"', " +
                            "gender = '"+g+ "', mobile = '"+txt_edit[1].getText()+"', type = '"+stat+"' WHERE lectid " +
                            "= '"+id+"'";

                    if (db.dbAction(update)){
                        f.dispose();
                        frame.setVisible(true);
                        facSelect();
                        lectSelect();
                        deptSelect();
                        JOptionPane.showMessageDialog(f, "Updated");
                    }
//                }
                }
            }
            if (e.getSource() == edit)
                UPDATE(cnt);
        }catch (Exception ex){/**/}

        if (e.getSource() == select_edit){
            course_allocate_select(0);
        }
        if (e.getSource() == select){
            course_allocate_select(1);
//            JOptionPane.showMessageDialog(frame, "Hola");
        }
    }
    public static void main(String[] args) throws Exception { new Control(); }

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

        if (!Character.isAlphabetic(e.getKeyChar()) && e.getSource() == txt_edit[0]  && e.getKeyChar() != KeyEvent.VK_SPACE)
            e.consume();

        for (int i = 1; i < 13; i++) {
            if (input4[1].getText().length() == 11 && e.getSource() == input4[1]) {
                e.consume();
            }
            if (e.getSource() == txt_edit[1] && (txt_edit[1].getText().length() == 11 || (!Character.isDigit(e.getKeyChar())))){
                e.consume();
            }

        }
        if (e.getSource() == num){
            if (!Character.isDigit(e.getKeyChar()))
                e.consume();
        }
        if (e.getSource() == input5){
            if (!Character.isDigit(e.getKeyChar()))
                e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == list ){
            if (list.getSelectedIndex() >= 0 ){
                show(map.get(list.getSelectedIndex()+1), 1);
            }
        }

        if (e.getSource() == list2){
            if (list2.getSelectedIndex() >= 0 ){
                show(map2.get(list2.getSelectedIndex()+1), 2);
            }
        }
    }

     @Override
     public void mouseClicked(MouseEvent e) {
         for (JTable t: tbl) {
             if (e.getSource() == t && t.getSelectedRow() < $data_list.size()){
                cnt = $data_list.get(t.getSelectedRow());
             }
         }
     }

     @Override
     public void mousePressed(MouseEvent mouseEvent) {}
     @Override
     public void mouseReleased(MouseEvent mouseEvent) {}
     @Override
     public void mouseEntered(MouseEvent mouseEvent) {}
     @Override
     public void mouseExited(MouseEvent mouseEvent) {}
 }