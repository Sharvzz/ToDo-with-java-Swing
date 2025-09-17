package com.todo;
import com.todo.gui.ToDoAppGUI;
import com.todo.util.DatabaseConnection;
import com.todo.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main{
    public static void main(String[] args){
        DatabaseConnection db_Connection = new DatabaseConnection();
        try{
            Connection cn =db_Connection.getDBConnection();
            System.out.println("Connection established");
        }
        catch(SQLException e){
            System.out.println("Conection Failed");
            System.exit(1);
        }
        try{
            
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
            System.err.println("Could not set Look and Feel"+ e.getMessage());
        }
        SwingUtilities.invokeLater(
            () -> {
                try{
                    new ToDoAppGUI().setVisible(true);
                }
                catch(Exception e){
                    System.err.println("Could not launch GUI"+ e.getMessage());
                }
            }
        );
    }
}