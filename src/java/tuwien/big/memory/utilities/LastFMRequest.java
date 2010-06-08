/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tuwien.big.memory.utilities;

import java.util.ArrayList;
import java.util.Collection;
import net.roarsoftware.lastfm.Artist;
import net.roarsoftware.lastfm.ImageSize;
import net.roarsoftware.lastfm.Tag;

/**
 *
 * @author gerhard
 */
public class LastFMRequest {

    /*Aufruf:
     *  try
        {
            RegisterControl.LastFmCall("rock");
        } catch (Exception ex)
        {
            System.out.println("Fehler: "+ex.getMessage());
        }
     *
     */
    public static ArrayList<String> getTopArtists(String genre) throws Exception
    {
        int count =0;
        ArrayList<String> linkList = new ArrayList<String>();
        String key = "9519d31ff23e1f96ba100660428bc26d";

        Collection<Artist> topArtists = Tag.getTopArtists(genre, key);
        //System.out.println("=========AB HIER topArtists");
        for (Artist artist : topArtists)
        {
            count++;
            //System.out.println(artist.getName() + ": " + artist.getImageURL(ImageSize.SMALL));
            linkList.add(artist.getImageURL(ImageSize.SMALL));//Groese des Images!
            if(count>=16)
                break;
        }
        //System.out.println("=========BIS HIER" + count + " topArtists");

        return linkList;
     }
}
