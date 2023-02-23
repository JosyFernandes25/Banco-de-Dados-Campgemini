/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/*Classe de controle de query em banco: insert, update, select e delete*/
public class TaskController {
    
    public void save (Task task){
        String sql = "INSERT INTO tasks (idProject," 
                + "name,"
                + " description,"
                + "completed," 
                + "notes," 
                + "deadline," 
                + "createdAt," 
                + "updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
           connection = ConnectionFactory.getConnection();
           statement = connection.prepareStatement(sql);
           statement.setInt(1,task.getIdProject());
           statement.setString(2, task.getName());
           statement.setString(3, task.getDescription());
           statement.setBoolean(4, task.isIsCompleted());
           statement.setString(5, task.getNotes());
           statement.setDate(6, new Date(task.getCreatedAt().getTime())); /*cria-se uma nova data, importando a bliblioteca java.sql*/
           statement.setDate(7, new Date(task.getCreatedAt().getTime()));/*getTime cria data em formato long*/
           statement.setDate(8, new Date(task.getCreatedAt().getTime()));
           /*statement necessecita do pacote java.sql para criação de data*/
           statement.execute();
           
        } catch (Exception ex){
            throw new RuntimeException ("Erro ao salvar a tarefa " + ex.getMessage(), ex);
            
        } finally {
            ConnectionFactory.closeConnection (connection, statement);
        }     
    }
    public void update (Task task){
        String sql = "UPDATE tasks SET " 
                + "idProject =?,"
                + "name = ?,"
                + "description = ?,"
                + " notes = ?,"
                + "completed = ?,"
                + "deadline = ?, "
                + "createdAt = ?,"
                + "updatedAt= ?,"
                + "WHERE id = ?"; /* baseado no id da tarefa ( atualização)*/
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            /*Conectandp ao banco*/
            connection = ConnectionFactory.getConnection();
            
            /*Preparando a Query*/
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isIsCompleted()); /*Modo de setar dados boolean é isIsCompleted*/
            statement.setDate(6, new Date (task.getDeadline().getTime()));
            statement.setDate(7, new Date (task.getCreatedAt().getTime()));
            statement.setDate(8, new Date ( task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            /*Executando a query*/
            statement.execute();
        } catch (Exception ex){
            throw new RuntimeException ("Erro ao atualizar a tarefa" + ex.getMessage(),ex);
        }      
    }
    public void removeById (int taskId){ /*remove por id*/
        String sql = "DELETE FROM tasks WHERE id = ? "; /*monte query mysql*/
        
        Connection connection = null;
        PreparedStatement statement= null; /*prepara o comando sql para ser usado na conexao*/
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql); /*prepara a conexão com a query sql*/
            statement.setInt(1, taskId); /*coloca número no ? ( que é o primeiro parametro) segundo o Id informado */
            statement.execute(); /*executa*/
        } catch ( Exception ex){
            throw new RuntimeException("Erro ao deletar a tarefa" + ex.getMessage(),ex);
        } finally{ /*Finaliza conexão*/
            ConnectionFactory.closeConnection(connection, statement);     
        }
            
    }
    public List<Task> getAll (int idProject) throws SQLException{
        String sql = "SELECT * FROM tasks WHERE idProject = ? ";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null; /* Traz resultados vindos do banco de dados.*/
        
         List<Task> tasks = new ArrayList<Task>(); /*cria uma lista*/
         
         try {
             connection = ConnectionFactory.getConnection();
             statement = connection.prepareStatement(sql);
             statement.setInt(1, idProject);
             resultSet = statement.executeQuery(); /*execute query traz como resposta um result set*/
             
             while (resultSet.next()) { /*indica que enquanto tiver um proximo, os valores serão pegos. Pega de um a um*/
                 Task task = new Task (); /*crie o objeto lista*/
                 /*para setar valores na tabela criada:*/
                 task.setId( resultSet.getInt("id")); /*nome tabela, tipo de dado setado, buscado pelo result set
                 pesquisado em seu tipo ( int, string), e pelo nome da coluna que se encontra*/
                 task.setIdProject(resultSet.getInt("idProject"));
                 task.setName(resultSet.getString("name"));
                 task.setDescription(resultSet.getString("description"));
                 task.setNotes(resultSet.getString("notes"));
                 task.setIsCompleted(resultSet.getBoolean("completed"));
                 task.setDeadline(resultSet.getDate("deadline"));
                 task.setCreatedAt(resultSet.getDate("createdAt"));
                 task.setUpdatedAt(resultSet.getDate("updatedAt"));
                 
                 tasks.add(task); /*adiciona a tarefa dentro da lista, indicando qual a tarefa a colocar dentro do metodo
                 que foi setado as informações*/
                 
             }
         } catch ( Exception ex) {
             throw new RuntimeException("Erro ao inserir a tarefa" + ex.getMessage(),ex); 
             
         } finally {
             ConnectionFactory.closeConnection(connection, statement);
             if ( resultSet != null){
                 resultSet.close(); /*fecha a conexão do result set*/
             }
         }
         
        return tasks;      
    }
}
