import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CardInputStream extends InputStream {

    private BufferedReader reader;

    public CardInputStream(InputStream input) {
        this.reader = new BufferedReader(new InputStreamReader(input));
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    public Card readCard() throws IOException {
        String tag = reader.readLine();

        if (tag == null || !tag.equals("CARD")) {
            return null;
        }
        else if (tag.equals("CARD")) {
            long id = Long.parseLong(reader.readLine());
            String name = reader.readLine();
            Rank rank = Rank.valueOf(reader.readLine());
            long price = Long.parseLong(reader.readLine());
            return new Card(id, name, rank);
            
        }

        
        if (tag.equals("OK")) {
            return null;
        }
        else {
            return null;
        }
        
    }


    public String readResponse() throws IOException {
        return reader.readLine();
    }
}

