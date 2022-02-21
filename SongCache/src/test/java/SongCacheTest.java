import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SongCacheTest {
    @Test
    public void cacheIsWorking() {
        SongCache cache = new SongCacheImpl();
        cache.recordSongPlays("ID-1", 3);
        cache.recordSongPlays("ID-1", 1);
        cache.recordSongPlays("ID-2", 2);
        cache.recordSongPlays("ID-3", 5);

//        assertThat(cache.getPlaysForSong("ID-1"), is(4));
//        assertThat(cache.getPlaysForSong("ID-9"), is(-1));
//
//        assertThat(cache.getTopNSongsPlayed(2), contains("ID-3",
//                "ID-1"));
//        assertThat(cache.getTopNSongsPlayed(0), is(empty()));
    }

    @Test
    public void test1() {
        SongCache songs = new SongCacheImpl();

        List<CompletableFuture> fs = new ArrayList<>();
        List<String> names = IntStream.range(1, 11)
                .mapToObj((i) ->"ID-" + i)
                .collect(Collectors.toList());

        for (String name : names){
            for (int i = 0; i < 100; i++) {
                fs.add(CompletableFuture.runAsync(()-> songs.recordSongPlays(name, 10)));
            }
        }

        CompletableFuture.allOf(fs.toArray(new CompletableFuture[fs.size()])).join();
        System.out.println(songs);

        boolean flag =  names.parallelStream()
                .allMatch((name)->songs.getPlaysForSong(name) == 1000);
        assertTrue(flag);
    }
}
