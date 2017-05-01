// Use the method r.up(), r.down(), 
//r.extend(), r.contract(), 
//r.raise(), r.lower(), 
//r.pick(), r.drop()

class RobotControl {
	private Robot r;

	public RobotControl(Robot r) {
		this.r = r;
	}

	public void control(int barHeights[], int blockHeights[], int required[], boolean ordered) {
		// PartA();
		PartB(barHeights);
	}

	// Part B : Variable bar heights but fixed block heights
	private void PartB(int barHeights[]) {
		int targetBlockHeight = 0, sourceBlockHeight = 8, maxBarHeight = 0, h = 0, w = 8, d = 0, blockHeight = 2;
		for (int i = 0; i < barHeights.length; i++)
			maxBarHeight = (barHeights[i] > maxBarHeight) ? barHeights[i] : maxBarHeight;
		int i, j, k;
		h = (sourceBlockHeight > maxBarHeight) ? sourceBlockHeight-1 : maxBarHeight + 1;

		for (i = 0; i < h; i++)
			r.up();
		//h++;
		for (i = 0; i < 4; i++) {
			for (j = 0; j <= w; j++)
				r.extend();
			for (j = h; j > sourceBlockHeight-1; j--)
				r.lower();
			r.pick();
			for (j = h; j > sourceBlockHeight-1; j--)
				r.raise();
			//targetBlockHeight += blockHeight;
			sourceBlockHeight -= blockHeight;
			for (j = 0; j <= w; j++)
				r.contract();
			for (j = h; j > targetBlockHeight+1; j--)
				r.lower();
			r.drop();
			targetBlockHeight += blockHeight;
			for (j = h; j > targetBlockHeight-1; j--)
				r.raise();
			if ((sourceBlockHeight == targetBlockHeight && h == targetBlockHeight)||(h == targetBlockHeight && targetBlockHeight != 8) || (h-targetBlockHeight<1 && targetBlockHeight != 8)) {
				h += blockHeight;
				for (j = 0; j < blockHeight; j++)
					r.up();
			} 
			if (sourceBlockHeight < h && maxBarHeight + blockHeight < h && targetBlockHeight != h && h-targetBlockHeight >blockHeight && h-targetBlockHeight-2>=2) {
				int oldh = h;
				h = (sourceBlockHeight > maxBarHeight) ? sourceBlockHeight - 1 : maxBarHeight + blockHeight;
				for (j = oldh; j > h; j--)
					r.down();
			} 
		}
		/*
		 * int height = 8, width = 8, depth = 0; int i, j, k; for (i = 0; i <
		 * barHeights.length; i++) { if (barHeights[i] > height) height =
		 * barHeights[i]; } height++; for (i = 0; i <= height; i++) r.up();
		 * r.lower(); for (j = 2, k = 8; j <= 8; j = j + 2, k = k - 2) { for (i
		 * = 0; i <= width; i++) r.extend(); for (i = 0; i < depth + j; i++)
		 * r.lower(); r.pick(); for (i = 0; i < depth + j; i++) r.raise(); for
		 * (i = 0; i <= width; i++) r.contract(); for (i = 0; i < depth + k;
		 * i++) r.lower(); r.drop(); for (i = 0; i < depth + k; i++) r.raise();
		 * } for (i = 0; i < 2; i++) r.down();
		 */

	}

	// Part A : Fixed bar heights and block heights
	private void PartA() {
		int targetBlockHeight = 0, sourceBlockHeight = 8, maxBarHeight = 4, h = 0, w = 8, d = 0, blockHeight = 2;
		int i, j, k;
		h = (sourceBlockHeight > maxBarHeight) ? sourceBlockHeight : maxBarHeight;

		for (i = 0; i < h - 1; i++)
			r.up();
		for (i = 0; i < 4; i++) {
			for (j = 0; j <= w; j++)
				r.extend();
			for (j = h; j > sourceBlockHeight; j--)
				r.lower();
			r.pick();
			for (j = h; j > sourceBlockHeight; j--)
				r.raise();
			targetBlockHeight += blockHeight;
			sourceBlockHeight -= blockHeight;
			for (j = 0; j <= w; j++)
				r.contract();
			for (j = h; j > targetBlockHeight; j--)
				r.lower();
			r.drop();
			for (j = h; j > targetBlockHeight; j--)
				r.raise();
			if (sourceBlockHeight < h && maxBarHeight + blockHeight < h && targetBlockHeight != h) {
				int oldh = h;
				h = (sourceBlockHeight > maxBarHeight) ? sourceBlockHeight : maxBarHeight + blockHeight;
				for (j = oldh; j > h; j--)
					r.down();
			} else if (h == targetBlockHeight && targetBlockHeight != 8) {
				h += blockHeight;
				for (j = 0; j < blockHeight; j++)
					r.up();
			}
		}

		/*
		 * height = (totalBlocksHeight > maxBarHeight) ? totalBlocksHeight :
		 * maxBarHeight; for (i = 0; i < height - 1; i++) r.up();
		 * 
		 * for (j = 0; j < 3; j++) { for (i = 0; i <= width; i++) r.extend();
		 * for(i=height; i>remainingBlockHeight;i--) r.lower(); r.pick(); for (i
		 * = 0; i <= width; i++) r.contract(); for (i = height; i > blockHeight;
		 * i--) r.lower(); r.drop(); for (i = 0; i < height - blockHeight; i++)
		 * r.raise(); totalBlocksHeight = totalBlocksHeight - blockHeight;
		 * height1 = height; height = (totalBlocksHeight > maxBarHeight) ?
		 * totalBlocksHeight : maxBarHeight; if (height <= height1) for (i =
		 * height; i > height - 2; i--) r.down();
		 * totalBlocksHeight=totalBlocksHeight+2;
		 * remainingBlockHeight=remainingBlockHeight-2; }
		 */

		/*
		 * for (i = 0; i <= height; i++) r.up(); r.lower(); for (j = 0, k = 6; j
		 * <= 6; j = j + 2, k = k - 2) { for (i = 0; i <= width; i++)
		 * r.extend(); for (i = 0; i < depth + j; i++) r.lower(); r.pick(); for
		 * (i = 0; i < depth + j; i++) r.raise(); for (i = 0; i <= width; i++)
		 * r.contract(); for (i = 0; i < depth + k; i++) r.lower(); r.drop();
		 * for (i = 0; i < depth + k; i++) r.raise(); }
		 */

	}
}
