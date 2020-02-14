package com.java24hours;

import com.sun.jmx.mbeanserver.JmxMBeanServer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JMenuBar;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyleConstants;

public class Main {

    private JFileChooser f= new JFileChooser();
    private final String NAME="Новый файл";
    private JTabbedPane tabs = new JTabbedPane();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
	public Main() {

        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("Файл");
        JMenu mem= new JMenu("Темы");
        JMenu text= new JMenu("шрифты");

            JMenuItem newFile = new JMenuItem("создать файл");
            JMenuItem openFile = new JMenuItem("открыть файл");
            JMenuItem saveFile = new JMenuItem("сохранить файл");

    JMenuItem black = new JMenuItem("Тёмная тема");

            JMenuItem usually=new JMenuItem("Обычный");
            JMenuItem tolst= new JMenuItem("Полужирный");
            JMenuItem chert= new JMenuItem("курсив");

                    file.add(newFile);
                    file.add(openFile);
                    file.add(saveFile);

        text.add(usually);
        text.add(tolst);
        text.add(chert);

                mem.add(black);

                menu.add(file);
                menu.add(mem);
                menu.add(text);

        JFrame window = new JFrame("NotePad");
        window.setSize(800, 600);
        window.setJMenuBar(menu);
        window.add(tabs);

    window.setResizable(true);
    window.setLocationRelativeTo(null);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setVisible(true);


        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextArea text=new JTextArea();

                scroll scroll= new scroll(text, false, "");
                tabs.add(NAME, scroll);
            }
        });

        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                scroll text = (scroll) tabs.getSelectedComponent();
                String output = text.getText();



                if(tabs.countComponents()!=0) {
                    if (text.isOpened()) {
                        try {
                            FileOutputStream writer = new FileOutputStream(text.getPath());
                            writer.write(output.getBytes());
                        } catch (IOException eq) {
                            eq.printStackTrace();
                        }
                    }else {
                        f.showSaveDialog(null);
                        File file = f.getSelectedFile();


                        try {
                            FileOutputStream writer = new FileOutputStream(file);
                            writer.write(output.getBytes());
                        } catch (IOException eq) {
                            eq.printStackTrace();
                        }
                    }
                }

            }
        });


        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                f.showOpenDialog(null);
                File file = f.getSelectedFile();

                    String input = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

                    JTextArea text =new JTextArea(input);

                    scroll scroll= new scroll(text, true, file.getAbsolutePath());
                    tabs.addTab(file.getAbsolutePath(), scroll);
                }catch (IOException ec){
                    ec.printStackTrace();
                }
            }
        });


        black.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               window.getContentPane().setBackground(Color.BLACK);

            }
        });


                      usually.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Font font = new Font("Verdana", Font.PLAIN, 11);
                    }
                });

        tolst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font font = new Font("Verdana", Font.BOLD, 15);
            }
        });


                    chert.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                        }
                    });


    }
}