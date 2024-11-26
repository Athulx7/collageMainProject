package database_AL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;


public class DBConnectionAL {
    private ResultSetMetaData rsm=null; 
    private Connection con=null;
    private Vector rowData=null;
    private Statement st=null;
    private ResultSet rs=null;
    public DBConnectionAL(){
        try
        {
            if(con==null)
            {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection ("jdbc:mysql://localhost:3306/instant_fuel","root","");
                st= con.createStatement();
            
            } 
        }        
        catch(Exception er)
        {
            System.out.println("DB Access failed.."+er);
        }
    }
   
    public int putData(String qry){
       try{
           return st.executeUpdate(qry);
       }catch(Exception e){
           System.out.println(" qry Access failed.."+e);
       }
       return 0;
    }
    
    public Vector getData(String qry){
        try{            
            rowData=new Vector();
            rs=st.executeQuery(qry);
            rsm=rs.getMetaData();
            int colCount=rsm.getColumnCount();//meta data concept is used for finding number of coloms using getColumnCount() 
            while(rs.next()){
                Vector colVector=new Vector();
                for(int i=0;i<colCount;i++){
                    colVector.add(rs.getString((i+1)));
                }
                rowData.add(colVector);
            }
            return rowData;
        }catch(Exception e){
            System.out.println("Access failed..");
        }
        return rowData;
    }
}
