package application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Game2ModelTest {
	Game2Model model = new Game2Model("src/demo.xml");
	@Test
	public void testGetID() {
		if(!(model.getID("free")==0)||!(model.getID("libre")==0)){
			fail("Not correst");			
		}
	}
	@Test
	public void testTileWord() {
		List<String> output = model.TileWord(7);
		List<String> rst = new ArrayList();
		rst.add("libre");rst.add("free");
		rst.add("dog");rst.add("chien");
		rst.add("rain cats and dogs");rst.add("pleuvoir averse");
		rst.add("backpack");rst.add("sac ид dos");
		rst.add("computer");rst.add("ordinateur");
		rst.add("happy");rst.add("content(e)");
		rst.add("my");rst.add("mon");
		if(output.contains(rst)){
			fail("Not yet implemented");			
		}
	}
}
