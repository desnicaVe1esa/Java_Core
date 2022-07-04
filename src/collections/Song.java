package collections;

import java.util.Objects;

public class Song implements Comparable<Song> {
    String title;
    String artist;
    String rating;
    String bpm;

    public Song(String t, String a, String r, String b) {
        title = t;
        artist = a;
        rating = r;
        bpm = b;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getRating() {
        return rating;
    }

    public String getBpm() {
        return bpm;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                "artist='" + artist + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return title.equals(song.title) && artist.equals(song.artist) && rating.equals(song.rating) && bpm.equals(song.bpm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist, rating, bpm);
    }

    @Override
    public int compareTo(Song o) {
        return title.compareTo(o.getTitle());
    }
}
