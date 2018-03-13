package com.dekeeu.lab1;

import com.dekeeu.lab1.UI.LaboratoriesUI;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        LaboratoriesUI view  = new LaboratoriesUI();

        try {
            view.run();
        } catch (IOException e) {
            e.printStackTrace(); // this should have been be cought
        }
    }
}
