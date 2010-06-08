/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tuwien.big.memory.utilities;

import java.util.ArrayList;
import java.util.Collection;
import net.roarsoftware.lastfm.Album;
import net.roarsoftware.lastfm.Artist;
import net.roarsoftware.lastfm.ImageSize;
import net.roarsoftware.lastfm.Tag;

/**
 *
 * @author gerhard
 */
public class LastFMRequest {

    private static String apiKey = "9519d31ff23e1f96ba100660428bc26d";

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
    public static ArrayList<Artist> getTopArtists(String genre) throws Exception
    {
        int count =0;
        ArrayList<Artist> artistList = new ArrayList<Artist>();
        //String key = "9519d31ff23e1f96ba100660428bc26d";

        Collection<Artist> topArtists = Tag.getTopArtists(genre, apiKey);
        System.out.println("=========AB HIER topArtists");
        for (Artist artist : topArtists)
        {
            count++;
            System.out.println(artist.getName() + ": " + artist.getImageURL(ImageSize.SMALL));
            //artistList.put(artist.getName(), artist.getImageURL(ImageSize.SMALL));//Groese des Images!
            artistList.add(artist);
            if(count==3)
                break;
        }
        System.out.println("=========BIS HIER" + count + " topArtists");

        return artistList;
     }

    public static ArrayList<String> getMemoryImages(String genre, int cardCount) throws Exception
    {
        ArrayList<String> imagesList = new ArrayList<String>();
        Collection<Album> topAlbums = Tag.getTopAlbums(genre, apiKey);
        int counter = 0;
        for(Album album:topAlbums)
        {
            counter ++;
            imagesList.add(album.getImageURL(ImageSize.MEDIUM));
            System.out.println("IMAGEURL: " + album.getImageURL(ImageSize.MEDIUM));
            if(counter >= cardCount)
                break;
        }
        return imagesList;

    }
}
