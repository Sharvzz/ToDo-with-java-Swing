package com.todo.gui;
import com.todo.dao.ToDoAppDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.chrono.JapaneseChronology;
import java.util.List;
public class ToDoAppGUI  extends JFrame {
    private ToDoAppDAO todoDAO;
    private JTable todoTable;
    private DefaultTableModel tableModel;
    private JTextField titleField;
    private JTextArea descriptionArea; 
    private JCheckBox completedCheckBox;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JComboBox<String> filterComboBox;

    public ToDoAppGUI(){
        this.todoDAO = new ToDoAppDAO();
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents(){
        setTitle("To-Do List Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Title", "Description", "Completed", "Created At", "Updated At"};

        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
        }
        };

        todoTable = new JTable(tableModel);
        todoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todoTable.getSelectionModel().addListSelectionListener(
            (e) -> {
                if(!e.getValueIsAdjusting()){
                    // loadselectedtodo();
                }
            } 
        );

        titleField = new JTextField(20);
        descriptionArea = new JTextArea(3,20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        completedCheckBox = new JCheckBox("Completed");

        addButton = new JButton("Add Todo");
        updateButton = new JButton("Update Todo");
        deleteButton = new JButton("Delete Todo");
        refreshButton = new JButton("Refresh Todo");

        String[] filterOptions = {"All", "Completed", "Pending"};
        filterComboBox =  new JComboBox<>(filterOptions);
        filterComboBox.addActionListener((e) ->{
            // filterTodos();
        });
    }

    private void setupLayout(){
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(titleField, gbc);    
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(new JScrollPane(descriptionArea), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(completedCheckBox, gbc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Filter:"));
        filterPanel.add(filterComboBox);

        JPanel northJPanel = new JPanel(new BorderLayout());
        
        add(inputPanel, BorderLayout.NORTH);

        


    }
}
