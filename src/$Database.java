import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class $Database extends Dec{
    Statement query;
    String srcTxt = "";
    Connection con = DriverManager.getConnection(url, uname, psw);

    protected $Database() throws ParseException, SQLException {}

    /**
     * Database Connection
     * */
    void dbConnect(){
        try{
            query = con.createStatement();
        }catch (Exception ex){ex.printStackTrace();}
    }
    /**
     * Database Action
     **/
    boolean dbAction(String q){
        boolean bool = false;
        try{
            if (query.executeUpdate(q) == 1){
                bool =  true;
            }else
                bool = false;
        }catch (Exception e){e.printStackTrace();}
        return bool;
    }
}
