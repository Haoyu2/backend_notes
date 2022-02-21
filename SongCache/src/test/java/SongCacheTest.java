import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
    }


    @Test
    public void test() throws InterruptedException {
        SongCache songs = new SongCacheImpl();


        List<String> names = IntStream.range(1, 11)
                .mapToObj((i) ->"ID-" + i)
                .collect(Collectors.toList());

        List<Callable<Optional>> tasks = new ArrayList<>();
        for (String name : names){
            for (int i = 0; i < 100; i++) {
                tasks.add(()->{songs.recordSongPlays(name,10);return null;});
            }
        }
        Executors.newCachedThreadPool().invokeAll(tasks);
        System.out.println(songs);
        boolean flag =  names.parallelStream()
                .allMatch((name)->songs.getPlaysForSong(name) == 1000);
        assertTrue(flag);
        System.out.println(songs.getTopNSongsPlayed(3));
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
