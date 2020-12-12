package GraphicInterface;

import Entities.Trainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TrainerListJPanel {
    private JFrame window;

    public TrainerListJPanel(JFrame window){
        this.window = window;
    }
    public JScrollPane createScrollPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        panel = trainerNamesButton(panel);

        JScrollPane jsp = new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBounds(370, 70, 145, 200);
        jsp.getVerticalScrollBar().setUnitIncrement(16);

        return jsp;
    }

    public JPanel trainerNamesButton(JPanel panel){
        Trainer trainerRead = new Trainer();
        ArrayList<Trainer> trainers = trainerRead.read();

        for(Trainer trainer : trainers){
            panel.add(createButton(trainer));
        }
        return panel;
    }
    public JButton createButton(Trainer trainer){
        JButton newButton = new JButton(trainer.getName());
        newButton.setForeground(Color.WHITE);
        newButton.setBackground(Color.GRAY);
        ActionListener trainerName = new TrainerNameActionListener(trainer, this.window);
        newButton.addActionListener(trainerName);

        return newButton;
    }
}
