
public class PartEClass {
	
	static int sourceHeight = 0;
	static int tempHeight = 0;
	static int targetHeight = 0;
	static int maxBarHeight = 8, h = 1;
	static int firstIteration = 0;
	static int[] sourceHeightArray = new int[sourceHeight];
	static int[] tempHeightArray = new int[tempHeight];
	static int[] targetHeightArray = new int[targetHeight];
	static String armEnd = "target";
	
	// Block heights to be placed in the descending order
	public static void PartE(int[] barHeights, int[] blockHeights,Robot r) {
		solve(blockHeights, "source", "temp", "target", blockHeights, r);
	}

	//recursive method
	public static void solve(int[] blockHeights, String start, String auxiliary,
	  String end, int[]blockheight, Robot r) { 
		if (blockHeights[blockHeights.length-1]== 1) { 
	  moveBlocks(start,end,blockHeights[blockHeights.length-1],blockheight,r); 
	  }
	  else { 
		  solve(PartDClass.removeElements(blockHeights), start, end,auxiliary,blockheight,r); 
		  moveBlocks(start,end,blockHeights[blockHeights.length-1],blockheight,r);
		  solve(PartDClass.removeElements(blockHeights), auxiliary, start, end,blockheight, r); }
	  }

	//moving the block from start to end. This start and end can be any 2 of the places i.e. source, temp and target
	private static void moveBlocks(String start, String end, int reqBlock, int[] blockheight, Robot r) {
		int i, w = 1;
		if (firstIteration == 0) {
			sourceHeightArray = blockheight;
			w = 8;
			firstIteration++;
		}

		sourceHeight = PartDClass.height(sourceHeightArray);
		tempHeight = PartDClass.height(tempHeightArray);
		targetHeight = PartDClass.height(targetHeightArray);
		maxBarHeight = 8;
		h = PartDClass.findHeight(tempHeight, sourceHeight, targetHeight, reqBlock, maxBarHeight, h, 0, r);
		if (start == "target" && end == "temp") {
			if (armEnd.equals("target")) {
				for (i = h; i > targetHeight; i--)
					r.lower();
				r.pick();
				for (i = h; i > targetHeight; i--)
					r.raise();
				w = 7;
				for (i = 0; i <= w; i++)
					r.extend();
				for (i = h; i > tempHeight + reqBlock; i--)
					r.lower();
				r.drop();
				for (i = h; i > tempHeight + reqBlock; i--)
					r.raise();
			}
			targetHeightArray = PartDClass.removeElements(targetHeightArray);
			tempHeightArray = PartDClass.addElements(tempHeightArray, reqBlock);
			armEnd = "temp";
		}
		if (start == "temp" && end == "target") {
			if (armEnd.equals("target"))
				w = 7;
			for (i = 0; i <= w; i++)
				r.extend();
			for (i = h; i > tempHeight; i--)
				r.lower();
			r.pick();
			tempHeightArray = PartDClass.removeElements(tempHeightArray);
			for (i = h; i > tempHeight; i--)
				r.raise();
			w = 1;
			for (i = 0; i <= w; i++)
				r.contract();
			for (i = h; i > targetHeight + reqBlock; i--)
				r.lower();
			r.drop();
			for (i = h; i > targetHeight + reqBlock; i--)
				r.raise();
			targetHeightArray = PartDClass.addElements(targetHeightArray, reqBlock);
			armEnd = "target";
		}
		if (start == "target" && end == "source") {
			if (armEnd.equals("target")) {
				for (i = h; i > targetHeight; i--)
					r.lower();
				r.pick();
				for (i = h; i > targetHeight; i--)
					r.raise();
				w = 7;
				for (i = 0; i <= w; i++)
					r.extend();
				for (i = h; i > sourceHeight + reqBlock; i--)
					r.lower();
				r.drop();
				for (i = h; i > sourceHeight + reqBlock; i--)
					r.raise();
			}
			targetHeightArray = PartDClass.removeElements(targetHeightArray);
			sourceHeightArray = PartDClass.addElements(sourceHeightArray, reqBlock);
			armEnd = "source";
		}
		if (start == "source" && end == "target") {
			if (armEnd.equals("target"))
				w = 8;
			else
				w = 1;
			for (i = 0; i <= w; i++)
				r.extend();
			for (i = h; i > sourceHeight; i--)
				r.lower();
			r.pick();
			sourceHeightArray = PartDClass.removeElements(sourceHeightArray);
			for (i = h; i > sourceHeight; i--)
				r.raise();
			w = 1;
			for (i = 0; i <= w; i++)
				r.contract();
			for (i = h; i > targetHeight + reqBlock; i--)
				r.lower();
			r.drop();
			for (i = h; i > targetHeight + reqBlock; i--)
				r.raise();
			targetHeightArray = PartDClass.addElements(targetHeightArray, reqBlock);
			armEnd = "target";
		}
		if (start == "temp" && end == "source") {
			if (armEnd.equals("target"))
				w = 7;
			else
				w = 1;
			for (i = 0; i <= w; i++)
				r.extend();
			for (i = h; i > tempHeight; i--)
				r.lower();
			r.pick();
			tempHeightArray = PartDClass.removeElements(tempHeightArray);
			for (i = h; i > tempHeight; i--)
				r.raise();
			w = 1;
			for (i = 0; i <= w; i++)
				r.contract();
			for (i = h; i > sourceHeight + reqBlock; i--)
				r.lower();
			r.drop();
			for (i = h; i > sourceHeight + reqBlock; i--)
				r.raise();
			sourceHeightArray = PartDClass.addElements(sourceHeightArray, reqBlock);
			armEnd = "source";
		}
		if (start == "source" && end == "temp") {
			if (armEnd.equals("target"))
				w = 8;
			for (i = 0; i <= w; i++)
				r.extend();
			for (i = h; i > sourceHeight; i--)
				r.lower();
			r.pick();
			sourceHeightArray = PartDClass.removeElements(sourceHeightArray);
			for (i = h; i > sourceHeight; i--)
				r.raise();
			if (armEnd.equals("target"))
				w = 0;
			for (i = 0; i <= w; i++)
				r.contract();
			for (i = h; i > tempHeight + reqBlock; i--)
				r.lower();
			r.drop();
			for (i = h; i > tempHeight + reqBlock; i--)
				r.raise();
			tempHeightArray = PartDClass.addElements(tempHeightArray, reqBlock);
			armEnd = "temp";
		}
	}

}
