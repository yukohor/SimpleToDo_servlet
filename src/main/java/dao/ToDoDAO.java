package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Todo;

public class ToDoDAO {
    
    private final String JDBC_URL = "jdbc:mysql://localhost/simpletodos?useSSL=false&allowPublicKeyRetrieval=true";
    private final String DB_USER = "root";
    private final String DB_PASS = "52481001uk";
    
    public List<Todo> findAll(){
        List<Todo> todoList = new ArrayList<>();
        
        
        try {
            // 1. ドライバのクラスをJava上で読み込む

            Class.forName("com.mysql.cj.jdbc.Driver");
    }catch (ClassNotFoundException e) {
        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
    }
        
        try(Connection con = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
            
            //SELECT文の準備
            String sql = "SELECT ID,TODOS,CREATED_AT FROM SIMPLETODOS.TODOS ORDER BY ID DESC";
            PreparedStatement pStmt = con.prepareStatement(sql);
            
            //SELECT文実行
            ResultSet rs = pStmt.executeQuery();
            
            //SELECT文の結果をArraylistに格納
                    
            
            while (rs.next()) {
                int id = rs.getInt("ID");
                String todos = rs.getString("TODOS");
                Timestamp created_at = rs.getTimestamp("CREATED_AT");
                Todo todo = new Todo(id,todos,created_at);
                todoList.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return todoList;
}
   public boolean create(Todo todo) {
       //JDBCドライバの読み込み
	   try {
   	Class.forName("com.mysql.cj.jdbc.Driver");
   	
   }catch (ClassNotFoundException e) {
   	throw new IllegalStateException("JDBCドライバを読み込めませんでした");
  }
   //DB接続
   try(
  Connection con = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
   	
  	//INSERT文の準備
   	String sql = "INSERT INTO TODOS(TODOS) VALUES(?,?)";
   	PreparedStatement pStmt  = con.prepareStatement(sql);
   	
   //INSERT文の？に使用する値を設定してSQL文完成
   	pStmt.setString(1, todo.getTodos());
   	
   	//INSET文を実行　resultに追加された行数が代入される
   	int result = pStmt.executeUpdate();
   	if(result != 1) {
   		return false;
   	}
   }catch (SQLException e) {
	   e.printStackTrace();
	   return false;
   }
   return true;
    }
}