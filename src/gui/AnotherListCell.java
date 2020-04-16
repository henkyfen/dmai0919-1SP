package gui;

import model.Renderable;

import javax.swing.*;
import java.awt.*;

public class AnotherListCell implements ListCellRenderer<Renderable> {

    private DefaultListCellRenderer dfcr;

    /**
     * This is a renderer which creates a title for use in tables / lists
     * @return the rendered title
     */
    public Component getListCellRendererComponent(JList<? extends Renderable>
                                                          listProducts, Renderable render, int index, boolean isSelected, boolean cellHasFocus) {
        dfcr = new DefaultListCellRenderer();
        String item = "***** " + render.getTitle() + " *****";
        return dfcr.getListCellRendererComponent(listProducts, item, index, isSelected, cellHasFocus);
    }
}
