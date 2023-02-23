/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/*Conecta ao banco de dados*/

public class ConnectionFactory {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver"; /*Indica que usarei o banco com mysql, atrav�s da deped�ncia jdbc (Driver)*/
    public static final String URL = "jdbc:mysql://localhost:3306/agendaAppJosi"; /*Indica o caminho at� meu banco de dados*/
    public static final String USER = "root";
    public static final String PASS = "";
    
    /*M�todo para criar conec��o com o banco*/
    
    public static Connection getConnection(){ /* m�todo devolve uma conex�o*/
        try{
            Class.forName(DRIVER); /*Indica o driver o qual se usa para conex�o*/
            return DriverManager.getConnection (URL, USER, PASS); /*Par�metros para a conex�o*/
            
        } catch (Exception ex){
            throw new RuntimeException ("Erro na Conex�o com o banco de dados", ex); /*Em caso de erro, essa mensagem ser� exibida*/
        }
    }
    public static void closeConnection (Connection connection){
        try {
            if (connection != null){
                connection.close();
                }
            } catch (Exception ex){
                throw new RuntimeException ("Erro ao fechar a conex�o com o banco de dados",ex );
                }
        }
    
    public static void closeConnection (Connection connection, PreparedStatement statement){
        try {
            if (connection != null){ /*Se, houver uma conex�o estabelecida, ir� ser fechada*/
                connection.close();
            }
            if ( statement != null){ /*encerra a conex�o do statement*/
                statement.close ();
            }
        } catch (Exception ex){
            throw new RuntimeException ("Erro ao fechar conex�o com o banco",ex);
        }
      }
   }
        
   


      
  
