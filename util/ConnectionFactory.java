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
    
    public static final String DRIVER = "com.mysql.jdbc.Driver"; /*Indica que usarei o banco com mysql, através da depedência jdbc (Driver)*/
    public static final String URL = "jdbc:mysql://localhost:3306/agendaAppJosi"; /*Indica o caminho até meu banco de dados*/
    public static final String USER = "root";
    public static final String PASS = "";
    
    /*Método para criar conecção com o banco*/
    
    public static Connection getConnection(){ /* método devolve uma conexão*/
        try{
            Class.forName(DRIVER); /*Indica o driver o qual se usa para conexão*/
            return DriverManager.getConnection (URL, USER, PASS); /*Parâmetros para a conexão*/
            
        } catch (Exception ex){
            throw new RuntimeException ("Erro na Conexão com o banco de dados", ex); /*Em caso de erro, essa mensagem será exibida*/
        }
    }
    public static void closeConnection (Connection connection){
        try {
            if (connection != null){
                connection.close();
                }
            } catch (Exception ex){
                throw new RuntimeException ("Erro ao fechar a conexão com o banco de dados",ex );
                }
        }
    
    public static void closeConnection (Connection connection, PreparedStatement statement){
        try {
            if (connection != null){ /*Se, houver uma conexão estabelecida, irá ser fechada*/
                connection.close();
            }
            if ( statement != null){ /*encerra a conexão do statement*/
                statement.close ();
            }
        } catch (Exception ex){
            throw new RuntimeException ("Erro ao fechar conexão com o banco",ex);
        }
      }
   }
        
   


      
  
