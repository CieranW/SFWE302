package edu.baylor.cs.junit.demo.app;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import edu.baylor.cs.junit.demo.app.impl.DefaultMusicBox;
import edu.baylor.cs.junit.demo.app.objects.Coin;

/**
 * Tests {@link DefaultMusicBox}
 * @author cerny
 *
 */
public class BoxTester {

	protected IMusicBox box = null;
	
    @BeforeEach
    void init() {
    	box = new DefaultMusicBox();
    }

    @Test
    void feed() {
    	box.insertCoin(Coin.cent);
    	assertEquals(.01f, box.balance(), "Cent expected");
    }

    @Test
    // @Disabled("TODO: need to fix :)... yes you!")
    void feedEmpty() {
        assertThrows(NullPointerException.class, () -> {
            box.insertCoin(null);
        });
    }
    
    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => coins={0}, total={1}")
    @MethodSource("coins")
    void feedParams(Coin[] coins, float total) {
    	for (Coin coin : coins) {
    		box.insertCoin(coin);
		}
    	assertEquals(total, box.balance(), "Different total expected");
    
    }
    
    @SuppressWarnings("unused")
	private static Stream<Arguments> coins() {
    	return Stream.of(
                Arguments.of(new Coin[]{Coin.dollar,Coin.dime,Coin.cent}, 1.11f),
                Arguments.of(new Coin[]{Coin.dollar,Coin.nicle,Coin.cent,Coin.quarter}, 1.31f),
                Arguments.of(new Coin[]{Coin.dollar,Coin.dime, Coin.nicle,Coin.cent,Coin.quarter}, 1.41f)
        );
    }
    
    @Test
    // @Disabled("TODO: need to fix :)... yes you!")
    void playFailHard() {
        assertThrows(Exception.class, () -> {
            box.playSong(-1);
        });
    }
    @Test
    // @Disabled("TODO: need to fix :)... yes you!")
    void playFail() {
        assertThrows(Exception.class, () -> {
            box.playSong(0);
        });
    }
    @Test
    // @Disabled("TODO: need to fix :)... yes you!")
    void playFailAgain() {
        assertThrows(Exception.class, () -> {
            box.playSong(1);
        });

    }
    @Test
    // @Disabled("TODO: need to fix :)... yes you!")
    void playAlmostPass() {
        assertThrows(Exception.class, () -> {
            box.playSong(1);
        });
    }
    @Test
    void playFailPaid() {
    	box.insertCoin(Coin.cent);
    	String out = box.playSong(1);
    	assertEquals("Not enough credit", out);
    }
    @Test
    void playPassPaid() {
    	box.insertCoin(Coin.cent);
    	box.insertCoin(Coin.dollar);
    	String out = box.playSong(1);
    	assertEquals("Playing "+box.listSongs().get(0).getName(),out);
    }
    @Test
    // @Disabled("TODO: need to fix :)... yes you!")
    void correctDeduction() {
    	box.insertCoin(Coin.cent);
    	box.insertCoin(Coin.dollar);
    	String out = box.playSong(1);
    	assertEquals("Playing "+box.listSongs().get(0).getName(),out);
    }
    
}