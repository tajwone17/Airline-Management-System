package Airline_Management_System;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AirlineManagementSystem {
    private JFrame mainFrame;
    private ArrayList<Flight> flights;

    public AirlineManagementSystem() {
        mainFrame = new JFrame("Airline Management System");
        mainFrame.setSize(400, 400);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        flights = new ArrayList<>();

        JButton addFlightButton = new JButton("Add Flight");
        addFlightButton.setBounds(125, 30, 150, 50);
        addFlightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                mainFrame.setVisible(false);
                openAddFlightFrame();
            }
        });

        JButton viewFlightsButton = new JButton("View Flights");
        viewFlightsButton.setBounds(125, 150, 150, 50);
        viewFlightsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                openViewFlightsFrame();
            }
        });

        JButton searchFlightButton = new JButton("Search Flights");
        searchFlightButton.setBounds(125, 270, 150, 50);
        searchFlightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                openSearchFlightsFrame();
            }
        });

        mainFrame.setLayout(null);
        mainFrame.add(addFlightButton);
        mainFrame.add(viewFlightsButton);
        mainFrame.add(searchFlightButton);

        mainFrame.setVisible(true);
    }

    private void openAddFlightFrame() {
        JFrame addFlightFrame = new JFrame("Add Flight");
        addFlightFrame.setSize(300, 200);

        JTextField flightNumberField = new JTextField(10);
        JTextField originField = new JTextField(10);
        JTextField destinationField = new JTextField(10);
        JTextField departureTimeField = new JTextField(10);
        JTextField arrivalTimeField = new JTextField(10);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String flightNumber = flightNumberField.getText();
                String origin = originField.getText();
                String destination = destinationField.getText();
                String departureTime = departureTimeField.getText();
                String arrivalTime = arrivalTimeField.getText();

                Flight flight = new Flight(flightNumber, origin, destination, departureTime, arrivalTime);
                flights.add(flight);
                JOptionPane.showMessageDialog(addFlightFrame, "Flight added successfully!");
                addFlightFrame.dispose(); // Close the frame after adding the flight
                mainFrame.setVisible(true);
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFlightFrame.dispose();
                mainFrame.setVisible(true);
            }
        });

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Flight Number:"));
        panel.add(flightNumberField);
        panel.add(new JLabel("Origin:"));
        panel.add(originField);
        panel.add(new JLabel("Destination:"));
        panel.add(destinationField);
        panel.add(new JLabel("Departure Time:"));
        panel.add(departureTimeField);
        panel.add(new JLabel("Arrival Time:"));
        panel.add(arrivalTimeField);
        panel.add(addButton);
        panel.add(backButton);

        addFlightFrame.add(panel);

        addFlightFrame.setVisible(true);
    }

    private void openViewFlightsFrame() {
        JFrame viewFlightsFrame = new JFrame("View Flights");
        viewFlightsFrame.setSize(400, 300);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        for (Flight flight : flights) {
            textArea.append("Flight Number: " + flight.getFlightNumber() + "\n");
            textArea.append("Origin: " + flight.getOrigin() + "\n");
            textArea.append("Destination: " + flight.getDestination() + "\n");
            textArea.append("Departure Time: " + flight.getDepartureTime() + "\n");
            textArea.append("Arrival Time: " + flight.getArrivalTime() + "\n\n");
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewFlightsFrame.dispose();
                mainFrame.setVisible(true);
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        viewFlightsFrame.add(panel);
        viewFlightsFrame.setVisible(true);
    }

    private void openSearchFlightsFrame() {
        JFrame searchFlightsFrame = new JFrame("Search Flights");
        searchFlightsFrame.setSize(300, 200);

        JLabel searchLabel = new JLabel("Enter flight number:");
        JTextField searchField = new JTextField(10);
        JButton searchButton = new JButton("Search");
        JTextArea resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                resultArea.setText(""); // Clear previous search results

                for (Flight flight : flights) {
                    if (flight.getFlightNumber().equalsIgnoreCase(searchTerm)) {
                        resultArea.append("Flight Number: " + flight.getFlightNumber() + "\n");
                        resultArea.append("Origin: " + flight.getOrigin() + "\n");
                        resultArea.append("Destination: " + flight.getDestination() + "\n");
                        resultArea.append("Departure Time: " + flight.getDepartureTime() + "\n");
                        resultArea.append("Arrival Time: " + flight.getArrivalTime() + "\n\n");
                    }
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchFlightsFrame.dispose();
                mainFrame.setVisible(true);
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchButton);

        searchFlightsFrame.add(panel, BorderLayout.NORTH);
        searchFlightsFrame.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        searchFlightsFrame.add(backButton, BorderLayout.SOUTH);

        searchFlightsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new AirlineManagementSystem();
    }
}

