package application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Game1ModelTest {
	Game1Model model = new Game1Model("src/demo.xml");
	@Test
	public void testGame1Model() {
		try{
			model = new Game1Model("src/demo.xml");
		}catch(Exception e) {
			fail("Not correct");
		}
	}
	@Test
	public void testShuffleString() {
		String str = "hello";
		List<String> rst = new ArrayList();
		rst.add("h");
		rst.add("e");
		rst.add("l");
		rst.add("l");
		rst.add("o");
		List<String> output = model.shuffleString(str);
		if(!(output.containsAll(rst)&&output.size()==5)){
			fail("Not correct");
		}
	}

}
