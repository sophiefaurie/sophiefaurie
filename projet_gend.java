import javax.swing.*;
import javafx.event.ActionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class projet_gend extends JFrame {

    private JPanel hostnamePanel;
    private JPanel ipPanel;
    private JPanel statusPanel;
    private JLabel imageLabel;

    // FONCTION pour créer les 3 panneaux d'informations
    private void createInfoPanels(JPanel mainPanel) {
        
        // Panel pour HOSTNAME (Adresse IP)
        hostnamePanel = new JPanel();
        hostnamePanel.setBorder(BorderFactory.createTitledBorder("HOSTNAME"));
        hostnamePanel.setLayout(new BoxLayout(hostnamePanel, BoxLayout.PAGE_AXIS));
    
        // Panel pour ADRESSE IP
        ipPanel = new JPanel();
        ipPanel.setBorder(BorderFactory.createTitledBorder("ADRESSE IP"));
        ipPanel.setLayout(new BoxLayout(ipPanel, BoxLayout.PAGE_AXIS));
    
        // Panel pour ACCESSIBLE
        statusPanel = new JPanel();
        statusPanel.setBorder(BorderFactory.createTitledBorder("ACCESSIBLE"));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.PAGE_AXIS));
    
        // Ajouter les panneaux au mainPanel
        mainPanel.add(hostnamePanel);
        mainPanel.add(ipPanel);
        mainPanel.add(statusPanel);
    }

    // FONCTION pour mettre à jour les informations pour Caserne Jouan ou Jourdan
    private void updateInfoPanel(String caserne) {
        clearInfoPanel();

        List<String> ipAddresses = new ArrayList<>();
        List<String> hostnames = new ArrayList<>();

        if ("Jouan".equals(caserne)) {
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            
            hostnames.add("GGD087ER000001");
            hostnames.add("GGD087ER000002");
            hostnames.add("GGD087ER000003");
            hostnames.add("GGD087ER000004");
            hostnames.add("GGD087ER000005");
            hostnames.add("GGD087ER000006");
            hostnames.add("GGD087ER000007");
            hostnames.add("GGD087ER000010");
            hostnames.add("GGD087ER000020");
            hostnames.add("GGD087ER000021");
            hostnames.add("GGD087ER000025");


        } else if ("Jourdan".equals(caserne)) {
            ipAddresses.add("1.1.1.1");
            ipAddresses.add("8.8.8.8");
            ipAddresses.add("9.9.9.9");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            ipAddresses.add("33.X.X.X");
            
            hostnames.add("GGD087ER000101");
            hostnames.add("GGD087ER000121");
            hostnames.add("GGD087ER000131");
            hostnames.add("GGD087ER000102");
            hostnames.add("GGD087ER000122");
            hostnames.add("GGD087ER000103");
            hostnames.add("GGD087ER000104");
            hostnames.add("GGD087ER000124");
            hostnames.add("GGD087ER000111");
            hostnames.add("GGD087ER000106");
            hostnames.add("GGD087ER000107");
            hostnames.add("GGD087ER000108");
            hostnames.add("GGD087ER000109");
            hostnames.add("GGD087ER000110");
        }

        // Mettre à jour les informations avec les nouvelles données
        for (int i = 0; i < hostnames.size(); i++) {
            JLabel hostnameLabel = new JLabel(hostnames.get(i));
            JLabel ipLabel = new JLabel(ipAddresses.get(i));

            // Vérifie l'accessibilité de l'adresse IP
            try {
                if (ping(ipAddresses.get(i))) {
                    JLabel statusLabel = new JLabel("OK");
                    statusLabel.setForeground(Color.GREEN);
                    statusPanel.add(statusLabel);
                } else {
                    JLabel statusLabel = new JLabel("Échec");
                    statusLabel.setForeground(Color.RED);
                    statusPanel.add(statusLabel);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // Ajouter les labels au panneau correspondant
            hostnamePanel.add(hostnameLabel);
            ipPanel.add(ipLabel);
        }

        // Rafraîchir l'affichage
        revalidate();
        repaint();
    }

    // FONCTION pour effacer les informations des panneaux
    private void clearInfoPanel() {
        hostnamePanel.removeAll();
        ipPanel.removeAll();
        statusPanel.removeAll();
    }

    // FONCTION pour effectuer un ping
    private boolean ping(String ipAddress) {
        try {
            Process process = Runtime.getRuntime().exec("ping " + ipAddress);
            int returnCode = process.waitFor();
            return returnCode == 0;
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // FONCTION pour actualiser la fenêtre
    private void refresh() {
        dispose();
        new projet_gend();
    }


    public projet_gend() {
        super("Visualisation des commutateurs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création du panneau principal
        JPanel panel = new JPanel(new BorderLayout());

        // En-tête
        JPanel headerPanel = new JPanel();
        headerPanel.add(new JLabel("Visualisation des commutateurs"));
        panel.add(headerPanel, BorderLayout.NORTH);

        // Menu de navigation
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS)); 

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int navPanelWidth = (int) (screenSize.getWidth() * 0.5); 

        navPanel.setPreferredSize(new Dimension(navPanelWidth, (int) screenSize.getHeight()));

        // Première ligne avec les boutons "Caserne Jourdan" et "Caserne Jouan"
        JPanel buttonsPanel = new JPanel(new FlowLayout());  

        JButton caserneJourdanButton = new JButton("Caserne Jourdan");
        JButton caserneJouanButton = new JButton("Caserne Jouan");
        JButton vueCarteJourdanButton = new JButton("Vue Carte Jourdan");
        JButton vueCarteJouanButton = new JButton("Vue Carte Jouan");

        caserneJourdanButton.addActionListener(e -> updateInfoPanel("Jourdan"));
        caserneJouanButton.addActionListener(e -> updateInfoPanel("Jouan"));
        vueCarteJourdanButton.addActionListener(e -> showImage("C:\\Users\\sophi\\Documents\\synoptique_jourdan.png"));
        vueCarteJouanButton.addActionListener(e -> showImage("C:\\Users\\sophi\\Documents\\synoptique_jouan.png"));

        buttonsPanel.add(caserneJourdanButton);
        buttonsPanel.add(caserneJouanButton);
        buttonsPanel.add(vueCarteJourdanButton);
        buttonsPanel.add(vueCarteJouanButton);

        // Ajouter les boutons au navPanel
        navPanel.add(buttonsPanel);

        // Ajouter le navPanel au panneau principal
        panel.add(navPanel, BorderLayout.WEST);

        // Contenu principal
        JPanel mainPanel = new JPanel(new GridLayout(1, 3)); 
        createInfoPanels(mainPanel);
        // Ajouter le mainPanel au panneau principal
        panel.add(mainPanel, BorderLayout.CENTER);        

        // Ajouter un JLabel pour afficher l'image dans le navPanel
        imageLabel = new JLabel();
        navPanel.add(imageLabel);

        // Pied de page
        JPanel footerPanel = new JPanel(new BorderLayout());

        // Copyright aligné à droite
        JPanel copyrightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        copyrightPanel.add(new JLabel("Copyright 2024 © Sophie FAURIE"));

        // Bouton "Actualiser" aligné à gauche
        JPanel refreshPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton refreshButton = new JButton("Actualiser");
        refreshButton.addActionListener(e -> {
            refresh();
        });
        refreshPanel.add(refreshButton);

        footerPanel.add(copyrightPanel, BorderLayout.WEST); // Aligné à droite
        footerPanel.add(refreshPanel, BorderLayout.EAST); // Aligné à gauche

        panel.add(footerPanel, BorderLayout.SOUTH); 

        add(panel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);  
        pack(); 
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Afficher l'image dans une nouvelle fenêtre
    public class ImageViewer extends JFrame {

        public ImageViewer(String imagePath) {
            super("Vue Carte");
    
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image originalImage = originalIcon.getImage();
    
            // Taille de l'écran
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = (int) screenSize.getWidth();
            int screenHeight = (int) screenSize.getHeight();
    
            // Utiliser la résolution de l'écran comme dimensions souhaitées
            int desiredWidth = screenWidth;
            int desiredHeight = screenHeight;
    
            // Redimensionner l'image
            Image resizedImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
    
            // Créez un ImageIcon à partir de l'image redimensionnée
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
    
            JLabel imageLabel = new JLabel(resizedIcon);
    
            add(imageLabel);
    
            // Taille de la fenêtre
            setSize(desiredWidth, desiredHeight);
    
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
        }
    }

    // FONCTION pour afficher l'image
    private void showImage(String imagePath) {
        new ImageViewer(imagePath);
    }    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(projet_gend::new);
    }

} 
