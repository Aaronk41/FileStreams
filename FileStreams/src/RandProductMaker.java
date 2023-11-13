import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class RandProductMaker {
    private JFrame frame;
    private JTextField nameField, descriptionField, idField, costField, countField;
    private JButton addButton;
    private RandomAccessFile randomAccessFile;
    private int recordCount = 0;

    public RandProductMaker() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Random Product Maker");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(6, 2, 10, 10));

        frame.getContentPane().add(new JLabel("Name:"));
        nameField = new JTextField();
        frame.getContentPane().add(nameField);

        frame.getContentPane().add(new JLabel("Description:"));
        descriptionField = new JTextField();
        frame.getContentPane().add(descriptionField);

        frame.getContentPane().add(new JLabel("ID:"));
        idField = new JTextField();
        frame.getContentPane().add(idField);

        frame.getContentPane().add(new JLabel("Cost:"));
        costField = new JTextField();
        frame.getContentPane().add(costField);

        frame.getContentPane().add(new JLabel("Record Count:"));
        countField = new JTextField();
        countField.setEditable(false);
        frame.getContentPane().add(countField);

        addButton = new JButton("Add");
        frame.getContentPane().add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecord();
            }
        });

        frame.setVisible(true);
    }

    private void addRecord() {
        try {
            String name = nameField.getText().trim();
            String description = descriptionField.getText().trim();
            String id = idField.getText().trim();
            double cost = Double.parseDouble(costField.getText().trim());

            // Validate the inputs here

            Product product = new Product(name, description, id, cost);

            randomAccessFile = new RandomAccessFile("productData.dat", "rw");
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.writeUTF(product.formatForRandomAccess());

            recordCount++;
            countField.setText(String.valueOf(recordCount));

            clearFields();

            randomAccessFile.close();
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        nameField.setText("");
        descriptionField.setText("");
        idField.setText("");
        costField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new RandProductMaker();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

