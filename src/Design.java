import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Design extends Dec{
    JList list;
    Design() throws Exception{
        selecOption.addItem(empty);
        allocateSelect[0].addItem(empty);
        setSideBar();
        container.setLayout(cardLayout);
        container.add(dash, String.valueOf(btn[0].getText()));
        container.add(lect, String.valueOf(btn[1].getText()));
        container.add(facilitators, String.valueOf(btn[2].getText()));
        container.add(dept, String.valueOf(btn[3].getText()));
        container.add(allocate, String.valueOf(btn[4].getText()));

        container.setBounds(205, 50, 1260, 700);

        dash.setLayout(null);
        lect.setLayout(null);
        dept.setLayout(null);
        facilitators.setLayout(null);
        allocate.setLayout(crd);
        facilitators.setBackground(Color.white);
        dash.setBackground(Color.WHITE);
        dept.setBackground(Color.WHITE);
        lect.setBackground(Color.WHITE);
        allocate.setBackground(Color.WHITE);


        manual.setCellSelectionEnabled(false);
        manual.setRowSelectionAllowed(false);
        manual.setColumnSelectionAllowed(false);

        lectTable.setCellSelectionEnabled(false);
        lectTable.setRowSelectionAllowed(false);
        lectTable.setColumnSelectionAllowed(false);

        dashTable.setCellSelectionEnabled(false);
        dashTable.setRowSelectionAllowed(false);
        dashTable.setColumnSelectionAllowed(false);

        deptTable.setCellSelectionEnabled(false);
        deptTable.setRowSelectionAllowed(false);
        deptTable.setColumnSelectionAllowed(false);

        header.setBounds(960, 70, 200,40);
        header.setFont(new Font("Monospace", Font.BOLD, 25));
        header.setForeground(Color.WHITE);

        header2.setBounds(960, 80, 200,20);
        header2.setFont(new Font("Monospace", Font.BOLD, 25));
        header2.setForeground(Color.WHITE);

        header3.setBounds(970, 80, 200,20);
        header3.setFont(new Font("Monospace", Font.BOLD, 25));
        header3.setForeground(Color.WHITE);
        for (int i = 0; i < txt.length; i++) {
            if (i < 2){
                input[i].setBounds(960, 150+(i*90), 280, 40);
                selecOption.setBounds(960, 150+(i*90), 280, 40);
                selecOption.setFont(new Font("monospace", Font.PLAIN, 17));
                selecOption.setBackground(Color.LIGHT_GRAY);
                input[i].setFont(new Font("Monospace", Font.PLAIN, 17));
                input[i].setBackground(new Color(240, 240, 240));
                input[i].setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
                input[i].setForeground(Color.BLACK);
                gender[i].setBounds(960+(i*80), 320, 70, 40);
                gender[i].setFocusPainted(false);
                gender[i].setBackground(Color.LIGHT_GRAY);
                gender[i].setForeground(Color.BLACK);

                input[1].setVisible(false);
                radio.add(gender[i]);
                lect.add(gender[i]);
                lect.add(input[i]);
            }
            fm.setForeground(Color.BLACK);
            fm.setBackground(new Color(240, 240, 240));
            fm.setBounds(960, 130+(i * 95), 280, 40);
            fm.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
            fm.setFont(new Font("Monospace", Font.PLAIN, 17));

            txt[i].setBounds(960, 120+(i*85), 300, 40);
            txt[i].setFont(new Font("Monospace", Font.PLAIN, 18));
            txt[i].setForeground(Color.BLACK);
            lect.add(fm);
            lect.add(txt[i]);
        }
        int inc = 0;
        for (int i = 0; i < txt2.length; i++) {
            if (i<2){
                input2[i].setBounds(960, 150+(i*200), 280, 40);
                input2[i].setFont(new Font("Monospace", Font.PLAIN, 17));
                input2[i].setBackground(new Color(240, 240, 240));
                input2[i].setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
                input2[i].setForeground(Color.BLACK);
                dept.add(input2[i]);
            }
            txt2[i].setBounds(960, 120+(i*80)+inc, 300, 40);
            if (i ==1){
                txt2[i].setBounds(960, 120+(i*70)+inc, 300, 40);
            }
            txt2[i].setFont(new Font("Monospace", Font.PLAIN, 18));
            txt2[i].setForeground(Color.BLACK);
            dept.add(txt2[i]);
            inc+=20;
        }

        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit.setBounds(1160, 460, 80, 40);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("monospace", Font.BOLD, 17));
        submit.setFocusPainted(false);
        submit.setBackground(colors[0]);

        submit2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit2.setBounds(1160, 400, 80, 40);
        submit2.setForeground(Color.WHITE);
        submit2.setFont(new Font("monospace", Font.BOLD, 17));
        submit2.setFocusPainted(false);
        submit2.setBackground(colors[0]);

        submit4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit4.setForeground(Color.WHITE);
        submit4.setFont(new Font("monospace", Font.BOLD, 17));
        submit4.setFocusPainted(false);
        submit4.setBackground(colors[0]);
        for (int i = 0; i < tableHeader.length; i++) {
            tableHeaderText[i].setFont(new Font("Monospace", Font.BOLD, 28));
            tableHeaderText[i].setForeground(Color.WHITE);
            tableHeader[i].setBackground(new Color(90,50,255));
            tableHeaderText[6].setFont(new Font("Monospace", Font.BOLD, 28));
            tableHeaderText[6].setForeground(Color.WHITE);
        }

        for (int i = 1; i < tableHeader.length; i++) {
            tableHeader[i].setBounds(20, 65, 900, 50);
            tableHeaderText[i].setBounds(30, 65, 200, 40);

            if(i < 3){
                tablePanel[i].setBackground(Color.WHITE);
                tablePanel[i].setBounds(20, 120, 900, 400);
            }
        }
        for (int i = 0; i < 2; i++) {
            alert[i].setBounds(70, 5, 1200,50);
            alert[i].setBackground(Color.WHITE);
            alert[i].setVisible(false);

            bg[i+1].setBounds(950, 60, 300,60);
            bg[i+1].setBackground(new Color(90,50,255));

            bg[i].setBounds(950, 60, 300,60);
            bg[i].setBackground(new Color(90,50,255));

        }
        for (int i = 0; i < level.length; i++) {
            level[i].setBounds(960+(i*140), 250, 130, 30);
            if (i>=2)
                level[i].setBounds(960+((i-2)*140), 290, 130, 30);
            level[i].setFocusPainted(false);
            level[i].setBackground(new Color(0,0,0,0));
            lvl.add(level[i]);
            dept.add(level[i]);
        }
        tableHeaderText[0].setBounds(70, 210, 200, 40);
        tableHeader[0].setBounds(60, 210, 1160, 50);

        tableHeaderText[3].setBounds(30, 20, 400, 40);
        tableHeader[3].setBounds(20, 20, 1220, 50);

        tableHeaderText[6].setBounds(30, 20, 200, 40);
        tableHeader[5].setBounds(20, 20, 1220, 50);

        lectTable.repaint();
        lectTable.setRowHeight(30);

        lectTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        lectTable.getColumnModel().getColumn(1).setPreferredWidth(400);
        lectTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        lectTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        lectTable.getColumnModel().getColumn(4).setPreferredWidth(150);

        lectTable.setBackground(Color.WHITE);
        lectTable.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        lectTable.setFont(new Font("monospace", Font.PLAIN, 15));

        tablePanel[1].add(lectScroll);
        lect.add(tablePanel[1]);

        lect.add(selecOption);
        lect.add(alert[1]);
        lect.add(tableHeaderText[1]);
        lect.add(tableHeader[1]);
        lect.add(submit);
        lect.add(header);
        lect.add(bg[0]);

        deptTable.repaint();
        deptTable.setRowHeight(30);
        deptTable.getColumnModel().getColumn(1).setPreferredWidth(600);
        deptTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        deptTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        deptTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        deptTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        deptTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        deptTable.getColumnModel().getColumn(6).setPreferredWidth(200);
        deptTable.setBackground(Color.WHITE);
        deptTable.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        deptTable.setFont(new Font("monospace", Font.PLAIN, 15));

        tablePanel[2].add(deptScroll);
        dept.add(tablePanel[2]);
        dept.add(alert[0]);
        dept.add(tableHeaderText[2]);
        dept.add(tableHeader[2]);
        dept.add(submit2);
        dept.add(header2);
        dept.add(bg[1]);

        dashTable.setRowHeight(30);
        dashTable.repaint();
        dashTable.getColumn("Mobile").setWidth(6000);
        dashTable.setBackground(Color.WHITE);
        dashTable.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        dashTable.setFont(new Font("monospace", Font.PLAIN, 15));
        dashTable.setSize(tablePanel[0].getSize());
        tablePanel[0].setBackground(Color.WHITE);
        tablePanel[0].setBounds(60, 260, 1160, 400);
        tablePanel[0].add(dashScroll);
        dash.add(tablePanel[0]);
        dash.add(tableHeaderText[0]);
        dash.add(tableHeader[0]);
        for (int i = 0; i < 2; i++) {
            toggle[i].setBounds(20+(i*120), 75, 100, 40);
            toggle[i].setFont(new Font("monospace", Font.BOLD, 17));
            toggle[i].setName(allName[i]);
            toggle[i].setFocusPainted(false);
            toggle[i].setCursor(new Cursor(Cursor.HAND_CURSOR));

            toggle2[i].setBounds(20+(i*120), 75, 100, 40);
            toggle2[i].setFont(new Font("monospace", Font.BOLD, 17));
            toggle2[i].setName(allName[i]);
            toggle2[i].setFocusPainted(false);
            toggle2[i].setCursor(new Cursor(Cursor.HAND_CURSOR));

            allocateSelect[0].setBounds(30, 200, 250,40);
            allocateSelect[0].setBackground(Color.white);

            selectText[i].setBounds(30, 160+(i*80), 100,40);
            selectText[i].setForeground(Color.BLACK);
            selectText[i].setFont(new Font("monospace", Font.BOLD, 17));
            selectText[i+1].setFont(new Font("monospace", Font.BOLD, 17));
            selectText[i+1].setBounds(30, 160+(i*160), 100,40);

            input3[i].setBounds(30, 270+(i*80), 250,40);
            input3[i].setFont(new Font("monospace", Font.PLAIN, 17));

            submit3.setBounds(180, 270+(i*130), 100,40);
            submit3.setText("Allocate");
            submit3.setFont(new Font("monospace", Font.BOLD, 17));
            submit3.setBackground(colors[0]);
            submit3.setForeground(Color.white);
            submit3.setCursor(new Cursor(Cursor.HAND_CURSOR));
            submit3.setFocusPainted(false);
//            allocatePanel[1].setBackground(Color.BLACK);
            allocatePanel[1].add(toggle2[i]);
            allocatePanel[0].add(toggle[i]);
            allocatePanel[0].add(input3[i]);
            allocatePanel[0].add(allocateSelect[0]);
            allocatePanel[0].add(selectText[i]);
            allocatePanel[0].add(selectText[i+1]);
            allocatePanel[0].add(alert[2]);
        }

        border.setBackground(Color.white);
        border.setBounds(0, 110, 300, 400);

        for (int i = 0; i < resultView.length; i++) {
            resultView[i].setBackground(new Color(245, 245, 245));

            preview.setBounds(320, 80, 920,800);
            preview2.setBounds(390, 80, 850,800);

            preview.add(resultView[0], "manual");
            preview2.add(resultView[1], "random");
        }

        tableHeaderText[6].setBounds(30, 60, 300, 40);
        tableHeader[6].setBounds(20, 60, 900, 50);
        tablePanel[4].setBounds(20,120, 900, 500);
        facTable.setRowHeight(30);
        facTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        facTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        facTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        facTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        facTable.getColumnModel().getColumn(4).setPreferredWidth(200);
        facTable.setFont(new Font("monospace", Font.PLAIN, 15));
        tablePanel[4].add(facScroll);
        for (int i = 0; i < input4.length; i++) {
            input4[i].setBounds(960,160+(i*80), 280, 40);
            input4[i].setFont(new Font("Monospace", Font.PLAIN, 18));
            input4[i].setFont(new Font("Monospace", Font.PLAIN, 17));
            input4[i].setBackground(new Color(240, 240, 240));
            input4[i].setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
            input4[i].setForeground(Color.BLACK);

            submit4.setBounds(1160, 200+((i+1)*80), 80, 40);
            txt3[i].setBounds(960,120+(i*80), 280, 40);
            txt3[i].setFont(new Font("Monospace", Font.PLAIN, 18));

            txt3[2].setBounds(960,120+(2*80), 280, 40);
            txt3[2].setFont(new Font("Monospace", Font.PLAIN, 18));
            gender2[i].setBounds(960+(i*80), 310, 70, 40);
            gender2[i].setFocusPainted(false);
            gender2[i].setBackground(Color.white);
            gender2[i].setForeground(Color.BLACK);

            grp.add(gender2[i]);
            facilitators.add(gender2[i]);
            facilitators.add(txt3[i]);
            facilitators.add(txt3[i+1]);
            facilitators.add(submit4);
            facilitators.add(input4[i]);
        }

        facilitators.add(input4[0]);
        facilitators.add(header3);
        facilitators.add(bg[2]);
        facilitators.add(alert[3]);
        facilitators.add(tablePanel[4]);
        facilitators.add(tableHeaderText[6]);
        facilitators.add(tableHeader[6]);

        for (int i = 0; i < outPut.length; i++) {
            outPut[i].setBounds(20, 30+(i*60), 200, 40);
            outPut[i].setFont(new Font("monospace", Font.PLAIN, 23));
            outPutRand[i].setBounds(20, 30+(i*60), 200, 40);
            outPutRand[i].setFont(new Font("monospace", Font.PLAIN, 23));

            $_outPut_response_rand[i].setBounds(220, 30+(i*60), 400, 40);
            $_outPut_response_rand[i].setFont(new Font("monospace", Font.PLAIN, 27));
            $_outPut_response[i].setBounds(220, 30+(i*60), 400, 40);
            $_outPut_response[i].setFont(new Font("monospace", Font.PLAIN, 27));

            allocateSelect[0].setFont(new Font("monospace",Font.PLAIN ,17));
            resultView[0].add($_outPut_response[i]);
            resultView[0].add(outPut[i]);
            resultView[1].add($_outPut_response_rand[i]);
            resultView[1].add(outPutRand[i]);
        }

        alert[2].setBounds(10, 460, 300, 50);
        alert[2].setBackground(Color.WHITE);
        alert[2].setVisible(false);

        alert[3].setBounds(950, 0,300,50);
        alert[3].setBackground(Color.WHITE);

        manual.getColumnModel().getColumn(0).setPreferredWidth(40);
        manual.getColumnModel().getColumn(1).setPreferredWidth(600);
        manual.getColumnModel().getColumn(2).setPreferredWidth(200);
        manual.getColumnModel().getColumn(3).setPreferredWidth(200);
        manual.setRowHeight(30);
        manual.setFont(new Font("monospace", Font.PLAIN, 17));

        $_resultView_table_panel[0].add(maual_scroll);
        $_resultView_table_panel[0].setBounds(0, 275, 800, 340);
        $_resultView_table_panel[0].setBackground(Color.BLACK);
        resultView[0].add($_resultView_table_panel[0]);

        allocatePanel[0].add(submit3);
        allocatePanel[0].add(tableHeaderText[3]);
        allocatePanel[0].add(tableHeader[3]);
        allocatePanel[0].add(border);
        allocatePanel[0].add(preview);
        allocatePanel[0].setBackground(Color.WHITE);

        tableHeader[4].setBounds(20, 20, 1220, 50);
        tableHeaderText[4].setForeground(Color.WHITE);
        tableHeaderText[4].setBounds(30, 20, 300, 40);

        list = new JList(item);
        JScrollPane listScroll = new JScrollPane(list);
        listScroll.setBorder(BorderFactory.createLineBorder(new Color(90,50,255), 2, true));
        listScroll.setBounds(20, 310, 350, 380);


        $_resultView_table_panel[1].add(rand_scroll);
        $_resultView_table_panel[1].setBounds(0, 275, 800, 340);
        $_resultView_table_panel[1].setBackground(Color.BLACK);
        rand.getColumnModel().getColumn(0).setPreferredWidth(40);
        rand.getColumnModel().getColumn(1).setPreferredWidth(600);
        rand.getColumnModel().getColumn(2).setPreferredWidth(200);
        rand.getColumnModel().getColumn(3).setPreferredWidth(200);
        rand.setRowHeight(30);
        rand.setFont(new Font("monospace", Font.PLAIN, 17));


        resultView[1].add($_resultView_table_panel[1]);
        list.setCursor(new Cursor(Cursor.HAND_CURSOR));
        list.setFont(new Font("monospace", Font.PLAIN, 20));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bg[4].setBackground(new Color(90,50,255));
        bg[4].setBounds(20, 250, 350, 60);
        header4.setForeground(Color.WHITE);
        header4.setBounds(30, 250, 250, 50);
        header4.setFont(new Font("Monospace", Font.BOLD, 25));
        submit5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit5.setBounds(130, 200, 100, 30);
        submit5.setForeground(Color.WHITE);
        submit5.setFont(new Font("monospace", Font.BOLD, 17));
        submit5.setFocusPainted(false);
        submit5.setBackground(colors[0]);
        num.setFont(new Font("monospace", Font.BOLD, 14));
        num.setBounds(30, 150, 200, 30);
        allocatePanel[1].add(num);


        allocatePanel[1].add(submit5);
        allocatePanel[1].add(header4);
        allocatePanel[1].add(bg[4]);
        allocatePanel[1].add(listScroll);
        allocatePanel[1].add(preview2);
        allocatePanel[1].add(tableHeaderText[4]);
        allocatePanel[1].add(tableHeader[4]);
        allocatePanel[1].setBackground(Color.WHITE);

        allocate.add(allocatePanel[0], String.valueOf(toggle[0].getName()));
        allocate.add(allocatePanel[1], String.valueOf(toggle[1].getName()));

        allocate.add(allocatePanel[0], String.valueOf(toggle2[0].getName()));
        allocate.add(allocatePanel[1], String.valueOf(toggle2[1].getName()));
        panel.add(container);

//        frame.setUndecorated(true);
//        frame.setIconImage(buff);
        frame.add(container);
        frame.add(title);
        frame.add(nav);
        frame.add(sideBar);
        frame.add(panel);
        frame.setSize(1500,800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Active Method
     */
    public void active(JButton button, boolean bool) {
        for(JButton b: btn){
            if (b == button && bool){
                button.setBackground(new Color(255, 255, 255));
                button.setForeground(Color.BLACK);
                for (JPanel a: alert) {
                    a.setVisible(false);
                }
            }else{
                b.setBackground(new Color(90,90,255));
                b.setForeground(Color.WHITE);
            }
        }
    }
    public void activeToggle(JButton button, boolean bool) {
        for(JButton b: toggle){
            if (b == button && bool){
                b.setBackground(new Color(90,90,255));
                b.setForeground(Color.WHITE);

                for (JPanel a: alert) {
                    a.setVisible(false);
                }
            }else{
                b.setBackground(new Color(255, 255, 255));
                b.setForeground(Color.BLACK);
            }
        }
    }
    public void activeToggle2(JButton button, boolean bool) {
        for(JButton b: toggle2){
            if (b == button && bool){
                b.setBackground(new Color(90,90,255));
                b.setForeground(Color.WHITE);

                for (JPanel a: alert) {
                    a.setVisible(false);
                }
            }else{
                b.setBackground(new Color(255, 255, 255));
                b.setForeground(Color.BLACK);
            }
        }
    }
    /**
     * Sidebar Method
     */
    void setSideBar(){
        for (int i = 0; i < btn.length; i++) {
            btn[i].setBackground(new Color(130,120,200));
            btn[i].setBounds(5, 60+(i*70), 190, 60);
            btn[i].setFont(new Font("Monospace", Font.BOLD, 18));
            btn[i].setForeground(Color.WHITE);
            btn[i].setFocusPainted(false);
            btn[i].setBorderPainted(true);
            btn[i].setBorder(BorderFactory.createLineBorder(new Color(130,100,255), 3));
            btn[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btn[i].setName(btnName[i]);
            frame.add(btn[i]);
        }
        active(btn[0], true);
        title.setBackground(new Color(90,50,255));
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Monospace", Font.BOLD, 25));
        title.setBounds(10,5,1050,20);

        sideBar.setBackground(new Color(90,50,255));
        sideBar.setBounds(0,41,200, 940);
        panel.setBounds(0,0,1300,650);
        panel.setBackground(Color.WHITE);
        nav.setBounds(0,0,1500, 40);
        nav.setBackground(new Color(90,50,255));
    }

    /**
     * Alert Message Method
     */
    void alertMessage(int p,int i, String msg){
        message = msg;
        cl = i;
        alert[p].setVisible(true);
    }

}