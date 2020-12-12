package GraphicInterface;

import Entities.Pokemon;
import Entities.Trainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainerNameActionListener implements ActionListener {
    private Trainer trainer;
    private JFrame window;

    public TrainerNameActionListener(Trainer trainer, JFrame window) {
        this.window = window;
        this.trainer = trainer;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        TrainerDetailsWindow details = new TrainerDetailsWindow(this.trainer, window);
    }
}
