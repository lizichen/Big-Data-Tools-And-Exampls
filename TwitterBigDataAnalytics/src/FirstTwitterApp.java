import sun.font.TrueTypeFont;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by lizichen1 on 2/28/17.
 */
public class FirstTwitterApp {

    private static final String FILENAME = "myTwitterTimeline.txt";


    public static void main(String[] args) throws TwitterException {

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter(FILENAME);
            bw = new BufferedWriter(fw);

            String content;

            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("kdh5WlmXvMmBDEClYvv2yMAdw")
                    .setOAuthConsumerSecret("TNjzKqeoU50zdmEFgjybtiZXU9Cys2EBDg7UzgioYxWH2BHFE0")
                    .setOAuthAccessToken("368169901-H6ZBqQOWU7HlQoDzjzdyaFAeeY80j7rOvoeN7rXu")
                    .setOAuthAccessTokenSecret("4UdXOHNDfhn10dFtIy0jWi6YWMUjixtJ7GOx6WbGjPHBI");

            TwitterFactory rf = new TwitterFactory(cb.build());
            twitter4j.Twitter twitter = rf.getInstance();

            List<Status> status = twitter.getHomeTimeline();
            for(Status st : status){
                content = st.getUser().getName()+" ---- "+st.getText() + "\n";
                bw.write(content);
            }

            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }




    }
}
