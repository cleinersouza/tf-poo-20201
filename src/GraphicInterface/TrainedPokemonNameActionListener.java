package GraphicInterface;

import Entities.TrainedPokemon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainedPokemonNameActionListener implements ActionListener {
    private TrainedPokemon trainedPokemon;
    private JFrame window;

    public TrainedPokemonNameActionListener(TrainedPokemon trainedPokemon, JFrame window) {
        this.window = window;
        this.trainedPokemon = trainedPokemon;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        TrainedPokemonDetailsWindow details = new TrainedPokemonDetailsWindow(this.trainedPokemon, window);
    }
}
