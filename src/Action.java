import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.util.Random;

public class Action {

    /**
     * Generate Special Key
     * */
    String ID(){
        Random rand = new Random();
        int n1 = 10+rand.nextInt(98-10);
        int n2 = 100 + rand.nextInt(999-100);
        int low, up;
        String lower = "", upper = "";
        for (int i = 0, j = 0; i < 2 || j < 3; i++, j++) {
            if (i < 2){
                low = 97+rand.nextInt(121-97);
                lower+=(char)low;
            }
            up = 66+rand.nextInt(90-66);
            upper+=(char)up;
        }

        String id = n1+upper+n2+lower;
        return id;
    }

    /**
     * Generate random Key()
     * */
    int[] $_Key_Gen(int Dnum, int count){
        int[] key = new int[Dnum];
        Random rand = new Random();
        boolean generate;
        for (int i = 0; i < key.length; i++) {
            generate = true;
            int num = 0;
            while (generate){
                num = rand.nextInt(count);
                generate = false;
                for (int k = 0; k < key.length-1; k++) {
                    if (key[k] == num){
                        generate = true;
                    }
                }
            }
            key[i] = num;
        }
        return key;
    }

    /**
     * Generate Lecturers ID
     * */
    String lecturerID(){
        String lectId = "";
        try {
            FileInputStream file = new FileInputStream("src/lectId.eed");
            FileWriter write = new FileWriter("src/lectId.eed", true);
            int r = file.read();
            String check = "";

            boolean lectState = true;
            while (lectState){
                lectState = false;
                lectId = "EED-"+ID();
                while (r != -1){
                    check+=(char)r;
                    if (r == 32){
                        if (check == lectId){
                            lectState = true;
                        }
                        check = "";
                    }
                    r = file.read();
                }
            }
            write.append(lectId+"\n");
            write.close();
            file.close();
        }catch (Exception e){e.printStackTrace();}

        return lectId;
    }

    /**
     * Generate Departments ID
     * */
    String departmentID(){
        String deptId = "";
        try {
            FileInputStream file = new FileInputStream("src/deptId.eed");
            FileWriter write = new FileWriter("src/deptId.eed", true);
            int r = file.read();
            String check = "";

            boolean lectState = true;
            while (lectState){
                lectState = false;
                deptId = "SCH-"+ID();
                while (r != -1){
                    check+=(char)r;
                    if (r == 32){
                        if (check == deptId){
                            lectState = true;
                        }
                        check = "";
                    }
                    r = file.read();
                }
            }
            write.append(deptId+"\n");
            write.close();
            file.close();
        }catch (Exception e){e.printStackTrace();}

        return deptId;
    }
//    /**
//     * Search For Lecturers
//     * */

    /**
     * Clear Table
     * */
    void clearTable(Object[][] tbl){
        for (int k = 0; k < tbl.length; k++) {
            for (int l = 0; l < tbl[k].length; l++) {
                tbl[k][l] = null;
            }
        }

    }

}

