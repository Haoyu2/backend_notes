import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SongCacheImpl implements SongCache {
    ConcurrentHashMap<String, AtomicInteger> songs = new ConcurrentHashMap<>();

    @Override
    public void recordSongPlays(String songId, int numPlays) {
        songs.putIfAbsent(songId, new AtomicInteger(0));
        songs.get(songId).getAndAdd(numPlays);
    }

    @Override
    public int getPlaysForSong(String songId) {
        return songs.get(songId).intValue();
    }

    @Override
    public List<String> getTopNSongsPlayed(int n) {
        return songs.entrySet().stream()
                .sorted((x, y)->x.getValue().intValue() - x.getValue().intValue())
                .map((x)->x.getKey())
                .collect(Collectors.toList())
                .subList(0, n);
    }

    @Override
    public String toString() {
        return "SongCacheImpl{" +
                "songs=" + songs +
                '}';
    }
}
