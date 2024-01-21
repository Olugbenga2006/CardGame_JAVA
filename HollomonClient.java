import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HollomonClient {
    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public HollomonClient(String server, int port) throws IOException {
        socket = new Socket(server, port);
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }

    public List<Card> login(String username, String password) throws IOException {
        // Send username and password to server
        os.write((username.toLowerCase() + "\n" + password + "\n").getBytes());
        os.flush();

        // Read server response
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String response = reader.readLine();

        // Check if login was successful
        if (response.startsWith("User " + username.toLowerCase() + " logged in successfully.")) {
            // Create a CardInputStream object using the InputStream coming from the socket
            CardInputStream cardInputStream = new CardInputStream(is);

            // Read all cards from the server and add them to the List
            List<Card> cards = new ArrayList<Card>();
            Card card = cardInputStream.readCard();
            while (card != null) {
                cards.add(card);
                card = cardInputStream.readCard();
            }

            // Sort the list using the ordering implemented in the Card class
            Collections.sort(cards);

            // After all cards have been sent, the server sends OK on a single line


            return cards;
        } else {
            return null;
        }
    }
    public long getCredits() throws IOException {
        // Send CREDITS command to server
        os.write("CREDITS\n".getBytes());
        os.flush();

        // Read server response
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String response = reader.readLine();

        // Parse the response as a long integer and return it
        if (response != null ) {
            return Long.parseLong(response);
        } else {
            throw new IOException("Error reading credits from server.");
        }
    }
    public List<Card> getCards() throws IOException {
        // Send CARDS command to server
        os.write("CARDS\n".getBytes());
        os.flush();

        // Read all cards from the server and add them to the List
        CardInputStream cardInputStream = new CardInputStream(is);
        List<Card> cards = new ArrayList<Card>();
        Card card = cardInputStream.readCard();
        while (card != null) {
            cards.add(card);
            card = cardInputStream.readCard();
        }

        // Sort the list using the ordering implemented in the Card class
        Collections.sort(cards);




        return cards;
    }
    public List<Card> getOffers() throws IOException {
        // Send OFFERS command to server
        os.write("OFFERS\n".getBytes());
        os.flush();



        // Create a CardInputStream object using the InputStream coming from the socket
        CardInputStream cardInputStream = new CardInputStream(is);

        // Read all cards from the server and add them to the List
        List<Card> cards = new ArrayList<Card>();
        Card card = cardInputStream.readCard();
        while (card != null) {
            cards.add(card);
            card = cardInputStream.readCard();
        }

        // Sort the list using the ordering implemented in the Card class
        Collections.sort(cards);

        return cards;
    }
    public boolean buyCard(Card card) throws IOException {
        

        // Send BUY command to server
        String command = "BUY " + card.getId() + "\n";
        os.write(command.getBytes());
        os.flush();

        // Read server response
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String response = reader.readLine();

        // Check if buying was successful
        if (response.equals("OK")) {
            return true;
        } else if (response.equals("ERROR")) {
            return false;
        }
        long price = card.getPrice();
        long credits = getCredits();

        if (credits < price) {
            return false;
        }
        else {
            throw new IOException("Unexpected response from server: " + response);
        }
    }

    public boolean sellCard(Card card, long price) throws IOException {
        // Send SELL command to server
        String command = "SELL " + card.getId() + " " + price + "\n";
        os.write(command.getBytes());
        os.flush();

        // Read server response
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String response = reader.readLine();

        // Check if placing the order was successful
        if (response.equals("OK")) {
            return true;
        } else {
            return false;
        }
    }











    public void close() throws IOException {
        if (is != null) {
            is.close();
        }
        if (os != null) {
            os.close();
        }
        if (socket != null) {
            socket.close();
        }
    }
}

