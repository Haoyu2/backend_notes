import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SongCacheImpl implements SongCache {
    private volatile ConcurrentHashMap<String, AtomicInteger> songs = new ConcurrentHashMap<>();
    private volatile List<String> songs_ordered;
    ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

    public SongCacheImpl() {
        timer.scheduleAtFixedRate(() -> sortSongs(),
                0L, 2000L, TimeUnit.MILLISECONDS);
    }

    @Override
    public void recordSongPlays(String songId, int numPlays) {
        if (!songs.containsKey(songId)){
            synchronized (songId){
                if (!songs.containsKey(songId)){
                    songs.put(songId, new AtomicInteger(0));
                }
            }
        }
        songs.get(songId).getAndAdd(numPlays);
    }

    @Override
    public int getPlaysForSong(String songId) {
        return songs.get(songId).intValue();
    }

    public void sortSongs() {
        songs_ordered = songs.entrySet().parallelStream()
                .sorted((x, y) -> y.getValue().intValue() - x.getValue().intValue())
                .map((x) -> x.getKey())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getTopNSongsPlayed(int n) {
        if (songs_ordered == null) sortSongs();
        return songs_ordered.subList(0, n);
    }

    @Override
    public String toString() {
        return "SongCacheImpl{" +
                "songs=" + songs +
                '}';
    }
}
