package dbSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectSample01{
    public static void main(String[] args) {
        Connection con =null;
        Statement stmt= null;
        ResultSet rs = null;
        try {
            //1.ドライバークラスをJavaに読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //2.DBと接続
             con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "Qwerasdy@7601"
                    );
             
            //3.DBとやり取りする窓口
             stmt = con.createStatement();
             
            //4.serect 文の実行と結果を格納、代入
             System.out.println("検索キーワードを入力<");
             //String input =keyIn();
             
             //String sql ="select*from country where Name ='"+input+"'";
             
             String sql = "SELECT * FROM country LIMIT 50";
             rs = stmt.executeQuery(sql);
             
            //6.結果を表示
             while( rs.next() ){
                 // Name列の値を取得
                 String name = rs.getString("Name");
                 // 取得した値を表示
                 System.out.println(name);
             }
                } catch (ClassNotFoundException e) {
                    System.out.println("jdbcドライバのロードに失敗");
                    e.printStackTrace();
                } catch (SQLException e) {
                    System.out.println("データベースに異常発生した");
                    e.printStackTrace();
            
            //7.接続を閉じる
        }finally{
           
            if(stmt != null) {
                
                try {
                    stmt.close();
                }catch(SQLException e) {
                    System.err.println("Statementを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
                
            } if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("データベース切断時にエラーが発生");
                    e.printStackTrace();
                }
        }
            if( rs != null ){
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("ResultSetを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
       
          
           
            }
    }
   }
   
}
